package com.msxj.android.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.kaixin.android.R;
import com.msxj.android.MSXJActivity;

/**
 * 用户反馈类
 * 
 * @author beggar
 * 
 */
public class FeedBackActivity extends MSXJActivity {
	private Button mCancel;
	private Button mSubmit;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feedback_activity);
		findViewById();
		setListener();
	}

	private void findViewById() {
		mCancel = (Button) findViewById(R.id.feedback_cannel);
		mSubmit = (Button) findViewById(R.id.feedback_submit);
	}

	private void setListener() {
		mCancel.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// 关闭当前界面
				finish();
			}
		});
		mSubmit.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Toast.makeText(FeedBackActivity.this, "提交成功",
						Toast.LENGTH_SHORT).show();
				// 关闭当前界面
				finish();
			}
		});
	}
}
