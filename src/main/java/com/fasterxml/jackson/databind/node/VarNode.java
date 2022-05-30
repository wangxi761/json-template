package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.node.ValueNode;

import java.io.IOException;
import java.util.Objects;

public class VarNode extends ValueNode {
	private String _value;
	
	private String varName;
	
	public VarNode(String value) {
		this._value = value;
		this.varName = value.substring(2, value.length() - 1);
	}
	
	@Override
	public int hashCode() {
		return this._value.hashCode();
	}
	
	@Override
	public JsonToken asToken() {
		return JsonToken.VALUE_STRING;
	}
	
	@Override
	public void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		if (this._value == null) {
			jsonGenerator.writeNull();
		} else {
			
			jsonGenerator.writeNumber(this._value);
		}
	}
	
	@Override
	public JsonNodeType getNodeType() {
		return JsonNodeType.STRING;
	}
	
	@Override
	public String asText() {
		return this._value;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (o == null) {
			return false;
		} else {
			return o instanceof VarNode && Objects.equals(((VarNode) o)._value, this._value);
		}
	}
	
	public String getVarName() {
		return varName;
	}
}
