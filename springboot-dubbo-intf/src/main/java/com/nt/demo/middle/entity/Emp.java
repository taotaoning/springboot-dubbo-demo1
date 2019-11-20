package com.nt.demo.middle.entity;


import com.nt.demo.middle.annotations.Verify;


import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Create by TaoTaoNing
 * 2019/7/25
 **/

/**
 * @Table需要引入jar包 persistence-api
 */

@Table(name = "emp")
public class Emp implements Serializable {


    private static final long serialVersionUID = -330785389071166905L;


    @Verify(name = "员工号", required = true,maxLength = 5)
    @Column(name = "empno")
    private Integer empno;

    @Column(name = "ename")
    private String ename;

    @Column(name = "sal")
    private Integer sal;

    @Column(name = "deptno")
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
