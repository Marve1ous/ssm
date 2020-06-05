package cn.marve1ous.service;

import cn.marve1ous.model.Result;
import cn.marve1ous.model.Student;
import cn.marve1ous.model.Stugrade;
import cn.marve1ous.model.Turn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface StudentService {

    Student selectByID(String id);

    int insert(Student student);

    int updateStudent(Student student);

    int delete(String id, String reason);

    /**
     * 分页显示学生信息
     *
     * @param id 模糊查询内容
     * @param page    页面号：默认一页10名学生
     * @return List
     */
    List<Student> searchAll(String id, String name, String classid, int sex, String nation, String card, int year, int page);

    Student searchByCard(String card);

    int getID(int year);

    int anyBody(int year);

    List<Map<String, Object>> forTeacher(String tid, int page);

    List<Stugrade> selectGrade(String stuid);

    int insertGrade(Stugrade stugrade);

    int updateGrade(Stugrade stugrade);

    int existGrade(Stugrade stugrade);

    List<String> hasLesson(String stuid);

    List<String> tLesson(String tid);

    List<String> tClass(String tid);

    List<Result> selectResult(String id, String name, String classid, int sex, String nation, String card, int year, int page);

    int insertResult(Result result);

    int updateResult(Result result);

    int deleteResult(String resultid);

    int existResult(String resultid);

    List<Turn> turnInfo(String id, String name, String classid, int sex, String nation, String card, int year, int page);

    int insertTurn(Turn turn);

    List<Map<String, Object>> forAdmin(int pg);

    int existTest(String testid);

    HashMap<String, Integer> countMap();
}
