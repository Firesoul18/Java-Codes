import java.util.Vector;

public class HashTable {
    private Vector<Object> v = new Vector<>();
    public void insert(Object key, Object data){
        int index = hashFunction(key);
        v.setElementAt(data,index);
    }

    private int hashFunction(Object key){
        int index=0;

        //implement hash function

        return index;
    }

    public static void main(String[] args) {
        HashTable h = new HashTable();
        h.insert("td",new Vector<Integer>());
    }
}
