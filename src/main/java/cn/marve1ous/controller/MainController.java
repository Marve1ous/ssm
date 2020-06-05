package cn.marve1ous.controller;

import cn.marve1ous.dao.LessontestMapper;
import cn.marve1ous.model.*;
import cn.marve1ous.service.StudentService;
import cn.marve1ous.service.UserService;
import cn.marve1ous.util.UserUtil;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;
    private static final Logger logger = Logger.getLogger(User.class);

    // 登录
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, String> login(@RequestBody Map<String, String> map) {
        Subject subject = SecurityUtils.getSubject();
        String info = "welcome!";
        if (subject.isAuthenticated()) {
            HashMap<String, String> res = new HashMap<>();
            res.put("message", "请勿多次登录");
            return res;
        }
        String userId = map.get("id");
        String password = map.get("pwd");
        HashMap<String, String> result = new HashMap<>();
        UsernamePasswordToken token = new UsernamePasswordToken(userId, password);
        try {
            subject.login(token);
            User user = userService.selectById(userId);
            String role = "";
            for (String tmp : userService.getUserRole(userId))
                role = tmp;
            result.put("role", role);
            result.put("userId", userId);
            result.put("userName", user.getUsername());
            result.put("status", "success");
        } catch (IncorrectCredentialsException e) {
            String log = "登录密码错误. Password for account " + token.getPrincipal() + " was incorrect.";
            logger.info(log);
            info = "登录密码错误";
        } catch (ExcessiveAttemptsException e) {
            String log = "登录失败次数过多";
            logger.info(log);
            info = "登录失败次数过多";
        } catch (LockedAccountException e) {
            String log = "帐号已被锁定. The account for username " + token.getPrincipal() + " was locked.";
            logger.info(log);
            info = "帐号已被锁定";
        } catch (DisabledAccountException e) {
            String log = "帐号已被禁用. The account for username " + token.getPrincipal() + " was disabled.";
            logger.info(log);
            info = "帐号已被禁用";
        } catch (ExpiredCredentialsException e) {
            String log = "帐号已过期. the account for username " + token.getPrincipal() + "  was expired.";
            logger.info(log);
            info = "帐号已过期";
        } catch (UnknownAccountException e) {
            String log = "帐号不存在. There is no user with username of " + token.getPrincipal();
            logger.info(log);
            info = "帐号不存在";
        }
        result.put("info", info);
        return result;
    }

    // 登出
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String out() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated())
            subject.logout();
        // 重定向redirect
        return "redirect:sign-in.html";
    }

    // 修改密码
    // ***只能修改自己的密码
    @RequestMapping(value = "changepwd", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, String> changePwd(@RequestBody Map<String, String> map) {
        // String username = map.get("id"); 不能接受id，防止修改别人的密码
        // old
        String userpwd = map.get("pwd");
        // new
        String userpwd2 = map.get("pwd2");
        HashMap<String, String> hashMap = new HashMap<>();
        String result = "";
        try {
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            User user = userService.selectById(username);
            String pwd = user.getUserpwd();
            if (pwd.equals(userpwd)) {
                user.setUserpwd(userpwd2);
                int r = userService.updatePwd(user);
                if (r != 0)
                    result = "更改成功";
                else result = "出现错误，请重试";
            } else result = "原密码错误";
        } catch (Exception e) {
            e.printStackTrace();
            result = "更改失败";
        }
        hashMap.put("result", result);
        return hashMap;
    }

    // 找回密码 for --> Student表
    @RequestMapping(value = "found", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, String> found(@RequestBody Map<String, String> map) {
        String userid = map.get("id");
        String username = map.get("name");
        String usercard = map.get("card");
        HashMap<String, String> hashMap = new HashMap<>();
        String result;
        String state;
        int i = 0;
        User user = userService.selectById(userid);
        Student student = studentService.selectByID(userid);
        if (user != null) {
            // 判断信息
            if (user.getUsername().equals(username) && student.getStucard().equals(usercard)) {
                user.setUserpwd(userid);
                i = userService.updatePwd(user);
            }
        }
        if (i != 0) {
            result = "密码重置成功，您的密码与您的账号相同";
            state = "true";
        }
        else {
            result = "信息有误，请重新输入";
            state = "false";
        }
        hashMap.put("result", result);
        hashMap.put("state", state);
        return hashMap;
    }

//    @RequestMapping("unauth")
//    @ResponseBody
//    public HashMap<String, String> unAuth() {
//        String result = "You do not have the permission";
//        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("result", result);
//        return hashMap;
//    }

    // 未认证
    @RequestMapping(value = "unauth")
    public String unAuth() {
        return "redirect:500.html";
    }

    @RequestMapping(value = "info")
    @ResponseBody
    public HashMap<String, Object> getMyInfo() {
        HashMap<String, Object> hashMap = new HashMap<>();
        Student student;
        Subject subject = SecurityUtils.getSubject();
        String userid = (String) subject.getPrincipal();
        if (subject.hasRole("student")) {
            student = studentService.selectByID(userid);
            hashMap.put("info", student);
            hashMap.put("role", "student");
        } else {
            User user = userService.selectById(userid);
            HashMap<String, String> map = new HashMap<>();
            map.put("stuid", userid);
            map.put("stuname", user.getUsername());
            hashMap.put("info", map);
            if (subject.hasRole("teacher"))
                hashMap.put("role", "teacher");
            else hashMap.put("role", "admin");
        }
        return hashMap;
    }


    // for admin
    @RequestMapping(value = "studentInsert", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, String> insertStu(@RequestBody Map<String, String> map) {
        String name = map.get("name");
        String sex = map.get("sex");
        String nation = map.get("nation");
        String card = map.get("card");
        String year = map.get("year");
        HashMap<String, String> hashMap = new HashMap<>();
        String result;

        if (name == null || sex == null || nation == null || year == null)
            result = "请填写全部信息";
        else if (!Pattern.compile("[0-9]+").matcher(sex).matches() && Pattern.compile("[0-9]+").matcher(year).matches())
            result = "请填写正确的内容";
        else {
            int ye = Integer.parseInt(year);
            int se = Integer.parseInt(year);
            if (studentService.searchByCard(card) != null)
                result = "该学籍信息已注册";
            else try {
                int id;
                if (studentService.anyBody(ye) == 1)
                    id = ye * 1000 + 1;
                else id = studentService.getID(ye);
                // 判断目前是否有学生
                String classid = String.valueOf(year) + "00";
                if (new Random().nextInt(2) != 0)
                    classid += "1";
                else classid += "2";
                int t = studentService.insert(new Student(String.valueOf(id), name, classid, se, nation, card, ye));
                if (t != 0)
                    result = "学籍信息导入成功";
                else result = "学籍信息导入失败";
            } catch (Exception e) {
                e.printStackTrace();
                result = "学籍信息导入失败";
            }
        }
        hashMap.put("result", result);
        return hashMap;
    }

    // for admin
    @RequestMapping(value = "studentModify", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, String> modifyStu(@RequestBody Map<String, String> map) {
        // required
        String id = map.get("id");
        String name = map.get("name");
        String classid = map.get("classid");
        String sex = map.get("sex");
        String nation = map.get("nation");
        String card = map.get("card");
        String year = map.get("year");
        String result;
        HashMap<String, String> hashMap = new HashMap<>();

        if (id == null || name == null || classid == null || sex == null || nation == null || year == null)
            result = "请填写全部信息";
        else if (!Pattern.compile("[0-9]+").matcher(sex).matches() && Pattern.compile("[0-9]+").matcher(year).matches())
            result = "请填写正确的内容";
        else {
            Student student = new Student(id, name, classid, Integer.parseInt(sex), nation, card, Integer.parseInt(year));
            int r = studentService.updateStudent(student);

            if (r != 0)
                result = "学籍修改成功";
            else result = "学籍修改失败";
        }
        hashMap.put("result", result);
        return hashMap;
    }

    //for admin
    @RequestMapping(value = "getStudent", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> getStudent(@RequestBody Map<String, String> map) {
        HashMap<String, Object> hashMap = new HashMap<>();
        String id = map.get("id");
        String name = map.get("name");
        String classid = map.get("classid");
        String sex = map.get("sex");
        String nation = map.get("nation");
        String card = map.get("card");
        String year = map.get("year");
        String page = map.get("page");
        int se;
        int ye;
        int pg;
        if (sex == null || !Pattern.compile("[0-9]+").matcher(sex).matches()) se = 0;
        else se = Integer.parseInt(sex);
        if (year == null || !Pattern.compile("[0-9]+").matcher(year).matches()) ye = 0;
        else ye = Integer.parseInt(year);
        if (page == null || !Pattern.compile("[0-9]+").matcher(page).matches()) pg = 0;
        else pg = Integer.parseInt(page);
        List<Student> stuList = studentService.searchAll(id, name, classid, se, nation, card, ye, pg);
        hashMap.put("page", pg+1);
        hashMap.put("list", stuList);
        return hashMap;
    }

    // for student
    @RequestMapping(value = "getGrade", method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String, Object> getMyGrade() {
        HashMap<String, Object> hashMap = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        String userid = (String) subject.getPrincipal();
        List<Stugrade> stugrades = studentService.selectGrade(userid);
        hashMap.put("list", stugrades);
        return hashMap;
    }


    // for teacher and admin
    @RequestMapping(value = "getMyStudent", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> getMyStudent(@RequestBody Map<String, String> map) {
        HashMap<String, Object> hashMap = new HashMap<>();
        // 条件查询（模糊）
        String page = map.get("page");
        int pg;
        if (page == null) pg = 0;
        else pg = Integer.parseInt(page);
        Subject subject = SecurityUtils.getSubject();
        String userid = (String) subject.getPrincipal();
        List<Map<String, Object>> list;
        // tid --> lesson --> sid
        if (subject.hasRole("teacher"))
            list = studentService.forTeacher(userid, pg);
        else list = studentService.forAdmin(pg);
        hashMap.put("list", list);
        return hashMap;
    }

    // for admin and teacher
    @RequestMapping(value = "uploadGrade", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, String> uploadGrade(@RequestBody Map<String, String> map) {
        HashMap<String, String> hashMap = new HashMap<>();
        String testid = map.get("testid");
        String stuid = map.get("stuid");
        String lessonid = map.get("lessonid");
        String grade = map.get("grade");
        // 判断是编辑还是提交
        String method = map.get("method");
        String result;
        Student student = studentService.selectByID(stuid);
        int exist = studentService.existTest(testid);
        if (testid == null || stuid == null || lessonid == null || grade == null || method == null) {
            result = "信息不完整";
        } else if (!Pattern.compile("[0-9]+").matcher(grade).matches()) {
            result = "成绩应为整型数据";
        } else if (student == null) result="填入的信息有误";
        else if (exist==1) {
            result = "考试信息不存在";
        }
        else {
            Stugrade stugrade = new Stugrade(testid, stuid, lessonid, Integer.valueOf(grade));
            // 判断是否为自己的课，不能提交其他科目的成绩
            Subject subject = SecurityUtils.getSubject();
            String tid = (String) subject.getPrincipal();
            List<String> lesson = studentService.hasLesson(stuid);
            String stuclassid = student.getStuclassid();
            List<String> teacherclass = studentService.tClass(tid);
            List<String> teacherlesson = studentService.tLesson(tid);
            if ((lesson.contains(lessonid) && teacherlesson.contains(lessonid) && teacherclass.contains(stuclassid)) || subject.hasRole("admin")) {
                if (method.equals("insert")) {
                    if (studentService.existGrade(stugrade) != 1) {
                        result = "该学生成绩已提交，不要重复提交";
                    } else {
                        int i = studentService.insertGrade(stugrade);
                        if (i != 0)
                            result = "提交成功";
                        else result = "提交失败";
                    }
                } else if (method.equals("update")) {
                    int i = studentService.updateGrade(stugrade);
                    if (i != 0)
                        result = "提交成功";
                    else result = "提交失败";
                } else result = "请选择类型";
            } else result = "你无权进行此操作";
        }

        hashMap.put("result", result);
        return hashMap;
    }

    // |  |  |
    // V  V  V
    // for admin
    @RequestMapping(value = "resultInsert", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> resultInsert(@RequestBody Map<String, String> map) {
        HashMap<String, Object> hashMap = new HashMap<>();
        String resultid = UserUtil.getUU();
        String resultname = map.get("resultname");
        String stuid = map.get("id");
        String resulttype = map.get("type");
        String resultact = map.get("act");
        String result;
        if (resultname == null || stuid == null || resulttype == null || resultact == null)
            result = "请填写全部信息";
        else if (!Pattern.compile("[0-9]+").matcher(resulttype).matches())
            result = "请填写正确的信息";
        else {
            int type = Integer.parseInt(resulttype);
            Student student = studentService.selectByID(stuid);
            if(student!=null) {
                int i = studentService.insertResult(new Result(resultid, resultname, stuid, type, resultact));
                if (i != 0)
                    result = "奖惩信息添加成功";
                else result = "添加失败";
            }else result = "该学号不存在";
        }
        hashMap.put("result", result);
        return hashMap;
    }


    @RequestMapping(value = "resultSearch", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> awardSearch(@RequestBody Map<String, String> map) {
        HashMap<String, Object> hashMap = new HashMap<>();
        String id = map.get("id");
        String name = map.get("name");
        String classid = map.get("classid");
        String sex = map.get("sex");
        String nation = map.get("nation");
        String card = map.get("card");
        String year = map.get("year");
        String page = map.get("page");
        int se;
        int ye;
        int pg;
        if (sex == null || !Pattern.compile("[0-9]+").matcher(sex).matches()) se = 0;
        else se = Integer.parseInt(sex);
        if (year == null || !Pattern.compile("[0-9]+").matcher(year).matches()) ye = 0;
        else ye = Integer.parseInt(year);
        if (page == null || !Pattern.compile("[0-9]+").matcher(page).matches()) pg = 0;
        else pg = Integer.parseInt(page);
        List<Result> result = studentService.selectResult(id, name, classid, se, nation, card, ye, pg);
        hashMap.put("list", result);
        return hashMap;
    }

    @RequestMapping(value = "resultManage", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, String> awardManage(@RequestBody Map<String, String> map) {
        HashMap<String, String> hashMap = new HashMap<>();
        String result;

        String method = map.get("method");
        String resultid = map.get("resultid");
        String resultname = map.get("resultname");
        String stuid = map.get("id");
        String resulttype = map.get("type");
        String resultact = map.get("act");

        if (method.equals("update")) {

            if (resultid == null || resultname == null || stuid == null || resulttype == null || resultact == null)
                result = "请填写全部信息";
            else if (!Pattern.compile("[0-9]+").matcher(resulttype).matches())
                result = "请填写正确的信息";
            else {

                int type = Integer.parseInt(resulttype);
                int i = studentService.updateResult(new Result(resultid, resultname, stuid, type, resultact));
                if (i == 1)
                    result = "奖惩信息修改成功";
                else if (i == -1)
                    result = "本学期无考试";
                else result = "修改失败";
            }
        } else if (method.equals("delete")) {
            if (resultid == null)
                result = "请填写全部信息";
            else {
                int i = studentService.existResult(resultid);
                if (i != 0) result = "不存在该项";
                else {
                    int j = studentService.deleteResult(resultid);
                    if (j != 0)
                        result = "删除成功";
                    else result = "删除失败";
                }
            }
        } else result = "请求方法错误";
        hashMap.put("result", result);
        return hashMap;
    }

    @RequestMapping(value = "turnInfo", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> turnInfo(@RequestBody Map<String, String> map) {
        HashMap<String, Object> hashMap = new HashMap<>();
        String id = map.get("id");
        String name = map.get("name");
        String classid = map.get("classid");
        String sex = map.get("sex");
        String nation = map.get("nation");
        String card = map.get("card");
        String year = map.get("year");
        String page = map.get("page");
        int se;
        int ye;
        int pg;
        if (sex == null || !Pattern.compile("[0-9]+").matcher(sex).matches()) se = 0;
        else se = Integer.parseInt(sex);
        if (year == null || !Pattern.compile("[0-9]+").matcher(year).matches()) ye = 0;
        else ye = Integer.parseInt(year);
        if (page == null || !Pattern.compile("[0-9]+").matcher(page).matches()) pg = 0;
        else pg = Integer.parseInt(page);
        List<Turn> turns = studentService.turnInfo(id, name, classid, se, nation, card, ye, pg);
        hashMap.put("list", turns);
        hashMap.put("page", pg+1);
        return hashMap;
    }

    @RequestMapping(value = "turnDo", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> turnDo(@RequestBody Map<String, String> map) {
        HashMap<String, Object> hashMap = new HashMap<>();
        String result;
        String turnid = UserUtil.getUU();
        String turnname = map.get("turnname");
        String stuid = map.get("stuid");
        if (turnname == null || stuid == null)
            result = "请填写所有信息";
        else {
            Student student = studentService.searchByCard(stuid);
            if (student == null)
                result = "学生不存在";
            else {
                int i = studentService.insertTurn(new Turn(turnid, turnname, stuid));
                if (i != 0)
                    result = "操作成功";
                else result = "操作失败";
            }
        }
        hashMap.put("result", result);
        return hashMap;
    }

    @RequestMapping(value = "turnAll", method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String, Object> turnAll() {
        HashMap<String, Object> hashMap = new HashMap<>();
        HashMap<String, Integer> map = studentService.countMap();
        hashMap.put("list", map);
        return hashMap;
    }

}
