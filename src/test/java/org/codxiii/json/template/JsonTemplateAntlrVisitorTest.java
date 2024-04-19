package org.codxiii.json.template;

import org.codxiii.json.template.ast.JsonTemplateAntlrVisitor;
import org.codxiii.json.template.ast.JsonTemplateNode;
import org.codxiii.json.template.ast.json.*;
import org.codxiii.json.template.parser.JSONBaseVisitor;
import org.codxiii.json.template.parser.JSONParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class JsonTemplateAntlrVisitorTest {
	private JsonTemplateAntlrVisitor jsonTemplateAntlrVisitor;
	
	@BeforeEach
	void setUp() {
		jsonTemplateAntlrVisitor = new JsonTemplateAntlrVisitor();
	}
	
	@Test
	public void shouldGenerateObjectNode() {
		JSONParser parser = TestUtil.createParser(TestUtil.getTestResource("test.json"));
		JSONParser.JsonContext json = parser.json();
		json.accept(new JSONBaseVisitor<Void>() {
			@Override
			public Void visitObj(JSONParser.ObjContext ctx) {
				JsonTemplateNode<?> jsonTemplateNode = jsonTemplateAntlrVisitor.visitObj(ctx);
				assertInstanceOf(ObjectNode.class, jsonTemplateNode);
				return null;
			}
		});
	}
	
	@Test
	public void shouldGenerateArrayNode() {
		JSONParser parser = TestUtil.createParser(TestUtil.getTestResource("test.json"));
		JSONParser.JsonContext json = parser.json();
		json.accept(new JSONBaseVisitor<Void>() {
			@Override
			public Void visitArr(JSONParser.ArrContext ctx) {
				JsonTemplateNode<?> jsonTemplateNode = jsonTemplateAntlrVisitor.visitArr(ctx);
				assertInstanceOf(ArrayNode.class, jsonTemplateNode);
				return null;
			}
		});
	}
	
	@Test
	public void shouldGenerateTextNode() {
		JSONParser parser = TestUtil.createParser(TestUtil.getTestResource("test.json"));
		JSONParser.JsonContext json = parser.json();
		json.accept(new JSONBaseVisitor<Void>() {
			@Override
			public Void visitStr(JSONParser.StrContext ctx) {
				JsonTemplateNode<?> jsonTemplateNode = jsonTemplateAntlrVisitor.visitStr(ctx);
				assertInstanceOf(TextNode.class, jsonTemplateNode);
				return null;
			}
		});
	}
	
	@Test
	public void shouldGenerateVarNode() {
		JSONParser parser = TestUtil.createParser(TestUtil.getTestResource("test.json"));
		JSONParser.JsonContext json = parser.json();
		json.accept(new JSONBaseVisitor<Void>() {
			@Override
			public Void visitVar(JSONParser.VarContext ctx) {
				JsonTemplateNode<?> jsonTemplateNode = jsonTemplateAntlrVisitor.visitVar(ctx);
				assertInstanceOf(VarNode.class, jsonTemplateNode);
				return null;
			}
		});
	}
	
	@Test
	public void shouldGenerateBoolNode() {
		JSONParser parser = TestUtil.createParser(TestUtil.getTestResource("test.json"));
		JSONParser.JsonContext json = parser.json();
		json.accept(new JSONBaseVisitor<Void>() {
			@Override
			public Void visitBool(JSONParser.BoolContext ctx) {
				JsonTemplateNode<?> jsonTemplateNode = jsonTemplateAntlrVisitor.visitBool(ctx);
				assertInstanceOf(BoolNode.class, jsonTemplateNode);
				return null;
			}
		});
	}
	
	@Test
	public void shouldGenerateNumberNode() {
		JSONParser parser = TestUtil.createParser(TestUtil.getTestResource("test.json"));
		JSONParser.JsonContext json = parser.json();
		json.accept(new JSONBaseVisitor<Void>() {
			@Override
			public Void visitNum(JSONParser.NumContext ctx) {
				JsonTemplateNode<?> jsonTemplateNode = jsonTemplateAntlrVisitor.visitNum(ctx);
				assertInstanceOf(NumberNode.class, jsonTemplateNode);
				return null;
			}
		});
	}
	
	@Test
	public void shouldGenerateNullNode() {
		JSONParser parser = TestUtil.createParser(TestUtil.getTestResource("test.json"));
		JSONParser.JsonContext json = parser.json();
		json.accept(new JSONBaseVisitor<Void>() {
			@Override
			public Void visitNull(JSONParser.NullContext ctx) {
				JsonTemplateNode<?> jsonTemplateNode = jsonTemplateAntlrVisitor.visitNull(ctx);
				assertInstanceOf(NullNode.class, jsonTemplateNode);
				return null;
			}
		});
	}
}