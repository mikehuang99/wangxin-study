/**
 *  广州旺鑫计算机科技有限公司
 */
package com.wangxin.langshu.business.util.tools;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;

/**
 * Object To Map and Map To Object
 * 
 *  
 */
public final class ObjectMapUtil2 {

	
	
	
    /**
	 * map转对象方法
	 * 
	 * @param entity
	 * @param params
	 * @return
	 */
	public static <T> T mapToObjModle(T entity, Map<String, Object> params) {
		Class<?> clazz = entity.getClass();
		// 得到对象的字段
		List<Field> fields = getAccessibleFields(clazz);
		// 迭代字段
		for (Field f : fields) {
			String name = f.getName();
			Object objVal = params.get(name);
			// 找到对应值，进行转化设置
			if (objVal != null) {
				try {
					if (f.getType().equals(String.class)) {
						objVal = String.valueOf(objVal);
					} else if (!f.getType().isAssignableFrom(objVal.getClass())) {
						if (String.valueOf(objVal)!=null&&!"".equals(String.valueOf(objVal).trim())) {
							//转换依赖方法：org.apache.commons.beanutils.ConvertUtils.convert(Object, Class<?>)
							objVal = ConvertUtils.convert(objVal, f.getType());
						} else {
							objVal = null;
						}
					}
					f.set(entity, objVal);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
		return entity;
	}
 
	/**
	 * 循环向上转型, 获取对象所有的DeclaredField
	 * 
	 * 如向上转型到Object仍无法找到, 返回null.
	 */
	public static List<Field> getAccessibleFields(final Class<?> clazz) {
		List<Field> fields = new ArrayList<>();
		for (Class<?> superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
			for (Field f : superClass.getDeclaredFields()) {
				boolean hasInSubClass = false;
				for (Field f2 : fields) {
					if (f2.getName().equals(f.getName())) {
						hasInSubClass = true;
						break;
					}
				}
				if (!hasInSubClass) {
					makeAccessible(f);
					fields.add(f);
				}
			}
		}
		return fields;
	}
 
	/**
	 * 改变private/protected的成员变量为public，尽量不调用实际改动的语句，避免JDK的SecurityManager抱怨。
	 */
	public static void makeAccessible(Field field) {
		if ((!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers())
				|| Modifier.isFinal(field.getModifiers())) && !field.isAccessible()) {
			field.setAccessible(true);
		}
	}

}
