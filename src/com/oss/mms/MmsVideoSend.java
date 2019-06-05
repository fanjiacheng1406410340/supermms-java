package com.oss.mms;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.stream.FileImageInputStream;

import sun.misc.BASE64Encoder;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dahantc.beans.Content;
import com.dahantc.util.HttpUtil;

/**
 * 类描述：彩信下发、彩信上传
 * 
 * @version: 1.0
 * @author: 8588
 * @date: 2017年6月28日 下午1:28:53
 */
public class MmsVideoSend {

	/**
	 * 方法描述：上传超级彩信模板
	 */
	public String submit(String url, String account, String password, String templateNo, String phones) throws IOException {
		String sendPost = null;

		JSONObject json = new JSONObject();

		json.put("account", account);
		json.put("password", password);
		json.put("cmdId", "002");
		json.put("templateNo", templateNo);
		json.put("mobiles", phones);

		try {

			sendPost = HttpUtil.postMethod(url, json.toJSONString().getBytes());

			System.out.println("-------------------------");
			System.out.println(sendPost);
			System.out.println("-------------------------");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return sendPost;
	}

	/**
	 * 方法描述：获取状态报告
	 */
	public String report(String url, String account, String password) throws IOException {
		String sendPost = null;

		JSONObject json = new JSONObject();

		json.put("account", account);
		json.put("password", password);
		json.put("cmdId", "004");

		try {

			sendPost = HttpUtil.postMethod(url, json.toJSONString().getBytes());

			System.out.println("-------------------------");
			System.out.println(sendPost);
			System.out.println("-------------------------");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return sendPost;
	}

	/**
	 * 方法描述：上传超级彩信模板
	 */
	public String upload(String url, String account, String password, String path, String title) throws IOException {
		String sendPost = null;

		JSONObject json = new JSONObject();

		json.put("account", account);
		json.put("password", password);
		json.put("cmdId", "001");
		json.put("title", title);

		File fileFolder = new File(path);
		File[] files = fileFolder.listFiles();

		JSONArray array = new JSONArray();
		for (File file : files) {
			String suffix = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length());
			Content content = new Content();
			if (!suffix.equals("txt")) {
				content.setContent(encodeBase64File(file.getPath()));
				content.setName(file.getName());
			} else {
				content.setContent(txt2String(file));
			}
			array.add(content);
		}
		json.put("content", array);
		try {

			sendPost = HttpUtil.postMethod(url, json.toJSONString().getBytes());

			System.out.println("-------------------------");
			System.out.println(sendPost);
			System.out.println("-------------------------");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return sendPost;
	}

	/**
	 * 方法描述：审核状态获取
	 */
	public String queryVideoCheckStatus(String url, String account, String password, String templateNo) throws IOException {
		String sendPost = null;

		JSONObject json = new JSONObject();

		json.put("account", account);
		json.put("password", password);
		json.put("cmdId", "003");
		json.put("templateNo", templateNo);
		try {

			sendPost = HttpUtil.postMethod(url, json.toJSONString().getBytes());

			System.out.println("-------------------------");
			System.out.println(sendPost);
			System.out.println("-------------------------");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return sendPost;
	}

	/**
	 * 将文件转成base64 字符串
	 *
	 * @param path
	 *            文件路径
	 * @return
	 */
	public static String encodeBase64File(String path) {
		try {
			// 将文件 转换为字符串
			File file = new File(path);
			FileInputStream inputFile = new FileInputStream(file);
			byte[] buffer = new byte[(int) file.length()];
			inputFile.read(buffer);
			inputFile.close();
			System.err.println("加密");
			// 字符串加密
			return new BASE64Encoder().encode(buffer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 读取txt文件的内容
	 * 
	 * @param file
	 *            想要读取的文件对象
	 * @return 返回文件内容
	 */
	private static String txt2String(File file) {
		StringBuilder result = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));// 构造一个BufferedReader类来读取文件
			String s = null;
			int flag = 0;
			while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
				if (flag == 0) {
					result.append(s);
				} else {
					result.append(System.lineSeparator() + s);
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}

}
