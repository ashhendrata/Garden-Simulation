//Interface implemented by Plantlings which animals will
//attempt to eat from your Fantasy Garden

public interface AnimalEdible{
   
   //Determines if this plant gets eaten on a particular day
   //returns a random double representing the respective Plantlet's
   //odds of being eaten, as a decimal percentage
   //
   //For example, if on a particular day this Plantling has a 
   //25% chance of being eaten, this function would return 0.25
   public double getEatChance();
   

   
}