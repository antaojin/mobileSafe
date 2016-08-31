package com.example.xutils;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private String name;
	@ViewInject(R.id.tv1)
	private TextView tv1;
	
	@ViewInject(R.id.tv2)
	private TextView tv2;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ViewUtils.inject(this);
		
	}
	@Deprecated
	@OnClick(R.id.btn1)
	public void clickXXX(View view){
		Log.d("tag", "tv1="+tv1+"\ntv2="+tv2);
		
		Toast.makeText(this, "我被点击了", Toast.LENGTH_SHORT).show();
	}

}
