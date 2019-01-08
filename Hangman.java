/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import java.util.Scanner;
import java.util.Random;
import java.lang.StringBuilder;
/**
 *
 * @author Setup
 */
public class Hangman {
    
    public static boolean check(String target,StringBuilder guess){
        
        String x = guess.toString();
        if(x.equals(target)){
            return true;
        }
        else
            return false;
    }
    
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner scan = new Scanner(System.in);
        int n = rand.nextInt(4);
        //System.out.println(n);
        
        String[] words = new String[5];
        words[0] = "scientific";
        words[1] = "christmas";
        words[2] = "station";
        words[3] = "instruction";
        words[4] = "application";
     
        String targetWord = words[n];
        StringBuilder sb1 = new StringBuilder();
        for(int i = 0;i<targetWord.length();i++){
                sb1.append("-");
        }
        StringBuilder sb2 = new StringBuilder();
        
        System.out.println("Welcome to Hangman! \nPress 1 to start or 0 to exit");
        int start = scan.nextInt();
        int lives = 6;
        
        if(start == 1){
            System.out.println("You have "+lives+" guesses to figure out your word");
            System.out.println("Your word is: "+ sb1);
            while (lives > -1){
                
                System.out.println("Enter a letter to guess, or type \"guess\" to enter your guess");
                
                String answer = scan.next();
                
                if(answer.equals("guess")){
                        System.out.println("What is your guess");
                        String guess = scan.next();
                        
                        if(guess.equals(targetWord)){
                            System.out.println("YOU WIN! Your word was: " +targetWord);
                            System.exit(0);
                        }
                        else{
                            lives = lives -1;
                            System.out.println("Sorry that guess was incorrect.\n You have "+lives+" guess remaining");
                            System.out.println("Your word is "+sb1);
                            
                        }
                            
                    }
                else{
                    int correctCount = 0;
                    for(int i = 0;i<targetWord.length();i++){
                        char place = targetWord.charAt(i);
                        String x = Character.toString(place);
                        
                        
                        if(answer.equals(x)){
                            sb1.deleteCharAt(i);
                            sb1.insert(i, answer);
                            System.out.println("Good guess! Your word now is "+sb1);
                            correctCount++;
                        }
                    
                    }
                    if(Hangman.check(targetWord,sb1) == true){
                        System.out.println("You win! the word was "+targetWord);
                        break;
                    }
                    
                    
                    if(correctCount == 0){
                            lives = lives -1;
                            System.out.println("Sorry that guess was incorrect.\n You have "+lives+" guess remaining");
                            System.out.println("Your word is "+sb1);
                        }
                }
            }
            System.out.println("Oh no you are out of lives that means you lose!");
            System.out.println("Your word was "+targetWord);
        }
    }
    
}
