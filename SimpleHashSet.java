/**
 * Created by Shimmy on 4/25/2017.
 */
public abstract class SimpleHashSet implements SimpleSet {

    /** the number representing the initial size of a SimpleSet */
    protected static final int INITIAL_CAPACITY = 16;

    /** the number representing the enlargement factor used for rehashing before adding elements. */
    protected static final int ENLARGE = 2;

    /** the number representing the reduce factor used for rehashing after deleting elements. */
    protected static final double REDUCE = 0.5;

    /** the number representing the initial upper load factor of a SimpleSet */
    protected static final float INITIAL_UPPER_LOAD_FACTOR = 0.75f;

    /** the number representing the initial lower load factor of a SimpleSet */
    protected static final float INITIAL_LOWER_LOAD_FACTOR = 0.25f;

    /**
     * See SimpleSet
     * @param newValue New value to add to the set
     * @return See SimpleSet
     */
    @Override
    public boolean add(String newValue) {

        return false;
    }

    /**
     * See SimpleSet
     * @param searchVal Value to search for
     * @return See SimpleSet
     */
    @Override
    public boolean contains(String searchVal) {

        return false;
    }

    /**
     * See SimpleSet
     * @param toDelete Value to delete
     * @return See SimpleSet
     */
    @Override
    public boolean delete(String toDelete) {

        return false;
    }

    /**
     * See SimpleSet
     * @return See SimpleSet
     */
    @Override
    public int size() {

        return 0;
    }

    /**
     * @return The total number of slots in a SimpleSet Array.
     */
    public int capacity(){

        return 0;
    }
}
