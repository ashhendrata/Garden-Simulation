import java.util.Random;
//An abstraction of your fantasy garden
//Stores a collection of Plantlets via a 2D array
public class FantasyGarden{
    
    
    
    
    //ints for the different temperature possibilities on a given day
    public static final int TEMP_CHILLY = 0;
    public static final int TEMP_WARM = 1;
    public static final int TEMP_HOT = 2;
    //string descriptions of each temperature type
    public static final String[] TEMP_STR = {"chilly", "warm", "hot"};
    
    //ints for the different weather possibilities on a given day
    public static final int WEATHER_SUNNY = 0;
    public static final int WEATHER_CLOUDY = 1;
    public static final int WEATHER_RAINY = 2;
    //string descriptions of each weather type
    public static final String[] WEATHER_STR = {"sunny", "cloudy", "rainy"};    
    
    //Used by Garden's toString to represent a plot with no Plantlet in it
    private static final String EMPTY_STR = "[      ] ";
    
    //An array representing the contents of your garden. Each index of this
    // array (row, col) represents one "plot" where a Plantlet can be planted.
    private Plantlet[][] garden;
            
    
    
    
    
   private int plantedCT;    
    
   //Accepts a row and col argument, representing the dimensions of the
   // garden (the garden will have rows * cols "plots" for Plantlets)
   public FantasyGarden(int rows, int cols){       
      this.garden = new Plantlet[rows][cols];
      this.plantedCT = 0;
   }
    
    
    
   //Attempts to "plant" the argument Plantlet in the garden by adding it
   //to the next available plot in the garden array.
   //The garden is populated column by column, row by row. (i.e. first Plantlet
   //is planted in row=0, col=0, second is planted in row=0, col=1, and so on...
   //
   //Returns a boolean indicating if the Plantlet is succesfully planted or not. 
   public boolean addPlantlet(Plantlet toPlant){
      int totalPlots = garden.length * garden[0].length;
      if (plantedCT < totalPlots) {
         int row = plantedCT / garden[0].length;
         int column = plantedCT % garden[0].length;
         garden[row][column] = toPlant; //plants in next available plot
         plantedCT++; //keeps track of number of plants planted
         return true;
      } else {
         return false;
      }
  }  
  
  
  
  
  
    
   //Simulates the argument number of days in the garden.
   //Each day, a description of that days' progression is printed
   //along with a String representation of the garden's state as of
   //the end of that day.   
   public void simulateDays(int numOfDays){
       
      for (int day = 1; day <= numOfDays; day++){
         int todaysWeather = Plantlet.rand.nextInt(WEATHER_STR.length);
         int todaysTemp = Plantlet.rand.nextInt(TEMP_STR.length);
         System.out.println("*** DAY " + day + " ***");
         System.out.println("The temp is: " + TEMP_STR[todaysTemp] + ", the weather is: " + WEATHER_STR[todaysWeather]);
         growPlants(todaysTemp, todaysWeather); //first, take care of any growing/wilting
         handleAnimals(); //then, take care of any animal eating
         printGarden(day); //and finally, print the garden
      } 
   }
    
    
    
    
    
       
   //Helper function to handle the progression of Plantlets each day
   private void growPlants(int temp, int weather){
      for (int row = 0; row < garden.length; row++) {
         for (int column = 0; column < garden[0].length; column++){ //iterates through grid of plots
            if (garden[row][column] != null){
               Plantlet currPlantlet = garden[row][column];
               System.out.print("Plantlet at row: " + row + ", col: " + column);
               if (currPlantlet.isWilted()){
                  System.out.println(" is wilted!"); //was wilted
               } else {
                  int todaysGrowth = currPlantlet.simOneDayGrowth(temp, weather);
                  if (currPlantlet.isWilted()){
                     System.out.println(" wilts!"); //wilts during this day
                  } else {
                     System.out.println(" grew by: " + todaysGrowth + " GU!"); //0 or more
                  }
               }
            }
         }
      }
      
   }
    
    
    
    
    
    
   //Helper function to handle any Plantlets getting eaten by animals each day
   private void handleAnimals(){ 
      for (int row = 0; row < garden.length; row++) {
         for (int col = 0; col < garden[0].length; col++) { //iterates through grid of plots
            Plantlet currPlantlet = garden[row][col];
            if (currPlantlet != null && currPlantlet instanceof AnimalEdible) {
               double eatenProbability = Math.random(); //generates random double
               AnimalEdible ediblePlantlet = (AnimalEdible) currPlantlet;
               if (eatenProbability < ediblePlantlet.getEatChance()) { //based on probabilities, the plant might get eated
                  System.out.println("Animals ate the plantlet at row: " + row + ", col: " + col + "!");
                  garden[row][col] = null; //removed if eated
               }
            }
         }
      }
   }    
    
    
    
    //Helper function to handle printing the state of the garden
   private void printGarden(int day){       
      System.out.println("\nGarden state at end of day " + day + ":\n");
      System.out.println(this);
   }        
    
    
    
    
    
    
   //Returns a String representation of the garden, printed col by col,
   //row by row in a matrix. ***Do not modify this method***
   public String toString(){
       
      String toReturn = "";
      for (int row = 0; row < garden.length; row++){
         for (int col = 0; col < garden[0].length; col++){
            if (garden[row][col] != null)
               toReturn += "[" + garden[row][col].toString() + "] ";
            else
               toReturn += EMPTY_STR;
         }
         toReturn += "\n";
      }              
      return toReturn;
   }
    
    
    
   //Accessor method for the 2D garden array
   public Plantlet[][] getGarden(){
      return garden;
   }
    
    
    
}
