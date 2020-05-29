package com.great.tool;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;
/**
 * 但sun公司是建议不这样做的。尤其是更新了JDK版本，项目甚至还存在保存的信息。
 * 可引用 import org.apache.commons.codec.binary.Base64;进行替换
 */
//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;
import org.apache.commons.codec.binary.Base64;
import org.springframework.util.StringUtils;

public class UnitTools {

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {

		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "utf-8"));
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	public static JSONObject sendGet(String url){
        PrintWriter out = null;
        BufferedReader in = null;
        JSONObject jsonObject = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpURLConnection conn =(HttpURLConnection)realUrl.openConnection();
            if(200 == conn.getResponseCode()){
                //得到输入流
                InputStream is =conn.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while(-1 != (len = is.read(buffer))){
                    baos.write(buffer,0,len);
                    baos.flush();
                }
                result= baos.toString("utf-8");
                jsonObject = JSONObject.fromObject(result);
                System.out.println(jsonObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return jsonObject;
    } 
	public static JSONObject httpPostData(String uri,String auth,String obj ) {
		try {
			HttpClient httpclient = HttpClients.createDefault();
			HttpPost httppost = new HttpPost(uri);
			// 添加http头信息
			httppost.addHeader("Authorization", auth); // 认证token
			//httppost.addHeader("Content-Type", "application/json-patch+json");
			httppost.addHeader("Content-Type", "application/json");
			if(!StringUtils.isEmpty(obj)){
				httppost.setEntity(new StringEntity(obj));
			}
			HttpResponse response;
			response = httpclient.execute(httppost);
			// 检验状态码，如果成功接收数据
			int code = response.getStatusLine().getStatusCode();
			System.out.println(code + "code");
			if (code == 200) {
				String rev = EntityUtils.toString(response.getEntity());
				JSONObject o = JSONObject.fromObject(rev);
				System.out.println("返回数据===" + o.toString());
				return o;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * @Title: GetImageStrFromUrl
	 * @Description: TODO(将一张网络图片转化成Base64字符串)
	 * @param imgURL 网络资源位置
	 * @return Base64字符串
	 */
	public static String GetImageStrFromUrl(String imgURL) {
		System.out.println("网络图片转bas64 URL="+imgURL);
		ByteArrayOutputStream data = new ByteArrayOutputStream();
		try {
			 // 创建URL
            URL url = new URL(imgURL);
            byte[] by = new byte[1024];
            // 创建链接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            InputStream is = conn.getInputStream();
            // 将内容读取内存中
            int len = -1;
            while ((len = is.read(by)) != -1) {
                data.write(by, 0, len);
            }
            // 关闭流
            is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
//		BASE64Encoder encoder = new BASE64Encoder();
		// 返回Base64编码过的字节数组字符串
//		return encoder.encode(data.toByteArray());
		return Base64.encodeBase64String(data.toByteArray());
	}
 
	/**
	 * @Title: GetImageStrFromPath
	 * @Description: TODO(将一张本地图片转化成Base64字符串)
	 * @param imgPath
	 * @return
	 */
	public static String GetImageStrFromPath(String imgPath) {
		FileInputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgPath);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
//		BASE64Encoder encoder = new BASE64Encoder();
		// 返回Base64编码过的字节数组字符串
//		return encoder.encode(data);
		return Base64.encodeBase64String(data);
	}
 
	/**
	 * @Title: GenerateImage
	 * @Description: TODO(base64字符串转化成图片)
	 * @param imgStr
	 * @return
	 */
	public static boolean GenerateImage(String imgStr,String imgFilePath) {
		if (imgStr == null) // 图像数据为空
			return false;
//		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
//			byte[] b = decoder.decodeBuffer(imgStr);
			byte[] b=Base64.decodeBase64(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
