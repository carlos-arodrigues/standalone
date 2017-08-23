package com.sargentdisc.standalone.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sargentdisc.domain.model.userfile.UserFile;
import com.sargentdisc.standalone.service.StandaloneService;

@Controller
public class ReadWriteFileController {
	
	@Autowired
	StandaloneService service;

	@RequestMapping("/readfile")
	public String readFile() {
		return "uploadForm";
	}
	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
		UserFile userFile = new ObjectMapper().readValue(new String(file.getBytes()), UserFile.class);
		service.processFile(userFile);
		return "uploadForm";
	}	

}