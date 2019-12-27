import java.util.LinkedList;

/**
 * Class HoldingQueue contains the details of the Persons present
 *      in the holding queue.
 */
public class HoldingQueue extends VirtualLine{

    private LinkedList<Person> hq = new LinkedList<Person>();
    private int maxSize;//maximum size of the line at once.

    /**
     * Method returns the capacity of the holding queue.
     *
     * @return
     *  returns the capacity of the holding queue.
     */
    public int getMaxSize() {
        return maxSize;
    }

    /**
     * Adds the specifies Person onto the rear of the holding queue.
     *
     * @param p
     *      user entered object to be added onto to the rear of the Line.
     */
    public void enqueue(Person p) { hq.addLast(p); }

    /**
     * Removes the Person at the front of the holding queue.
     *
     * @return
     *      returns the removed Person from the queue.
     *
     * @exception
     *  throws exception when the queueu is empty.
     */
    public Person dequeue() throws Exception {
        if(hq.peek()== null)
            throw new NullPointerException("Queue Empty");

        return hq.removeFirst();
    }

    /**
     * Method sets the user entered parameter as the new capacity.
     *
     * @param maxSize
     *  User entered new capacity to the current holding queue.
     */
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }


    /**
     * Method returns the details of the Persons in the Holding queue
     *  in a String.
     *
     * @return
     *  returns the String of Details of the Persons.
     */
    public String toString() {

        LinkedList<Person> tempQ = new LinkedList<Person>();

        String s ="";
        while(hq.size()>0)
            tempQ.push(hq.pop());

        while(tempQ.size()>0) {
            s += "["+tempQ.peek().queuePrint()+"] ";
            hq.push(tempQ.pop());
        }

        return s;

    }
}

