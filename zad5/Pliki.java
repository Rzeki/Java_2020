import java.io.*;
import java.util.*;
import pliki.*;

public class Pliki {

    static public String deletedot(String v)
    {
        if(v.contains("."))
        {
            if(v.lastIndexOf(".")==v.length()-1)
            {
                liczba_zdan++;   //zdania sa jeżeli na końcu wyrazu występuje kropka (praktycznie liczymy liczbę kropek)
            }
            return v.replace(".","\0");
        }
        return v;
    }

    static public WordObject addW(int w,String s)
    {
        WordObject temp3 = new WordObject();        //metoda która dodaje do rankingu
        temp3.add(w,s);
        return temp3;
    }

    static public String deletecomma(String v)
    {
        if(v.contains(","))
        {
            return v.replace(",","\0");      
        }
        return v;
    }

    static public String unnecessarychars1(String v)
    {
        if(v.contains("!"))
        {
            return v.replace("!","\0");
        }
        return v;  
    }

    static public String unnecessarychars2(String v)
    {
        if(v.contains("?"))
        {
            return v.replace("?","\0");
        }
        return v;
    }

    static public String unnecessarychars3(String v)
    {
        if(v.contains("\""))
        {
            return v.replace("\"","\0");
        }
        return v;   
    }

    static int liczba_slow=0;
    static int liczba_zdan=0;
    static int liczba_niebialych_znakow=0;
    static String temp;

    public static void main(String[] args) throws IOException
    {
        try
        {
            if(args.length!=1)
            {
                throw new IOException("Niewłasciwa liczba argumentów!");
            }
            String file = args[0].toString();
            HashSet<String> unikalneslowa = new HashSet<>();
            ArrayList<String> wszystkieslowa = new ArrayList<>();
            ArrayList<WordObject> ranking = new ArrayList<>();
            
            Scanner scanner = new Scanner(new FileReader(file));
            while(scanner.hasNext())
            {
                liczba_slow++;
                temp = scanner.next();
                liczba_niebialych_znakow+=temp.length();
                temp = deletecomma(temp);
                temp = deletedot(temp);
                temp = unnecessarychars1(temp);
                temp = unnecessarychars2(temp);
                temp = unnecessarychars3(temp);
                temp = temp.toLowerCase();   //uniwersalizacja słowa, usuwanie niepotrzebnych znaków
                unikalneslowa.add(temp);
                wszystkieslowa.add(temp);
            }
            System.out.print("Liczba wyrazów w pliku: ");
            System.out.println(liczba_slow);
            System.out.print("Liczba zdań w pliku: ");
            System.out.println(liczba_zdan);
            System.out.print("Liczba niebiałych znaków w pliku: ");
            System.out.println(liczba_niebialych_znakow);                 

            int licznik=0;
            String[] unik = unikalneslowa.toArray(new String[unikalneslowa.size()]);
            String[] slow = wszystkieslowa.toArray(new String[wszystkieslowa.size()]);

            for(int i=0;i<unik.length;i++)
            {
                for(int j=0;j<slow.length;j++)
                {
                    if(unik[i].equals(slow[j]))
                    {
                        licznik++;                  //sprawdzanie ile razy powtarza sie dane slowo
                    }
                }
                ranking.add(addW(licznik,unik[i]));   //wstawianie liczby powtorzen i slowa do rankingu
                licznik=0;
            }

            Collections.sort(ranking, new Comparator<WordObject>(){
                @Override
                public int compare(WordObject a, WordObject b)
                {
                    return ((a.wystapienia>b.wystapienia)?-1:(a.wystapienia<b.wystapienia)?1:0);       //własny komparator, który porównuje i zamienia odpowiednio obiekty WordObject
                }
            });
            
            System.out.println();
            System.out.println();
            int miejsca=1;
            System.out.println(miejsca+". MIEJSCE");
            miejsca++;
            for(int i=0;i<ranking.size()-1;i++)
            {
                System.out.println("\""+ranking.get(i).slowo+"\" występuje: "+ranking.get(i).wystapienia+" razy");
                if(ranking.get(i).wystapienia != ranking.get(i+1).wystapienia)
                {
                    System.out.println(miejsca+". MIEJSCE");
                    miejsca++;
                }
            }
            System.out.println("\""+ranking.get(ranking.size()-1).slowo+"\" występuje: "+ranking.get(ranking.size()-1).wystapienia+" razy"); //ostatni element nie był wyświetlany przez potrzebę umieszczenia podium

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }
}
