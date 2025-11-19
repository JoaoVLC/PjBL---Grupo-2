package main.service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Repository<T> implements Iterable<T> {

    private List<T> itens = new ArrayList<>();

    public void add(T item) {
        itens.add(item);
    }

    public List<T> getAll() {
        return itens;
    }

    public void remove(T item) {
        itens.remove(item);
    }

    @Override
    public Iterator<T> iterator() {
        return itens.iterator();
    }

    public int size() {
        return itens.size();
    }
}
