package ru.inodinln.example.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.inodinln.example.dto.DebitCardDTO;
import ru.inodinln.example.entity.DebitCard;
import java.util.List;

@Component
public class DebitCardDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public DebitCardDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<DebitCard> getCards(int userId) {
        Session session = sessionFactory.getCurrentSession();

        Query q = session.createQuery("select c FROM DebitCard c WHERE c.owner.id = :userId");
        q.setParameter("userId", userId);


        //System.out.println(session.get(User.class, userId).getDbtCards());

        return (List<DebitCard>) q.getResultList();
    }

    @Transactional
    public void addNewCard (DebitCard newCard) {
        Session session = sessionFactory.getCurrentSession();
        session.save(newCard);
    }

    @Transactional(readOnly = true)
    public DebitCard getCard(int cardNumber) {
        Session session = sessionFactory.getCurrentSession();
        //return (DebitCard) session.get(User.class, userId).getDbtCards().stream().filter((c) -> c.getCardNumber() == cardNumber);


        Query q = session.createQuery("select c FROM DebitCard c WHERE c.cardNumber = :cardNumber");
        q.setParameter("cardNumber", cardNumber);
       return (DebitCard) q.getResultList().stream().findAny().orElse(null);
   }

   @Transactional
    public void updateCard (int cardNumber, DebitCardDTO dbtCardDTO) {
       BeanUtils.copyProperties(dbtCardDTO, getCard(cardNumber));
        //jdbcTemplate.update("UPDATE debitcards SET currBalance=? WHERE cardNumber=?", card.getCurrBalance(), cardNumber);
    }

    @Transactional
    public void deleteCard (int cardNumber) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(getCard(cardNumber));
    }
}
