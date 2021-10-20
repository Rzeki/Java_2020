package pakiet;

public interface Visitor<T> {
    public boolean shouldRemove(T a);
    public void elo();
}
