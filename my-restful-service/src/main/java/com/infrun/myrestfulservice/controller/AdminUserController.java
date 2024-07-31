package com.infrun.myrestfulservice.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.infrun.myrestfulservice.bean.AdminUser;
import com.infrun.myrestfulservice.bean.AdminUserV2;
import com.infrun.myrestfulservice.bean.User;
import com.infrun.myrestfulservice.dao.UserDaoService;
import com.infrun.myrestfulservice.exception.UserNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/admin")
public class AdminUserController {
    private UserDaoService service;

    public AdminUserController(UserDaoService service) {
        this.service = service;
    }

    // filter된 값을 return 하기 위해 MappingJacksonValue로 반환
    // @GetMapping(path = "/v1/users/{id}") // = /admin/v1/users/{id}
    // @GetMapping(value="/users/{id}", params="version=1")
    // @GetMapping(value="/users/{id}", headers="X-API-VERSION=1")
    @GetMapping(value="/users/{id}", produces="application/vnd.company.appv1+json")
    public MappingJacksonValue retrieveUser4AdminV1(@PathVariable int id) {

        User user = service.findOne(id);

        AdminUser adminUser = new AdminUser();
        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        } else {
            // user에서 adminUser로 property 복사
            BeanUtils.copyProperties(user, adminUser);
        }

        // "id", "name", "joinDate", "ssn" 형태로 필터 만들기
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "ssn");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(adminUser);
        mapping.setFilters(filters);

        return mapping;
    }

    // @GetMapping(path = "/v2/users/{id}")
    // @GetMapping(value="/users/{id}", params="version=2")
    //@GetMapping(value="/users/{id}", headers="X-API-VERSION=2")
    @GetMapping(value="/users/{id}", produces="application/vnd.company.appv2+json")
    public MappingJacksonValue retrieveUser4AdminV2(@PathVariable int id) {

        User user = service.findOne(id);

        AdminUserV2 adminUser = new AdminUserV2();
        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        } else {
            BeanUtils.copyProperties(user, adminUser);
            adminUser.setGrade("VIP");
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "grade");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(adminUser);
        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping(path = "/users")
    public MappingJacksonValue retrieverAllUsers() {
        List<User> users = service.findAll();
        List<AdminUser> adminUsers = new ArrayList<>();

        AdminUser adminUser = null;

        for(User user: users) {
            adminUser = new AdminUser();
            BeanUtils.copyProperties(user, adminUser);
            adminUsers.add(adminUser);
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "ssn");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(adminUsers);
        mapping.setFilters(filters);

        return mapping;
    }
}
