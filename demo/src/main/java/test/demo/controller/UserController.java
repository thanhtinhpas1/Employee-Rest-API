package test.demo.controller;

import java.security.MessageDigest;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.demo.model.BaseResponse;
import test.demo.model.User;
import test.demo.model.UserDetail;
import test.demo.service.JwtService;
import test.demo.service.UserService;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/rest")
public class UserController {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService UserService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> login(HttpServletRequest httpServletRequest, @RequestBody User user) {
        BaseResponse response = new BaseResponse();
        HttpStatus status = HttpStatus.OK;
        String result = "";
        try {
            UserDetail entity = (UserDetail) UserService.loadUserByUsername(user.getUsername());
            if (entity != null && entity.getPassword().trim().length() > 0) {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(user.getPassword().getBytes());
                byte[] dg = md.digest();
                String pw = DatatypeConverter.printHexBinary(dg);

                if (entity.getPassword().compareToIgnoreCase(pw) == 0) {
                    result = jwtService.generateToken(user.getUsername());
                    response.setData(result);
                } else {
                    result = "Wrong user or password";
                    status = HttpStatus.BAD_REQUEST;
                    response.setStatus(status);
                    response.setMessage(result);
                }
            } else {
                result = "Wrong user or password";
                status = HttpStatus.BAD_REQUEST;
                response.setStatus(status);
                response.setMessage(result);
            }
        } catch (Exception e) {
            result = "Server error";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            e.printStackTrace();
        }
        return new ResponseEntity<BaseResponse>(response, status);
    }

}