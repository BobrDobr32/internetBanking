package ru.inodinln.example.dto;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDate;

public class DebitCardViewDTO {

    private BigDecimal currBalance;

    private int cardNumber;

    private LocalDate expDate;

    private int cvv;

    public BigDecimal getCurrBalance() {
        return currBalance;
    }

    public void setCurrBalance(BigDecimal currBalance) {
        this.currBalance = currBalance;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public void setExpDate(LocalDate expDate) {
        this.expDate = expDate;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

}
