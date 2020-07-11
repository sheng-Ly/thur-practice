package com.sheng.thur.practice.util;

import com.sheng.thur.practice.exception.ServiceException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Description：用于判断对象的属性是否为空
 *
 * @author sheng
 * @date 2020/7/10 22:21
 * @since JDK 1.8
 */
public class IsAllFieldsNullUtils {
    /**
     * 用于判断属姓是否为空，要求属姓有 get 方法
     *
     * @param o          要判断属性是否为空的对象
     * @param fieldNames 要排除的属性名
     * @return true 代表全属性为空，false 则相反
     * @throws ServiceException 可能出现的异常
     */
    public static boolean isNull(Object o, String... fieldNames) throws ServiceException {
        // 非空判断
        if (o == null) {
            // 所有都为空
            return true;
        }
        // 用来存储排除的方法名
        List<String> list = null;
        // 非空判断
        if (fieldNames != null) {
            list = getMethodNamesList(fieldNames);
        }
        // 获取所有的方法，这里可以设置为获取公开方法，以为 get 方法为公开方法
        Method[] methods = o.getClass().getDeclaredMethods();
        // 用于接收方法的返回值
        Object obj = null;
        try {
            for (Method method : methods) {
                // 判断是否排除某个元素
                if (fieldNames != null) {
                    // 判断是否为 get方法
                    if (method.getName().startsWith("get") && !list.contains(method.getName())) {
                        // 都是公开方法，可以不用设置
                        method.setAccessible(true);
                        // 执行方法
                        obj = method.invoke(o);
                        // 判断返回值是否为空
                        if (obj != null) {
                            return false;
                        }
                    }
                } else {
                    // 判断是否为 get方法
                    if (method.getName().startsWith("get")) {
                        // 都是公开方法，可以不用设置
                        method.setAccessible(true);
                        // 执行方法
                        obj = method.invoke(o);
                        // 判断返回值是否为空
                        if (obj != null) {
                            return false;
                        }
                    }
                }
            }
        } catch (Exception e) {
            // 返回 service 层
            throw new ServiceException(StatusCodeEnum.ERROR);
        }
        // 默认返回 true，属性为空
        return true;
    }

    /**
     * 判断传入的对象的属姓是否为空，属姓没有 get 方法
     *
     * @param o          要判断的对象
     * @param fieldNames 要排除的属性名
     * @return true 代表全属性为空，false 则相反
     */
    public static boolean isFieldsNull(Object o, String... fieldNames) throws ServiceException {
        // 非空判断
        if (o == null) {
            // 所有都为空
            return true;
        }
        // 用来存储排除的属姓名
        List<String> list = null;
        // 非空判断
        if (fieldNames != null) {
            list = getFieldNamesList(fieldNames);
        }
        // 获取所有的属性，属性一般为 private
        Field[] fields = o.getClass().getDeclaredFields();
        // 用于接收属性的值
        Object obj = null;
        try {
            for (Field field : fields) {
                // 判断是否排除某个成员变量
                if (fieldNames != null) {
                    // 非空判断
                    if (field != null && !list.contains(field.getName())) {
                        // 私有属性，设置开关
                        field.setAccessible(true);
                        // 判断值
                        obj = field.get(o);
                        // 判断值是否为空
                        if (obj != null) {
                            return false;
                        }
                    }
                } else {
                    // 非空判断
                    if (field != null) {
                        // 私有属性，设置开关
                        field.setAccessible(true);
                        // 判断值
                        obj = field.get(o);
                        // 判断值是否为空
                        if (obj != null) {
                            return false;
                        }
                    }
                }
            }
        } catch (Exception e) {
            // 返回 service 层错误
            throw new ServiceException(StatusCodeEnum.ERROR);
        }
        // 默认返回 true，属性为空
        return true;
    }

    /**
     * 将属姓名添加到集合
     *
     * @param fieldNames 属姓名
     * @return 方法名集合
     */
    private static List<String> getFieldNamesList(String[] fieldNames) {
        List<String> list;// 用来存储方法名
        list = new ArrayList<>();
        String start = null;
        // 循环取值，拼接方法名字符串
        for (String fieldName : fieldNames) {
            // 非空判断
            if (fieldName != null) {
                // 直接添加属性名
                list.add(fieldName);
            }
        }
        return list;
    }

    /**
     * 将拼接属姓的 get 方法名添加到集合
     *
     * @param fieldNames 属姓名
     * @return 方法名集合
     */
    private static List<String> getMethodNamesList(String[] fieldNames) {
        List<String> list;// 用来存储方法名
        list = new ArrayList<>();
        String start = null;
        StringBuilder methodName;
        // 循环取值，拼接方法名字符串
        for (String fieldName : fieldNames) {
            // 非空判断
            if (fieldName != null) {
                // 取首字母
                start = fieldName.substring(0, 1);
                // 转换为大写
                start = start.toUpperCase();
                // 拼接字符串
                methodName = new StringBuilder();
                methodName.append("get").append(start).append(fieldName.substring(1));
                // 添加到数组
                list.add(methodName.toString());
            }
        }
        return list;
    }
}
