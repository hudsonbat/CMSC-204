/**
 * This generic class represents the data element that is
 * the basis of the data structure class, the MorseCodeTree class.
 * It contains a field that denotes the data for a given node of a
 * binary tree and two fields that denote the left and right child nodes
 * of a given parent node and contains methods for accessing and mutating 
 * the nodes of a binary tree. 
 * @author hudson
 * @param <T> The generic data type to be stored in the binary tree nodes.
 */
public class TreeNode<T> {
	
	private T data;
	private TreeNode<T> leftChild;
	private TreeNode<T> rightChild;
	
	/**
	 * This constructor constructs a node based on the
	 * data supplied as an argument and calls the triply
	 * parameterized constructor with the left and right
	 * child nodes set to null.
	 * @param dataNode The data stored in the node.
	 */

	public TreeNode(T dataNode) {
		this(dataNode,null,null);
	}
	
	/**
	 * This copy constructor constructs a deep copy of a node 
	 * based on the node passed as an argument. It calls the 
	 * getData(), getLeftChild(), and getRightChild() methods of 
	 * the node passed as an argument and passes them to the
	 * triply parameterized constructor.
	 * @param node The node to be copied.
	 */
	
	public TreeNode(TreeNode<T> node) {
		this(node.getData(), node.getLeftChild(), node.getRightChild());
	}
	
	/**
	 * This constructor constructs a node with left and right children 
	 * and data based on the arguments passed to the constructor. 
	 * @param dataNode The data of the node.
	 * @param leftChild The left child of the node.
	 * @param rightChild The right child of the node.
	 */
	
	public TreeNode(T dataNode, TreeNode<T> leftChild, TreeNode<T> rightChild) {
		this.data = dataNode;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
	
	/**
	 * This method returns the data in a given node.
	 * @return The data in a node.
	 */
	
	public T getData() {
		return data;
	}
	
	/**
	 * This method sets the data in a node to the 
	 * data passed as an argument.
	 * @param data The data to be stored in a node.
	 */
	
	public void setData(T data) {
		this.data = data;
	}
	
	/**
	 * This method returns the left child of a 
	 * given parent node.
	 * @return The left child of a given parent node.
	 */
	
	public TreeNode<T> getLeftChild() {
		return leftChild;
	}
	
	/**
	 * This method returns the right child of a given 
	 * parent node.
	 * @return The right child of a given parent node.
	 */
	
	public TreeNode<T> getRightChild(){
		return rightChild;
	}
	
	/**
	 * This method creates a right child node
	 * for a given node from the node passed as 
	 * an argument.
	 * @param rightChild The node to be set as the right child of the node.
	 */
	
	public void setRightChild(TreeNode<T> rightChild) {
		this.rightChild = rightChild;
	}
	
	/**
	 * This method creates a right child node
	 * for a given node from the node passed as 
	 * an argument.
	 * @param leftChild The node to be set as the left child of the node.
	 */
	
	public void setLeftChild(TreeNode<T> leftChild) {
		this.leftChild = leftChild;
	}
	
	/**
	 * This method returns true if a given node has a 
	 * right child node and false otherwise.
	 * @return true if a given node has a right child node and false otherwise.
	 */
	
	public boolean hasRightChild() {
		return rightChild != null;
	}
	
	/**
	 * This method returns true if a given node has a 
	 * left child node and false otherwise.
	 * @return true if a given node has a left child node and false otherwise.
	 */
	
	public boolean hasLeftChild() {
		return leftChild != null;
	}
	
	/**
	 * This method returns true if a given node is a leaf
	 * and false otherwise.
	 * @return true if the given node is a leaf and false otherwise.
	 */
	
	public boolean isLeaf() {
		return (leftChild == null) && (rightChild == null);
	}
	
}
