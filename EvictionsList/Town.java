
/**
 * Keeps information about a town and its eviction data, in a certain year.
 *
 * @author cs230 staff (SK)
 * @version Oct 7, 2019
 */
public class Town implements Comparable<Town>
{
    private String name;         // name of this town
    private String state;        // state it belong to
    private int population;     
    private double povertyRate;  //from 0.0 to 100.00 %
    private int evictions;       //number of evictions that actually happened
    private double evictionRate; //defined as % of evictions over 
    //the town population, like 2.26 for 2.26%
    private boolean flagged; //true when evictionRate > threshold
    
    final private double SMALL_NUM = 0.0001; //to be used when testing doubles for equality

    /**
     * constructs a Town object given its name, state, population, poverty rate,
     *                                     and num of actual evictions in a certain year.
     * 
     * @param name the name of the Town
     * @param state the state this town belong to  
     * @param pop the population of the town
     * @param povRate the poverty rate in the town
     * @param evictions the number of actual evictions in this town in a certain year
     * 
     * */
    public Town(String name, String state, int pop, double povRate,
    int evictions) 
    {
        this.name = name;
        this.state = state;
        population = pop;
        povertyRate = povRate;
        this.evictions = evictions;
        flagged = false; //default value

        //calculate and assigne the evictionRate
        evictionRate = ((double)evictions/population) * 100; //ex: 2.26 for 2.26%
        //pay attention here as you devide two integers, expecting a non-zero double value
    }

    /**
     * returns the eviction rate for this town
     * 
     * @return The evictionRate for this town
     * */
    public double getEvictionRate(){
        return evictionRate;
    }

    /**
     *  getter to return whether this town is on watch or not
     * 
     * @return true if this town should be on watch, false otherwise
     * */
    public boolean getFlagged() {
        return flagged;
    }

    /**
     *  getter to return the name of this town
     * 
     * @return the name of this town
     * */
    public String getName() {
        return name;
    }

    /**
     *  getter to return the satte of this town
     * 
     * @return the state of this town
     * */
    public String getState() {
        return state;
    }

    /**
     * returns a string represenation of this Town object
     * 
     * @return the string representation of this object
     * */
    public String toString() {
        return population + "\t" +
        povertyRate + "\t"  + 
        "\t" + evictions + "\t"  +
        "\t" + evictionRate + "\t" + flagged + "\t" + name + ", " + state; 
    }

    /*
     * Determines whether the current Town is volnerable, based on the input threshold,
     * and flags it accordingly
     * 
     * @param the threshold used to determine whether this Town is volnerable
     * @return true if this town's flag was set to true, false otherwise
     * */
    public boolean setFlag(double threshold) {
        //true if town's eviction rate is greater than the threshold, 
        //false otherwise
        boolean f = this.evictionRate > threshold; 
        this.flagged = f;
        return f;
    }

    /**
     * compares this Town to the inputted one. The comparison is based on the 
     * eviction rates. 
     * 
     * returns -1, 0, or +1 if this Town has, respectively, a smaller,
     * equal or greater evictionRate than the inputted one. 
     * 
     * @param other the Town to compare to this Town
     * 
     * @return  -1 if this Town has smaller eviction rate than the inputted one
     *          +1 if this Town has greater eviction rate than the inputted one
     *          0 if this Town has the same eviction rate as the inputted one
     * */
    public int compareTo(Town other) {

        //tiny small diff in the eviction rates, practically the same rates
        if (Math.abs(this.evictionRate-other.evictionRate) < SMALL_NUM)  
            return 0;

        if (this.evictionRate < other.evictionRate) 
            return -1;
        else 
            return 1;
    }

    /**
     * Compares this Town to the inputed one for equality: 
     * Two towns are considered equal when they have the same name and
     * belong to the same state.
     * 
     * @param other the Town to compare to this Town for equality
     * 
     * @return  true if the two towns have the same name and state, 
     * false otherwise.
     * */
    public boolean equals(Town other) {
        return name.equals(other.name) && state.equals(other.state);
    }

    /*
     * main()
     * for testing
     * 
     */
    public static void main(String[] args) {
        //create two town for testing
        Town holland = new Town("Holland", "TX", 609, 12.16, 4);
        Town sanCarlos = new Town("SanCarlos", "CA", 29454, 1.52, 4);

        System.out.println("Testing toString()");
        System.out.println(holland);

        System.out.println(sanCarlos);

        //test compateTo()
        System.out.println("\nTesting compareTo()");
        System.out.println("compare holland to sanCarlos (positive): " + 
            holland.compareTo(sanCarlos));
        System.out.println("compare sanCarlos to holland (negative): " + 
            sanCarlos.compareTo(holland));
        System.out.println("compare holland to holland (zero): " + 
            holland.compareTo(holland));

        //test equals()
        System.out.println("\nTesting equals()");
        System.out.println("holland equals to holland (true): " + 
            holland.equals(holland));
        System.out.println("holland equals to sanCarlos (false): " + 
            holland.equals(sanCarlos));

        System.out.println(holland.getEvictionRate());
        holland.setFlag(0.010);
        System.out.println(holland.getFlagged());
    }

}
