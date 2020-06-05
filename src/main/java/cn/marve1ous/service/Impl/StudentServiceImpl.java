package cn.marve1ous.service.Impl;

import cn.marve1ous.dao.*;
import cn.marve1ous.model.*;
import cn.marve1ous.service.StudentService;
import cn.marve1ous.util.UserUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private StudentbakMapper studentbakMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserroleMapper userroleMapper;
    @Resource
    private TurnMapper turnMapper;

    @Override
    public Student selectByID(String id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public int insert(Student student) {
        String id = student.getStuid();
        String name = student.getStuname();
        int sex = student.getStusex();
        String nation = student.getStunation();
        String card = student.getStucard();
        int i = studentMapper.insert(student);
        int j = studentbakMapper.insert(new Studentbak(card, name, sex, nation, card, "有效"));
        int k = userMapper.insert(new User(id, id, name));
        int l = userroleMapper.insert(new Userrole(id, "3"));
        int m = turnMapper.insert(new Turn(UserUtil.getUU(), "入学", card));
        if (i + j + k + l + m == 5)
            return 1;
        else return 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public int updateStudent(Student student) {
        String id = student.getStuid();
        String name = student.getStuname();
        int sex = student.getStusex();
        String nation = student.getStunation();
        String newcard = student.getStucard();
        Student old = studentMapper.selectByPrimaryKey(id);
        String card = old.getStucard();
        String oldname = old.getStuname();
        if (!student.equals(old)) {
            int i = studentMapper.updateByPrimaryKeySelective(student);
            // 修改card的处理
            if (!card.equals(newcard)) {
                int k = studentbakMapper.updateCard(card, newcard);
                if (k == 0) return 0;
                int l = studentMapper.updateTurncard(card, newcard);
                if (l == 0) return 0;
            }
            // 修改name的处理
            if (!name.equals(oldname)) {
                int t = studentMapper.updateUsername(id, name);
                if (t == 0) return 0;
            }
            int j = studentbakMapper.updateByPrimaryKeySelective(new Studentbak(newcard, name, sex, nation, card, null));
            if (i + j == 2)
                return 1;
        }
        return 0;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public int delete(String id, String reason) {
        // 仅保留bak和turn
        Student student = studentMapper.selectByPrimaryKey(id);
        String card = student.getStucard();
        int i = studentMapper.deleteByPrimaryKey(id);
        int j = studentbakMapper.updateByPrimaryKey(new Studentbak(card, student.getStuname(), student.getStusex(), student.getStunation(), student.getStucard(), "退学"));
        int k = userroleMapper.deleteByPrimaryKey(id);
        int l = userMapper.deleteByPrimaryKey(id);
        int m = turnMapper.insert(new Turn(UserUtil.getUU(), reason, card));
        int n = studentMapper.deleteStugrade(id);
        int o = studentMapper.deleteRes(id);
        return 1;
    }

    @Override
    public List<Student> searchAll(String id, String name, String classid, int sex, String nation, String card, int year, int page) {
        return studentMapper.searchAll(id, name, classid, sex, nation, card, year, page);
    }

    @Override
    public Student searchByCard(String card) {
        return studentMapper.searchByCard(card);
    }

    @Override
    public int getID(int year) {
        return studentMapper.getID(year);
    }

    @Override
    public int anyBody(int year) {
        return studentMapper.anyBody(year);
    }

    @Override
    public List<Map<String, Object>> forTeacher(String tid, int page) {
        return studentMapper.forTeacher(tid, page);
    }

    @Override
    public List<Map<String, Object>> forAdmin(int pg) {
        return studentMapper.forAdmin(pg);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public int insertGrade(Stugrade stugrade) {
        return studentMapper.insertGrade(stugrade);
    }

    @Override
    public int existGrade(Stugrade stugrade) {
        return studentMapper.existGrade(stugrade);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public List<String> hasLesson(String stuid) {
        return studentMapper.hasLesson(stuid);
    }

    @Override
    public List<String> tLesson(String tid) {
        return studentMapper.tLesson(tid);
    }

    @Override
    public List<Stugrade> selectGrade(String stuid) {
        return studentMapper.selectGrade(stuid);
    }

    @Override
    public List<String> tClass(String tid) {
        return studentMapper.tClass(tid);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public int updateGrade(Stugrade stugrade) {
        return studentMapper.updateGrade(stugrade);
    }

    @Override
    public List<Result> selectResult(String id, String name, String classid, int sex, String nation, String card, int year, int page) {
        return studentMapper.selectResult(id, name, classid, sex, nation, card, year, page);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public int insertResult(Result result) {
        studentMapper.insertResult(result);
        String id = result.getStuid();
        String act = result.getResultact();
        if (act.equals("开除"))
            delete(id, act);
        else if (act.equals("作弊")) {
            String testid = studentMapper.getRecentTest(id);
            studentMapper.cheat(id, testid);
        }
        return 1;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public int updateResult(Result result) {
        int i = studentMapper.updateResult(result);
        String id = result.getStuid();
        String act = result.getResultact();
        if (act.equals("开除"))
            delete(id, act);
        else if (act.equals("作弊")) {
            String testid = studentMapper.getRecentTest(id);
            studentMapper.cheat(id, testid);
        }
        return 1;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public int deleteResult(String resultid) {
        return studentMapper.deleteResult(resultid);
    }

    @Override
    public int existResult(String resultid) {
        return studentMapper.existResult(resultid);
    }

    @Override
    public List<Turn> turnInfo(String id, String name, String classid, int sex, String nation, String card, int year, int page) {
        return studentMapper.turnInfo(id, name, classid, sex, nation, card, year, page);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public int insertTurn(Turn turn) {
        String name = turn.getTurnname();
        String card = turn.getStuid();
        Student student = studentMapper.searchByCard(card);
        String id = student.getStuid();

        // id --> card
        turn.setStuid(card);
        int t;
        switch (name) {
            case "转学":
            case "退学":
            case "开除":
            case "升学": {
                int i = delete(id, name);
                if (i != 0) return 1;
                break;
            }
            case "休学": {
                int i = studentMapper.updateBak(card, name);
                t = studentMapper.insertTurn(turn);
                return 1;
            }
            case "复学": {
                int i = studentMapper.updateBak(card, "有效");
                System.out.println("i=" + i);
                t = studentMapper.insertTurn(turn);
                return 1;
            }
            case "留级": {
                int i = studentMapper.updateByPrimaryKeySelective(student);
                t = studentMapper.insertTurn(turn);
                return 1;
            }
        }
        return 0;
    }


    @Override
    public int existTest(String testid) {
        return studentMapper.existTest(testid);
    }

    @Override
    public HashMap<String, Integer> countMap() {
        HashMap<String, Integer> map = new HashMap<>();
        List<String> listname = studentMapper.countName();
        List<Integer> listcount = studentMapper.countNameNum();
        int size = listname.size();
        int size2 = listcount.size();
        if (size==size2) {
            for (int i = 0; i < size; i++) {
                String name = listname.get(i);
                int count = listcount.get(i);
                map.put(name, count);
            }
        }
        return map;
    }
}
