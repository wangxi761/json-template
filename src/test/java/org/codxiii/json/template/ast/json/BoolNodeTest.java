package org.codxiii.json.template.ast.json;

import static org.junit.jupiter.api.Assertions.*;

import org.codxiii.json.template.ast.JsonTemplateNodeType;
import org.junit.jupiter.api.Test;

class BoolNodeTest {
	
	@Test
	void getNodeTypeReturnsBool() {
		BoolNode node = new BoolNode(true, 0, 1);
		assertEquals(JsonTemplateNodeType.BOOL, node.getNodeType());
	}
	
	@Test
	void getValueReturnsCorrectBoolean() {
		BoolNode node = new BoolNode(true, 0, 1);
		assertTrue(node.getValue());
	}
	
	@Test
	void getValueReturnsCorrectBooleanWhenFalse() {
		BoolNode node = new BoolNode(false, 0, 1);
		assertFalse(node.getValue());
	}
	
	@Test
	void getStartReturnsCorrectStart() {
		BoolNode node = new BoolNode(true, 0, 1);
		assertEquals(0, node.getStart());
	}
	
	@Test
	void getEndReturnsCorrectEnd() {
		BoolNode node = new BoolNode(true, 0, 1);
		assertEquals(1, node.getEnd());
	}
}