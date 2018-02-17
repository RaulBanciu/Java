package Model.Utils;

import Model.Utils.MyIDictionary;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class MyDictionary<K, V> implements MyIDictionary<K, V> {
    Map<K, V> map = new HashMap();

    public MyDictionary() {
    }
    @Override
    public MyIDictionary<K, V> cloned() {
        MyIDictionary<K, V> d = new MyDictionary<>();
        for(K key : this.keySet())
            d.add(key, map.get(key));
        return d;
    }
    public MyDictionary (MyIDictionary<K,V> d){
        map=d.getContent();
    }

    public void add(K key, V value) {
        this.map.put(key, value);
    }

    public void del(K key) {
        this.map.remove(key);
    }

    public void update(K key, V value) {
        this.map.put(key, value);
    }

    public V lookup(K key) {
        return this.map.get(key);
    }

    public boolean isDefined(K key) {
        return this.map.containsKey(key);
    }

    public Collection<K> getKeys() {
        return this.map.keySet();
    }

    public Collection<V> getValues() {
        return this.map.values();
    }

    public Set<K> keySet() {
        return map.keySet();
    }

    public V get(K key) {
        return map.get(key);
    }

    public Iterable<Map.Entry<K, V>> getAll() {
        return map.entrySet();
    }

    public String toString() {
        String s = "";

        for(Iterator var3 = this.map.keySet().iterator(); var3.hasNext(); s = s + "\n") {
            K elem = (K)var3.next();
            s = s + elem;
            s = s + " --> ";
            s = s + this.map.get(elem);
        }

        return s;
    }

    @Override
    public Map<K, V> getContent() {
        return map;
    }

    @Override
    public void setContent(Map<K, V> m) {
        map=m;
    }
}
