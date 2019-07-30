package com.nt.demo.middle.entity;

import java.io.Serializable;

/**
 * Create by TaoTaoNing
 * 2019/7/25
 **/
public class Emp implements Serializable {


    private static final long serialVersionUID = -330785389071166905L;

    private Integer empno;

    private String ename;

    private Integer sal;

    private Integer deptno;

    public Integer getEmpno() {
        return empno;
    }

    public void setEmpno(Integer empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Integer getSal() {
        return sal;
    }

    public void setSal(Integer sal) {
        this.sal = sal;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    @Override
    public String toString() {
        return "EmpDTO{" +
                "empno=" + empno +
                ", ename='" + ename + '\'' +
                ", sal=" + sal +
                ", deptno=" + deptno +
                '}';
    }
}
