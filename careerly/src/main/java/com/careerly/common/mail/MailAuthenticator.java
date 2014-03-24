package com.careerly.common.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailAuthenticator extends Authenticator {
   private String username;
   private String password;

   public MailAuthenticator(String username, String password) {
      this.username = username;
      this.password = password;
   }

   protected PasswordAuthentication getPasswordAuthentication() {
      return new PasswordAuthentication(username, password);
   }

}