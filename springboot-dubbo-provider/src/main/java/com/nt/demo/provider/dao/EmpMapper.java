package com.nt.demo.provider.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.nt.demo.middle.entity.Emp;
import com.nt.demo.provider.utils.MyMapper;

/**
 * Create by TaoTaoNing
 * 2019/7/25
 **/
@DS("db-mysql")
public interface EmpMapper extends MyMapper<Emp> {
}
