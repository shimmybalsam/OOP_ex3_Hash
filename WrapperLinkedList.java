import java.util.LinkedList;

/**
 * This is a wrapper class for LinkedList so that it can be created as an Array Type.
 */
public class WrapperLinkedList {
    LinkedList<String> l;

    /** creates a new Wrapper of a String LinkedList */
    public WrapperLinkedList(){
        l = new LinkedList<String>();
    }

    /**
     * adds a new String value to the wrapped LinkedList
     * @param s the given String value which needs to be added.
     */
    public void add(String s){
        this.l.add(s);
    }

    /**
     * removes a given value from the wrapped LinkedList, if it exists.
     * @param s the value which needs to be removed.
     */
    public void remove(String s){
        this.l.remove(s);
    }

    /**
     * checks if a given value is in the Wrapper Linked List
     * @param s the String value to be checked.
     * @return true iff the value was found, false otherwise.
     */
    public boolean contains(String s){
        return this.l.contains(s);
    }

    /**
     * gets the first element in a Wrapped Link List and removes it making the next value in turn to
     * become the first (pop).
     * @return returns the first value in line.
     */
    public String get_data(){
        String data = this.l.removeFirst();
        return data;
    }

    /**
     *  checks if the WrappedLinkedList is empty.
     * @return true iff the WrapperLinkedList is empty, false otherwise.
     */
    public boolean isEmpty(){
        return this.l.isEmpty();
    }

}
