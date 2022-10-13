/*
* @author deadfishh
*
* i copied this from andy
* i'm not sure why it's here it's not a game
* but that's okay
*/

import java.util.*;
public class Scramble
{
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("type in a sentence that you want to be scrambled!");
        boolean ahead = true;
        while (ahead)
        {
            String sentence = scanner.nextLine();
            if (sentence.equals("quit"))
            {
                return;
            }
            execute(sentence);
        }
    }
    
    public static void execute(String sentence)
    {
        int place = 0;
        String result = "";
        for (int i = 0; i < sentence.length(); i++)
        {
            if (sentence.charAt(i) == ' ')
            {
                result += mixWord(sentence.substring(place, i)) + " ";
                place = i + 1;
            }
            else if (i == sentence.length() - 1)
            {
                result += mixWord(sentence.substring(place));
            }
        }
        System.out.println(result);
    }
    
    public static String mixWord(String word)
    {
        ArrayList<Character> list = getList(word);
        String result = list.get(0) + "";
        list.remove(0);
        if (list.size() == 0)
        {
            return result;
        }
        while (list.size() > 1)
        {
            int num = (int)(Math.random() * (list.size() - 1));
            result += list.get(num);
            list.remove(num);
            //i--;
        }
        result += list.get(0);
        return result;
    }
    
    public static ArrayList<Character> getList(String word)
    {
        ArrayList<Character> list = new ArrayList<Character>();
        for (int i = 0; i < word.length(); i++)
        {
            list.add(word.charAt(i));
        }
        return list;
    }
}
