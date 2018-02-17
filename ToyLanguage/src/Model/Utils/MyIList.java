package Model.Utils;

import Model.MyException;

import java.util.List;

public interface MyIList<E> {
    void add(E element) throws MyException;
    List<E> getOut();
    String toString();
}
