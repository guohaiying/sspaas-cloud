package com.sspaas.sspaascloud.controller.file;


import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sspaas.sspaascloud.controller.commons.BaseController;
import com.sspaas.sspaascloud.entity.PageCrt;
import com.sspaas.sspaascloud.kit.JSONKit;
import com.sspaas.sspaascloud.service.file.IFileService;


@RequestMapping("/file")
@Controller
public class FileController extends BaseController {
	
	/**用户管理controller日志记录器*/
	Logger logger = Logger.getLogger(FileController.class);
	
	@Autowired
	private IFileService fileService;

	JSONKit json= new JSONKit();

	
	/** 跳转到文件管理页面 */
	@RequestMapping("/fileList")
	public String toPageFile() {
		return "/backstagemng/file-mng/file";
	}
	
	
	/**查找所有文件*/
	@RequestMapping("/selectAllList")
	public void findAllFile( PageCrt pageCrt,Integer parentId,Integer userId, String username, String fileName, HttpServletResponse response) {
		if(parentId==null){
			parentId=0;
		}
		
		pageCrt.getMap().put("parentId", parentId);
		pageCrt.getMap().put("userId", userId);
		pageCrt.getMap().put("username", username);
		pageCrt.getMap().put("fileName", fileName);
		PageCrt p = fileService.findAllFile(pageCrt);
		PrintWriter out;
		out = super.getPrintWriter(response);
		this.objToJson(p, out);
	}
	
	
	 /** 获取所有类型根目录 */
    @RequestMapping("/getAllFileUser")
    public void getAllFileUser(HttpServletResponse response, PageCrt pageCrt) {
        List<Map<String,Object>> listmap= fileService.getAllFileUser(pageCrt);
        PrintWriter out = json.getPrintWriter(response);
        json.objToJson(listmap, out);
    }
    
    
    /** 根据userId查询用户树结构 */
    @RequestMapping("/getAllFileByUserId")
    public void getAllFileByUserId(HttpServletResponse response, Integer userId) {
        List<Map<String,Object>> listmap= fileService.getAllFileByUserId(userId);
        PrintWriter out = json.getPrintWriter(response);
        json.objToJson(listmap, out);
    }
	
}
