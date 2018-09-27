package com.spring.boot.dao.impl;

import com.spring.boot.dao.UserDao;
import com.spring.boot.dao.mapper.UserMapper;
import com.spring.boot.dao.mapper3.RoleMapper;
import com.spring.boot.models.User;
import com.spring.boot.models3.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("userDao")
public class UserDaoImpl implements UserDao{
/*
   @Autowired
   @Qualifier("jdbcTemplate")
   private JdbcTemplate template;
*/
   @Autowired
   private UserMapper userMapper;

   @Autowired
   private RoleMapper roleMapper;

    public void insertData(String userName, String address) {

        User user = new User();
        user.setUsername(userName);
        user.setAddress(address);
        userMapper.insertData(user);

        Role role = new Role();
        role.setUsername(userName);
        role.setAddress(address);
        roleMapper.insertData(role);


    }


    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public RoleMapper getRoleMapper() {
        return roleMapper;
    }

    public void setRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }
}
