package com.nt.demo.provider.service;

import com.nt.demo.middle.entity.Emp;
import com.nt.demo.middle.intf.TestService;
import com.nt.demo.provider.dao.EmpMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Create by TaoTaoNing
 * 2019/7/2
 **/
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private EmpMapper empMapper;
    @Override
    public String hello() {
        return "hello springboot + dubbo";
    }

    @Override
    public Emp selectEmp(int empno){
        System.out.println("-----------------");
        return empMapper.selectEmp();
    }

    @Override
    public int insertEmp(Emp emp) {
        return empMapper.insertEmp(emp);
    }

    @Override
    public int insertSelective(Emp emp) {
        return empMapper.insertSelective(emp);
    }
}
