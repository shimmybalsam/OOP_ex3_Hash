/**
 * This class represents a deleted item in a ClosedHashSet, to be used based on it's specific memory address.
 */
public class DeletedItem {
    String s;

    /**
     * creates a new deleted item.
     */
    public DeletedItem(){

        this.s = "DeletedItem";
    }

    /**
     * @return a String representing the deleted item
     */
    public String getDeletedItem(){

        return this.s;
    }
}
