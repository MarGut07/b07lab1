import java.io.File;

public class Driver{
    public static void main (String[] args)throws Exception{
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));
        double [] c1 = {6,5};
        int [] e1 = {0,3};
        Polynomial p1  = new Polynomial(c1, e1);
        double [] c2 = {-2,-9};
        int [] e2 = {1,4};
        Polynomial p2 = new Polynomial(c2, e2);
        Polynomial s = p1.add(p2);
        System.out.println("s(0.1)="+s.evaluate(0.1));
        if(s.hasRoot(1))
            System.out.println("1 is a root of s");
        else
            System.out.println("1 is not a root of s");

        System.out.println("Adittional testing");
        double [] c3 = {6,2,7,5};
        int [] e3 = {0,1,2,3};
        Polynomial p3  = new Polynomial(c3, e3);
        double [] c4 = {-2,-5,2,-9};
        int [] e4 = {1,2,3,4};
        Polynomial p4 = new Polynomial(c4, e4);
        s = p3.add(p4);
        System.out.println("s(0.1)="+s.evaluate(0.1));

        double [] c5 = {2,1};
        int [] e5 = {0,1};
        Polynomial p5 = new Polynomial(c5, e5);
        Polynomial m = p5.multiply(p5);
        System.out.println("m(1.0)="+m.evaluate(1));
        if(m.hasRoot(-2))
            System.out.println("-2 is a root of s");
        else
            System.out.println("-2 is not a root of s");

        m = p5.multiply(p3);
        System.out.println("m(-1)="+m.evaluate(-1));
        if(m.hasRoot(-2))
            System.out.println("-2 is a root of s");
        else
            System.out.println("-2 is not a root of s");

        if(m.hasRoot(0))
            System.out.println("0 is a root of s");
        else
            System.out.println("0 is not a root of s");

        File file = new File("Input_exp.txt");

        Polynomial p6 = new Polynomial(file);
        System.out.println("s(0.1)="+p6.evaluate(0.1));
        if(p6.hasRoot(1))
            System.out.println("1 is a root of s");
        else
            System.out.println("1 is not a root of s");

        for(int i=0; i<4; i++){
            System.out.println("coefficient "+p6.coefficients[i]);
            System.out.println("exponents "+p6.exponents[i]);
        }

<<<<<<< HEAD
=======
        p6.saveToFile("output.txt");



>>>>>>> 20575cf (modified Polynomial.java and Driver.java)
    }

}
