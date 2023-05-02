package com.example.recipenowwebappbackend.service;




import com.example.recipenowwebappbackend.dao.UserDao;
import com.example.recipenowwebappbackend.dto.UserDto;
import com.example.recipenowwebappbackend.entity.User;
import com.example.recipenowwebappbackend.service.base.BaseService;
import com.example.recipenowwebappbackend.transformer.UserTransformer;

import java.util.List;


public interface UserService extends BaseService<User, UserDto, UserDao, UserTransformer> {
    UserDto findUserByEmail(String email);

    UserDto getCurrentUser();

    Boolean isUserExistsByEmail(String email);
}
