package com.sspaas.sspaascloud.service.appmng.android.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sspaas.sspaascloud.dao.appmng.android.AndroidVersionMapper;
import com.sspaas.sspaascloud.entity.appmng.AndroidVersion;
import com.sspaas.sspaascloud.entity.appmng.DataResult;
import com.sspaas.sspaascloud.service.appmng.android.IAndroidVersionService;

@Service
public class AndroidVersionService implements IAndroidVersionService {
	@Autowired
	AndroidVersionMapper androidVersionMapper;

	@Override
	public DataResult getLastVersion() {
		DataResult dataResult = new DataResult();
		AndroidVersion  lastAndroidVersion= androidVersionMapper.getLastVersion();
		dataResult.setStatusCode(200);
		dataResult.setStatusMsg("获取最新版本信息成功");
		dataResult.setDataList(lastAndroidVersion);
		return dataResult;
	}
	
	
}
