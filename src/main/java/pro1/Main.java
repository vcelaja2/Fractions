package pro1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //InteractiveDoubleParsing.main();
        //InteractiveFractionParsing.main();
        System.out.println(new Fraction(1400,150));
        System.out.println(new Fraction(52440,3620));
        System.out.println(new Fraction(1300,1260));
        File inputDir=new File("C:/data/input");
        File outputDir=new File("C:/data/output");
        File[] outputFiles= outputDir.listFiles();

        //Vytvoření a případné vyprázdnění cílové složky
        if (!outputDir.mkdirs()){
            System.out.println("Čistím cílovou složku C:/data/output/");
            for (File outputFile : outputFiles){
                outputFile.delete();
            }
        }
        //načtení a zpracování dat (převážně hotové ze cvičení)
        File[] inputFiles= inputDir.listFiles();
        for (File inputFile : inputFiles) {
            File outputFile = new File(outputDir+inputFile.toString().substring(13));
            System.out.println("Reading " + inputFile);
            ExamRecord[] records = readInputFile(inputFile.toPath());
            System.out.println("První člověk v souboru "+records[0].getName());

            //zápis zpracovaných dat do cílového souboru
            try {
                BufferedWriter writer = Files.newBufferedWriter(outputFile.toPath());
                System.out.println("Writing " + outputFile);
                for (ExamRecord record : records) {  
                    writer.write(record.getName() + "," + record.getScore());
                    writer.newLine();
                }
                writer.close();
            } catch (IOException e) {
                System.out.println("Chyba ve zpracování souboru " + inputFile);
            }
        }

        //kontrola výsledků skrz porovnání hashů s refOutput soubrem
        File refDir=new File("C:/data/refOutput/output");
        File[] refFiles= refDir.listFiles();
        boolean mismatch = false;
        if (!refDir.exists()) {
            System.out.println("Kontrolní složka neexistuje");
        }
        else if (refFiles.length != outputFiles.length){
            System.out.println("Kontrola neúspěšná, nesedí počet souborů");
        }
        else{
            System.out.println("Nyní proběhne kontrola souborů");
            System.out.println("Projít všechny soubory může chvíli trvat");
            Arrays.sort(outputFiles);
            Arrays.sort(refFiles);
            for (int i = 0; i < refFiles.length; i++){
                try{
                    if (!Utils.hash(outputFiles[i].toString(), "MD5").equals(Utils.hash(refFiles[i].toString(), "MD5"))){
                    System.out.println("Nesedí soubor " +outputFiles[i].toString());
                    mismatch = true;
                    }
                    System.out.println(Utils.hash(outputFiles[i].toString(), "MD5")); 
                    System.out.println(Utils.hash(refFiles[i].toString(), "MD5")); 
                }
                catch(NoSuchAlgorithmException e){
                    mismatch = true;
                    System.out.println("Špatně zadaný algoritmus");
                    break;
                }
            }
        }
        
        if (mismatch == false){
            System.out.println("Kontrola proběhla úspěšně");
        }
        else{
            System.out.println("Kontrola skončila neúspěchem");
        }
    }

    public static ExamRecord[] readInputFile(Path path)
    {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            return new ExamRecord[0];
        }
        List<ExamRecord> resultList = new ArrayList<>();
        for(String line : lines)
        {
            String[] split= line.split("[:=;]");
            resultList.add(new ExamRecord(
                    split[0].trim(), //úprava pro hezčí formát
                    Fraction.parse(split[1])
            ));
        }
        return resultList.toArray(new ExamRecord[0]);
    }
}