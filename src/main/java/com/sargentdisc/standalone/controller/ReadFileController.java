package com.sargentdisc.standalone.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/readfile")
public class ReadFileController {


	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public void saveFile(@PathVariable("id") Long id) throws Exception {

	}

}
