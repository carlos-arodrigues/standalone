package com.sargentdisc.standalone;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.nio.file.Files;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReadWriteFileControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void uploadFile() throws Exception {

		String fileName = "DocumentTest.txt";
		ClassLoader classLoader = this.getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());

		MockMultipartFile fileMock = new MockMultipartFile("DocumentTest.txt", Files.readAllBytes(file.toPath()));

		mvc.perform(MockMvcRequestBuilders.fileUpload("http://localhost:8081/standalone/uploadFile")
				.file(fileMock)
				.contentType(MediaType.MULTIPART_FORM_DATA))
				.andExpect(status().isCreated());
	}

}
