package test.demo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import test.demo.model.Student;

@Repository
public class StudentDAO implements BaseDAO<Student> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    @Deprecated
    public List<Student> getAll() {
        return sessionFactory.getCurrentSession().createCriteria(Student.class).list();
    }

    @Override
    public Student getOne(long id) {
        return (Student) sessionFactory.getCurrentSession().get(Student.class, id);
    }

    @Override
    public Long saveOne(Student entity) {
        return (Long) (sessionFactory.getCurrentSession().save(entity));
    }

    @Override
    public void updateOne(Student entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public void deleteOne(long id) {
        Session session = sessionFactory.getCurrentSession();
        Student entity = session.get(Student.class, id);
        if (entity != null) {
            session.delete(entity);
            session.flush();
        }
    }
    
}