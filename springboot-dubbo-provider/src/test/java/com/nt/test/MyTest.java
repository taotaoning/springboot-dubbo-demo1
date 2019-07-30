package com.nt.test;

import com.nt.demo.provider.ProviderApplication;
import com.nt.demo.provider.dao.EmpMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Create by TaoTaoNing
 * 2019/7/29
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProviderApplication.class)
public class MyTest {
    @Autowired
    EmpMapper empMapper;

    @Test
    public void test1(){
        System.out.println(empMapper.selectEmp());
    }
}
