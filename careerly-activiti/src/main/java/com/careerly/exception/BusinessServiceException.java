package com.careerly.exception;

import java.io.PrintStream;
import java.io.PrintWriter;


/**   
* @Title: BusinessServiceException.java 
* @Package com.careerly.exception 
* @Description: 操作业务逻辑层异常类
* @author careerly
*/ 
public class BusinessServiceException extends Exception {

   private static final long serialVersionUID = -8582869876478864758L;
   private Throwable nestedThrowable = null;

   public BusinessServiceException() {
      super();
   }

   public BusinessServiceException(Throwable nestedThrowable) {
      this.nestedThrowable = nestedThrowable;
   }

   public BusinessServiceException(String msg) {
      super(msg);
   }

   public BusinessServiceException(String msg,Throwable nestedThrowable) {
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
