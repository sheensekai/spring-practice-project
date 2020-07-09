package com.example.tests.services;

import com.example.GenderEnum;
import com.example.UserStatusEnum;
import com.example.entities.*;
import com.example.model.*;
import com.example.repository.*;
import com.example.service.impl.*;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseTestClass {
    protected static GenderRepository genderRepository;
    protected static UserInfoRepository userInfoRepository;
    protected static UserRepository userRepository;
    protected static UserStatusNameRepository userStatusNameRepository;
    protected static UserStatusRepository userStatusRepository;

    protected GenderServiceImpl genderService;
    protected static UserInfoServiceImpl userInfoService;
    protected static UserServiceImpl userService;
    protected static UserStatusNameServiceImpl userStatusNameService;
    protected static UserStatusServiceImpl userStatusService;

    protected static List<String> rightNameList;
    protected static List<String> wrongNameList;

    protected static List<Gender> genderList;
    protected static List<GenderModel> genderModelList;

    protected static List<User> userList;
    protected static List<UserModel> userModelList;

    protected static List<UserInfo> userInfoList;
    protected static List<UserInfoModel> userInfoModelList;

    protected static List<UserStatusName> userStatusNameList;
    protected static List<UserStatusNameModel> userStatusNameModelList;

    protected static List<UserStatus> userStatusList;
    protected static List<UserStatusModel> userStatusModelList;

    @BeforeAll
    static void init() {
        rightNameList = new ArrayList<>();
        rightNameList.addAll(Arrays.asList("male", "MALE", "MalE", "female",
                "FEMALE", "FEmalE", "unknown", "UNKNOWN", "UnknoWN"));

        wrongNameList = new ArrayList<>();
        wrongNameList.addAll(Arrays.asList("notmale", "malemale", "fefefefemale", "notfemale", "asdfasdgasd",
                "femal", "mal", "email", "femalemale"));

        genderList = new ArrayList<>();
        for (int i = 0; i < rightNameList.size(); ++i) {
            genderList.add(new Gender(i, rightNameList.get(i)));
        }

        genderModelList = new ArrayList<>();
        for (int i = 0; i < rightNameList.size(); ++i) {
            genderModelList.add(new GenderModel(genderList.get(i)));
        }

        userInfoList = new ArrayList<>();
        userInfoList.add(new UserInfo(1, "Denis", "Fevralev", 1, 10L));
        userInfoList.add(new UserInfo(2, "Rustam", "Veysalov", 1, 20L));
        userInfoList.add(new UserInfo(3, "Nastya", "Korolenko", 2, 50L));

        userInfoModelList = new ArrayList<>();
        userInfoModelList.add(new UserInfoModel(userInfoList.get(0), GenderEnum.MALE.toString().toLowerCase()));
        userInfoModelList.add(new UserInfoModel(userInfoList.get(1), GenderEnum.MALE.toString().toLowerCase()));
        userInfoModelList.add(new UserInfoModel(userInfoList.get(2), GenderEnum.FEMALE.toString().toLowerCase()));

        userList = new ArrayList<>();
        userList.add(new User(1, "sheensekai", "sheensekai@ya.ru", 20L, 1, 10L));
        userList.add(new User(2, "rstvsl", "yanerustam@ya.ru", 40L, 1, 20L));
        userList.add(new User(3, "nastya123", "asdfasdf@ya.ru", 50L, 2, 50L));

        userModelList = new ArrayList<>();
        userModelList.add(new UserModel(userList.get(0)));
        userModelList.add(new UserModel(userList.get(1)));
        userModelList.add(new UserModel(userList.get(2)));

        userStatusNameList = new ArrayList<>();
        userStatusNameList.add(new UserStatusName(1, UserStatusEnum.ONLINE.toString().toLowerCase()));
        userStatusNameList.add(new UserStatusName(2, UserStatusEnum.OFFLINE.toString().toLowerCase()));
        userStatusNameList.add(new UserStatusName(3, UserStatusEnum.UNKNOWN.toString().toLowerCase()));

        userStatusNameModelList = new ArrayList<>();
        userStatusNameModelList.add(new UserStatusNameModel(userStatusNameList.get(0)));
        userStatusNameModelList.add(new UserStatusNameModel(userStatusNameList.get(1)));
        userStatusNameModelList.add(new UserStatusNameModel(userStatusNameList.get(2)));

        userStatusList = new ArrayList<>();
        userStatusList.add(new UserStatus(1L, 10L, 1, 1));
        userStatusList.add(new UserStatus(2L, 40L, 2, 1));
        userStatusList.add(new UserStatus(3L, 100L, 3, 2));

        userStatusModelList = new ArrayList<>();
        userStatusModelList.add(new UserStatusModel(userStatusList.get(0), UserStatusEnum.ONLINE));
        userStatusModelList.add(new UserStatusModel(userStatusList.get(1), UserStatusEnum.ONLINE));
        userStatusModelList.add(new UserStatusModel(userStatusList.get(2), UserStatusEnum.OFFLINE));
    }
}
