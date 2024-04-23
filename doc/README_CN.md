# JSON Template 语法库
![Static Badge](https://img.shields.io/badge/language-json--template-8A2BE2)
![Static Badge](https://img.shields.io/badge/type--safe-8A2BE2)
![Static Badge](https://img.shields.io/badge/flexible-8A2BE2)
![Static Badge](https://img.shields.io/badge/convenience-8A2BE2)

[english](../README.md)

## 简介
`json-template`库扩展了标准JSON格式，包括了变量插值和在对象与数组末尾使用可选逗号。
这种格式非常适合动态生成配置文件、API响应以及其他需要灵活性和定制化的数据结构。


## 功能
- **变量插值**: 支持使用`${var}`语法直接在JSON中嵌入变量，适用于键和值
- **类型安全**: 通过在插值变量上强制类型规范，增强了JSON模板的可靠性。有助于防止常见的数据类型错误
- **空值增强**: 允许使用可选链`?.`和空值合并`??`操作符更有效地处理空值
- **可选逗号**: 允许在列表和对象的末尾使用可选逗号，便于更容易地进行修改和扩展

## 语法规则

### 变量插值

`json-template`库允许您在JSON模板的值层和键层动态插入值，实现高度可定制的JSON配置。

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

#### 规则
1. **Variable Scope**: Ensure that all variables are defined within the scope where the JSON template is processed, or are explicitly passed to the template processor.
2. **Fallback Values**: You can specify default values for variables to handle cases where a variable might not be set.
3. **Escaping**: Use escaping mechanisms defined by your library to include literal `\${}` in your JSON without being processed as a variable.

#### 注意事项
- Variable names should be valid identifiers and should not conflict with JSON syntax or reserved keywords.
- Carefully handle user-provided variable values to avoid unintended modifications to the JSON structure, particularly in security-sensitive contexts.
- Maintain clear and descriptive variable naming to ensure the readability and maintainability of your JSON templates.
- Leverage the full power of your library's features by combining node and variable interpolation for dynamic JSON generation.

### 代码格式化

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

## 贡献
Contributions are welcome! Feel free to submit Pull Requests or open Issues to suggest new features or report bugs. Please make sure to follow the contributing guidelines.


