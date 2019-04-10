package com.retask.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.retask.game.message.response.AWSUploadResponse;
import com.retask.game.services.aws.S3Services;

@RestController
public class AWSUploadFileController {

	@Autowired
	S3Services s3Services;

	@Value("${gkz.s3.bucket}")
	private String bucket;

	@Value("${gkz.s3.region}")
	private String region;

	@Value("${gkz.s3.url}")
	private String url;

	@PostMapping("/api/file/upload")
	public AWSUploadResponse uploadMultipartFile(@RequestParam("file") MultipartFile file) {

		String tempUrl = url.replace("{gkz.s3.region}", region);

		String keyName = file.getOriginalFilename();
		tempUrl = tempUrl + bucket + "/" + keyName;

		s3Services.uploadFile(keyName, file);
		return (new AWSUploadResponse(0, "Upload Successful", tempUrl));
	}
}