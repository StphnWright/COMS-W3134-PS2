import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class SymbolBalance implements SymbolBalanceInterface {
    
    private String filename;
    
    public static void main(String[] args) {
        SymbolBalance s = new SymbolBalance();
        s.setFile("TestFiles/Test6.java");
        BalanceError error = s.checkFile();
        
        System.out.println(error);
    }
    
    public void setFile(String filename) {
            this.filename = filename;
    }
	
    // Returns either MismatchError(int lineNumber, char currentSymbol, char symbolPopped)
    // EmptyStackError(int lineNumber), 
	// NonEmptyStackError(char topElement, int sizeOfStack). 
	// All three classes implement BalanceError
    public BalanceError checkFile() {
        try {
            int lineNumber = 1;
            MyStackInterface<Character> stack = new MyStack<>();
            
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            
            int prevC = -1; 
            int c;
            boolean insideComment = false;
            boolean insideString = false;
            
            while ((c = reader.read()) != -1) {
                char ch = (char) c;
                if (ch == '\n') {
                    lineNumber++;
                } else if (prevC == '/' && ch == '*' && !insideComment) {
                    insideComment = true;
                    stack.push(ch);
                } else if (prevC == '*' && ch == '/' && insideComment) {
                    insideComment = false;
                    if (stack.isEmpty()) {
                        return new EmptyStackError(lineNumber);
                    }
                    char topCh = stack.pop();
                    if (topCh != '*') {
                        return new MismatchError(lineNumber, ch, topCh);
                    }
                } else if (insideComment) {
                    //do nothing inside the comment
                } else if (ch == '"' && !insideString) {
                    insideString = true;
                    stack.push(ch);
                } else if (ch == '"' && insideString) {
                    insideString = false;
                    if (stack.isEmpty()) {
                        return new EmptyStackError(lineNumber);
                    }
                    char topCh = stack.pop();
                    if (topCh != '"') {
                        return new MismatchError(lineNumber, ch, topCh);
                    }
                } else if (insideString) {
                    //do nothing inside the string
                } else if (ch == '{' || ch == '(' || ch == '[') {
                    stack.push(ch);
                } else if (ch == '}') {
                    if (stack.isEmpty()) {
                        return new EmptyStackError(lineNumber);
                    }
                    char topCh = stack.pop();
                    if (topCh != '{') {
                        return new MismatchError(lineNumber, ch, topCh);
                    }
                } else if (ch == ')') {
                    if (stack.isEmpty()) {
                        return new EmptyStackError(lineNumber);
                    }
                    char topCh = stack.pop();
                    if (topCh != '(') {
                        return new MismatchError(lineNumber, ch, topCh);
                    }
                } else if (ch == ']') {
                    if (stack.isEmpty()) {
                        return new EmptyStackError(lineNumber);
                    }
                    char topCh = stack.pop();
                    if (topCh != '[') {
                        return new MismatchError(lineNumber, ch, topCh);
                    }
                }
                prevC = c;  
            }
            
            reader.close();
            
            if (! stack.isEmpty()) {
                return new NonEmptyStackError(stack.peek(), stack.size());
            }    
                      
            return null;
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
    }
    
}
