import pakiet.Obj;
import java.util.LinkedList; //Linked list jest najefektywniejsza, gdy przeszukujemy liste element po elemencie, w przeciwienstwie do array list, ktora jest efektywniejsza podczas random access

public class Queue {
    public LinkedList<Obj> tablica;
    Queue()
    {
        tablica = new LinkedList<Obj>();
    }

    void addElem(Obj a)
    {
        tablica.add(a); //O(1) dodawnanie obiektu do tablicy
    }

    Integer get()
    {
        int max=0;
        int index=tablica.size()-1;
        Obj temp=tablica.get(tablica.size()-1);
        for(Obj b : tablica)
        {
            if(max<b.priority)
            {
                max=b.priority;   //Zlozonosc O(n) dla przeszukiwania tablicy w poszukiwaniu maksimum gdzie n to rozmiar tablicy
                index = tablica.indexOf(b);
                temp = b;
            }
        }
        tablica.remove(index);
        return temp.value;
    }

    void show()
    {
        System.out.println();
        System.out.print("{ ");
        for(Obj k: this.tablica)
        {
            System.out.print("("+k.value+","+k.priority+") ");
        }
        
        System.out.print("}");
        System.out.println();
    }

    public static void main(String[] args)
    {
        Queue kolej = new Queue();
        Obj test = new Obj(1,1);
        Obj test2 = new Obj(7,2);
        Obj test3 = new Obj(5,2);
        Obj test4 = new Obj(89,7);
        Obj test5 = new Obj(45,3);
        Obj test6 = new Obj(25,3);
        Obj test7 = new Obj(16,4);
        kolej.addElem(test);
        kolej.addElem(test2);
        kolej.addElem(test3);
        kolej.addElem(test4);
        kolej.addElem(test5);
        kolej.addElem(test6);
        kolej.addElem(test7);
        kolej.show();
        System.out.println("Wyrzucam obiekt o najwiekszym priorytecie: "+kolej.get());
        kolej.show();
        System.out.println("Wyrzucam obiekt o najwiekszym priorytecie: "+kolej.get());
        System.out.println("Wyrzucam obiekt o najwiekszym priorytecie: "+kolej.get());
        kolej.show();
    }
}
