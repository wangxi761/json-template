# JSON Template Syntax Library

## Introduction
The `json-template` library extends the standard JSON format to include variable interpolation and optional trailing commas in objects and arrays. This format is ideal for dynamically generating configuration files, API responses, and other data structures that require flexibility and customization.

## Features
- **Optional Commas**: Allows an optional trailing comma at the end of lists and objects, facilitating easier modifications and extensions.
- **Variable Interpolation**: Supports embedding variables directly into JSON using the `${var}` syntax, applicable both in keys and values.
- **Flexibility**: Designed to cater to scenarios requiring customized JSON data outputs.

## Quick Start
1. **Basic Usage**:
   Here is a simple example of how you can use the `json-template` to include variables in your JSON:
    ```
    {
        "name": "John Doe",
        "welcome_message": "Hello, ${name}!",
        "age": ${age},
        "is_${status}": true,
    }
    ```
   This example demonstrates how to interpolate a variable into a JSON value.

2. **Running the Example**:
   Compile and run your `json-template` parser to see how the template transforms into actual JSON data.

## Documentation

### Variable Interpolation

Variable interpolation in the `json-template` library allows you to dynamically insert values into your JSON templates at both the value level and key level, enabling highly customizable JSON configurations.

#### Syntax
- **Value Interpolation**:
  Use the syntax `${variableName}` within string values to interpolate a variable. The parser will automatically replace `${variableName}` with the value of the variable at runtime.

  Example:
  ```json
  {
      "greeting": "Hello, ${username}!"
  }
  ```
  Here, `${username}` will be substituted with the actual user's name when the JSON is processed.

- **Key Interpolation**:
  Variables can also be interpolated into object keys, which allows keys to be dynamically generated based on variable values.

  Example:
  ```json
  {
      "${dynamicKey}": "This is a dynamically named key."
  }
  ```
  If `dynamicKey` is set to `UserId`, the resulting JSON key will be `UserId`.

- **Direct Value Interpolation**:
  You can directly insert numeric or boolean values through interpolation, making the template dynamically responsive to numeric or logical data.

  Example:
  ```
  {
      "name": "John Doe",
      "age": ${age}
  }
  ```
  In this case, `${age}` will be replaced by the numerical value assigned to `age`.

- **Key Fragment Interpolation**:
  Part of a key can be dynamically defined using variable interpolation, enabling more complex JSON structures based on variable states.

  Example:
  ```json
  {
      "is_${status}": true
  }
  ```
  If `status` equals `active`, the key would be `is_active`.

#### Rules
1. **Variable Scope**: Ensure that all variables are defined within the scope where the JSON template is processed, or are explicitly passed to the template processor.
2. **Fallback Values**: You can specify default values for variables to handle cases where a variable might not be set.
3. **Escaping**: Use escaping mechanisms defined by your library to include literal `${}` in your JSON without being processed as a variable.

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
```json
{"object": {"id": "1","name": "John Doe","email": ""},
  "array": [{"id": "1","name": "John Doe","email": ""},{"id": "2","name": "Jane Doe","email": ""}],
  "string": "Hello, World!","number": 42,"boolean": true,"null": null,
  "var":${var},
"text_interpolation${c}": "Hello, ${object}!",
}
```
```json
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


