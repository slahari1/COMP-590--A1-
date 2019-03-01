package main;

/* SymbolWithCodeLength
 * 
 * Class that encapsulates a symbol value along with the length of the code
 * associated with that symbol. Used to build the canonical huffman tree.
 * Implements Comparable in order to sort first by code length and then by symbol value.
 */

public class SymbolWithCodeLength implements Comparable<SymbolWithCodeLength> {
	
	// Instance fields should be declared here.
	int _value;
	int _code_length;
	
	// Constructor
	public SymbolWithCodeLength(int value, int code_length) {
		_value = value;
		_code_length = code_length;
	}

	// codeLength() should return the code length associated with this symbol
	public int codeLength() {
		// Needs implementation
		return _code_length;
	}

	// value() returns the symbol value of the symbol
	public int value() {
		// Needs implementation
		return _value; 
	}

	// compareTo implements the Comparable interface
	// First compare by code length and then by symbol value.
	@Override 
		public int compareTo(SymbolWithCodeLength other) {
		if (this._code_length > other._code_length){
			return 1;
		}
		else if (this._code_length < other._code_length){
			return -1;
		}
		else {
			if (this._value > other._value){
				return 1;
			}
			else if (this._value < other._value){
				return -1;
			}
			else {
				return 0;
			}
		} 
	}
}
