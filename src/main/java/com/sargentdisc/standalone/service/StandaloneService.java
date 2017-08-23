package com.sargentdisc.standalone.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sargentdisc.domain.model.userfile.UserFile;


@Service
public class StandaloneService {
	
	public UserFile processFile(UserFile userFile){
		userFile.removeCommasWithSpace();
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<?> requestEntity = new HttpEntity<Object>(userFile, requestHeaders);
		ResponseEntity<UserFile> response = new RestTemplate().exchange("http://localhost:8080/sargent-disc/api/v1/userfile/", HttpMethod.POST, requestEntity, UserFile.class);
		return response.getBody();
	}

}
