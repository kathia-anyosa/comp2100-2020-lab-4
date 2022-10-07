package task2;

import task1.MyTokenizer;
import task1.Token;

/**
 * Name: Parser.java
 *
 *  The main objective of this class is to implement a simple parser.
 *  It should be able to parser the following grammar rule:
 *  <exp>    ::= <term> | <term> + <exp> | <term> - <exp>
 *  <term>   ::=  <factor> | <factor> * <term> | <factor> / <term>
 *  <factor> ::= <unsigned integer> | ( <exp> )
 *
 * @author: Kathia Anyosa
 * @Uid: u6414938
 * @Date: 10 April 2020
 */

public class Parser {

    MyTokenizer _tokenizer;

    public Parser(MyTokenizer tokenizer) { _tokenizer = tokenizer; }

    public Exp parseExp() {
        // TODO: Implement parse function for <exp>
        // ########## YOUR CODE STARTS HERE ##########
        Exp term = parseTerm();
        //System.out.println("term = " + term.show());

        if (_tokenizer.hasNext() && _tokenizer.current().type() == Token.Type.ADD){
            _tokenizer.next();
            Exp exp = parseExp();
            return new AddExp(term, exp);
        } else if (_tokenizer.hasNext() && _tokenizer.current().type() == Token.Type.SUB){
            _tokenizer.next();
            Exp exp = parseExp();
            return new SubExp(term, exp);
        } else {
            return term;
        }
        // ########## YOUR CODE ENDS HERE ##########
    }

    public Exp parseTerm() {
        // TODO: Implement parse function for <term>
        // ########## YOUR CODE STARTS HERE ##########
        Exp factor = parseFactor();
        //System.out.println("factor = " + factor.show());

        if (_tokenizer.hasNext() && _tokenizer.current().type() == Token.Type.MUL){
            _tokenizer.next();
            Exp term = parseTerm();
            return new MultExp(factor, term);
        } else if (_tokenizer.hasNext() && _tokenizer.current().type() == Token.Type.DIV){
            _tokenizer.next();
            Exp term = parseTerm();
            return new DivExp(factor, term);
        } else {
            return factor;
        }
        // ########## YOUR CODE ENDS HERE ##########
    }

    public Exp parseFactor() {
        // TODO: Implement parse function for <factor>
        // ########## YOUR CODE STARTS HERE ##########
        //System.out.println("_tokenizer = " + _tokenizer.current().token());
        if (_tokenizer.current().type() == Token.Type.LBRA){
            _tokenizer.next();
            //System.out.println("next = " + _tokenizer.current().token());
            Exp term = parseExp();
            //System.out.println("term = " + term.show());
            _tokenizer.next();
            //System.out.println("next = " + _tokenizer.current().token());
            return term;
        } else {
            Exp unsigned = new IntExp(Integer.parseInt(_tokenizer.current().token()));
            //System.out.println("unsigned = " + unsigned.show());
            _tokenizer.next();
            return unsigned;
        }
        // ########## YOUR CODE ENDS HERE ##########
    }
}
