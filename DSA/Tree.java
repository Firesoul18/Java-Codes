public class Tree {
	private int data;
	private Tree left, right;
	private int size;

	public Tree(int data, Tree left, Tree right) {
		this.data = data;
		this.left = left;
		this.right = right;
		size = 1;
	}

	public Tree(Tree cTree) {
		this.data = cTree.data;
		this.left = cTree.left;
		this.right = cTree.right;
		size = cTree.size;
	}

	public boolean isPresent(int num){
		if(this.data==num){
			return true;
		}
		if(data>num){
			Tree searchTree = this.left;
			if(searchTree==null){
				return false;
			}
			return searchTree.isPresent(num);
		}
		else{
			Tree searchTree=this.right;
			if(searchTree==null){
				return false;
			}
			return searchTree.isPresent(num);
		}
	}


	public void push(int n){
		if(n<data){
			Tree leftTree = this.getLeft();
			if(leftTree==null){
				this.setLeft(new Tree(n, null, null));
				size++;
				return;
			}
			leftTree.push(n);
			size++;
		}
		else{
			Tree rightTree = this.getRight();
			if(rightTree==null){
				this.setRight(new Tree(n,null,null));
				size++;
				return;
			}
			rightTree.push(n);
			size++;
		}
	}

	public void inOrder(){
		if(left!=null)
		left.inOrder();
		System.out.print(data+" ");
		if(right!=null)
		right.inOrder();
	}

	public void preOrder(){
		System.out.print(data+" ");
		if(left!=null)
		left.preOrder();
		if(right!=null)
		right.preOrder();
	}

	public void postOrder(){
		if(left!=null)
		left.postOrder();
		if(right!=null)
		right.postOrder();
		System.out.print(data+" ");
	}



	public int getData() {
		return data;
	}

	public Tree getLeft() {
		return left;
	}

	public Tree getRight() {
		return right;
	}

	public void setData(int data) {
		this.data = data;
	}

	public void setLeft(Tree left) {
		this.left = left;
	}

	public void setRight(Tree right) {
		this.right = right;
	}

	public int size() {
		return size;
	}

	public static void main(String args[]) {
		Tree a = new Tree(32, null, null);
		a.push(12);
		a.push(38);
		a.push(10);
		a.push(17);
		a.push(24);
		a.push(33);
		a.push(40);
		a.push(62);
		a.push(2);
		System.out.print("\nIn Order: ");
		a.inOrder();
		System.out.print("\nPre Order: ");
		a.preOrder();
		System.out.print("\nPost Order: ");
		a.postOrder();
		System.out.println("\nIs Present for 12: "+a.isPresent(12));
		System.out.println("Is Present for 10: "+a.isPresent(10));
		System.out.println("Is Present for 17: "+a.isPresent(17));
		System.out.println("Is Present for 2: "+a.isPresent(2));
		System.out.println("Is Present for 62: "+a.isPresent(62));
		System.out.println("Is Present for 923: "+a.isPresent(923));

	}

}