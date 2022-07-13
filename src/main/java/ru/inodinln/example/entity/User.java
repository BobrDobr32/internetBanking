package ru.inodinln.example.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "pswrd")
    private String pswrd;

    @Column(name = "regDate")
    private LocalDate regDate;

    @Column(name = "email")
    private String eMail;

    @OneToMany(mappedBy = "owner")
    private List<DebitCard> dbtCards;

   @OneToMany(mappedBy = "owner")
   private List<Loan> loans;

    public User(){}

    public User (String firstName, String lastName, String eMail, String pswrd) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.pswrd = pswrd;
        this.regDate = LocalDate.now();

    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPswrd() {
        return pswrd;
    }

    public void setPswrd(String pswrd) {
        this.pswrd = pswrd;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<DebitCard> getDbtCards() {
        return dbtCards;
    }

    public void setDbtCards(List<DebitCard> dbtCards) {
        this.dbtCards = dbtCards;
    }

  public List<Loan> getLoans() {
       return loans;
    }

    public void setLoans(List<Loan> loans) {
       this.loans = loans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if ((o == null) || (getClass() != o.getClass())) return false;
        User us = (User) o;
        return eMail.equals(us.eMail) && firstName.equals(us.firstName) && lastName.equals(us.lastName) && pswrd.equals(us.pswrd) &&
                regDate.equals(us.regDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, eMail, pswrd, regDate);
    }

    @Override
    public String toString() {
        return ("First Name: " + firstName + "\nLast name: " + lastName + "\nE-mail: "
                + eMail + "\nPassword: " + pswrd);}



}
