package com.nt.demo.provider.service;

import com.nt.demo.middle.annotations.ResponseMessage;
import com.nt.demo.middle.annotations.Verifys;
import com.nt.demo.middle.entity.Emp;
import com.nt.demo.middle.entity.User;
import com.nt.demo.middle.intf.TestService;
import com.nt.demo.provider.dao.EmpMapper;
import com.nt.demo.provider.dao.UserMapper;
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

    @Autowired
    private UserMapper userMapper;

    @Override
    public String hello() {
        return "hello springboot + dubbo";
    }

    @Override
    public Emp selectEmp(int empno){
        System.out.println("-----------------");
        return empMapper.selectByPrimaryKey(empno);
    }

    @Override
    public int insertEmp(Emp emp) {
        return empMapper.insert(emp);
    }

    @Verifys
    @Override
    public ResponseMessage insertSelective(Emp emp) {
        empMapper.insertSelective(emp);
        return new ResponseMessage("00","success");
    }

    @Override
    public User selectUserById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
}
