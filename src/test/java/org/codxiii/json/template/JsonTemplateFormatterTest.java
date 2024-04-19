package org.codxiii.json.template;

import lombok.SneakyThrows;
import org.codxiii.json.template.format.JsonTemplateFormatter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.stream.Collectors;

class JsonTemplateFormatterTest {
	
	@AfterEach
	void tearDown() {
		System.out.println("==============================================");
	}
	
	@Test
	@SneakyThrows
	void formatString() {
		InputStream input = this.getClass().getClassLoader().getResourceAsStream("test.json");
		assert input != null;
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))) {
			String inputStr = reader.lines().collect(Collectors.joining("\n"));
			System.out.println(JsonTemplateFormatter.format(inputStr));
		}
	}
	
	@Test
	void formatStream() {
		InputStream input = this.getClass().getClassLoader().getResourceAsStream("test.json");
		assert input != null;
		System.out.println(JsonTemplateFormatter.format(input));
	}
	
	@Test
	@SneakyThrows
	void formatPath() {
		URL resource = this.getClass().getClassLoader().getResource("test.json");
		assert resource != null;
		System.out.println(JsonTemplateFormatter.format(Paths.get(resource.toURI())));
	}
}