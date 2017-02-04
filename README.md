# ExSolver

Expression solver.

Original Comments from 2013-12-28

mathex™ Solver.

mathex™ Solver simplifies any mathematical expression.
It can handle any number of nested brackets.
Variables which have their predefined values can be used in the expression.
Radix convertions from 2 to 36 including hexadecimal, octal and binary number systems are supported. 
Various functions including but not limited to sin(), cos(), ln() ... are supported.

I followed the rule "practice design, not decoration" rule to some extent.
Tried to simplify the GUI to make the software more user-friendly.
I used swing controls designing the GUI. 
Tooltips are used to guide the user to use the software. 
I used the html-like tag support for clarity.

Internally, I used a constructor which accepts the expression as a String and, a TreeMap of variables. 
I used a function called "selectAndSolve" which selects what operations to perform.
Being the main point of the program, it is recursively called by other parts of the program.
By doing so, I managed to decrease code redundancy. 
I divided the program into different parts. 
By using different methods for each of the operations, (one method for a operator and its opposite),
it was easy to maintain cohesion. 
Before doing the calculation, I used a method to make it cleaner 
(i.e.: removing whitespaces, adding operators where not given, remove repeated operators...). 
I commented when necessary, so I can easily maintain the code. 
