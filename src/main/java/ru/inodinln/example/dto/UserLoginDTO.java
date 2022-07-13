package ru.inodinln.example.dto;

public class UserLoginDTO {

       private String pswrd;

        private String eMail;

    //public UserLoginDTO(){}

    //public UserLoginDTO(String eMail, String pswrd){
        //this.eMail = eMail;
        //this.pswrd = pswrd;
   // }

    public String getPswrd() {
        return pswrd;
    }

    public void setPswrd(String pswrd) {
        this.pswrd = pswrd;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

}
