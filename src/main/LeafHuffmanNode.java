package main;

public class LeafHuffmanNode implements HuffmanNode {
	
	int _value;
	int _level;
	
	public LeafHuffmanNode(int level, int symbol) {
		_value = symbol;
		_level = level;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isLeaf() {
		return true;
	}

	@Override
	public int symbol() throws Exception {
		return this._value;
	}

	@Override
	public int height() {
		return 0;
	}
	
	public int level() {
		return _level;
	}
	
	public void setLevel(int level) {
		_level = level;
	}

	@Override
	public boolean isFull() {
		return true;
	}

	@Override
	public boolean insertSymbol(int length, int symbol) throws Exception {
		throw new Exception ("insert symbol is not a valid operation on leaf node"); 
	}

	@Override
	public HuffmanNode left() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HuffmanNode right() {
		// TODO Auto-generated method stub
		return null;
	}

}
