
package samuraiboardgame;

import java.awt.Color;
import java.awt.Graphics2D;

public class Hex{
    
enum hexType{none}
enum cityType1{none, rice, buddah, helmet}
enum cityType2{none, rice, buddah, helmet}
enum cityType3{none, rice, buddah, helmet}

hexType hexT;
cityType1 type1;
cityType2 type2;
cityType3 type3;

        public static Color sea = new Color(37,98,211);
        public static Color land = new Color(204, 153, 0);
        public static Color ronin = Color.YELLOW;
        public static Color none = new Color(0, 0, 153);
        public static Color city = new Color(127, 127, 127);
        public static Color rice = new Color(244,244,244);
        public static Color helmet = new Color(77,22,4);
        public static Color village = new Color(139,69,19);
        public static Color cityColor = new Color(119,136,153);



Hex()
{
    hexT = hexType.none;
       
}


public void setType(hexType _type)
  {
      hexT = _type;
      
  }
 public void setHexType(hexType _type)
 {
     hexT = _type;
 }
 public  hexType getHexType()
 {
     return(hexT);
 }
 
 
 public Color getColor()
 {
     return(none);
 }
  
  
  
  
  
  
  
  
  
}
