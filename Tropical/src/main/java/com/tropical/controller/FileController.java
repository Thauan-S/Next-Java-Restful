package com.tropical.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tropical.data.vo.v1.UploadFileResponseVO;
import com.tropical.services.FileStorageServices;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Endpoint de arquivos")
@RestController
@RequestMapping("/api/file/v1")
public class FileController {
	
	@Autowired
	private FileStorageServices service;
	
	@PostMapping("/uploadFile")
	public UploadFileResponseVO uploadFile(@RequestParam("file")MultipartFile file) {
		var fileName=service.storeFile(file);
		
		String fileDownloadUri=ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/api/file/v1/downloadFile/")
				.path(fileName)
				.toUriString();
		return new UploadFileResponseVO(fileName,fileDownloadUri,file.getContentType(),file.getSize());
	}
}
