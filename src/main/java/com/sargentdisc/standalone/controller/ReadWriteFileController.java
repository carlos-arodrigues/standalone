package com.sargentdisc.standalone.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sargentdisc.domain.model.userfile.UserFile;
import com.sargentdisc.standalone.service.StandaloneService;

@RestController
public class ReadWriteFileController {
	
	@Autowired
	StandaloneService service;

	@RequestMapping("/readfile")
	public String readFile() {
		return "uploadForm";
	}
	
	@RequestMapping(value = "/processfile", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public UserFile processFile() throws IOException {
		String fileName = "DocumentTest.txt";
		ClassLoader classLoader = this.getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		UserFile userFile = new ObjectMapper().readValue(new String(Files.readAllBytes(file.toPath())), UserFile.class);
		return service.processFile(userFile);
	}	
	
	@RequestMapping(value = "/uploadfile", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE})
	@ResponseStatus(HttpStatus.CREATED)
	public void uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
		UserFile userFile = new ObjectMapper().readValue(new String(file.getBytes()), UserFile.class);
		service.processFile(userFile);
	}	

}