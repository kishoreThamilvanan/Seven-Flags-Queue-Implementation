/**
 * Class Person contains the attributes of the Customer
 *      his status, no. of lines he can be and his number.
 */
public class Person {

    private int number;// The number to reference each customer.
    private int maxLines;// maximum no. of lines the person can be on.
    private String ride;// Name of the ride that the customer is in.

    /**
     * enumeration containing the custtomers membership status.
     */
    public enum memStatus {
        Gold,Silver,Regular;
    }

    /**
     * Method returns maximum number of lines that the customer can be.
     *
     * @return
     *  returns the maximum number of lines the customer can be.
     */
    public int getMaxLines() {
        return maxLines;
    }

    Status Status;// variable of the Enumeration Status.
    public memStatus memStatus;// variable of the Enumeration memStatus.

    /**
     * Method returns the status of the Person.
     *
     * @return
     *  returns the Status of the Person.
     */
    public Status getStatus() {
        return Status;
    }

    /**
     * Method assigns the user desired Status to the current Status
     *      of the person.
     *
     * @param status
     *  user desired Status for the Person
     */
    public void setStatus(Status status) {
        Status = status;
    }

    /**
     * Method which returns the Person's number.
     *
     * @return
     *  returns the number of the Person.
     */
    public int getNumber() { return number; }

    /**
     * Method sets the user entered number for the Person.
     *
     * @param number
     *  user entered number for the Person.
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Method sets the user entered maximum number of lines to the Person's
     *      maximum number of lines.
     *
     * @param maxLines
     *   user entered max number of lines for the person.
     */
    public void setMaxLines(int maxLines) {
        this.maxLines = maxLines;
    }

    /**
     * Method returns the name of the Ride in which the person is currently on.
     *
     * @return
     *  returns the name of the ride the person is currently onj.
     */
    public String getRide() {
        return ride;
    }

    /**
     * Method sets the name of the current ride entered by the
     *      user to the person.
     *
     * @param ride
     *  name of the current ride given by the user.
     */
    public void setRide(String ride) {
        this.ride = ride;
    }

    /**
     * Method returns the membership status of the Person.
     *
     * @return
     *   returns the membership status of the person.
     */
    public String getMemStatus() { return ""+memStatus; }

    /**
     * Method Checks the String parameter and sets the user entered
     *      membership Status to the person
     *
     * @param s
     *  String parameter which contains the user entered membereship Status.
     *
     * @return
     *  returns the boolean value whether the program is successfully performed.
     */
    public Boolean setMemStatus(String s) {
        if(s.compareToIgnoreCase("Gold")==0)
        {
            this.memStatus = memStatus.Gold;
            return true;
        }

        else if(s.compareToIgnoreCase("Silver")==0)
        {
            this.memStatus = memStatus.Silver;
            return true;
        }

        else if(s.compareToIgnoreCase("Regular")==0)
        {
            this.memStatus = memStatus.Regular;
            return true;
        }

        return false;
    }

    /**
     * Default constructor.
     */
    public Person()
    {
        number =0;
        maxLines=0;
        Status = Status.Available;
        memStatus = memStatus.Regular;

        ride = "";
    }

    /**
     * Parametrised constructor.
     *
     * @param number
     *  parameter contains the number of the Person to be set.
     */
    public Person(int number)
    {
        if(number < 0)
            System.out.print("\nThe number cannot be negative.");
        else
            this.number = number;
    }

    /**
     * Method returns the details of the customer for normal print.
     *
     * @return
     *  return the string containing the details of the person.
     */
    public String customerPrint()
    {
        return getRide()+ " "+getStatus();
    }

    /**
     * Method return the details of the customer for Queue print.
     *
     * @return
     */
    public String queuePrint() { return getMemStatus()+" "+getNumber(); }

    /**
     * Method returns a cloned object of the Person.
     *
     * @return
     *  returns the cloned Object.
     *
     * @throws CloneNotSupportedException
     *  throws when the clone is not supported.
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Method returns the string containing the details of the Person.
     *
     * @return
     *  returns the String of details of the person.
     */
    @Override
    public String toString() {
        return "[ " + memStatus +" "+
                number +
                " ]";
    }
}