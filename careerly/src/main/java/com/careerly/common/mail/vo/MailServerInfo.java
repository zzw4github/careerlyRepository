package com.careerly.common.mail.vo;

import java.io.Serializable;
import java.util.Date;

public class MailServerInfo implements Serializable {
   private static final long serialVersionUID = 2120795136313539745L;
   private String host;
   private String userName;
   private String passWord;
   
   public String getHost() {
      return host;
   }

   public void setHost(String host) {
      this.host = host;
   }

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getPassWord() {
      return passWord;
   }

   public void setPassWord(String passWord) {
      this.passWord = passWord;
   }

}
