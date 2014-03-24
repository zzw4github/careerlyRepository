package com.careerly.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

/**   
* @Title: InterfaceException.java 
* @Package com.careerly.exception 
* @Description: 操作外部接口异常类
* @author careerly
*/ 
public class InterfaceException extends Exception{

	private static final long serialVersionUID = -8255584562356942977L;
	
	private Throwable nestedThrowable = null;

    public InterfaceException() {
        super();
    }
    public InterfaceException(String msg) {
        super(msg);
    }

    public InterfaceException(Throwable nestedThrowable) {
        this.nestedThrowable = nestedThrowable;
    }

    public InterfaceException(String msg, Throwable nestedThrowable) {
        super(msg);
        this.nestedThrowable = nestedThrowable;
    }

    public void printStackTrace() {
        super.printStackTrace();
        if (nestedThrowable != null) {
            nestedThrowable.printStackTrace();
        }
    }

    public void printStackTrace(PrintStream ps) {
        super.printStackTrace(ps);
        if (nestedThrowable != null) {
            nestedThrowable.printStackTrace(ps);
        }
    }

    public void printStackTrace(PrintWriter pw) {
        super.printStackTrace(pw);
        if (nestedThrowable != null) {
            nestedThrowable.printStackTrace(pw);
        }
    }
}
