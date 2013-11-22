/*
 * $RCSfile: FileUtil,v $$
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.struts2.ServletActionContext;


/**
 * <p>Title:FileUtil</p>
 * <p>Description:FileUtil</p>
 * <p>Copyright:Copyright (C) 2011</p>
 * @since 2011
 */
public class FileUtil 
{
	/**
	 * <p>Description:得到某个目录的大小</p>
	 * @param directoryPath  目录路径
	 * @return int
	 */
	public static int getDirectorySize(String directoryPath) 
	{
		return getDirectorySize(new File(directoryPath));
	}

	/**
	 * <p>Description:得到某个目录的大小</p>
	 * @param file  文件对象
	 * @return int
	 */
	public static int getDirectorySize(File file) 
	{
		int size = 0;

		File[] fileList = file.listFiles();
		if (fileList != null) {
			for (int i = 0; i < fileList.length; i++) 
			{
				if (fileList[i].isDirectory()) {
					size += getDirectorySize(fileList[i]);
				} else {
					size += fileList[i].length();
				}

			}
		}
		
		return size;
	}
	  /** 
     * <p>Description:删除文件 </p>
     * @param filePathAndName String 文件路径及名称 如c:/fqf.txt 
     * @param fileContent String 
     * @return void 
	 * @throws Exception 
     */ 
   public static void delFile(String filePathAndName)
   { 
       try { 
           String filePath = filePathAndName; 
           filePath = filePath.toString(); 
           java.io.File myDelFile = new java.io.File(filePath); 
           myDelFile.delete(); 
       } catch (Exception e) { 
           System.out.println("删除文件操作出错"); 
           e.printStackTrace(); 
       } 
   } 
   /** 
    * <p>Description:删除文件夹 </p>
    * @param filePathAndName String 文件夹路径及名称 如c:/fqf 
    * @param fileContent String 
    * @return void 
    */ 
  public static void delFolder(String folderPath) 
  { 
      try { 
    	  /*删除完里面所有内容 */
          delAllFile(folderPath); 
          String filePath = folderPath; 
          filePath = filePath.toString(); 
          java.io.File myFilePath = new java.io.File(filePath); 
          /*删除空文件夹 */
          myFilePath.delete(); 
      } catch (Exception e) { 
          System.out.println("删除文件夹操作出错"); 
          e.printStackTrace(); 
      } 
  } 

  /** 
    * <p>Description:删除文件夹里面的所有文件 </p>
    * @param path String 文件夹路径 如 c:/fqf 
    */ 
  public static void delAllFile(String path) 
  { 
      File file = new File(path); 
      
      if (!file.exists()) { 
          return; 
      } 
      
      if (!file.isDirectory()) { 
          return; 
      } 
      
      String[] tempList = file.list(); 
      File temp = null; 
      for (int i = 0; i < tempList.length; i++) 
      { 
          if (path.endsWith(File.separator)) { 
              temp = new File(path + tempList[i]); 
          } else { 
              temp = new File(path + File.separator + tempList[i]); 
          } 
          
          if (temp.isFile()) { 
              temp.delete(); 
          } 
          
          if (temp.isDirectory()) { 
        	  /*先删除文件夹里面的文件 */
              delAllFile(path+"/"+ tempList[i]);
              /*再删除空文件夹 */
              delFolder(path+"/"+ tempList[i]);
          } 
          
      } 
  } 
  
  /** 
	* @param fileName 
	* @return String
	*/
  public static String getExtention(String fileName)  
  {
      int pos = fileName.lastIndexOf(".");
      return fileName.substring(pos);
  } 
  
  /** 
	* @param file  
	* @return InputStream
	*/
  public static InputStream getInputStream(File file)
  {
	  try {
		return  new FileInputStream(file);
	  } catch (FileNotFoundException e) {
		e.printStackTrace();
	  }
	  return null; 
   }
  
  /** 
	* @param file  
	* @param filePath 
	* @return OutputStream
	*/
  public static OutputStream getOutputStream(File file ,String filePath)
  {
	  	try {
	  		return new FileOutputStream(filePath + "/" + file.getName());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
  }

  /** 
	* @param source  源文件或者目录
	* @param target 目标路径
	* @throws IOException
	*/
	private static void copy(File source, File target) throws IOException 
	{
		  File tar = new File(target, source.getName());
		  if (source.isDirectory()) {
			    System.out.println("开始创建目录：" + tar.getPath());
			    tar.mkdir();
			    File[] fs = source.listFiles();
			    for (int i = 0; i < fs.length; i++) 
			    {
			       copy(fs[i], tar);
			    }
		  } else {
			    System.out.println("开始从" + source + "拷贝文件到" + tar.getPath());
			    InputStream is = new FileInputStream(source);
			    OutputStream os = new FileOutputStream(tar);
			    byte[] buf = new byte[1024];
			    int len = 0;
			    while ((len = is.read(buf)) != -1) 
			    {
			       os.write(buf, 0, len);
			    }
			    is.close();
			    os.close();
		  }
	}

	/** 
	* <p>Description:拷贝文件或者目录到某个指定的路径</p>
	* @param source  源文件或者目录
	* @param target 目标路径
	* @throws IOException
	*/
	public static void copy(String source, String target) 
	{
		File sour = new File(source);
		File tar = new File(target);
		
		try {
			copy(sour, tar);
		} catch (IOException e) {
		  e.printStackTrace();
		}
	}
	
	/**
	* <p>Description:创建新文件</p>
	* @param path
	* @return void
	*/
	public static void createFile(String path) 
	{
		File file = new File(path);
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	* <p>Description:压缩文件或者目录到指定的路径</p>
	* @param zipFileName  目标路径
	* @param inputPath       被压缩的文件或者目录
	* @return void
	*/
	public static void zip(String zipFileName, String inputPath) 
	{
	  File inputFile = new File(inputPath);
	  ZipOutputStream out;
	  try {
	    out = new ZipOutputStream(new FileOutputStream(zipFileName));
	    zip(out, inputFile, inputFile.getName());
	    System.out.println("压缩完成！");
	    out.close();
	  } catch (Exception e) {
	    e.printStackTrace();
	  }
	}
	
	/**
	* @param zipFilePath  
	* @param paths       
	* @param names
	* @return void
	* @exception IOException
	*/
	public static void zip(String zipFilePath,String[] paths,String[] names)throws IOException
	{
		File f = new File(zipFilePath);
		f.createNewFile();
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(f));
		//out.putNextEntry(new ZipEntry("/"));
		
		for(int i=0;i<paths.length;i++)
		{
			out.putNextEntry(new ZipEntry(names[i]));
			InputStream in = ServletActionContext.getServletContext().getResourceAsStream(paths[i]);
            int b;
            while ((b = in.read()) != -1) 
            {
                out.write(b);
            }
            in.close();			
		}
		
		out.flush();
		out.close();
	}
	
	/**
	* @param out
	* @param f
	* @param base
	* @return void
	* @exception Exception
	*/
	public static void zip(ZipOutputStream out, File f, String base) throws Exception 
	{
	  System.out.println("正在压缩：" + f.getName() + " ");
	  if (f.isDirectory()) {
	    File[] fs = f.listFiles();
	    base += "/";
	    System.out.println("新建目录条目：" + f.getName());
	    /*生成相应的目录*/
	    out.putNextEntry(new ZipEntry(base)); 
	    for (int i = 0; i < fs.length; i++) 
	    {
	       /*对本目录下的所有文件对象递归调用本方法*/
	       zip(out, fs[i], base + fs[i].getName());
	    }
	  } else {
	    System.out.println("新增文件条目：" + f.getName());
	    out.putNextEntry(new ZipEntry(base));
	    InputStream is = new FileInputStream(f);
	    byte[] buf = new byte[1024];
	    int len = 0;
	    while ((len = is.read(buf)) != -1) 
	    {
	       out.write(buf, 0, len);
	    }
	    is.close();
	  }
	}

	/**
	* <p>Description:创建新目录</p>
	* @param path
	* @return void
	*/
	public static void createDir(String path) 
	{
		File file = new File(path);
		file.mkdirs();
	}
	
	/**
	 * <p>Description:下载文件</p>
	 * @param url
	 * @param strBeiAnPath
	 * @param fileDownloadPath
	 * @return	 void
	 */
	public static void copyFiles(String url,String strBeiAnPath,String fileDownloadPath) 
	{
		if (!CheckUtils.isNUll(url)) {
			
			try {
				String[] arrayFjUrl = url.split(",");
				for(int urlIndex=0;urlIndex<arrayFjUrl.length;urlIndex++)
				{
					/* 文件下载目录路径 */
					String downloadDir = ServletActionContext.getServletContext().getRealPath(fileDownloadPath); 
					/*文件下载路径 */
					String downloadFile = ServletActionContext.getServletContext().getRealPath(arrayFjUrl[urlIndex]); 	
					
					/* 发现企图下载不在 /download 下的文件, 就显示空内容 */
					if(downloadFile.startsWith(downloadDir)) { 
						copy(downloadFile,strBeiAnPath);
					} 
				}
			} catch (Exception de){
			}
			
		}
	}
}
