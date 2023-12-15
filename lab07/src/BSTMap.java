import java.util.Set;
import java.util.Iterator;

public class BSTMap <K extends Comparable<K>, V> implements Map61B<K, V>{

    private int count;
    private class BSTNode {
        private K key;
        private V value;

        private BSTNode left;
        private BSTNode right;
        public BSTNode(K a, V b){
            this.key = a;
            this.value = b;
        }
    }

//    private class BSTMapIterator implements Iterator<K>{
//        // constructor
//        public BSTMapIterator(){
//
//        }
//        // check if the next element exists
//        public boolean hasNext(){
//            while(iterator.hasNext()){
//                next();
//            }
//        }
//        // moves the iterator to next element
//        public K next(){
//
//        }
//        public void remove(){
//            throw new UnsupportedOperationException();
//        }
//    }
//

    private BSTNode root;
    /** Associates the specified value with the specified key in this map.
     *  If the map already contains the specified key, replaces the key's mapping
     *  with the value specified. */

    public BSTNode putHelper(BSTNode curr, K key, V value){
        if (curr == null) {
            count += 1;
            return new BSTNode(key, value);
        }
        if (key.compareTo(curr.key) == 0)
            curr.value = value;
        if (key.compareTo(curr.key) > 0)
            curr.right = putHelper(curr.right, key, value);
        if (key.compareTo(curr.key) < 0)
            curr.left = putHelper(curr.left, key, value);
        return curr;

    }
    public void put(K key, V value){

        root = putHelper(root, key, value);
    }

    public V getHelper(BSTNode curr, K key){
        if (curr == null)
            return null;
        if (key.compareTo(curr.key) == 0)
            return curr.value;
        else if (key.compareTo(curr.key) < 0)
            return getHelper(curr.left, key);
        else
            return getHelper(curr.right, key);
    }
    /** Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key. */
    public V get(K key){
        return getHelper(root, key);
    }

    public boolean containHelper(BSTNode curr, K key){
        if (curr == null)
            return false;
        if (key.compareTo(curr.key) == 0)
            return true;
        else if (key.compareTo(curr.key) < 0)
            return containHelper(curr.left, key);
        else
            return containHelper(curr.right, key);
    }

    /** Returns whether this map contains a mapping for the specified key. */
    public boolean containsKey(K key){
        return containHelper(root, key);
    }


    /** Returns the number of key-value mappings in this map. */
    public int size(){
        return count;
    }

    /** Removes every mapping from this map. */
    public void clear(){
        count = 0;
        root = null;
    }

    /** Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    public Set<K> keySet(){
        throw new UnsupportedOperationException();
    }

    /** Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key){
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator(){
        throw new UnsupportedOperationException();
    }
}
