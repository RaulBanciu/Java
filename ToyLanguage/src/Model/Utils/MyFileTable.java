package Model.Utils;

import Model.MyException;

import javax.swing.*;
import java.io.BufferedReader;
import java.util.*;

import Model.Utils.MyIFileTable;

public class MyFileTable implements MyIFileTable {
    MyIDictionary<Integer, Pair<String, BufferedReader>> fileTable = new MyDictionary();
    int current = 0;

    public MyFileTable() {
    }

    public int add(Pair<String, BufferedReader> value) {
        this.fileTable.add(this.current, value);
        ++this.current;
        return this.current - 1;
    }

    public void del(int value) {
        this.fileTable.del(value);
    }

    public Pair<String, BufferedReader> lookup(int key) {
        return (Pair)this.fileTable.lookup(key);
    }

    public Collection<Pair<String, BufferedReader>> getFileNames() {
        return this.fileTable.getValues();
    }

    public Collection<Pair<String, BufferedReader>> getValues() {
        return this.fileTable.getValues();
    }
    public String toString() {
        String s = "";

        int i;
        for(Iterator var3 = this.fileTable.getKeys().iterator(); var3.hasNext(); s = s + (String)((Pair)this.fileTable.lookup(i)).getLeft()) {
            i = ((Integer)var3.next()).intValue();
            s = s + i;
            s = s + " --> ";
        }

        return s;
    }

    @Override
    public Set<Integer> keySet() {
        Set<Integer> x=new HashSet<Integer>(fileTable.getKeys());
        return x;
    }

    public Iterable<Map.Entry<Integer, Pair<String,BufferedReader>>> getAll() {
        return fileTable.getAll();
    }

    @Override
    public Pair<String,BufferedReader> get(Integer key) {
        return fileTable.lookup(key);}

    @Override
    public MyIDictionary<Integer, Pair<String, BufferedReader>> getContent() {
        return fileTable;
    }
}