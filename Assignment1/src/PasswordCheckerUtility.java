/**
d * Thus class represents a password checking utility. The class contains a set of static methods that
 * represent different conditions that a password must satisfy. Each method accepts a password 
 * string as an argument and returns a boolean value depending on whether the condition being
 * checked is satisfied. Each method also throws a specific type of exception to designate the
 * reason a password does not satisfy a given requirement. The class also contains a method that 
 * determines the validity of a list of passwords as well as a method that checks whether two passwords 
 * are identical.
 * @author hudson
 */

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class PasswordCheckerUtility {
	
	/**
	 * No-arg constructor
	 */
	
	public PasswordCheckerUtility() {
		
	}
	
	
	/**
	 * This method compares two passwords that it accepts as arguments to determine if they are equal.
	 * @param password The reference password to be compared.
	 * @param passwordConfirm The password to compare to the reference password.
	 * @throws UnmatchedException The exception thrown if the passwords do not match.
	 */
	
	public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException {
			if(!password.equals(passwordConfirm)) {
				throw new UnmatchedException("Passwords do not match");
			}
	}
	
	/**
	 * This method compares two passwords that it accepts as arguments to determine if they are equal. 
	 * If the passwords are equal, it returns true and if they differ, it returns false.
	 * @param password The reference password to be compared.
	 * @param passwordConfirm The password to compare the reference password to.
	 * @return True if the passwords are equal and false is the passwords are different.
	 */
	
	public static boolean comparePasswordsWithReturn(String password, String passwordConfirm) {
		
		boolean status = true;
		
			if(!password.equals(passwordConfirm)) {
				status = false;
			}
		
		return status;
	}
	
	/**
	 * This method accepts an arraylist of passwords as an argument, identifies each invalid password,
	 * and returns an arraylist containing each invalid password and the corresponding exception message
	 * for each invalid password.
	 * @param passwords The arraylist of passwords to be examined.
	 * @return The arraylist of invalid passwords and exception messages that each password caused.
	 */
	
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords){
		ArrayList<String> pws = new ArrayList<String>();
		
		for (int i = 0; i < passwords.size();i++) {
			try {
				if(PasswordCheckerUtility.isValidPassword(passwords.get(i))) {
				}
			}catch(RuntimeException e) {
				pws.add(passwords.get(i) + " -> " + e.getMessage());
			}
			
		}
		
		return pws;
	}
	
	/**
	 * This method accepts a password String argument and checks whether the password supplied
	 * is between 6 and 9 characters (inclusive).
	 * @param password The password whose length is examined.
	 * @return True if the password is between 6 and 9 characters long and false otherwise.
	 */
	
	public static boolean hasBetweenSixAndNineChars(String password) {
		
		boolean weak = false;
		
			if(password.length() >= 6 && password.length() <=9) {
				weak = true;
			}
		
		return weak;
	}
	
	/**
	 * This method accepts a password String as an argument and determines whether the
	 * password supplied contains a digit.
	 * @param password The password to be analyzed.
	 * @return True if the password contains a digit and false otherwise. 
	 * @throws NoDigitException
	 */
	
	public static boolean hasDigit(String password) throws NoDigitException{
		
		boolean result = false;
		
		String regex = "[0-9]+";
		
		Pattern pt = Pattern.compile(regex);
		Matcher mt = pt.matcher(password);
		
			if(mt.find()) {
				result = true;
			} else {
				throw new NoDigitException("The password must contain at least one digit");
			}
		
		return result;
		
	}
	
	/**
	 * This method accepts a password String as an argument and determines whether
	 * the password has a lowercase letter or not. 
	 * @param password The password to be checked.
	 * @return True if the password contains a lowercase letter and false otherwise.
	 * @throws NoLowerAlphaException The exception thrown if the password doesn't contain a lowercase letter.
	 */
	
	public static boolean hasLowerAlpha(String password) throws NoLowerAlphaException {
		boolean result = false;
		
		String regex = "[a-z]+";
		
		Pattern pt = Pattern.compile(regex);
		Matcher mt = pt.matcher(password);
		
			if(mt.find()) {
				result = true;
			} else {
				throw new NoLowerAlphaException("The password must contain at least one lower case alphabetic character");
			}
		
		return result;
		
	}
	
	/**
	 * This method accepts a password String as an argument and determines whether
	 * the password has a uppercase letter or not. 
	 * @param password The password to be checked.
	 * @return True if the password contains an uppercase letter and false otherwise.
	 * @throws NoUpperAlphaException The exception thrown if the password doesn't contain an uppercase letter.
	 */
	
	public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException{
		boolean result = false;
		
		String regex = "[A-Z]+";
		
		Pattern pt = Pattern.compile(regex);
		Matcher mt = pt.matcher(password);
		
			if(mt.find()) {
				result = true;
			} else {
				throw new NoUpperAlphaException("The password must contain at least one uppercase alphabetic character");
			}
		
		return result;
		
	}
	
	/**
	 * This method accepts a password String as an argument and determines whether
	 * the password contains the same character 3 or more times in a row.
	 * @param password The password to be checked.
	 * @return True if the password contains the same character 3 or more times in a row.
	 * @throws InvalidSequenceException The exception thrown when the password does contain the same character in a sequence of 
	 * 3 or more characters.
	 */
	
	
	public static boolean hasSameCharInSequence(String password) throws InvalidSequenceException {
		boolean result = true;
		
		String regex = "(\\w)\\1{2,}";
		
		Pattern pt = Pattern.compile(regex);
		Matcher mt = pt.matcher(password);
		
			if(mt.find()) {
				throw new InvalidSequenceException("The password cannot contain more than two of the same character in sequence");
			} else {
				result = false;
				}
		
		return result;
		
	}
	
	/**
	 * This method accepts a password String as an argument and determines whether
	 * the password contains a special character.
	 * @param password The password to be checked.
	 * @return True if the password does contain a special character and false otherwise.
	 * @throws NoSpecialCharacterException The exception thrown if the password doesn't contain a special character.
	 */
	
	
	public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException{
		boolean result = false;
		
		String regex = "[^\\w\\s]";
		
		Pattern pt = Pattern.compile(regex);
		Matcher mt = pt.matcher(password);
		
			if(mt.find()) {
				result = true;
			} else {
				throw new NoSpecialCharacterException("The password must contain at least one special character");
			}
		
		return result;
		
	}
	
	/**
	 * This method accepts a password String as an argument and determines whether
	 * the password length is greater than or equal to 6 characters.
	 * @param password The password to be checked.
	 * @return True if the password's length is 6 characters or more and false otherwise.
	 * @throws LengthException The exception thrown if the password is less than 6 characters long.
	 */
	
	public static boolean isValidLength(String password) throws LengthException{
		boolean status = false;
		if(password.length() >= 6) {
			status = true;
		} else {
			throw new LengthException("The password must be at least 6 characters long");
		}
		
		return status;
	}
	
	
	/**
	 * This method accepts a password String as an argument and determines if the password is
	 * valid by calling a set of methods defined in this class and passing the password as an
	 * argument to each method.
	 * @param password The password to be checked.
	 * @return True if the password is valid and false otherwise.
	 * @throws LengthException Exception thrown if the password is less than 6 characters long.
	 * @throws NoUpperAlphaException Exception thrown if the password doesn't contain a uppercase letter.
	 * @throws NoLowerAlphaException Exception thrown if the password doesn't contain a lowercase character.
	 * @throws NoDigitException Exception thrown if the password doesn't contain a digit.
	 * @throws NoSpecialCharacterException Exception thrown if the password doesn't contain a special character.
	 * @throws InvalidSequenceException Exception thrown if the password contains an invalid sequence.
	 */
	
	public static boolean isValidPassword(String password)
							 throws LengthException, 
									NoUpperAlphaException, 
									NoLowerAlphaException, 
									NoDigitException,
									NoSpecialCharacterException,
									InvalidSequenceException{
		
		boolean status = true;
		
		if (!isValidLength(password)) {
			status = false;
			throw new LengthException("The password must be at least 6 characters long");
		} else if(!hasUpperAlpha(password)) {
			status = false;
			throw new NoUpperAlphaException("The password must contain at least one uppercase alphabetic character");
		} else if(!hasLowerAlpha(password)) {
			status = false;
			throw new NoLowerAlphaException("The password must contain at least one lower case alphabetic character");
		}else if(!hasDigit(password)) {
			status = false;
			throw new NoDigitException("The password must contain at least one digit");
		} else if (!hasSpecialChar(password)) {
			status = false;
			throw new NoSpecialCharacterException("The password must contain at least one special character");
		} else if (hasSameCharInSequence(password)) {
			status=false;
			throw new InvalidSequenceException("The password cannot contain more than two of the same character in sequence");
		}
		
		return status;
		
	}
	
	/**
	 * This method accepts a password String as an argument and determines if the password is valid
	 * and if the password is weak (i.e. less than or equal to 9 characters long).
	 * @param password The password to be checked.
	 * @return True if the password is weak and false otherwise.
	 * @throws WeakPasswordException Exception thrown if the password is weak.
	 */
	
	public static boolean isWeakPassword(String password) throws WeakPasswordException{
		
		boolean status = true;
		
		if(isValidPassword(password) && password.length() > 9) {
			status = false;
		} else if(isValidPassword(password) && password.length() <= 9) {
			throw new WeakPasswordException("The password is OK but weak - it contains fewer than 10 characters");
		}
		return status;
	}
	

}
