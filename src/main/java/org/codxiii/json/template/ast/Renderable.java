package org.codxiii.json.template.ast;

import java.util.Map;

public interface Renderable {
	
	String render(Map<String, Object> binding);
}
