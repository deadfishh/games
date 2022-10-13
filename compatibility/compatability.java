/*
* @author deadfishh
*
* this is the compatability test we did in linear algebra
* i just made an online version of it to make it easier
* it does stuff with dot products and vectors
* enjoy!
*/


import java.util.Scanner;

public class compatability
{
    public static void main(String[] args)
    {
        int[] rachel = {6, 8, -2, 6, -4, 2};
        int[] guest = new int[6];
        Scanner scanner = new Scanner(System.in);
        System.out.println("welcome to the compatability test!");
        System.out.println("you will be asked a series of questions");
        System.out.println("please enter your name.");
        String name = scanner.nextLine();
        System.out.println("#1: what is your favorite ap lit book?");
        System.out.println("the options are beowulf, hamlet, the things they carried, frankenstein, beloved, and the great gatsby");
        String book = scanner.nextLine().toLowerCase();
        boolean end = false;
        while (!end)
        {
            end = true;
            switch(book)
            {
                case "beowulf":
                    guest[0] = -4;
                    break;
                case "hamlet":
                    guest[0] = 6;
                    break;
                case "tttc":
                case "the things they carried":
                    guest[0] = 2;
                    break;
                case "frankenstein":
                case "fronk":
                    guest[0] = -6;
                    break;
                case "beloved":
                    guest[0] = 4;
                    break;
                case "gatsby":
                case "the great gatsby":
                    guest[0] = -2;
                    break;
                default:
                    end = false;
                    System.out.println("that's not an ap lit book, try again.");
                    book = scanner.nextLine();
            }
        }
        for (int i = 1; i < 6; i++)
        {
            String message = "";
            boolean positive = true;
            if (i % 2 == 0)
                positive = false;
            switch (i)
            {
                case 1:
                    message = "how strongly do you feel about this opinion, from 1 to 10?";
                    break;
                case 2:
                    message = "what is your opinion of matt lowry, from -6 to 6?";
                    break;
                case 3:
                    message = "and how strongly do you feel about this, from 1 to 10?";
                    break;
                case 4:
                    message = "what is your opinion of alvin and the chipmunks, slowed down edition, from -6 to 6?";
                    break;
                case 5:
                    message = "again, how strongly do you feel about this, 1 to 10?";
                    break;
            }
            System.out.println(message);
            String question = "";
            boolean number = false;
            while (!number)
            {
                try
                {
                    question = scanner.nextLine();
                    int q = Integer.parseInt(question);
                    number = true;
                    if ((positive && (q < 1 || q > 10)) || (!positive && (q < -6 || q > 6)))
                    {
                        System.out.println("that's not in range.  try again.");
                        number = false;
                    }
                }
                catch (NumberFormatException ex)
                {
                    System.out.println("i asked for a number, dipshit.  try again.");
                    number = false;
                }
            }
            int q = Integer.parseInt(question);
            guest[i] = q;
        }
        System.out.println("nice! you've finished the questioning portion!");
        double standard = 0;
        for (int i = 0; i < rachel.length; i++)
        {
            standard += (rachel[i])*(rachel[i]);
        }
        double dot = 0;
        for (int i = 0; i < rachel.length; i++)
        {
            dot += (rachel[i])*(guest[i]);
        }
        double percent = 1 - ((Math.abs(dot - standard))/(dot));
        double rpercent = Math.round(percent * 1000.0) / 10.0;
        System.out.println("your percent compatability with rachel is " + rpercent + "%");
        valuejudgment(rpercent);
    }
    
    public static void valuejudgment(double rpercent)
    {
        if (rpercent <= 0)
        {
            System.out.println("this absolutely sucks.  please never speak to me again.");
        }
        else if (rpercent > 0 && rpercent <= 25)
        {
            System.out.println("hmmm, not too good.  maybe keep your distance.");
        }
        else if (rpercent > 25 && rpercent <= 50)
        {
            System.out.println("you did an adequate job on this, i would not mind being acquiantances.");
        }
        else if (rpercent > 50 && rpercent <= 75)
        {
            System.out.println("woah!  you did better than most people, congrats!");
        }
        else if (rpercent > 75 && rpercent <= 85)
        {
            System.out.println("this is pretty compatible, i'm impressed!");
        }
        else if (rpercent > 85 && rpercent <= 90)
        {
            System.out.println("this is an incredibly good score, nice job!");
        }
        else if (rpercent > 90 && rpercent <= 95)
        {
            System.out.println("what are we, twins?  kidding, adam got -73%.  but fr, very cool 10/10");
        }
        else if (rpercent > 95)
        {
            System.out.println("this score is too insane to be true.  rachel probably took the test for you");
        }
    }
}
