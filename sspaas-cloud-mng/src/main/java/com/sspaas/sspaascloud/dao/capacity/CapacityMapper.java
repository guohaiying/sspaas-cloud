package com.sspaas.sspaascloud.dao.capacity;

import java.util.List;

import com.sspaas.sspaascloud.entity.Capacity;
import com.sspaas.sspaascloud.entity.PageCrt;

public interface CapacityMapper {
	
	/**
	 * @method: total
	 * @Description: 容量类型列表数据总数
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月23日 上午11:29:58
	 * @return: Integer
	 */
	Integer total();
	
	/**
	 * @method: selectCapacityTypeList
	 * @Description: 容量类型数据
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月23日 上午11:33:21
	 * @param page
	 * @return: List<Capacity>
	 */
	List<Capacity> selectCapacityTypeList(PageCrt page);
	
	/**
	 * @method: editCapacity
	 * @Description: 修改容量类型
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月23日 下午3:02:58
	 * @param capacity
	 * @return: Integer
	 */
	Integer editCapacity(Capacity capacity);
	
	/**
	 * @method: addCapacity
	 * @Description: 添加容量类型
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月23日 下午4:13:03
	 * @param capacity
	 * @return: Integer
	 */
	Integer addCapacity(Capacity capacity);
	
	/**
	 * @method: selectCapacityByType
	 * @Description: 查询容量类型为赠送的主键
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月23日 下午4:15:19
	 * @return: Integer
	 */
	Integer selectCapacityByType();
	
	/**
	 * @method: deleteCapacity
	 * @Description: 删除容量类型
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月23日 下午5:02:37
	 * @param capacityId
	 * @return: Integer
	 */
	Integer deleteCapacity(int capacityId);
}