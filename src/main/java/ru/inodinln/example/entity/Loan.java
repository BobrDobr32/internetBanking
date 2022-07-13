package ru.inodinln.example.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "loans")
public class Loan {

    @Id
    //@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "loantype")
    private String loanType;

    @Column(name = "regdate")
    private LocalDate regDate;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "rate")
    private double rate; //interest Rate

    @Column(name = "term")
    private Integer term; //ru.inodinln.example.entity.Loan Term

    @Column(name = "payment")
    private double payment; //Monthly Payment

    @Column(name = "loannumber")
    private int loanNumber;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User owner;

    public Loan(){}

    public Loan(String loanType, LocalDate regDate, BigDecimal amount, double rate, Integer term,
                double payment, int loanNumber, User owner) {
        this.loanType = loanType;
        this.regDate = regDate;
        this.amount = amount;
        this.rate = rate;
        this.term = term;
        this.loanNumber = loanNumber;
        this.payment = payment;
        this.owner = owner;
    }

    public String getLoanType() {return loanType;}
    public void setLoanType(String loanType) {this.loanType = loanType;}
    public LocalDate getRegDate () {
            return regDate;
        }
    public void setRegDate (LocalDate regDate) {
            this.regDate = regDate;
        }
    public BigDecimal getAmount () {
            return amount;
        }
    public void setAmount (BigDecimal amount) {
            this.amount = amount;
        }
    public double getRate () {
            return rate;
        }
    public void setRate (double rate) {
            this.rate = rate;
        }
    public Integer getTerm () {
            return term;
        }
    public void setTerm (Integer term) {
            this.term = term;
        }
    public double getPayment () {
            return payment;
        }
    public void setPayment (double payment) {
            this.payment = payment;
        }
    public int getLoanNumber() {
        return loanNumber;
    }
    public void setLoanNumber(int loanNumber) {
        this.loanNumber = loanNumber;
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
        return "Loan{" +
                "id=" + id +
                ", loanType='" + loanType + '\'' +
                ", regDate=" + regDate +
                ", amount=" + amount +
                ", rate=" + rate +
                ", term=" + term +
                ", payment=" + payment +
                ", loanNumber=" + loanNumber +
                '}';
    }
}

