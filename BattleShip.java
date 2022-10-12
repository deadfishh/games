 
import java.util.Scanner;

public class BattleShip
{
    private static String[][] playergrid = new String[10][10];
    private static String[][] mygrid = new String[10][10];
    private static String[][] screengrid = new String[10][10];
    private static Scanner scanner = new Scanner(System.in);
    private static String pt = "pt";
    private static String sub = "submarine";
    private static String battle = "battleship";
    private static String ac = "aircraft carrier";
    private static int ptlength = 2;
    private static int sublength = 3;
    private static int battlelength = 4;
    private static int aclength = 5;
    private static boolean redo = false;
    private static int[] lastguess = new int[2];
    private static int[] thisguess = new int[2];
    private static boolean first = true;
    private static boolean second = true;
    private static boolean threeplus = false;
    private static boolean vertical;
    private static boolean hit = false;
    private static boolean finished = false;
    private static int k = 0;
    private static int playercount = 0;
    private static int computercount = 0;
    private static int[][] playerpt = new int[2][2];
    private static int[][] playersub = new int[3][2];
    private static int[][] playerbattle = new int[4][2];
    private static int[][] playerac = new int[5][2];
    private static int[][] comppt = new int[2][2];
    private static int[][] compsub = new int[3][2];
    private static int[][] compbattle = new int[4][2];
    private static int[][] compac = new int[5][2];
    private static boolean[] sunken = new boolean[8];
    private static int[] special = {50, 50};
    public static void main(String args[])
    {
        int[][] comppt = new int[2][2];
        welcome();
        makegrids();
        //makemygrid();
        makeallrandomgrid();
        //printgrid(mygrid);
        getallinfo();
        while (playercount < 14 && computercount < 14)
        {
            playerturn();
            myturn();
        }
        goodbye();
    }
    
    public static void welcome()
    {
        System.out.println("\n");
        System.out.println("welcome to battleship!  a new game has started!");
        System.out.println("the grid being used is 10 x 10");
        System.out.println("for all ordered pairs, write them like \"E5\".");
        System.out.println("remember!  letter first, then number!");
        System.out.println();
    }
    
    public static void goodbye()
    {
        if (playercount == 14)
        {
            System.out.println("you won! what a pleasant surprise!!!");
        }
        else
        {
             System.out.println("all you had to do was open the bluej files and look at them");
             System.out.println("it's right there on the desktop with all of the ship coordinates");
             System.out.println("there really is no hope for the human race");
        }
    }
    
    public static void makegrids()
    {
        for (int i = 0; i < playergrid.length; i++)
        {
            for (int j = 0; j < playergrid[0].length; j++)
            {
                playergrid[i][j] = " ";
                mygrid[i][j] = " ";
                screengrid[i][j] = " ";
            }
        }
    }
    
    public static void makemygrid()
    {
        // pt
        mygrid[3 - 1][5 - 1] = "O";
        mygrid[3 - 1][6 - 1] = "O";
        
        comppt[0][0] = 2;
        comppt[0][1] = 4;
        comppt[1][0] = 2;
        comppt[1][1] = 5;
        
        // submarine
        mygrid[1 - 1][2 - 1] = "O";
        mygrid[2 - 1][2 - 1] = "O";
        mygrid[3 - 1][2 - 1] = "O";
        
        compsub[0][0] = 0;
        compsub[0][1] = 1;
        compsub[1][0] = 1;
        compsub[1][1] = 1;
        compsub[2][0] = 2;
        compsub[2][1] = 1;
        
        // battleship
        mygrid[9 - 1][2 - 1] = "O";
        mygrid[9 - 1][3 - 1] = "O";
        mygrid[9 - 1][4 - 1] = "O";
        mygrid[9 - 1][5 - 1] = "O";
        
        compbattle[0][0] = 8;
        compbattle[0][1] = 1;
        compbattle[1][0] = 8;
        compbattle[1][1] = 2;
        compbattle[2][0] = 8;
        compbattle[2][1] = 3;
        compbattle[3][0] = 8;
        compbattle[3][1] = 4;
        
        // aircraft carrier
        mygrid[4 - 1][4 - 1] = "O";
        mygrid[5 - 1][4 - 1] = "O";
        mygrid[6 - 1][4 - 1] = "O";
        mygrid[7 - 1][4 - 1] = "O";
        mygrid[8 - 1][4 - 1] = "O";
        
        compac[0][0] = 3;
        compac[0][1] = 3;
        compac[1][0] = 4;
        compac[1][1] = 3;
        compac[2][0] = 5;
        compac[2][1] = 3;
        compac[3][0] = 6;
        compac[3][1] = 3;
        compac[4][0] = 7;
        compac[4][1] = 3;
        
    }
    
    public static void setbattleship(String shipname, int length)
    {
        System.out.println("type in the coordinates of where you want your " + shipname + " to go (it's " + length + " pixels long).");
        int index1 = 0;
        int index2 = 0;
        redo = true;
        while (redo)
        {
            redo = false;
            hit = false;
            String input = scanner.nextLine();
            if (!redo && intcatch(input))
            {
                redo = true;
            }
            else
            {
                String string1 = input.substring(0, 1);
                index1 = lettertoint(string1);
                index2 = Integer.parseInt(input.substring(1)) - 1;
                if (!redo && badplaces(string1, index2))
                {
                    redo = true;
                }
            }
            thisguess[0] = index1;
            thisguess[1] = index2;
            if (!redo && !first && !nextto(lastguess, thisguess))
            {
                System.out.println("no, they need to be next to each other.");
                redo = true;
            }
            if (!redo && threeplus && !direction(lastguess, thisguess))
            {
                redo = true;
            }
        }
        playergrid[index1][index2] = "O";
        switch (shipname)
        {
            case "pt":
                playerpt[k - 1][0] = index1;
                playerpt[k - 1][1] = index2;
                break;
            case "submarine":
                playersub[k - 1][0] = index1;
                playersub[k - 1][1] = index2;
                break;
            case "battleship":
                playerbattle[k - 1][0] = index1;
                playerbattle[k - 1][1] = index2;
                break;
            case "aircraft carrier":
                playerac[k - 1][0] = index1;
                playerac[k - 1][1] = index2;
                break;
        }
        lastguess[0] = index1;
        lastguess[1] = index2;
    }
    
    public static void getinfo(String shipname, int length)
    {
        k = 1;
        for (k = 1; k < length + 1; k++)
        {
            if (k == 1)
            {
                first = true;
                second = false;
                threeplus = false;
            }
            else if (k == 2)
            {
                second = true;
                first = false;
                threeplus = false;
            }
            else
            {
                threeplus = true;
                first = false;
                second = false;
            }
            System.out.println("coordinate #" + k);
            setbattleship(shipname, length);
        }
        printfancygrid(playergrid, screengrid);
    }
    
    public static void playerturn()
    {
        hit = false;
        System.out.println("player's turn:");
        System.out.println("type your guess.");
        int index1 = 0;
        int index2 = 0;
        redo = true;
        while (redo)
        {
            redo = false;
            String input = scanner.nextLine();
            if (!redo && intcatch(input))
            {
                redo = true;
            }
            else
            {
                String string1 = input.substring(0, 1);
                if (!lettercheck(string1))
                {
                    redo = true;
                }
                index1 = lettertoint(input.substring(0, 1));
                index2 = Integer.parseInt(input.substring(1)) - 1;
                if (!redo && boundcheck(index2))
                {
                    redo = true;
                }
            }
            if (!redo && screengrid[index1][index2] == "H" || !redo && screengrid[index1][index2] == "╳")
            {
                System.out.println("no, you already guessed that one.  try again.");
                redo = true;
            }
            if (!redo && mygrid[index1][index2] == " ")
            {
                System.out.println("no, sorry.  that's a miss.");
                screengrid[index1][index2] = "╳";
            }
            if (!redo && mygrid[index1][index2] == "O")
            {
                System.out.println("that's a hit!");
                screengrid[index1][index2] = "H";
                evaluatesinking();
                playercount++;
                if (playercount == 14)
                {
                    return;
                }
                System.out.println("you get another turn.");
                hit = true;
                redo = true;
            }
            if ((!redo) || (redo && hit))
            {
                printfancygrid(playergrid, screengrid);
            }
        }
    }
    
    public static void myturn()
    {
        while (hit = true)
        {
           boolean goodnum = false;
           hit = false;
           System.out.println("computer's turn:");
           int index1 = 0;
           int index2 = 0;
           while (!goodnum)
           {
               goodnum = true;
               index1 = (int)(Math.random()*10);
               index2 = (int)(Math.random()*10);
               if (special[0] != 50)
               {
                   if (special[0] != 0 && (playergrid[special[0] - 1][special[1]] == "O" || playergrid[special[0] - 1][special[1]] == " "))
                   {
                       index1 = special[0] - 1;
                       index2 = special[1];
                   }
                   else if (special[1] != 9 && (playergrid[special[0]][special[1] + 1] == "O" || playergrid[special[0]][special[1] + 1] == " "))
                   {
                       index1 = special[0];
                       index2 = special[1] + 1;
                   }
                   else if (special[0] != 9 && (playergrid[special[0] + 1][special[1]] == "O" || playergrid[special[0] + 1][special[1]] == " "))
                   {
                       index1 = special[0] + 1;
                       index2 = special[1];
                   }
                   else if (special[1] != 0 && (playergrid[special[0]][special[1] - 1] == "O" || playergrid[special[0]][special[1] - 1] == " "))
                   {
                       index1 = special[0];
                       index2 = special[1] - 1;
                   }
                   else
                   {
                       special[0] = 50;
                       special[1] = 50;
                       goodnum = false;
                   }
               }
               else if (goodnum && playergrid[index1][index2] == "H" || playergrid[index1][index2] == "╳")
               {
                   goodnum = false;
               }
           }
           System.out.println("the computer's guess is " + (inttoletter(index1)) + (index2 + 1) + ".");
           if (!hit && playergrid[index1][index2] == " ")
           {
               System.out.println("computer missed.  oh well.");
               playergrid[index1][index2]  = "╳";
               hit = false;
               printfancygrid(playergrid, screengrid);
               return;
           }
           if (!hit && playergrid[index1][index2] == "O")
           {
               System.out.println("that's a hit!");
               System.out.println("the computer gets another turn :)");
               special[0] = index1;
               special[1] = index2;
               playergrid[index1][index2] = "H";
               evaluatesinking();
               hit = true;
               computercount++;
               if (computercount == 14)
               {
                   return;
               }
               printfancygrid(playergrid, screengrid);
           }
        }
        
    }
    
    public static void evaluatesinking()
    {
        // player grid
        if (!sunken[0] && playergrid[playerpt[0][0]][playerpt[0][1]] == "H" && playergrid[playerpt[1][0]][playerpt[1][1]] == "H")
        {
            System.out.println("\ni've sunken your pt!\n");
            sunken[0] = true;
            special[0] = 50;
            special[1] = 50;
        }
        if (!sunken[1] && playergrid[playersub[0][0]][playersub[0][1]] == "H" && playergrid[playersub[1][0]][playersub[1][1]] == "H" && playergrid[playersub[2][0]][playersub[2][1]] == "H")
        {
            System.out.println("\ni've sunken your submarine!\n");
            sunken[1] = true;
            special[0] = 50;
            special[1] = 50;
        }
        if (!sunken[2] && playergrid[playerbattle[0][0]][playerbattle[0][1]] == "H" && playergrid[playerbattle[1][0]][playerbattle[1][1]] == "H" && playergrid[playerbattle[2][0]][playerbattle[2][1]] == "H" && playergrid[playerbattle[3][0]][playerbattle[3][1]] == "H")
        {
            System.out.println("\ni've sunken your battleship!\n");
            sunken[2] = true;
            special[0] = 50;
            special[1] = 50;
        }
        if (!sunken[3] && playergrid[playerac[0][0]][playerac[0][1]] == "H" && playergrid[playerac[1][0]][playerac[1][1]] == "H" && playergrid[playerac[2][0]][playerac[2][1]] == "H" && playergrid[playerac[3][0]][playerac[3][1]] == "H" && playergrid[playerac[4][0]][playerac[4][1]] == "H")
        {
            System.out.println("\ni've sunken your aircraft carrier!\n");
            sunken[3] = true;
            special[0] = 50;
            special[1] = 50;
        }
        
        // computer grid
        if (!sunken[4] && screengrid[comppt[0][0]][comppt[0][1]] == "H" && screengrid[comppt[1][0]][comppt[1][1]] == "H")
        {
            System.out.println("\nyou've sunken my pt!\n");
            sunken[4] = true;
        }
        if (!sunken[5] && screengrid[compsub[0][0]][compsub[0][1]] == "H" && screengrid[compsub[1][0]][compsub[1][1]] == "H" && screengrid[compsub[2][0]][compsub[2][1]] == "H")
        {
            System.out.println("\nyou've sunken my submarine!\n");
            sunken[5] = true;
        }
        if (!sunken[6] && screengrid[compbattle[0][0]][compbattle[0][1]] == "H" && screengrid[compbattle[1][0]][compbattle[1][1]] == "H" && screengrid[compbattle[2][0]][compbattle[2][1]] == "H" && screengrid[compbattle[3][0]][compbattle[3][1]] == "H")
        {
            System.out.println("\nyou've sunken my battleship!\n");
            sunken[6] = true;
        }
        if (!sunken[7] && screengrid[compac[0][0]][compac[0][1]] == "H" && screengrid[compac[1][0]][compac[1][1]] == "H" && screengrid[compac[2][0]][compac[2][1]] == "H" && screengrid[compac[3][0]][compac[3][1]] == "H" && screengrid[compac[4][0]][compac[4][1]] == "H")
        {
            System.out.println("\nyou've sunken my aircraft carrier!\n");
            sunken[7] = true;
        }
    }
    
    public static void makerandomgrid(int length)
    {
        boolean goodnum = false;
        boolean updown = false;
        int index1 = 0;
        int index2 = 0;
        while (!goodnum)
        {
            if (Math.random() > 0.5)
            {
                updown = true;
            }
            goodnum = true;
            index1 = (int)(Math.random()*10);
            index2 = (int)(Math.random()*10);
            if (playergrid[index1][index2] == "O")
            {
                goodnum = false;
            }
            if (updown && index1 > 10 - length)
            {
                goodnum = false;
                //break;
            }
            if (!updown && index2 > 10 - length)
            {
                goodnum = false;
                //break;
            }
            int temp1 = index1;
            int temp2 = index2;
            for (int i = 0; i < length; i++)
            {
                if (goodnum && mygrid[temp1][temp2] == "O")
                {
                    goodnum = false;
                    break;
                }
                if (updown)
                {
                    temp1++;
                }
                else
                {
                    temp2++;
                }
            }
        }
        for (int i = 0; i < length; i++)
        {
            mygrid[index1][index2] = "O";
            switch(length)
            {
                case 2:
                    comppt[i][0] = index1;
                    comppt[i][1] = index2;
                    break;
                case 3:
                    compsub[i][0] = index1;
                    compsub[i][1] = index2;
                    break;
                case 4:
                    compbattle[i][0] = index1;
                    compbattle[i][1] = index2;
                    break;
                case 5:
                    compac[i][0] = index1;
                    compac[i][1] = index2;
                    break;
            }
            if (updown)
            {
                index1++;
            }
            else
            {
                index2++;
            }
        }
    }
    
    public static void makeallrandomgrid()
    {
        makerandomgrid(ptlength);
        makerandomgrid(sublength);
        makerandomgrid(battlelength);
        makerandomgrid(aclength);
    }
    
    public static void getallinfo()
    {
        getinfo(pt, ptlength);
        getinfo(sub, sublength);
        getinfo(battle, battlelength);
        getinfo(ac, aclength);
    }
    
    public static void printgrid(String[][] ship)
    {
        String s = "\n ";
        for (int i = 1; i < ship.length + 1; i++)
        {
            s += " " + i;
        }
        s += "\n";
        for (int i = 0; i < ship.length; i++)
        { 
            //if (i < 9)
            //{
            //    s += " ";
            //}
            s += inttoletter(i + 1);
            for (int j = 0; j < ship[0].length; j++)
            {
                s += " " + ship[i][j];
            }
            s += "\n";
        }
        System.out.println(s);
    }
    
    public static boolean badplaces(String string1, int index2)
    {
        if (boundcheck(index2))
        {
            return true;
        }
        if (!lettercheck(string1))
        {
            return true;
        }
        int index1 = lettertoint(string1);
        if (!playergrid[index1][index2].equals(" "))
        {
            System.out.println("you are a dipshit.  there's already a ship there.");
            System.out.println("try again.");
            return true;
        }
        return false;
    }
    
    public static boolean boundcheck(int index2)
    {
        if ( index2 > 9 || index2 < 0)
        {
            System.out.println("that's out of bounds, your numbers need to be between 1 and 10");
            System.out.println("try again.");
            return true;
        }
        return false;
    }
    
    public static boolean lettercheck (String letter)
    {
        if (letter.length() > 1)
        {
            System.out.println("you dipshit, obviously that's more than one letter");
            System.out.println("try again.");
            return false;
        }
        
        switch (letter)
        {
            case "a":
            case "A":
            case "b":
            case "B":
            case "c":
            case "C":
            case "d":
            case "D":
            case "e":
            case "E":
            case "f":
            case "F":
            case "g":
            case "G":
            case "h":
            case "H":
            case "i":
            case "I":
            case "j":
            case "J":
                return true;
        }
        return false;
    }
    
    public static boolean intcatch(String input)
    {
        /*if (input.indexOf(", ") == -1)
        {
            System.out.println("you formatted it wrong");
            System.out.println("try again.");
            return true;
        }*/
        try
        {
            int index2 = Integer.parseInt(input.substring(1));
        }
        catch (NumberFormatException ex)
        {
            System.out.println("i asked for a number, dipshit.  try again.");
            return true;
        }
        return false;
    }
    
    public static boolean nextto(int[] lastguess, int[] thisguess)
    {
        int lastsum = lastguess[0] + lastguess[1];
        int thissum = thisguess[0] + thisguess[1];
        if (lastguess[0] != thisguess[0] && lastguess[0] != thisguess[0] + 1 && lastguess[0] != thisguess[0] - 1)
        {
            return false;
        }
        else if (lastguess[1] != thisguess[1] && lastguess[1] != thisguess[1] + 1 && lastguess[1] != thisguess[1] - 1)
        {
            return false;
        }
        else if (lastsum != thissum + 1 && lastsum != thissum - 1)
        {
            return false;
        }
        else if (lastsum == thissum)
        {
            return false;
        }
        if (second && lastguess[0] == thisguess[0])
        {
            vertical = false;
        }
        if (second && lastguess[1] == thisguess[1])
        {
            vertical = true;
        }
        return true;
    }
    
    public static boolean direction(int[] lastguess, int[] thisguess)
    {
        if (threeplus && vertical && lastguess[1] != thisguess[1])
        {
            System.out.println("you're going horizontal when it should be vertical");
            System.out.println("try again.");
            return false;
        }
        if (threeplus && !vertical && lastguess[0] != thisguess[0])
        {
            System.out.println("you're going vertical when it should be horizontal");
            System.out.println("try again");
            return false;
        }
        return true;
    }
    
    public static int lettertoint(String letter)
    {
        if (letter.length() > 1)
        {
            System.out.println("that's not a singular letter.");
            System.out.println("try again.");
            return 50;
        }
        switch (letter)
        {
            case "a":
            case "A":
                return 0;
            case "b":
            case "B":
                return 1;
            case "c":
            case "C":
                return 2;
            case "d":
            case "D":
                return 3;
            case "e":
            case "E":
                return 4;
            case "f":
            case "F":
                return 5;
            case "g":
            case "G":
                return 6;
            case "h":
            case "H":
                return 7;
            case "i":
            case "I":
                return 8;
            case "j":
            case "J":
                return 9;
        }
        System.out.println("that letter isn't in range.");
        System.out.println("try again.");
        return 50;
    }
    
    public static String inttoletter(int num)
    {
        if (num > 10)
        {
            System.out.println("that's not in range.");
            System.out.println("try again");
            return "null";
        }
        switch (num)
        {
            case 0:
                return "A";
            case 1:
                return "B";
            case 2:
                return "C";
            case 3:
                return "D";
            case 4:
                return "E";
            case 5:
                return "F";
            case 6:
                return "G";
            case 7:
                return "H";
            case 8:
                return "I";
            case 9:
                return "J";
        }
        return "null";
    }
    
    public static void printfancygrid(String[][] grid)
    {
        System.out.println("NEW LINE");
        int rows = 5;
        int cols = 10;
        String s = "";
        int rownum = 0;
        for (int r = 0; r < rows; r++)
        {
            if (r == 0)
            {
                s += "┌";
                for (int i = 0; i < cols - 1; i++)
                {
                    s += "───┬";
                }
                s += "───┐";
                s += "\n";
            }
            else
            {
                s += "├";
                for (int i = 0; i < cols - 1; i++)
                {
                    s += "───┼";
                }
                s += "───┤";
                s += "\n";
            }
            s += "│";
            for (int i = 0; i < cols; i++)
            {
                s += " " + grid[rownum][i] + " │";
            }
            rownum++;
            s += "\n";
            s += "├";
            for (int i = 0; i < cols - 1; i++)
            {
                s += "───┼";
            }
            s += "───┤";
            s += "\n";
            if (r != rows - 1)
            {
                for (int i = 0; i < cols; i++)
                {
                    s += "│ " + grid[rownum][i] + " ";
                }
                rownum++;
                s += "│";
                s += "\n";
            }
        }
        s += "│";
        for (int i = 0; i < cols; i++)
        {
            s += " " + grid[rownum][i] + " │";
        }
        s += "\n";
        s += "└";
        for (int i = 0; i < cols - 1; i++)
        {
            s += "───┴";
        }
        s += "───┘";
        System.out.println(s);
    }
    
    public static void printfancygrid(String[][] grid1, String[][] grid2)
    {
        String offset = "                              ";
        int rows = 5;
        int cols = 10;
        String s = "";
        int rownum = 0;
        s += "\n";
        s += "        ";
        for (int i = 0; i < cols; i++)
        {
            s += " " + (i + 1) + "  ";
        }
        s += offset;
        s += "  ";
        for (int i = 0; i < cols; i++)
        {
            s += " " + (i + 1) + "  ";
        }
        s += "\n";
        for (int r = 0; r < rows; r++)
        {
            if (r == 0)
            {
                s += "       ";
                s += "┌";
                for (int i = 0; i < cols - 1; i++)
                {
                    s += "───┬";
                }
                s += "───┐";
                s += offset;
                s += "  ";
                s += "┌";
                for (int i = 0; i < cols - 1; i++)
                {
                    s += "───┬";
                }
                s += "───┐";
                s += "\n";
            }
            else
            {
                s += "       ";
                s += "├";
                for (int i = 0; i < cols - 1; i++)
                {
                    s += "───┼";
                }
                s += "───┤";
                s += offset;
                s += "  ";
                s += "├";
                for (int i = 0; i < cols - 1; i++)
                {
                    s += "───┼";
                }
                s += "───┤";
                s += "\n";
            }
            s += "     ";
            s += inttoletter(rownum) + " ";
            s += "│";
            for (int i = 0; i < cols; i++)
            {
                s += " " + grid1[rownum][i] + " │";
            }
            s += offset;
            s += inttoletter(rownum) + " ";
            s += "│";
            for (int i = 0; i < cols; i++)
            {
                s += " " + grid2[rownum][i] + " │";
            }
            rownum++;
            s += "\n";
            s += "       ";
            s += "├";
            for (int i = 0; i < cols - 1; i++)
            {
                s += "───┼";
            }
            s += "───┤";
            s += offset;
            s += "  ";
            s += "├";
            for (int i = 0; i < cols - 1; i++)
            {
                s += "───┼";
            }
            s += "───┤";
            s += "\n";
            if (r != rows - 1)
            {
                s += "     ";
                s += inttoletter(rownum) + " ";
                s += "│";
                for (int i = 0; i < cols; i++)
                {
                    s += " " + grid1[rownum][i] + " │";
                }
                s += offset;
                s += inttoletter(rownum) + " ";
                s += "│";
                for (int i = 0; i < cols; i++)
                {
                    s += " " + grid2[rownum][i] + " │";
                }
                rownum++;
                s += "\n";
            }
        }
        s += "     ";
        s += inttoletter(rownum) + " ";
        s += "│";
        for (int i = 0; i < cols; i++)
        {
            s += " " + grid1[rownum][i] + " │";
        }
        s += offset;
        s += inttoletter(rownum) + " ";
        s += "│";
        for (int i = 0; i < cols; i++)
        {
            s += " " + grid2[rownum][i] + " │";
        }
        s += "\n";
        s += "       ";
        s += "└";
        for (int i = 0; i < cols - 1; i++)
        {
            s += "───┴";
        }
        s += "───┘";
        s += offset;
        s += "  ";
        s += "└";
        for (int i = 0; i < cols - 1; i++)
        {
            s += "───┴";
        }
        s += "───┘";
        System.out.println(s);
    }
}
