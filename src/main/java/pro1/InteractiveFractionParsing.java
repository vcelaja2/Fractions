package pro1;
import java.util.ArrayList;
import java.util.Scanner;

public class InteractiveFractionParsing {
    Scanner sc = new Scanner(System.in);
    private ArrayList<Fraction> results = new ArrayList<Fraction>(); //list pro ukládání všech zadaných čísel (mohlo by se hodit :)
    private String input = "play";

    //statická meotda main (toto je jediný způsob, kterým se mi povedlo zprovoznit ji jako statickou)
    public static void main(){
        InteractiveFractionParsing InteractiveFractionParsing = new InteractiveFractionParsing();
        InteractiveFractionParsing.fractionParse();
        InteractiveFractionParsing.vypis();
    }

    //hlavní metoda dle zadání
    public void fractionParse(){
        System.out.println("Spouštíte hlavní metodu třídy InteractiveFractionParsing");
        System.out.println("Metoda umí pracovat s procenty a se zlomky");
        System.out.println("Pro ukončení metody použijte příkaz: stop");
        
        while(!input.equalsIgnoreCase("stop")){
            boolean fail = false;
            Fraction number = new Fraction(0, 1);

            System.out.println("Zadejte zlomek, prosím");
            input = sc.nextLine();

            try {
              number = Fraction.parse(input);
            } 
            catch (NumberFormatException e) {
                fail = true;
            }
        
            if (input.equals("stop")) {
                System.out.println("Metoda se nyní ukončí");
            }
            else if (fail == true){
                System.out.println("Zadali jste neplatný řetězec: " + input);
                fail = false;
            }
            else {
                System.out.println("Zadali jste zlomek: " + number);
                results.add(number);
            }
        }
        input = "play";
    }

    //metoda pro výpis všech zadaných čísel
    public void vypis(){
        System.out.println("Vypisuji Vámi zadané hodnoty");
        for (Fraction a : results){
            System.out.println(a);
        }
    }
}
