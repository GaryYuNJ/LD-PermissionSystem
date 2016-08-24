package ldps;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.ldps.model.NodeModel;
import com.ldps.model.ResourceModel;
import com.ldps.service.INodeService;
import com.ldps.service.IResourceService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class TestResouce {
	private static Logger logger = Logger.getLogger(CustomerAPITest.class);
	@Resource(name="nodeService")
	private INodeService iNodeService;
	@Resource(name="iResourceService")
	private IResourceService iResourceService;
	@Test
	public void testAddNode(){
		NodeModel nodeModel=new NodeModel();
		nodeModel.setName("绿地大楼1-第4层-北区23");
		iNodeService.addNode(28, nodeModel);
	}
	@Test
	public void testGetAllNode(){
		logger.info(JSON.toJSON(iNodeService.getAllNodeTree()));
	}
	@Test
	public void testCreateResource(){
		ResourceModel rm=new ResourceModel();
		rm.setName("闸机01");
		rm.setTypeId(1);
		rm.setMac("xxxx");
		rm.setNodeId(1);
		rm.setCreateUser(1000);
		iResourceService.createResource(rm);
	}
	//测试资源组新增
	@Test
	public void testCreateResouceGr(){
		
	}
}
