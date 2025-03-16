package pro1;
import java.util.ArrayList;
import java.util.Scanner;

public class InteractiveDoubleParsing {
    Scanner sc = new Scanner(System.in);
    private ArrayList<Double> results = new ArrayList<Double>(); //list pro ukládání všech zadaných čísel (mohlo by se hodit :)
    private String input = "play";

    //statická meotda main (toto je jediný způsob, kterým se mi povedlo zprovoznit ji jako statickou)
    public static void main(){
        InteractiveDoubleParsing InteractiveDoubleParsing = new InteractiveDoubleParsing();
        InteractiveDoubleParsing.doubleParse();
        InteractiveDoubleParsing.vypis();
    }

    //hlavní metoda dle zadání
    public void doubleParse(){
        System.out.println("Spouštíte hlavní metodu třídy InteractiveDoubleParsing");
        System.out.println("Metoda umí pracovat s desetinnými čísly oddělenými desetinnou tečkou");
        System.out.println("Pro ukončení metody použijte příkaz: stop");
        
        while(!input.equalsIgnoreCase("stop")){
            boolean fail = false;
            double number = 0;

            System.out.println("Zadejte číselnou hodnotu, prosím");
            input = sc.nextLine();

            try {
              number = Double.parseDouble(input);
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
                System.out.println("Zadali jste číslo: " + number);
                results.add(number);
            }
        }
        input = "play";
    }

    //metoda pro výpis všech zadaných čísel
    public void vypis(){
        System.out.println("Vypisuji Vámi zadané hodnoty");
        for (double a : results){
            System.out.println(a);
        }
    }
}
