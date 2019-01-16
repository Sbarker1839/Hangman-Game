package hangman;

import java.io.IOException;
import java.util.Scanner;
import java.util.Random;
import java.lang.StringBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Steven Barker
 */
public class Hangman {
    
    //Checks to see if all of users' guesses leads to correct word
    public static boolean check(String target,StringBuilder guess){
        
        String x = guess.toString();
        if(x.equals(target)){
            return true;
        }
        else
            return false;
    }
    
    public static void main(String[] args) throws IOException {
        
        String site = Jsoup.connect("http://watchout4snakes.com/wo4snakes/Random/RandomWord").get().html();
        Document doc = Jsoup.parse(site);
        String title = doc.title();
        System.out.println(title);
        
        Element body = doc.body();
        Element wordList = body.getElementById("result");
        
        Elements par = body.getElementsByTag("table");
        System.out.println(par.html());
        //Elements paragraphs = body.getAllElements();
        //for(Element paragraph : paragraphs){
        //    System.out.println(paragraph.text());
        //}
        
        System.out.println(wordList.attributes().html());
        //System.out.println(wordList.attributes());
        
        
        Random rand = new Random();
        Scanner scan = new Scanner(System.in);
        int n = rand.nextInt(7);
        
        
        String[] words = new String[8];
        words[0] = "intensity";
        words[1] = "christmas";
        words[2] = "wicked";
        words[3] = "instruction";
        words[4] = "application";
        words[5] = "lightweight";
        words[6] = "ultimate";
        words[7] = "quality";
     
        String targetWord = words[n];
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for(int i = 0;i<targetWord.length();i++){
                sb1.append("-");
            }
        boolean playing = false;
        
        System.out.println("Welcome to Hangman! \nPress 1 to start or 0 to exit");
        int start = scan.nextInt();
        int lives = 6;
        if(start == 1){
            playing = true;
        }
        Hangman.play(targetWord, lives, sb1, sb2, scan);
        
        while(playing = true){
            System.out.println("Would you like to play again?\nPress 1 to start or 0 to exit");
            start = scan.nextInt();
            if(start ==1){
                
                n = rand.nextInt(4);
                targetWord = words[n];
                StringBuilder sb3 = new StringBuilder();
                StringBuilder sb4 = new StringBuilder();
                for(int i = 0;i<targetWord.length();i++){
                sb3.append("-");
                }
                lives = 6;
                Hangman.play(targetWord, lives, sb3, sb4, scan);
            }
            else if(start == 0){
                System.out.println("Thanks for playing!");
                System.exit(0);
            }
            
        }
    }
    
    public static void play(String targetWord, int lives, StringBuilder sb1, StringBuilder sb2, Scanner scan){
        System.out.println("You have "+lives+" lives to figure out your word");
        StringBuilder sb5 = new StringBuilder();
            System.out.println("Your word is: "+ sb1);
            while (lives > 0){
                
                System.out.println("Enter a letter to guess, or type \"guess\" to enter your guess");
                
                String answer = scan.next();
                
                
                
                if(answer.equals("guess")){
                        System.out.println("What is your guess");
                        String guess = scan.next();
                        
                        if(guess.equals(targetWord)){
                            System.out.println("YOU WIN! Your word was: " +targetWord);
                            return;
                            
                        }
                        else{
                            lives = lives -1;
                            
                            System.out.println("Sorry that guess was incorrect.\nYou have "+lives+" lives remaining");
                            System.out.println("Your word is "+sb1);
                            
                        }
                            
                    }
                else{
                    int correctCount = 0;
                    int prevGuess = 0;
                    
                    for(int i = 0;i<sb5.length();i++){
                        
                        if(sb5.toString().contains(answer)){
                            prevGuess++;
                            correctCount++;
                        }
                    }
                    for(int i = 0;i<targetWord.length();i++){
                        char place = targetWord.charAt(i);
                        String x = Character.toString(place);
                        
                        
                        if(answer.equals(x)){
                            sb1.deleteCharAt(i);
                            sb1.insert(i, answer);
                            sb5.append(answer);
                            correctCount++;
                        }
                    
                    }
                    
                    if(correctCount>0 && prevGuess == 0){
                        System.out.println("Good guess! Your word now is "+sb1);
                        System.out.println("Your past guesses are: " + sb2);
                    }
                    else if(correctCount>0&& prevGuess>0){
                        System.out.println("You already guessed that silly! Try again.");
                        System.out.println("Your word is "+sb1);
                    }
                    
                    if(Hangman.check(targetWord,sb1) == true){
                        System.out.println("You win! the word was "+targetWord);
                        return;
                    }
                    
                    
                    if(correctCount == 0){
                            lives = lives -1;
                            System.out.println("Sorry that guess was incorrect.\nYou have "+lives+" lives remaining");
                            System.out.println("Your word is "+sb1);
                            sb2.append(answer +", ");
                            sb5.append(answer);
                            System.out.println("Your past guesses are: " + sb2);
                        }
                }
            }
            System.out.println("Oh no you are out of lives that means you lose!");
            System.out.println("Your word was "+targetWord);
        }
    }
    

