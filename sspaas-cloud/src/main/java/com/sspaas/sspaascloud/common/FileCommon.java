package com.sspaas.sspaascloud.common;

/**
 * 文件状态常量
 * 
 * @Name:FileState
 * @Description:
 * @author: joker
 * @Create:2016年12月5日上午11:44:12
 */
public class FileCommon {
	// 文件状态 1正常 2删除
	/**
	 * 文件状态:正常
	 */
	public static final Short FILE_STATE_NORMAL = 1;
	/**
	 * 文件状态:删除
	 */
	public static final Short FILE_STATE_DELETE = 2;

	// 文件类型
	/**
	 * 文件类型 :未知
	 */
	public static final Short FILE_TYPE_UN_KONWN = 0;
	/**
	 * 文件类型:文件夹
	 */
	public static final Short FILE_TYPE_FOLDER = 1;
	/**
	 * 文件类型:文件
	 */
	public static final Short FILE_TYPE_FILE = 2;
	/**
	 * @Fields FILE_TYPE_ID : 文件夹的file_type_id
	 */
	public static final Integer FILE_TYPE_ID = 1;

	/**
	 * @Fields FILE_TYPE_OTHER_ID : 文件类型为其他ID
	 */
	public static final int FILE_TYPE_OTHER_ID = 49;

}
