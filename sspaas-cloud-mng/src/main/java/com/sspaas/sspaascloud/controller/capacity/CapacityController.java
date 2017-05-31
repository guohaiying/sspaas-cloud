package com.sspaas.sspaascloud.controller.capacity;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.sspaas.sspaascloud.controller.commons.BaseController;
import com.sspaas.sspaascloud.entity.Capacity;
import com.sspaas.sspaascloud.entity.PageCrt;
import com.sspaas.sspaascloud.service.capacity.ICapacityService;

/**
 * 云盘后台管理系统-容量管理Controller
 * 
 * 关于容量的操作
 * 
 * @author dongshaowei
 */
@RequestMapping("/capacity")
@Controller
public class CapacityController extends BaseController {
	
	/**容量管理controller日志记录器*/
	Logger logger = Logger.getLogger(CapacityController.class);
	
	@Autowired
	private ICapacityService capacityService;
	
	/**
	 * @method: capacityJsp
	 * @Description: 跳转到容量类型页面
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月23日 上午10:35:15
	 * @return: String
	 */
	@RequestMapping("/capacityJsp")
	public String capacityJsp() {

		logger.debug("跳转到：容量类型页面");
		return "/backstagemng/capacity/capacitytype";
	}
	
	/**
	 * @method: capacityTypeList
	 * @Description: 容量类型列表
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月23日 上午11:14:26
	 * @param response
	 * @param pageCrt
	 */
	@RequestMapping("/capacityTypeList")
	public void capacityTypeList(HttpServletResponse response, PageCrt pageCrt) {

		logger.debug("请求所有的容量类型数据");
		PageCrt p =capacityService.capacityTypeList(pageCrt);
		PrintWriter out = super.getPrintWriter(response);
		this.objToJson(p, out);
	}
	
	/**
	 * @method: editCapacity
	 * @Description: 修改容量类型
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月23日 下午3:46:03
	 * @param response
	 * @param capacity
	 */
	@RequestMapping("/editCapacity")
	public void editCapacity(HttpServletResponse response, Capacity capacity,MultipartFile file) {

		logger.debug("修改容量类型："+capacity);
		Integer i = capacityService.editCapacity(capacity,file);
		PrintWriter out = super.getPrintWriter(response);
		if(i != null && i!= 0){
			this.objToJson("success", out);
        }
	}
	
	/**
	 * @method: addCapacity
	 * @Description: 添加容量类型
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月23日 下午4:57:01
	 * @param response
	 * @param capacity
	 */
	@RequestMapping("/addCapacity")
	public void addCapacity(HttpServletResponse response, Capacity capacity,MultipartFile file) {

		logger.debug("添加容量类型："+capacity);
		Integer i = capacityService.addCapacity(capacity,file);
		PrintWriter out = super.getPrintWriter(response);
		if(i != null && i!= 0){
			this.objToJson(i, out);
        }
	}
	
	/**
	 * @method: deleteCapacity
	 * @Description: 删除容量类型
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月23日 下午4:58:04
	 * @param response
	 * @param capacityId: void
	 */
	@RequestMapping("/deleteCapacity")
	public void deleteCapacity(HttpServletResponse response, int capacityId, String type) {

		logger.debug("添加容量类型："+capacityId);
		Integer i = capacityService.deleteCapacity(capacityId, type);
		PrintWriter out = super.getPrintWriter(response);
		if(i != null && i!= 0){
			this.objToJson(i, out);
        }
	}
}
