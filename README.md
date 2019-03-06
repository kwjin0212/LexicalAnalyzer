# LexicalAnalyzer
This is a part of Compiler for computing language C Minus.

SPECIFICATIONS: This project is to use the grammar definition in the appendix of your text to guide the construction of a lexical analyzer.  The lexical analyzer should return tokens as described. Keep  in mind these tokens will serve as the input to the parser. You must enhance the definitions by adding a keyword "float" as a data type to the material on page 493 and beyond. Specifically, rule 5 on page 492 should state 
    type-specifier -> int | void | float 
and any other modifications necessary must be included.  
Page 491 and 492 should be used to guide the construction of the lexical analyzer. A few notable features: 
0) the project's general goal is to construct a list of tokens capable    of being passed to a parser. 
1) comments should be totally ignored, not passed to the parser and    not reported. 
2) comments might be nested. 
3) one line comments are designated by // 
4) multiple line comments are designated by /* followed by */ in     a match up fashion for the nesting. 
5) a symbol table* for identifiers should be constructed (as    per recommendation of your text, I actually recommend    construction of the symbol table during parsing).   
  a) the symbol table should keep track of the identifier    
  b) be extensible    
  c) keep track of scope    
  d) be constructed efficiently    * this will not be evaluated until project 3 
6) upon reporting of identifiers, their nesting depth/declarations    should be displayed. 
