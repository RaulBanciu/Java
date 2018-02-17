package Model.Utils;
import Model.MyException;

import java.util.List;

public interface MyIStack<T>{
    void push(T val);
    List<T> getStack();
    T pop() throws MyException;
    boolean isEmpty();
    String toString();
}
