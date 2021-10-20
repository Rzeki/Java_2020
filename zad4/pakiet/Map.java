package pakiet;
import java.util.ArrayList;

public class Map<T,F>
{
    public ArrayList<Type> mapa;

    public class Type{ 
        public T klucz;
        public F wartosc;
        
        Type(T a,F b)
        {
            klucz = a;
            wartosc = b;
        }
    }

    public Map()
    {
        mapa = new ArrayList<>();
    }

    public void shouldRemove(T a)
    {
        for(Type e: mapa)
        {
            if(e.klucz.equals(a))
            {
                mapa.remove(e);
                break;
            }
        }
    }

    public F GetValue(T key) throws NullPointerException
    {
        for(Type o:mapa)
        {
            if(key.equals(o.klucz))
            {
                return o.wartosc;
            }
        }
        throw new NullPointerException("Nie znaleziono klucza!");
    }

    public boolean KeyExist(T key)
    {
        for(Type o:mapa)
        {
            if(key.equals(o.klucz))
            {
                return true;
            }
        }
        return false;
    }

    public void addElem(T key, F value)
    {
        Type temp = new Type(key,value);
        for(Type o: mapa)
        {
            if(o.klucz.equals(key))
            {
                shouldRemove(key);
                break;
            }
        }
        mapa.add(temp);
    }

    public void show()
    {
        System.out.print("{");
        for(Type o: mapa)
        {
            System.out.print("("+o.klucz+","+o.wartosc+") ");
        }
        System.out.println("}");
    }

}

