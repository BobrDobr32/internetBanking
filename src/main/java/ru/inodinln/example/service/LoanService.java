package ru.inodinln.example.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.inodinln.example.dao.LoanDao;
import ru.inodinln.example.dto.DebitCardViewDTO;
import ru.inodinln.example.dto.LoanDTO;
import ru.inodinln.example.dto.LoanViewDTO;
import ru.inodinln.example.entity.DebitCard;
import ru.inodinln.example.entity.Loan;
import ru.inodinln.example.entity.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class LoanService {

    private final LoanDao loanDao;

    public LoanService(LoanDao loanDao) {
        this.loanDao = loanDao;
    }

    public void addLoan(LoanDTO loanDTO, User currentUser) {
        Random r = new Random();
        Loan newLoan = new Loan();
        BeanUtils.copyProperties(loanDTO, newLoan);
        newLoan.setLoanNumber(r.nextInt(20000));
        newLoan.setRegDate(LocalDate.now());
        newLoan.setOwner(currentUser);

        if (newLoan.getAmount().compareTo(BigDecimal.valueOf(50000)) < 1) {
            newLoan.setRate(10);
            newLoan.setPayment(newLoan.getAmount().doubleValue()*(0.0083* newLoan.getTerm()+1));
        }
        else if (newLoan.getAmount().compareTo(BigDecimal.valueOf(250000)) < 1) {
            newLoan.setRate(7);
            newLoan.setPayment(newLoan.getAmount().doubleValue()*(0.0058* newLoan.getTerm()+1));}
        else {
            newLoan.setRate(5);
            newLoan.setPayment(newLoan.getAmount().doubleValue()*(0.0042* newLoan.getTerm()+1));
        }
        loanDao.addLoan(newLoan);
    }

    public List<Loan> getLoans(int userId) {
        return loanDao.getLoans(userId);
    }

    public List<LoanViewDTO> getLoansToView(int userId) {
        List<Loan> listLoans = getLoans(userId);
        List<LoanViewDTO> listLoansDTO = new ArrayList<>(listLoans.size());
        for (Loan loan : listLoans) {
            listLoansDTO.add(listLoans.indexOf(loan), new LoanViewDTO());
            BeanUtils.copyProperties(loan, listLoansDTO.get(listLoans.indexOf(loan)));
        }
        return listLoansDTO;
    }

    public Loan getLoan(int loanNumber) {
        return loanDao.getLoan(loanNumber);
    }

    public LoanViewDTO getLoanToView(int loanNumber) {
            LoanViewDTO loanViewDTO = new LoanViewDTO();
            BeanUtils.copyProperties(getLoan(loanNumber), loanViewDTO);
            return loanViewDTO;
    }

    public void  updateLoan (int loanNumber, LoanDTO loan) {
        loanDao.updateLoan(loanNumber, loan);
    }

    public boolean loanIsNull (int loanNumber) {
        return loanDao.getLoan(loanNumber) == null;
    }

    public void deleteLoan(int loanNumber) {
        loanDao.deleteLoan(loanNumber);
    }
}
