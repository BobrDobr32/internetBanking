package ru.inodinln.example.validation.entity;

import org.springframework.context.annotation.Bean;


public class LoanValidObj {

    private String loanTypeErrMsg;

    private String loanAmountErrMsg;

    private String loanTermErrMsg;

    public String getLoanTypeErrMsg() {
        return loanTypeErrMsg;
    }

    public void setLoanTypeErrMsg(String loanTypeErrMsg) {
        this.loanTypeErrMsg = loanTypeErrMsg;
    }

    public String getLoanAmountErrMsg() {
        return loanAmountErrMsg;
    }

    public void setLoanAmountErrMsg(String loanAmountErrMsg) {
        this.loanAmountErrMsg = loanAmountErrMsg;
    }

    public String getLoanTermErrMsg() {
        return loanTermErrMsg;
    }

    public void setLoanTermErrMsg(String loanTermErrMsg) {
        this.loanTermErrMsg = loanTermErrMsg;
    }
}
