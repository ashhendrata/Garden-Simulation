import java.util.Random;

public class GardenClient{
  
  
  
  public static void main(String[] args){
    
    
    FantasyGarden fg = new FantasyGarden(3, 4); 
    fg.addPlantlet(new GrubGrubRoot()); 
    fg.addPlantlet(new WiggleWort(12)); 
    fg.addPlantlet(new SnarfBerry());
    fg.addPlantlet(new KupoFruit());   
    fg.addPlantlet(new CroakOak());  

    fg.addPlantlet(new GrubGrubRoot()); 
    fg.addPlantlet(new WiggleWort(3)); 
    fg.addPlantlet(new WiggleWort(5)); 
    fg.addPlantlet(new KupoFruit());   
    fg.addPlantlet(new CroakOak()); 

    /*
    System.out.println(fg.addPlantlet(new GrubGrubRoot())); //true
    System.out.println(fg.addPlantlet(new GrubGrubRoot())); //true 
    System.out.println(fg.addPlantlet(new GrubGrubRoot())); //false
    System.out.println(fg.addPlantlet(new GrubGrubRoot())); //false
    System.out.println(fg.addPlantlet(new GrubGrubRoot())); //false
    System.out.println(fg.addPlantlet(new GrubGrubRoot())); //false
    System.out.println(fg.addPlantlet(new GrubGrubRoot())); //false
    System.out.println(fg.addPlantlet(new GrubGrubRoot())); //false
    System.out.println(fg.addPlantlet(new GrubGrubRoot())); //false
   */
    fg.simulateDays(10);

      

  }
   
   
   
}
