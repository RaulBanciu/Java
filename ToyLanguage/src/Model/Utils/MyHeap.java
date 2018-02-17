package Model.Utils;

import java.util.*;

public class MyHeap implements MyIHeap {

    Map<Integer, Integer> heap = new HashMap();
    int firstFree = 1;

    public MyHeap() {
    }

    @Override
    public int add(Integer val) {
        heap.put(firstFree, val);
        firstFree++;
        return firstFree - 1;
    }

    @Override
    public void delete(Integer adr) {
        heap.remove(adr);
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public boolean isDefined(Integer adr) {
        return heap.containsKey(adr);
    }

    @Override
    public Integer getVal(Integer adr) {
        return heap.get(adr);
    }

    @Override
    public void update(Integer adr, Integer val) {
        heap.put(adr, val);
    }

    @Override
    public String toString() {
        String s = "";

        for (Iterator i = this.heap.keySet().iterator(); i.hasNext(); s = s + "\n") {
            int key = (int) i.next();
            s = s + key;
            s += "-->";
            s += this.heap.get(key);
        }

        return s;
    }

    public Set<Integer> keySet() {
        return heap.keySet();
    }

    public Iterable<Map.Entry<Integer, Integer>> getAll() {
        return heap.entrySet();
    }

    @Override
    public Collection<Integer> getValues() {
        return heap.values();
    }

    @Override
    public Collection<Integer> getAdresses() {
        return heap.keySet();
    }

    @Override
    public Map<Integer, Integer> getContent() {
        return heap;
    }

    @Override
    public void setContent(Map heap) {
        this.heap=heap;
    }
}
