/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samuraiboardgame;
import java.awt.Color;

/**
 *
 * @author 152003235
 */


public class City extends Hex {

    
    enum cityType{village, city, capital}
    int[] cityResource = new int[3];
    cityType cityT;
    final int RICE = 1;
    final int HELMET = 2;
    final int BUDDAH = 3;
    final int RICEHELM = 4;
    final int RICEBUDDAH = 5;
    final int HELMBUDDAH = 6;
    final int ALL = 7;
    static int numTotalRice = 0;
    static int numTotalHelm = 0;
    static int numTotalBuddah = 0;
    Color color;
    //public boolean surrounded;
    public boolean captured;
    int[] cappedBy = new int[3];
    boolean capped;
    
    int numCityResources;
    
    City(cityType _city)
    {
        cityT=_city;
        if(cityT == cityType.village)
            numCityResources =1;
        if(cityT == cityType.city)
            numCityResources =2;
        if(cityT == cityType.capital)
        {
            numCityResources =3;
  
        }
        //surrounded = false;
        captured = false;
        //cappedBy = 0;
    }
    City(cityType _city, int one)
    {
        cityT=_city;
        if(cityT == cityType.city)
            numCityResources =2;
        cityResource[0] = one;
        if(cityResource[0] == 1)
            SamuraiBoardGame.numRice--;
        else if(cityResource[0] == 2)
            SamuraiBoardGame.numHelm -- ;
        else
            SamuraiBoardGame.numBuddah--;
        
        
        //surrounded = false;
        captured = false;
        //cappedBy = 0;
    }
    City(cityType _city, int one, int two)
    {
        cityT=_city;
        if(cityT == cityType.city)
            numCityResources =2;
        cityResource[0] = one;
        cityResource[1] = two;
        
        //surrounded = false;
        captured = false;
        //cappedBy = 0;
    }
   
    public int getResourceType(int i)
    {
        return(cityResource[i]);
    }
    public void setCityType(cityType _typeofcity)
    {
    cityT = _typeofcity;
    }
    public void setCap()
    {
        cityResource[0] = 0;
        cityResource[1] = 0;
        cityResource[2] = 0;
    }
    public void setResource(int i, int b)
    {
        cityResource[0] = i;
        cityResource[1] = b;
        
//        if(cityT == cityType.city)
//        {
//            cityResource[0] = (int)(Math.random()*3)+1;
//            
//            while(cityResource[0] == 1 && SamuraiBoardGame.numRice == 0)
//               cityResource[0] = (int)(Math.random()*3)+1;
//           while(cityResource[0] == 2 && SamuraiBoardGame.numHelm == 0)
//               cityResource[0] = 3;
//            
//            if(cityResource[0] == 1)
//                SamuraiBoardGame.numRice--;
//           else if(cityResource[0] == 2)
//                SamuraiBoardGame.numHelm--;
//           else
//               SamuraiBoardGame.numBuddah--;
//            
//            cityResource[1] = (int)(Math.random()*3)+1;
//            
//            
//            while(cityResource[1]==cityResource[0])
//            {
//                cityResource[1] = (int)(Math.random()*3)+1;
//                
//                while(cityResource[1] == 1 && SamuraiBoardGame.numRice == 0)
//                cityResource[1] = (int)(Math.random()*3)+1;
//                while(cityResource[1] == 2 && SamuraiBoardGame.numHelm ==0)
//                cityResource[1] = (int)(Math.random()*3)+1;
//                while(cityResource[1] == 3 && SamuraiBoardGame.numBuddah ==0)
//                cityResource[1] = (int)(Math.random()*3)+1;
//                
//                
//                
//            }
//            
//            
//            if(cityResource[0] != cityResource[1])
//            {
//                if(cityResource[1] == 1)
//                SamuraiBoardGame.numRice--;
//                else if(cityResource[1] == 2)
//                     SamuraiBoardGame.numHelm--;
//                else
//                    SamuraiBoardGame.numBuddah--;
//            }
//        }
//          if(cityT == cityType.capital)
//        {
//            cityResource[0] = 1;
//            cityResource[1] = 2;
//            cityResource[2] = 3;
//            //SamuraiBoardGame.numRice--;
//            //SamuraiBoardGame.numHelm--;
//            //SamuraiBoardGame.numBuddah--;
//            
//        }
    }
    public void setResource(int i)
    {
        cityResource[0] = i;
    }
    public void setResource()
    {
        cityResource[0] = 1;
        cityResource[1] = 2;
        cityResource[2] = 3;
    }
    public cityType getCityType()
    {
        return(cityT);
    }
    public void setCity(int _city, int i )
    {
        cityResource[i] = _city;
    }
    public int getResource(int i)
    {
        return(cityResource[i]);
    }
    public Color getColor()
    {
        if(cityT == cityType.village)
             return(village);
         
         if(cityT == cityType.city)
             return(cityColor);
         
         if(cityT == cityType.capital)
             return(Color.YELLOW);
           return(city);
    }
    
}
