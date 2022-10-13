 
import java.util.ArrayList;
import java.util.Scanner;

public class hangman
{
    private static String phrase = "track meet: won!";
    private static String screenphrase = "e";
    private static String[] arrayphrase = new String[phrase.length()];
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<String> letters = new ArrayList<String>();
    private static boolean finished = false;
    public static void main()
    {
        System.out.println("new");
        makealphabet();
        makearray(phrase);
        printlines();
        getguess();
        while (!finished)
        {
            getguess();
        }
        goodbye();
    }
    
    public static void getguess()
    {
        boolean redo = true;
        String letter = "";
        while (redo)
        {
            String temp = scanner.nextLine();
            redo = false;
            if (lettercheck(temp))
            {
                System.out.println("that's more than one letter.");
                System.out.println("try again.");
                redo = true;
            }
            if (!redo && guessedcheck(temp))
            {
                System.out.println("you already guessed that letter.");
                System.out.println("try again.");
                redo = true;
            }
            if (!redo)
            {
                letter = temp;
            }
        }
        boolean notthere = true;
        for (int i = 0; i < arrayphrase.length; i++)
        {
            if (arrayphrase[i] != null && arrayphrase[i].equals(letter))
            {
                arrayphrase[i] = null;
                notthere = false;
                removeletter(letter);
            }
        }
        if (notthere)
        {
            System.out.println("nope, there's no \"" + letter + "\".");
            removeletter(letter);
        }
        else
        {
            System.out.println("you got a letter! nice!");
        }
        printlines();
        printletters();
        if (checkfinished())
        {
            return;
        }
    }
    
    private static boolean lettercheck(String letter)
    {
        if (letter.length() > 1)
        {
            return true;
        }
        return false;
    }
    
    private static boolean guessedcheck(String letter)
    {
        for (int i = 0; i < letters.size(); i++)
        {
            if (letters.get(i).equals(letter))
            {
                return false;
            }
        }
        return true;
    }
    
    public static void removeletter(String letter)
    {
        for (int i = 0; i < letters.size(); i++)
        {
            if (letters.get(i).equals(letter))
            {
                letters.remove(i);
            }
        }
    }
    
    private static void makealphabet()
    {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < alphabet.length(); i++)
        {
            letters.add(alphabet.substring(i, i + 1));
        }
    }
    
    private static void makearray(String phrase)
    {
        for (int i = 0; i < phrase.length(); i++)
        {
            arrayphrase[i] = phrase.substring(i, i + 1);
        }
    }
    
    public static boolean checkfinished()
    {
        String[] temp = arrayphrase;
        for (int i = 0; i < temp.length; i++)
        {
            if (temp[i] != null && temp[i].equals(" "))
            {
                temp[i] = null;
            }
        }
        for (int i = 0; i < temp.length; i++)
        {
            if (temp[i] != null)
            {
                return false;
            }
        }
        finished = true;
        return true;
    }
    
    public static void printlines()
    {
        String line = "▁▁▁";
        System.out.println();
        for (int i = 0; i < phrase.length(); i++)
        {
            boolean quit = false;
            String temp = phrase.substring(i, i + 1);
            switch (temp)
            {
                case " ":
                case ".":
                case ";":
                case ":":
                case ",":
                case "!":
                case "?":
                    System.out.print("  " + temp + " ");
                    quit = true;
                    break;
            }
            if (!quit && arrayphrase[i] == null)
            {
                System.out.print(" " + phrase.substring(i, i + 1) + "  ");
            }
            else if (!quit)
            {
                System.out.print(line + " ");
            }
        }
        System.out.println("\n");
    }
    
    public static void printletters()
    {
        String s = "available letters:\n";
        for (int i = 0; i < letters.size(); i++)
        {
            s += letters.get(i) + " ";
            if ((i + 1) != 0 && (i + 1) % 5 == 0)
            {
                s += "\n";
            }
        }
        System.out.println(s);
    }
    
    public static void goodbye()
    {
        System.out.println("looks like the game is over.");
        System.out.println("nice job, thanks for playing!");
    }
}
