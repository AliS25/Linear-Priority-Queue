//Ali Sbeih 3/25/2022
//A program to measure the running time of insert and removeMin operations
import java.util.Random;

public class PriorityQueuePerformanceTester {
    /** step size between tested sizes of queues */
    public static final int STEP = 2_00;

    /** maximum queue size to consider */
    public static final int MAX = 30_000;

    public static void main(String[] args){
        testInsert();
        testRemoveMin();
    }

    /**
     * Measure the (average) running times of insert for a range of instance
     * sizes of queues. Record the running times in a CSV file called
     * "insert-times.csv"
     */
    static void testInsert(){
        RunTimer rt = new RunTimer();
        CSVWriter csv = new CSVWriter("insert-times.csv");
        Random r = new Random();

        // make the first row of the csv file with appropriate
        // headings
        csv.addEntry("queue size");
        csv.addEntry("time per insert operation");
        csv.endLine();

        for (int size = STEP; size <= MAX; size += STEP) {
            // create a new queue of correct size
            SimplePriorityQueue<Integer> queue = new LinearPriorityQueue<Integer>();
//provide random values to use in operation
            for(int i=0; i<size;i++){
                // measure the elapsed time for insert
                rt.start();
                queue.insert(r.nextInt(),r.nextInt());
                rt.stop();
            }

            // add a new row with the size and the *average* time per
            // operation
            csv.addEntry(size);
            csv.addEntry(rt.getElapsedNanos() / size);
            csv.endLine();

            rt.reset();
        }
        csv.close();
    }
    /** Measure the (average) running times of removeMin for a range of instance
     * sizes of queues. Record the running times in a CSV file called
     * "remove-min-times.csv"
     */
    static void testRemoveMin(){
        RunTimer rt = new RunTimer();
        CSVWriter csv = new CSVWriter("remove-min-times.csv");
        Random r = new Random();

        // make the first row of the csv file with appropriate
        // headings
        csv.addEntry("queue size");
        csv.addEntry("time per removeMin operation");
        csv.endLine();

        for (int size = STEP; size <= MAX; size += STEP) {
            // create a new queue of correct size
            SimplePriorityQueue<Integer> queue = new LinearPriorityQueue<Integer>();
//provide random values to use in operation
            for(int i=0; i<size;i++){
                queue.insert(r.nextInt(),r.nextInt());
            }
            for(int i=0; i<size;i++){
                // measure the elapsed time for removeMin
                rt.start();
                queue.removeMin();
                rt.stop();
            }

            // add a new row with the size and the *average* time per
            // operation
            csv.addEntry(size);
            csv.addEntry(rt.getElapsedNanos()/size );
            csv.endLine();

            rt.reset();
        }
        csv.close();
    }


}
