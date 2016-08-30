package ldps;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.ldps.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class TestUser {
	private static Logger logger = Logger.getLogger(CustomerAPITest.class);
	@Resource(name="userService")
	private IUserService iUserService;
	@Test
	public void testQueryUser(){
		logger.info(JSON.toJSON(iUserService.getUserByUP("admin", "admin")));
	}
}
