/*
* @author deadfishh
*
* this is wordle
* it's done in the scanner
* it would probably be better with js
* or at least joption pane maybe i'll do that
* fuck around find out!
* to set the phrase set String word to something
*/


import java.util.ArrayList;
import java.util.Scanner;


public class Wordle

{
    public static void main(String[] args)
    {
        ArrayList<String> letters = new ArrayList<String>();
        System.out.println("\nwelcone to wordle, rachel edition!");
        System.out.println("* means the letter is not there");
        System.out.println("+ means the letter is there, but in the wrong spot");
        System.out.println("type \"quit\" to quit");
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        Scanner scanner = new Scanner(System.in);
        
        for (int i = 0; i < alphabet.length(); i++)
        {
            letters.add(alphabet.substring(i, i + 1));
        }
        
        String word = "track";
        System.out.println("the amount of letters in the wordle is " + word.length());
        for (int r = 1; r < 7; r++)
        {
            String reply = "";
            String guess = scanner.nextLine();
            if (guess.equals("quit"))
            {
                return;
            }
            
            if (guess.equals(word))
            {
                System.out.println("you got it!!! you got the wordle on try #" + r + "!!! how splendid :)");
                return;
            }
        
            if (word.length() != guess.length())
            {
                System.out.println("the word length is " + word.length() + ", i told you this.");
                System.out.println("you're getting kicked out now.");
                return;
            }
        
            for (int i = 0; i < guess.length(); i++)
            {
                boolean ahead = true;
                if (guess.substring(i, i + 1).equals(word.substring(i, i + 1)))
                {
                    reply += guess.substring(i, i + 1);
                    ahead = false;
                }
                if (ahead)
                {
                    for (int j = 0; j < word.length(); j++)
                    {
                        if (guess.substring(i, i + 1).equals(word.substring(j, j + 1)))
                        {
                            reply += "+";
                            ahead = false;
                        }
                    }
                }
                if (ahead)
                {
                    reply += "*";
                    for (int a = 0; a < letters.size(); a++)
                    {
                        if (guess.substring(i, i + 1).equals(letters.get(a)))
                        {
                            letters.remove(a);
                        }
                    }
                }
            }
            System.out.println(reply);
            System.out.println("the available letters are " + array2string(letters));
        }
        System.out.println("sorry, you lose.  the word was " + word + ". oh well.  try again next time");
    }
    public static String array2string(ArrayList<String> words)
    {
        String answer = "";
        for (int i = 0; i < words.size(); i++)
        {
            answer += words.get(i) + " ";
        }
        return answer;
    }
}

