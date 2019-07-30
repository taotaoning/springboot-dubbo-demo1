package com.nt.demo.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * Create by TaoTaoNing
 * 2019/7/25
 **/
@Data
public class EmpVO implements Serializable {

    private static final long serialVersionUID = 1336158868439733428L;

    private Integer empno;

    private String ename;

    private Integer sal;

    private Integer deptno;
}
