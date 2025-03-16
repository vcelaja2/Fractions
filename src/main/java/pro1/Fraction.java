package pro1;

import java.util.regex.Pattern;

public class Fraction extends Number
{
    private long n; // Čitatel
    private long d; // Jmenovatel

    public Fraction(long n, long d) {
        long g = Utils.gcd(n,d);
        this.n = n / g;
        this.d = d / g;
    }

    public Fraction add(Fraction other){
        return new Fraction(
                n*other.d + other.n*d,
                d * other.d
        );
    }

    public static Fraction parse(String s)
    {
        s = s.replace(" ","");
        String[] split = s.split(Pattern.quote("+"));
        Fraction sum = new Fraction(0,1);
        for(String part : split)
        {
            Fraction partFraction;
            if(part.contains("%")){
                partFraction = new Fraction(
                      Long.parseLong(part.replace("%","")),
                      100);
            }
            else if(part.contains("/")){
                String[] split2 = part.split("/");
                partFraction = new Fraction(
                    Long.parseLong(split2[0]),
                    Long.parseLong(split2[1]));
            }

            //úprava pro práci s desetinnými čísly (kvůli třídě InteractiveFractionParsing)
            else if(part.contains(".")){
                String[] split3 = part.split(Pattern.quote("."));
                int length = split3[1].length();
                long b = Utils.longPow(10, length);
                partFraction = new Fraction(
                    (Long.parseLong(split3[0])*b) + Long.parseLong(split3[1]),
                    b);
            }
            
            //úprava pro práci s celými čísly (kvůli třídě InteractiveFractionParsing)
            else{
                partFraction = new Fraction(Long.parseLong(part), 1);
            }
            sum = sum.add(partFraction);
        }
        return sum;
    }

    public String toString() {
       return n + "/" + d;
    }

    @Override
    public int intValue() {
        return (int) (n / d);
    }

    @Override
    public long longValue() {
        return  n /d;
    }

    @Override
    public float floatValue() {
        return (float) n / (float) d;
    }

    @Override
    public double doubleValue() {
        return (double) n / (double) d;
    }
}