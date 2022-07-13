package ru.inodinln.example.validation.entity;

public class UserLogValidObj {

    private String eMailErrMsg;

    private String pswrdErrMsg;

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
