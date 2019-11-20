package com.nt.demo.middle.intf;


import com.nt.demo.middle.annotations.ResponseMessage;
import com.nt.demo.middle.annotations.Verifys;
import com.nt.demo.middle.entity.Emp;
import com.nt.demo.middle.entity.User;

/**
 * Create by TaoTaoNing
 * 2019/7/2
 **/
public interface TestService {

    String hello();

    Emp selectEmp(int empno);

    int insertEmp(Emp emp);

    @Verifys
    ResponseMessage insertSelective(Emp emp);

    /**
     * 根据userid获取用户信息--主干修改
     * @param userId
     * @return
     */
    User selectUserById(Integer userId);
}
