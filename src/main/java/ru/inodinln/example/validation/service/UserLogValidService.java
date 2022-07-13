package ru.inodinln.example.validation.service;

import org.springframework.stereotype.Service;
import ru.inodinln.example.dto.UserLoginDTO;
import ru.inodinln.example.dto.UserRegDTO;
import ru.inodinln.example.validation.entity.UserLogValidObj;
import ru.inodinln.example.validation.entity.UserRegValidObj;

@Service
public class UserLogValidService {

    private final UserLogValidObj userLogValidObj = new UserLogValidObj();


    public UserLogValidObj userLogValidation(UserLoginDTO userLogDTO) {
        if (eMailValidation(userLogDTO.geteMail()) & pswrdValidation(userLogDTO.getPswrd()))
        return null;
        return userLogValidObj;
    }

    public boolean eMailValidation(String eMail) {
        if (eMail == null) {
            userLogValidObj.seteMailErrMsg("Field can not be empty");
            return false;
        } else if (eMail.length() < 5 || eMail.length() > 25) {
            userLogValidObj.seteMailErrMsg("E-mail length may be from 2 to 15 symbols");
            return false;
        }
        userLogValidObj.seteMailErrMsg(null);
        return true;
    }

    public boolean pswrdValidation(String pswrd) {
        if (pswrd == null) {
            userLogValidObj.setPswrdErrMsg("Field can not be empty");
            return false;
        } else if (pswrd.length() > 25) {
            userLogValidObj.setPswrdErrMsg("Password length may be less 25 symbols");
            return false;
        }
        userLogValidObj.setPswrdErrMsg(null);
        return true;
    }

}
