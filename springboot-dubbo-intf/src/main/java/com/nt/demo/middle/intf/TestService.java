package com.nt.demo.middle.intf;


import com.nt.demo.middle.entity.Emp;

/**
 * Create by TaoTaoNing
 * 2019/7/2
 **/
public interface TestService {

    String hello();

    Emp selectEmp(int empno);

    int insertEmp(Emp emp);

    int insertSelective(Emp emp);

}
