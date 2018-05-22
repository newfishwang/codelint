package cn.edu.bupt.sice.service;

import cn.edu.bupt.sice.vo.UserVO;
import org.apache.catalina.User;

public interface IUserService {
    UserVO findUserByUserName(String phone);
}