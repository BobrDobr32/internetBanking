package ru.inodinln.example.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.inodinln.example.dao.UserDao;
import ru.inodinln.example.dto.UserRegDTO;
import ru.inodinln.example.entity.User;

import java.time.LocalDate;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User logVerification(String eMail, String pswrd) {
        return userDao.logVerification(eMail, pswrd);
    }

    public boolean regVerification(String eMail) {
        return userDao.regVerification(eMail);
    }

    public void addUser (User user) {
        userDao.addUser(user);
    }

    public User doRegister(UserRegDTO userRegDTO) {
        User newUser = new User();
        BeanUtils.copyProperties(userRegDTO, newUser);
        newUser.setRegDate(LocalDate.now());
        userDao.addUser(newUser);
        return newUser;
    }
}
