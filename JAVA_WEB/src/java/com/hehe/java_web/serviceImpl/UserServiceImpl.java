package com.hehe.java_web.serviceImpl;

import com.hehe.java_web.dao.IProfileDao;
import com.hehe.java_web.dao.IUserDao;
import com.hehe.java_web.pojo.Profile;
import com.hehe.java_web.pojo.User;
import com.hehe.java_web.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao iUserDao;

    @Autowired
    private IProfileDao iProfileDao;

    @Override
    public List<User> getPartUser() {
        return iUserDao.selectUserAll();
    }

    @Override
    public User getUser(String username) {
       return iUserDao.selectUserByUsername(username);
    }

    @Override
    public boolean addUser(User user) {
        User temp = getUser(user.getUName());
        if (temp == null) {
            return iUserDao.insertUser(user) == 1;
        }
        return false;
    }

    @Override
    public boolean login(String username, String password) {
        User temp = getUser(username);
        if (temp != null) {
            if (temp.getUPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Profile getProfileByUsername(String username) {
        User temp = getUser(username);
        if (temp != null) {
            return iProfileDao.selectProfileByUserName(username);
        }
        return null;
    }

    @Override
    public Integer getUserCount() {
        return iUserDao.selectUserCount();
    }

    @Override
    public boolean updateProfile(String username, Profile profile) {
        User temp = getUser(username);
        if (temp != null) {
            return iProfileDao.updateProfile(profile) == 1;
        }
        return false;
    }

    @Override
    public boolean delUser(User user) {
        return iUserDao.deleteUser(user) == 1;
    }

    @Override
    public boolean addProfile(Profile profile) {
        return iProfileDao.insertProfile(profile) == 1;
    }

    @Override
    public Integer getUserScore(String PUserName) {
        return iProfileDao.getUserScore(PUserName);
    }

    @Override
    public User getUserByUID(Integer UID) {
        return iUserDao.selectUserByUID(UID);
    }
}
