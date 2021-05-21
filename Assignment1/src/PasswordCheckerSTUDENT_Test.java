import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author 
 *
 */
public class PasswordCheckerSTUDENT_Test {
	
	ArrayList<String> passwordsArray;
	String lessThanSix = "Hello";
	String greaterThanSix = "hello13%";
	String upperCase = "HELLO12";
	String noUppercase = "hello6";
	String noDigit2 = "Goodbye";
	String noSpecialChar = "GoodBye2";
	String weak = "Hello12$";
	String strong = "Heelloo2$";
	String validPassword1 = "Hello123$";
	String validPassword2 = "Goodbye6%";
	String noDigit = "HeLlo$!";
	String hasDigit = "HeLlo$3";
	String validSeq = "HheeLLo1";
	String invalidSeq = "HHHelloo1!";

	@Before
	public void setUp() throws Exception {
		String[] p = {lessThanSix,greaterThanSix,upperCase,noUppercase,noDigit2,noSpecialChar,weak,strong,validPassword1,
				validPassword2,noDigit,hasDigit,validSeq,invalidSeq};
		passwordsArray = new ArrayList<String>();
		passwordsArray.addAll(Arrays.asList(p));
	}

	@After
	public void tearDown() throws Exception {
		passwordsArray = null;
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		
		try {
			assertTrue(PasswordCheckerUtility.isValidLength(greaterThanSix));
			assertTrue(PasswordCheckerUtility.isValidLength(lessThanSix));
		} catch (LengthException e) {
			e.printStackTrace();
			}
	}

	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		try {
			assertTrue(PasswordCheckerUtility.hasUpperAlpha(noUppercase));
			assertTrue(PasswordCheckerUtility.hasUpperAlpha(upperCase));
		} catch (NoUpperAlphaException e) {
			e.printStackTrace();
			}
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		try {
			assertTrue(PasswordCheckerUtility.hasLowerAlpha(upperCase));
			assertTrue(PasswordCheckerUtility.hasLowerAlpha(noUppercase));
		} catch (NoLowerAlphaException e) {
			e.printStackTrace();
			}
	}
	/**
	 * Test if the password is OK but has between 6 to 9 characters.
	 * This test should throw a WeakPasswordException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		try {
			assertTrue(PasswordCheckerUtility.isWeakPassword(weak));
			assertTrue(PasswordCheckerUtility.isWeakPassword(strong));
		} catch (WeakPasswordException e) {
			e.printStackTrace();
			}
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		try {
			assertFalse(PasswordCheckerUtility.hasSameCharInSequence(validSeq));
			assertTrue(PasswordCheckerUtility.hasSameCharInSequence(invalidSeq));
		} catch (InvalidSequenceException e) {
			e.printStackTrace();
			}
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		try {
			assertTrue(PasswordCheckerUtility.hasDigit(hasDigit));
			assertTrue(PasswordCheckerUtility.hasDigit(noDigit));
		} catch (NoDigitException e) {
			e.printStackTrace();
			}
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword(validPassword1));
			assertTrue(PasswordCheckerUtility.isValidPassword(validPassword2));
		} catch (Exception e) {
			e.printStackTrace();
			}
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		ArrayList<String> results;
		results = PasswordCheckerUtility.getInvalidPasswords(passwordsArray);
		assertEquals(results.size(), 9);
		assertEquals(results.get(0), "Hello -> The password must be at least 6 characters long");
		assertEquals(results.get(1), "hello13% -> The password must contain at least one uppercase alphabetic character");
		assertEquals(results.get(2), "HELLO12 -> The password must contain at least one lower case alphabetic character");
		assertEquals(results.get(3), "hello6 -> The password must contain at least one uppercase alphabetic character");
		assertEquals(results.get(4), "Goodbye -> The password must contain at least one digit"); 
		assertEquals(results.get(5), "GoodBye2 -> The password must contain at least one special character");
		assertEquals(results.get(6), "HeLlo$! -> The password must contain at least one digit");
		assertEquals(results.get(7), "HheeLLo1 -> The password must contain at least one special character");
		assertEquals(results.get(8), "HHHelloo1! -> The password cannot contain more than two of the same character in sequence");
	}
	
}