package com.sfebiz.common.dao.helper;

import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: <a href="mailto:lenolix@163.com">李星</a>
 * Version: 1.0.0
 * Since: 14/11/5 下午12:03
 */
public class DaoHelper {

    private static Velocity ve;

    private static final String LOGISTICAL_DELETE_MODEL = "logistical";

    private static final String PHYSICAL_DELETE_MODEL = "physical";

    /**
     * 获取所有getXXX的方法
     * * @param path
     *
     * @param clasz
     */


    public static List<String> getGetters(Class clasz) {

        if (clasz == null) {
            return null;
        }
        Method[] ms = clasz.getMethods();
        List<String> list = new ArrayList();
        if (ms != null) {
            for (Method m : ms) {
                String methodName = m.getName();
                if (methodName.length() > 4 && methodName.startsWith("get") && !methodName.equals("getClass")) {
                    if (methodName.equalsIgnoreCase("getIsDelete")) {
                        continue;
                    }
                    String s = "" + m.getName().charAt(3);
                    if (s.equals(s.toUpperCase())) {
                        list.add(s.toLowerCase() + methodName.substring(4));
                    }
                }
            }
            return list;

        }
        return null;
    }

    public static void initVe() {
        // 初始化并取得Velocity引擎
        ve = new Velocity();
        ve.setProperty(Velocity.RESOURCE_LOADER, "classpath");
        ve.setProperty(Velocity.INPUT_ENCODING, "GBK");

        Velocity.setProperty(
                "classpath." + Velocity.RESOURCE_LOADER + ".class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        ve.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
        ve.setProperty(VelocityEngine.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.NullLogChute");
        try {
            ve.init();
        } catch (Exception e) {
            System.exit(-1);
        }
    }

    static {
        initVe();
    }


    public final static String getColumnName(String s) {
        if (StringUtils.isBlank(s)) {
            return "";
        }
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 || !String.valueOf(s.charAt(i)).toUpperCase().equals(String.valueOf(s.charAt(i)))) {
                sb.append(s.charAt(i));
            } else {
                sb.append("_").append(String.valueOf(s.charAt(i)).toLowerCase());
            }
        }
        return sb.toString();
    }

    /**
     * 生成sqlmap文件, 默认是逻辑删除
     *
     * @param path            sqlmap文件的保存路径
     * @param mapperInterface dao接口类
     * @param mapperDO        dto对象类
     * @param tableName       dto对应的数据库表名
     */
    public static void genXML(String path, Class mapperInterface, Class mapperDO, String tableName) {
        genXML(path, mapperInterface, mapperDO, tableName, true);
    }

    /**
     * 生成sqlmap文件
     *
     * @param path            sqlmap文件的保存路径
     * @param mapperInterface dao接口类
     * @param mapperDO        dto对象类
     * @param tableName       dto对应的数据库表名
     * @paeam deleteModel     删除模式: LOGISTICAL_DELETE_MODEL or PHYSICAL_DELETE_MODEL
     */
    public static void genXML(String path, Class mapperInterface, Class mapperDO, String tableName, Boolean isDeleteLogically) {
        if (StringUtils.isBlank(path) || mapperInterface == null || mapperDO == null) {
            return;
        }

        String classSimpleNameWithLowerCase = new String(mapperDO.getSimpleName().charAt(0) + "").toLowerCase() + mapperDO.getSimpleName().substring(1);
        String classSimpleName = mapperDO.getSimpleName();
        List<String> getters = getGetters(mapperDO);

        Context context = new VelocityContext();
        context.put("class", mapperInterface.getName());
        context.put("csnlc", classSimpleNameWithLowerCase);
        context.put("cn", mapperDO.getName());
        context.put("getters", getters);
        context.put("tn", StringUtils.isBlank(tableName) ? getColumnName(classSimpleName) : tableName);
        context.put("deleteModel", isDeleteLogically ? LOGISTICAL_DELETE_MODEL : PHYSICAL_DELETE_MODEL);
        Map m_getters = new HashMap<String, String>();
        for (String s : getters) {
            m_getters.put(s, getColumnName(s));
        }
        context.put("m_getters", m_getters);


        FileWriter fw = null;
        try {
            File file = new File(path);
            fw = new FileWriter(file);
            Template t = ve.getTemplate("sql.vm");
            t.merge(context, fw);
            fw.flush();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    /**
     * 生成sqlmap文件，并支持扩展属性字段：features, 默认是逻辑删除
     *
     * @param path            sqlmap文件的保存路径
     * @param mapperInterface dao接口类
     * @param mapperDO        dto对象类
     * @param tableName       dto对应的数据库表名
     */
    public static void genXMLWithFeature(String path, Class mapperInterface, Class mapperDO, String tableName) {
        genXMLWithFeature(path, mapperInterface, mapperDO, tableName, true);
    }


    /**
     * 生成sqlmap文件，并支持扩展属性字段：features
     *
     * @param path              sqlmap文件的保存路径
     * @param mapperInterface   dao接口类
     * @param mapperDO          dto对象类
     * @param tableName         dto对应的数据库表名
     * @param isDeleteLogically 是否是逻辑删除
     */
    public static void genXMLWithFeature(String path, Class mapperInterface, Class mapperDO, String tableName, Boolean isDeleteLogically) {
        if (StringUtils.isBlank(path) || mapperInterface == null || mapperDO == null) {
            return;
        }

        String classSimpleNameWithLowerCase = new String(mapperDO.getSimpleName().charAt(0) + "").toLowerCase() + mapperDO.getSimpleName().substring(1);
        String classSimpleName = mapperDO.getSimpleName();
        List<String> getters = getGettersWithFeature(mapperDO);


        Context context = new VelocityContext();
        context.put("class", mapperInterface.getName());
        context.put("csnlc", classSimpleNameWithLowerCase);
        context.put("csn", classSimpleName);
        context.put("cn", mapperDO.getName());
        context.put("getters", getters);
        context.put("tn", StringUtils.isBlank(tableName) ? getColumnName(classSimpleName) : tableName);
        context.put("deleteModel", isDeleteLogically ? LOGISTICAL_DELETE_MODEL : PHYSICAL_DELETE_MODEL);
        Map m_getters = new HashMap<String, String>();
        for (String s : getters) {
            m_getters.put(s, getColumnName(s));
        }
        context.put("m_getters", m_getters);


        FileWriter fw = null;
        try {
            File file = new File(path);
            fw = new FileWriter(file);
            Template t = ve.getTemplate("sql.vm");
            t.merge(context, fw);
            fw.flush();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private static List<String> getGettersWithFeature(Class clasz) {
        if (clasz == null) {
            return null;
        }
        Method[] ms = clasz.getMethods();
        List<String> list = new ArrayList();
        if (ms != null) {
            for (Method m : ms) {
                String methodName = m.getName();
                if (methodName.length() > 4 && methodName.startsWith("get") && !methodName.equals("getClass")) {
                    if (methodName.equalsIgnoreCase("getFeatureMap") || methodName.equalsIgnoreCase("getFeaturesAsMap")
                            || methodName.equalsIgnoreCase("getFeature") || methodName.equalsIgnoreCase("getIsDelete")) {
                        continue;
                    }
                    String s = "" + m.getName().charAt(3);
                    if (s.equals(s.toUpperCase())) {
                        list.add(s.toLowerCase() + methodName.substring(4));
                    }
                }

            }
            return list;

        }
        return null;
    }
}