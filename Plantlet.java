import java.util.Random;

//A Plantlet of a Plant to be planted in your garden
//Plantlet is an *ABSTRACT CLASS*... All common Plantlet 
//attributes/functionality defined here and inherited by children
public abstract class Plantlet{
    
    
    //The string label used to represent a wilted Plantlet    
    private static final String WILTED_STR = "WILT";
    //The GU (ie "grow units") value used to represent a wilted Plantlet
    protected static final int WILTED_VALUE = -1;
    //Used for generating random values here and in children classes
    protected static Random rand = new Random();
    
    
    //The required GUs for the Plantlet to be considered "fully grown"
    private int requiredGU;
    //The number of GUs the Plantlet has currently grown
    private int currentGU = 0;
    //A single character used to identify the Plantlet type
    private char id;


    //At instantiation, the required GU and an ID is required for the Plantlet
    public Plantlet(int requiredGU, char id) {        
        this.requiredGU = requiredGU;
        this.id = id;        
    }
 
    //Sets the Plantlet to be wilted
    //Once a Plantlet is wilted, it cannot grow and cannot become un-wilted
    protected void wilt(){
        currentGU = WILTED_VALUE;
    }
    
    //Returns how much the Plantlet has grown (its current accumulated GUs)
    public int getGU(){
        return currentGU;
    }
    
    //Returns how many GUs are required for this Plantlet to be "fully grown"
    public int getRequiredGU(){
        return requiredGU;
    }
    
    //returns a boolean indicating if the Plantlet is wilted (true) or not (false)
    public boolean isWilted(){
        return (currentGU == WILTED_VALUE);
    }
    
    
    //Increases the Plantlet's current GUs by the argument amount.  Returns 
    //the GUs the Plantlet *actually* grew by (as GUs cannot exceed required)
    //
    //For example:
    //  Given a KupoFruit (requires 15 GUs) w/ a current GUs of 11, a call of
    //  grow (4) would increase the KupoFruit's GUs by 3 and thus return 3.
    //
    //An exception is thrown if:
    //  a negative argument is passed
    //  the Plantlet is currently wilted (wilted Plantlets cannot grow)
    protected int grow(int increasedGU){
        if (isWilted())
            throw new IllegalStateException("Error! A wilted plant cannot grow!");
        if (increasedGU < 0)
            throw new IllegalArgumentException("Error! Cannot grow a plant by a negative amount!");
        int oldGU = this.currentGU;
        this.currentGU = Math.min(requiredGU, currentGU + increasedGU);   
        return currentGU - oldGU;
    }
    
    
    
    //Returns a String representation of the Plantlet.
    //The String will include the Plantlet's ID and a percentage (rounded down) 
    //indicating how fully grown it is (its current GU out of its required GU).
    //The String returned ***MUST ALWAYS BE EXACTLY 6 CHARACTERS LONG!***
    //
    //For Example: 
    //  a GrubGrubRoot at 6 GU would return (w/out the quotes): "G  60%"
    public String toString(){
        if (this.getGU() != WILTED_VALUE){
            int growthPercent = (this.getGU() * 100) / this.getRequiredGU(); //percentage of growth
            if (String.valueOf(growthPercent).length() == 1){
                return this.id + "   " + growthPercent + "%";
            } else if (String.valueOf(growthPercent).length() == 2) {
                return this.id + "  " + growthPercent + "%";
            } else { //100%
                return this.id + " " + growthPercent + "%";
            }
            
        } else {
            return this.id + " " + WILTED_STR;
        }
        
    }    
    
    
    //Simulates one day's growth, determines how much (if at all)
    //the Plantlet grows by and/or if the Plantlet wilts.
    //Updates the Plantlet's state accordingly.
    //Accepts the day's temperature and weather conditions as arguments
    //
    //Returns:
    //  the number of GUs the Plantlet grew by, if it grew, or
    //  -1 if the Plantlet wilted on this day, or    
    //  0 if the Plantlet didn't grow or was already wilted    
    public abstract int simOneDayGrowth(int temp, int weather);
    
}
