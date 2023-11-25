import java.util.ArrayList;

public class CroakOak extends Plantlet{

    private static final int REQ_GU = 4;
    private static final char ID = 'C';
    private static ArrayList<String> previousConditions = new ArrayList<>();

    public CroakOak(){
        super(REQ_GU, ID); //calls Plantlet's constructor
    }

    public int simOneDayGrowth(int temp, int weather){
        if (isWilted()){
            return 0; //the Plantlet was already wilted (no growth)
        }
        if (this.getGU() < REQ_GU){ //not fully grown: a CroakOak cannot wilt once fully grown 
            String currConditions = String.valueOf(temp) + String.valueOf(weather);
            if (previousConditions.contains(currConditions)){ //in ArrayList (temp and weather combination has occured before)
                wilt();
                return WILTED_VALUE; //Wilts if exposed to the same combination of temp and weather twice before being fully grown
            }
            previousConditions.add(currConditions); //adds to the ArrayList previousConditions if the temp and weather combination has not occured before
        }
        return grow(1); //Always grows by 1 GU each day, regardless of temperature/weather conditions
    }

}
