package cn.edu.bupt.sice.vo;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.List;

@Data
public class UserVO{
    private String userName;
    private String password;
    private String phone;
    private String role;
}