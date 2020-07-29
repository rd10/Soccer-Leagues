package com.example;

public class Validate {

    static int isInteger(String amount){
        int num=0;
        try {
            num = Integer.parseInt(amount);
        } catch (NumberFormatException nfe){
            throw new IllegalArgumentException(String.format("%s is not an integer", amount));
            //System.out.printf("%s is not an integer %n", amount);
        }
        return num;
    }

    static boolean isRangeValid(int min, int max, int amount) {
        if (amount < min || amount > max) {
            return false;
        }
        return true;
    }

    static char validateGuess(char letter){
        if(!Character.isLetter(letter)){
            throw new IllegalArgumentException("A letter is required");
        }
        letter = Character.toLowerCase(letter);
        if(letter != 'n' && letter != 'y'){
            throw new IllegalArgumentException(letter + " is an invalid letter");
        }

        return letter;
    }
}
