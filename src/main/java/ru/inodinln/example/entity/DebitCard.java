package ru.inodinln.example.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "debitcards")
public class DebitCard {

    @Id
    //@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "currbalance")
    private BigDecimal currBalance;

    @Column(name = "cardnumber")
    private int cardNumber;

    @Column(name = "expdate")
    private LocalDate expDate; //expiration Date

    @Column(name = "cvv")
    private int cvv;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User owner;

    public DebitCard(){}

    public DebitCard(int cardNumber, LocalDate expDate, int cvv, BigDecimal currBalance, User owner) {
        this.cardNumber = cardNumber;
        this.expDate = expDate;
        this.cvv = cvv;
        this.currBalance = currBalance;
        this.owner = owner;
    }

    public BigDecimal getCurrBalance () {
        return currBalance;
    }
    public void setCurrBalance (BigDecimal currBalance) {
        this.currBalance = currBalance;
    }
    public int getCardNumber () {
        return cardNumber;
    }
    public void setCardNumber (int cardNumber) {
        this.cardNumber = cardNumber;
    }
    public LocalDate getExpDate () {
        return expDate;
    }
    public void setExpDate (LocalDate expDate) {
        this.expDate = expDate;
    }
    public int getCvv () {
        return cvv;
    }
    public void setCvv (int cvv) {
        this.cvv = cvv;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "DebitCard{" +
                "id=" + id +
                ", currBalance=" + currBalance +
                ", cardNumber=" + cardNumber +
                ", expDate=" + expDate +
                ", cvv=" + cvv +
                '}';
    }
}
