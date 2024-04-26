package org.codxiii.json.template.util;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ClassCacheTest {
	@Test
	void evalPropertyUsingInheritedMethod() {
		Employee employee = new Employee();
		employee.setDepartment("IT");
		assertThat(ClassCache.getProperty(employee, "department")).isEqualTo("IT");
	}
	
	@Test
	void evalPropertyFromSuperclass() {
		Employee employee = new Employee();
		employee.setName("Jane");
		assertThat(ClassCache.getProperty(employee, "name")).isEqualTo("Jane");
	}
	
	@Test
	void evalPropertyUsingField() {
		Employee employee = new Employee();
		employee.age = 45;
		assertThat(ClassCache.getProperty(employee, "age")).isEqualTo(45);
	}
	
	@Test
	void evalNonexistentProperty() {
		Employee employee = new Employee();
		assertThatThrownBy(() -> ClassCache.getProperty(employee, "salary"))
			.isInstanceOf(RuntimeException.class)
			.hasMessageContaining("No accessible field/method");
	}
	
	@Getter
	@Setter
	public static class Person {
		private String name;
		public int age;
	}
	
	@Getter
	@Setter
	public static class Employee extends BeanUtilsTest.Person {
		private String department;
	}
}