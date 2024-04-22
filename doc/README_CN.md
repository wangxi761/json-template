# JSON Template 语法库

[english](../README.md)

## 简介
The `json-template` library extends the standard JSON format to include variable interpolation and optional trailing commas in objects and arrays. This format is ideal for dynamically generating configuration files, API responses, and other data structures that require flexibility and customization.

## 功能
- **Optional Commas**: Allows an optional trailing comma at the end of lists and objects, facilitating easier modifications and extensions.
- **Variable Interpolation**: Supports embedding variables directly into JSON using the `${var}` syntax, applicable both in keys and values.
- **Flexibility**: Designed to cater to scenarios requiring customized JSON data outputs.

## 语法规则

### 变量插值

Variable interpolation in the `json-template` library allows you to dynamically insert values into your JSON templates at both the value level and key level, enabling highly customizable JSON configurations.

#### 插值类型

- **文本插值**:
  Use the syntax `${variableName}` within string values to interpolate a variable. The parser will automatically replace `${variableName}` with the value of the variable at runtime.

  Example:
  ```json
  {
      "greeting": "Hello, ${username}!"
  }
  ```
  Here, `${username}` will be substituted with the actual user's name when the JSON is processed.

- **节点插值**:
  You can directly insert numeric or boolean values through interpolation, making the template dynamically responsive to numeric or logical data.

  Example:
  ```
  {
      "name": "John Doe",
      "age": ${age}
  }
  ```
  In this case, `${age}` will be replaced by the numerical value assigned to `age`.

#### Rules
1. **Variable Scope**: Ensure that all variables are defined within the scope where the JSON template is processed, or are explicitly passed to the template processor.
2. **Fallback Values**: You can specify default values for variables to handle cases where a variable might not be set.
3. **Escaping**: Use escaping mechanisms defined by your library to include literal `\${}` in your JSON without being processed as a variable.

#### Considerations
- Variable names should be valid identifiers and should not conflict with JSON syntax or reserved keywords.
- Carefully handle user-provided variable values to avoid unintended modifications to the JSON structure, particularly in security-sensitive contexts.

#### Usage Tips
- Maintain clear and descriptive variable naming to ensure the readability and maintainability of your JSON templates.
- Leverage the full power of your library's features by combining node and variable interpolation for dynamic JSON generation.

### Formatting

```java
JsonTemplate jsonTemplate = new JsonTemplate();
String formatted = jsonTemplate.format(jsonTemplateString);
```
this is an example:
```
{"object": {"id": "1","name": "John Doe","email": ""},
  "array": [{"id": "1","name": "John Doe","email": ""},{"id": "2","name": "Jane Doe","email": ""}],
  "string": "Hello, World!","number": 42,"boolean": true,"null": null,
  "var":${var},
"text_interpolation${c}": "Hello, ${object}!",
}
```
```
{
  "object": {
    "id": "1",
    "name": "John Doe",
    "email": "",
  },
  "array": [
    {
      "id": "1",
      "name": "John Doe",
      "email": "",
    },
    {
      "id": "2",
      "name": "Jane Doe",
      "email": "",
    },
  ],
  "string": "Hello, World!",
  "number": 42,
  "boolean": true,
  "null": null,
  "var": ${var},
  "text_interpolation${c}": "Hello, ${object}!",
}
```

## Contributing
Contributions are welcome! Feel free to submit Pull Requests or open Issues to suggest new features or report bugs. Please make sure to follow the contributing guidelines.


