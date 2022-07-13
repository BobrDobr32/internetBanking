package ru.inodinln.example.dto;


import java.math.BigDecimal;

public class DebitCardDTO {

    private BigDecimal currBalance;

    public BigDecimal getCurrBalance() {
        return currBalance;
    }

    public void setCurrBalance(BigDecimal currBalance) {
        this.currBalance = currBalance;
    }

    public DebitCardDTO(){}

}

