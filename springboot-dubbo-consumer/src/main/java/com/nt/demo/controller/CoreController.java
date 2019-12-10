package com.nt.demo.controller;

import com.asiainfo.checkstand.service.PayApiService;
import com.nt.demo.middle.entity.Emp;
import com.nt.demo.middle.entity.User;
import com.nt.demo.middle.exception.MyException;
import com.nt.demo.middle.intf.TestService;
import com.nt.demo.pojo.EmpVO;
import com.nt.demo.middle.annotations.ResponseMessage;
import com.nt.demo.pojo.UserVO;
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

    @Reference(version = "1.0.0",
            application = "checkstand",
            registry = "checkstand-registry")
    private PayApiService payApiService;

    @PostMapping("/register")
    public ResponseMessage checkLogin(@RequestBody @Valid EmpVO empVO, BindingResult errors) {

        log.info(empVO.toString());
        Emp emp = new Emp();
        BeanUtils.copyProperties(empVO, emp);
        log.info(emp.toString());
        ResponseMessage responseMessage = null;
        try {
            responseMessage = testService.insertSelective(emp);
        }catch (MyException e){
            return new ResponseMessage(e.getErrorCode(),e.getMessage());
        }
        return responseMessage;
    }

    @GetMapping("/getEmp/{empno}")
    public EmpVO getEmp(@PathVariable(name = "empno", required = true) Integer empno) {
        log.info(empno + "--");
        Emp emp = testService.selectEmp(empno);
        log.info(emp.toString());
        EmpVO empVO = new EmpVO();
        BeanUtils.copyProperties(emp, empVO);
        return empVO;
    }

    @GetMapping("getUser/{userId}")
    public UserVO getUser(@PathVariable(name = "userId", required = true) Integer userId) {
        User user = testService.selectUserById(userId);
        UserVO userVO = new UserVO();
        if (null != user) {
            BeanUtils.copyProperties(user, userVO);
        }else {
            log.info("该用户不存在");
        }

        return userVO;
    }

    @RequestMapping("/send")
    public String getEmp() throws Exception {
        System.out.println("-------------------------");
        return "{resultCode:\"00\",resultMsg:\"cccccc\"}";
    }

}
