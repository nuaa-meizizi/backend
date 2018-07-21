package com.nuaa.health.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nuaa.health.util.GenericJsonResult;
import com.nuaa.health.util.HResult;

@RestController
@RequestMapping(value = "file")
public class FileController {

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public GenericJsonResult<String> upload(@RequestParam("file") MultipartFile file,
			@RequestParam(value = "token", required = true) String token, HttpServletRequest request) {

		GenericJsonResult<String> result = new GenericJsonResult<String>(HResult.S_OK);
		String uploadDir = request.getSession().getServletContext().getRealPath("/")+"upload/";
		File dir = new File(uploadDir);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		if (!file.isEmpty()) {
			try {
				BufferedOutputStream out = new BufferedOutputStream(
						new FileOutputStream(new File(uploadDir+file.getOriginalFilename())));
				out.write(file.getBytes());
				out.flush();
				out.close();
			} catch (Exception e) {
				result.setStatus(HResult.E_UPLOAD_FAIL);
			}
		} else {
			result.setStatus(HResult.E_UPLOAD_FILE_EMPTY);
		}
		return result;
	}
}
