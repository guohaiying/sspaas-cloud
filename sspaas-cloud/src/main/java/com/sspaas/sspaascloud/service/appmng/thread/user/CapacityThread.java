package com.sspaas.sspaascloud.service.appmng.thread.user;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.sspaas.sspaascloud.dao.appmng.user.UserCapacityMapper;
import com.sspaas.sspaascloud.dao.appmng.user.UserPurchaseCapacityMapper;
import com.sspaas.sspaascloud.entity.appmng.UserPurchaseCapacity;

public class CapacityThread extends Thread {

	/**日志记录器*/
	Logger log = Logger.getLogger(CapacityThread.class);
	
	private int userId;
	private UserPurchaseCapacityMapper userPurchaseCapacityDAO;
	private UserCapacityMapper userCapacityDAO;
	
	public CapacityThread(int userId, UserPurchaseCapacityMapper userPurchaseCapacityDAO, UserCapacityMapper userCapacityDAO){
		this.userId = userId;
		this.userPurchaseCapacityDAO = userPurchaseCapacityDAO;
		this.userCapacityDAO = userCapacityDAO;
	}
	
	public void run(){
		log.debug("多线程处理用户容量到期情况...");
		List<UserPurchaseCapacity> UPClist = userPurchaseCapacityDAO.selectCapacityTimeByUserId(userId); //查询用户所有正常容量订单
		if (UPClist != null){
			for (UserPurchaseCapacity upc : UPClist){
				long matureTime = upc.getMatureTime();
				String orderNumber = upc.getOrderNumber();
				Integer capacityId = upc.getCapacityId();
				if (new Date().getTime() >= matureTime){ //容量已到期
					int result = userPurchaseCapacityDAO.updateStateByNum(orderNumber); //根据订单号更新订单状态为到期
					if (result == 0){
						log.debug("更新订单状态失败！");
						return;
					}
					int res = userCapacityDAO.updateCapacityByUserId(userId, capacityId); //更新用户总容量
					if (res == 0){
						log.debug("更新用户总容量失败！");
						return;
					}
					
					//添加推送逻辑，提示用户购买的容量已到期
				}
			}
		}
	}
}
