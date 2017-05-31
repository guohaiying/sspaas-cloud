package com.sspaas.sspaascloud.service.system.impl;

import java.util.List;

import static org.aspectj.bridge.Version.text;

public class MenuRoleJson {

	private String id;
	private String pId;
	private String name;
	private boolean checked;
	private boolean open;

    public void setId(String id) {
		this.id = id;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
    public void setOpen(boolean open) {
        this.open = open;
    }


}
