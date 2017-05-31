package com.sspaas.sspaascloud.controller.file;


import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.sspaas.sspaascloud.controller.commons.BaseController;
import com.sspaas.sspaascloud.entity.FileType;
import com.sspaas.sspaascloud.entity.PageCrt;
import com.sspaas.sspaascloud.kit.JSONKit;
import com.sspaas.sspaascloud.service.file.IFileTypeService;


@RequestMapping("/fileType")
@Controller
public class FileTypeController extends BaseController {
	
	/**用户管理controller日志记录器*/
	Logger logger = Logger.getLogger(FileTypeController.class);

	@Autowired
	private IFileTypeService fileTypeService;

	JSONKit json= new JSONKit();
	
	/** 跳转到文件类型管理页面 */
	@RequestMapping("/fileTypeList")
	public String toPageFoleType() {
		return "/backstagemng/file-mng/filetype";
	}
	
	
	/**查找所有文件类型*/
	@RequestMapping("/selectAllList")
	public void findAllFileType( PageCrt pageCrt,Integer parentId,  HttpServletResponse response) {
		pageCrt.getMap().put("parentId", parentId);
		PageCrt p = fileTypeService.findAllFileType(pageCrt);
		PrintWriter out;
		out = super.getPrintWriter(response);
		this.objToJson(p, out);
	}
	
	 /** 获取所有类型根目录 */
    @RequestMapping("/getTypeRoot")
    public void getTypeRoot(HttpServletResponse response, PageCrt pageCrt) {
        List<Map<String,Object>> listmap= fileTypeService.getTypeRoot(pageCrt);
        PrintWriter out = json.getPrintWriter(response);
        json.objToJson(listmap, out);
    }
    
    /** 添加 */
    @RequestMapping("/addType")
    public String addType(HttpServletResponse response, PageCrt pageCrt, FileType filetype,MultipartFile file) {
        Integer result= fileTypeService.addType(pageCrt,filetype,file);
        return "/backstagemng/file-mng/filetype";
    }
    
    /** 修改 */
    @RequestMapping("/updateType")
    public String updateType(HttpServletResponse response, PageCrt pageCrt, FileType filetype,MultipartFile file) {
        Integer result= fileTypeService.updateType(pageCrt,filetype,file);
        return "/backstagemng/file-mng/filetype";
    }
    
	
    /** 删除 */
    @RequestMapping("/deleteType")
    public void deleteType(HttpServletResponse response, FileType filetype) {
        Integer result= fileTypeService.deleteType(filetype);
        PrintWriter out = json.getPrintWriter(response);
        json.objToJson(result, out);
    }
    
    
    
    
}
