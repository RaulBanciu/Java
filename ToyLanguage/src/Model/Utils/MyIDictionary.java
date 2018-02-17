package Model.Utils;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface MyIDictionary<K, V> {
    void add(K var1, V var2);

    void del(K var1);
    MyIDictionary<K, V> cloned();

    void update(K var1, V var2);

    V lookup(K var1);

    boolean isDefined(K var1);

    public Iterable<Map.Entry<K, V>> getAll();

    Set<K> keySet() ;

    V get(K key) ;
    Collection<K> getKeys();

    Collection<V> getValues();

    String toString();
    void setContent(Map<K,V> m);

    Map<K,V> getContent();
}
