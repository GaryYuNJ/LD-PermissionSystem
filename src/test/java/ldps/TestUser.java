package ldps;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.ldps.service.IUserService;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class TestUser {
	private static Logger logger = Logger.getLogger(CustomerAPITest.class);
	//@Resource(name="userService")
	private IUserService iUserService;
	@Test
	public void testQueryUser(){
		logger.info(JSON.toJSON(iUserService.getUserByUP("admin", "admin")));
	}
	@Test
	public void test(){
		String ss="buildingId=1&mobile=15951976919&123qweASDzxc";
		logger.info(DigestUtils.md5Hex(ss));
	}
}
