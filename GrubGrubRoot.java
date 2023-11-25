//Representation of a GrubGrubRoot Plantlet
//GrubGrubRoots like heat and rain, but dislikes the cold!
//Animals don't like GrubGrubRoots and won't try to eat them

//A GrubGrubRoot *IS-A* Plantlet
public class GrubGrubRoot extends Plantlet{  
    
    //Requires 10 GUs to be fully grown
    private static final int REQ_GU = 10;
    //GrubGrubRoot's character identifier
    private static final char ID = 'G';
    


    public GrubGrubRoot(){
        super(REQ_GU, ID);
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
    //  Reminder: plants will grow up to BUT NOT OVER their required GU    
    public int simOneDayGrowth(int temp, int weather){  
        if (isWilted()){
            return 0; //the Plantlet was already wilted (no growth)
        }
        if (temp == FantasyGarden.TEMP_CHILLY && weather != FantasyGarden.WEATHER_RAINY){ //Wilts if it is exposed to a day that is chilly and not rainy
            wilt();
            return WILTED_VALUE; //the Plantlet wilted on this day
        } 
        
        int oneDayGrowth;
        if (temp == FantasyGarden.TEMP_HOT){ //hot
            if (weather == FantasyGarden.WEATHER_RAINY){ //hot and rainy
                oneDayGrowth = 4;
            } else { //hot but not rainy
                oneDayGrowth = 2;
            }
        } else {
            oneDayGrowth = 1;
        }
        return grow(oneDayGrowth);
    }
    
    
    
    
}
