package cn.edu.bupt.sice.service.impl;

import cn.edu.bupt.sice.service.IUserService;
import cn.edu.bupt.sice.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired  //数据库服务类
    private IUserService suserService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //SUser对应数据库中的用户表，是最终存储用户和密码的表，可自定义
        //本例使用SUser中的email作为用户名:
        UserVO user = suserService.findUserByUserName(userName);

        if (user == null) {
            throw new UsernameNotFoundException("UserName " + userName + " not found");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        System.err.println("username is " + user.getUserName() + ", " + user.getRole());
        return new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPassword(), authorities);
    }

}