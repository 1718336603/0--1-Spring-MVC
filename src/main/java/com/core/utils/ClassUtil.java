package com.core.utils;

/**
 * @DESC 主要用于getClass()相关操作
 * @AUTHOR zzf
 * @DATA 2021/5/30 18:14
 */

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.JarURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.stream.Collectors;

/**
 * 类操作工具类
 */
//@Slf4j
public final class ClassUtil {

    /**
     * file形式url协议
     */
    public static final String FILE_PROTOCOL = "file";

    /**
     * jar形式url协议
     */
    public static final String JAR_PROTOCOL = "jar";

    /**
     * 获取classLoader
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }
    /**
     * 指定当前的类的类加载器去加载className类并获取Class
     */
    public static Class<?> loadClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            System.out.println("load class error");
//            log.error("load class error", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 实例化class
     */
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(String className) {
        try {
            Class<?> clazz = loadClass(className);
            return (T) clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            System.out.println("newInstance error");
//            log.error("newInstance error", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 实例化class
     */
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(Class<?> clazz) {
        try {
            return (T) clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            System.out.println("newInstance error");
//            log.error("newInstance error", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 设置类的属性值
     */
    public static void setField(Field field, Object target, Object value) {
        setField(field, target, value, true);
    }

    /**
     * field 需要重设的属性名
     * setAccessible 设置该属性能否通过反射获取
     * target 需要设置值的实例对象
     * 设置类的属性值
     */
    public static void setField(Field field, Object target, Object value, boolean accessible) {
        field.setAccessible(accessible);
        try {
            field.set(target, value);
        } catch (IllegalAccessException e) {
            System.out.println("setField error");
//            log.error("setField error", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取包下类集合
     */
    public static Set<Class<?>> getPackageClass(String basePackage) {
        URL url = getClassLoader()
                .getResource(basePackage.replace(".", "/"));
        //获取资源的绝对路径
        if (null == url) {
            throw new RuntimeException("无法获取项目路径文件");
        }
        try {
            if (url.getProtocol().equalsIgnoreCase(FILE_PROTOCOL)) {
                // 若为普通文件夹，则遍历
                File file = new File(url.getFile());
                Path basePath = file.toPath();
                return Files.walk(basePath)
                        .filter(path -> path.toFile().getName().endsWith(".class"))
                        .map(path -> getClassByPath(path, basePath, basePackage))
                        .collect(Collectors.toSet());
            } else if (url.getProtocol().equalsIgnoreCase(JAR_PROTOCOL)) {
                // 若在 jar 包中，则解析 jar 包中的 entry
                JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                return jarURLConnection.getJarFile()
                        .stream()
                        .filter(jarEntry -> jarEntry.getName().endsWith(".class"))
//                        .map((ClassUtil)->{return getClassByJar})
                        .map(ClassUtil::getClassByJar)
                        .collect(Collectors.toSet());
            }
            return Collections.emptySet();
        } catch (IOException e) {
            System.out.println("load package error");
//            log.error("load package error", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 从Path获取Class
     */
    private static Class<?> getClassByPath(Path classPath, Path basePath, String basePackage) {
        String packageName = classPath.toString().replace(basePath.toString(), "");
        String className = (basePackage + packageName)
                .replace("/", ".")
                .replace("\\", ".")
                        .replace(".class", "");
        return loadClass(className);
    }

    /**
     * 从jar包获取Class
     */
    private static Class<?> getClassByJar(JarEntry jarEntry) {
        String jarEntryName = jarEntry.getName();
        // 获取类名
        String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
        return loadClass(className);
    }
}