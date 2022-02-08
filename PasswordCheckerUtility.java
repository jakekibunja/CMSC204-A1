import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PasswordCheckerUtility {
	
	//Compare equality of two passwords
	public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException{
		boolean samePassword = password.equals(passwordConfirm);
		try {
			if (samePassword == false) {
				throw new UnmatchedException("The passwords do not match.");
			}
		}
		catch (UnmatchedException exception) {
			exception.printStackTrace();
		}
	}
	
	//Compare equality of two passwords
	public static boolean comparePasswordsWithReturn(String password, String passwordConfirm){
		boolean samePassword = password.equals(passwordConfirm);
		return samePassword;
	}
	
	//Checks the password length requirement - – The password must be at least 6 characters long
	public static boolean isValidLength(String password) throws LengthException{
		boolean validLength = true;
		try {
			if (password.length() < 6) {
				validLength = false;
				throw new LengthException("The password must be at least 6 characters long");
			}
		}
		catch (LengthException exception) {
			exception.printStackTrace();
		}
			return validLength;
	}
	
	//Weak password length check - Password contains 6 to 9 characters , still considers valid, just weak
	public static boolean hasBetweenSixAndNineChars(String password) {
		if (password.length() > 5 && password.length() < 10) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//Checks the password lowercase requirement - Password must contain a lowercase alpha character
	public static boolean hasLowerAlpha(String password)throws NoLowerAlphaException{
		String lowerPassword = password.toLowerCase();
		boolean hasLower = false;
		char pass;
		
		for (int i = 0; i < password.length(); i++) {
			pass = password.charAt(i);
			if (Character.isLowerCase(pass)) {
				hasLower = true;
			}
		}
		try {
			if (hasLower == false) {
				throw new NoLowerAlphaException("The password must contain at least one lowercase alphabetic character");
			}
		}
		catch (NoLowerAlphaException exception) {
			exception.printStackTrace();
		}
		
		return hasLower;
	}

	//Checks the password alpha character requirement - Password must contain an uppercase alpha character
	public static boolean hasUpperAlpha(String password)throws NoUpperAlphaException{
		String upperPassword = password.toUpperCase();
		boolean hasUpper = false;
		char pass;
		
		for (int i = 0; i < password.length(); i++) {
			pass = password.charAt(i);
			if (Character.isUpperCase(pass)) {
				hasUpper = true;
			}
		}
		try {
			if (hasUpper == false) {
				throw new NoUpperAlphaException("The password must contain at least one uppercase alphabetic character");
			}
		}
		catch (NoUpperAlphaException exception) {
			exception.printStackTrace();
		}
		
		return hasUpper;
	}

	//Checks the password Digit requirement - Password must contain a numeric character
	public static boolean hasDigit(String password)throws NoDigitException {
		boolean hasDigit = false;
		hasDigit = password.matches(".*\\d.*");
		try {
			if (hasDigit == false) {
				throw new NoDigitException("The password must contain at least one digit");
			}
		}
		catch (NoDigitException exception) {
			exception.printStackTrace();
		}
		return hasDigit;
	}
	
	//Checks the password Sequence requirement - Password should not contain more than 2 of the same character in sequence
	public static boolean hasSameCharInSequence(String password) throws InvalidSequenceException{
		boolean sameChar = false;
		for (int i = 0; i < (password.length() - 2); i++) {
			if (password.charAt(i) == password.charAt(i+1)) {
				if (password.charAt(i) == password.charAt(i+2))
					sameChar = true;
			}
		}
		try {
			if (sameChar == true) {
				throw new InvalidSequenceException("The password cannot contain more than two of the same character in sequence.");
			}
		}
		catch (InvalidSequenceException exception) {
			exception.printStackTrace();
		}
		return sameChar;
	}
	
	//Checks the password SpecialCharacter requirement - Password must contain a Special Character
	public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException{
		boolean hasSpecial = false;
		
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
		Matcher matcher = pattern.matcher(password);
		hasSpecial = !matcher.matches();
		
		try {
			if (hasSpecial == false) {
				throw new NoSpecialCharacterException("The password must contain at least one special character.");
			}
		}
		catch (NoSpecialCharacterException exception) {
			exception.printStackTrace();
		}

		return hasSpecial;
	}
	
	//Reads a file of passwords and the passwords that failed the check will be added to an invalidPasswords with the reason
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) throws WeakPasswordException, LengthException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException{
		ArrayList<String> invalidPasswords = new ArrayList<String>();
		
		for (int i = 0; i < passwords.size(); i++) {
			if (!isValidPassword(passwords.get(i))) {
				invalidPasswords.add(passwords.get(i));
			}
		}
		return invalidPasswords;
	}
	
	//Checks if password is valid but between 6 -9 characters
	public static boolean isWeakPassword(String password) throws WeakPasswordException,LengthException,
    NoUpperAlphaException,NoLowerAlphaException,NoDigitException,NoSpecialCharacterException,InvalidSequenceException{
		
		boolean weakPass = false;
		
		isValidLength(password);
		hasUpperAlpha(password);
		hasLowerAlpha(password);
		hasDigit(password);
		hasSpecialChar(password);
		hasSameCharInSequence(password);
		hasLowerAlpha(password);
		
		try {
			if (hasBetweenSixAndNineChars(password) ) {
				weakPass = true;
				throw new WeakPasswordException("The password is OK but weak - it contains fewer than 10 characters.");
			}	
		}
		catch (WeakPasswordException exception) {
			exception.printStackTrace();
			}
			return weakPass;
	}
		
	
	//Return true if valid password (follows all rules from above), returns false if an invalid password
	public static boolean isValidPassword(String password) throws WeakPasswordException,LengthException,
    NoUpperAlphaException,NoLowerAlphaException,NoDigitException,NoSpecialCharacterException,InvalidSequenceException{
		boolean isValid = true;
		
		if (isValidLength(password) == false ) {
			isValid = false;
		}
		if (hasUpperAlpha(password) == false ) {
			isValid = false;
		}
		if (hasLowerAlpha(password) == false ) {
			isValid = false;
		}
		if (hasDigit(password) == false ) {
			isValid = false;
		}
		if (hasSpecialChar(password) == false ) {
			isValid = false;
		}
		if (hasSameCharInSequence(password) == false ) {
			isValid = false;
		}
		if (isWeakPassword(password) == false ) {
			isValid = false;
		}
		
		return isValid;
	}
	
}

