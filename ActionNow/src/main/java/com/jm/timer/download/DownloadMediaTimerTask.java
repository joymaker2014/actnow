/**
 * 
 */
package com.jm.timer.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jm.constants.MediaType;
import com.jm.constants.UrlConstants;
import com.jm.timer.TimerTask;
import com.jm.timer.TimerTrigger;
import com.jm.timer.accesstoken.AccessTokenTimer;
import com.jm.util.CommonUtils;

/**
 * @author LuZheqi
 * 
 */
public class DownloadMediaTimerTask extends TimerTask {
	private final int category;

	private final int type;

	private final MediaType mediaType;

	private final Date time;

	private final String mediaId;

	public DownloadMediaTimerTask(TimerTrigger trigger, int category, int type,
			MediaType mediaType, Date time, String mediaId) {
		super(trigger);
		this.category = category;
		this.type = type;
		this.mediaType = mediaType;
		this.time = time;
		this.mediaId = mediaId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jm.timer.TimerTask#run(long)
	 */
	@Override
	protected void run(long paramLong) {
		String filePathRoot = CommonUtils.getProperties("data.base.dir",
				".\\data\\");
		if (!filePathRoot.endsWith("\\")) {
			filePathRoot += "\\";
		}
		SimpleDateFormat myFmt = new SimpleDateFormat("yyyy.MM.dd");
		filePathRoot = filePathRoot + mediaType + "\\" + myFmt.format(time)
				+ "\\" + category + "\\" + type;
		File path = new File(filePathRoot);
		if (!path.exists() && !path.isDirectory()) {
			path.mkdirs();
		}
		String fileName = filePathRoot + "\\" + mediaId;
		if (mediaType.equals(MediaType.IMAGE)) {
			fileName += ".jpg";
		} else if (mediaType.equals(MediaType.VOICE)) {
			fileName += ".mp3";
		}
		String url = UrlConstants.DOWNLOAD_MEDIA_URL.replaceAll("ACCESS_TOKEN",
				AccessTokenTimer.getAccessToken()).replaceAll("MEDIA_ID",
				mediaId);
		CloseableHttpResponse response = null;
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpget = new HttpGet(url);
			response = httpclient.execute(httpget);

			HttpEntity entity = response.getEntity();
			if (entity != null) {
				long len = entity.getContentLength();
				if (len != -1) {
					byte[] content = EntityUtils.toByteArray(entity);
					JSONObject object = null;
					try {
						object = JSON.parseObject(new String(content, "UTF-8"));
					} catch (Exception e) {
					} finally {
						if (null != object && null != object.get("errmsg")) {
							return;
						}
					}
					System.out.println(response.getStatusLine());
					OutputStream os = new FileOutputStream(fileName);
					// 开始读取
					os.write(content);
					// 完毕，关闭所有链接
					os.close();

				} else {
					// Stream content out
				}
			}
			response.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
