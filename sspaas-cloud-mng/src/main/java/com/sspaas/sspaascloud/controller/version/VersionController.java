package com.sspaas.sspaascloud.controller.version;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.sspaas.sspaascloud.controller.commons.BaseController;
import com.sspaas.sspaascloud.entity.AndroidVersion;
import com.sspaas.sspaascloud.entity.PageCrt;
import com.sspaas.sspaascloud.service.version.IAndroidVersionService;


@Controller
@RequestMapping("/version")
public class VersionController extends BaseController{
	/** 日志记录器 */
	Logger log = Logger.getLogger(VersionController.class);
	@Autowired
	IAndroidVersionService androidVersionService;

	@RequestMapping("/version")
	public String allappuser() {
		return "/backstagemng/version/version";
	}
	
	
	
	/**
	 * @method: versionList
	 * @param response
	 * @param pageCrt
	 * @Return: void
	 */
	@RequestMapping("/versionList")
	public void versionList(HttpServletResponse response, PageCrt pageCrt) {

		PageCrt p =androidVersionService.versionList(pageCrt);
		PrintWriter out = super.getPrintWriter(response);
		this.objToJson(p, out);
	}
	
	
	
	/**
	 * @method: editVersion
	 * @Description: 修改版本信息
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月23日 下午3:46:03
	 * @param response
	 * @param capacity
	 */
	@RequestMapping("/editVersion")
	public void editVersion(HttpServletResponse response, AndroidVersion version,MultipartFile file) {

		Integer i = androidVersionService.editVersion(version,file);
		PrintWriter out = super.getPrintWriter(response);
		if(i != null && i!= 0){
			this.objToJson("success", out);
        }
	}
	
	/**
	 * @method: addVersion
	 * @Description: 添加版本信息
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月23日 下午4:57:01
	 * @param response
	 * @param capacity
	 */
	@RequestMapping("/addVersion")
	public void addVersion(HttpServletResponse response, AndroidVersion version,MultipartFile file) {

		Integer i = androidVersionService.addVersion(version,file);
		PrintWriter out = super.getPrintWriter(response);
		if(i != null && i!= 0){
			this.objToJson(i, out);
        }
	}
	
	/**
	 * @method: deleteVersion
	 * @Description: 删除版本信息
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月23日 下午4:58:04
	 * @param response
	 * @param capacityId: void
	 */
	@RequestMapping("/deleteVersion")
	public void deleteVersion(HttpServletResponse response, int id) {

		Integer i = androidVersionService.deleteVersion(id);
		PrintWriter out = super.getPrintWriter(response);
		if(i != null && i!= 0){
			this.objToJson(i, out);
        }
	}
	
	
	

}
