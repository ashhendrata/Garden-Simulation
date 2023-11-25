public class SnarfBerry extends Plantlet implements AnimalEdible {

//Has a character ID of ’S’
//Requires 5 GU to be fully grown
// grows by 1 GU each day, unless it is rainy and/or chilly (in which case it doesn’t grow at all)
//Never wilts
//Animals will try to eat this Plantlet. Specifically, on a given day:
//a SnarfBerry is not fully grown there is a 5% chance it is eaten – a SnarfBerry is fully grown there is a 10% chance it is eaten


    private static final int REQ_GU = 5;
    private static final char ID = 'S';

    public SnarfBerry(){
        super(REQ_GU, ID);
    }


    public int simOneDayGrowth(int temp, int weather){ 
        //never wilts so never returns -1

        if (temp != FantasyGarden.TEMP_CHILLY || weather != FantasyGarden.WEATHER_RAINY){ //grows if not rainy and/or not chilly
            return grow(1); //grows by 1 GU each day
        }
        return grow(0); //Does not grow otherwise
 
    }
    
    //if a SnarfBerry is not fully grown there is a 5% chance it is eaten 
    //if a SnarfBerry is fully grown there is a 10% chance it is eaten
    public double getEatChance() {
        if (this.getGU() == REQ_GU){
            return 0.10;
        } else {
            return 0.05;
        }
    }


}
