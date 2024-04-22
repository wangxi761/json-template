package org.codxiii.json.template;

import org.codxiii.json.template.format.JsonTemplateFormatter;
import org.codxiii.json.template.format.JsonTemplateFormatterConfig;
import org.codxiii.json.template.test.TestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.*;

class JsonTemplateFormatterTest {
	
	@Test
	public void testObjectFormat() {
		String originalContent = TestUtil.getContentFromPath(TestUtil.getResourcePathFromClassPath("test.json"));
		String expectedContent = TestUtil.getContentFromPath(TestUtil.getResourcePathFromClassPath("test_formatted.json"));
		
		String formatted = new JsonTemplate().format(originalContent);
		then(formatted).isNotEqualTo(expectedContent);
	}
	
	
	@Test
	public void testAllowTrailComma() {
		String originalContent = "{\"a\":1}";
		JsonTemplate jsonTemplate = new JsonTemplate();
		String formatted = jsonTemplate.format(originalContent);
		String expected = TestUtil.getResourceFromClassPath("format/testAllowTrailComma_formatted.json");
		then(formatted).isEqualTo(expected);
	}
	
	@Test
	public void testDoNotAllowTrailComma() {
		String originalContent = "{\"a\":1}";
		JsonTemplate jsonTemplate = new JsonTemplate().setConfig(JsonTemplateFormatterConfig.createDefaultConfig().setAllowTrailingComma(false));
		String formatted = jsonTemplate.format(originalContent);
		String expected = TestUtil.getResourceFromClassPath("format/testDoNotAllowTrailComma_formatted.json");
		then(formatted).isEqualTo(expected);
	}
}