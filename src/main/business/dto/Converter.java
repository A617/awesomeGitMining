package main.business.dto;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Converter {
	/**
	 * this method cannot handle ArrayList's assignment
	 * @param vo's name
	 *            
	 * @param po object
	 *           
	 * @return vo
	 */
	public static Object convert(String name, Object object) {
		Class<?> classType = object.getClass();
		Field[] datas = classType.getDeclaredFields();
		Object vo = null;
		name = "main.vo." + name;
		try {
			Class<?> c = Class.forName(name);
			vo = c.newInstance();
			// 获取所有的属性
			Field[] fields = c.getDeclaredFields();
			for (Field voField : fields) {
				String voName = voField.getName();
				for (Field poField : datas) {
					String poName = poField.getName();
					// 如果变量名相等且类型相同
					if (poName.equals(voName)) {
						if (voField.getType().equals(poField.getType())) {
							// 打破封装
							voField.setAccessible(true);
							// 给vo对象属性赋值
							voField.set(vo, getValue(poName, object));
						}
					}
				}

			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}

	/**
	 * 
	 * @param name
	 * @return the values in po
	 */
	private static String getValue(String name, Object object) {
		String result = null;
		Class<?> classType = object.getClass();
		// 获取get方法的名字
		String firstLetter = name.substring(0, 1).toUpperCase(); // 将属性的首字母转换为大写
		String methodName = "get" + firstLetter + name.substring(1);
		Method logMethod;
		try {
			logMethod = classType.getMethod(methodName);
			result = (String) logMethod.invoke(object);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
