package com.sspaas.sspaascloud.service.capacity.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sspaas.sspaascloud.dao.capacity.CapacityMapper;
import com.sspaas.sspaascloud.entity.Capacity;
import com.sspaas.sspaascloud.entity.PageCrt;
import com.sspaas.sspaascloud.service.capacity.ICapacityService;
import com.sspaas.sspaascloud.util.swift.SwiftUploadPojo;
import com.sspaas.sspaascloud.util.swift.SwiftUploadUtils;

@Service
public class CapacityService implements ICapacityService {

	Logger logger = Logger.getLogger(CapacityService.class);
	
	@Autowired
	private CapacityMapper capacityDAO;
	
	@Autowired
	private SwiftUploadPojo uploadPojo;

	@Override
	public PageCrt capacityTypeList(PageCrt page) {
		logger.info("分页查询容量类型");
        int rows = page.getRows();
        int pag = page.getPage();
        Integer records=capacityDAO.total();
        page.setRecords(records);
        page.setTotal(records % rows == 0 ? records / rows : records / rows + 1);
        int index = ((pag - 1) * rows);
        page.setIndex(index);
        List<Capacity> list = capacityDAO.selectCapacityTypeList(page);
        page.setList(list);
        return page;
	}

	@Override
	public Integer editCapacity(Capacity capacity,MultipartFile file) {
		
		if(file!=null){
			// 先上传头像
			uploadPojo.setMultipartFile(file);
			uploadPojo.setContainerName("capacity");
			String object = SwiftUploadUtils.createObject(uploadPojo);
			capacity.setIcon(object);
		}
		
		return capacityDAO.editCapacity(capacity);
	}

	@Override
	public Integer addCapacity(Capacity capacity, MultipartFile file) {
		
		// 先上传头像
		uploadPojo.setMultipartFile(file);
		uploadPojo.setContainerName("capacity");
		String object = SwiftUploadUtils.createObject(uploadPojo);
		
		capacity.setIcon(object);
		if (capacity.getType() == 1){ //1 赠送  2购买
			Integer capacityId = capacityDAO.selectCapacityByType();
			if (capacityId != null){
				return 2;
			}
		}
		
		capacity.setAddTime(new Date().getTime());
		return capacityDAO.addCapacity(capacity);
	}

	@Override
	public Integer deleteCapacity(int capacityId, String type) {
		if (type.equals("赠送")){ //1 赠送  2购买
			return 2;
		}
		
		return capacityDAO.deleteCapacity(capacityId);
	}
}
