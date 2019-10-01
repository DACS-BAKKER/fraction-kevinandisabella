/* KEVIN AND ISABELLA'S FRACTION.JAVA */
public class Fraction {
    /* VARIABLE */
    private int numerator;
    private int denominator;

    /* TAKE IN NUMERATOR */
    public Fraction(int numerator) {
        this.numerator = numerator;
        this.denominator = 1;
    }

    /* TAKE IN NUMERATOR AND DENOMINATOR */
    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    /* TAKE IN STRING */
    public Fraction(String fraction) {
        int i = fraction.indexOf("/");
        if (i != -1) {
            this.numerator = Integer.valueOf(fraction.substring(0, i));
            this.denominator = Integer.valueOf(fraction.substring(i + 1));
        } else {
            this.numerator = Integer.valueOf(fraction);
            this.denominator = 1;
        }
    }

    /* ADD FRACTION */
    public Fraction add(Fraction fraction) {
        int n = this.numerator * fraction.denominator + this.denominator * fraction.numerator;
        int d = this.denominator * fraction.denominator;
        int gcd = gcd(n, d);
        return new Fraction(n / gcd, d / gcd);
    }

    /* SUBTRACT FRACTION */
    public Fraction subtract(Fraction fraction) {
        int n = this.numerator * fraction.denominator - this.denominator * fraction.numerator;
        int d = this.denominator * fraction.denominator;
        int gcd = gcd(n, d);
        return new Fraction(n / gcd, d / gcd);
    }

    /* MULTIPLY FRACTION */
    public Fraction multiply(Fraction fraction) {
        int n = this.numerator * fraction.numerator;
        int d = this.denominator * fraction.denominator;
        int gcd = gcd(n, d);
        return new Fraction(n / gcd, d / gcd);
    }

    /* DIVIDE FRACTION */
    public Fraction divide(Fraction fraction) {
        int n = this.numerator * fraction.denominator;
        int d = this.denominator * fraction.numerator;
        int gcd = gcd(n,d);
        return new Fraction(n / gcd, d / gcd);
    }

    /* TO DECIMAL */
    public double toDecimal() {
        return (double) numerator / this.denominator;
    }

    /* COMPARE WITH FRACTION */
    public int compareTo(Fraction fraction) {
        int n = this.numerator * fraction.denominator;
        int d = fraction.numerator * this.denominator;
        if (d > n)
            return 1;
        if (d < n)
            return -1;
        return 0;
    }

    /* GCD */
    public int gcd(int x, int y) {
        if (x % y == 0)
            return Math.abs(y);
        return gcd(y, x % y);
    }

    /* CONVERT TO STRING */
    public String toString() {
        if (denominator != 1)
            return this.numerator + "/" + this.denominator;
        return String.valueOf(numerator);
    }
}