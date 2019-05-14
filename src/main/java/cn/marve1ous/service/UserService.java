package cn.marve1ous.service;

import cn.marve1ous.model.User;

import java.util.ArrayList;
import java.util.List;

public interface UserService {
    User selectById(int id);
    ArrayList<User> all();
    List<String> getUserRole(int id);
    List<String> getPermissionByRole(String role);
    String getUserPwd(int id);
}
