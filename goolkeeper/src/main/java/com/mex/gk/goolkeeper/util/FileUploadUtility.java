package com.mex.gk.goolkeeper.util;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.BasicLogger;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtility {

	
	private static final String ABS_PATH="D:\\Miguel_Arriaga\\Dropbox\\IDE\\workspace-sts-3.8.4\\goal-keeper\\goolkeeper\\src\\main\\webapp\\assets\\images\\";
	private static String REAL_PATH = "";
	
	private static final BasicLogger logger = LoggerFactory.logger(FileUploadUtility.class);
	
	public static void uploadFile(HttpServletRequest request, MultipartFile file, String code) {
		//
		REAL_PATH = request.getSession().getServletContext().getRealPath("/assets/images/");
		logger.debug(REAL_PATH);	
		logger.debug(REAL_PATH);
		
		if (!new File(ABS_PATH).exists()) {
			new File(ABS_PATH).mkdirs();			
		}
		if (!new File(REAL_PATH).exists()) {
			new File(REAL_PATH).mkdirs();			
		}
		try {
			file.transferTo(new File(REAL_PATH+code+".jpg"));
			file.transferTo(new File(ABS_PATH+code+".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


	
}
