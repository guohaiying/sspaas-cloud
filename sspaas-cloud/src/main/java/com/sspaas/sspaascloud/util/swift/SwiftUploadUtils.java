package com.sspaas.sspaascloud.util.swift;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.common.DLPayload;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.common.Payloads;
import org.openstack4j.model.identity.v3.Token;
import org.openstack4j.model.storage.block.options.DownloadOptions;
import org.openstack4j.model.storage.object.SwiftContainer;
import org.openstack4j.model.storage.object.SwiftObject;
import org.openstack4j.openstack.OSFactory;
import org.springframework.web.multipart.MultipartFile;

import com.sspaas.sspaascloud.kit.DateKit;

/**
 * 文件上传工具类，已经封装完毕
 * 
 * @author lk
 *
 */

public class SwiftUploadUtils {

	private static Token token;

	/**
	 * 
	 * @return 认证成功后，返回项目ID
	 */

	public static OSClientV3 unscopedAuthentication(SwiftUploadPojo uploadPojo) {
		// use Identifier.byId("domainId") or
		// Identifier.byName("example-domain")

		// Identifier domainIdentifier =
		// Identifier.byId("a80c962ce3f249d6b038aa7d2d6f09bb");//domainId
		//
		OSClientV3 os;
		Identifier domainIdentifier1 = Identifier.byName(uploadPojo.getDomainName());
		os = OSFactory.builderV3().endpoint(uploadPojo.getEndPointUrl())
				.credentials(uploadPojo.getUserName(), uploadPojo.getPassword(), domainIdentifier1).authenticate();
		// System.out.println("认证地址:");
		// System.out.println("认证地址:" + os.getEndpoint());
		// System.out.println("tokenId：" + os.getToken().getId());
		// System.out.println("通过认证=========");
		// System.out.println("项目ID:" + os.getToken().getProject().getId());
		return os;
	}

	//
	// public static void main(String[] args) {
	//
	// Identifier domainIdentifier = Identifier.byName("default");
	// OSClientV3 os =
	// OSFactory.builderV3().endpoint("http://221.122.16.191:5000/v3")
	// .credentials("wuchun", "wuchun123", domainIdentifier).authenticate();
	// String containerName = "test";
	// String objectName = "test";
	// File file = new File("D:\\FeiQ.1060559168.exe");
	//
	// String etag = os.objectStorage().objects().put(containerName, objectName,
	// Payloads.create(file));
	// System.out.println(etag);
	// }

	/**
	 * 每天创建一个容器
	 * 
	 * @return
	 */

	public static OSClientV3 createContainer(SwiftUploadPojo uploadPojo) {
		// 容器按日期排序
		OSClientV3 os = unscopedAuthentication(uploadPojo);
		// String containerName = String.valueOf(new Date().getTime());
		List<SwiftContainer> list = (List<SwiftContainer>) os.objectStorage().containers().list();
		for (SwiftContainer container : list) {
			if (uploadPojo.getContainerName().equals(container.getName())) {
				return os;
			}
		}
		os.objectStorage().containers().create(uploadPojo.getContainerName());
		return os;
	}

	/**
	 * 返回值：文件访问或是下载地址
	 */
	public static String createObject(SwiftUploadPojo uploadPojo) {
		MultipartFile file = uploadPojo.getMultipartFile();
		// 文件名唯一性
		String fileName = DateKit.getRandomFileName(uploadPojo.getUserName());
		// 文件类型
		String originName = file.getOriginalFilename();
		String fileType = originName.substring(originName.lastIndexOf("."));
		// 文件输入流
		InputStream is = null;
		try {
			is = file.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 创建容器。创建容器方法中调用注册的方法
		OSClientV3 os = createContainer(uploadPojo);
		String etag = os.objectStorage().objects().put(uploadPojo.getContainerName(), fileName + fileType,
				Payloads.create(is));
		// System.out.println("文件的hashId：" + etag);
		/*
		 * System.out.println("swift文件上传工具类存储成功后返回文件路径：" +
		 * uploadPojo.getSwiftUrl() + os.getToken().getProject().getId() + "/" +
		 * uploadPojo.getContainerName() + "/" + fileName + fileType); token =
		 * os.getToken();
		 */
		return uploadPojo.getSwiftUrl() + os.getToken().getProject().getId() + "/" + uploadPojo.getContainerName() + "/"
				+ fileName + fileType;
	}

	/**
	 * @Name:createObject
	 * @Description:保存对象 返回保存对象名称
	 * @author: joker
	 * @Create:2016年12月5日下午5:52:11
	 * @param uploadPojo
	 * @param method
	 *            任意值 没有使用的参数 方法重载使用区别上面返回下载地址的方法
	 * @return String swift保存文件名称
	 */
	public static String createObject(SwiftUploadPojo uploadPojo, Integer method) {
		MultipartFile file = uploadPojo.getMultipartFile();
		// 文件名唯一性
		String fileName = DateKit.getRandomFileName(uploadPojo.getUserName());
		// 文件类型
		String originName = file.getOriginalFilename();
		String fileType = "";
		if (originName.lastIndexOf(".") != -1) {

			fileType = originName.substring(originName.lastIndexOf("."));
		}
		// 文件输入流
		InputStream is = null;
		try {
			is = file.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 创建容器。创建容器方法中调用注册的方法
		OSClientV3 os = createContainer(uploadPojo);
		String etag = os.objectStorage().objects().put(uploadPojo.getContainerName(), fileName + fileType,
				Payloads.create(is));
		return fileName;
	}

	/**
	 * @Name:getSwiftObject
	 * @Description:获取SwiftObject对象
	 * @author: joker
	 * @Create:2016年12月6日下午3:03:08
	 * @param uploadPojo
	 * @param uploadName
	 *            文件上传时的名称
	 * @return SwiftObject
	 */
	public static SwiftObject getSwiftObject(SwiftUploadPojo uploadPojo, String uploadName) {

		OSClientV3 osV3 = unscopedAuthentication(uploadPojo);

		return osV3.objectStorage().objects().get(uploadPojo.getContainerName(), uploadName);
	}

	public static DLPayload getDLPayload(SwiftUploadPojo uploadPojo, String uploadName,
			DownloadOptions paramDownloadOptions) {

		OSClientV3 osV3 = unscopedAuthentication(uploadPojo);

		return osV3.objectStorage().objects().download(uploadPojo.getContainerName(), uploadName, paramDownloadOptions);
	}

	/**
	 * @Title: getTempUrl
	 * @Description: 获取访问文件临时url
	 * @author: joker
	 * @Create:2016年12月20日下午4:42:30
	 * @param uploadPojo
	 * @param uploadName
	 *            swift存储文件名
	 * @param oldName
	 *            浏览器下载显示文件名
	 * @return
	 * @throws Exception
	 *             String
	 */
	public static String getTempUrl(SwiftUploadPojo uploadPojo, String uploadName, String oldName) throws Exception {
		String method = "GET";
		// 获取unix时间戳
		long time = new Date().getTime() / 1000;
		long expires = time + uploadPojo.getExpiresTime();
		// 拼接文件路径 "/v1/AUTH_bbc95ed02b384596a0d3ec33c934cdb7/container/object";
		String path = uploadPojo.getTempUrlPref() + uploadPojo.getProjectId() + "/" + uploadPojo.getContainerName()
				+ "/" + uploadName;
		String format = String.format("%s\n%s\n%s", method, expires, path);
		// 进行加密
		byte[] signature = HMACSHA1.HmacSHA1Encrypt(format, uploadPojo.getSecretKey());
		String tempUrlSig = HMACSHA1.byte2hex(signature);
		// 拼接tempUrl路径
		return uploadPojo.getSwiftUrl() + uploadPojo.getProjectId() + "/" + uploadPojo.getContainerName() + "/"
				+ uploadName + "?temp_url_sig=" + tempUrlSig + "&temp_url_expires=" + expires + "&filename=" + oldName;

	}
}
