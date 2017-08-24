package com.sargentdisc.standalone;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.sargentdisc.domain.model.userfile.UserFile;

@RunWith(SpringRunner.class)
public class ReadWriteFileControllerTest extends SpringTest {

	@Test
	public void should_process_the_file_provided() throws IOException {
		configureUrl("/processfile");
		ResponseEntity<UserFile> response = restTemplate.postForEntity(url, null, UserFile.class);
		Assert.assertThat(response.getStatusCode(), is(equalTo(HttpStatus.CREATED)));
	}

	@Test
	public void upload_file_without_commas_with_spaces() throws IOException {
		configureUrl("/uploadfile");
		String fileName = "DocumentTest.txt";

		MultiValueMap<String, Object> multipartMap = new LinkedMultiValueMap<>();
		multipartMap.add("file", new org.springframework.core.io.ClassPathResource(fileName));

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "multipart/form-data");

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(multipartMap, headers), String.class, "");
		Assert.assertThat(response.getStatusCode(), is(equalTo(HttpStatus.CREATED)));
	}

}
