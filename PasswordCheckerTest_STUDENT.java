import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author 
 *
 */
public class PasswordCheckerTest_STUDENT {
	String password = "JakeK";
	String passwordConfirm = "jakek";
	String allCaps = "JAKEK";
	String withDigit = "JakeK1";
	String withSpecialChar = "JakeK!";
	String withNoDuplicate = "GoodBye!";
	String between6And9Chars = "JavaisFun";
	String longPassword = "JavaisFunveryfun";
	ArrayList<String> invalidPasswordsArray;

	@Before
	public void setUp() throws Exception {
		String[] containsInvalidPwd = {"jakek", "whenitrainsitpours", "11111111111", "!@#$%^&", "NICE", "4wardMarch"};
		invalidPasswordsArray = new ArrayList<String>();
		invalidPasswordsArray.addAll(Arrays.asList(containsInvalidPwd));	
	}

	@After
	public void tearDown() throws Exception {
		invalidPasswordsArray = null;
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 * @throws LengthException 
	 */
	@Test
	public void testIsValidPasswordTooShort() throws LengthException
	{
		assertTrue(PasswordCheckerUtility.isValidLength("JakeKib1!"));
		assertTrue(PasswordCheckerUtility.isValidLength("Jak1!"));
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 * @throws NoUpperAlphaException 
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha() throws NoUpperAlphaException
	{
		assertTrue(PasswordCheckerUtility.hasUpperAlpha(allCaps));
		assertTrue(PasswordCheckerUtility.hasUpperAlpha(passwordConfirm));
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 * @throws NoLowerAlphaException 
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha() throws NoLowerAlphaException
	{
		assertTrue(PasswordCheckerUtility.hasLowerAlpha(password));
		assertTrue(PasswordCheckerUtility.hasLowerAlpha(allCaps));
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 * @throws InvalidSequenceException 
	 * @throws NoSpecialCharacterException 
	 * @throws NoDigitException 
	 * @throws NoLowerAlphaException 
	 * @throws NoUpperAlphaException 
	 * @throws LengthException 
	 * @throws WeakPasswordException 
	 */
	@Test
	public void testIsWeakPassword() throws InvalidSequenceException, WeakPasswordException, LengthException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException
	{
		assertTrue(PasswordCheckerUtility.isWeakPassword("JakeKib123!"));
		assertTrue(PasswordCheckerUtility.isWeakPassword("JakeKib1!"));
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()  throws InvalidSequenceException
	{
		assertTrue(PasswordCheckerUtility.hasSameCharInSequence("JakeKibunja"));
		assertTrue(PasswordCheckerUtility.hasSameCharInSequence("JaaakeKibunja"));
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 * @throws NoDigitException 
	 */
	@Test
	public void testIsValidPasswordNoDigit() throws NoDigitException
	{
		assertTrue(PasswordCheckerUtility.hasDigit(withDigit));
		assertTrue(PasswordCheckerUtility.hasDigit(password));
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 * @throws InvalidSequenceException 
	 * @throws NoSpecialCharacterException 
	 * @throws NoDigitException 
	 * @throws NoLowerAlphaException 
	 * @throws NoUpperAlphaException 
	 * @throws LengthException 
	 * @throws WeakPasswordException 
	 */
	@Test
	public void testIsValidPasswordSuccessful() throws WeakPasswordException, LengthException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException
	{
		assertTrue(PasswordCheckerUtility.isValidPassword("JakeKib123!"));
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 * @throws InvalidSequenceException 
	 * @throws NoSpecialCharacterException 
	 * @throws NoDigitException 
	 * @throws NoLowerAlphaException 
	 * @throws NoUpperAlphaException 
	 * @throws LengthException 
	 * @throws WeakPasswordException 
	 */
	@Test
	public void testInvalidPasswords() throws WeakPasswordException, LengthException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException {
		ArrayList<String> results;
		results = PasswordCheckerUtility.getInvalidPasswords(invalidPasswordsArray);
		assertEquals(results.size(), 6);
		}
	
}
