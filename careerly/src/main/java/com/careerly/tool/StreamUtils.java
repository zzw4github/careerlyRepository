package com.careerly.tool;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Vector;

public class StreamUtils {
	/**
	 * @author careerly
	 * @date 2014-2-24
	 * @time 上午10:49:43
	 * @Description:URL类型转换为 byte[]类型的数据
	 * @param paramURL
	 * @return byte[]
	 * @throws 
	 */
	public static byte[] getUrlArray(URL paramURL) {
		byte[] arrayOfByte = (byte[]) null;
		int i = 0;
		Vector localVector;
		try {
			InputStream localInputStream = paramURL.openStream();
			localVector = new Vector();
			byte b;
			while ((b = (byte) localInputStream.read()) != -1) {
				localVector.add(i, new Byte(b));
				i++;
			}
		} catch (Exception localException) {
			return null;
		}
		arrayOfByte = new byte[localVector.size()];
		for (int j = 0; j < localVector.size(); j++)
			arrayOfByte[j] = ((byte) ((Byte) localVector.elementAt(j))
					.intValue());
		return arrayOfByte;
	}

	/**
	 * @author careerly
	 * @date 2014-2-24
	 * @time 上午10:50:43
	 * @Description: URL类型转换为String类型的数据
	 * @param paramURL
	 * @return String
	 * @throws 
	 */
	public static String getUrlString(URL paramURL) {
		byte[] arrayOfByte = getUrlArray(paramURL);
		if (arrayOfByte == null)
			return null;
		return new String(arrayOfByte);
	}

	/**
	 * @author careerly
	 * @date 2014-2-24
	 * @time 上午10:52:28
	 * @Description: 从输入流中读取byte[]
	 * @param paramInputStream
	 * @param byteSize
	 * @return
	 * @throws IOException byte[]
	 */
	public static byte[] getArrayFromIS(InputStream paramInputStream,
			int byteSize) throws IOException {
		if (paramInputStream == null)
			return new byte[0];
		ByteArrayOutputStream localByteArrayOutputStream = null;
		if (byteSize == -1)
			localByteArrayOutputStream = new ByteArrayOutputStream();
		else
			localByteArrayOutputStream = new ByteArrayOutputStream(byteSize);
		byte[] arrayOfByte = new byte[65535];
		int i = 0;
		int j = 0;
		while ((j = paramInputStream.read(arrayOfByte)) != -1) {
			if ((byteSize != -1) && (i + j >= byteSize)) {
				localByteArrayOutputStream.write(arrayOfByte, 0, byteSize - i);
				break;
			}
			localByteArrayOutputStream.write(arrayOfByte, 0, j);
			i += j;
		}
		return localByteArrayOutputStream.toByteArray();
	}

	/**
	 * @author careerly
	 * @date 2014-2-24
	 * @time 上午10:54:21
	 * @Description: 输入流转化为文件流
	 * @param paramInputStream
	 * @param paramFile
	 * @throws IOException void
	 */
	public static void inputStreamToFile(InputStream paramInputStream, File paramFile)
										throws IOException {
		FileOutputStream localFileOutputStream = null;
		try {
			if (paramInputStream == null) {
				return;
			}
			localFileOutputStream = new FileOutputStream(paramFile);
			byte[] arrayOfByte = new byte[65535];
			int i = 0;
			while ((i = paramInputStream.read(arrayOfByte)) != -1)
				localFileOutputStream.write(arrayOfByte, 0, i);
		} finally {
			if (localFileOutputStream != null) {
				localFileOutputStream.close();
			}
		}
	}
}