package com.xhsoft.framework.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;
import java.util.zip.Checksum;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * <p>Title: CompassUtil.java</p> 
 * <p>Description: ZiP压缩文件工具类</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * @author lijw
 * @date 2013-6-26
 */
public class CompassUtil {
	
	public static final String CU_SUFFIX = ".zip";
	public static final String CU_SEPARATE = "/";
	public static final int CU_BUFFER_SIZE = 1*1024;
	
	/**
	 * <p>Description:压缩文件递归方法</p>
	 * @param zos
	 * @param file
	 * @param filePath
	 * @param isDelete 
	 * @author lijw
	 * @since 2013-5-13
	 */
	private static void compress(ZipOutputStream zos,File file,String rootPath,boolean isDelete){
		if(file.isDirectory()){
			try {
				zos.putNextEntry(new ZipEntry(rootPath+CU_SEPARATE));
				
				rootPath  = rootPath.length() == 0 ? "":rootPath+CU_SEPARATE;
				
				for(File f : file.listFiles()){
					compress(zos,f,rootPath+f.getName(),isDelete);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else{
			InputStream inStream = null;
			try {
				zos.putNextEntry(new ZipEntry(rootPath));
				inStream = new FileInputStream(file);
				byte[] buffer = new byte[CU_BUFFER_SIZE];
				@SuppressWarnings("unused")
				int b = 0;
				while((b = inStream.read(buffer))!=-1){
					zos.write(buffer);
				}
				inStream.close();
				zos.closeEntry();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally{
				inStream=null;
			}
		}
		if(isDelete){
			file.delete();
		}
	}
	
	/**
	 * <p>Description:解压缩文件</p>
	 * @param zipPath
	 * @param savePath
	 * @param isDelete 
	 * @author lijw
	 * @since 2013-5-14
	 */
	public static void unzip(String zipPath,String savePath,boolean isDelete){
		File file = new File(zipPath);
		//判断文件是否存在
		if(file.exists()==false){
			//文件不存在
			return;
		}
		try {
			ZipFile zFile = new ZipFile(file);
			InputStream inStream = new FileInputStream(file);
			Checksum cksum = new CRC32();
			CheckedInputStream cis = new CheckedInputStream(inStream, cksum );
			ZipInputStream zis = new ZipInputStream(cis);
			ZipEntry ze = null;
			while((ze = zis.getNextEntry())!=null){
				String fileName = ze.getName();
				File temp = new File(savePath+CU_SEPARATE+fileName);
				if(ze.isDirectory()){
					temp.mkdirs();
				}else
				{
					//定义输出流
					OutputStream outStream = new FileOutputStream(temp);
					InputStream is  = zFile.getInputStream(ze);
					byte[] buffer =  new byte[CU_BUFFER_SIZE];
					@SuppressWarnings("unused")
					int b = 0;
					while((b=is.read())!=-1){
						outStream.write(buffer);
					}
					outStream.close();
					is.close();
				}
			}
			zis.close();
			
			if(isDelete){
				file.delete();
			}
			
		} catch (ZipException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <p>Description:压缩删除源文件</p>
	 * @param srcPath
	 * @param zipPath 
	 * @author lijw
	 * @since 2013-5-13
	 */
	public static void zipDelSrc(String srcPath,String zipPath){
		OutputStream outStream = null;
		try {
			outStream = new FileOutputStream(zipPath);
			Checksum cksum  = new CRC32();//循环冗余校验
			CheckedOutputStream cos = new CheckedOutputStream(outStream, cksum);
			ZipOutputStream zos = new ZipOutputStream(cos);
			
			File file = new File(srcPath);
			if(file.exists()){
				compress(zos,file,file.getName(),true);
			}
			zos.close();
			outStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			outStream = null;
		}
	}
	
	/**
	 * <p>Description:压缩不删除源文件</p>
	 * @param srcPath
	 * @param zipPath 
	 * @author lijw
	 * @since 2013-5-13
	 */
	public static void zipNotDelSrc(String srcPath,String zipPath){
		OutputStream outStream = null;
		try {
			outStream = new FileOutputStream(zipPath);
			Checksum cksum  = new CRC32();//循环冗余校验
			CheckedOutputStream cos = new CheckedOutputStream(outStream, cksum);
			ZipOutputStream zos = new ZipOutputStream(cos);
			
			File file = new File(srcPath);
			if(file.exists()){
				compress(zos,file,file.getName(),false);
			}
			zos.close();
			outStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			outStream = null;
		}
	}
	
	//新建压缩文件包
	//将文件添加到压缩包内
	//将文件添加到压缩包内并删除源文件
	//删除压缩包内文件
	//浏览压缩包内文件
	//加密压缩包
	//加密压缩包内文件
	//解密压缩包
	//解密压缩包内文件

}
