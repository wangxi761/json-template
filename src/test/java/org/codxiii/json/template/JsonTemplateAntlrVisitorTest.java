package org.codxiii.json.template;

import org.codxiii.json.template.ast.JsonTemplateAntlrVisitor;
import org.codxiii.json.template.ast.JsonTemplateNode;
import org.codxiii.json.template.ast.json.*;
import org.codxiii.json.template.parser.JsonTemplateParser;
import org.codxiii.json.template.parser.JsonTemplateParserBaseVisitor;
import org.codxiii.json.template.test.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;
import static org.codxiii.json.template.parser.JsonTemplateParser.*;

/**
 * ArrayNode
 * BoolNode
 * NullNode
 * NumberNode
 * ObjectNode
 * TextInterpolationNode
 * TextNode
 * VarNode
 */
class JsonTemplateAntlrVisitorTest {
	private JsonTemplateAntlrVisitor jsonTemplateAntlrVisitor;
	
	@BeforeEach
	void setUp() {
		jsonTemplateAntlrVisitor = new JsonTemplateAntlrVisitor();
	}
	
	@Test
	public void shouldGenerateObjectNode() {
		JsonTemplateParser parser = TestUtil.createParser(TestUtil.getResourceFromClassPath("test.json"));
		JsonTemplateParser.Json_templateContext json = parser.json_template();
		json.accept(new JsonTemplateParserBaseVisitor<Void>() {
			@Override
			public Void visitObj(ObjContext ctx) {
				JsonTemplateNode<?> jsonTemplateNode = jsonTemplateAntlrVisitor.visitObj(ctx);
				then(jsonTemplateNode)
					.isInstanceOf(ObjectNode.class);
				return null;
			}
		});
	}
	
	@Test
	public void shouldGenerateArrayNode() {
		JsonTemplateParser parser = TestUtil.createParser(TestUtil.getResourceFromClassPath("test.json"));
		JsonTemplateParser.Json_templateContext json = parser.json_template();
		json.accept(new JsonTemplateParserBaseVisitor<Void>() {
			@Override
			public Void visitArr(ArrContext ctx) {
				JsonTemplateNode<?> jsonTemplateNode = jsonTemplateAntlrVisitor.visitArr(ctx);
				then(jsonTemplateNode)
					.isInstanceOf(ArrayNode.class);
				return null;
			}
		});
	}
	
	@Test
	public void shouldGenerateTextNode() {
		JsonTemplateParser parser = TestUtil.createParser(TestUtil.getResourceFromClassPath("test.json"));
		JsonTemplateParser.Json_templateContext json = parser.json_template();
		json.accept(new JsonTemplateParserBaseVisitor<Void>() {
			@Override
			public Void visitStr(StrContext ctx) {
				JsonTemplateNode<?> jsonTemplateNode = jsonTemplateAntlrVisitor.visitStr(ctx);
				then(jsonTemplateNode)
					.isInstanceOfAny(TextNode.class, TextInterpolationNode.class);
				return null;
			}
		});
	}
	
	@Test
	public void shouldGenerateVarNode() {
		JsonTemplateParser parser = TestUtil.createParser(TestUtil.getResourceFromClassPath("test.json"));
		JsonTemplateParser.Json_templateContext json = parser.json_template();
		json.accept(new JsonTemplateParserBaseVisitor<Void>() {
			@Override
			public Void visitVar(VarContext ctx) {
				JsonTemplateNode<?> jsonTemplateNode = jsonTemplateAntlrVisitor.visitVar(ctx);
				then(jsonTemplateNode)
					.isInstanceOf(VarNode.class);
				return null;
			}
		});
	}
	
	@Test
	public void shouldGenerateBoolNode() {
		JsonTemplateParser parser = TestUtil.createParser(TestUtil.getResourceFromClassPath("test.json"));
		JsonTemplateParser.Json_templateContext json = parser.json_template();
		json.accept(new JsonTemplateParserBaseVisitor<Void>() {
			@Override
			public Void visitBool(BoolContext ctx) {
				JsonTemplateNode<?> jsonTemplateNode = jsonTemplateAntlrVisitor.visitBool(ctx);
				then(jsonTemplateNode)
					.isInstanceOf(BoolNode.class);
				return null;
			}
		});
	}
	
	@Test
	public void shouldGenerateNullNode() {
		JsonTemplateParser parser = TestUtil.createParser(TestUtil.getResourceFromClassPath("test.json"));
		JsonTemplateParser.Json_templateContext json = parser.json_template();
		json.accept(new JsonTemplateParserBaseVisitor<Void>() {
			@Override
			public Void visitNull(NullContext ctx) {
				JsonTemplateNode<?> jsonTemplateNode = jsonTemplateAntlrVisitor.visitNull(ctx);
				then(jsonTemplateNode)
					.isInstanceOf(NullNode.class);
				return null;
			}
		});
	}
	
	@Test
	public void shouldGenerateNumberNode() {
		JsonTemplateParser parser = TestUtil.createParser(TestUtil.getResourceFromClassPath("test.json"));
		JsonTemplateParser.Json_templateContext json = parser.json_template();
		json.accept(new JsonTemplateParserBaseVisitor<Void>() {
			@Override
			public Void visitNum(NumContext ctx) {
				JsonTemplateNode<?> jsonTemplateNode = jsonTemplateAntlrVisitor.visitNum(ctx);
				then(jsonTemplateNode)
					.isInstanceOf(NumberNode.class);
				return null;
			}
		});
	}
	
	
}