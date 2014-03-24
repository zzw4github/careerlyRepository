package com.careerly.common.jsch;

import java.io.InputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class JschHandler {
	 
    /**
     * @author careerly
     * @date 2014-2-26
     * @time 上午11:05:09
     * @Description: ssh登录，并进行操作
     * @param host
     * @param user
     * @param pwd
     * @param command
     * @return String
     */
    public  String sshExecute(String host, String user, String pwd,
            String command) {
        StringBuffer sb = new StringBuffer();
        try {
            JSch jsch = new JSch();
            jsch.setConfig("StrictHostKeyChecking", "no");  
            Session session = jsch.getSession(user, host, 22);
            session.setPassword(pwd);
            session.connect();
            Channel channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);
            InputStream in = channel.getInputStream();
            channel.connect();
            int nextChar;
            while (true) {
                while ((nextChar = in.read()) != -1) {
                    sb.append((char) nextChar);
                }
                if (channel.isClosed()) {
                    System.out.println("exit-status: "
                            + channel.getExitStatus());
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                }
            }
            channel.disconnect();
            session.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();

    }
    
}
