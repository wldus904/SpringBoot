package com.infrun.myrestfulservice.dao;

import com.infrun.myrestfulservice.bean.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// 스테레오 타입(빈의 유형) : controller, repository, service, configuration, component
// 참고 : https://incheol-jung.gitbook.io/docs/q-and-a/spring/stereo-type
@Component // dao 역할을 혼합할꺼라 @Service 사용안하고 @component 사용
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    private static int usersCount = 3;

    // 정적 변수 초기화(class 가 로드될 때 한번만 초기화)
    static {
        users.add(new User(1, "Kenneth", new Date()));
        users.add(new User(2, "Alice", new Date()));
        users.add(new User(3, "Elena", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++usersCount);
        }

        if (user.getJoinDate() == null) {
            user.setJoinDate(new Date());
        }

        users.add(user);

        return user;
    }

    public User findOne(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }

        return null;
    }
}
