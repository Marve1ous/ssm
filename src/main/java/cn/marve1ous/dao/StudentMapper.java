package cn.marve1ous.dao;

import cn.marve1ous.model.Result;
import cn.marve1ous.model.Student;
import cn.marve1ous.model.Stugrade;
import cn.marve1ous.model.Turn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface StudentMapper {
    int deleteByPrimaryKey(String stuid);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(String stuid);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    List<Student> searchAll(@Param("id") String stuid, @Param("name") String name,
                            @Param("classid") String classid, @Param("sex") int sex,
                            @Param("nation") String nation, @Param("card") String card,
                            @Param("year") int stuyear, @Param("page") int page);

    Student searchByCard(String card);

    int getID(int year);

    int anyBody(int year);

    List<Map<String, Object>> forTeacher(@Param("tid") String tid, @Param("page") int page);

    List<Map<String, Object>> forAdmin(@Param("page") int pg);

    int insertGrade(Stugrade stugrade);

    int updateGrade(Stugrade stugrade);

    String getRecentTest(String stuid);

    int cheat(@Param("stuid") String stuid, @Param("testid") String testid);

    int existGrade(Stugrade stugrade);

    List<Stugrade> selectGrade(String stuid);

    List<String> hasLesson(String stuid);

    List<String> tLesson(String tid);

    List<String> tClass(String tid);

    List<Result> selectResult(@Param("id") String stuid, @Param("name") String name,
                              @Param("classid") String classid, @Param("sex") int sex,
                              @Param("nation") String nation, @Param("card") String card,
                              @Param("year") int stuyear, @Param("page") int page);


    int insertResult(Result result);

    int updateResult(Result result);

    int deleteResult(String resultid);

    int existResult(String resultid);

    List<Turn> turnInfo(@Param("id") String stuid, @Param("name") String name,
                        @Param("classid") String classid, @Param("sex") int sex,
                        @Param("nation") String nation, @Param("card") String card,
                        @Param("year") int stuyear, @Param("page") int page);

    int insertTurn(Turn turn);

    int updateTurncard(@Param("card") String card, @Param("newcard") String newcard);

    int updateUsername(@Param("id") String id, @Param("name") String name);

    int deleteStugrade(String id);

    int deleteRes(String id);

    int existTest(String testid);

    int updateBak(@Param("id") String card, @Param("status") String name);

    List<String> countName();

    List<Integer> countNameNum();

}