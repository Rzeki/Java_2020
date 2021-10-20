import pakiet.*;

public class HouseItems extends Map<Integer,String> {

    public class Thief implements Visitor<Integer>
    {
        public boolean shouldRemove(Integer a)
        {
            return (a>1000);
        }
    }

    public HouseItems acceptVisitor(Visitor<Integer> obiekt)
    {
        HouseItems temp = new HouseItems();
        for(Type o: mapa)
        {
            if(!obiekt.shouldRemove(o.klucz))
            {
                temp.addElem(o.klucz, o.wartosc);  //trik ktory pozwala na "usuwanie" elementow z mapy
            }
        }
        return temp;
    }

    public static void main(String[] args)
    {
        HouseItems mapka = new HouseItems();
        mapka.addElem(500, "telefon");
        mapka.addElem(3000, "telewizor");
        mapka.addElem(700, "waza1");
        mapka.addElem(700, "waza2");
        mapka.addElem(2500, "laptop");
        mapka.addElem(20, "ladowarka od laptopa");
        mapka.addElem(1200, "dron");
        mapka.addElem(900, "roomba");
        mapka.addElem(10000, "samochod");
        mapka.addElem(300, "buty");
        Thief zlodziej = mapka.new Thief();
        System.out.println();
        System.out.println("Przed wizyta zlodzieja:");
        mapka.show();
        System.out.println();
        mapka = mapka.acceptVisitor(zlodziej);
        System.out.println("Po wizycie zlodzieja:");
        mapka.show();
        
    }
}
