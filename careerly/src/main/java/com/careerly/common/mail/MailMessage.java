package com.careerly.common.mail;

import java.io.File;

public class MailMessage {
   private String to = null;
   private String subject;
   private String content;
   private File[] attachments;
   private String contentType = "text/html; charset=utf-8";

   public String getTo() {
      return to;
   }

   public void setTo(String to) {
      this.to = to;
   }

   public String getSubject() {
      return subject;
   }

   public void setSubject(String subject) {
      this.subject = subject;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   public File[] getAttachments() {
      return attachments;
   }

   public void setAttachments(File[] attachments) {
      this.attachments = attachments;
   }

   public String getContentType() {
      return contentType;
   }

   public void setContentType(String contentType) {
      this.contentType = contentType;
   }

}
