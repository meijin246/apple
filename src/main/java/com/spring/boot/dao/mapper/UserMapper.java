package com.spring.boot.dao.mapper;

import com.spring.boot.models.User;
import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface UserMapper {
    void insertData(User user);
}
