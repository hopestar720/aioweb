/*
 * $RCSfile: ClassUtil,v $$
 * $Revision: 1.0  $
 * $Date: 2011  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>Title: ClassUtil</p> 
 * <p>Description: </p> 
 * @author lizj
 * @version 1.0
 */
public class ClassUtil
{
    private static final Log log = LogFactory.getLog(ClassUtil.class);
    private static final boolean DEBUG = log.isDebugEnabled();
    
    public static final Object[] Empty = new Object[]{};
    
    private ClassUtil(){}
    
    /**
     * @param clazz
     * @return
     */
    public static String getClassName(Class<?> clazz)
    {
        String className = clazz.getName();
        
        int k = className.lastIndexOf(".");
        
        if(k < 0){
            return className;
        }
        
        return className.substring(k + 1);
    }

    /**
     * @param dir
     * @param className
     * @return
     */
    public static File getClassFile(File dir, String className)
    {
        String filePath = StringUtil.replace(className, ".", File.separator) + ".class";
        
        return new File(dir, filePath);
    }

    /**
     * @param dir
     * @param className
     * @return
     */
    public static File getClassFile(String dir, String className)
    {
        String filePath = StringUtil.replace(className, ".", File.separator) + ".class";
        
        return new File(dir, filePath);
    }

    /**
     * @param fp
     * @param fc
     * @return - boolean
     * @author: lizj
     */
    public static boolean isInnerClassFile(File fp, File fc)
    {
        String n1 = fp.getName();
        String n2 = fc.getName();
        
        if(!n1.endsWith(".class") || !n2.endsWith(".class")){
            return false;
        }
        
        n1 = StringUtil.getLastB4(n1, ".");
        n2 = StringUtil.getLastB4(n2, ".");
        
        return (n2.startsWith(n1 + "$"));
    }
    
    /**
     * @param className
     * @return
     * @throws ClassNotFoundException - Class
     * @author: lizj
     */
    public static Class<?> getClass(String className) throws ClassNotFoundException
    {
        Class<?> clazz = null;
        
        try{
            clazz = Thread.currentThread().getContextClassLoader().loadClass(className);
        }catch(Exception e){}
        
        if(clazz == null){
            clazz = ClassUtil.class.getClassLoader().loadClass(className);
        }
        
        if(clazz == null){
            clazz = Class.forName(className);
        }
        
        return clazz;
    }
    
    /**
     * 
     * @param child
     * @param parent
     * @return
     * @throws ClassNotFoundException - Class
     * @author: lizj
     */
    public static Class<?> getClass(String className, String parent) throws ClassNotFoundException
    {
        Class<?> childClass  = ClassUtil.getClass(className);
        
        Class<?> parentClass = null;
        
        if(parent == null){
            parentClass = Object.class;
        } else{
            parentClass = ClassUtil.getClass(parent);
        }
        
        if(!parentClass.isAssignableFrom(childClass)){
            throw new ClassCastException(className + " class must be implement the " + parent + " interface.");
        }
        
        return childClass;
    }
    
    /**
     * @param className
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static Object getInstance(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        return getInstance(className, Object.class);
    }
    
    /**
     * 
     * @param className
     * @param parent
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException - Object
     * @author: lizj
     */
    public static Object getInstance(String className, Class<?> parent) throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        Class<?> clazz = ClassUtil.getClass(className);
        
        if(parent == null){
            parent = Object.class;
        }
        
        if(!parent.isAssignableFrom(clazz)){
            throw new ClassCastException(className + " class must be implement the " + parent.getName() + " interface.");
        }
        
        return clazz.newInstance();
    }
    
    /**
     * 
     * @param className
     * @param parent
     * @param parameters
     * @return
     * @throws ClassNotFoundException
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException - Object
     * @author: lizj
     */
    public static Object getInstance(String className, String parent, Object[] parameters) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        Class<?> clazz = ClassUtil.getClass(className, parent);
        
        Class<?>[] types = null;
        
        if(parameters == null){
            types = new Class[0];
        }else{
            types = new Class[parameters.length];
        }
        
        Constructor<?> c = clazz.getConstructor(types);
        
        return c.newInstance(parameters);
    }
    
    /**
     * 
     * @param className
     * @param parent
     * @param parameterTypes
     * @param parametersObjects
     * @return
     * @throws ClassNotFoundException
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException - Object
     * @author: lizj
     */
    public static Object getInstance(String className, String parent, Class<?>[] parameterTypes, Object[] parametersObjects) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        Class<?> clazz = ClassUtil.getClass(className, parent);
        
        Constructor<?> c = clazz.getConstructor(parameterTypes);
        
        return c.newInstance(parametersObjects);
    }
    
    /**
     * 
     * @param object
     * @param methodName
     * @return
     * @throws IllegalArgumentException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException - Object
     * @author: lizj
     */
    public static Object call(Object object, String methodNames) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        java.util.StringTokenizer st = new java.util.StringTokenizer(methodNames, ".");
        
        while(object != null && st.hasMoreTokens())
        {
            object = object.getClass().getMethod(st.nextToken(), new Class[0]).invoke(object, new Object[0]);
        }
        
        return object;
    }
    
    /**
     * @param object
     * @param methodName
     * @param parameters
     * @return
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static Object call(Object object, String methodName, Object[] parameters) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException
    {
        Class<?>[] parameterTypes = new Class[parameters.length];
        
        for(int i = 0; i < parameterTypes.length; i++)
        {
            parameterTypes[i] = parameters[i].getClass();
        }
        
        return call(object, methodName, parameterTypes, parameters);
    }

    /**
     * 
     * @param object
     * @param methodName
     * @param parameterTypes
     * @param parametersObjects
     * @return - Object
     * @author lizj
     * @throws NoSuchMethodException 
     * @throws SecurityException 
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @throws IllegalArgumentException 
     */
    public static Object call(Object object, String methodName, Class<?>[] parameterTypes, Object[] parametersObjects) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException
    {
        return object.getClass().getMethod(methodName, parameterTypes).invoke(object, parametersObjects);
    }
    
    /**
     * @param clazz
     * @return String
     * @author lizj
     */
    public static String getClassPathRoot(Class<?> clazz)
    {
        String classPath = StringUtil.replace(clazz.getName(), ".", "/") + ".class";
        
        String classFile = getClassName(clazz) + ".class";
        
        String filePath  = clazz.getResource(classFile).toString();
        
        if(filePath.endsWith(classPath)){
            filePath = filePath.substring(0, filePath.length() - classPath.length());
        }
        
        if(filePath.startsWith("jar:")){
            filePath = filePath.substring("jar:file:/".length());
            
            if(filePath.endsWith("!/")){
                filePath = filePath.substring(0, filePath.lastIndexOf("/", filePath.length() - 2) + 1);
            }
        } else if(filePath.startsWith("file:")){
            filePath = filePath.substring("file:/".length());
        }
        
        return filePath;
    }
    
    /**
     * @return String
     * @author lizj
     */
    public static String getSuperRoot()
    {
        return ClassUtil.getSuperRoot(ClassUtil.class);
    }
    
    /**
     * 
     * @param clazz
     * @return - java.lang.String
     */
    public static String getSuperRoot(Class<?> clazz)
    {
        String file = ClassUtil.getClassPathRoot(clazz);
        
        file = new java.io.File(file).getParentFile().getAbsolutePath();
        
        file = StringUtil.replace(file, "\\", java.io.File.separator);
        
        if(!file.endsWith(java.io.File.separator)){
            file = file + java.io.File.separator;
        }
        
        return file;
    }
    
    /**
     * @param object
     * @author lizj
     */
    public static void println(Object object)
    {
        println(object, true);
    }
    
    /**
     * @param object
     * @param b
     * @author lizj
     */
    public static void println(Object object, boolean b)
    {
        if(object == null){
            System.out.println("[BEAN] NULL");
            
            return;
        }
        
        System.out.println("[BEAN] " + object.getClass().getName());
        
        java.lang.reflect.Method[] ms = null;
        
        if(b){
            ms = object.getClass().getMethods();
        }else{
            ms = object.getClass().getDeclaredMethods();
        }
        
        for(int i = 0; i < ms.length; i++)
        {
            if(ms[i].getName().startsWith("get")){
                if(ms[i].getParameterTypes().length < 1){
                    try{
                        System.out.println("" + ms[i].getName() + "():" + ms[i].invoke(object, Empty));
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    
    /**
     * @param object
     * @return
     * @author lizj
     */
    public static String toString(Object object)
    {
        return toString(object, true);
    }
    
    /**
     * @param object
     * @param b
     * @return
     * @author lizj
     */
    public static String toString(Object object, boolean b)
    {
        String line = "\r\n";
        StringBuilder buf = new StringBuilder();
        
        if(object == null){
            buf.append("[BEAN]: NULL");
            
            return buf.toString();
        }
        
        buf.append("[BEAN]: " + object.getClass().getName()).append(line);
        
        java.lang.reflect.Method[] ms = null;
        
        if(b){
            ms = object.getClass().getMethods();
        }else{
            ms = object.getClass().getDeclaredMethods();
        }
        
        String name = null;
        
        for(int i = 0; i < ms.length; i++)
        {
            if(ms[i].getName().startsWith("get")){
                if(ms[i].getParameterTypes().length < 1){
                    name = ms[i].getName().substring(3);
                    
                    try{
                        buf.append(name + ": " + ms[i].invoke(object, Empty)).append(line);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
        
        return buf.toString();
    }
    
    /**
     * @param clazz
     * @return
     * @author lizj
     */
    public static String getClassRootPath(Class<?> clazz)
    {
        String line = java.io.File.separator;
        
        String name = clazz.getName();
                
        String path = "";
        
        int s = 0;
        
        do
        {            
            s = name.indexOf(".", s);
            
            if(s != -1){
                path += ".." + line;
                s++;
            }else{
                break;
            }
        }while(true);
        
        return path;
    }
    
    /**
     * @param className
     * @return String
     */
    public static String getClassName(String className)
    {
        if(className == null){
            return className;
        }
        
        int k = className.lastIndexOf(".");
        
        if(k > -1){
            return className.substring(k + 1);
        }
        
        return className;
    }
    
    /**
     * @return
     * @author lizj
     */
    public static String getCallerName()
    {
        return getCallerName(0);
    }
    
    /**
     * 
     * @return - String
     * @author: lizj
     */
    public static String getCallerName(int depth)
    {
        String JDK_VERSION = System.getProperty("java.version");
        
        double version = 1.3D;
        
        try{
            JDK_VERSION = JDK_VERSION.substring(0, 3);
            
            version = Double.parseDouble(JDK_VERSION);
        }catch(NumberFormatException e){
            e.printStackTrace();
        }
        
        if(DEBUG){
            log.debug("java.version: " + version);
        }
        
        if(version >= 1.5D){
            try{
                Thread currentThread = Thread.currentThread();
                
                StackTraceElement[] stack = (StackTraceElement[])(Thread.class.getMethod("getStackTrace", new Class[0]).invoke(currentThread, new Object[0]));
                
                if(stack != null && stack.length >= 3){
                    for(int i = 0; i < stack.length; i++)
                    {
                        if(stack[i].getClassName().equals(ClassUtil.class.getName()) && "getCallerName".equals(stack[i].getMethodName())){
                            if(i + 2 + depth < stack.length){
                                return stack[i + 2 + depth].getClassName() + "." + stack[i + 2 + depth].getMethodName(); 
                            }
                        }
                    }
                    
                    return null;
                }
            }catch(IllegalArgumentException e1){
                e1.printStackTrace();
            } catch(SecurityException e1){
                e1.printStackTrace();
            }catch(IllegalAccessException e1){
                e1.printStackTrace();
            }catch(InvocationTargetException e1){
                e1.printStackTrace();
            }catch(NoSuchMethodException e1){
                e1.printStackTrace();
            }
        }else if(version >= 1.4D){
            StackTraceElement[] stack = (new Throwable()).getStackTrace();
            
            if(stack != null && stack.length >= (3 + depth)){
                return stack[2 + depth].getClassName() + "." + stack[2 + depth].getMethodName();
            }
        }
        
        java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream(4096);
        
        new Throwable().printStackTrace(new java.io.PrintStream(bos));
        
        String x = bos.toString();
        
        java.io.BufferedReader br = new java.io.BufferedReader(new java.io.StringReader(x));
        
        String val = null;
        String str = null;
        
        try{
            int i = 0;
            
            while((str = br.readLine()) != null)
            {
                if(i++ == (3 + depth)){
                    str = StringUtil.getA5(str, "at ");
                    
                    val = str = StringUtil.getB4(str, "(");
                }
            }
        }catch(IOException e){
        }
        
        return val;
    }
    
    /**
     * @return
     * @author lizj
     */
    public static String getCallerClass()
    {
        return getCallerClass(0);
    }
    
    /**
     * @return - String
     * @author: lizj
     */
    public static String getCallerClass(int depth)
    {
        String caller = getCallerName(depth);
        
        if(caller != null){
            int k = caller.lastIndexOf(".");
            
            if(k > -1){
                return caller.substring(0, k);
            }
        }
        
        return null;
    }
    
    /**
     * @return
     * @author lizj
     */
    public static String getCallerMethod()
    {
        return getCallerMethod(0);
    }
    
    /**
     * @return - String
     * @author: lizj
     */
    public static String getCallerMethod(int depth)
    {
        String caller = getCallerName(depth);
        
        if(caller != null){
            int k = caller.lastIndexOf(".");
            
            if(k > -1){
                return caller.substring(k + 1);
            }
        }
        
        return null;
    }
    
    /**
     * @param type
     * @param s
     * @return Object
     * @author lizj
     */
    public static Object cast(Class<?> type, String s)
    {
        if(s == null || type == null){
            return null;
        }
        
        Object value = null;
        
        if(type == char.class || type == Character.class){
            value = (s.length() > 0 ? Character.valueOf(s.charAt(0)) : null);
        }else if(type == boolean.class || type == Boolean.class){
            String x = s.toLowerCase();
            
            boolean b = ("1".equals(x) || "y".equals(x) || "on".equals(x) || "yes".equals(x) || "true".equals(x));
            
            value = new Boolean(b);    
        }else if(type == byte.class || type == Byte.class){
            try{
                value = Byte.parseByte(s);
            }catch(NumberFormatException e){
            }
        }else if(type == short.class || type == Short.class){
            try{
                value = Short.parseShort(s);
            }catch(NumberFormatException e){
            }
        }else if(type == int.class || type == Integer.class){
            try {
                value = Integer.parseInt(s);
            }catch(NumberFormatException e){
            }
        }else if(type == float.class || type == Float.class){
            try {
                value = Float.parseFloat(s);
            }catch(NumberFormatException e){
            }
        }
        else if(type == double.class || type == Double.class){
            try{
                value = Double.parseDouble(s);
            }catch(NumberFormatException e){
            }
        } else if(type == long.class || type == Long.class){
            try{
                value = Long.parseLong(s);
            }catch(NumberFormatException e){
            }
        }
        else if(type == String.class){
            value = s;
        }
        else if(type == StringBuilder.class){
            value = new StringBuilder(s);
        }
        else if(type == StringBuffer.class){
            value = new StringBuffer(s);
        }
        else if(type == java.io.Reader.class){
            value = new java.io.StringReader(s);
        }
        else if(type == java.util.Date.class){
            if(s.length() > 0){
                try{
                    String format = (s.length() == "yyyy-MM-dd".length() ? "yyyy-MM-dd" : "yyyy-MM-dd HH:mm:ss SSS");
                    
                    SimpleDateFormat dateFormat = new SimpleDateFormat(format);
                    
                    value = dateFormat.parse(s);
                }catch(java.text.ParseException e){
                    if(DEBUG){
                        log.debug("Exception: " + e.getMessage());
                    }
                }
            }
        }
        else if(type == java.sql.Date.class){
            if(s.length() > 0){
                try{
                    String format = (s.length() == "yyyy-MM-dd".length() ? "yyyy-MM-dd" : "yyyy-MM-dd HH:mm:ss SSS");
                    
                    SimpleDateFormat dateFormat = new SimpleDateFormat(format);
                    
                    java.util.Date date = dateFormat.parse(s);
                    
                    value = new java.sql.Date(date.getTime());
                } catch(java.text.ParseException e){
                    if(DEBUG){
                        log.debug("Exception: " + e.getMessage());
                    }
                }
            }
        }
        else if(type == java.sql.Timestamp.class){
            if(s.length() > 0) {
                try{
                    String format = (s.length() == "yyyy-MM-dd".length() ? "yyyy-MM-dd" : "yyyy-MM-dd HH:mm:ss SSS");
                    
                    SimpleDateFormat dateFormat = new SimpleDateFormat(format);
                    
                    java.util.Date date = dateFormat.parse(s);
                    
                    value = new java.sql.Timestamp(date.getTime());
                }catch(java.text.ParseException e){
                    if(DEBUG){
                        log.debug("Exception: " + e.getMessage());
                    }
                }
            }
        }
        
        return value;
    }
    
    /**
     * @param writer
     * @param className
     * @param expression
     * @author lizj
     */
    public static void write(final java.io.Writer writer, final String className, final String expression)
    {
        try{
            Class<?> type = ClassUtil.getClass(className);
            
            write(writer, type, expression);
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    
    /**
     * @param writer
     * @param type
     * @param expression
     * @author lizj
     */
    public static void write(final java.io.Writer writer, final Class<?> type, final String expression)
    {
        String name = null;
        String text = null;
        
        Method[] methods = type.getMethods();
        
        try{
            for(int i = 0; i < methods.length; i++)
            {
                name = methods[i].getName();
                
                if(name.startsWith("get") && methods[i].getParameterTypes().length < 1){
                    name = name.substring(3);
                    name = java.beans.Introspector.decapitalize(name);
                    
                    text = expression;
                    text = StringUtil.replace(text, "${name}", name);
                    text = StringUtil.replace(text, "${endl}", "\r\n");
                    
                    writer.write(text);
                }
            }
            
            writer.flush();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    /**
     * @param a
     * @param b
     * @return
     */
    public static boolean equals(Object a, Object b)
    {
        return (a == b) || (a != null && a.equals(b));
    }
    
    public static void main(String[] args)
    {
        System.out.println(getClassPathRoot(ClassUtil.class));
        
        System.out.println(getCallerClass(1) + "." + getCallerMethod(1));
    }
}
