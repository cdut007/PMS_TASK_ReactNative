package com.thirdpart.model;

import android.R.integer;
import android.content.Intent;

public class ConstValues {
	
	public static final String WEATHER = "weather";

	public static final String ACCOUNT = "account";
	
	public static final String ID = "id";
	
	public static final String SEARCH = "search";
	public static final String COMMENT = "comment";
	public static final String FEED_BACK = "feedback";

	

	public static final String DISTANCE = "distance";
	public static final String NOTIFICATION = "notification";
	public static final String Tag = "tag";


	public static final class IntentAction {
		public static final String FIND_LOCATION = "com.jameschen.location.FIND";
		public static final String LOCATION_CHANGED = "com.jameschen.location.CHANGED";
		
	}

	/**
	 * setting .
	 * */
	public static class Item {

		public static final String PLAN = "plan";
		public static final String HOME = "home";
		public static final String ISSUE = "issue";
		public static final String WITNESS = "witness";
		public static final String TASK = "task";
		public static final String MINE = "mine";
		
		public static final String WITNESS_CATEGORY = "witness_category";
		
		
	}

	
	/**
	 * setting .
	 * */
	public static class Setting {

		public static final String SharedName = "set";
		public static final String receiveMsg = "receive_msg";
	}

	
	/**
	 * category info for define ids for diff category...
	 * */
	public static class CategoryInfo {
		public static String name = "category";



	/**
	 * register const values
	 * */
	public static class Register {
		/** requst login action */
		public static final int REQUST_REGISTER = 0X07;

		/** verfiycode success */
		public static final int VERIFIYCODE_SUCC = 0X05;
		/** verfiycode failed */
		public static final int VERIFIYCODE_FAIL = 0x09;
		/** register success */
		public static final int REGISTER_SUCC = 0X01;
		/** register failed */
		public static final int REGISTER_FAIL = 0x03;

		/** register error */
		public static final int REGISTER_ERROR = 0x04;
	}

	public static class User {

		public static final int FEED_BACK_SUCC = 0x02;
		public static final int FEED_BACK_FAIL = 0x03;

		public static final int PASSWORD_MODIFY_SUCC = 0x0;
		public static final int PASSWORD_MODIFY_FAIL = 0x01;

		public static final String SharedName = "user";
		public static final String account = "account";
		public static final String password = "password";
		public static final String userinfo = "userinfo";
		public static final String logon = "loginstate";
		public static final String randomcode = "randomcode";
		public static final String tag = "personinfo";
	}



	/**
	 * login const values
	 * */
	public static class Login {

		/** requst login action */
		public static final int REQUST_LOGIN = 0X01;
		/** Login success */
		public static final int LOGIN_SUCC = 0X0;
		/** Login failed */
		public static final int LOGIN_FAIL = 0x03;
		/** Login error */
		public static final int LOGIN_ERROR = 0x04;

		public static final int ACCOUNT_ERROR = -1;

		public static final int PASSWORD_ERROR = -2;

		/** requst password forget action */
		public static final int REQUST_PASSWORD_FORGET = 0X05;

		/** result password reset action */
		public static final int RESULT_PASSWORD_RESET = 0X06;

	}

	public static class Cache {
		public static final String SharedName = "cache";
		public static final String IMAGE_CACHE_DIR = "imageCache";
		public static final String path = "/mnt/sdcard/PMS/Cache";
		public static final String SCAN_IMAGE_CACHE_DIR = path+"/eventPhoto/";
		public static final String UPLOAD_IMAGE_CACHE_DIR =path+"uploadPhoto/";
		public static final String EXTRA_IMAGE = "extra_image";
		

		public static final int LOAD_CACHE = 1;
	}
}
}
