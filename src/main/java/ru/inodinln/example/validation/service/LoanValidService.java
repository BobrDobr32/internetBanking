package ru.inodinln.example.validation.service;

import org.springframework.stereotype.Service;
import ru.inodinln.example.dto.LoanDTO;
import ru.inodinln.example.entity.Loan;
import ru.inodinln.example.validation.entity.LoanValidObj;

import java.math.BigDecimal;


@Service
public class LoanValidService {

    private final LoanValidObj loanValidObj = new LoanValidObj();

    public LoanValidObj loanValidation(LoanDTO loanToBeValid) {
        if (loanTypeValidation(loanToBeValid) & loanAmountValidation(loanToBeValid) &
                loanTermValidation(loanToBeValid))
            return null;
        return loanValidObj;
    }

    public boolean loanTypeValidation(LoanDTO loanToBeValid) {
        if (loanToBeValid.getLoanType() == null) {
            loanValidObj.setLoanTypeErrMsg("Field can not be empty");
            return false;
        }
        else if (loanToBeValid.getLoanType().equals("Mortgage") || loanToBeValid.getLoanType().equals("Car loan")){
            loanValidObj.setLoanTypeErrMsg(null);
            return true;
        }
        loanValidObj.setLoanTypeErrMsg("Type may be \"Mortgage\" or \"Car loan\" only");
        return false;
    }

    public boolean loanAmountValidation(LoanDTO loanToBeValid) {
        if (loanToBeValid.getAmount() == null) {
            loanValidObj.setLoanAmountErrMsg("Field can not be empty");
            return false;
        } else if (loanToBeValid.getAmount().compareTo(BigDecimal.valueOf(5000)) < 0 ||
                loanToBeValid.getAmount().compareTo(BigDecimal.valueOf(500000)) > 0) {
            loanValidObj.setLoanAmountErrMsg("Amount may be from 5000 to 500000");
            return false;
        }
        loanValidObj.setLoanAmountErrMsg(null);
        return true;
    }

    public boolean loanTermValidation(LoanDTO loanToBeValid) {
        if (loanToBeValid.getTerm() == null) {
            loanValidObj.setLoanTermErrMsg("Field can not be empty");
            return false;
        } else if (!(loanToBeValid.getTerm() instanceof Integer)){
            loanValidObj.setLoanTermErrMsg("Integer type only");
            return false;
        } else if (loanToBeValid.getTerm() < 6 || loanToBeValid.getTerm() > 36) {
            loanValidObj.setLoanTermErrMsg("Term may be from 6 to 36");
            return false;
        }
        loanValidObj.setLoanTermErrMsg(null);
        return true;
    }

}
