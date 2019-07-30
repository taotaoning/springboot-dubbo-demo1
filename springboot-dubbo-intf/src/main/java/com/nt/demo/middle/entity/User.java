package com.nt.demo.middle.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Create by TaoTaoNing
 * 2019/7/25
 **/
public class User implements Serializable {

    private static final long serialVersionUID = -7404046015917532175L;

    private Integer userId;

    private String userName;

    private String userPwd;

    private Date loginTime;

    private Short state;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", loginTime=" + loginTime +
                ", state=" + state +
                '}';
    }
}
