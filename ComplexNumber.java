/* KEVIN AND ISABELLA'S COMPLEXNUMBER.JAVA */
public class ComplexNumber {
    /* FRACTION A AND FRACTION B */
    private Fraction f1;
    private Fraction f2;

    /* TAKES IN TWO FRACTIONS */
    public ComplexNumber(Fraction f1, Fraction f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

    /* TAKES IN ONE FRACTION */
    public ComplexNumber(Fraction f1) {
        this.f1 = f1;
        this.f2 = new Fraction(0);
    }

    /* TAKES IN AN INTEGER */
    public ComplexNumber(int a) {
        this.f1 = new Fraction(a);
        this.f2 = new Fraction(0);
    }

    /* TAKES IN A STRING */
    public ComplexNumber(String a) {
        int i = a.indexOf("+");
        int j = a.substring(1).indexOf("-");
            if (i != -1) {
                this.f1 = new Fraction(a.substring(0, i));
                this.f2 = new Fraction(a.substring(i, a.length() - 1));
            } else if (j != -1) {
                this.f1 = new Fraction(a.substring(0, j + 1));
                this.f2 = new Fraction(a.substring(j + 1, a.length() - 1));
            } else {
                this.f1 = new Fraction(a);
                this.f2 = new Fraction(0);
            }
    }

    /* ADDS COMPLEX NUMBER */
    public ComplexNumber add(ComplexNumber n) {
        Fraction a = this.f1.add(n.f1);
        Fraction b = this.f2.add(n.f2);
        return new ComplexNumber(a, b);
    }

    /* SUBTRACTS COMPLEX NUMBER */
    public ComplexNumber subtract(ComplexNumber n) {
        Fraction a = this.f1.subtract(n.f1);
        Fraction b = this.f2.subtract(n.f2);
        return new ComplexNumber(a, b);
    }

    /* MULTIPLIES COMPLEX NUMBER */
    public ComplexNumber multiply(ComplexNumber n) {
        Fraction a = (this.f1.multiply(n.f1)).subtract(f2.multiply(n.f2));
        Fraction b = (this.f1.multiply(n.f2)).add(f2.multiply(n.f1));
        return new ComplexNumber(a, b);
    }

    /* DIVIDE COMPLEX NUMBER */
    public ComplexNumber divide(ComplexNumber n) {
        Fraction a = (this.f1.multiply(n.f1).add(this.f2.multiply(n.f2))).divide((n.f1.multiply((n.f1))).add((n.f2.multiply(n.f2))));
        Fraction b = (this.f2.multiply(n.f1).subtract(this.f1.multiply(n.f2))).divide((n.f1.multiply((n.f1))).add((n.f2.multiply(n.f2))));
        return new ComplexNumber(a, b);
    }

    /* COMPARE WITH COMPLEX NUMBER */
    public int compareTo(ComplexNumber n) {
        if (f1.compareTo(n.f1) > 0 || (f1.compareTo(n.f1) == 0 && f2.compareTo(n.f2) > 0))
            return 1;
        else if (f1.compareTo(n.f1) < 0 || (f1.compareTo(n.f1) == 0 && f2.compareTo(n.f2) < 0))
            return -1;
        return 0;
    }

    /* TO STRING */
    public String toString() {
        if (f2.compareTo(new Fraction(0)) != 0) {
            if (f2.compareTo(new Fraction(0, 1)) < 0)
                return f1 + "+" + f2 + "i";
            return f1.toString() + f2 + "i";
        }
        return f1.toString();
    }
}
