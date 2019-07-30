package test.com.nt.demo.controller; 

import com.nt.demo.WebConsumerApplication;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/** 
* CoreController Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 29, 2019</pre> 
* @version 1.0 
*/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebConsumerApplication.class)
public class CoreControllerTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: checkLogin(@RequestBody @Valid EmpVO empVO, BindingResult errors) 
* 
*/ 
@Test
public void testCheckLogin() throws Exception {

}

/** 
* 
* Method: getUser(@PathVariable(name = "empno",required = true) Integer empno) 
* 
*/ 
@Test
public void testGetUser() throws Exception { 
//TODO: Test goes here... 
} 


} 
