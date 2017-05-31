package com.sspaas.sspaascloud.controller.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sspaas.sspaascloud.controller.commons.BaseController;
import com.sspaas.sspaascloud.entity.PageCrt;
import com.sspaas.sspaascloud.service.user.IUserPurchaseCapacityService;


@RequestMapping("/userpurchasecapacity")
@Controller
public class UserPurchaseCapacityController extends BaseController {
	
	/**容量管理controller日志记录器*/
	Logger logger = Logger.getLogger(UserPurchaseCapacityController.class);
	
	@Autowired
	private IUserPurchaseCapacityService userPCapacityService;
	
	/**
	 * @method: usercapacityJsp
	 * @Description: 跳转到订单页面
	 * @Author: 郭海英（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月23日 上午10:35:15
	 * @return: String
	 */
	@RequestMapping("/usercapacityJsp")
	public String usercapacityJsp() {
		return "/backstagemng/users-mng/userpcapacity";
	}
	
	
	/**
	 * @method: capacityTypeList
	 * @Description: 订单列表
	 * @Author: 郭海英（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月23日 上午11:14:26
	 * @param response
	 * @param pageCrt
	 */
	@RequestMapping("/getUserOrder")
	public void getUserOrder(HttpServletResponse response, PageCrt pageCrt) {

		logger.debug("请求所有的容量类型数据");
		PageCrt p =userPCapacityService.getUserOrder(pageCrt);
		PrintWriter out = super.getPrintWriter(response);
		this.objToJson(p, out);
	}
	
	
}
