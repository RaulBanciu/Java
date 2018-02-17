package Model.Utils;

import Model.MyException;

import java.util.Iterator;
import java.util.List;

public class MyList<T> implements MyIList<T> {
    java.util.List<T> list = new java.util.ArrayList<T>();

    @Override
    public void add(T elem) throws MyException {
        boolean res = list.add(elem);
        if (res == false) {
            throw new MyException("The list hasn't been modified!");
        }
    }

    public List<T> getOut(){
        return list;
    }

    public String toString() {
        String s = "";

        for(Iterator var3 = this.list.iterator(); var3.hasNext(); s = s + "\n") {
            T elem = (T)var3.next();
            s = s + elem.toString();
        }

        return s;
    }
}
