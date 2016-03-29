package com.cg.springmvc;

import java.util.Random;

public class OTPGenerator {
	public static int OTP_CHARACTER_LENGTH=8;
	
	public static void main(String[] args) {
		System.out.println(generateOTP());

	}


    protected OTPGenerator() {
    }

    public static String generateOTP() {
    	String capitalAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    	String alphbet = "abcdefghijklmnopqrstuvwxyz";
    	String numbers = "0123456789";
    	String specialCharacters = "!@#$%^&*()~";
    	
    	String capChars = getRandomChars(2, capitalAlphabet);
    	String alph = getRandomChars(2, alphbet);
    	String num = getRandomChars(2, numbers);
    	String specChars = getRandomChars(2, specialCharacters);
        String characterString = 
		        new String(capChars+alph+num+specChars); 
        return characterString;
    }
    
    public static String getRandomChars(int size, String string){
    	StringBuilder generatedToken = new StringBuilder();
		Random r = new Random();
		for (int i=0; i<size; i++) 
			generatedToken.append(string.charAt(r.nextInt(string.length())));
		
		return generatedToken.toString();
    }
}