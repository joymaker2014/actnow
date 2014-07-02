package com.jm.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jm.model.ack.Ack;
import com.jm.util.CommonUtils;

@Controller
public class DownloadResource {
	@RequestMapping(value = "download", method = RequestMethod.POST)
	public Ack get(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String mediaType = req.getParameter("mediatype");
		String mediaId = req.getParameter("mediaid");
		int category = Integer.valueOf(req.getParameter("category"));
		int type = Integer.valueOf(req.getParameter("type"));
		long timeStamp = Long.valueOf(req.getParameter("timestamp"));

		String filePathRoot = CommonUtils.getProperties("data.base.dir",
				".\\data\\");
		if (!filePathRoot.endsWith("\\")) {
			filePathRoot += "\\";
		}
		SimpleDateFormat myFmt = new SimpleDateFormat("yyyy.MM.dd");
		Date time = new Date(timeStamp);
		filePathRoot = filePathRoot + mediaType + "\\" + myFmt.format(time)
				+ "\\" + category + "\\" + type;
		File path = new File(filePathRoot);
		if (!path.exists() && !path.isDirectory()) {
			return new Ack("Wrong Parameters");
		}
		String fileName = filePathRoot + "\\" + mediaId;
		if (mediaType.equals(com.jm.constants.MediaType.IMAGE)) {
			fileName += ".jpg";
		} else if (mediaType.equals(com.jm.constants.MediaType.VOICE)) {
			fileName += ".mp3";
		} else {
			return new Ack("Wrong MediaType");
		}

		File file = new File(fileName);
		if (file.isFile()) {
			return new Ack("Wrong Parameters");
		}
		OutputStream os = null;
		try {
			os = res.getOutputStream();
			res.reset();
			res.setHeader("Content-Disposition",
					"attachment; filename=dict.txt");
			res.setContentType("application/octet-stream; charset=utf-8");
			os.write(FileUtils.readFileToByteArray(file));
			os.flush();
		} finally {
			if (os != null) {
				os.close();
			}
		}
		return null;
	}
}
