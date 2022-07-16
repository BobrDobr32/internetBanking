package ru.inodinln.example.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.inodinln.example.dao.DebitCardDao;
import ru.inodinln.example.dto.DebitCardDTO;
import ru.inodinln.example.dto.DebitCardViewDTO;
import ru.inodinln.example.entity.DebitCard;
import ru.inodinln.example.entity.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class DbtCrdService {

    private final DebitCardDao debitCardDao;

    public DbtCrdService(DebitCardDao debitCardDao) {
        this.debitCardDao = debitCardDao;
    }

    public void addCard(DebitCardDTO dbtCardDTO, User currentUser) {
        Random r = new Random();
        DebitCard newCard = new DebitCard();
        BeanUtils.copyProperties(dbtCardDTO, newCard);
        newCard.setOwner(currentUser);
        newCard.setCardNumber(r.nextInt(20000));
        newCard.setExpDate(LocalDate.now().plusYears(4));
        newCard.setCvv(r.nextInt(1000));
        debitCardDao.addNewCard(newCard);
    }

    public void  updateCard (int cardNumber, DebitCardDTO dbtCardDTO) {
        debitCardDao.updateCard(cardNumber, dbtCardDTO);
    }

    public List<DebitCard> getCards(int userId) {
        return debitCardDao.getCards(userId);
    }

    public List<DebitCardViewDTO> getCardsToView(int userId) {
        List<DebitCard> listCards = getCards(userId);
        List<DebitCardViewDTO> listCardsDTO = new ArrayList<>(listCards.size());
        for (DebitCard debitCard : listCards) {
            listCardsDTO.add(listCards.indexOf(debitCard), new DebitCardViewDTO());
            BeanUtils.copyProperties(debitCard, listCardsDTO.get(listCards.indexOf(debitCard)));
        }
        return listCardsDTO;
    }

    public DebitCard getCard(int cardNumber) {
        return debitCardDao.getCard(cardNumber);
    }

    public DebitCardViewDTO getCardToView(int cardNumber) {
        DebitCardViewDTO debitCardViewDTO = new DebitCardViewDTO();
        BeanUtils.copyProperties(getCard(cardNumber), debitCardViewDTO);
        return debitCardViewDTO;
    }

    public void deleteCard(int cardNumber) {
        debitCardDao.deleteCard(cardNumber);
    }

    public boolean dbtCrdIsNull (int cardNumber) {
        return debitCardDao.getCard(cardNumber) == null;
    }
}
