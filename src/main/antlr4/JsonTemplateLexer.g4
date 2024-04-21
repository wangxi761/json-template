lexer grammar JsonTemplateLexer;

DQUOTE
    : '"'
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
    : DQUOTE (ESC | SAFECODEPOINT)* DQUOTE
    ;
NUMBER
    : '-'? INT ('.' [0-9] +)? EXP?
    ;
WS
    : [ \t\n\r] + -> skip
    ;
LEFT_BRACE
    : '{'
    ;
RIGHT_BRACE
    : '}'
    ;
SQUARE_LEFT
    : '['
    ;
SQUARE_RIGHT
    : ']'
    ;
COMMA
    : ','
    ;
COLON
    : ':'
    ;
VAR_START
    : '${'
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
