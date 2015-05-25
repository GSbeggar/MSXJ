package com.msxj.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import com.kaixin.android.R;

public class CreatActivity extends Activity {
	private Button mback;
	private Button mcommit;
	private Spinner mbartype;
	private EditText mbarname;
	private EditText mbardis;
	private TextView mtitle;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creat);
		Init();
	}
	
	
	
	public void Init() {
		
	}
	

}
