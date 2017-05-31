package com.sspaas.sspaascloud.service.system.impl;

import java.util.ArrayList;
import java.util.List;

import com.sspaas.sspaascloud.entity.Menu;


public class MenuJson {

	//计算导航菜单
	public static String getMenuJson(List<Menu> listAll){
		StringBuffer meu=new StringBuffer();
		 meu.append("[");
		 for(int i=0;i<listAll.size();i++){
			 Menu m=listAll.get(i);
			 if(m.getMenuType()==0){
				 meu.append("{\"menuid\":\""+m.getId()+"\",");
				 meu.append("\"icon\":\""+m.getMenuIcon()+"\",");
				 meu.append("\"menuname\":\""+m.getMenuName()+"\",");
				 meu.append("\"menus\":[");
				 for(int it=0;it<listAll.size();it++){
					 Menu mu=listAll.get(it);
					 if(m.getMenuType()==1 || mu.getFatherId().equals(m.getId())){
						 meu.append("{\"mid\":\""+mu.getId()+"\",");
						 meu.append("\"menuname\":\""+mu.getMenuName()+"\",");
						 meu.append("\"icon\":\""+mu.getMenuIcon()+"\",");
						 meu.append("\"url\":\""+mu.getMenuUrl()+"\"},");
					 }
				 }
				 meu.append("]},");
			 }
		 }
		 meu.append("]");

		return meu.toString();
	}
    //计算授权树形图
	public static List<MenuRoleJson> getTree(List<Menu> list, List<String> permissionsList){
		//存储实体类对象
		List<MenuRoleJson> list2=new ArrayList<MenuRoleJson>();
		//存储字段对象
		List<MenuRoleJson> list3=null;
		//遍历目录集合，取相应值
		for(int i=0;i<list.size();i++){
			//实例授权Json实体类
			MenuRoleJson mJson=new MenuRoleJson();
			//获取到目录实体
			Menu me=list.get(i);
			mJson.setId(me.getId());
			mJson.setpId(me.getFatherId());
			mJson.setName(me.getMenuName());
			mJson.setOpen(true);
			System.err.println(permissionsList);
			//查看用户角色是否有权限
			if (permissionsList.contains(me.getId())) {
				mJson.setChecked(true);
			}
			list2.add(mJson);
		}
		return list2;
	}



}
