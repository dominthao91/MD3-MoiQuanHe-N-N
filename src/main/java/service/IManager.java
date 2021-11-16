package service;

import java.util.List;

public interface IManager<T>{
    List<T> showAll();
    T findById(int id);
    boolean add(T t, int[] arr);
    boolean edit(T t,int[] arr);
    boolean delete(int id);
}
