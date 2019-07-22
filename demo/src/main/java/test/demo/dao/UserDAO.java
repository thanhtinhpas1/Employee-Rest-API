package test.demo.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import test.demo.model.User;

@Repository
public class UserDAO{

    @Autowired
    private SessionFactory sessionFactory;

    public User findUserByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criterion = session.createCriteria(User.class);
        return (User) criterion.add(Restrictions.eq("username", username)).uniqueResult();
    }
}