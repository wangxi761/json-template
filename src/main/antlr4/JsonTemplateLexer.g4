lexer grammar JsonTemplateLexer;

DQUOTE: '"' -> pushMode(IN_STRING);
FALSE: 'false';
TRUE: 'true';
NULL: 'null';
LEFT_BRACE: '{';
RIGHT_BRACE: '}';
SQUARE_LEFT: '[';
SQUARE_RIGHT: ']';
COMMA: ',';
COLON: ':';
VAR_START: '${' -> pushMode(EMEBDDED_VAR);
VARNAME: [a-zA-Z_] [a-zA-Z_0-9]*;
NUMBER: '-'? INT ('.' [0-9] +)? EXP?;
WS: [ \t\n\r] + -> skip;
fragment ESC: '\\' (["\\/bfnrt$] | UNICODE);
fragment UNICODE: 'u' HEX HEX HEX HEX;
fragment HEX: [0-9a-fA-F];
fragment SAFECODEPOINT: ~ [$"\\\u0000-\u001F];
fragment INT: '0' | [1-9] [0-9]*;
fragment EXP: [Ee] [+\-]? INT;
mode IN_STRING;
TEXT: (ESC | SAFECODEPOINT)+;
VAR_START_IN_STRING: VAR_START -> type(VAR_START),pushMode(EMEBDDED_VAR);
DQUOTE_IN_STRING: DQUOTE -> type(DQUOTE), popMode;
mode EMEBDDED_VAR;
QUESTION_DOT: '?.';
DOT: '.';
DOUBLE_QUESTION: '??' -> pushMode(DEFAULT_VALUE_MODE);
DOUBLE_COLON: '::' -> pushMode(EMEBDDED_TYPE);
VARNAME_IN_STRING: VARNAME -> type(VARNAME);
RIGHT_BRACE_IN_STRING: RIGHT_BRACE -> type(RIGHT_BRACE), popMode;
mode DEFAULT_VALUE_MODE;
DEFAULT_VALUE: ~[}:]+ -> type(TEXT);
DEFAULT_VALUE_END: DOUBLE_COLON -> type(DOUBLE_COLON), mode(EMEBDDED_TYPE);
mode EMEBDDED_TYPE;
NUMBER_TYPE: 'number' ;
STRING_TYPE: 'string';
BOOL_TYPE: 'bool';
ARRAY_TYPE: 'array';
OBJECT_TYPE: 'object';
TEXT_TYPE: 'text';
NULL_TYPE: 'null' -> type(NULL);
RIGHT_BRACE_IN_TYPE: RIGHT_BRACE -> type(RIGHT_BRACE), popMode, popMode;