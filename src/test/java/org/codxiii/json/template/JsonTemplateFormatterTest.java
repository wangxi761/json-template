package org.codxiii.json.template;

import org.codxiii.json.template.format.JsonTemplateFormatter;
import org.codxiii.json.template.test.TestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.*;

class JsonTemplateFormatterTest {
	
	@Test
	public void testObjectFormat() {
		String originalContent = TestUtil.getContentFromPath(TestUtil.getResourcePathFromClassPath("test.json"));
		String expectedContent = TestUtil.getContentFromPath(TestUtil.getResourcePathFromClassPath("test_formatted.json"));
		
		String formatted = JsonTemplateFormatter.format(originalContent);
		then(formatted).isNotEqualTo(expectedContent);
	}
	
}