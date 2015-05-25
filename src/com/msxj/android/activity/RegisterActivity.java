package com.msxj.android.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.kaixin.android.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity{
	private Button add;  
	private TextView login;
    private EditText mphone;  
    private EditText name; 
    private EditText pass;
    private EditText pass2;
    private ProgressDialog mDialog;  
    private String responseMsg = "";  
    private static final int REQUEST_TIMEOUT = 5*1000;//设置请求超时10秒钟    
    private static final int SO_TIMEOUT = 10*1000;  //设置等待数据超时时间10秒钟    
    private static final int LOGIN_OK = 1;  
      
	
	 public void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState);  
	        setContentView(R.layout.register_activity);  
	        add = (Button)findViewById(R.id.register_add);  
	       name= (EditText)findViewById(R.id.register_name);  
	       pass = (EditText)findViewById(R.id.register_pass);  
	       pass2 = (EditText)findViewById(R.id.register_pass2); 
	       mphone = (EditText)findViewById(R.id.register_phone);  
	       login=(TextView)findViewById(R.id.register_login);
	       //登录
	     login.setOnClickListener(new TextView.OnClickListener(){

	   		@Override
	   		public void onClick(View arg0) {
	   			// TODO Auto-generated method stub
	   			Intent intent = new Intent();  
	               intent.setClass(RegisterActivity.this, LoginActivity.class);  
	               startActivity(intent);  
	               finish();
	   		}
	          });
	         
	       //注册
	    		   add.setOnClickListener(new Button.OnClickListener()  
	        {  
	  
	            @Override  
	            public void onClick(View arg0) {  
	            	String uid=name.getEditableText().toString();
	            	String pwd=pass.getEditableText().toString();
	            	String phone=mphone.getEditableText().toString();
	            	String pwd2=pass2.getEditableText().toString();
	            if(phone.equals("")){
	        		Toast.makeText(RegisterActivity.this, "请填写手机号码", Toast.LENGTH_SHORT).show();
	        		}
	            
	            else if(uid.equals("")){
	        		Toast.makeText(RegisterActivity.this, "请填写用户名", Toast.LENGTH_SHORT).show();
	    		
	    	}
	            	else if(pwd.equals("")){
	            		Toast.makeText(RegisterActivity.this, "请填写密码", Toast.LENGTH_SHORT).show();
	            		
	            	} 		
	            	else if(!pwd.equals(pwd2)){
	            		Toast.makeText(RegisterActivity.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
	            		
	            	} 		
	            	else
	            	{
	            	
	                mDialog = new ProgressDialog(RegisterActivity.this);  
	                mDialog.setTitle("注册");  
	                mDialog.setMessage("正在连接服务器，请稍后...");  
	                mDialog.show();  
	                Thread loginThread = new Thread(new LoginThread());  
	                  
	                loginThread.start();  
	            	}
	  
	            }  
	              
	        });  
	          
	    }  
	      
	      
	    private boolean loginServer(String username, String password,String phone)  
	    {  
	        boolean loginValidate = false;  
	        //使用apache HTTP客户端实现  
	        String urlStr = "http://192.168.56.1:8080/Login/signup";  
	        HttpPost request = new HttpPost(urlStr);  
	        //如果传递参数多的话，可以对传递的参数进行封装  
	        List<NameValuePair> params = new ArrayList<NameValuePair>();  
	        //添加用户名和密码  
	        params.add(new BasicNameValuePair("username",username));  
	        params.add(new BasicNameValuePair("password",password)); 
	        params.add(new BasicNameValuePair("phone",phone)); 
	        try  
	        {  
	            //设置请求参数项  
	            request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));  
	            HttpClient client = getHttpClient();  
	            //执行请求返回相应  
	            HttpResponse response = client.execute(request);  
	              
	            //判断是否请求成功  
	            if(response.getStatusLine().getStatusCode()==200)  
	            {  
	            	System.out.println("1111111111111111");
	                loginValidate = true;  
	                //获得响应信息  
	                responseMsg = EntityUtils.toString(response.getEntity());  
	            }  
	        }catch(Exception e)  
	        {  
	            e.printStackTrace();  
	        }  
	        return loginValidate;  
	    }  
	      
	     
	      
	    //初始化HttpClient，并设置超时  
	    public HttpClient getHttpClient()  
	    {  
	        BasicHttpParams httpParams = new BasicHttpParams();  
	        HttpConnectionParams.setConnectionTimeout(httpParams, REQUEST_TIMEOUT);  
	        HttpConnectionParams.setSoTimeout(httpParams, SO_TIMEOUT);  
	        HttpClient client = new DefaultHttpClient(httpParams);  
	        return client;  
	    }  
	  
	    //Handler  
	    Handler handler = new Handler()  
	    {  
	        public void handleMessage(Message msg)  
	        {  
	            switch(msg.what)  
	            {  
	            case 0:  
	                mDialog.cancel();  
	  
	                Toast.makeText(getApplicationContext(), "注册成功！请登录", Toast.LENGTH_LONG).show();  
	                Intent intent = new Intent();  
	                intent.setClass(RegisterActivity.this, LoginActivity.class);  
	                startActivity(intent);  
	                finish();  
	                break;  
	            case 1:  
	                mDialog.cancel();  
	                Toast.makeText(getApplicationContext(), "用户名已存在", Toast.LENGTH_SHORT).show();  
	                break;  
	            case 2:  
	                mDialog.cancel();  
	                Toast.makeText(getApplicationContext(), "注册失败，请重新注册", Toast.LENGTH_SHORT).show();  
	                break;  
	            case 3:  
	                mDialog.cancel();  
	                Toast.makeText(getApplicationContext(), "服务器验证失败", Toast.LENGTH_SHORT).show();  
	                break;  
	              
	            }  
	              
	        }  
	    };  
	      
	    //LoginThread线程类  
	    class LoginThread implements Runnable  
	    {  
	  
	        @Override  
	        public void run() {  
	            String username = name.getText().toString();  
	            String password =pass.getText().toString();
	            String phone = mphone.getText().toString();
	            System.out.println("username="+username+":password="+password+":phone="+phone);  
	                  
	            //URL合法，但是这一步并不验证密码是否正确  
	            boolean loginValidate = loginServer(username, password,phone);  
	            System.out.println("----------------------------bool is :"+loginValidate+"----------response:"+responseMsg);  
	            Message msg = handler.obtainMessage();  
	            if(loginValidate)  
	            {  
	                if(responseMsg.equals("success"))  
	                {  
	                    msg.what = 0;  
	                    handler.sendMessage(msg);  
	                }else if (responseMsg.equals("userexist")) 
	                {  
	                    msg.what = 1;  
	                    handler.sendMessage(msg);  
	                }  
	            }else  
	            {  
	                msg.what = 2;  
	                handler.sendMessage(msg);  
	            }  
	        }  
	          
	    }  
	     
}
