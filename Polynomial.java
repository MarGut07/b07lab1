public class Polynomial{

    double [] coefficients;
    int [] exponents;

    public Polynomial(){
        coefficients = new double [1];
        coefficients[0] = 0.0;
        exponents = new int [1]
        exponents[0] = 0
    }

    public Polynomial(double [] coefficients, int [] exponents){
        int length = coefficients.length;

        this.coefficients = new double [length];
        this.exponents = new int [length];

        for(int i = 0; i<length; i++){
            this.coefficients[i] = coefficients[i];
            this.exponents = exponents[i];
        }
    }

    public Polynomial add (Polynomial poly_arg){ 
    	int [] max_lenght = new int[this.exponents.length + poly_arg.exponents.length];
    	int i = 0;
        int length_arg  = poly_arg.coefficients.length;
        int length_call = this.coefficients.length;
        double [] result_coefficients;

        if(length_arg >= length_call){
            result_coefficients = new double[length_arg];
            for(i = 0; i<length_call; i++){
                result_coefficients[i] = this.coefficients[i] + poly_arg.coefficients[i];
            }
            while(i<length_arg){
                result_coefficients[i] = poly_arg.coefficients[i];
                i++;
            }
        }
        else{
            result_coefficients = new double[length_call];
            for(i = 0; i<length_arg; i++){
                result_coefficients[i] = this.coefficients[i] + poly_arg.coefficients[i];
            }
            while(i<length_call){
                result_coefficients[i] = this.coefficients[i];
                i++;
            }
        }
        return new Polynomial(result_coefficients);
    }

    public double evaluate(double x){
        short c;
        double answer = 0, product = 0;
        for(int i = 0; i<this.coefficients.length; i++){
            c=0;
            product = 1;
            while(c<i){
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

}
