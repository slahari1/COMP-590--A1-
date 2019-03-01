package main;

public class InternalHuffmanNode implements HuffmanNode {

	HuffmanNode _left;
	HuffmanNode _right;
	int _level;

	public InternalHuffmanNode(int level) {
		_level = level;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isLeaf() {
		return false;
	}

	@Override
	public int symbol() throws Exception {
		throw new Exception("operation not valid for internal nodes");
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
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
		return this.left() != null && this.left().isFull() && this.right() != null && this.right().isFull();
	}

	@Override
	public boolean insertSymbol(int length, int symbol) throws Exception {
		/*
		 * if (this.left() == null){
		 * 
		 * this.setLeft(new LeafHuffmanNode(symbol));//create new leaf node and
		 * set to left child return true; } else { if (this.right() == null){
		 * this.setRight(new LeafHuffmanNode(symbol)); return true; } else{ if
		 * (this.right().isLeaf()){ InternalHuffmanNode I = new
		 * InternalHuffmanNode(); I.setLeft(this.right()); this.setRight(I); } }
		 * 
		 * return this.right().insertSymbol(length, symbol); // create new leaf
		 * node and set to right child }
		 */

		if (!this.isFull()) {
			if (this.left() == null) {
				if (this.level() == length - 1) {
					this.setLeft(new LeafHuffmanNode(this.level() + 1, symbol));
					return true;
				} else {
					this.setLeft(new InternalHuffmanNode(this.level() + 1));
					return this.left().insertSymbol(length, symbol);
				}
			} else { // left not null
				if (this.left().isLeaf() || !this.left().insertSymbol(length, symbol)) {
					// left insert is not successful move to the right
					if (this.right() == null) {
						if (this.level() == length - 1) {
							this.setRight(new LeafHuffmanNode(this.level() + 1, symbol));
							return true;
						} else {
							this.setRight(new InternalHuffmanNode(this.level() + 1));
							return this.right().insertSymbol(length, symbol);
						}

					} else {
						// right is not null
						return this.right().insertSymbol(length, symbol);

					}
				} else {
					return true;
				}
			}
		} else {
			return false;
		}
	}

	@Override
	public HuffmanNode left() {
		// TODO Auto-generated method stub
		return this._left;
	}

	@Override
	public HuffmanNode right() {
		// TODO Auto-generated method stub
		return this._right;
	}

	public void setLeft(HuffmanNode node) {
		_left = node;
	}

	public void setRight(HuffmanNode node) {
		_right = node;
	}
}
