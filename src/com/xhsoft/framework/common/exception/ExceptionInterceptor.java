/*
 * Copyright 2002-2006,2009 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xhsoft.framework.common.exception;

import java.io.IOException;
import java.sql.SQLException;
import java.lang.Exception;

import org.hibernate.hql.ast.QuerySyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ExceptionInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 6724582704116901783L;
	private static final Logger logger = LoggerFactory.getLogger("business");

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		String result = "";

		String actionName = invocation.getInvocationContext().getName();
		String action = invocation.getAction().toString();
		try {

			result = invocation.invoke();

		} catch (QuerySyntaxException ex) {

			logger.error("异常拦截器拦截到异常：HQL语句出错异常!" + "    " + "action名为:"
					+ action + "    " + "action方法为:" + actionName + "    "
					+ "异常 详细信息：" + ex.toString());

			return null;

		} catch (SQLException ex) {

			logger.error("异常拦截器拦截到异常：SQL语句出错异常!" + "    " + "action名为:"
					+ action + "    " + "action方法为:" + actionName + "    "
					+ "异常的详细信息：" + ex.toString());

			return null;

		} catch (NullPointerException ex) {

			logger.error("异常拦截器拦截到异常：空指针异常!" + "action名为:" + action + "    "
					+ "action方法为:" + actionName + "    " + "异常的详细信息："
					+ ex.toString());

			return null;

		} catch (ClassNotFoundException ex) {

			logger.error("异常拦截器拦截到异常：指定的类不存在异常!" + "action名为:" + action
					+ "    " + "action方法为:" + actionName + "    " + "异常的详细信息："
					+ ex.toString());

			return null;

		} catch (ArithmeticException ex) {

			logger.error("异常拦截器拦截到异常：数学运算异常!" + "action名为:" + action + "    "
					+ "action方法为:" + actionName + "    " + "异常的详细信息："
					+ ex.toString());

			return null;

		} catch (ArrayIndexOutOfBoundsException ex) {

			logger.error("异常拦截器拦截到异常：数组下标越界异常!" + "action名为:" + action + "    "
					+ "action方法为:" + actionName + "    " + "异常的详细信息："
					+ ex.toString());

			return null;

		} catch (IllegalArgumentException ex) {

			logger.error("异常拦截器拦截到异常：方法的参数错误异常!" + "action名为:" + action
					+ "    " + "action方法为:" + actionName + "    " + "异常的详细信息："
					+ ex.toString());

			return null;

		} catch (IllegalAccessException ex) {

			logger.error("异常拦截器拦截到异常：没有访问权限异常!" + "action名为:" + action + "    "
					+ "action方法为:" + actionName + "    " + "异常的详细信息："
					+ ex.toString());

			return null;

		} catch (StackOverflowError ex) {

			logger.error("异常拦截器拦截到异常：堆栈溢出错误异常!" + "action名为:" + action + "    "
					+ "action方法为:" + actionName + "    " + "异常的详细信息："
					+ ex.toString());

			return null;

		} catch (IOException ex) {

			logger.error("异常拦截器拦截到异常：IO异常!" + "action名为:" + action + "    "
					+ "action方法为:" + actionName + "    " + "异常的详细信息："
					+ ex.toString());
			return null;
		}
		return null;
	}
}