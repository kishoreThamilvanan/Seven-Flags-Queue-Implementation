/**
 * Class RandomGenerator helps to random rides every time the method
 *      selectRide is called.
 */
public class RandomGenerator {

    /**
     * Method returns random rides everytime this method is called.
     *
     * @param rides
     * Parameter contains the array of rides.
     *
     * @return
     *  returns the randomly chosen ride from the given array of rides.
     */
    public static Ride selectRide(Ride[] rides)
    {
        int size = rides.length;
        int i= (int)(Math.random()*size);

        return rides[i];
    }

    /**
     * Method returns a random ride from the given recieved set of rides
     *  also based on the probability of each ride.
     *
     * @param rides
     *  Paramter contains the array of rides.
     *
     * @param probabilities
     *  paramter contains the probabilities of being chosen for each ride.
     *
     * @return
     * returns the randomly chosen ride based on the probability.
     *
     * @throws Exception
     *  throws Exception when the total probability is not equal to 1.
     */
    public static Ride selectRide(Ride[] rides,
                                  double[] probabilities) throws Exception
    {
        double totalProb =0;

        for(int i=0;i<probabilities.length;i++)
        {
            totalProb+=probabilities[i];
        }

        if(totalProb != 1)
            throw new Exception(){};

        else
        {
            double a,b,c,d;
            a = probabilities[0];
            b = probabilities[1];
            c = probabilities[2];
            d = probabilities[3];

            double prob = Math.random()* rides.length;

            if(prob<=a)
                return rides[0];

            else if(prob<=(a+b))
                return rides[1];

            else if(prob<=(a+b+c))
                return rides[2];

            else
                return rides[3];


        }
    }
}
