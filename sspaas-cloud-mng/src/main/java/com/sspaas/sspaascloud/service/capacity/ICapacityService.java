package com.sspaas.sspaascloud.service.capacity;

import org.springframework.web.multipart.MultipartFile;

import com.sspaas.sspaascloud.entity.Capacity;
import com.sspaas.sspaascloud.entity.PageCrt;

public interface ICapacityService {

    /**
     * @method: capacityTypeList
     * @Description: 容量类型列表
     * @Author: 董绍威（作者）
     * @Version: V1.00 （版本号）
     * @Create Date: 2016年12月23日 上午11:26:24
     * @param pageCrt
     * @return: PageCrt
     */
	public PageCrt capacityTypeList(PageCrt pageCrt);
	
	/**
	 * @method: editCapacity
	 * @Description: 修改容量类型
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月23日 下午3:01:24
	 * @param capacity
	 * @return: Integer
	 */
	public Integer editCapacity(Capacity capacity,MultipartFile file);
	
	/**
	 * @method: addCapacity
	 * @Description: 添加容量类型
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月23日 下午3:51:17
	 * @param capacity
	 * @return: Integer
	 */
	public Integer addCapacity(Capacity capacity, MultipartFile file);
	
	/**
	 * @method: deleteCapacity
	 * @Description: 删除容量类型
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月23日 下午4:58:34
	 * @param capacityId
	 * @param type
	 * @return: Integer
	 */
	public Integer deleteCapacity(int capacityId, String type);
}
