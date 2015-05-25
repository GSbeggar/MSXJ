package com.msxj.android.activity;

import android.content.Intent;
import android.os.Bundle;

import com.kaixin.android.R;
import com.msxj.android.MSXJActivity;

/**
 * 启动引导页
 * 
 * @author beggar
 * 
 */
public class StartActivity extends MSXJActivity implements Runnable {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_activity);
		// 启动一个线程
		new Thread(this).start();
	}

	public void run() {
		try {
			// 一秒后跳转到登录界面
			Thread.sleep(1000);
			startActivity(new Intent(StartActivity.this, LoginActivity.class));
			finish();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}