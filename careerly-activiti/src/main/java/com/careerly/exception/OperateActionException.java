package com.careerly.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

/**   
* @Title: OperateActionException.java 
* @Package com.careerly.exception 
* @Description: 操作controller异常类
* @author careerly
*/ 
public class OperateActionException extends Exception {
   private static final long serialVersionUID = -8582869876478864758L;
   private Throwable nestedThrowable = null;
   private Object result = null;

   public OperateActionException() {
      super();
   }

   public OperateActionException(Object result) {
      this.result = result;
   }

   public OperateActionException(String msg) {
      super(msg);
   }

   public OperateActionException(Object result, String msg) {
      super(msg);
      this.result = result;
   }

   public OperateActionException(Throwable nestedThrowable) {
      this.nestedThrowable = nestedThrowable;
   }

   public OperateActionException(String msg, Throwable nestedThrowable) {
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

   public Object getResult() {
      return result;
   }

   public void setResult(Object result) {
      this.result = result;
   }
}
