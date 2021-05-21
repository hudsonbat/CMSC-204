/**
 * This class represents a binary search tree data structure for Strings.
 * This class implements the LinkedConverterTreeInterface interface
 * and employs some constructors and private methods in order to do so.
 * It contains methods that add nodes to a binary search tree, fetch nodes from 
 * a binary search tree, and give the inorder order of a binary search tree. 
 * The no-arg constructor of the class builds a binary search tree on the basis
 * of Morse code so the class can be thought of a Morse code binary search tree class.
 * @author hudson
 */

import java.util.ArrayList;

public class MorseCodeTree implements LinkedConverterTreeInterface<String> {
	
	private TreeNode<String> root;
	private ArrayList<String> list = new ArrayList<>();
	private int i = 0;
	private int j = 1;
	
	/**
	 * This no-arg constructor calls the buildTree method,
	 * which creates a tree of alphabetic characters based
	 * on Morse code.
	 */
	
	public MorseCodeTree() {
		buildTree();
	}
	
	/**
	 * This constructor creates a root with the String
	 * data passed as a parameter serving as the root node
	 * by passing the data to the TreeNodeclass constructor.
	 * @param data The data to be stored in the root.
	 */
	
	public MorseCodeTree(String data) {
		root = new TreeNode<>(data);
	}
	
	/**
	 * Returns a reference to the root
	 * @return reference to root
	 */

	@Override
	public TreeNode<String> getRoot() {
		return root;
	}
	
	/**
	 * sets the root of the Tree
	 * @param newNode a TreeNode that will be the new root
	 */

	@Override
	public void setRoot(TreeNode<String> newNode) {
		root = newNode;
	}
	
	/**
	 * Adds result to the correct position in the tree based on the code
	 * This method will call the recursive method addNode
	 * 
	 * @param code the code for the new node to be added
	 * @return the linkedConverterTree with the new node added
	 */

	@Override
	public LinkedConverterTreeInterface<String> insert(String code, String result) {
		addNode(root, code, result);
		return this;
	}
	
	/**
	 * This is a recursive method that adds element to the correct position 
	 * in the tree based on the code.
	 * 
	 * @param root the root of the tree for this particular recursive instance of addNode
	 * @param code the code for this particular recursive instance of addNode
	 * @param letter the data of the new TreeNode to be added
	 * @throws UnsupportedOperationException If the code has too many dots or dashes and the letter cannot be added.
	 */

	@Override
	public void addNode(TreeNode<String> root, String code, String letter) throws UnsupportedOperationException {
	
		TreeNode<String> currentNode = root;
		TreeNode<String> newNode = new TreeNode<>(letter);
		
		if(code.length() == j) {
			if(code.equals(".")) {
				currentNode.setLeftChild(newNode);
			} else if (code.equals("-")) {
				currentNode.setRightChild(newNode);
			}
		} else {
			if(code.charAt(i) == '.' && currentNode.hasLeftChild()) {
					currentNode = currentNode.getLeftChild();
					code = getNewCode(code,j);
					addNode(currentNode, code, letter);
			} else if(code.charAt(i) == '-' && currentNode.hasRightChild()) {
					currentNode = currentNode.getRightChild();
					code = getNewCode(code,j);
					addNode(currentNode,code,letter);
			} else {
				throw new UnsupportedOperationException("No node at this position!");
			}
		}
	}
	
	/**
	 * This private method returns the code passed as argument to 
	 * both the fetchNode() and addNode() methods by returning a 
	 * subset of the code based on the integer passed as an 
	 * argument.
	 * @param code The code to be subset.
	 * @param first The new first index of the code. 
	 * @return The shortened code.
	 */
	
	private String getNewCode(String code, int first) {
		String codeNew = "";
		
		for(int index = first; index < code.length(); index++) {
			codeNew += code.charAt(index);
		}
		
		return codeNew;
	}
	
	/**
	 * Fetch the data in the tree based on the code
	 * This method will call the recursive method fetchNode
	 * 
	 * @param code the code that describes the traversals within the tree
	 * @return the result that corresponds to the code
	 */

	@Override
	public String fetch(String code) {
			return fetchNode(root, code);
	}
	
	/**
	 * This is the recursive method that fetches the data of the TreeNode
	 * that corresponds with the code
	 * 
	 * @param root the root of the tree for this particular recursive instance of addNode
	 * @param code the code for this particular recursive instance of fetchNode
	 * @return the data corresponding to the code
	 * @throws UnsupportedOperationException If the code has too many dots or dashes and therefore has no corresponding character.
	 */

	@Override
	public String fetchNode(TreeNode<String> root, String code) throws UnsupportedOperationException {
		
		TreeNode<String> currentNode = root;
		String result = "";
		
		if(code.length() == i || currentNode.isLeaf()) {
			 result = currentNode.getData();
		} else {
			if (code.charAt(i) == '.' && currentNode.hasLeftChild()) {
					currentNode = currentNode.getLeftChild();
					code = getNewCode(code,j);
					result = fetchNode(currentNode, code);	
			} else if(code.charAt(i) == '-' && currentNode.hasRightChild()) {
					currentNode = currentNode.getRightChild();
					code = getNewCode(code,j);
					result = fetchNode(currentNode, code);
			} else {
				throw new UnsupportedOperationException("Code contains too many dashes or dots!");
			}
		}
		
		return result;
		
	}
	
	/**
	 * This operation is not supported for a LinkedConverterTree
	 * @param data data of node to be deleted
	 * @return reference to the current tree
	 * @throws UnsupportedOperationException If the method is invoked.
	 */

	@Override
	public LinkedConverterTreeInterface<String> delete(String data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("This operation is not supported in the MorseCodeTree");
	}
	
	/**
	 * This operation is not supported for a LinkedConverterTree
	 * @return reference to the current tree
	 * @throws UnsupportedOperationException If the method is invoked.
	 */

	@Override
	public LinkedConverterTreeInterface<String> update() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("This operation is not supported in the MorseCodeTree");
	}
	
	/**
	 * This method builds the LinkedConverterTree by inserting TreeNodes
	 * into their proper locations
	 */

	@Override
	public void buildTree() {
		
		root = new TreeNode<>("");
		
		insert(".","e");
		insert("-","t");
		insert("..","i");
		insert(".-","a");
		insert("-.","n");
		insert("--","m");
		insert("...","s");
		insert("..-","u");
		insert(".-.","r");
		insert(".--","w");
		insert("-..","d");
		insert("-.-","k");
		insert("--.","g");
		insert("---","o");
		insert("....","h");
		insert("...-","v");
		insert("..-.","f");
		insert(".-..","l");
		insert(".--.","p");
		insert(".---","j");
		insert("-...","b");
		insert("-..-","x");
		insert("-.-.","c");
		insert("-.--","y");
		insert("--..","z");
		insert("--.-","q");
		
	}
	
	/**
	 * Returns an ArrayList of the items in the linked converter Tree in LNR (Inorder) Traversal order
	 * Used for testing to make sure tree is built correctly
	 * @return an ArrayList of the items in the linked Tree
	 */

	@Override
	public ArrayList<String> toArrayList() {
		
		ArrayList<String> copy = new ArrayList<>();
		LNRoutputTraversal(root, list);
		
		for(String s : list) {
			copy.add(s);
		}
		
		return copy;
	}
	
	/**
	 * The recursive method to put the contents of the linked converter tree in an ArrayList 
	 * LNR (Inorder)
	 * @param root the root of the tree for this particular recursive instance
	 * @param list the ArrayList that will hold the contents of the tree in LNR order
	 */

	@Override
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		
		if (root == null) {
		} else {
			LNRoutputTraversal(root.getLeftChild(), list);
			list.add(root.getData());
			LNRoutputTraversal(root.getRightChild(),list);
		}
		
	}
	

}
