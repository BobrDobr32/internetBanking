package ru.inodinln.example.validation.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.inodinln.example.dto.DebitCardDTO;
import ru.inodinln.example.entity.DebitCard;
import ru.inodinln.example.validation.entity.DbtCrdValidObj;

import java.math.BigDecimal;

@Service
public class DbtCrdValidService {

    private final  DbtCrdValidObj dbtCardValidObj = new DbtCrdValidObj();

    public DbtCrdValidObj dbtCrdValidation (DebitCardDTO dbtCardDTO) {
       if (dbtCardDTO.getCurrBalance() == null) {
           dbtCardValidObj.setCurrBalanceError("Field can not be empty");
           return dbtCardValidObj;
       }
        else if (dbtCardDTO.getCurrBalance().compareTo(BigDecimal.valueOf(0)) < 0
               || dbtCardDTO.getCurrBalance().compareTo(BigDecimal.valueOf(100000)) > 0){ //check amount bounds from 0 to 100000
                dbtCardValidObj.setCurrBalanceError("Amount may be from 0 to 100000");
                return dbtCardValidObj;
       }
        return null;
    }

}
