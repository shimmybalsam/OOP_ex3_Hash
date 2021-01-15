/**
 * This class represents a Closed Hash Set.
 */
public class ClosedHashSet extends SimpleHashSet {
    float upperLF, lowerLF;
    int current_capacity = INITIAL_CAPACITY;
    int size = 0;
    String[] chs;

    /** The divider for both c1 and c2 used in the hashing formula of a ClosedHashSet. */
    private static final int C = 2;

    /**
     * creates a new, default, Closed Hash Set.
     */
    public ClosedHashSet(){
        chs = this.createCHS(INITIAL_CAPACITY);
        upperLF = INITIAL_UPPER_LOAD_FACTOR;
        lowerLF = INITIAL_LOWER_LOAD_FACTOR;
    }

    /**
     * creates a new Closed Hash Set, based on given load factors.
     * @param upperLoadFactor the maximum load factor, before rehashing is needed.
     * @param lowerLoadFactor the minimum load factor, before rehashing is needed.
     */
    public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor){
        this.chs = this.createCHS(INITIAL_CAPACITY);
        this.upperLF = upperLoadFactor;
        this.lowerLF = lowerLoadFactor;
    }

    /**
     * creates a new, default, Closed Hash Set and uploads to it given elements.
     * @param data a list of String elements which need to be hashed and added to the Closed Hash Set.
     */
    public ClosedHashSet(java.lang.String[] data){
        this();
        for (int i = 0; i < data.length; i++) {
            this.add(data[i]);
        }
    }

    /**
     * @param capacity the willed size of a new String Array.
     * @return a new String Array.
     */
    public String[] createCHS(int capacity){
        return new String[capacity];
    }

    /**
     * See SimpleSet
     * @param newVal
     * @return See SimpleSet
     */
    public boolean add(String newVal){
        int index;
        if (this.contains(newVal)){
            return false;
        }
        if ((float)(this.size()+1) / (float)(this.capacity()) > upperLF){
            this.rehash(capacity()*ENLARGE);
        }
        for (int i = 0; i < this.capacity(); i++){
            index = (newVal.hashCode()+((i+(i*i))/C))&(capacity()-1);
            if (chs[index] == null){
                chs[index] = newVal;
                size++;
                return true;
            }
        }
        return false;
    }

    /**
     * See SimpleSet
     * @param toDelete Value to delete
     * @return See SimpleSet
     */
    public boolean delete(String toDelete){
        if (!this.contains(toDelete)){
            return false;
        }
        for (int i = toDelete.hashCode()&(this.capacity()-1); i<this.capacity(); i++){
            if (chs[i]!= null && chs[i].equals(toDelete)){
                chs[i] = new DeletedItem().getDeletedItem();
                size--;
                break;
            }
        }
        if ((float)(this.size())/(float)(this.capacity()) < lowerLF){
            rehash((int)(this.capacity()*REDUCE));
        }
        return true;
    }

    /**
     * See SimpleSet
     * @param searchVal Value to search for
     * @return See SimpleSet
     */
    public boolean contains(String searchVal){
        for (int i = searchVal.hashCode()&(this.capacity()-1); i <this.capacity(); i++){
            if (chs[i] != null && chs[i].equals(searchVal)){
                return true;
            }
        }
        return false;
    }

    /**
     * See SimpleSet
     * @return See SimpleSet
     */
    public int size(){

        return size;
    }

    /**
     * @return The total number of slots in the Array of a Closed Hash Set.
     */
    public int capacity(){

        return current_capacity;
    }

    /**
     * builds a new Closed Hash Set either bigger or smaller, based on an add or delete action which surpassed
     * the upper/lower load factor.
     * @param new_capacity the size of the willed new Closed Hash Set.
     */
    public void rehash(int new_capacity){
        String[] newCHS = this.createCHS(new_capacity);
        DeletedItem d = new DeletedItem();
        String x = d.getDeletedItem();
        int index;
        for (int i = 0; i < this.capacity(); i++){
            // compares the exact spot in the memory of chs[i] and x to see if they are both a DeletedItem.
            if (chs[i]!=null && !(chs[i]==x)){
                for (int j = 0; j < new_capacity; j++) {
                    index = (chs[i].hashCode() + (j + (j * j)) / C) & (new_capacity - 1);
                    if (chs[j] == null) {
                        newCHS[index] = chs[i];
                        break;
                    }
                }
            }
        }
        current_capacity = new_capacity;
        chs = newCHS;
    }
}
