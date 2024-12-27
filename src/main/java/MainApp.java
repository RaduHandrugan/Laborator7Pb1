/*1. În fișierul de intrare carti.json se găsesc informații despre cărțile dintr-o colecție personală.
Pentru fiecare carte se reține id-ul cărții (a câta carte citită), titlul cărții, numele autorului şi
anul apariției.
Să se creeze tipul înregistrare (record) Carte având componentele titlul, autorul şi
anul apariție.
Să se creeze o colecție de obiecte de tip Map<Integer,Carte> în care se vor adăuga
cărțile citite din fișier. Id-ul cărților reprezintă cheia elementelor din colecția Map, iar
valoarea elementelor din colecție este reprezentată de obiecte de tip Carte. Se va utiliza
implementarea HashMap a interfeței Map şi inferența tipului pentru variabilele locale. Pentru
colecția Map creată să se implementeze următoarele cerințe:
1) Să se afișeze colecția (se vor afișa atât cheile cât şi valorile, utilizând inferenţa tipului
pentru variabilele locale).
2) Să se șteargă o carte din colecția Map
3) Să se adauge o carte la colecția Map (se va utiliza metoda putIfAbsent)
4) Sa se salveze în fișierul JSON modificările făcute asupra colecției
5) Să se creeze o colecție Set<Carte> care extrage din colecția Map cărțile autorului
Yual Noah Harari. Se va utiliza Stream API şi colectori. Se va afișa colecția creată cu
ajutorul metodei forEach.
6) Să se afișeze ordonat după titlul cărți elementele din colecția Set folosind Stream API,
expresii Lambda şi referințe la metode.
* */


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class MainApp
{
    private static final String FILE_PATH = "D:\\Apps\\IntelliJ\\Laborator7Pb1\\src\\main\\resources\\carti.json"; //path pt fisierul carti json

    public static void main(String[] args)
    {
        //inititalizare object
        ObjectMapper mapper = new ObjectMapper();


        Map<Integer, Carte> cartiMap = new HashMap<>();  // declar colectia MAP

        try      //citire din fisierul carti.json
        {

            cartiMap = mapper.readValue(new File(FILE_PATH), new TypeReference<>() {});
        } catch (IOException e)
        {
            System.out.println("Eroare la citirea din fisier! "+ e.getMessage());
        }

        // 1.afisare colectie intreaga
        System.out.println("\nColecția de carti este: ");
        cartiMap.forEach((id, carte) -> System.out.println("ID: " + id + ", Carte: " + carte)
        );



        // 2.stergere o carte din colectie
        int idDeSters=2;   // am ales sa sterg cartea cu ID-ul 2
        cartiMap.remove(idDeSters);
        System.out.println("\nCartea a fost stearsa cu succe!");
        cartiMap.forEach((id, carte) -> System.out.println("ID: " + id + ", Carte: " + carte)); //afisare dupa stergere

        // 3.adaug carte la colectie
        Carte carteNoua = new Carte("Ion", "Liviu Rebreanu", 1983);
        cartiMap.putIfAbsent(7, carteNoua);
        System.out.println("\nCartea s-a adaugat cu succes!");
        cartiMap.forEach((id, carte) -> System.out.println("ID: " + id + ", Carte: " + carte));

        // 4.salvare fisier
        try
        {
            mapper.writeValue(new File(FILE_PATH), cartiMap);
           // mapper.writeValue(new File("src/main/resources/carti_output.json"), cartiMap);  // mi-am creat un nou fisier in care sa afisez modificarile pt ca imi strica fisierul cu date original


        } catch (IOException e)
        {
            System.out.println("Eroare la salvarea în fisier! ");
        }

        // 5.set <carte>
        Set<Carte> cartiAutor = cartiMap.values().stream().filter(carte -> "Yuval Noah Harari".equals(carte.autorul())).collect(Collectors.toSet());
        System.out.println("\nAfisare a cartilor lui Yuval Noah Harari:");
        cartiAutor.forEach(System.out::println);

        // 6.afisare ordon alfabetic dupa titlul
        System.out.println("\nOrdonare dupa titlu: ");
        cartiAutor.stream().sorted(Comparator.comparing(Carte::titlul)).forEach(System.out::println);
    }
}
