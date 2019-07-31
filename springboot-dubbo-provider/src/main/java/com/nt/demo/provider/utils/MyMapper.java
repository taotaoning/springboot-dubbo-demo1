package com.nt.demo.provider.utils;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 本地的通用mapper 接口，不能被MapperScan扫描到，否则会报错
 * Create by TaoTaoNing
 * 2019/7/31
 **/

public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
