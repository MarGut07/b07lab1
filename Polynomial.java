import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;


public class Polynomial{

    //fields
    double [] coefficients;
    int [] exponents;

    //constructors
    public Polynomial(){
        coefficients = new double [1];
        coefficients[0] = 0.0;
        exponents = new int [1];
        exponents[0] = 0;
    }

    public Polynomial(double [] coefficients, int [] exponents){
        int length = coefficients.length;

        this.coefficients = new double [length];
        this.exponents = new int [length];

        for(int i = 0; i<length; i++){
            this.coefficients[i] = coefficients[i];
            this.exponents[i] = exponents[i];
        }
    }

    public Polynomial(File file) throws Exception{
        BufferedReader input =new BufferedReader(new FileReader(file));
        String line = input.readLine();
        String [] line_sep = line.split("");
        int i=0, terms=0, c=0, d=0, b=0;

        for (i = 0; i<line.length(); i++){
            if(line_sep[i].compareTo("+")==0 || line_sep[i].compareTo("-")==0)
                terms++;
        }
        terms++;

        if(line_sep[0].compareTo("+")==0 || line_sep[0].compareTo("-")==0)
            terms--;

        coefficients = new double[terms];
        exponents = new int[terms];

        String str = "";

        for (i = 0; i<line.length(); i++){

            b=0;
            if(line_sep[i].compareTo("-")==0){
                str = "";
                str = str.concat("-");
                i++;
                while(line_sep[i].compareTo("x")!=0 && line_sep[i].compareTo("+")!=0 && line_sep[i].compareTo("-")!=0){
                    str = str.concat(line_sep[i]);
                    i++;
                    if(i == line.length())
                        break;
                }
                i--;
            }else if(line_sep[i].compareTo("x") == 0){
                str = "";
                i++;
                while(line_sep[i].compareTo("x") != 0 && line_sep[i].compareTo("+") != 0 && line_sep[i].compareTo("-")!=0){
                    str = str.concat(line_sep[i]);
                    i++;
                    if(i == line.length())
                        break;
                }
                i--;
                b=1;
            }else if(line_sep[i].compareTo("+")==0){
                str = "";
                i++;
                while((line_sep[i].compareTo("x")!=0 && line_sep[i].compareTo("+")!=0 && line_sep[i].compareTo("-")!=0)){
                    str = str.concat(line_sep[i]);
                    i++;
                    if(i == line.length())
                        break;
                }
                i--;
            }else{
                str="";
                while((line_sep[i].compareTo("x")!=0 && line_sep[i].compareTo("+")!=0 && line_sep[i].compareTo("-")!=0)){
                    str = str.concat(line_sep[i]);
                    i++;
                    if(i == line.length())
                        break;
                }
                i--;
            }
            if(b == 0 && i<line.length()){
                coefficients[c] = Double.parseDouble(str);
                c++;
            }else if(i<line.length()){
                if(d < c-1)
                    d++;
                if(str.compareTo("")==0)
                    str = str.concat("1");
                exponents[d] = Integer.parseInt(str);
                if(coefficients[d] == 0){
                    coefficients[d] = 1;
                    c++;
                }
                d++;
            }
        }
    }

    //methods
    public Polynomial add (Polynomial poly_arg){ 

    	int max_length = this.exponents.length + poly_arg.exponents.length;
    	int [] temp_exp = new int [max_length];
    	double [] temp_coe = new double [max_length];
    	int i=0, c=0;

    	for(i=0; i < this.exponents.length; i++){
            temp_exp[c] = this.exponents[i];
            temp_coe[c] = this.coefficients[i];
            c++;
        }

        int is_in=0, zeros=0;
        for(i=0; i < poly_arg.exponents.length; i++){
            is_in=0;
            for(int j=0; j < this.exponents.length; j++){
                if(poly_arg.exponents[i] == this.exponents[j]){
                    temp_coe[j] = poly_arg.coefficients[i]+this.coefficients[j];
                    is_in++;
                    if(temp_coe[j] == 0){
                        zeros++;
                    }
                }

            }
            if(is_in == 0){
                temp_exp[c] = poly_arg.exponents[i];
                temp_coe[c] = poly_arg.coefficients[i];
                c++;
            }
        }

        int [] exp = new int[c-zeros];
        double [] coe = new double[c-zeros];
        int h = 0;
        for(i=0; i<c; i++){
            if(temp_coe[i] != 0){
                exp[h] = temp_exp[i];
                coe[h] = temp_coe[i];
                h++;
            }
        }

        return new Polynomial(coe, exp);

    }

    public double evaluate(double x){
        short c;
        double answer = 0, product = 0;
        int p = 0;
        for(int i = 0; i<this.coefficients.length; i++){

            c=0;
            p=this.exponents[i];
            product = 1;
            while(c<p){
                product = product * x;
                c++;
            }
            answer += product * this.coefficients[i];
        }
        return answer;
    }

    public boolean hasRoot(double x){
        if(this.evaluate(x) == 0)
            return true;
        else
            return false;
    }


    public Polynomial multiply (Polynomial poly_arg){
        int max_length = this.exponents.length * poly_arg.exponents.length;
    	int [] temp_exp = new int [max_length];
    	double [] temp_coe = new double [max_length];
    	int i=0,j=0, c=0, zeros=0;

    	for(i=0; i<this.exponents.length; i++){
            for(j=0; j < poly_arg.exponents.length; j++){
                temp_exp[c] = this.exponents[i]+poly_arg.exponents[j];
                temp_coe[c] = this.coefficients[i]*poly_arg.coefficients[j];
                c++;
            }
    	}

    	for(i=0; i<c; i++){
            for(j=i+1; j<c; j++){
                if(temp_exp[i] == temp_exp[j] && temp_exp[i] != -1){
                    temp_coe[i] += temp_coe[j];
                    temp_exp[j] = -1;
                    temp_coe[j] = 0;
                    zeros++;
                }
            }
            if(temp_coe[i] == 0 && temp_exp[i] != -1){
                temp_exp[i] = -1;
                zeros++;
            }
    	}
        int [] exp = new int[c-zeros];
        double [] coe = new double[c-zeros];
        int h = 0;
        for(i=0; i<c; i++){
            if(temp_coe[i] != 0){
                exp[h] = temp_exp[i];
                coe[h] = temp_coe[i];
                h++;
            }
        }

        return new Polynomial(coe, exp);

    }

    public void saveToFile(String file_name) throws Exception{
        int i=0;
        String str="";

        for(i=0; i < exponents.length; i++){
            if(coefficients[i]>0 && i != 0)
                str = str.concat("+");

            str = str.concat(String.valueOf(coefficients[i]));
            if(exponents[i] > 0)
                str = str.concat("x");
                if(exponents[i]>1)
                    str = str.concat(String.valueOf(exponents[i]));
        }

        File file = new File(file_name);

        if(file.createNewFile()){
            FileWriter output = new FileWriter(file_name);
            output.write(str);
            output.close();
        }else{
        this.saveToFile(file_name+"(1)");
        }
    }

}
