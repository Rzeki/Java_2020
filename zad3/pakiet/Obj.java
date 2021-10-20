package pakiet;

public class Obj {
    public Integer value;
    public int priority;

    public Obj(Integer value, int priority) throws ArithmeticException
    {
        if(priority<0)
        {
            throw new ArithmeticException("Priorytet nie może być mniejszy od 0!"); //warunek ze priorytet nie moze byc ujemny
        }
        try{
        this.value = value;
        this.priority = priority;
        }
        catch(ArithmeticException e){
            e.printStackTrace();
        }
    }
}