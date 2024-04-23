# JSON Template Syntax Library

![Static Badge](https://img.shields.io/badge/language-json--template-8A2BE2)
![Static Badge](https://img.shields.io/badge/type--safe-8A2BE2)
![Static Badge](https://img.shields.io/badge/flexible-8A2BE2)
![Static Badge](https://img.shields.io/badge/convenience-8A2BE2)

[中文版](doc/README_CN.md)

## Introduction

The `json-template` library extends the standard JSON format to include variable interpolation and optional trailing commas in objects and arrays. This format is ideal for dynamically generating
configuration files, API responses, and other data structures that require flexibility and customization.

## Features

- **Variable Interpolation**: Supports embedding variables directly into JSON using the `${var}` syntax, applicable both in keys and values.
- **Type Safety**: Enhances the reliability of JSON templates by enforcing type specifications on interpolated variables. This feature helps prevent common data type errors such as mismatches and
  incorrect data formats.
- **Null Enhancements**: Allows Optional chaining `?.` and Nullish coalescing `??` operators to handle null values more effectively.
- **Optional Commas**: Allows an optional trailing comma at the end of lists and objects, facilitating easier modifications and extensions.

## Syntax

### Variable Interpolation

Variable interpolation in the `json-template` library allows you to dynamically insert values into your JSON templates at both the value level and key level, enabling highly customizable JSON
configurations.

#### Interpolation Types

- **TEXT Interpolation**:
  Use the syntax `${variableName}` within string values to interpolate a variable. The parser will automatically replace `${variableName}` with the value of the variable at runtime.

  Example:
  ```json
  {
      "greeting": "Hello, ${username}!"
  }
  ```
  Here, `${username}` will be substituted with the actual user's name when the JSON is processed.

- **Node Interpolation**:
  You can directly insert numeric or boolean values through interpolation, making the template dynamically responsive to numeric or logical data.

  Example:
  ```
  {
      "name": "John Doe",
      "age": ${age}
  }
  ```
  In this case, `${age}` will be replaced by the numerical value assigned to `age`.

### Interpolation Expressions

You can use the following interpolation expressions to access nested properties or perform operations on variables:

```
${person.name?.firstName??sam::string}
```

#### Dereferencing Operator `.`

The Dereferencing Operator `.` is used to access nested properties of an object. If any property in the chain is `null`, the expression will return `null`.

#### Optional Chaining Operator `?.`

The Optional Chaining Operator `?.` allows you to safely access nested properties without causing an error if any intermediate property is `null`.

#### Type Specifier `::`

You can specify the expected type of the variable using the Type Specifier `::`. This feature enhances type safety by ensuring that the variable is of the correct type before being interpolated.
The optional types are `string`, `number`, `boolean`, `object`, `null`, and `array`.


#### Nullish Coalescing Operator `??`

The nullish coalescing `??` operator is a logical operator that returns its right-hand side operand when its left-hand side operand is `null` .
The default value may look like this:
* **`${a??sam}`** : By default, the `sam` will be treated as a string type.  
* **`${a??42::number}`** : You need to specify the `number` type explicitly.
* **`${a??true::bool}`** : You need to specify the `bool` type explicitly.
* **`${a??[]::array}`** :  You need to specify the `array` type explicitly.
* **`${a??{}::object}`** :  You need to specify the `object` type explicitly.

#### Rules

1. **Variable Scope**: Ensure that all variables are defined within the scope where the JSON template is processed, or are explicitly passed to the template processor.
2. **Fallback Values**: You can specify default values for variables to handle cases where a variable might not be set.
3. **Escaping**: Use escaping mechanisms defined by your library to include literal `\${}` in your JSON without being processed as a variable.

#### Considerations

- Variable names should be valid identifiers and should not conflict with JSON syntax or reserved keywords.
- Carefully handle user-provided variable values to avoid unintended modifications to the JSON structure, particularly in security-sensitive contexts.
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


