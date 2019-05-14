package cn.marve1ous.dao;

import cn.marve1ous.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public interface UserDao {
    User selectById(int id);
    ArrayList<User> all();
    List<String> getUserRole(int id);
    List<String> getPermissionByRole(String role);
    String getUserPwd(int id);
}
