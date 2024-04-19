package org.codxiii.json.template;

import lombok.SneakyThrows;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.codxiii.json.template.parser.JSONLexer;
import org.codxiii.json.template.parser.JSONParser;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class TestUtil {
	
	
	@SneakyThrows
	public static String getTestResource(String name) {
		return Files.readString(Path.of(Objects.requireNonNull(TestUtil.class.getResource("/" + name)).toURI()));
	}
	
	public static JSONParser createParser(String source) {
		return new JSONParser(new CommonTokenStream(new JSONLexer(CharStreams.fromString(source))));
	}
	
	
}
