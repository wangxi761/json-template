package org.codxiii.json.template.ast.json;

import static org.junit.jupiter.api.Assertions.*;

import org.codxiii.json.template.ast.JsonTemplateNode;
import org.codxiii.json.template.ast.JsonTemplateNodeType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class ArrayNodeTest {
	
	@Test
	void getNodeTypeReturnsArray() {
		ArrayNode node = new ArrayNode(Collections.emptyList(), 0, 1);
		assertEquals(JsonTemplateNodeType.ARRAY, node.getNodeType());
	}
	
	@Test
	void addAddsElementToEmptyArray() {
		ArrayNode node = new ArrayNode(new ArrayList<>(), 0, 1);
		JsonTemplateNode<?> element = new JsonTemplateNode<>(0, 1) {
			@Override
			public JsonTemplateNodeType getNodeType() {
				return JsonTemplateNodeType.TEXT;
			}
		};
		node.add(element);
		assertFalse(node.isEmpty());
		assertEquals(1, node.size());
	}
	
	@Test
	void iteratorIteratesOverMultipleElements() {
		JsonTemplateNode<?> firstElement = new JsonTemplateNode<>(0, 1) {
			@Override
			public JsonTemplateNodeType getNodeType() {
				return JsonTemplateNodeType.TEXT;
			}
		};
		JsonTemplateNode<?> secondElement = new JsonTemplateNode<>(1, 2) {
			@Override
			public JsonTemplateNodeType getNodeType() {
				return JsonTemplateNodeType.NUMBER;
			}
		};
		ArrayNode node = new ArrayNode(Arrays.asList(firstElement, secondElement), 0, 2);
		int count = 0;
		for (JsonTemplateNode<?> ignored : node) {
			count++;
		}
		assertEquals(2, count);
	}
	
	@Test
	void isEmptyReturnsTrueForEmptyArray() {
		ArrayNode node = new ArrayNode(Collections.emptyList(), 0, 1);
		assertTrue(node.isEmpty());
	}
	
	@Test
	void sizeReturnsCorrectSizeForNonEmptyArray() {
		JsonTemplateNode<?> element = new JsonTemplateNode<>(0, 1) {
			@Override
			public JsonTemplateNodeType getNodeType() {
				return JsonTemplateNodeType.BOOL;
			}
		};
		ArrayNode node = new ArrayNode(Collections.singletonList(element), 0, 1);
		assertEquals(1, node.size());
	}
	
}