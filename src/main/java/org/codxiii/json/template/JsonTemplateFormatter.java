package org.codxiii.json.template;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.SneakyThrows;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.codxiii.json.template.parser.JSONLexer;
import org.codxiii.json.template.parser.JSONParser;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class JsonTemplateFormatter {
	@SneakyThrows
	public static String format(String input) {
		JSONParser parser = new JSONParser(new CommonTokenStream(new JSONLexer(CharStreams.fromString(input))));
		JsonNode node = new JsonNodeGenerator().visit(parser.json());
		return node.toPrettyString();
	}
	
	@SneakyThrows
	public static String format(InputStream input) {
		JSONParser parser = new JSONParser(new CommonTokenStream(new JSONLexer(CharStreams.fromStream(input, StandardCharsets.UTF_8))));
		JsonNode node = new JsonNodeGenerator().visit(parser.json());
		return node.toPrettyString();
	}
	
	@SneakyThrows
	public static String format(Path path) {
		JSONParser parser = new JSONParser(new CommonTokenStream(new JSONLexer(CharStreams.fromPath(path,StandardCharsets.UTF_8))));
		JsonNode node = new JsonNodeGenerator().visit(parser.json());
		return node.toPrettyString();
	}
}
