package com.nt.demo.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Create by TaoTaoNing
 * 2019/7/31
 **/
@Data
public class UserVO {

    private Integer userId;

    private String userName;

    private String userPwd;

    private Date loginTime;

    private Short state;
}
