import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MorseCodeTreeTest {
	
	MorseCodeTree tree;
	MorseCodeTree tree2;

	@BeforeEach
	void setUp() throws Exception {
		String data = "a";
		tree = new MorseCodeTree(data);
		tree.addNode(tree.getRoot(), ".", "b");
		tree.addNode(tree.getRoot(), "-", "c");
		tree.addNode(tree.getRoot(), "..", "d");
		tree.addNode(tree.getRoot(), ".-", "e");
		tree.addNode(tree.getRoot(), "-.", "f");
		tree.addNode(tree.getRoot(), "--", "g");
		tree2 = new MorseCodeTree();
	}

	@AfterEach
	void tearDown() throws Exception {
		tree= null;
		tree2=null;
	}

	@Test
	void testAddNode() {
		TreeNode<String> root = tree.getRoot();
		tree.addNode(root, "...", "s");
		assertEquals(tree.getRoot().getLeftChild().getLeftChild().getLeftChild().getData(),"s");
		tree.addNode(root, "---", "q");
		assertEquals(tree.getRoot().getRightChild().getRightChild().getRightChild().getData(),"q");
		
		TreeNode<String> root2 = tree2.getRoot();
		tree2.addNode(root2,"--...","!");
		assertEquals(tree2.getRoot().getRightChild().getRightChild().getLeftChild().getLeftChild().getLeftChild().getData(),"!");
		
		try {
			tree2.addNode(root2, "--..--..", "^");
		} catch(UnsupportedOperationException e) {
			assertTrue("No node at this position!",true);
		}
	}
	
	@Test
	void testFetchNode() {
		TreeNode<String> root = tree.getRoot();
		String ans1 = tree.fetchNode(root, ".-");
		assertEquals(ans1,"e");
		String ans2 = tree.fetchNode(root, "-.");
		assertEquals(ans2,"f");
		
		TreeNode<String> root2 = tree2.getRoot();
		String ans3 = tree2.fetchNode(root2, ".---");
		assertEquals(ans3,"j");
		
		try {
		String ans4 = tree2.fetchNode(root2, "-------");
		assertTrue("Code contains too many dashes or dots!",true);
		} catch(UnsupportedOperationException e) {
		}
	}
	
	
	@Test
	void testLNRoutputTraversal() {
		ArrayList <String> list = new ArrayList<>();
		TreeNode<String> root = tree.getRoot();
		tree.LNRoutputTraversal(root, list);
		ArrayList<String> copy = new ArrayList<>();
		copy = tree.toArrayList();
		
		assertEquals(copy,list);
		assertEquals("[d, b, e, a, f, c, g]",copy.toString());
	}
	
	
	

}
