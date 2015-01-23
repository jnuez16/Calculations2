/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculations;


/**
 *
 * @author jnuez16
 */
public class Calculations {

    static final Double G = 6.67384e-11;
    static Double M = 333054.253182;
    static Double m = 1.5;
    static Double l = 5e15;

    /**
     * @param args the command line arguments
     * 
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Double del = 1e-6, r = 2.0, dr = 0.1;
        int n = 10000;
        r = (secant(n, del, r, dr)/5.972e24);
        Double r1 = r*1e-9;
        System.out.println("The orbit length is " + r1 + " m");
        Double t = time(r)/86400;
        System.out.printf("Time %f\n", t);
    }

    public static Double secant(int n, Double del, Double x, Double dx) {
        int k = 0;
        Double x1 = x + dx, x2 = 0.0;
        Double g0 = g(x);
        Double g1 = g(x1);
        if (g1 > g0) {
            x1 = x - dx;
        }
        while ((Math.abs(dx) > del) && (k < n)) {
            
            Double d = f(x1) - f(x);
            dx = -(x1 - x) * f(x1) / d;
            x2 = x1 + dx;
            if(Double.isNaN(x2) || Double.isInfinite(x2))
            {
                break;
            }
            Double g2 = g(x2);
            if (g2 > g1) {
                x2 = x1 - dx;
            }       
            x = x1;
            x1 = x2;
            g1 = g2;
            k++;
        }
        if(k==n) System.out.println("Convergence not"
                + " found after " + n + " iterations.");
        return x1;
    }
    public static Double g(Double x)
    {
        Double u = ((-(G*m*M)/x)+(((l*l)/(2*x*x))*((m+M)/(m*M))));
        return u;
    }
    public static Double f(Double x)
    {
        Double u = ((-(G*m*M)/(x*x))+(((l*l)/(x*x*x))*((m+M)/(m*M)))); 
        return u;
    }
    
    public static Double time(Double r)
    {
        return ((2*Math.PI)*m*r*r)/l;
    }
}
