/*
 * %W% %E%
 *
 * Copyright (c) 2012, My Team and/or its affiliates. All rights reserved.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.file;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO
 *
 * @author Admin
 * @version 1.0.0
 * @since   2012-12-6
 */
public class PathHandle {
	
	public static String formatPath(String path){
		Pattern pat = Pattern.compile("\\");
		Matcher mac = pat.matcher(path);
		return mac.replaceAll("/");
	}

}
