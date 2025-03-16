package pro1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils
{
    public static long gcd(long a, long b)
    {
        while(b != 0)
        {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    //meotda pro nezáporné umocnění s výstupen typu long (už vím že to bylo zbytečné, ale funguje to..)
    public static long longPow(int base, long exponent){
        long result = 1;
        while (exponent != 0){
            result *= base;
            exponent--;
        }
        return result;
    }

    //metoda pro získání hash hodnoty
    public static String hash(String path, String algorithm) throws NoSuchAlgorithmException {
        File file = new File(path);
        MessageDigest md = MessageDigest.getInstance(algorithm);
        
        try{
            byte[] fileBytes = Files.readAllBytes(file.toPath());
            md.update(fileBytes);
        } 
        catch (IOException e){
            System.out.println("Chyba při hashování souboru "+file);
        }

        byte[] hashBytes = md.digest();

        StringBuilder hex = new StringBuilder();
        for (byte i : hashBytes) {
            hex.append(String.format("%02x", i));
        }

        return hex.toString();
    }
}
