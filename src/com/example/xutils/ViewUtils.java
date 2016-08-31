package com.example.xutils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

public class ViewUtils {
	public  static void inject(Activity activity){
		bindView(activity);//绑定控件
		bindOnClick(activity);//绑定点击事件
	}

	private static void bindView(Activity activity) {
		/*
		 * 1. 获取字节码
		 */
		Class clazz = activity.getClass();
		/*
		 * 2. 获取所有的字段
		 */
		Field[] declaredFields = clazz.getDeclaredFields();
		/*
		 * 3. 遍历字段,只过滤出添加了@ViewInject的字段
		 */
		for(Field field : declaredFields){
			/*
			 * 4. 获取到字段之后,获取字段的注解对象
			 * 
			 */
			ViewInject viewInject = field.getAnnotation(ViewInject.class);
			//如果该字段上有注解就代表这个字段就是用户想绑定View的
			if (viewInject!=null) {
				/*
				 * 5. 获取自定义注解上面的id
				 */
				int resId = viewInject.value();
				/*
				 * 6.通过 View view =  Activity.findViewByid(id) 
				 */
				View view = activity.findViewById(resId);
				
				/*
				 * 7. 通过暴力反射将view设置该当前字段
				 */
				field.setAccessible(true);
				try {
					field.set(activity, view);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	private static void bindOnClick(final Activity activity) {
		/*
		 * 1. 获取字节码
		 */
		Class clazz = activity.getClass();
		/*
		 * 2. 获取所有的Method
		 */
		Method[] declaredMethods = clazz.getDeclaredMethods();
		/*
		 * 3. 遍历方法,只找有自定义注解的方法
		 */
		for(final Method method : declaredMethods){
			/*
			 * 4. 获取当前Method上面的注解
			 */
			OnClick onClick = method.getAnnotation(OnClick.class);
			if (onClick!=null) {
				/*
				 * 5. 获取到注解上的id
				 */
				int resId = onClick.value();

				/*
				 * 6. 通过 View btn =  Activity.findViewByid(id) 
				 */
				final View view = activity.findViewById(resId);
				
				/*
				 * 7. 给btn绑定点击监听事件
				 * btn.setOnClickListener(new OnClickListener(){
				 * 	public void onClick(View view){
				 * 		8. 通过暴力反射调用当前遍历到的Method
				 * 	}
				 * });
				 */
				view.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
//						8. 通过暴力反射调用当前遍历到的Method
						method.setAccessible(true);
						try {
							method.invoke(activity, view);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
				
			}
			
			
			
		}
		
		
	}
}
