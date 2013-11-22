/*
 * $RCSfile: UploadFile,v $$
 * $Revision: 1.0  $
 * $Date: 2011  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * <p>Title: UploadFile.java</p> 
 * <p>Description: 文件上传工具类</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * @author lijw
 * @date 2013-6-17
 */
public class UploadUtil {
	//缓存流大小
	private static final int BUFFER_SIZE = 16 * 1024;

	/**
	 * <p>Description:生成随机文件名，上传单个文件</p>
	 * @param file	上传的文件
	 * @param filePath 文件上传的路径
	 * @param request	HTTP请求
	 * @return	返回文件上传路径
	 * @throws Exception 
	 * @author lijw
	 * @since 2013-6-17
	 */
	public static String uploadFile(File file, String filePath,HttpServletRequest request) throws Exception {
		Random random = new Random();
		/* 生成随机文件 */
		String path = createRandomFileName(file, filePath, random);
		/* 上传 */
		processFileStream(file, path);
		return "/" + dateToString(new Date(), "yyyyMMdd")
				+ path.substring(path.lastIndexOf("/"), path.length());
	}

	/**
	 * <p>Description:生成随机文件名，上传多个文件</p>
	 * @param file	上传文件数组
	 * @param filePath	上传文件路径
	 * @param request	HTTP请求
	 * @param dirPrefix	 上传文件路径的前缀
	 * @return 返回上传文件路径LIST集合
	 * @throws Exception 
	 * @author lijw
	 * @since 2013-6-17
	 */
	@SuppressWarnings("unchecked")
	public static List uploadFile(File[] file, String filePath,HttpServletRequest request, String dirPrefix) throws Exception {
		List<String> pathCollet = new ArrayList<String>();

		if (file != null) {
			Random random = new Random();

			for (int i = 0; i < file.length; i++) {
				String dir = "";

				if (file[i] != null && file[i].length() > 0) {
					/* 生成随机文件 */
					String path = createRandomFileName(file[i], filePath,random);
					/* 上传 */
					processFileStream(file[i], path);
					dir = dirPrefix+ "/"+ dateToString(new Date(), "yyyyMMdd")+ path.substring(path.lastIndexOf("/"),path.length());
					pathCollet.add(dir);
				}

			}

		}

		return pathCollet;
	}

	/**
	 * <p>Description:上传单个文件</p>
	 * @param file
	 * @param filePath
	 * @throws Exception 
	 * @author lijw
	 * @since 2013-6-17
	 */
	private static void processFileStream(File file, String filePath)throws Exception {
		/* 上传 */
		InputStream inStream = new FileInputStream(file);
		OutputStream outStream = new FileOutputStream(filePath);
		int bytexRead = 0;

		/* 定义缓冲区大 */
		byte buffer[] = new byte[BUFFER_SIZE];
		while ((bytexRead = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytexRead);
		}
		/* 上传结束,关闭流缓 */
		outStream.close();
		inStream.close();
	}

	/**
	 * <p>Description:创建随机文件名，并生成日期目录</p>
	 * @param file 传入文件
	 * @param filePath 文件根路径
	 * @param random 随机数对象
	 * @return  返回生成文件的全路径
	 * @author lijw
	 * @since 2013-6-17
	 */
	public static String createRandomFileName(File file, String filePath,Random random) {
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sb.append(rand);
		}

		/* 取当前时间字串 */
		String filename = dateToString(new Date(),
				"yyyyMMddHHmmss");
		/* 混和上之前的随机数作为主文件(不包含后) */
		sb.insert(0, filename);
		String dirname = dateToString(new Date(), "yyyyMMdd");
		File data_format_dir = new File(filePath + "/" + dirname);
		if (!data_format_dir.exists())
			data_format_dir.mkdirs();
		/* 原文件名 */
		String fileName = file.getName();
		/* 文件后缀(文件类型) */
		String end = file.getName().substring(fileName.lastIndexOf("."),fileName.length());
		/* 新文件名全称(包括主文件名和后) */
		String fullName = sb.toString() + end;
		sb = null;
		return data_format_dir.toString() + "/" + fullName;
	}

	/**
	 * <p>Description:上传文件（不改变原文件名）</p>
	 * @param file	传入的文件
	 * @param filepath 文件根路径
	 * @return 返回原文件名
	 * @throws Exception 
	 * @author lijw
	 * @since 2013-6-17
	 */
	public static String uploadFile(File file, String filepath)throws Exception {
		String filename = file.getName();
		InputStream inStream = new FileInputStream(file);
		OutputStream outStream = new FileOutputStream(filepath + "/" + filename);
		int bytexRead = 0;
		byte[] buffer = new byte[BUFFER_SIZE];

		while ((bytexRead = inStream.read(buffer, 0, BUFFER_SIZE)) != -1) {
			outStream.write(buffer, 0, bytexRead);
		}
		outStream.close();
		inStream.close();

		return filename;
	}

	/**
	 * <p>Description:创建图片缩略图</p>
	 * @param imgPath	文件名
	 * @param expectWidth	缩略图宽度（像素）
	 * @param expectHeight	缩略图高度（像素）
	 * @return
	 * @throws Exception 
	 * @author lijw
	 * @since 2013-6-17
	 */
	public static String createThumbnail(String imgPath, int expectWidth,int expectHeight) throws Exception {
		Random random = new Random();
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sb.append(rand);
		}

		String dir = imgPath.substring(0, imgPath.lastIndexOf("/"));
		String end = imgPath.substring(imgPath.lastIndexOf("."),imgPath.length());
		String newFile = dateToString(new Date(), "yyyyMMddHHmmss")+sb.toString()+ end;
		String newurl = dir + "/" + newFile;
		File orgFile = new File(imgPath);
		Image src = ImageIO.read(orgFile);
		/* 得到源图宽 */
		int old_w = src.getWidth(null);
		int old_h = src.getHeight(null);
		
		int newHeight = old_h;
		int newWidth = old_w;
		// 计算新图长宽
		if (old_w > expectWidth) {
			newHeight = old_h * expectWidth / old_w;
			newWidth = expectWidth;
		}

		if (newHeight > expectHeight) {
			newWidth = newWidth * expectHeight / newHeight;
			newHeight = expectHeight;
		}

		if (newWidth == 0) {
			newWidth = old_w;
		}

		if (newHeight == 0) {
			newHeight = old_h;
		}

		BufferedImage tag = new BufferedImage(newWidth, newHeight,Image.SCALE_SMOOTH);
		/* 绘制缩小后的图 */
		tag.getGraphics().drawImage(src, 0, 0, newWidth, newHeight, null);
		/* 输出到文件流 */
		FileOutputStream newimage = new FileOutputStream(newurl);
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);
		/* 设置JPEG编码 */
		encoder.encode(tag);
		newimage.close();
		return "/" + dateToString(new Date(), "yyyyMMdd") + "/" + newFile;
	}

	
	

	/**
	 * <p>
	 * Description:上传到指定路径下(该路径必须是确定存在的)
	 * </p>
	 * 
	 * @param src
	 * @param dst
	 * @return void
	 */
	public static void copy(File src, File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;

			try {
				in = new BufferedInputStream(new FileInputStream(src),
						BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst),
						BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				while (in.read(buffer) != -1) {
					out.write(buffer);
				}

			} finally {
				if (null != in) {
					in.close();
				}

				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * Description:当上传的指定目录不存在时,创建目录,并上传文件
	 * </p>
	 * 
	 * @param src
	 * @param dir
	 * @param fileName
	 * @return void
	 * @exception Exception
	 */
	public static void createCopy(File src, File dir, String fileName)
			throws Exception {
		try {
			InputStream in = null;
			OutputStream out = null;

			try {
				in = new BufferedInputStream(new FileInputStream(src),
						BUFFER_SIZE);

				if (!dir.exists()) {
					dir.mkdirs();
				}

				out = new BufferedOutputStream(new FileOutputStream(new File(
						dir, fileName)), BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				while (in.read(buffer) != -1) {
					out.write(buffer);
				}
			} finally {
				if (null != in) {
					in.close();
				}

				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * <p>
	 * Description:uploadToPath
	 * </p>
	 * 
	 * @param uploadFile
	 * @param uploadFileName
	 * @param path
	 * @return String
	 */
	public static String uploadToPath(File uploadFile, String uploadFileName,
			String path) {
		String returnPath = "";
		uploadFileName = dateToString(new Date(),
				"yyyyMMddHHmmss")
				+ FileUtil.getExtention(uploadFileName);
		String realpath = ServletActionContext.getServletContext().getRealPath(
				path);
		returnPath = path + "/" + uploadFileName;

		try {
			createCopy(uploadFile, new File(realpath), uploadFileName);
		} catch (Exception de) {
			de.printStackTrace();
			return null;
		}

		return returnPath;
	}

	/**
	 * <p>
	 * Description:uploadToPathMulti
	 * </p>
	 * 
	 * @param uploadFile
	 * @param applyfj
	 * @param uploadFileName
	 * @param path
	 * @return String
	 */
	public static String uploadToPathMulti(File uploadFile, String applyfj,
			String uploadFileName, String path) {
		String returnPath = "";
		uploadFileName = applyfj
				+ dateToString(new Date(),
						"yyyyMMddHHmmss")
				+ FileUtil.getExtention(uploadFileName);
		String realpath = ServletActionContext.getServletContext().getRealPath(
				path);
		returnPath = path + "/" + uploadFileName;

		try {
			createCopy(uploadFile, new File(realpath), uploadFileName);
		} catch (Exception de) {
			de.printStackTrace();
			return null;
		}

		return returnPath;
	}

	/**
	 * <p>
	 * Description:uploadPath
	 * </p>
	 * 
	 * @param uploadFile
	 * @param uploadFileName
	 * @param propertitesName
	 * @return String
	 */
	public static String uploadPath(File uploadFile, String uploadFileName,
			String propertitesName) {
		String returnPath = "";
		uploadFileName = dateToString(new Date(),
				"yyyyMMddHHmmss")
				+ FileUtil.getExtention(uploadFileName);
		ResourceBundle bundle = ResourceBundle.getBundle("upload");
		String uploadVideoPath = bundle.getString(propertitesName);
		String path = ServletActionContext.getServletContext().getRealPath(
				uploadVideoPath)
				+ "/"
				+ dateToString(new Date(), "yyyyMMdd");
		returnPath = uploadVideoPath + "/"
				+ dateToString(new Date(), "yyyyMMdd")
				+ "/" + uploadFileName;

		try {
			// String waterMark = bundle.getString("hydrological.markContent");
			// WaterMark.createMark(returnPath, waterMark,Color.BLACK,70f);
			createCopy(uploadFile, new File(path), uploadFileName);
		} catch (Exception de) {
			de.printStackTrace();
			return null;
		}

		return returnPath;
	}
	
	private static String dateToString(Date date, String format) 
	{
		try {
			SimpleDateFormat myFormatter = new SimpleDateFormat(format);
			return myFormatter.format(date);
		}catch (Exception err) {
			return "";
		}
	}
}
