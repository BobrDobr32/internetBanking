package ru.inodinln.example.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.inodinln.example.entity.DebitCard;
import ru.inodinln.example.entity.Loan;
import ru.inodinln.example.entity.User;

import java.time.LocalDate;
import java.util.ArrayList;

@Component
public class UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    //private final JdbcTemplate jdbcTemplate;

    //@Autowired
    //public UserDao (JdbcTemplate jdbcTemplate) {
        //this.jdbcTemplate = jdbcTemplate;
    //}

    @Transactional(readOnly = true)
    public User logVerification(String eMail, String pswrd){
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery("select user FROM User user WHERE user.eMail = :eMail " +
                "AND user.pswrd = :pswrd", User.class);
        q.setParameter("eMail", eMail);
        q.setParameter("pswrd", pswrd);
        return (User) q.getResultList().stream().findAny().orElse(null);
        //return !(jdbcTemplate.query("SELECT * FROM users WHERE eMail=? AND pswrd=?", new Object[]{eMail, pswrd},
                //new BeanPropertyRowMapper<>(User.class)).isEmpty());
    }
    @Transactional(readOnly = true)
    public boolean regVerification(String eMail){
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery("select user FROM User user WHERE user.eMail = :eMail", User.class);
        q.setParameter("eMail", eMail);
        return !(q.getResultList().isEmpty());
        //return !(jdbcTemplate.query("SELECT * FROM Users WHERE eMail=? ", new Object[]{eMail},
                //new BeanPropertyRowMapper<>(User.class)).isEmpty());
        //join fetch user.dbtCards join fetch user.loans
    }
    @Transactional()
    public void addUser (User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
        //System.out.println(user);
        //jdbcTemplate.update("INSERT INTO users VALUES(?, ?, ?, ?, ?)", user.getFirstName(), user.getLastName(),
                //user.getPswrd(), user.geteMail(), user.getRegDate());
    }
}


