package Model.Utils;

import java.io.BufferedReader;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public interface MyIFileTable {
    int add(Pair<String, BufferedReader> var1);

    void del(int var1);

    Set<Integer> keySet();


    Pair<String,BufferedReader> get(Integer key) ;
    Collection<Pair<String, BufferedReader>> getFileNames();
    Collection<Pair<String, BufferedReader>> getValues();
    MyIDictionary<Integer, Pair<String, BufferedReader>> getContent();

    Pair<String, BufferedReader> lookup(int var1);
}
