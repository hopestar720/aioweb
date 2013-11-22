package com.xhsoft.framework.common.jcaptcha;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.ImageFilter;

import com.jhlabs.image.WaterFilter;
import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomRangeColorGenerator;
import com.octo.captcha.component.image.deformation.ImageDeformation;
import com.octo.captcha.component.image.deformation.ImageDeformationByFilters;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.DecoratedRandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.textpaster.textdecorator.LineTextDecorator;
import com.octo.captcha.component.image.textpaster.textdecorator.TextDecorator;
import com.octo.captcha.component.image.wordtoimage.DeformedComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;


/**
 * 自定义验证码内容样式(通过源码改编)
 * @author hopestar720@126.com
 * @since 2013年11月22日
 */
public class ImageCaptchaEngineExtend extends ListImageCaptchaEngine {
	/**
	 * this method should be implemented as folow :
	 * <ul>
	 * <li>First construct all the factories you want to initialize the gimpy
	 * with</li>
	 * <li>then call the this.addFactoriy method for each factory</li>
	 * </ul>
	 */
	protected void buildInitialFactories() {

		// build filters 波浪实现类
		WaterFilter water = new WaterFilter();

		water.setAmplitude(3d);
		water.setAntialias(true);
		water.setPhase(20d);
		water.setWavelength(70d);

		ImageDeformation backDef = new ImageDeformationByFilters(
				new ImageFilter[] {});
		ImageDeformation textDef = new ImageDeformationByFilters(
				new ImageFilter[] {});
		ImageDeformation postDef = new ImageDeformationByFilters(
				new ImageFilter[] { water });

		// word generator
		WordGenerator dictionnaryWords = new RandomWordGenerator(
				"abcdefhjkmnprstuvwxyz23456789");
		// wordtoimage components
		RandomRangeColorGenerator colors = new RandomRangeColorGenerator(
				new int[] { 0, 150 }, new int[] { 0, 150 },
				new int[] { 0, 150 });

		// Arial,Tahoma,Verdana,Helvetica,宋体,黑体,幼圆, 字体大小
		Font[] fonts = new Font[] { new Font("Arial", 0, 10),
				new Font("Tahoma", 0, 10), new Font("Verdana", 0, 10),
				new Font("Helvetica", 0, 10), new Font("宋体", 0, 10),
				new Font("黑体", 0, 10), new Font("幼圆", 0, 10) };

		// 设置字符以及干扰线颜色
		RandomRangeColorGenerator lineColors = new RandomRangeColorGenerator(
				new int[] { 150, 255 }, new int[] { 150, 255 }, new int[] {
						150, 255 });

		// 添加干扰线(可选取圆点干扰实现类BaffleTextDecorator)
		TextPaster randomPaster = new DecoratedRandomTextPaster(new Integer(4),
				new Integer(4), colors, true,
				new TextDecorator[] { new LineTextDecorator(new Integer(1),
						lineColors) });
		// 背景描绘
		BackgroundGenerator back = new UniColorBackgroundGenerator(new Integer(
				140), new Integer(50), Color.white);

		FontGenerator shearedFont = new RandomFontGenerator(new Integer(30),
				new Integer(0), fonts);
		// word2image 1
		WordToImage word2image = new DeformedComposedWordToImage(shearedFont,
				back, randomPaster, backDef, textDef, postDef);
		// 输入图片
		this.addFactory(new GimpyFactory(dictionnaryWords, word2image));

	}
}
