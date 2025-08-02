import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * EvictionsList.java
 * Reads some Town data from a file.
 * Determines which of the towns have increased eviction rate, according to a threshold
 * Outputs the results in a txt file.
 * This version uses java's LinkedLists
 *
 * @author cs230 staff (SK) and **** Bessie Li
 * @version 9/20
 */
public class EvictionsList
{
    //instance variables
    private String inFileName; //input data
    private LinkedList<Town> towns;

    private int numFlagged; //num of flagged towns
    //Notice, this number changes when the theshold changes

    private double THRESHOLD = 0.30; //0.30% eviction rate threshold;

    /**
     * This is the constructor for the class
     * 
     * @param  String  string of the file name that needs to be read
     */
    public EvictionsList(String filename){
        inFileName = filename;
        towns = new LinkedList<Town>();
        numFlagged = 0;
    }
    
    /**
     * The readFromFile method takes a file, reads it, and puts the information into a linked list
     * 
     * @param  String   string of the file name that has to be read
     */
    public void readFromFile(String filename){
        try{
            Scanner scan = new Scanner(new File(filename));
            
            while (scan.hasNext()){
                scan.nextLine();
                
                scan.useDelimiter("\t|\\r|\\n");
                
                String townName = scan.next();
                String state = scan.next();
                int pop = Integer.parseInt(scan.next());
                double povRate = Double.parseDouble(scan.next());
                int evic = Integer.parseInt(scan.next());
                
                Town newTown = new Town(townName, state, pop, povRate, evic);
                
                towns.add(newTown);
            }
            
            scan.close();
        } catch (IOException e){
            System.out.println(e);
        }
    }

    /**
     * The setThreshold method sets the new threshold
     *
     * @param t  double representation of what the threshold will be set to
     * */
    public void setThreshold(double t){
        THRESHOLD = t;
    }

    /**
     * The flagTowns method sets flags of every town in the linked list
     */
    public void flagTowns(){
        numFlagged = 0;
        for (Town town : towns){
            town.setFlag(THRESHOLD);
            if (town.getFlagged())
                numFlagged ++;
        }
    }
    
    /**
     * Returns a String representation of this object: The names of the
     * files the program reads from and writes to, the eviction rate threshold,
     * and the flagged towns.
     *
     * @return A string representation of the object
     * */
    public String toString() {
        String s = "Read " + towns.size() + " towns  from " + inFileName +
            ".\n The following (" + numFlagged + ")  are flagged, at threshold: "+ THRESHOLD + ":\n";

        s = s + "popul \tpovRate" + " \tevictions "+ "\teviction rate  \t   on watch \tname, state \n" ;
        for (int i=0; i<towns.size(); i++) {
            Town currentTown = towns.get(i);
            if (currentTown.getFlagged())
                s = s + currentTown + "\n";
        }
        return s;
    }

    /**
     * The writeToFile method takes a file and writes to that file
     * 
     * @param  String  string of the file name that has to be written to
     */
    public void writeToFile(String filename){
        try{ 
            PrintWriter writer = new PrintWriter(new File(filename));
            
            writer.println(toString());
            
            writer.close();
        } catch (IOException e){
            System.out.println(e);
        }
    }
    
    /**
     * The main method used for testing methods
     *
     * @param  String[]  commandline statement
     */
    public static void main (String[] args) {
    	System.out.println("NOW PROCESSING SMALL EVICTION DATA ----------------------------------");
    	
        String inFileName = "smallEvictionData.txt";
        EvictionsList e = new EvictionsList(inFileName);
        e.readFromFile(inFileName);
        
        e.setThreshold(0.08); //0.08%
        e.flagTowns();
        e.writeToFile("results1.txt");
        System.out.println(e);
        
        System.out.println("----------------------------------");
        
        e.setThreshold(0.5); //0.5%
        e.flagTowns();
        e.writeToFile("results2.txt");
        System.out.println(e);
        
        System.out.println("----------------------------------");

        e.setThreshold(0.8); //0.8%
        e.flagTowns();
        e.writeToFile("results3.txt");
        System.out.println(e);
        
        System.out.println("----------------------------------");
        
        e.setThreshold(8); //8%
        e.flagTowns();
        e.writeToFile("results4.txt");
        System.out.println(e);
        
        
        
        
        System.out.println("NOW PROCESSING COMPLETE EVICTIONDATA FILE ----------------------------------");
        
        
        
        
        String inFileName1 = "completeEvictionData.txt";
        EvictionsList e1 = new EvictionsList(inFileName1);
        e1.readFromFile(inFileName1);
        
        e1.setThreshold(0.08); //0.08%
        e1.flagTowns();
        e1.writeToFile("results5.txt");
        System.out.println(e1);
        
        System.out.println("----------------------------------");
        
        e1.setThreshold(0.5); //0.5%
        e1.flagTowns();
        e1.writeToFile("results6.txt");
        System.out.println(e1);
        
        System.out.println("----------------------------------");

        e1.setThreshold(0.8); //0.8%
        e1.flagTowns();
        e1.writeToFile("results7.txt");
        System.out.println(e1);
        
        System.out.println("----------------------------------");
        
        e1.setThreshold(8); //8%
        e1.flagTowns();
        e1.writeToFile("results8.txt");
        System.out.println(e1);
        
    }
}
