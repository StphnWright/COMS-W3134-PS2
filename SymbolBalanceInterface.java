public interface SymbolBalanceInterface {
	
	public void setFile(String filename);
	
    // returns either MismatchError(int lineNumber, char currentSymbol, char symbolPopped)
	// EmptyStackError(int lineNumber), 
	// NonEmptyStackError(char topElement, int sizeOfStack). 
	// All three classes implement BalanceError
    public BalanceError checkFile(); 

}
