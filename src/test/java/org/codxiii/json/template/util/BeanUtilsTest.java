package org.codxiii.json.template.util;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class BeanUtilsTest {
	
	@Test
	void getPropertyFromMap() {
		Map<String, Object> map = new HashMap<>();
		map.put("key", "value");
		assertThat(BeanUtils.getProperty(map, "key")).isEqualTo("value");
	}
	
	@Test
	void getPropertyUsingMethod() {
		Person person = new Person();
		person.setName("John");
		assertThat(BeanUtils.getProperty(person, "name")).isEqualTo("John");
	}
	
	@Test
	void getPropertyUsingField() {
		Person person = new Person();
		person.age = 30;
		assertThat(BeanUtils.getProperty(person, "age")).isEqualTo(30);
	}
	
	@Test
	void getPropertyWithNonexistentField() {
		Person person = new Person();
		assertThatThrownBy(() -> BeanUtils.getProperty(person, "nonexistent"))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("No accessible field/method");
	}
	
	@Test
	void getPropertyFromNullObject() {
		assertThat(BeanUtils.getProperty(null, "anything")).isNull();
	}
	
	
	@Getter
	@Setter
	public static class Person {
		private String name;
		public int age;
	}
	
	@Getter
	@Setter
	public static class Employee extends Person {
		private String department;
	}
	
}