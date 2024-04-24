parser grammar JsonTemplateParser;
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
options {
    tokenVocab = JsonTemplateLexer;
}

json_template
    : value
    ;
obj
    : LEFT_BRACE pair (COMMA pair)* COMMA? RIGHT_BRACE
    | LEFT_BRACE RIGHT_BRACE
    ;
pair
    : str COLON value
    ;
arr
    : SQUARE_LEFT value (COMMA value)* COMMA? SQUARE_RIGHT
    | SQUARE_LEFT SQUARE_RIGHT
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
    : DQUOTE string_content* DQUOTE
    ;
string_content
    : TEXT
    | var
    ;
num
    : NUMBER
    ;
var
    : VAR_START interpolExpr RIGHT_BRACE
    ;
bool
    : TRUE | FALSE
    ;
null
    : NULL
    ;

interpolExpr
    : VARNAME accessors* nullCoalescingOp? typeSpec?
    ;

accessors
    : (QUESTION_DOT | DOT) VARNAME
    ;

nullCoalescingOp
    : DOUBLE_QUESTION TEXT?
    ;

typeSpec
    : DOUBLE_COLON type
    ;

type
    : NUMBER_TYPE
    | BOOL_TYPE
    | STRING_TYPE
    | ARRAY_TYPE
    | OBJECT_TYPE
    | TEXT_TYPE
    | NULL
    ;




