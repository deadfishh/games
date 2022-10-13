 

public class leontief
{
    public static void main()
    {
        // set the consumption matrix
        double[][] con = {
                            {7.0, 7.0, 3.0},
                            {2.0, 0.0, 2.0},
                            {5.0, 8.0, 6.0}
                        };
        
        // set the demand matrix
        double[][] demand = {
                            {0.0},
                            {100.0},
                            {0.0}
                        };
        
        // making sure that the identity matrix is possible
        checkid(con);
        
        // creating the identity matrix
        int[][] identity = makeid(con.length);
        
        // telling us the given variables
        System.out.println("the consumption matricie is:");
        System.out.println(array2stringd(con));
        System.out.println("the demand matricie is:");
        System.out.println(array2stringd(demand));
        
        // performing the equation I - C
        double[][] ic = subtraction(identity, con);
        System.out.println("I - C = ");
        System.out.println(array2stringd(ic));
        
        // making sure that the dimensions are 3x3 so the inverse will work
        checkinverse(ic);
        
        // taking the determinant
        double determ = determinant(ic);
        
        // making sure that the determinant isn't 0
        checkdeterm(determ);
        
        // getting the transpose of I - C
        double[][] tr = transpose(ic);
        System.out.println("the transpose of the I - C matricie is:");
        System.out.println(array2stringd(tr));
        
        double[][] inverse = threeinverse(ic);
                        
        System.out.println("the inverse of the I - C matricie is:");
        System.out.println(array2stringd(inverse));
        
        // make sure that it's possible to multiply the inverse of I - C with the demand matrix
        checktimes(inverse, demand);
        
        // multiplying the inverse of I - C with the demand matrix
        double[][] product = multiply(inverse, demand);
        
        // rounding the entries of the produciton vector to three decimals
        double[][] result = rounding(product);
        
        System.out.println("the production vector is:");
        System.out.println(array2stringd(result));
    }
    
    // make sure the identity matrix is possible
    public static void checkid (double m1[][])
    {
         if (m1.length != m1[0].length)
        {
            System.out.println("you can't make an identity matricie because consumption isn't a square matricie :/");
            return;
        }
    }
    
    // make the identity matrix
    public static int[][] makeid (int x)
    {
        int identity[][] = new int[x][x];
        for (int i = 0; i < identity.length; i++)
        {
            for (int j = 0; j < identity[0].length; j++)
            {
                if (i == j)
                   identity[i][j] = 1;
                else
                    identity[i][j] = 0;
            }
        }
        return identity;
    }
    
    // subtraction
    public static double[][] subtraction (int[][] m1, double[][] m2)
    {
        double[][] answer = new double[m1.length][m1[0].length];
        
        for (int i = 0; i < m1.length; i++)
        { 
            for (int j = 0; j < m1[0].length; j++)
                {
                    answer[i][j] = m1[i][j] - m2[i][j];
                }
        }
        
        return answer;
    }
    
    // make sure i am able to take the inverse
    public static void checkinverse(double m1[][])
    {
        if (m1.length != 3 || m1[0].length != 3)
        {
            System.out.println("this cannot be done, the dimensions of the matricie aren't 3x3");
            System.out.println("(my code can only do 3x3 because the inverse is too hard otherwise) \n");
            return;
        }
    }
    
    // take the determinant
    public static double determinant (double[][] m1)
    {
        double d1 = m1[0][0]*m1[1][1]*m1[2][2] - m1[0][0]*m1[1][2]*m1[2][1];
        double d2 = m1[0][1]*m1[1][2]*m1[2][0] - m1[0][1]*m1[1][0]*m1[2][2];
        double d3 = m1[0][2]*m1[1][0]*m1[2][1] - m1[0][2]*m1[1][1]*m1[2][0];
        double determ = Math.abs(d1 + d2 + d3);
        return determ;
    }
    
    // make sure the determinant isn't zero
    public static void checkdeterm(double determ)
    {
        if (determ == 0)
        {
            System.out.println("the determinant is 0.  this cannot be done.");
            return;
        }
        else
        { 
            System.out.println("the determinant of the I - C matricie is:");
            System.out.println(" " + Math.round(determ * 1000.0) / 1000.0 + "\n");
        }
    }
    
    // transpose
    public static double[][] transpose (double[][] m1)
    {
        double[][] answer = new double[m1.length][m1[0].length];
        
        for (int i = 0; i < m1.length; i++)
        {
            for (int j = 0; j < m1[0].length; j++)
            {
                answer[i][j] = m1[j][i];
            }
        }
        
        return answer;
    }
    
    // get the inverse
    public static double[][] threeinverse(double[][] m1)
    {
        double determ = determinant(m1);
        double[][] tr = transpose(m1);
        
        double mult = (1.0)/(determ);
        double a = tr[0][0];
        double b = tr[0][1];
        double c = tr[0][2];
        double d = tr[1][0];
        double e = tr[1][1];
        double f = tr[1][2];
        double g = tr[2][0];
        double h = tr[2][1];
        double ii = tr[2][2];
        
        // doing math to get each entry of the inverse matrix
        double det1 = Math.round(((e*ii - f*h)*(mult)) * 1000.0) / 1000.0;
        double det2 = Math.round(((f*g - d*ii)*(mult)) * 1000.0) / 1000.0;
        double det3 = Math.round(((d*h - e*g)*(mult)) * 1000.0) / 1000.0;
        double det4 = Math.round(((c*h - b*ii)*(mult)) * 1000.0) / 1000.0;
        double det5 = Math.round(((a*ii - c*g)*(mult)) * 1000.0) / 1000.0;
        double det6 = Math.round(((b*g - a*h)*(mult)) * 1000.0) / 1000.0;
        double det7 = Math.round(((b*f - c*e)*(mult)) * 1000.0) / 1000.0;
        double det8 = Math.round(((c*d - a*f)*(mult)) * 1000.0) / 1000.0;
        double det9 = Math.round(((a*e - b*d)*(mult)) * 1000.0) / 1000.0;
        
        // telling us what the inverse is!!
        double[][] answer = {
                            {det1, det2, det3},
                            {det4, det5, det6},
                            {det7, det8, det9}
                        };
        return answer;
    }
    
    // make sure that multiplication is possible
    public static void checktimes (double[][] m1, double[][] m2)
    {
        if (m1[0].length != m2.length)
        {
            System.out.println("sorry! the dimensions are off and you can't multiply these matrixes! \n");
            return;
        }
    }
    
    // multiply two matricies
    public static double[][] multiply (double m1[][], double m2[][])
    {
        double[][] answer = new double[m1.length][m2[0].length];
        
        for (int i = 0; i < m1.length; i++)
        { 
            for (int j = 0; j < m2[0].length; j++)
            {
                for (int k = 0; k < m2.length; k++)
                {
                    answer[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        
        return answer;
    }
    
    // rounding
    public static double[][] rounding (double[][] m1)
    {
        double[][] answer = new double[m1.length][m1[0].length];
        
        for (int i = 0; i < m1.length; i++)
        {
            for (int j = 0; j < m1[0].length; j++)
            {
                answer[i][j] = Math.round(m1[i][j] * 1000.0) / 1000.0;
            }
        }
        
        return answer;
    }
    
    // array to string method, for ints
    public static String array2stringi(int[][] m3)
    {
        String s = "";
        for (int i = 0; i < m3.length; i++)
        { 
            for (int j = 0; j < m3[0].length; j++)
            {
                s = s + " " + m3[i][j];
            }
            s = s + "\n";
        }
        return s;
    }
    
    // array to string method, for doubles
    public static String array2stringd(double[][] m3)
    {
        String s = "";
        for (int i = 0; i < m3.length; i++)
        { 
            for (int j = 0; j < m3[0].length; j++)
            {
                s = s + " " + m3[i][j];
            }
            s = s + "\n";
        }
        return s;
    }
}