/*
 * $RCSfile: ImageHepler,v $$
 * $Revision: 1.0  $
 * $Date: 2011  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * <p>Title:ImageHepler</p>
 * <p>Description: Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
                                  Jad home page: http://kpdus.tripod.com/jad.html
                                  Decompiler options: packimports(3) fieldsfirst ansi space 
                                  Source File Name:   ImageHepler.java
    </p>
 * <p>Copyright:Copyright (C) 2011</p>
 * @since 2011
 */
public class ImageHelper
{
	public ImageHelper()
	{
	}

	/**
     * <p>Description:makeThumbnail</p>
     * @param img 
     * @param width 
     * @param height    
     * @return BufferedImage
     */
	private static BufferedImage makeThumbnail(Image img, int width, int height)
	{
		BufferedImage tag = new BufferedImage(width, height, 1);
		Graphics g = tag.getGraphics();
		g.drawImage(img.getScaledInstance(width, height, 4), 0, 0, null);
		g.dispose();
		return tag;
	}

	/**
     * <p>Description:saveSubImage</p>
     * @param image 
     * @param subImageBounds 
     * @param subImageFile    
     * @return void
     * @exception IOException IO异常
     */
	private static void saveSubImage(BufferedImage image, Rectangle subImageBounds, File subImageFile) throws IOException
	{
		String fileName = subImageFile.getName();
		String formatName = fileName.substring(fileName.lastIndexOf('.') + 1);
		BufferedImage subImage = new BufferedImage(subImageBounds.width, subImageBounds.height, 1);
		Graphics g = subImage.getGraphics();
		
		if (subImageBounds.width > image.getWidth() || subImageBounds.height > image.getHeight()){
			int left = subImageBounds.x;
			int top = subImageBounds.y;
			if (image.getWidth() < subImageBounds.width)
				left = (subImageBounds.width - image.getWidth()) / 2;
			
			if (image.getHeight() < subImageBounds.height)
				top = (subImageBounds.height - image.getHeight()) / 2;
			
			g.setColor(Color.white);
			g.fillRect(0, 0, subImageBounds.width, subImageBounds.height);
			g.drawImage(image, left, top, null);
			
			ImageIO.write(image, formatName, subImageFile);
			System.out.println((new StringBuilder("if is running left:")).append(left).append(" top: ").append(top).toString());
		} else{
			g.drawImage(image.getSubimage(subImageBounds.x, subImageBounds.y, subImageBounds.width, subImageBounds.height), 0, 0, null);
			System.out.println("else is running");
		}
		
		g.dispose();
		ImageIO.write(subImage, formatName, subImageFile);
	}

	/**
     * <p>Description:cut</p>
     * @param srcImageFile 
     * @param descDir 
     * @param width    
     * @param height               
     * @param rect            
     * @return void
     * @exception IOException IO异常
     */
	public static void cut(String srcImageFile, String descDir, int width, int height, Rectangle rect) throws IOException
	{
		Image image = ImageIO.read(new File(srcImageFile));
		BufferedImage bImage = makeThumbnail(image, width, height);
		saveSubImage(bImage, rect, new File(descDir));
	}

	/**
     * <p>Description:cut</p>
     * @param srcImageFile 
     * @param descDir 
     * @param width    
     * @param height               
     * @param rect            
     * @return void
     * @exception IOException IO异常
     */
	public static void cut(File srcImageFile, File descDir, int width, int height, Rectangle rect) throws IOException
	{
		Image image = ImageIO.read(srcImageFile);
		BufferedImage bImage = makeThumbnail(image, width, height);
		saveSubImage(bImage, rect, descDir);
	}
	
	/**
     * <p>Description:通过传入需要处理的图片文件输入流，起始点（左上角）以及区域的宽度和高度，裁剪图片</p>
     * @param fileInputStream 图片文件输入流
     * @param zoomOutCropFime 图片文件输出
     * @param zoomRatio       缩放比例
     * @param x               起始点（左上角）x坐标
     * @param y               起始点（左上角）y坐标
     * @param width           区域的宽度
     * @param height          区域的高度
     * @return 处理后的图片BufferedImage，不能为null
     * @exception IOException IO异常
     */
    public static void zoomOutCrop(String fileInputStream,String zoomOutCropFime, 
    		                                                double zoomRatio, int x, int y, int width, int height) throws IOException 
    {
        if (zoomRatio > 1 || zoomRatio <= 0) zoomRatio = 1;

        BufferedImage image = ImageIO.read(new File(fileInputStream));

        int newWidth = new Double(image.getWidth() * zoomRatio).intValue();
        int newHeight = new Double(image.getHeight() * zoomRatio).intValue();

        BufferedImage bufferedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_3BYTE_BGR);
        Graphics graphics = bufferedImage.createGraphics();
        graphics.drawImage(image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);

        /*验证是否超出范围*/
        if (x + width > newWidth) {
            x = 0;
            width = newWidth;
        }
        
        if (y + height > newHeight) {
            y = 0;
            height = newHeight;
        }
        
        ImageIO.write(bufferedImage.getSubimage(x, y, width, height), "jpg", new File(zoomOutCropFime));
    }
    
  
    //图片格式转换
  	public static Image convertImg(String imgPath){
  		Image img = null;
  		
  		return img;
  		
  	}
  	//图片大小调整
  	//图片分辨率调整
  	//计算等比缩放长宽
  	public static void getSize(){}
  	//判断是否图片文件
  	public static boolean isImage(File file){
  		boolean flag = false;
  		try {
  			BufferedImage image = ImageIO.read(file);
  			if(image==null){
  				return flag;
  			}
  			
  			int width = image.getWidth();
  			int heigth = image.getHeight();
  			if(width==0||heigth==0){
  				return flag;
  			}
  			flag = true;
  		} catch (IOException e) {
  			e.printStackTrace();
  		}
  		return flag;
  		
  	}
}
