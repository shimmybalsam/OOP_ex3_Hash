import java.util.Collection;

/**
 * A Facade class for different type of SimpleSets.
 */
public class CollectionFacadeSet implements SimpleSet {

    private Collection<String> collection;

    /**
     * Creates a new facade wrapping the specified collection.
     */
    public CollectionFacadeSet(Collection<String> collection){
        this.collection = collection;
    }

    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set
     * @return False iff newValue already exists in the set
     */
    @Override
    public boolean add(String newValue) {
        if (!collection.contains(newValue)) {
            return collection.add(newValue);
        }
        else{
            return false;
        }
    }

    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted
     */
    @Override
    public boolean delete(String toDelete) {
        return collection.remove(toDelete);
    }

    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set
     */
    @Override
    public boolean contains(String searchVal) {
        return collection.contains(searchVal);
    }

    /**
     * @return The number of elements currently in the set
     */
    @Override
    public int size() {
        return collection.size();
    }
}
