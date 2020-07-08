package com.example.tests;

import com.example.GenderEnum;
import com.example.entities.Gender;
import com.example.entities.UserInfo;
import com.example.model.GenderModel;
import com.example.model.UserInfoModel;
import com.example.repository.GenderRepository;
import com.example.repository.UserInfoRepository;
import com.example.service.impl.GenderServiceImpl;
import com.example.service.impl.UserInfoServiceImpl;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseTestClass {
    protected static GenderRepository genderRepository;
    protected static UserInfoRepository userInfoRepository;

    protected GenderServiceImpl genderService;
    protected static UserInfoServiceImpl userInfoService;

    protected static List<String> rightNameList;
    protected static List<String> wrongNameList;

    protected static List<Gender> genderList;
    protected static List<GenderModel> genderModelList;

    protected static List<UserInfo> userInfoList;
    protected static List<UserInfoModel> userInfoModelList;

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
    }
}
