package com.sspaas.sspaascloud.dao.appmng.android;

import com.sspaas.sspaascloud.entity.appmng.AndroidVersion;

public interface AndroidVersionMapper {

	/**
	 * @Title: getLastVersion 
	 * @Description:获取安卓最新版本信息
	 * @author: joker
	 * @Create:2017年3月29日下午5:24:22
	 * @return AndroidVersion 
	 */
	AndroidVersion getLastVersion();

}
