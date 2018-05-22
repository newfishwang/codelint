package cn.edu.bupt.sice.service.impl;

import cn.edu.bupt.sice.service.IUserService;
import cn.edu.bupt.sice.vo.UserVO;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Override
    public  UserVO findUserByUserName(String phone) {
        UserVO userVO = new UserVO();
        userVO.setUserName("admin");
        userVO.setPassword("123abc");
        userVO.setPhone("13600000000");
        userVO.setRole("admin");
        return userVO;
    }
}