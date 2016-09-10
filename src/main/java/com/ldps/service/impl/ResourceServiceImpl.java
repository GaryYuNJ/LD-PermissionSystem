package com.ldps.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ldps.dao.CusGrpResourceRelModelMapper;
import com.ldps.dao.CusResourceRelModelMapper;
import com.ldps.dao.ResourceGrpRelModelMapper;
import com.ldps.dao.ResourceKeyMapper;
import com.ldps.dao.ResourceModelMapper;
import com.ldps.data.APIMessage;
import com.ldps.model.ResourceKeyModel;
import com.ldps.model.ResourceModel;
import com.ldps.service.IResourceService;

@Service("iResourceService")
public class ResourceServiceImpl implements IResourceService {

	@Resource
	private ResourceModelMapper resourceDao;
	@Resource
	private CusResourceRelModelMapper cusResourceRelDao;
	@Resource
	private CusGrpResourceRelModelMapper cusGrpResRelDao;
	@Resource
	private ResourceKeyMapper resourceKeyDao;
	@Resource
	private ResourceGrpRelModelMapper resourceGrpRelDao;
	
	@Override
	public ResourceModel queryResourceByMAC(String mac) {
		return resourceDao.selectByMac(mac);
	}
	
	@Override
	public ResourceModel queryWithGroupsByMAC(String mac) {
		return resourceDao.selectWithGroupsByMAC(mac);
	}

	@Override
	public ResourceModel queryModelById(Integer sourceId) {
		return resourceDao.selectByPrimaryKey(sourceId);
	}
	
	public ResourceModel getReourceAndKeyById(Integer sourceId){
		ResourceModel rm=queryModelById(sourceId);
		if(rm!=null){
			rm.setResourceKeys(queryKeyByresource(sourceId));
		}
		return rm;
	}
	
	public List<ResourceKeyModel> queryKeyByresource(Integer resourceId){
		return resourceKeyDao.selectByResourceId(resourceId);
	}

	/*
	获取building里的公共资源基本信息
	 */
	@Override
	public List<ResourceModel> queryValidPubResByBuildingId(Integer buildingId) {
		return resourceDao.selectValidPubResByBuildingId(buildingId);
	}

	/*
	获取building里的公共资源，resourceKeys同时返回
	 */
	@Override
	public List<ResourceModel> queryPubResWithKeysByBuildingId(Integer buildingId) {
		return resourceDao.selectPubResWithKeysByBuildingId(buildingId);
	}
	
	/*
	获取building里用户有权限设备
	 */
	@Override
	public List<ResourceModel> queryPrivateResByBIdAndCusId(
			Integer buildingId, Long customerId) {
		return cusResourceRelDao.selectPrivateResByBIdAndCusId(buildingId,customerId);
	}

	/*
		根据节点ID获取直接挂载的基础资源
	 */
	@Override
	public List<ResourceModel> queryBasicResByNodeId(Integer nodeId) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
		根据节点ID获取直接挂载的所有资源
	 */
	@Override
	public List<ResourceModel> queryAllResByNodeId(Integer nodeId) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	根据节点ID list获取挂载的所有基础资源
	 */
	@Override
	public List<ResourceModel> queryBasicResByNodeIdList(List<Integer> nodeIds) {
		if(null != nodeIds && nodeIds.size() > 0){
			return resourceDao.selectBasicResByNodeIdList(nodeIds);
		}else{
			return null;
		}
	}

	@Override
	public Integer queryResourceIdByMAC(String mac) {
		// TODO Auto-generated method stub
		ResourceModel model = resourceDao.selectIdByMac(mac);
		
		if(null == model){
			return null;
		}else{
			return model.getId();
		}
	}
	
	//新建资源
	@Override
	public int createResource(ResourceModel model) {
		if(resourceDao.insertSelective(model)>0){
			for(ResourceKeyModel resourceKey:model.getResourceKeys()){
				resourceKey.setResourceId(model.getId());
				resourceKeyDao.insertSelective(resourceKey);
			}
			return 1;
		}
		return 0;
	}
	
	//更新资源
	@Override
	public int updateResource(ResourceModel model) {
		if(null==model)
			return 0;
		if(null==model.getId())
			return createResource(model);
		model.setCreateDate(new Date());
		if(resourceDao.updateByPrimaryKeySelective(model)>0){
			List<Integer> keyIds = new ArrayList<Integer>();
			for(ResourceKeyModel resourceKey:model.getResourceKeys()){
				keyIds.add(resourceKey.getId());
			}
			resourceKeyDao.deleteByReourceNotIn(model.getId(), keyIds);
			for(ResourceKeyModel resourceKey:model.getResourceKeys()){
				resourceKey.setResourceId(model.getId());
				if(resourceKey.getId()==null){
					resourceKeyDao.insertSelective(resourceKey);
				}else{
					resourceKeyDao.updateByPrimaryKeySelective(resourceKey);
				}
			}
			return 1;
		}
		return 0;
	}

	@Override
	public int deleteResource(Integer primaryId) {
		resourceKeyDao.deleteByReourceNotIn(primaryId, null);
		return resourceDao.deleteByPrimaryKey(primaryId);
	}

	@Override
	public List<ResourceModel> queryBasicResByCondition(ResourceModel model, Integer pageNo, Integer pageSize) {
		if(model!=null){
			if(StringUtils.isEmpty(model.getName()))
				model.setName(null);
		} 
		return resourceDao.selectResouceListByCondition(model, pageNo, pageSize);
		//这个我们讨论下
		/*
		if(null == model || null == model.getSpecificUserId()){
			return resourceDao.selectResouceListByCondition(model, pageNo, pageSize);
		}else{
			return cusResourceRelDao.selectResouceListWithSpecUserId(model, pageNo, pageSize);
		}*/
		
	}

	@Override
	public List<ResourceModel> queryResByConditionWithCusId(ResourceModel model, Integer pageNo, Integer pageSize) {
		if(model!=null){
			if(StringUtils.isEmpty(model.getName()))
				model.setName(null);
		} 
		
		if(null == model || null == model.getSpecificUserId()){
			return resourceDao.selectResouceListByCondition(model, pageNo, pageSize);
		}else{
			return cusResourceRelDao.selectResouceListWithSpecUserId(model, pageNo, pageSize);
		}
	}
	
	@Override
	public List<ResourceModel> queryResByConditionWithCusGroupId(ResourceModel model, Integer pageNo, Integer pageSize) {
		if(model!=null){
			if(StringUtils.isEmpty(model.getName()))
				model.setName(null);
		} 
		
		if(null == model || null == model.getSpecificCusGroupId()){
			return resourceDao.selectResouceListByCondition(model, pageNo, pageSize);
		}else{
			return cusGrpResRelDao.selectResouceListWithSpecCusGroupId(model, pageNo, pageSize);
		}
	}

	@Override
	public int queryCountByCondition(ResourceModel model) {
		return resourceDao.selectCountByCondition(model);
	}

	@Override
	public List<ResourceModel> queryPriResWithKeysByBIdAndCusId(
			Integer buildingId, Long customerId) {
		return cusResourceRelDao.selectPriResWIthKeysByBIdAndCusId(buildingId, customerId);
	}

	@Override
	public APIMessage importResFromExcel(String filePath, Integer nodeId) throws IOException {
		
		APIMessage message = new APIMessage(); 
		List<ResourceModel> rModelList = new ArrayList<ResourceModel>();
		FileInputStream fis = null;
		//开始解析
		try{
	        fis = new FileInputStream(filePath);  
	        
	        //Create Workbook instance for xlsx/xls file input stream  
	        Workbook workbook = null;  
	        if(filePath.toLowerCase().endsWith("xlsx")){  
	            workbook = new XSSFWorkbook(fis);  
	        }else if(filePath.toLowerCase().endsWith("xls")){  
	            workbook = new HSSFWorkbook(fis);  
	        }
	           
	        //Get the number of sheets in the xlsx file  
	        int numberOfSheets = workbook.getNumberOfSheets();  
	        //loop through each of the sheets 
	        
	        for(int i=0; i < numberOfSheets; i++){
	               
	            //Get the nth sheet from the workbook  
	            Sheet sheet = workbook.getSheetAt(i);  
	            //every sheet has rows, iterate over them 0 
	            Iterator<Row> rowIterator = sheet.iterator();  
	            //第一行跳过
	            rowIterator.next();
	            //从第二行开始解析
	            while (rowIterator.hasNext())   
	            {  
	                Row row = rowIterator.next();  
	                //Every row has columns, get the column iterator and iterate over them  
	                Iterator<Cell> cellIterator = row.cellIterator();  
	                ResourceModel rModel = new ResourceModel();
	                ResourceKeyModel rKModel = new ResourceKeyModel();
	                List<ResourceKeyModel> rKModelList = new ArrayList<ResourceKeyModel>();
	                for(int a = 1; cellIterator.hasNext();a++){
	                	//Get the Cell object  
	                    Cell cell = cellIterator.next();  
	                    
	                    //check the cell type and process accordingly  
	                    String valueTmp = null;
	                    switch(cell.getCellType()){  
	                        case Cell.CELL_TYPE_STRING:  
	                        	valueTmp = cell.getStringCellValue().trim();
	                            break;  
	                        case Cell.CELL_TYPE_NUMERIC: 
	                        	valueTmp = String.valueOf((int)cell.getNumericCellValue());
	                    }
	                    
	                	switch(a){
	                		case 1 :
	                			rModel.setName(valueTmp);
	                			break;
	                		case 2 :
	                			rModel.setStatus(valueTmp);
	                			break;
	                		case 3 :
	                			rModel.setBuildingId(StringUtils.isEmpty(valueTmp)? null:Integer.parseInt(valueTmp));
	                			break;
	                		case 4 :
	                			rModel.setTypeId(StringUtils.isEmpty(valueTmp)? null:Integer.parseInt(valueTmp));
	                			break;
	                		case 5 :
	                			rModel.setFloor(StringUtils.isEmpty(valueTmp)? null:Integer.parseInt(valueTmp));
	                			break;
	                		case 6 :
	                			rModel.setShareEnable(valueTmp);
	                			break;
	                		case 7 :
	                			rModel.setIsVirtualResource(valueTmp);
	                			break;
	                		case 8 :
	                			rModel.setPermissionAttrId(StringUtils.isEmpty(valueTmp)? null:Integer.parseInt(valueTmp));
	                			break;
	                		case 9 :
	                			rModel.setDeviceType(StringUtils.isEmpty(valueTmp)? null:Integer.parseInt(valueTmp));
	                			break;
	                		case 10 :
	                			rModel.setNodeId(StringUtils.isEmpty(valueTmp)? nodeId:Integer.parseInt(valueTmp));
	                			break;
	                		case 11 :
	                			rKModel.setManufacturerId(StringUtils.isEmpty(valueTmp)? null:Integer.parseInt(valueTmp));
	                			break;
	                		case 12 :
	                			rKModel.setMac(valueTmp);
	                			break;
	                		case 13 :
	                			rKModel.setPassword(valueTmp);
	                			break;
	                	}
	                }
	                rModel.setCreateDate(new Date());
	                rModel.setCreateUser(0);
	                rKModelList.add(rKModel);
	                rModel.setResourceKeys(rKModelList);
	                rModelList.add(rModel);
	                
	                //end of cell iterator  
	            } //end of rows iterator  
	        } //end of sheets for loop  
	        //close file input stream  
		}catch(Exception e){
			e.printStackTrace();
			message.setStatus(-1);
        	message.setMessage("文件解析失败！");
        	return message;
		}finally {
            if (fis != null) {
            	fis.close();
            	fis = null;
            }
        }
		//解析结束
        
        
        StringBuffer failedResourceNames = new StringBuffer("");
        //循环插入resource
        if( null != rModelList && rModelList.size() > 0){
        	//先根据resourceName查找是否已有同名资源
        	ResourceModel currentModel = null;
        	for(ResourceModel newResModel : rModelList){
        		
        		try{
        			currentModel = resourceDao.selectWithResKeysByName(newResModel.getName());
                	
                	//有，更新(继续查询要保存的蓝牙设备是否已存在)；没有新建
                	if(null != currentModel){
                		newResModel.setId(currentModel.getId());
                		resourceDao.updateByPrimaryKeySelective(newResModel);
                		
                		//更新或者添加蓝牙设备
                		if(null != newResModel.getResourceKeys() && newResModel.getResourceKeys().size() >0 ){
                			ResourceKeyModel  newRKModel =  newResModel.getResourceKeys().get(0);
                			//先查看是否已存在相同厂商的信息，有，更新；没有，添加
                			for(ResourceKeyModel currentKeyModel: currentModel.getResourceKeys()){
                    			if(currentKeyModel.getManufacturerId() == newRKModel.getManufacturerId()){
                    				newRKModel.setId(currentKeyModel.getId());
                    			}
                    		}
                			newRKModel.setResourceId(currentModel.getId());
                			if(null != newRKModel.getId()){
                				//更新
                				resourceKeyDao.updateByPrimaryKeySelective(newRKModel);
                				
                			}else{
                				//添加
                				resourceKeyDao.insertSelective(newRKModel);
                				
                			}
                		}
                	}else{
                		int flag = resourceDao.insertSelective(newResModel);
                		
                		//资源添加成功，添加蓝牙设备信息
                		if(flag == 1){
                			//获取resourceId
                    		ResourceModel modelT= resourceDao.selectByCondition(newResModel);
                    		ResourceKeyModel  newRKModel =  newResModel.getResourceKeys().get(0);
                    		newRKModel.setResourceId(modelT.getId());
                			resourceKeyDao.insertSelective(newRKModel);
                		}
                	}
        		}catch(Exception e){
        			e.printStackTrace();
        			failedResourceNames.append(newResModel.getName()+", ");
        			message.setMessage("有部分资源导入失败："+failedResourceNames.toString());
        			message.setStatus(2);
        		}
        	}
        }else{
        	message.setStatus(0);
        	message.setMessage("没有可用数据");
        }
        
        return message;
	}

	@Override
	public List<ResourceModel> queryBasicResByConditionWithGId(
			ResourceModel model, Integer pageNo, Integer pageSize) {
		if(model!=null){
			if(StringUtils.isEmpty(model.getName()))
				model.setName(null);
		} 
		return resourceGrpRelDao.selectResouceListByConditionWithGId(model, pageNo, pageSize);
	}
	
	@Override
	public int selectCountConditionWithGId(ResourceModel model) {
		return resourceGrpRelDao.selectResouceCountConditionWithGId(model);
	}
	

	@Override
	public ResourceModel queryValidResByCIdAndMac(Long customerId,
			Integer resourceId) {
		//线检查是否是公共资源
		ResourceModel rModel = resourceDao.selectWithResKeysById(resourceId);
		if(rModel == null){
			return null;
			
		//如果是公共资源，直接返回资源详情
		}else if (null != rModel.getPermissionAttrId() && rModel.getPermissionAttrId() == 1){

			return rModel;
			
		//如果不是公共资源，要检查权限，有权限就返回详情
		}else{
			return cusResourceRelDao.selectValidResByCIdAndMac(customerId, resourceId);
		}
	}
}
