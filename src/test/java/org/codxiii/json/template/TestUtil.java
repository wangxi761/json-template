package org.codxiii.json.template;

import lombok.SneakyThrows;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.codxiii.json.template.parser.JsonTemplateLexer;
import org.codxiii.json.template.parser.JsonTemplateParser;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class TestUtil {
	
	
	@SneakyThrows
	public static String getTestResource(String name) {
		return Files.readString(Path.of(Objects.requireNonNull(TestUtil.class.getResource("/" + name)).toURI()));
	}
	
	public static JsonTemplateParser createParser(String source) {
		return new JsonTemplateParser(new CommonTokenStream(new JsonTemplateLexer(CharStreams.fromString(source))));
	}
	
	
}
