package cn.marve1ous.controller;

import cn.marve1ous.model.User;
import cn.marve1ous.service.UserService;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    private static Logger logger = Logger.getLogger(User.class);

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public HashMap login(@RequestParam("userId") String userId, @RequestParam("password") String password) {
//        String userId = map.get("userId");
//        String password = map.get("password");
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userId, password);
        HashMap<String, String> result = new HashMap<>();
        try {
            subject.login(token);
            User user = userService.selectById(Integer.parseInt(userId));
            result.put("userId", userId);
            result.put("userName", user.getName());
            result.put("status", "success");
        } catch (IncorrectCredentialsException e) {
            String log = "登录密码错误. Password for account " + token.getPrincipal() + " was incorrect.";
            logger.info(log);
            result.put("userId", userId);
            result.put("status", "fail");
        } catch (ExcessiveAttemptsException e) {
            String log = "登录失败次数过多";
            logger.info(log);
        } catch (LockedAccountException e) {
            String log = "帐号已被锁定. The account for username " + token.getPrincipal() + " was locked.";
            logger.info(log);
        } catch (DisabledAccountException e) {
            String log = "帐号已被禁用. The account for username " + token.getPrincipal() + " was disabled.";
            logger.info(log);
        } catch (ExpiredCredentialsException e) {
            String log = "帐号已过期. the account for username " + token.getPrincipal() + "  was expired.";
            logger.info(log);
        } catch (UnknownAccountException e) {
            String log = "帐号不存在. There is no user with username of " + token.getPrincipal();
            logger.info(log);
        }
        return result;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String out() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated())
            subject.logout();
        return "redirect:index.html";
    }

    /*
    PathVariable 路径上的值 /abc5
    RequestParam 提交的值  /abc?x=5
     */
    @RequestMapping("/get{id}")
    @ResponseBody
    public HashMap selectById(@PathVariable int id) {
        HashMap<String, User> hashMap = new HashMap<>();
        User user;
        try {
            user = userService.selectById(id);
            hashMap.put("info", user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashMap;
    }

    @RequestMapping("/all")
    @ResponseBody
    public HashMap getAll() {
        HashMap<String, ArrayList> hashMap = new HashMap<>();
        ArrayList<User> arrayList;
        try {
            arrayList = userService.all();
            hashMap.put("all", arrayList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashMap;
    }

    @RequestMapping("unauth")
    @ResponseBody
    public HashMap unAuth() {
        String msg = "You do not have the permission.";
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("msg", msg);
        return hashMap;
    }
}
