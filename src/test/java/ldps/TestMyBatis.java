package ldps;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.ldps.model.UserModel;
import com.ldps.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)     //琛ㄧず缁ф壙浜哠pringJUnit4ClassRunner绫�  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
   
public class TestMyBatis {  
    private static Logger logger = Logger.getLogger(TestMyBatis.class);  
    @Resource  
    private IUserService userService = null;  
  
    @Test  
    public void test1() { 
        UserModel user = userService.getUserById(1); 
        userService.getUserById(1); 
        logger.info(JSON.toJSONString(user));  
    }  
}  