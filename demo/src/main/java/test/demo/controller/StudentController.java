package test.demo.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.demo.model.BaseResponse;
import test.demo.model.Student;
import test.demo.service.StudentService;

@RestController
@RequestMapping("/rest")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<BaseResponse> all() {
        BaseResponse baseResponse = new BaseResponse();
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            List<Student> list = studentService.getAll();
            baseResponse.setData(list);
        } 
        catch (Exception ex) {
            baseResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            ex.printStackTrace();
        }

        return new ResponseEntity<BaseResponse>(baseResponse, httpStatus);
    }

    @PostMapping("/students")
    public ResponseEntity<BaseResponse> newStudent(@RequestBody Student student) {
        BaseResponse response = new BaseResponse();
        HttpStatus status = HttpStatus.OK;
        try {
            Long id = studentService.createOne(student);
            if (id > 0) {
                response.setData(id);
                status = HttpStatus.CREATED;
                response.setStatus(status);
            } else {
                status = HttpStatus.BAD_REQUEST;
                response.setStatus(status);
            }
        } catch (Exception ex) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            ex.printStackTrace();   
        }
        return new ResponseEntity<BaseResponse>(response, status);
    }

    @GetMapping(value = "/students/{id}")
    public ResponseEntity<BaseResponse> one(@PathVariable Long id) {
        BaseResponse response = new BaseResponse();
        HttpStatus status = HttpStatus.OK;
        try {
            Student entity = studentService.findOneById(id);
            if (entity != null) {
                response.setData(entity);
            } else {
                status = HttpStatus.NOT_FOUND;
                response.setStatus(status);
            }
        } 
        catch(NoSuchElementException ex) {
            status = HttpStatus.NOT_FOUND;
            response.setStatus(status);
        }
        catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setStatus(status);
            e.printStackTrace();
        }

        return new ResponseEntity<BaseResponse>(response, status);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<BaseResponse> replaceStudent(@RequestBody Student newStudent, @PathVariable Long id) {
        BaseResponse response = new BaseResponse();
        HttpStatus status = HttpStatus.OK;
        try {
            Student entity = studentService.findOneById(id);
            newStudent.setId(entity.getId());
            studentService.updateOne(newStudent);
            response.setStatus(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setStatus(status);
        }

        return new ResponseEntity<BaseResponse>(response, status);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<BaseResponse> deleteStudent(@PathVariable Long id) {
        BaseResponse response = new BaseResponse();
        HttpStatus status = HttpStatus.OK;
        try {
            studentService.deleteById(id);
            status = HttpStatus.NO_CONTENT;
            response.setStatus(status);
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setStatus(status);
        }

        return new ResponseEntity<BaseResponse>(response, status);
    }

}