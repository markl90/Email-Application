package utils;

import java.util.Random;

/**
 * Utility class to generate a random alpha/numeric password containing:
 * upper and lowercase letters, numbers and special characters.
 */
public class PasswordGenerator {

    private final String characters = "abcdefghijklmnopqrstuvwxyz";
    private final String numbers = "1234567890";
    private final String specialCharacters = "!£$%&@";

    private String randomPassword;
    private Random randomNumber;

    public PasswordGenerator(){
        this.randomNumber = new Random();

    }

    public String generatePassword(){
        this.randomPassword = generateRandomPassword(characters);
        this.randomPassword = capitaliseRandomLetters(this.randomPassword);
        this.randomPassword = addNumbers(this.randomPassword);
        this.randomPassword = addSpecialCharacters(this.randomPassword);
        return this.randomPassword;
    }


    /**Sets password length between 8 and 12
     * @return int between 8 and 12
     */
    private int setRandomLength(){
       int lowerLimit = 8;
       int randomLimitedInt = 0;
        do {
           randomLimitedInt = new Random().nextInt(13);
       } while (randomLimitedInt < lowerLimit);
       return randomLimitedInt;
    }

    /**
     * Generates a password of random length (bounded 8-12 characters)
     * takes the characterset of potential character choices as a string
     * @param characterSet
     * @return
     */
    private String generateRandomPassword(String characterSet){
        int length = setRandomLength();
        char[] password = new char [length];
        for (int i = 0; i<length; i++){
           int random = new Random().nextInt(characterSet.length());
           password[i] = characterSet.charAt(random);
        }
        return new String(password);
    }

    /**
     * Method to take a generated password and capitalise a random selection of characters.
     * bounded between a minimum of a single capitalisation and a maximum of one less than the entire password.
     * @param password String containing the password.
     * @return String containing the new upper and lowercase password.
     */
    private String capitaliseRandomLetters(String password){
        char[] passwordArray = password.toCharArray();
        int numberOfCapitals = randomNumber.nextInt(password.length());
        if (numberOfCapitals == 0){
            numberOfCapitals++;
        }
        if (numberOfCapitals == password.length()){
            numberOfCapitals--;
        }
        for (int i =0; i<numberOfCapitals; i++){
            int randomArrayPosition = randomNumber.nextInt(password.length());
            while (!Character.isLetter(passwordArray[randomArrayPosition]) || Character.isUpperCase(passwordArray[randomArrayPosition])){
                randomArrayPosition = randomNumber.nextInt(password.length());
            }
            passwordArray[randomArrayPosition] = Character.toUpperCase(passwordArray[randomArrayPosition]);
        }
        return new String(passwordArray);
    }


    /**
     * Method to introduce numerical characters into a password.
     * Replaces between one and three of the alpha characters with numeric.
     * @param password String containing the password.
     * @return String containg the new alpha numeric password.
     */
    private String addNumbers(String password){
        char[] numbers = this.numbers.toCharArray();
        int numberOfSubstitutions = 0;
        while(numberOfSubstitutions == 0){
            numberOfSubstitutions = randomNumber.nextInt(4);
        }
        char [] passwordArray = password.toCharArray();
        for(int i =0; i<numberOfSubstitutions; i++){
            passwordArray[randomNumber.nextInt(password.length())] = numbers[randomNumber.nextInt(numbers.length)];
        }
        return new String(passwordArray);
    }

    /**
     * Method to introduce numeric characters into a password.
     * Will replace between one and three of the existing alpha characters with special characters,
     * will avoid replacing numeric characters to mantain alpha/numeric password.
     * @param password
     * @return
     */
    private String addSpecialCharacters(String password){
        char[] specialCharacters = this.specialCharacters.toCharArray();
        int numberOfSubstitutions = 0;
        while(numberOfSubstitutions == 0){
            numberOfSubstitutions = randomNumber.nextInt(4);
        }
        char [] passwordArray = password.toCharArray();
        for(int i =0; i<numberOfSubstitutions; i++){
            int indexPosition = randomNumber.nextInt(password.length());
            while (Character.isDigit(password.charAt(indexPosition))){
                indexPosition = randomNumber.nextInt(password.length());
            }
            passwordArray[indexPosition] = specialCharacters[randomNumber.nextInt(specialCharacters.length)];
        }
        return new String(passwordArray);
    }


}
