package repositories;


import java.util.Map;

public interface Repository <T>{

    Map<Long, T> getItems();

    T getItemById(long id);

    void addItem(T item);

    long getIdCounter();
}
