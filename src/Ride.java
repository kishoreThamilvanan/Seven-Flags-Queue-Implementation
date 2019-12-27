import java.util.LinkedList;

/**
 * Class Ride contains the details of the different rides present.
 */
public class Ride {

    private int duration;// The time steps the ride takes for one ride cycle.
    private int timeLeft;// Time left end of the ride cycle.
    private String name;// Name of the ride.
    private int onRideCapacity;// capacity of the ride.
    VirtualLine virtualLine;// The Queue of the people waiting for the ride.
    HoldingQueue holdingQueue;// The queue of people who are next for the ride.
    LinkedList<Person> peopleOnRide = new LinkedList<Person>();


    public Ride() {
        this.duration = 0;
        this.timeLeft = 0;
        this.name = "";
        this.onRideCapacity = 0;
        this.virtualLine = new VirtualLine();
        this.holdingQueue = new HoldingQueue();
        this.peopleOnRide = new LinkedList<>();
    }

    /**
     * Method returns the duration of the ride.
     *
     * @return
     *  returns the duration of the ride.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Method sets the user entered duration to the ride.
     *
     * @param duration
     *  parameter contains the user entered value of duration.
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Method returns the time left on the ride.
     *
     * @return
     *  returns the time on the ride.
     */
    public int getTimeLeft() {
        return timeLeft;
    }

    /**
     * Method sets the user entered time to the time left in the ride.
     *
     * @param timeLeft
     * Parameter contans the user entered value of time left.
     */
    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    /**
     * Method returns the capacity of the ride.
     *
     * @return
     *  Returns the capacity of the ride.
     */
    public int getOnRideCapacity() { return onRideCapacity; }

    /**
     * Method sets the user entered capacity of the ride.
     *
     * @param onRideCapacity
     *  Parameter contains the capacity of the ried.
     */
    public void setOnRideCapacity(int onRideCapacity) {
        this.onRideCapacity = onRideCapacity;
    }

    /**
     * Method returns the name of the ride.
     *
     * @return
     *  returns the name of the ride.
     */
    public String getName() {
        return name;
    }

    /**
     * Method sets the user entered name of the ride.
     *
     * @param name
     *  Parameter contains the name of the ride to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method removes the person on ride after the ride is over.
     *
     * @return
     *  returns the person removed from the ride.
     */
    public Person dequeue(){
        peopleOnRide.peekFirst().setStatus(Status.Available);
        return peopleOnRide.removeFirst();
    }

    /**
     * Method adds a person to the ride.
     *
     * @param p
     *  Parameter contains the object of the Person to be added.
     */
    public void enqueue(Person p){
        peopleOnRide.push(p);
        p.setStatus(Status.OnRide);
        p.setRide(getName());
    }

    /**
     * Method returns the details of the ride in String.
     *
     * @return
     *  returns a String containing the details of the ride.
     */
    public String toString()
    {
        LinkedList<Person> tempQ = new LinkedList<Person>();
        Person p = new Person();
        String s ="";
        while(peopleOnRide.size()>0)
            tempQ.push(peopleOnRide.pop());

        while(tempQ.size()>0)
        {
            s += "["+tempQ.peek().queuePrint()+"] ";
            peopleOnRide.push(tempQ.pop());
        }

        return s;
    }


}
