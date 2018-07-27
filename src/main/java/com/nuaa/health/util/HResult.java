package com.nuaa.health.util;

public class HResult {
	public final static int S_OK = 0;
	
	public final static int E_UNKNOWN = 100000;
	public final static int E_ERROR_PARAMETER = 100001;
	public final static int E_FILE_EXCEPTION = 100002;
	public final static int E_DATABASE_ERROR = 100003;
	
	public final static int E_UPLOAD_FAIL = 120000;
	public final static int E_UPLOAD_FILE_EMPTY = 120001;
	
	public final static int E_USER_EXISTENCE = 130000;
	public final static int E_USER_NOTEXIST = 130001;
	public final static int E_PASSWORD_ERROR = 130002;
	public final static int E_USER_INFO_EXISTENCE = 130003;
	public final static int E_TOKEN_EXPIRE_OR_NOT_EXISTENCE = 130004;
	
	public final static int E_SETTING_EXISTENCE = 140000;
}
