package ldps;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.ldps.model.NodeModel;
import com.ldps.service.INodeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class TestResouce {
	private static Logger logger = Logger.getLogger(CustomerAPITest.class);
	@Resource(name="nodeService")
	private INodeService iNodeService;
	
	@Test
	public void testAddNode(){
		NodeModel nodeModel=new NodeModel();
		nodeModel.setName("绿地大楼1");
		iNodeService.addNode(1, nodeModel);
	}
	@Test
	public void testGetAllNode(){
		logger.info(JSON.toJSON(iNodeService.getAllNodeTree()));
	}
}
