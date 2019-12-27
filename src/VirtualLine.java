import java.util.LinkedList;

/**
 *  Class virtual line contains the list of Persons in the virtual line.
 */
public class VirtualLine extends java.util.LinkedList{

    private LinkedList<Person> vLine = new LinkedList<Person>();

    /**
     * Adds the specifies Person onto the rear of the Virtual Line.
     *
     * @param p
     *      user entered object to be added onto to the rear of the Line.
     */
    public void enqueue(Person p) { vLine.addLast(p); }

    /**
     * Removes the Person at the front of the VirtualLine.
     *
     * @return
     *      returns the removed Person from the queue.
     */
    public Person dequeue() throws Exception {
        if(vLine.peek()== null)
        throw new NullPointerException();

    return vLine.removeFirst(); }

    /**
     * Method returns string of details of the Persons present
     *      in the Virtual Line
     *
     * @return
     */
    public String toString()
    {

        LinkedList<Person> tempQ = new LinkedList<Person>();
        String s="";
        while(vLine.size()>0)
            tempQ.push((Person) vLine.pop());

        while(tempQ.size()>0) {
            s += "["+tempQ.peek().queuePrint()+"] ";
            vLine.push(tempQ.pop());
        }

        return s;
    }

    /**
     * Returns the Person at the front of the VirtualLine without
     *      removing them from the queue.
     *
     * @return
     *      returns the first Person from the VirtualLine.
     */
    public Person peek(){ return vLine.peekFirst(); }

    /**
     * Returns a boolean value after checking the Line is empty or not.
     *      returns true if there are no people returns false otherwise.
     * @return
     *      returns boolean value.
     */
    public boolean isEmpty() { return vLine.isEmpty(); }



}
