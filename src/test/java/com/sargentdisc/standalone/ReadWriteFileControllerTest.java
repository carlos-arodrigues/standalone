package com.sargentdisc.standalone;

import java.io.File;
import java.nio.file.Files;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sargentdisc.domain.model.userfile.UserFile;
import com.sargentdisc.standalone.service.StandaloneService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReadWriteFileControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	StandaloneService service;

	@Test
	public void upload_file_without_commas_with_spaces() throws Exception {

		String fileName = "DocumentTest.txt";
		ClassLoader classLoader = this.getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		UserFile userFile = new ObjectMapper().readValue(new String(Files.readAllBytes(file.toPath())), UserFile.class);
		UserFile newFile = service.processFile(userFile);
		Assert.assertNotNull(newFile);
	}

}
