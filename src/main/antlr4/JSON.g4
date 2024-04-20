grammar JSON;
/**
    {
        "name": "abc",
        "version": ${a},
        "description": "JSON ${b} parser",
        "author": "Igor Klopov",
        "li${c}nse": "MIT",
    }
    JSON_TEMPLATE base on json grammar;
    1. support string interpolation in json key and value;
        it can replace ${a} with a value;
        it can replace ${b} in a string value;
        it can replace ${c} in a key;
    2. support external comma at the end of object;
*/

json_template
    : value
    ;
obj
    : '{' pair (',' pair)* ','? '}'
    | '{' '}'
    ;
pair
    : str ':' value
    ;
arr
    : '[' value (',' value)* ']'
    | '[' ']'
    ;
value
    : str
    | num
    | var
    | bool
    | null
    | obj
    | arr
    ;
str
    : STRING
    ;
num
    : NUMBER
    ;
var
    : '${' VARNAME '}'
    ;
bool
    : TRUE | FALSE
    ;
null
    : NULL
    ;


FALSE
    : 'false';
TRUE
    : 'true' ;
NULL
    : 'null'
    ;
VARNAME
    : [a-zA-Z_] [a-zA-Z_0-9]*
    ;
STRING
    : '"' (ESC | SAFECODEPOINT)* '"'
    ;
NUMBER
    : '-'? INT ('.' [0-9] +)? EXP?
    ;
WS
    : [ \t\n\r] + -> skip
    ;
fragment ESC
    : '\\' (["\\/bfnrt] | UNICODE)
    ;
fragment UNICODE
    : 'u' HEX HEX HEX HEX
    ;
fragment HEX
    : [0-9a-fA-F]
    ;
fragment SAFECODEPOINT
    : ~ ["\\\u0000-\u001F]
    ;
fragment INT
    : '0' | [1-9] [0-9]*
    ;
fragment EXP
    : [Ee] [+\-]? INT
    ;


