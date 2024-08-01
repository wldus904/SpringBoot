package com.infrun.myrestfulservice.dto;

import com.infrun.myrestfulservice.bean.User;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private int count;
    private List<User> users;
}
