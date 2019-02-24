/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samuraiboardgame;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author 152003235
 */
public class Piece extends Hex{
    enum pieceType{none,rice , buddah , helmet, samurai , ronin, ship, tileExchange, figureExchange}
    pieceType pieceT;
    int value;
    int player;
    
    //Color
    Color color;
    
    final int RICE = 1;
    final int HELMET = 2;
    final int BUDDAH = 3;
    int pType = 0;
    //int[] playerInfluence = new int[4];
   
    Color newCol = new Color(244,219,159);
    Piece(pieceType _piece)
    {
        pieceT = _piece;
        if(pieceT == pieceType.rice)
            pType = 1;
        if(pieceT == pieceType.helmet)
            pType = 2;
        if(pieceT == pieceType.buddah)
            pType = 3;
        value = 0;
        player = 0;
    }
    Piece(pieceType _piece, int _val, int _player)
    {
        pieceT = _piece;
        if(pieceT == pieceType.rice)
            pType = 1;
        if(pieceT == pieceType.helmet)
            pType = 2;
        if(pieceT == pieceType.buddah)
            pType = 3;
        value = _val;
        player = _player;
    }
    Piece(pieceType _piece, int _val)
    {
        pieceT = _piece;
        if(pieceT == pieceType.rice)
            pType = 1;
        if(pieceT == pieceType.helmet)
            pType = 2;
        if(pieceT == pieceType.buddah)
            pType = 3;
        value = _val;
        player = 0;
    }
    //ADDED A CONSTUCTER WITH JUST A PeICE !!!!! BOOOOOOOM
    Piece(Piece _piece)
    {
        if(_piece.getPlayer()== 1)
           color = Color.red;
         if(_piece.getPlayer()== 2)
           color = Color.blue;
          if(_piece.getPlayer()== 3)
           color = Color.green;
           if(_piece.getPlayer()== 4)
           color = Color.CYAN;
           pieceT = _piece.pieceT;
           if(pieceT == pieceType.rice)
            pType = 1;
        if(pieceT == pieceType.helmet)
            pType = 2;
        if(pieceT == pieceType.buddah)
            pType = 3;
           value = _piece.value;
           player = _piece.getPlayer();
    }
    public void setPieceType(pieceType _piece)
    {
        pieceT = _piece;
    }
    public pieceType getPieceType()
    {
        return(pieceT);
    }
    public int getPlayer()
    {
        return(player);
    }
    public int getPType()
    {
        return(pType);
    }
    public int getValue()
    {
        return(value);
    }
    public void setPlayer(int i)
    {
        player = i;
    }
    
    public Color getColor()
    {
        return(color);   
    }
    
}
