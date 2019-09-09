public class fraction {
    public int numerator;
    public int denominator;
    public int location;
    // constructor takes the numerator and denominator and sets up the fraction
    Public fraction(int numerator, int denominator) {
       this.numerator= numerator;
       this.denominator=+denominator;
    }

    // constructor takes a whole number and sets up the fraction
    Public fraction(int number) {
        numberator=number;
        denominator=1;
    }

    // constructor takes a string representation of a fraction, say “5/12”, and
    // sets up the fraction
    Public fraction(String fraction) {
        for(int x=0;x<fraction.length();x++){
            if(fraction.substring(x,x+1).equals("/")){
                location=x;
            }
        }
        numerator=fraction.substring(0,location);
        denominator=fraction.substring(location);
    }

    // adds fraction f to this fraction - returns the resulting fraction
    Public fraction add(Fraction f){

    }

    // subtracts fraction f from this fraction - returns the resulting fraction
    Public fraction subtract(Fraction f) {

    }

    // multiplies fraction f by this fraction -  returns the resulting fraction
    Public fraction multiply(Fraction f) {

    }

    // divides fraction f to this fraction - returns the resulting fraction
    Public fraction divide(Fraction f) {

    }

    // returns the decimal representation (approximation) of the fraction
    public double toDecimal() {
        return numerator/denominator;
    }

    // compares this fraction to another fraction
    // return a negative number if this fraction is less than the other
    // return 0 if the fractions are equal
    // return a positive number if this fraction is greater than the other
    public int compareTo(Fraction f) {

    }

    // returns a string representation of the fraction
    public String toString() {

    }

    // do unit testing of this class
    public static void main(String[] args) {


}


}
