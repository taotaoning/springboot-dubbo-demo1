package com.nt.demo.controller;

import com.nt.demo.middle.entity.Emp;
import com.nt.demo.middle.intf.TestService;
import com.nt.demo.pojo.EmpVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Create by TaoTaoNing
 * 2019/7/4
 **/
@Slf4j
@RestController
@RequestMapping("/core")
public class CoreController {

    @Reference
    private TestService testService;

    @PostMapping("/register")
    public String checkLogin(@RequestBody @Valid EmpVO empVO, BindingResult errors){

        log.info(empVO.toString());

        Emp emp = new Emp();
        BeanUtils.copyProperties(empVO,emp);
        log.info(emp.toString());
        int result = testService.insertSelective(emp);
        if (0 != result){
            return "success";
        }
        return "fail";
    }

    @GetMapping("/getEmp/{empno}")
    public EmpVO getUser(@PathVariable(name = "empno",required = true) Integer empno){
        log.info(empno+"--");
        Emp emp = testService.selectEmp(empno);
        log.info(emp.toString());
        EmpVO empVO = new EmpVO();
        BeanUtils.copyProperties(emp,empVO);
        return empVO;
    }

}
