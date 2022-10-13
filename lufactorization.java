 /*
 @author deadfishh
 *
 * this does the lu factorization of a 3x3 matricie
 * in short, it will rref it for you
 * not too bad!!
 * matlab does it better though
 */

public class lufactorization
{
    public static void main()
    {
        // input the matrix A
        double[][] a = {
                            {7.0, 7.0, 3.0},
                            {2.0, 0.0, 2.0},
                            {5.0, 8.0, 6.0}
                        };
        
        // set the variables for the A matrix
        double a11 = a[0][0];
        double a12 = a[0][1];
        double a13 = a[0][2];
        double a21 = a[1][0];
        double a22 = a[1][1];
        double a23 = a[1][2];
        double a31 = a[2][0];
        double a32 = a[2][1];
        double a33 = a[2][2];
        
        // input the matrix b
        double[][] b = {
                            {2},
                            {-4},
                            {6}
                        };
        
        // set the variables for the lower matrix
        double l21 = 0;
        double l31 = 0;
        double l32 = 0;
        
        // set the variables for the upper matrix
        double u11 = 0;
        double u12 = 0;
        double u13 = 0;
        double u22 = 0;
        double u23 = 0;
        double u33 = 0;
        
        // do the actual math
        u11 = a11;
        u12 = a12;
        u13 = a13;
        l21 = a21 / u11;
        u22 = a22 - (l21*u12);
        u23 = a23 - (l21*u13);
        l31 = a31 / u11;
        l32 = (a32 - (l31*u12)) / u22;
        u33 = a33 - (l31*u13) - (l32*u23);
        
        // set the lower matrix
        double[][] lower = {
                                {1, 0, 0},
                                {l21, 1, 0},
                                {l31, l32, 1}
                            };
        
        // set the upper matrix
        double[][] upper = {
                                {u11, u12, u13},
                                {0, u22, u23},
                                {0, 0, u33}
                            };
        
        // do math to get the y matrix
        double y1 = b[0][0];
        double y2 = b[1][0] - l21*y1;
        double y3 = b[2][0] - l31*y1 - l32*y2;
        
        // set the y matrix
        double[][] y = {
                            {y1},
                            {y2},
                            {y3}
                        };
        
        // do math to get the x matrix
        double x3 = y3 / u33;
        double x2 = (y2 - u23*x3) / u22;
        double x1 = (y1 - u13*x3 - u12*x2)  / u11;
        
        // set the x matrix
        double[][] x = {
                            {x1},
                            {x2},
                            {x3},
                        };
        
        // print the matricies
        System.out.println("the A matrix is: \n" + array2stringd(a));
        System.out.println("the lower matrix is: \n" + array2stringd(lower));
        System.out.println("the upper matrix is: \n" + array2stringd(upper));
        System.out.println("the y matrix is: \n" + array2stringd(y));
        System.out.println("the x matrix is: \n" + array2stringd(x));
    }

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
