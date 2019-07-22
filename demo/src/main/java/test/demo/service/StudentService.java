package test.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import test.demo.dao.StudentDAO;
import test.demo.model.Student;


@Service
@Transactional
public class StudentService implements BaseService<Student>{

    @Autowired
    private StudentDAO dao;

    @Override
    public List<Student> getAll() {
        return dao.getAll();
    }

    @Override
    public Student findOneById(long id) {
        return dao.getOne(id);
    }

    @Override
    public Long createOne(Student entity) {
        return dao.saveOne(entity);
    }

    @Override
    public void updateOne(Student entity) {
        dao.updateOne(entity);
    }

    @Override
    public void deleteById(long id) {
        dao.deleteOne(id);
    }

}