package com.sspaas.sspaascloud.dao.appmng.user;

import java.util.List;
import java.util.Map;
import com.sspaas.sspaascloud.entity.appmng.UserPurchaseCapacity;

public interface UserPurchaseCapacityMapper {

	/**
	 * @Name: 个人扩容记录
	 * @Author: 郭海英（作者）
	 * @Return: ResponseEntity<DataResult>
	 */
	List<Map<String, Object>> getUserCapacityList(Map<String, Object> map);

	/**
	 * @Name: addUserPurchaseCapacity
	 * @Description: 添加用户初始容量
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月9日
	 * @param userPurchaseCapacity
	 * @return Integer
	 */
	Integer addUserPurchaseCapacity(UserPurchaseCapacity userPurchaseCapacity);
	
	/**
	 * @method: addUserCapacityOrder
	 * @Description: 添加用户购买容量的订单
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2017年2月16日 下午4:25:07
	 * @param userPurchaseCapacity
	 * @return: Integer
	 */
	Integer addUserCapacityOrder(UserPurchaseCapacity userPurchaseCapacity);
	
	/**
	 * @method: selectOrderByNum
	 * @Description: 根据订单号查询订单
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2017年2月17日 下午2:38:34
	 * @param outTradeNo
	 * 									：订单号
	 * @return: UserPurchaseCapacity
	 * 									：订单
	 */
	UserPurchaseCapacity selectOrderByNum(String outTradeNo);
	
	/**
	 * @method: selectCapacityTimeByUserId
	 * @Description: 查询用户所有正常容量订单
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2017年2月20日 上午11:22:55
	 * @param userId
	 * @return: List<UserPurchaseCapacity>
	 * 								：mature_time			容量到期时间
	 * 								：order_number		订单号
	 * 								：capacity_id			容量id
	 */
	List<UserPurchaseCapacity> selectCapacityTimeByUserId(Integer userId);
	
	/**
	 * @method: updateOrderByNum
	 * @Description: 根据订单号更新订单信息
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2017年2月17日 下午3:29:45
	 * @param outTradeNo
	 * 								：订单号
	 * @param time
	 * 								：系统当前时间戳
	 * @return: Integer
	 * 								：更新受影响行数
	 */
	Integer updateOrderByNum(String outTradeNo, long time);
	
	/**
	 * @method: updateStateByNum
	 * @Description: 更新用户订单信息为到期
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2017年2月20日 上午11:18:20
	 * @param orderNumber
	 * @return: Integer
	 */
	Integer updateStateByNum(String orderNumber);
	
	/**
	 * @Name: buyCapacity
	 * @Description: 用户购买容量添加记录
	 * @Author: 郭海英（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月9日
	 * @param map
	 * @return Integer
	 */
	Integer buyCapacity(Map<String, Object> map);

}