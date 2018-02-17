package Model.Utils;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface MyIHeap <K,V>{
    int add(Integer val);
    void delete(Integer adr);

    boolean isEmpty();
    boolean isDefined(Integer adr);

    Integer getVal(Integer adr);

    public Set<Integer> keySet();
   Iterable<Map.Entry<K, V>> getAll();
    void update(Integer adr, Integer val);
    String toString();
    Collection<Integer> getValues();
    Collection<Integer> getAdresses();
    void setContent(Map<Integer, Integer> heap);
    Map<Integer, Integer> getContent();
}
