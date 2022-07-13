package ru.inodinln.example.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.inodinln.example.dto.LoanDTO;
import ru.inodinln.example.entity.Loan;
import java.util.List;

@Component
public class LoanDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public LoanDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Loan> getLoans(int userId) {
       Session session = sessionFactory.getCurrentSession();

        Query q = session.createQuery("select l FROM Loan l WHERE l.owner.id = :userId");
        q.setParameter("userId", userId);
       return q.getResultList();
    }


    @Transactional()
    public void addLoan (Loan newLoan) {
        Session session = sessionFactory.getCurrentSession();
        session.save(newLoan);
    }

    @Transactional(readOnly = true)
    public Loan getLoan(int loanNumber) {
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery("select l FROM Loan l WHERE l.loanNumber = :loanNumber");
        q.setParameter("loanNumber", loanNumber);
        return (Loan) q.getResultList().stream().findAny().orElse(null);
    }

    @Transactional()
    public void updateLoan (int loanNumber, LoanDTO loan) {
        BeanUtils.copyProperties(loan, getLoan(loanNumber));
    }

    @Transactional()
    public void deleteLoan (int loanNumber) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(getLoan(loanNumber));
    }
}
