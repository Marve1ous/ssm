package cn.marve1ous.service.Impl;

import cn.marve1ous.dao.UserDao;
import cn.marve1ous.model.User;
import cn.marve1ous.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User selectById(int id) {
        return userDao.selectById(id);
    }

    @Override
    public ArrayList<User> all() {
        return userDao.all();
    }

    @Override
    public List<String> getUserRole(int id) {
        return userDao.getUserRole(id);
    }

    @Override
    public List<String> getPermissionByRole(String role) {
        return userDao.getPermissionByRole(role);
    }

    @Override
    public String getUserPwd(int id) {
        return userDao.getUserPwd(id);
    }
}
