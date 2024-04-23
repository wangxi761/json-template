package org.codxiii.json.template.ast;

import java.util.Map;

public interface IRender {
	
	String render(Map<String, Object> binding);
}
