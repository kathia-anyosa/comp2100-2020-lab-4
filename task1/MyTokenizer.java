package task1;

import java.util.ArrayList;

/**
 * Name: Tokenizer.java
 *
 * Remind:
 * 1. Your job is to implement next() method.
 * 2. Please do not modify anything else.
 * 3. Check the correctness of implementation via "TokenizerTest.java" before the submission.
 * 4. You may create additional fields or methods to finish your implementation.
 *
 * @author: Kathia Anyosa
 * @Uid: u6414938
 * @Date: 10 April 2020
 */

public class MyTokenizer extends Tokenizer {
    
    private String _buffer;		//save text
    private Token currentToken;	//save token extracted from next()

    /**
     *  Tokenizer class constructor
     *  The constructor extracts the first token and save it to currentToken
     *  **** please do not modify this part ****
     */
    public MyTokenizer(String text) {
    	_buffer = text;		// save input text (string)
    	next();		// extracts the first token.
    }
    
    /**
     *  This function will find and extract a next token from {@code _buffer} and
     *  save the token to {@code currentToken}.
     */
    public void next() {
        _buffer = _buffer.trim(); // remove whitespace
        
         if(_buffer.isEmpty()) {
            currentToken = null;	// if there's no string left, set currentToken null and return
            return;
         }
         
        char firstChar = _buffer.charAt(0);
        if(firstChar=='+')
        	currentToken = new Token("+", Token.Type.ADD);
        if(firstChar=='-')
        	currentToken = new Token("-", Token.Type.SUB);

        // TODO: Implement multiplication and division tokenising
        // TODO: Implement left round bracket and right round bracket
        // TODO: Implement integer literal tokenising
        // HINT: Character.isDigit() may be useful        
        // ########## YOUR CODE STARTS HERE ##########
        if (firstChar == '*'){
            currentToken = new Token("*", Token.Type.MUL);
        }

        if (firstChar == '/'){
            currentToken = new Token("/", Token.Type.DIV);
        }

        if (firstChar == '('){
            currentToken = new Token("(", Token.Type.LBRA);
        }

        if (firstChar == ')'){
            currentToken = new Token(")", Token.Type.RBRA);
        }

        if (Character.isDigit(firstChar)){
            char[] remaining = _buffer.toCharArray();
            int count = 0;

            while (count < remaining.length && Character.isDigit(remaining[count])){
                count++;
            }
            //System.out.println("count = " + count);

            ArrayList<Character> unsigned = new ArrayList<>();
            for (int i = 0; i < count; i++){
                unsigned.add(remaining[i]);
            }
            //System.out.println("unsigned = " + unsigned);

            String unsigned_literal = "";
            for (Character character : unsigned) {
                unsigned_literal = unsigned_literal.concat(Character.toString(character));
            }
            //System.out.println("string to be tokenized = " + unsigned_literal);
            currentToken = new Token(unsigned_literal, Token.Type.INT);
        }
        
        // ########## YOUR CODE ENDS HERE ##########
        
        // Remove the extracted token from buffer
        int tokenLen = currentToken.token().length();
        _buffer = _buffer.substring(tokenLen);
    }

    /**
     *  returned the current token extracted by {@code next()}
     *  **** please do not modify this part ****
     *  
     * @return type: Token
     */
    public Token current() {
    	return currentToken;
    }

    /**
     *  check whether there still exists another tokens in the buffer or not
     *  **** please do not modify this part ****
     *  
     * @return type: boolean
     */
    public boolean hasNext() {
    	return currentToken != null;
    }
}