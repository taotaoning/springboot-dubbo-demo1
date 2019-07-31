package com.nt.demo.middle.intf;


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

    int insertSelective(Emp emp);

    /**
     * 根据userid获取用户信息
     * @param userId
     * @return
     */
    User selectUserById(Integer userId);
}
