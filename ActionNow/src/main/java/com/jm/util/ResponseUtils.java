/**
 * 
 */
package com.jm.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

import com.jm.constants.response.News;
import com.jm.constants.response.ResponseKeys;

/**
 * @author LuZheqi
 * 
 */
public class ResponseUtils {
	private static Element createRootResponse(String fromName, String toName) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String times = format.format(new Date());

		Element rootXML = new Element("xml");
		rootXML.addContent(new Element(ResponseKeys.TOUSERNAME.toString())
				.setText(fromName));
		rootXML.addContent(new Element(ResponseKeys.FROMUSERNAME.toString())
				.setText(toName));
		rootXML.addContent(new Element(ResponseKeys.CREATETIME.toString())
				.setText(times));
		return rootXML;
	}

	public static String createTextResponse(String fromName, String toName,
			String text) {
		if (null == text) {
			text = "";
		}

		Element rootXML = createRootResponse(fromName, toName);
		rootXML.addContent(new Element(ResponseKeys.MSGTYPE.toString())
				.setText("text"));
		rootXML.addContent(new Element(ResponseKeys.CONTENT.toString())
				.setText(text));

		XMLOutputter XMLOut = new XMLOutputter();
		return XMLOut.outputString(rootXML);
	}

	public static String createImageResponse(String fromName, String toName,
			String mediaId) {
		if (null == mediaId) {
			mediaId = "";
		}
		Element rootXML = createRootResponse(fromName, toName);
		rootXML.addContent(new Element(ResponseKeys.MSGTYPE.toString())
				.setText("image"));
		Element imageXML = new Element("Image");
		imageXML.addContent(new Element(ResponseKeys.MEDIAID.toString())
				.setText(mediaId));
		rootXML.addContent(imageXML);

		XMLOutputter XMLOut = new XMLOutputter();
		return XMLOut.outputString(rootXML);
	}

	public static String createVoiceResponse(String fromName, String toName,
			String mediaId) {
		if (null == mediaId) {
			mediaId = "";
		}
		Element rootXML = createRootResponse(fromName, toName);
		rootXML.addContent(new Element(ResponseKeys.MSGTYPE.toString())
				.setText("voice"));
		Element voiceXML = new Element("Voice");
		voiceXML.addContent(new Element(ResponseKeys.MEDIAID.toString())
				.setText(mediaId));
		rootXML.addContent(voiceXML);

		XMLOutputter XMLOut = new XMLOutputter();
		return XMLOut.outputString(rootXML);
	}

	public static String createVideoResponse(String fromName, String toName,
			String mediaId, String title, String desciption) {
		if (null == mediaId) {
			mediaId = "";
		}
		if (null == title) {
			title = "";
		}
		if (null == desciption) {
			desciption = "";
		}

		Element rootXML = createRootResponse(fromName, toName);
		rootXML.addContent(new Element(ResponseKeys.MSGTYPE.toString())
				.setText("video"));
		Element vediaXML = new Element("Video");
		vediaXML.addContent(new Element(ResponseKeys.MEDIAID.toString())
				.setText(mediaId));
		vediaXML.addContent(new Element(ResponseKeys.TITLE.toString())
				.setText(title));
		vediaXML.addContent(new Element(ResponseKeys.DESCRIPTION.toString())
				.setText(desciption));
		rootXML.addContent(vediaXML);

		XMLOutputter XMLOut = new XMLOutputter();
		return XMLOut.outputString(rootXML);
	}

	public static String createMusicResponse(String fromName, String toName,
			String title, String desciption, String musicUrl,
			String hqMusicUrl, String mediaId) {
		if (null == title) {
			title = "";
		}
		if (null == desciption) {
			desciption = "";
		}
		if (null == musicUrl) {
			musicUrl = "";
		}
		if (null == hqMusicUrl) {
			hqMusicUrl = "";
		}
		if (null == mediaId) {
			mediaId = "";
		}

		Element rootXML = createRootResponse(fromName, toName);
		rootXML.addContent(new Element(ResponseKeys.MSGTYPE.toString())
				.setText("music"));
		Element musicXML = new Element("Music");
		musicXML.addContent(new Element(ResponseKeys.TITLE.toString())
				.setText(title));
		musicXML.addContent(new Element(ResponseKeys.DESCRIPTION.toString())
				.setText(desciption));
		musicXML.addContent(new Element(ResponseKeys.MUSICURL.toString())
				.setText(musicUrl));
		musicXML.addContent(new Element(ResponseKeys.HQMUSICURL.toString())
				.setText(hqMusicUrl));
		musicXML.addContent(new Element(ResponseKeys.THUMBMEDIAID.toString())
				.setText(mediaId));
		rootXML.addContent(musicXML);

		XMLOutputter XMLOut = new XMLOutputter();
		return XMLOut.outputString(rootXML);
	}

	public static String createArticlecResponse(String fromName, String toName,
			List<News> newses) {
		if (null == newses) {
			newses = new ArrayList<News>();
		}
		int articleCount = newses.size();
		if (articleCount > 10) {
			articleCount = 10;
		}

		Element rootXML = createRootResponse(fromName, toName);
		rootXML.addContent(new Element(ResponseKeys.MSGTYPE.toString())
				.setText("news"));
		rootXML.addContent(new Element(ResponseKeys.ARTICLECOUNT.toString())
				.setText(String.valueOf(articleCount)));
		Element articlesXML = new Element("Articles");
		for (int i = 0; i < articleCount; i++) {
			News news = newses.get(i);
			Element item = new Element("item");

			item.addContent(new Element(ResponseKeys.TITLE.toString())
					.setText(news.getTitle() == null ? "" : news.getTitle()));
			item.addContent(new Element(ResponseKeys.DESCRIPTION.toString())
					.setText(news.getDescription() == null ? "" : news
							.getDescription()));
			item.addContent(new Element(ResponseKeys.MUSICURL.toString())
					.setText(news.getPicUrl() == null ? "" : news.getPicUrl()));
			item.addContent(new Element(ResponseKeys.HQMUSICURL.toString())
					.setText(news.getUrl() == null ? "" : news.getUrl()));

			articlesXML.addContent(item);

		}
		rootXML.addContent(articlesXML);
		XMLOutputter XMLOut = new XMLOutputter();
		return XMLOut.outputString(rootXML);
	}
}
