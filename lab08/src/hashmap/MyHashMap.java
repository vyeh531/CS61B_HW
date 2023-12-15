package hashmap;

import java.util.Iterator;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Set;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author Vivian Yeh
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int size;
    private static final int initialCapacity  = 16;
    private static final double LOADFACTOR = 0.75;
    private double loadFactor_own;
    private int capacity;

    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        buckets = createTable(initialCapacity);
        loadFactor_own = LOADFACTOR;
        capacity = initialCapacity;
        size = 0;
    }

    public MyHashMap(int initialCapacity) {
        buckets = createTable(initialCapacity);
        loadFactor_own = LOADFACTOR;
        capacity = initialCapacity;
        size = 0;
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        this.loadFactor_own = loadFactor;
        buckets = createTable(initialCapacity);
        capacity = initialCapacity;
        size = 0;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<Node>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] table = new Collection[tableSize];
        for (int i = 0; i < tableSize; i++){
            table[i] = createBucket();
        }
        return table;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!
    public void resize() {
        int capacity_new= capacity * 2;
        Collection<Node>[] bucket_new =  createTable(capacity_new);
        for (Collection<Node> table : buckets){
            if (!table.isEmpty()){
                for( Node x : table){
                    int hash = x.key.hashCode();
                    int index = Math.floorMod(hash, capacity_new);
                    Node newnode = createNode(x.key, x.value);
                    bucket_new[index].add(newnode);
                }
            }
        }
        capacity = capacity_new;
        buckets = bucket_new;
    }
    @Override
    public void put(K key, V value){
        int hash = key.hashCode();
        int index = Math.floorMod(hash, capacity);
        Node node = createNode(key, value);

        if (containsKey(key)) {
            for (Node i : buckets[index]) {
                if (i.key.equals(key)) {
                    i.value = value;
                }
            }
        } else {
            if (((double)size / capacity) > loadFactor_own) {
                resize();
                index = Math.floorMod(hash, capacity);
            }
            buckets[index].add(node);
            size += 1;
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key. */
    @Override
    public V get(K key){
        if (key == null) {
            return null;
        }
        int hash = key.hashCode();
        int index = Math.floorMod(hash, capacity);
        for (Node node : buckets[index]) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    /** Returns whether this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key){
        int hash = key.hashCode();
        int index = Math.floorMod(hash, capacity);

        for (Node node : buckets[index]) {
            if (node.key.equals(key)) {
                return true;
            }
        }
        return false;
    }


    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    /** Removes every mapping from this map. */
    @Override
    public void clear() {
        buckets = createTable(capacity);
        this.size = 0;
    }

    /** Returns a Set view of the keys contained in this map. Not required for Lab 8.
     * If you don't implement this, throw an UnsupportedOperationException. */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /** Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key){
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
