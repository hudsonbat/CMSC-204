/**
 * This class is the utility for the MorseCodeTree class. It defines
 * methods that act on and make use of the data structure class (i.e. 
 * MorseCodeTree class). It contains a method that displays the characters 
 * of the MorseCodeTree in inorder order, and two methods that convert Morse 
 * code into English, one doing so based on a file containing Morse code and
 * the other based on a string of Morse code.
 * @author hudson
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MorseCodeConverter {
	
	private static MorseCodeTree tree = new MorseCodeTree();
	
	/**
	 * This method displays the characters 
	 * of the MorseCodeTree in inorder order.
	 * @return The inorder order of the Morse Code tree.
	 */
	
	public static String printTree() {
		
		String str = "";
		ArrayList<String> list = new ArrayList<>();
		list = tree.toArrayList();
		
		for(int i=0; i<list.size(); i++) {
			str += list.get(i) + " ";
		}
		
		return str;
	}
	
	/**
	 * This method takes a string of Morse code as a parameter
	 * and returns the English translation of this Morse code. 
	 * @param code The Morse code to be converted into English.
	 * @return The English translation of the Morse code. 
	 */
	
	public static String convertToEnglish(String code) {
		String str = "";
		int index = 0;
		String [] code2 = null;
		
		Scanner scan = new Scanner(code);
		while(scan.hasNextLine()) {
			code2 = scan.nextLine().split(" ");
		}
		
		scan.close();
		
		while(index < code2.length) {
			if(code2[index].equals("/")) {
				str += " ";
			} else {
				str += tree.fetch(code2[index]);
			}
			index++;
		}
		
		return str;
	}
	
	/**
	 * This method takes a file of Morse code as a parameter
	 * and returns the English translation of this Morse code.
	 * @param codeFile The file containing Morse code.
	 * @return The English translation of the Morse code contained in the file.
	 * @throws FileNotFoundException If the file passed as an argument cannot be found.
	 */
	
	public static String convertToEnglish(File codeFile) throws FileNotFoundException{
		String [] str = null;
		int index = 0;
		Scanner scan = new Scanner(codeFile);
		
		while(scan.hasNext()) {
			str = scan.nextLine().split(" ");
		}
		
		scan.close();
		
		String str2 = "";
		
		while(index < str.length) {
			if(str[index].equals("/")) {
				str2 += " ";
			} else {
				str2 += tree.fetch(str[index]);
			}
			index++;
		}
		
		return str2;
	}

}
