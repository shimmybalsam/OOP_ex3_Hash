/**
 * This class represents an Open Hash Set, where each slot contains a wrapped LinkedList.
 */
public class OpenHashSet extends SimpleHashSet {

    WrapperLinkedList[] ohs;
    float upperLF, lowerLF;
    int current_capacity = INITIAL_CAPACITY;
    int size = 0;

    /**
     * creates a new, default, Open Hash Set.
     */
    public OpenHashSet(){
        this.ohs = this.createInitialOHS(INITIAL_CAPACITY);
        this.upperLF = INITIAL_UPPER_LOAD_FACTOR;
        this.lowerLF = INITIAL_LOWER_LOAD_FACTOR;
    }

    /**
     * creates a new Open Hash Set, based on given load factors.
     * @param upperLoadFactor the maximum load factor, before rehashing is needed.
     * @param lowerLoadFactor the minimum load factor, before rehashing is needed.
     */
    public OpenHashSet(float upperLoadFactor, float lowerLoadFactor){
        this.ohs = this.createInitialOHS(INITIAL_CAPACITY);
        this.upperLF = upperLoadFactor;
        this.lowerLF = lowerLoadFactor;
    }

    /**
     * creates a new, default, Open Hash Set and uploads to it given elements.
     * @param data a list of String elements which need to be hashed and added to the Open Hash Set.
     */
    public OpenHashSet(java.lang.String[]data){
        this();
        for (int i = 0; i < data.length; i++) {
            this.add(data[i]);
        }
    }

    /**
     * @param size the willed size of a new WrapperLinkedList Array.
     * @return a new WrapperLinkedList Array.
     */
    public WrapperLinkedList[] createInitialOHS(int size){
        return new WrapperLinkedList[size];
    }

    /**
     * See SimpleSet
     * @param newVal
     * @return see SimpleSet
     */
    public boolean add(String newVal){
        int index;
        if (this.contains(newVal)){
            return false;
        }
        if ((float)(this.size()+1) / (float)this.capacity() <= upperLF) {
            index = newVal.hashCode() & (this.capacity() - 1);
        }else {
            this.rehash(this.capacity() * ENLARGE);
            index = newVal.hashCode() & (this.capacity() -1);
        }
        if (ohs[index] == null) {
            ohs[index] = new WrapperLinkedList();
        }
        ohs[index].add(newVal);
        size ++;
        return true;
    }

    /**
     * see SimpleSet
     * @param toDelete Value to delete
     * @return see SimpleSet
     */
    public boolean delete(String toDelete){
        if (!this.contains(toDelete)){
            return false;
        }
        int hash = toDelete.hashCode()&(this.capacity()-1);
        ohs[hash].remove(toDelete);
        if ((float)(this.size()) / (float)(this.capacity()) <= lowerLF) {
            this.rehash((int) (this.capacity() * REDUCE));
        }
        size --;
        return true;
    }

    /**
     * see SimpleSet
     * @param val
     * @return see SimpleSet
     */
    public boolean contains(String val) {
        int hash = val.hashCode() & (capacity() - 1);
        if (ohs[hash] == null){
            return false;
        }
        return ohs[hash].contains(val);
    }

    /**
     * see SimpleSet
     * @return see SimpleSet
     */
    public int size(){
        return size;
    }

    /**
     * builds a new Open Hash Set either bigger or smaller, based on an add or delete action which surpassed
     * the upper/lower load factor.
     * @param new_capacity the size of the willed new Open Hash Set.
     */
    public void rehash(int new_capacity){
        WrapperLinkedList[] new_OHS = createInitialOHS(new_capacity);
        for (int i = 0; i < this.capacity(); i++){
            if (!(ohs[i] == null)){
                while(!ohs[i].isEmpty()){
                    String data = ohs[i].get_data();
                    int new_index = data.hashCode()&(new_capacity-1);
                    new_OHS[new_index] = new WrapperLinkedList();
                    new_OHS[new_index].add(data);
                }
            }
        }
        current_capacity = new_capacity;
        ohs = new_OHS;
    }

    /**
     * @return The total number of slots in the Array of an Open Hash Set.
     */
    public int capacity(){
        return current_capacity;
    }
}
