package com.example;

public class Validate {

    static int convertToInteger(String amount){
        int num=100;
        try {
            num = Integer.parseInt(amount);
        } catch (NumberFormatException nfe){
            //throw new IllegalArgumentException(String.format("%s is not an integer", amount));
            System.out.printf("%s is not an integer %n", amount);
        }
        return num;
    }

    static boolean isRangeValid(int min, int max, int amount) {
        if (amount < min || amount > max) {
            return false;
        }
        return true;
    }

    static boolean validateLetter(char letter){
        if(!Character.isLetter(letter)){
            //throw new IllegalArgumentException("A letter is required");
            System.out.print("A letter is required. Please input 'y' or 'n':");
            return false;
        }
        letter = Character.toLowerCase(letter);
        if(letter != 'n' && letter != 'y'){
            //throw new IllegalArgumentException(letter + " is an invalid letter");
            System.out.printf("%s is an invalid letter. Please input 'y' or 'n':", letter);
            return false;
        }

        return true;
    }
}
