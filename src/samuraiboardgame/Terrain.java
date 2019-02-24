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
public class Terrain extends Hex{
    enum terrainType{land , sea }
    terrainType type;
    Terrain()
    {
        type = terrainType.land;
    }
    Terrain( terrainType _type)
    {
        type = _type;

    }
    public void setTerrainType(terrainType _type)
    {
        type = _type;
    }
    public terrainType getTerrainType()
    {
        return(type);
    }
    public Color getColor()
    {

        if (type == terrainType.land)
                    return(land);       
        if (type == terrainType.sea)
                    return(sea);

        return(Color.WHITE);
    }
    
}