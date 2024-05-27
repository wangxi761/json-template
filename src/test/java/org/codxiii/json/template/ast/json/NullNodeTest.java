package org.codxiii.json.template.ast.json;

import static org.junit.jupiter.api.Assertions.*;

import org.codxiii.json.template.ast.JsonTemplateNodeType;
import org.junit.jupiter.api.Test;

class NullNodeTest {
	
	@Test
	void getNodeTypeReturnsNull() {
		NullNode node = new NullNode(0, 1);
		assertEquals(JsonTemplateNodeType.NULL, node.getNodeType());
	}
	
	@Test
	void toRawStringReturnsNullString() {
		NullNode node = new NullNode(0, 1);
		assertEquals("null", node.toRawString());
	}
}