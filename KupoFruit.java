public class KupoFruit extends Plantlet{

    private static final int REQ_GU = 15;
    private static final char ID = 'K';
    private static int previousGrowth = -1;
    private static int previousTemp = -1;

    public KupoFruit(){
        super(REQ_GU, ID);
    }

    public int simOneDayGrowth(int temp, int weather){  
        if (isWilted()){
            return 0; //the Plantlet was already wilted (no growth)
        }

        if (temp == FantasyGarden.TEMP_CHILLY && previousTemp == FantasyGarden.TEMP_CHILLY){
            wilt();
            return WILTED_VALUE; //Wilts if it is exposed to two days in a row of chilly temperature
        }
        int oneDayGrowth;
        if (this.getGU() == 0){ //1st day (no growth yet)
            oneDayGrowth = 2; //On the first day its planted, it grows by 2 GU
            previousGrowth = 2;
        } else { //after 1st day
            if (weather == FantasyGarden.WEATHER_CLOUDY){
                oneDayGrowth = 2; //On a day that is cloudy, it grows by 2 GU
                previousGrowth = 2;
            } else {
                oneDayGrowth = previousGrowth + 1; //Otherwise, grows by however many GU it grew the previous day + 1
            }
        }
        previousTemp = temp; //helps keep track of previous temp: Wilts if it is exposed to two days in a row of chilly temperature
        return grow(oneDayGrowth);

    }

    public double getEatChance() {
        if (!isWilted()){ //Animals will try to eat this Plantlet if it is not wilted. A non-wilted KupoFruit always has a 15% eat chance
            return 0.15;
        }
        return 0.0;
    }

}
