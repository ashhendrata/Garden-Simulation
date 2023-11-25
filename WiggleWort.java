public class WiggleWort extends Plantlet implements AnimalEdible{

    private static final int REQ_GU = 20;
    private static final char ID = 'W';
    private int maxRandomGU;

    private static int hotTempCount = 0;
    
    public WiggleWort(int maxRandomGU){
        super(REQ_GU, ID);
        if (maxRandomGU < 5){
            this.maxRandomGU = 5; //The max GU has a minimum value of 5
        } else {
            this.maxRandomGU = maxRandomGU;
        }
    }

    public int simOneDayGrowth(int temp, int weather){  
        int randGU = rand.nextInt(maxRandomGU + 1); //Each day, the Plantlet grows a random number of GU between 0 and this maxRandomGU (inclusive on both bounds).
        if (temp == FantasyGarden.TEMP_CHILLY && weather == FantasyGarden.WEATHER_CLOUDY){
            randGU *= 3; //On a day that is both chilly and cloudy, the randomly generated GU grown that day triples
        }
        if (temp == FantasyGarden.TEMP_CHILLY && weather != FantasyGarden.WEATHER_CLOUDY){
            randGU *= 2; //On a day that is chilly but not cloudy, the randomly generated GU grown that day doubles
        }
        if (temp == FantasyGarden.TEMP_HOT){
            hotTempCount++; //helps keep track of num of times plant has been exposed to hot temp
        }
        if (hotTempCount == 3){ //Wilts if it has been exposed to hot temperature three times
            wilt();
            return WILTED_VALUE;
        }
        return grow(randGU);
    }

    //if a WiggleWort has never been exposed to hot temperature, its eat chance is 3%
    //if a WiggleWort has been exposed to hot temperature once, its eat chance is 15%
    //if a WiggleWort has been exposed to hot temperature twice, its eat chance is 30%
    public double getEatChance(){
        if (!isWilted()){
            if (hotTempCount == 0){
                return 0.03;
            } 
            if (hotTempCount == 1){
                return 0.15;
            } 
            if (hotTempCount == 2) {
                return 0.30;
            } 
        }
        return 0.0;
    }


}
