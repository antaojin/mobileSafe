# mobileSafe
This is ShenZhen itheima Android class 31.Test Project.

#Android基础day12-服务（下）&常见对话框
***
##1. Android中进程的种类（*）
1. Foreground Process
	1. 拥有一个处于Resumed状态的Activity的进程.
2. Visisable Process
	1. 拥有一个处于Paused状态的Activity的进程.
3. Service Process
	1. 拥有一个通过startService方法启动起来的进程.
4. Background Process
	1. 拥有一个处于停止状态的Activity.如果系统内存不足了,就会被杀死.
5. Empty Progcess
	1. 不拥有任何正在运行的四大组件.当系统需要内存就杀死了.
	2. Android保留空进程的唯一目的是为了缓存,下次启动就很快.牺牲空间换时间.

##2. Service的两种启动方式&生命周期（***）
1. startService
	1. onCreate 
	2. onStartCommand(没执行一次startService就被调用一次)
	3. onDestroy

2. bindService :如果Activity跟Service简单的绑定了,那么当Activity销毁的时候,Service也会被销毁.
	1. onCreate
	2. onBind 
	3. 如果绑定Service的Activity销毁了那么:
	4. onUnbind
	5. onDestory

3. startService+bindService+unBindService
	1. onCreate
	2. onStartCommand
	3. onBind
	4. onUnbind
	5. 当调用stopService的时候才会onDestroy.
	
##3. Activity和Service的交互方式(bindService)（**）
1. Extending the Binder class(需要练习出来)
	案例-音乐播放器
2. Using a Messenger(不讲)

3. Using AIDL(可以实现一个App中的Activity和另外一个App中的Service之间通信)
	1. aidl : Android Interface Definition  Language Android接口定义语言.
	2. IPC : inner Process Communication 进程间通信.

	案例-(美团(MainActivity)-支付宝(AlipayService))(尽可能练习出来)

##4. 常见对话框（***）
核心类:AlertDialog
核心类:ProgressDialog

1. 异常(对话框只能使用Activity的对话框)

		08-24 07:23:15.006: E/AndroidRuntime(2252): Caused by: android.view.WindowManager$BadTokenException: Unable to add window -- token null is not for an application
		08-24 07:23:15.006: E/AndroidRuntime(2252): 	at android.view.ViewRootImpl.setView(ViewRootImpl.java:589)
		08-24 07:23:15.006: E/AndroidRuntime(2252): 	at android.view.WindowManagerImpl.addView(WindowManagerImpl.java:326)
		08-24 07:23:15.006: E/AndroidRuntime(2252): 	at android.view.WindowManagerImpl.addView(WindowManagerImpl.java:224)
		08-24 07:23:15.006: E/AndroidRuntime(2252): 	at android.view.WindowManagerImpl$CompatModeWrapper.addView(WindowManagerImpl.java:149)

	
2. 确定和取消对话框
3. 单选对话框
4. 多选对话框
5. 进度条对话框

	
			int i = 0;
			public void showHorizontalProgressDialog(View view){
				i = 0;
				final ProgressDialog progressDialog = new ProgressDialog(this);
				progressDialog.setIcon(R.drawable.iv8);
				progressDialog.setTitle("下载电影");
				progressDialog.setMessage("请稍后");
				progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				progressDialog.show();
				
				progressDialog.setMax(100);
				
				handler.postDelayed(new Runnable() {
					
					@Override
					public void run() {
						if (i>=100) {
							progressDialog.dismiss();
						}else {
							progressDialog.setProgress(i++);
							//递归调用
							handler.postDelayed(this, 100);
						}
					}
				}, 100);
			}			
			

##5. 样式和主题（*）
1. 样式:其实就是把控件的通用的属性抽取到样式文件中.
2. 样式的继承
	1. 通过parent

			 <style name="MySmallTextView" parent="MyTextView">
	         <item name="android:textSize">18sp</item>
		    </style>

	2. 通过 父样式名.子样式名
		
			<style name="MySmallTextView.Yellow">
       			 <item name="android:textColor">#ffff00</item>
   			 </style>

3. 主题:也是样式,只不过样式是让控件使用的,主题是让Activity使用的.
	
		 <style name="AppTheme.PINK">
	        <item name="android:background">#ff69b4</item>
	        <item name="android:windowFullscreen">true</item>
	         <item name="android:windowNoTitle">true</item>
    		</style>
	
##6. 国际化（*）
i18n:internationalization 
1. 文字的国际化
2. 图片的国际化


##7. www.stackOverFlow.com


##8. Android中Context种类

	https://possiblemobile.com/2013/06/context/(*****)

1. Activity  一个Activity对应一个Context
2. Service 一个Service对应一个Context
3. BroadCastReceiver 中的onReceive(Context ) 方法执行完了了,这里的Context就完了
4. getApplicationContext  只要App的进程在,该Context就一直存在
