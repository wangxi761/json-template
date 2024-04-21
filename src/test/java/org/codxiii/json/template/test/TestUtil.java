package org.codxiii.json.template.test;

import lombok.SneakyThrows;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.codxiii.json.template.parser.JsonTemplateLexer;
import org.codxiii.json.template.parser.JsonTemplateParser;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestUtil {
	
	
	@SneakyThrows
	public static String getResourceFromClassPath(String name) {
		return Files.readString(Path.of(Objects.requireNonNull(TestUtil.class.getResource("/" + name)).toURI()));
	}
	
	public static JsonTemplateParser createParser(String source) {
		return new JsonTemplateParser(new CommonTokenStream(new JsonTemplateLexer(CharStreams.fromString(source))));
	}
	
	@SneakyThrows
	public static Path getResourcePathFromClassPath(String name) {
		return Path.of(Objects.requireNonNull(TestUtil.class.getResource("/" + name)).toURI());
	}
	
	@SneakyThrows
	public static List<Path> getResourcePathsFromClassPathDir(String dir) {
		URL resource = Objects.requireNonNull(TestUtil.class.getResource("/" + dir));
		Path dirPath = Paths.get(resource.toURI());
		if (!Files.isDirectory(dirPath)) {
			throw new IllegalArgumentException("Not a directory: " + dirPath);
		}
		try(Stream<Path> list = Files.list(dirPath)){
			return list.collect(Collectors.toList());
		}
	}
	
	@SneakyThrows
	public static String getContentFromPath(Path path) {
		return Files.readString(path);
	}
}
