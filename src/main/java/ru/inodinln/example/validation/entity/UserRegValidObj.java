package ru.inodinln.example.validation.entity;

public class UserRegValidObj {

    private String firstNameErrMsg;

    private String lastNameErrMsg;

    private String eMailErrMsg;

    private String pswrdErrMsg;

    public String getFirstNameErrMsg() {
        return firstNameErrMsg;
    }

    public void setFirstNameErrMsg(String firstNameErrMsg) {
        this.firstNameErrMsg = firstNameErrMsg;
    }

    public String getLastNameErrMsg() {
        return lastNameErrMsg;
    }

    public void setLastNameErrMsg(String lastNameErrMsg) {
        this.lastNameErrMsg = lastNameErrMsg;
    }

    public String geteMailErrMsg() {
        return eMailErrMsg;
    }

    public void seteMailErrMsg(String eMailErrMsg) {
        this.eMailErrMsg = eMailErrMsg;
    }

    public String getPswrdErrMsg() {
        return pswrdErrMsg;
    }

    public void setPswrdErrMsg(String pswrdErrMsg) {
        this.pswrdErrMsg = pswrdErrMsg;
    }
}
