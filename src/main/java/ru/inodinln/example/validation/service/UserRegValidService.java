package ru.inodinln.example.validation.service;

import org.springframework.stereotype.Service;
import ru.inodinln.example.dto.UserRegDTO;
import ru.inodinln.example.validation.entity.UserRegValidObj;

@Service
public class UserRegValidService {

    private final UserRegValidObj userRegValidObj = new UserRegValidObj();


    public UserRegValidObj userRegValidation(UserRegDTO userRegDTO) {
        if (firstNameValidation(userRegDTO.getFirstName()) & lastNameValidation(userRegDTO.getLastName()) &
                eMailValidation(userRegDTO.geteMail()) & pswrdValidation(userRegDTO.getPswrd()))
        return null;
        return userRegValidObj;
    }

    public boolean firstNameValidation(String firstName) {
        if (firstName == null) {
            userRegValidObj.setFirstNameErrMsg("Field can not be empty");
            return false;
        } else if (firstName.length() < 2 || firstName.length() > 15) {
            userRegValidObj.setFirstNameErrMsg("First name length maybe from 2 to 15 symbols");
            return false;
        }
        userRegValidObj.setFirstNameErrMsg(null);
        return true;
    }

    public boolean lastNameValidation(String lastName) {
        if (lastName == null) {
            userRegValidObj.setLastNameErrMsg("Field can not be empty");
            return false;
        } else if (lastName.length() < 2 || lastName.length() > 20) {
            userRegValidObj.setLastNameErrMsg("Last name length maybe from 2 to 20 symbols");
            return false;
        }
        userRegValidObj.setLastNameErrMsg(null);
        return true;
    }

    public boolean eMailValidation(String eMail) {
        if (eMail == null) {
            userRegValidObj.seteMailErrMsg("Field can not be empty");
            return false;
        } else if (eMail.length() < 5 || eMail.length() > 40) {
            userRegValidObj.seteMailErrMsg("E-mail length may be from 5else to 40 symbols");
            return false;
        }
        else if (false) { //не стал писать регулярное выражение
            userRegValidObj.setPswrdErrMsg("E-mail may contains: 0-9, A-Z, a-z");
            return false;
        }
        userRegValidObj.seteMailErrMsg(null);
        return true;
    }

    public boolean pswrdValidation(String pswrd) {
        if (pswrd == null) {
            userRegValidObj.setPswrdErrMsg("Field can not be empty");
            return false;
        } else if (pswrd.length() > 25) {
            userRegValidObj.setPswrdErrMsg("Password length may be дуыы 25 symbols");
            return false;
        }
        else if (false) { //не стал писать регулярное выражение
            userRegValidObj.setPswrdErrMsg("Password may contains: 0-9, A-Z, a-z,");
            return false;
        }
        userRegValidObj.setPswrdErrMsg(null);
        return true;
    }

}
