package com.zkt.common.core.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Properties;
import java.util.Random;

import sun.misc.BASE64Decoder;

/**
 * 文件处理的工具类
 */
public class FileUtil {

	/**
	 * 英文符号 "."
	 */
	private final static String SPOT = ".";

	/**
	 * 高级缓冲区大小：20 * 1024(Bit)
	 */
	private final static int FILEUPLOAD_SIZETHRESHOLD = 20 * 1024;

	/**
	 * 创建一个新的文件名（不含后缀名）
	 * 
	 * @param length
	 *            --int*-- 新文件名的长度（至少大于13）
	 * @return 新的文件名
	 */
	public static String gainNewFileName(int length) {
		StringBuffer tmpBuffer = new StringBuffer();

		// 部分一：系统时间
		tmpBuffer.append(System.currentTimeMillis());

		// 部分二：随机数
		int size = length - tmpBuffer.length();
		if (size > 0) {
			Random random = new Random();
			for (int i = 0; i < size; i++) {
				tmpBuffer.append(random.nextInt(10));
			}
			random = null;
		}

		return tmpBuffer.toString();
	}

	/**
	 * 创建一个新的文件名（包含后缀名）
	 * 
	 * @param fileName
	 *            --String*-- 带后缀的文件名
	 * @param length
	 *            --int*-- 新文件名的长度（至少大于13）
	 * @return 新的文件名
	 */
	public static String gainNewFileName(String fileName, int length) {
		StringBuffer tmpBuffer = new StringBuffer();

		// 部分一：系统时间
		tmpBuffer.append(System.currentTimeMillis());

		// 部分二：随机数
		int size = length - tmpBuffer.length();
		if (size > 0) {
			Random random = new Random();
			for (int i = 0; i < size; i++) {
				tmpBuffer.append(random.nextInt(10));
			}
			random = null;
		}

		// 部分三：后缀名
		if (fileName.indexOf(SPOT) != -1)
			tmpBuffer.append(fileName.substring(fileName.lastIndexOf(SPOT)));

		return tmpBuffer.toString();
	}

	/**
	 * 创建一个文件相对路径（包含文件名和后缀名）
	 * 
	 * @param type
	 *            --String*-- 文件类型名（如：CUST、STDM、PRDT、MESG、OQMK）
	 * @param fileName
	 *            --String*-- 带后缀的文件名
	 * @param length
	 *            --int*-- 新文件名的长度（至少大于13）
	 * @return 文件相对路径
	 */

	/**
	 * 生成(绝对路径的)指定目录，只生成当前目录文件夹，不生成上级文件夹，若指定目录存在则不生成
	 * 
	 * @param absolutePath
	 *            --String*-- 指定目录（即文件夹）的绝对路径
	 * @return true表示生成成功 或 已存在
	 */
	public static boolean createFolder(String absolutePath) {
		if (absolutePath == null || absolutePath.trim().equals("")) {
			return false;
		}
		File file = new File(absolutePath);
		if (!file.exists()) {
			file.mkdir();
		} else if (!file.isDirectory()) {
			file.mkdir();
		}

		file = null;

		return true;
	}

	/**
	 * 生成指定文件（绝对路径的）所有必需目录，若指定目录已存在则不生成
	 * 
	 * @param fileAbsolutePath
	 *            --String*-- 指定文件的绝对路径
	 * @return true表示生成成功 或 已存在
	 */

	/**
	 * 根据文本类文件的绝对路径，读取文件内容
	 * 
	 * @param absoluatePath
	 *            --String*-- 文件的绝对路径
	 * @return 文件内容
	 */
	public static StringBuffer readFile(String absoluatePath) {
		StringBuffer buffer = new StringBuffer();

		// 判断路径是否为空
		if (absoluatePath == null || absoluatePath.equals("")
				|| absoluatePath.trim().equals(""))
			return buffer;

		// 判断路径对应的是否是文件，是否可读
		File file = new File(absoluatePath);
		if (file == null || !file.exists() || !file.isFile() || !file.canRead())
			return buffer;

		// 开始读取文件内容
		FileInputStream fileInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		String temp = null;

		try {
			fileInputStream = new FileInputStream(file);
			inputStreamReader = new InputStreamReader(fileInputStream);
			bufferedReader = new BufferedReader(inputStreamReader);
			temp = bufferedReader.readLine();
			while (temp != null) {
				buffer.append(temp);
				temp = bufferedReader.readLine();
			}
		} catch (IOException e) {
			// e.printStackTrace();
		} finally {
			file = null;
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
				} finally {
					fileInputStream = null;
				}
			}
			if (inputStreamReader != null) {
				try {
					inputStreamReader.close();
				} catch (IOException e) {
				} finally {
					inputStreamReader = null;
				}
			}
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
				} finally {
					bufferedReader = null;
				}
			}
			temp = null;
		}

		return buffer;
	}

	/**
	 * 读取文件，返回字节数组
	 * 
	 * @param file
	 *            --File*-- 文件对象
	 * @return null或字节数组
	 */
	public static byte[] readFile(File file) throws Exception {
		// 定义返回值
		byte[] returnByteArray = null;

		// 效验文件对象是否为null
		if (file == null)
			return returnByteArray;

		FileInputStream fileInputStream = null;
		ByteArrayOutputStream byteArrayOutputStream = null;
		byte[] tmpByteArray = null;
		int position = 0, bufferSize = 1024;
		try {
			fileInputStream = new FileInputStream(file);
			byteArrayOutputStream = new ByteArrayOutputStream(bufferSize);
			tmpByteArray = new byte[bufferSize];
			while ((position = fileInputStream.read(tmpByteArray)) != -1) {
				byteArrayOutputStream.write(tmpByteArray, 0, position);
			}
			byteArrayOutputStream.flush();
			returnByteArray = byteArrayOutputStream.toByteArray();
		} catch (Exception e) {
			throw e;
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (Exception e) {
					// e.printStackTrace();
				} finally {
					fileInputStream = null;
				}
			}
			if (byteArrayOutputStream != null) {
				try {
					byteArrayOutputStream.close();
				} catch (Exception e) {
					// e.printStackTrace();
				} finally {
					byteArrayOutputStream = null;
				}
			}
			tmpByteArray = null;

		}

		return returnByteArray;
	}

	/**
	 * 根据字节数组数据，生成文件
	 * 
	 * @param file
	 *            --File*-- 文件对象
	 * @param dataByteArray
	 *            --byte[]*-- 数据的字节数组
	 * @return true表示成功
	 */
	public static boolean createFile(File file, byte[] dataByteArray)
			throws Exception {
		// 效验文件对象是否为null
		if (file == null)
			return false;

		// 效验文件数据是否为null
		if (dataByteArray == null)
			return false;

		FileOutputStream fileOutputStream = null;
		BufferedOutputStream bufferedOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(file);
			bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
			bufferedOutputStream.write(dataByteArray);
			bufferedOutputStream.flush();
		} catch (Exception e) {
			throw e;
		} finally {
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (Exception e) {
					// e.printStackTrace();
				} finally {
					fileOutputStream = null;
				}
			}
			if (bufferedOutputStream != null) {
				try {
					bufferedOutputStream.close();
				} catch (Exception e) {
					// e.printStackTrace();
				} finally {
					bufferedOutputStream = null;
				}
			}
		}

		return true;
	}

	/**
	 * 复制文件
	 * 
	 * @param fromFile
	 *            --File*--源文件对象
	 * @param toFile
	 *            --File*--目标文件对象
	 * @return true表示成功，false则表示失败
	 */
	public static boolean copyFile(File fromFile, File toFile) {
		InputStream in = null; // (缓冲)输入流
		OutputStream out = null; // (缓冲)输出流
		FileInputStream fileIn = null; // 文件输入流
		FileOutputStream fileOut = null; // 文件输出流
		byte[] data = null;

		if (fromFile.length() <= 0)
			return false;

		try {
			fileIn = new FileInputStream(fromFile);
			fileOut = new FileOutputStream(toFile);
			in = new BufferedInputStream(fileIn, FILEUPLOAD_SIZETHRESHOLD);
			out = new BufferedOutputStream(fileOut, FILEUPLOAD_SIZETHRESHOLD);
			data = new byte[1];
			while (in.read(data) != -1) {
				out.write(data);
			}
			out.flush();// 将缓冲区中的数据全部写出

			return true;
		} catch (FileNotFoundException e) {
			// return false;
		} catch (IOException e) {
			// return false;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
				in = null;
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
				out = null;
			}
			if (fileIn != null) {
				try {
					fileIn.close();
				} catch (IOException e) {
				}
				fileIn = null;
			}
			if (fileOut != null) {
				try {
					fileOut.close();
				} catch (IOException e) {
				}
				fileOut = null;
			}
			data = null;
		}
		return false;
	}

	/**
	 * 复制文件
	 * 
	 * @param fromFile
	 *            --String*--源文件绝对目录
	 * @param toFile
	 *            --String*--目标文件绝对目录
	 * @return true表示成功，false则表示失败
	 */
	public static boolean copyFile(String fromFile, String toFile) {
		if (fromFile == null || fromFile.trim().equals(""))
			return false;

		if (toFile == null || toFile.trim().equals(""))
			return false;

		File fromFileObj = new File(fromFile);
		File toFileObj = new File(toFile);
		if (fromFileObj == null || toFileObj == null)
			return false;

		if (!fromFileObj.isFile() || !fromFileObj.exists()
				|| !fromFileObj.canRead())
			return false;

		if (toFileObj.exists())
			return false;

		return copyFile(fromFileObj, toFileObj);
	}

	/**
	 * 获取指定长度的随机数字字符串
	 * 
	 * @param length
	 *            --int*-- 字符串长度
	 * @return 随机数字字符串
	 */
	public static String getRandomLengthNum(int length) {
		StringBuffer tmpBuffer = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			tmpBuffer.append(random.nextInt(10));
		}
		String tmpStr = tmpBuffer.toString();

		// 清空
		tmpBuffer = null;
		random = null;

		return tmpStr;
	}

	/**
	 * Base64 String转图片
	 * 
	 * @param imgStr
	 * @param imgFilePath
	 * @return
	 */
	public static boolean stringToImage(String imgStr, String imgFilePath) {// 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] bytes = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {// 调整异常数据
					bytes[i] += 256;
				}
			}
			// 生成jpeg图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(bytes);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 获取Properties文件值
	 * 
	 * @param filePath
	 * @param key
	 * @return
	 */
	public static String getPropertiesValue(String key) {
		Properties props = new Properties();
		try {
			InputStream in = FileUtil.class
					.getResourceAsStream("/conf.properties");
			props.load(in);
			String value = props.getProperty(key);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取jpush.Properties文件值
	 * 
	 * @param filePath
	 * @param key
	 * @return
	 */
	public static String getJpushPropertiesValue(String key) {
		Properties props = new Properties();
		try {
			InputStream in = FileUtil.class
					.getResourceAsStream("/jpush.properties");
			props.load(in);
			String value = props.getProperty(key);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 将base64字符解码保存文件
	 * 
	 * @param base64Code
	 * @param targetPath
	 * @throws Exception
	 */
	public static void decoderBase64File(String base64Code, String targetPath) {
		byte[] buffer = null;
		FileOutputStream out = null;
		try {
			buffer = new BASE64Decoder().decodeBuffer(base64Code);
			out = new FileOutputStream(targetPath);
			out.write(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String readFileContent(String filePath) {
		String content = "";
		FileInputStream fis = null;
		BufferedReader br = null;
		try {
			File file = new File(filePath);
			if (file == null || !file.exists()) {
				return null;
			}
			String tempStr = "";
			fis = new FileInputStream(file);
			br = new BufferedReader(new InputStreamReader(fis));
			while ((tempStr = br.readLine()) != null) {
				content = content + tempStr;
			}
		} catch (IOException e) {
			e.printStackTrace();
			content = null;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return content;
	}

	public static void writeFile(String filePath, String content) {
		FileWriter writer;
		try {
			File file = new File(filePath);
			if (file == null || !file.exists()) {
				return;
			}
			writer = new FileWriter(filePath);
			writer.write(content);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}