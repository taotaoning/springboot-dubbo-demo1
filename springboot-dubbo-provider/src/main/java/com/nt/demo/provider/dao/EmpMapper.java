package com.nt.demo.provider.dao;

import com.nt.demo.middle.entity.Emp;

/**
 * Create by TaoTaoNing
 * 2019/7/25
 **/
public interface EmpMapper {

    Emp selectEmp();

    int insertEmp(Emp emp);

    int insertSelective(Emp emp);
}
