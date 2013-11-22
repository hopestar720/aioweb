/*
 * $RCSfile: NotLoginException,v $$
 * $Revision: 1.0  $
 * $Date: 2012-12-28  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.exception;

/**
 * <p>Title:NotLoginException</p>
 * <p>Description:NotLoginException</p>
 * <p>Copyright:Copyright (C) 2011</p>
 * @author wenzhi
 * @date 2011-12-28
 */
public class NotLoginException extends BaseRuntimeException
{

    private static final long serialVersionUID = -927028338979047567L;

    public NotLoginException() 
    {
        super("global.error.code.not.login", new String[] {});
    }
}
