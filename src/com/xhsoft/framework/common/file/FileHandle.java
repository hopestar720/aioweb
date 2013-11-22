/**
 * Copyright (c) 2012, tuoming.com and/or its affiliates. All rights reserved.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO
 * 
 * @author lijiangwei
 *@since 2012-11-9
 */
public class FileHandle {
	public static final char SEPARATOR = File.separatorChar;

	public static void copy(String source, String target) {

		File f_src = new File(source);
		File f_tar = new File(target);
		if (f_src.isDirectory()) {
			if(!f_tar.exists()){
				f_tar.mkdirs();
			}
			String[] files = f_src.list();
			for (int i = 0; i < files.length; i++) {
				String new_src = source + SEPARATOR + files[i].toString();
				String new_tar = target + SEPARATOR + files[i].toString();
				copy(new_src, new_tar);
			}
		}else{
			copyFile(source,target);
		}
	}

	/**
	 * 功能描述：删除文或目录
	 * @params {:,:}
	 * @return void
	 * @author lijiangwei
	 * @since 2012-11-12
	 */
	public static void delete(String source) {
		File f_src = new File(source);
		if (f_src.isDirectory()) {
			String[] files = f_src.list();
			for (String file : files) {
				delete(source + SEPARATOR + file);
			}
		}
		f_src.delete();
	}

	private static void copyFile(String srcFileName, String tarFileName) {
		File f_src = new File(srcFileName);
		File f_tar = new File(tarFileName);
		InputStream inStream = null;
		OutputStream outStream = null;
		try {
			inStream = new FileInputStream(f_src);
			outStream = new FileOutputStream(f_tar);
			byte[] buffer = new byte[1024];
			int c;
			while ((c = inStream.read(buffer)) != -1) {
				for (int i = 0; i < c; i++) {
					outStream.write(buffer[i]);
				}
			}
			inStream.close();
			outStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			inStream = null;
			outStream = null;
		}
	}
	
	private static void copy(){}
	
	public static String transPath(String path){
		Pattern pat = Pattern.compile("/");
		Matcher mat = pat.matcher("\\");
		mat.replaceAll(path);
		return path;
	}
}
