import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * This class analyzes and compares the run time for each type of SimpleSet for upload and contains actions.
 */
public class SimpleSetPerformanceAnalyzer {
    static long timeBefore, difference;

    /** Divide by this number in order to transform nanoseconds into milliseconds. */
    private static final int NANOTOMILI = 1000000;

    /** The number representing the index in an array in which a LinkedList is placed. */
    private static final int LINKEDLISTINDEX = 3;

    /** The number representing the amount of rounds needed for JVM's warm up before contains. */
    private static final int WARM_UP = 70000;

    /** The number representing the amount of rounds needed for JVM's warm up before contains, when using
     * LinkedList. */
    private static final int LINKED_LIST_WARM_UP = 7000;

    /**
     * calculates how long it takes to find a given value within a given SimpleSet.
     * @param set the SimpleSet in which we are searching.
     * @param val the String which we are searching for.
     * @return the time (in nanoseconds) it takes to find 'val' in 'set'.
     */
    public static long containsTime(SimpleSet set, String val){
            timeBefore = get_time();
            for (int i = 1; i <= WARM_UP; i++) {
                set.contains(val);
            }
            difference = get_time() - timeBefore;
            return difference/WARM_UP;
    }

    /**
     * warms up the JVM, in order to optimize time checks.
     * @param set the SimpleSet in which we are searching.
     * @param val the String which we are searching for.
     */
    public static void runContains(SimpleSet set, String val){
        for (int i = 1; i <= WARM_UP; i++) {
            set.contains(val);
        }
    }

    /**
     * same idea as containsTime, but specifically for a LinkedList.
     * @param set the SimpleSet in which we are searching.
     * @param val the String which we are searching for.
     * @return the time (in nanoseconds) it takes to find 'val' in 'set'.
     */
    public static long containsTimeLinkedList(SimpleSet set, String val){
        timeBefore = get_time();
        for (int i = 1; i <= LINKED_LIST_WARM_UP; i++) {
            set.contains(val);
        }
        difference = get_time() - timeBefore;
        return difference/LINKED_LIST_WARM_UP;
    }

    /**
     * same as runContains, but a specific warm up for checking within a LinkedList
     * @param set the SimpleSet LinkedList in which we are searching.
     * @param val the String which we are searching for.
     */
    public static void runContainsLinkedList(SimpleSet set, String val){
        for (int i = 1; i <= LINKED_LIST_WARM_UP; i++) {
            set.contains(val);
        }
    }

    /**
     * @return Time in nanoseconds.
     */
    public static long get_time(){
        return System.nanoTime();
    }

    /**
     * builds the arrays of data1 and data2, and the arrays of the different type of Simplesets, and runs
     * the time analyses as explained above.
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        SimpleSet[] types1 = new SimpleSet[5];
        SimpleSet[] types2 = new SimpleSet[5];
        types1[0] = new OpenHashSet();
        types1[1] = new ClosedHashSet();
        types1[2] = new CollectionFacadeSet(new TreeSet<>());
        types1[3] = new CollectionFacadeSet(new LinkedList<>());
        types1[4] = new CollectionFacadeSet(new HashSet<>());
        types2[0] = new OpenHashSet();
        types2[1] = new ClosedHashSet();
        types2[2] = new CollectionFacadeSet(new TreeSet<>());
        types2[3] = new CollectionFacadeSet(new LinkedList<>());
        types2[4] = new CollectionFacadeSet(new HashSet<>());
        String[] data1 = Ex3Utils.file2array("data1.txt");
        String[] data2 = Ex3Utils.file2array("data2.txt");
        // checks how long in ms it takes to add data1 to each set.
        System.out.println("Times for data1 upload:");
        for (int i = 0; i<types1.length; i++){
            timeBefore = get_time();
            for (int j = 0; j < data1.length; j++){
                types1[i].add(data1[j]);
            }
            difference = get_time() - timeBefore;
            System.out.println(difference/NANOTOMILI);
        }
        System.out.println("Times for contains in data1:");
        // checks times for contains "hi" and "-13170890158" in data1 for each type of set.
        for (int i = 0; i < types1.length; i++){
            if (i == 3){
                runContainsLinkedList(types1[LINKEDLISTINDEX], "hi");
                System.out.println("hi: " + containsTimeLinkedList(types1[LINKEDLISTINDEX], "hi"));
                runContainsLinkedList(types1[LINKEDLISTINDEX], "-13170890158");
                System.out.println("-13170890158: "+containsTimeLinkedList(types1[LINKEDLISTINDEX],"-13170890158"));
            }else{
                runContains(types1[i], "hi");
                System.out.println("hi: " + containsTime(types1[i], "hi"));
                runContains(types1[i], "-13170890158");
                System.out.println("-13170890158: " + containsTime(types1[i], "-13170890158"));
            }
        }
        // checks how long in ms it takes to add data2 to each set.
        System.out.println("Times for data2 upload:");
        for (int i = 0; i<types2.length; i++){
            timeBefore = get_time();
            for (int j = 0; j < data2.length; j++){
                types2[i].add(data2[j]);
            }
            difference = get_time() - timeBefore;
            System.out.println(difference/NANOTOMILI);
        }
        // checks times for contains "23" and "hi" in data2 for each type of set.
        System.out.println("Times for contains in data2:");
        for (int i = 0; i < types2.length; i++) {
            if (i == 3){
                runContainsLinkedList(types2[LINKEDLISTINDEX], "23");
                System.out.println("23: " + containsTimeLinkedList(types2[LINKEDLISTINDEX], "23"));
                runContainsLinkedList(types2[LINKEDLISTINDEX], "hi");
                System.out.println("hi: " + containsTimeLinkedList(types2[LINKEDLISTINDEX], "hi"));
            }else{
                runContains(types2[i], "23");
                System.out.println("23: " + containsTime(types2[i], "23"));
                runContains(types2[i], "hi");
                System.out.println("hi: " + containsTime(types2[i], "hi"));
            }
        }
    }
}
