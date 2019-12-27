import java.util.LinkedList;
import java.util.Scanner;

/**
 * Class contains the driver for the simulators
 */
public class SevenFlags extends RandomGenerator{

    public static int timeStep=0;
    static Ride[] rides = new Ride[4];
    static LinkedList<Person> rc = new LinkedList<Person>();
    static LinkedList<Person> sc = new LinkedList<Person>();
    static LinkedList<Person> gc = new LinkedList<Person>();
    public static int regular_customers;
    public static int silver_customers;
    public static int gold_customers;
    public static int n;

    /**
     * Method gets the necessary data from the user.
     * @throws Exception
     */
    public static void initial_data() throws Exception
    {
        rides[0] = new Ride();
        rides[1] = new Ride();
        rides[2] = new Ride();
        rides[3] = new Ride();

        Scanner input = new Scanner(System.in);

        System.out.print("\nWelcome to seven flags!");
        System.out.print("\nPlease enter the number of regular customers: ");
        regular_customers = input.nextInt();

        System.out.print("Please enter the number of silver customers: ");
        silver_customers = input.nextInt();

        System.out.print("Please enter the number of gold customers: ");
        gold_customers = input.nextInt();


        rides[0].setName("Blue Scream of Death");

        System.out.print("\n\nPlease enter the duration of Blue Scream of Death (minutes): ");
        rides[0].setDuration(input.nextInt());

        System.out.print("Please enter the capacity of Blue Scream of Death: ");
        rides[0].setOnRideCapacity(input.nextInt());

        System.out.print("Please enter the holding queue size of Blue Scream of Death: ");
        rides[0].holdingQueue.setMaxSize(input.nextInt());

        rides[1].setName("Kingda Knuth");

        System.out.print("\nPlease enter the duration of Kingda Knuth (minutes): ");
        rides[1].setDuration(input.nextInt());

        System.out.print("nPlease enter the Capacity of Kingda Knuth: ");
        rides[1].setOnRideCapacity(input.nextInt());

        System.out.print("nPlease enter the holding queue size of Kingda Knuth: ");
        rides[1].holdingQueue.setMaxSize(input.nextInt());

        rides[2].setName("i386 Tower of Terror");

        System.out.print("\nPlease enter the duration of i386 Tower of Terror (minutes): ");
        rides[2].setDuration(input.nextInt());

        System.out.print("Please enter the capacity of i386 Tower of Terror: ");
        rides[2].setOnRideCapacity(input.nextInt());

        System.out.print("Please enter the holding queue size of i386 Tower of Terror: ");
        rides[2].holdingQueue.setMaxSize(input.nextInt());

        rides[3].setName("GeForce");

        System.out.print("\nPlease enter the duration of GeForce: ");
        rides[3].setDuration(input.nextInt());

        System.out.print("Please enter the capacity of GeForce: ");
        rides[3].setOnRideCapacity(input.nextInt());

        System.out.print("Please enter the holding queue size of GeForce: ");
        rides[3].holdingQueue.setMaxSize(input.nextInt());

        System.out.print("\nPlease Enter the number of time Steps to be run: ");
        n = input.nextInt();

        // create customers based on the above obtained details.
        createCustomers();

        // assign the created customers to random rides.
        assignCustomers();
    }

    /**
     * Method creates and initializes user entered number of customers
     *      in each catogory.
     */
    public static void createCustomers(){


        for(int i =1; i<=gold_customers;i++)
        {
            Person p = new Person();
            p.setNumber(i);
            p.setMaxLines(3);
            p.setMemStatus("Gold");
            gc.push(p);
        }

        for(int i =1; i<=silver_customers;i++)
        {
            Person p = new Person();
            p.setNumber(i);
            p.setMaxLines(2);
            p.setMemStatus("Silver");
            sc.push(p);
        }

        for(int i =1; i<=regular_customers;i++)
        {
            Person p = new Person();
            p.setNumber(i);
            p.setMaxLines(1);
            p.setMemStatus("Regular");
            rc.push(p);
        }
    }

    /**
     * Method assigns the different customers on the rides randomly
     *      and also ensure to put gold and silver customers in more than
     *          one ride(i.e. in other virtual lines).
     *
     *  @exception
     *   throws Exception when the ride is null
     */
    public static void assignCustomers() throws Exception{

        // Assigning gold customers
        while(gc.size()>0)
        {
            Ride t = selectRide(rides);

            // if the ride size is less than the ride capacity add the
            //      person on the the ride
            if(t.peopleOnRide.size() < t.getOnRideCapacity()) {
                Person g1 = gc.peekFirst();
                Person g2 = gc.peekFirst();

                    gc.peekFirst().setStatus(Status.OnRide);
                    t.enqueue(gc.removeFirst());


                t = selectRide(rides);
                t.virtualLine.enqueue(g1);

                t = selectRide(rides);
                t.virtualLine.enqueue(g2);
            }

            /**
             * if the holding queue size is less than the maximum capacity
             *      then add it to the holdin queue.
             */
            else if(t.holdingQueue.getMaxSize() > t.holdingQueue.size()) {
                gc.peekFirst().setStatus(Status.Holding);
                t.holdingQueue.enqueue(gc.removeFirst());
            }

            /**
             * else then just add the person to the virtual queue.
             */
            else {
                gc.peekFirst().setStatus(Status.Available);
                t.virtualLine.enqueue(gc.removeFirst());
            }

        }

        // Assigning silver customers
        while(sc.size()>0) {

            Ride t = selectRide(rides);

            // if the ride size is less than the ride capacity add the
            //      person on the the ride
            if (t.peopleOnRide.size() < t.getOnRideCapacity()) {
                Person s1 = sc.peekFirst();

                sc.peekFirst().setStatus(Status.OnRide);
                t.enqueue(sc.removeFirst());


                t = selectRide(rides);
                t.virtualLine.enqueue(s1);

            }

            /**
             * if the holding queue size is less than the maximum capacity
             *      then add it to the holding queue.
             */
            else if (t.holdingQueue.getMaxSize() > t.holdingQueue.size()) {
                sc.peekFirst().setStatus(Status.Holding);
                t.holdingQueue.enqueue(sc.removeFirst());
            }

            /**
             * else then just add the person to the virtual queue.
             */
            else {
                sc.peekFirst().setStatus(Status.Available);
                t.virtualLine.enqueue(sc.removeFirst());
            }
        }

        // Assigning regular customers
        while(rc.size()>0) {

            Ride t = selectRide(rides);

            // if the ride size is less than the ride capacity add the
            //      person on the the ride
            if (t.peopleOnRide.size() < t.getOnRideCapacity()) {
                rc.peekFirst().setStatus(Status.OnRide);
                t.enqueue(rc.removeFirst());
            }

            /**
             * if the holding queue size is less than the maximum capacity
             *      then add it to the holdin queue.
             */
            else if (t.holdingQueue.size() < t.holdingQueue.getMaxSize()) {
                rc.peekFirst().setStatus(Status.Holding);
                t.holdingQueue.enqueue(rc.removeFirst());

            }

            /**
             * else then just add the person to the virtual queue.
             */
            else {
                rc.peekFirst().setStatus(Status.Available);
                t.virtualLine.enqueue(rc.removeFirst());
            }
        }


    }

    /**

    /**
     * Method increments the timeStep of the simulator and does the necessary
     *      functions when time is increased
     */
    public static void nextTimeStep() throws Exception {
        timeStep++;

        for (int i = 0; i < 4; i++) {
            rides[i].setTimeLeft(Math.abs(rides[i].getDuration()-timeStep));


            /* if the ride is over, remove the customers out and put them in
             *    respective customer queue to add them in another queue
             */
            if (rides[i].getTimeLeft() == 0) {
                while (rides[i].peopleOnRide.size() > 0) {

                    Person t = rides[i].peopleOnRide.peekFirst();
                    if (t.getMemStatus().equalsIgnoreCase("Gold")) {
                        rides[i].peopleOnRide.peekFirst().setStatus(Status.Available);
                        gc.push(rides[i].dequeue());
                    }

                    else if (t.getMemStatus().equalsIgnoreCase("Silver")) {
                        rides[i].peopleOnRide.peekFirst().setStatus(Status.Available);
                        sc.push(rides[i].dequeue());
                    }

                    else {
                        rides[i].peopleOnRide.peekFirst().setStatus(Status.Available);
                        rc.push(rides[i].dequeue());
                    }

                }

                /*
                 Now customer standing in holding queues will be pushed onto the
                     ride and the people on the virtual queue will be pushed to
                          holding queue.
                 */

                while(rides[i].peopleOnRide.size()< rides[i].getOnRideCapacity())
                {
                    //dequeuing from holding queue and adding in the ride.
                    if(rides[i].holdingQueue.peekFirst() != null)
                    rides[i].enqueue(rides[i].holdingQueue.dequeue());
                }

                while(rides[i].holdingQueue.size()<rides[i].holdingQueue.getMaxSize())
                {
                    //dequeueing from the virtual queue to the holding queue

                    if(rides[i].virtualLine.peekFirst() != null)
                    rides[i].holdingQueue.enqueue(rides[i].virtualLine.dequeue());
                }

            }
        }


        /*
                Now pushing the removed customers from the respective customer
                      queues to virtual lines of random rides.
         */
        while(gc.size()>0){
            Ride r = selectRide(rides);
            r.virtualLine.enqueue((Person) gc.removeFirst());
        }

        while(sc.size()>0){
            Ride r = selectRide(rides);
            r.virtualLine.enqueue((Person) sc.removeFirst());
        }

        while(rc.size()>0){
            Ride r = selectRide(rides);
            r.virtualLine.enqueue((Person) rc.removeFirst());
        }


    }

    /**
     * Method prints the details and status of the ride.
     */
    public static void rideStatus() {
        System.out.print("\nAt Time " + timeStep + ": ");

        for (int i = 0; i < 4; i++) {
            rides[i].setTimeLeft(Math.abs(rides[i].getDuration()-timeStep));

            System.out.print("\n\n"+rides[i].getName()+" - Time remaining: "
                    + rides[i].getTimeLeft());
            System.out.print("\nOn Ride: "+ rides[i].toString());
            System.out.print("\nHolding Queue: "+rides[i].holdingQueue.toString());
            System.out.print("\nVirtual Queue: "+rides[i].virtualLine.toString()+"\n");

            for(int j=0;j<15;j++)
            System.out.print("-");

        }

    }

    /**
     * Method prints the customer status
     * @throws Exception
     */
    public static void customerStatus() throws Exception
    {
        System.out.print("\nRegular Customers: ");

        LinkedList<Person> tempQ = new LinkedList<Person>();
        Person p = new Person();

        
        String s ="";
        for(int i=1;i<=regular_customers;i++)
        {
        	
            for(int j=0;j<4;j++)
            {
               // while( k < rides[j].getOnRideCapacity()) {
                	
                for(int k = 0;k<rides[j].peopleOnRide.size();k++){
                    
                	if(rides[j].peopleOnRide.
                    		get(k).getMemStatus().
                            equalsIgnoreCase("Regular") &&
                            rides[j].peopleOnRide.get(k).getNumber() == i) {

                        p = rides[j].peopleOnRide.get(k);
                        s += i + ") " + p.customerPrint() + "\n";
                        break;
                    }
                }

               

           }

     
        }

        System.out.println("\nNUM LINE STATUS");
        System.out.println("--- ---- ------");

        System.out.print(s);
    }


    /**
     * Main method to perform the simulation
     *
     * @param args
     *  args required for method to work.
     *
     * @throws Exception
     * throws exception when the queue is empty at times.
     */
    public static void main(String[] args) throws Exception
    {

        initial_data();

        for(int i=0;i<n; i++) {
            rideStatus();
            System.out.print("\n");
            customerStatus();

            System.out.print("\n\n");
            nextTimeStep();

            for(int j=0;j<50;j++)
                System.out.print("-");


        }


    }
}
