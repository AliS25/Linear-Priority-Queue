//Ali Sbeih 3/24/2022
/**
 * A container that stores elements, where each element has an
 * associated priority represented as a long value. Elements can
 * be inserted into the priority queue together
 * with an associated priority, where priorities are assumed to be
 * distinct. Smaller long values indicate higher
 * priority. Access is only granted to the highest priority element in
 * the queue. The queue is implemented using a doubly linked list.
 */
public class LinearPriorityQueue<E> implements SimplePriorityQueue<E>{
    //number of elements in the queue
    private int size=0;
    //highest priority element in the queue
    private Node<E> head=null;
    //lowest priority element in the queue
    private Node<E> tail = null;



    //Get the current size of the priority queue
    public int size(){
        return size;
    }



    /**
     * Determine if the priority queue is currently empty
     */
    public boolean isEmpty(){
       return head==null;
    }



    /**
     * Return the element with highest priority stored in the
     * queue. This method does not modify the state of the
     * queue.
     */
    public E min(){
        return head.value;
    }



    /**
     * Insert the element x with priority k.
     */
    public void insert(long k, E x){
        //Start at the highest priority element
        Node<E> cur = head;
        //loop through the queue until an element with lower priority(bigger long value)
        //is found or your reach the end of the loop
        for (int i = 0; i < size-1; ++i) {
            if(cur.priority<k){
                cur = cur.next;
            }
            else break;
        }
        // make a new Node to store x with priority k
        Node<E> nd = new Node<E>();
        nd.value = x;
        nd.priority = k;

// if the queue is empty, the newly added item is stored as the head and the tail
        if ( isEmpty() == true) {
            head = nd;
            tail = nd;
        }

        else {
            //if we are inserting at the head, make the created node the new head
            if (cur == head) {
                nd.next = head;
                head.previous = nd;
                head = nd;
            }
            //if we are inserting at the tail, make the created node the new tail
            else if(nd.priority>tail.priority) {
                tail.next=nd;
                nd.previous=tail;
                tail=nd;
            }
            //if we are inserting between the head and the tail, place the created node between
            //node cur(the one we stopped at in the loop) and its previous node
            else{
                Node<E> pred = cur.previous;
                pred.next = nd;
                nd.previous = pred;
                nd.next = cur;
                cur.previous = nd;
            }
        }
        //increase the size upon insertion
        ++size;
            }



    /**
     * Remove and return the element with highest priority in the queue.
     */
    public E removeMin() {
        //check that the queue is not empty
        if (!isEmpty()) {
            //store the value to be returned of the element with the highest priority
            E value = head.value;
            // remove the head(highest priority element) by updating it to be the next element in the queue
            head = head.next;
            //decrease the size upon removing
            --size;
            return value;
        }
        //if empty, return null
        return null;
    }



    /**
     * An inner class representing a node in the linked list. Each
     * node stores a reference to an element, its priority, as well
     * as a reference to the next and previous nodes in the list.
     */
    private class Node<E> {
        Node<E> next;
        Node<E> previous;
        E value;
        long priority;
    }
}
