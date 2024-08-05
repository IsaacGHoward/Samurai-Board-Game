
//updated 11/10/2015
package samuraiboardgame;

import Sound.sound;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

/**********************************
 * This is the main class of a Java program to play a game based on hexagonal
 * tiles.
 * The mechanism of handling hexes is in the file hexmech.java.
 * 
 * Written by: M.H.
 * Date: December 2012
 * 
 ***********************************/

public class SamuraiBoardGame {
    static int numRice;
    static int numBuddah;
    static int numHelm;

    JFrame frame = new JFrame("Samurai");

    // constants and global variables
    static Color blue = new Color(109, 197, 232);

    final static Color COLOURGRID = Color.BLACK;
    final static Color COLOURONE = new Color(255, 255, 255, 200);
    final static Color COLOURONETXT = Color.BLUE;
    final static Color COLOURTWO = new Color(0, 0, 0, 200);
    final static Color COLOURTWOTXT = new Color(255, 100, 255);

    final static int BWIDTH = 100; // board size.
    final static int BHEIGHT = 25;
    final static int HEXSIZE = 35; // hex size in pixels
    final static int BORDERS = -10;
    final static int SCRSIZE = HEXSIZE * (BHEIGHT + 5) + BORDERS * 3; // screen size (vertical dimension).

    final static int menuScreenSize = 40 * (25 + 1) + -10 * 3;

    JButton button = new JButton("Click Me!");

    int widen = 2;
    boolean help = false;
    boolean menu;
    boolean first = true;
    boolean startScreen = true;

    sound bgSound = null;

    int boardshift = 14;
    int boardshiftdown = 0;

    static Image Background;
    static Image title;
    static Image rice;
    static Image helmet;
    static Image buddah;
    static Image ronin;
    static Image ship;
    static Image samurai;
    static Image swap;
    static Image hand;

    Piece chosen = new Piece(Piece.pieceType.none, 0);
    int lastChosen;
    boolean gameOver = false;
    int cities;

    // player variables
    int numPiecesPerPlayer = 5;
    int numPlayers = 0;
    int playerTurn;
    int numTies = 0;

    Piece[] player1 = new Piece[numPiecesPerPlayer];
    Piece[] player2 = new Piece[numPiecesPerPlayer];
    Piece[] player3 = new Piece[numPiecesPerPlayer];
    Piece[] player4 = new Piece[numPiecesPerPlayer];

    boolean[] player1Used = new boolean[20];
    boolean[] player2Used = new boolean[20];
    boolean[] player3Used = new boolean[20];
    boolean[] player4Used = new boolean[20];
    int[] player1points = new int[3];
    int[] player2points = new int[3];
    int[] player3points = new int[3];
    int[] player4points = new int[3];
    int numPiecesPlayer1 = 20;
    int numPiecesPlayer2 = 20;
    int numPiecesPlayer3 = 20;
    int numPiecesPlayer4 = 20;

    Piece[] pieces = new Piece[20];

    Hex[][] board = new Hex[BWIDTH][BHEIGHT];

    private SamuraiBoardGame() {
        initGame();
        createAndShowGUI();
        // java.awt.event.MouseMotionAdapter m = new MouseMotionListener();

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SamuraiBoardGame();
            }
        });
    }

    Piece randomPiecesForPlayers(int playerNum) {
        int num = (int) (Math.random() * 20) + 1;
        if (playerNum == 1) {
            boolean i = true;
            while (i) {
                num = (int) (Math.random() * 20);

                if (player1Used[num] == false) {

                    numPiecesPlayer1--;
                    player1Used[num] = true;
                    return (pieces[num]);
                }
            }

        }
        if (playerNum == 2) {
            boolean i = true;
            while (i) {
                num = (int) (Math.random() * 20);

                if (player2Used[num] == false) {

                    numPiecesPlayer2--;
                    player2Used[num] = true;
                    return (pieces[num]);
                }
            }
        }
        if (playerNum == 3) {
            boolean i = true;
            while (i) {
                num = (int) (Math.random() * 20);
                if (player3Used[num] == false) {
                    numPiecesPlayer3--;
                    player3Used[num] = true;
                    return (pieces[num]);
                }
            }

        }
        if (playerNum == 4) {
            boolean i = true;
            while (i) {
                num = (int) (Math.random() * 20);
                if (player4Used[num] == false) {
                    numPiecesPlayer4--;
                    player4Used[num] = true;
                    return (pieces[num]);
                }
            }

        }
        return (null);

    }

    void initPiecesArray() {

        // 2 3 4 helmet
        // 2 3 4 buddha
        // 2 3 4 rice
        // 1 1 2 2 3 samurai
        // 1 1 2 ship
        // 1 ronin
        // 1 fig exchange
        // 1 tile exchance

        // helmets
        pieces[0] = new Piece(Piece.pieceType.helmet, 2);
        pieces[1] = new Piece(Piece.pieceType.helmet, 3);
        pieces[2] = new Piece(Piece.pieceType.helmet, 4);
        // buddahs
        pieces[3] = new Piece(Piece.pieceType.buddah, 2);
        pieces[4] = new Piece(Piece.pieceType.buddah, 3);
        pieces[5] = new Piece(Piece.pieceType.buddah, 3);
        // rice
        pieces[6] = new Piece(Piece.pieceType.rice, 2);
        pieces[7] = new Piece(Piece.pieceType.rice, 3);
        pieces[8] = new Piece(Piece.pieceType.rice, 4);
        // samurai
        pieces[9] = new Piece(Piece.pieceType.samurai, 1);
        pieces[10] = new Piece(Piece.pieceType.samurai, 1);
        pieces[11] = new Piece(Piece.pieceType.samurai, 2);
        pieces[12] = new Piece(Piece.pieceType.samurai, 2);
        pieces[13] = new Piece(Piece.pieceType.samurai, 3);
        // ship
        pieces[14] = new Piece(Piece.pieceType.ship, 1);
        pieces[15] = new Piece(Piece.pieceType.ship, 1);
        pieces[16] = new Piece(Piece.pieceType.ship, 2);
        pieces[17] = new Piece(Piece.pieceType.ronin, 1);
        pieces[18] = new Piece(Piece.pieceType.figureExchange, 0);
        pieces[19] = new Piece(Piece.pieceType.tileExchange, 0);

    }

    void reset() {
        if (first) {
            first = false;
            Background = Toolkit.getDefaultToolkit().getImage("./zb3BZCv.GIF");
            title = Toolkit.getDefaultToolkit().getImage("./title.jpg");
            rice = Toolkit.getDefaultToolkit().getImage("./rice.PNG");
            helmet = Toolkit.getDefaultToolkit().getImage("./helmet.PNG");
            buddah = Toolkit.getDefaultToolkit().getImage("./buddha.PNG");

            ronin = Toolkit.getDefaultToolkit().getImage("./ronin.PNG");
            ship = Toolkit.getDefaultToolkit().getImage("./ship2.PNG");
            samurai = Toolkit.getDefaultToolkit().getImage("./samurai.PNG");
            swap = Toolkit.getDefaultToolkit().getImage("./swap.PNG");
            hand = Toolkit.getDefaultToolkit().getImage("./hand.PNG");
        }

        for (int i = 0; i < BWIDTH; i++) {
            for (int j = 0; j < BHEIGHT; j++) {
                board[i][j] = new Hex();
            }
        }
        initPiecesArray();
        for (int i = 0; i < 20; i++) {
            player1Used[i] = false;
            player2Used[i] = false;
            player3Used[i] = false;
            player4Used[i] = false;
        }

        for (int j = 0; j < 5; j++) {
            if (numPlayers > 1) {
                player1[j] = randomPiecesForPlayers(1);
                player1[j].setPlayer(1);
            }
            if (numPlayers >= 2) {
                player2[j] = randomPiecesForPlayers(2);
                player2[j].setPlayer(2);
            }

            if (numPlayers >= 3) {
                player3[j] = randomPiecesForPlayers(3);
                player3[j].setPlayer(3);
            }

            if (numPlayers >= 4) {
                player4[j] = randomPiecesForPlayers(4);
                player4[j].setPlayer(4);
            }
        }

        playerTurn = 1;

        button.setSize(150, 75);
        button.setVisible(true);

        //
        if (!startScreen)
            Island();
    }

    void startGame() {
        if (numPlayers == 2) {
            numRice = 5;
            numHelm = 4;
            numBuddah = 5;
        } else if (numPlayers == 3) {
            numRice = 8;
            numHelm = 7;
            numBuddah = 8;
        } else if (numPlayers == 4) {
            numRice = 10;
            numHelm = 10;
            numBuddah = 10;
        }

        for (int i = 0; i < BWIDTH; i++) {
            for (int b = 0; b < BHEIGHT; b++) {
                if (board[i][b] instanceof City) {

                    City city = (City) (board[i][b]);
                    if (city.getCityType() == City.cityType.village) {
                        city.setResource((int) (Math.random() * 3) + 1);
                        while (city.getResource(0) == 1 && numRice == 0) {
                            System.out.println("CHECK1");
                            city.setResource((int) (Math.random() * 3) + 1);
                        }
                        while (city.getResource(0) == 2 && numHelm == 0) {
                            System.out.println("CHECK2");
                            city.setResource((int) (Math.random() * 3) + 1);
                        }
                        while (city.getResource(0) == 3 && numBuddah == 0) {
                            System.out.println("CHECK3");
                            city.setResource((int) (Math.random() * 3) + 1);
                        }
                        if (city.getResource(0) == 1)
                            numRice--;
                        else if (city.getResource(0) == 2)
                            numHelm--;
                        else
                            numBuddah--;
                    } else if (city.getCityType() == City.cityType.capital) {
                        city.setResource();
                    }

                }
            }
        }
        //// if(numRice != 0)
        //// {
        //// if(numPlayers == 2)
        //// {
        //// numRice = 6;
        //// numHelm = 6;
        //// numBuddah = 6;
        //// }
        //// city.setResource();
        //// }
        //// else if(numHelm != 0)
        //// {
        //// if(numPlayers == 2)
        //// {
        //// numRice = 6;
        //// numHelm = 6;
        //// numBuddah = 6;
        //// }
        //// city.setResource();
        //// }
        //// else if(numBuddah != 0)
        //// {
        //// if(numPlayers == 2)
        //// {
        //// numRice = 6;
        //// numHelm = 6;
        //// numBuddah = 6;
        //// }
        //// city.setResource();
        //// }
        //
        //
        // }
        // }
        // }

    }

    int rand = (int) (Math.random() * 3) + 1;

    void Island() {
        if (numPlayers > 1) {
            board[7 + boardshift][17] = new Terrain();

            board[7 + boardshift][18] = new City(City.cityType.village);

            board[8 + boardshift][18] = new Terrain();

            board[8 + boardshift][17] = new City(City.cityType.village);

            board[9 + boardshift][17] = new Terrain();
            board[9 + boardshift][16] = new Terrain();
            board[10 + boardshift][16] = new Terrain();

            board[10 + boardshift][17] = new City(City.cityType.village);

            board[11 + boardshift][16] = new Terrain();

            board[11 + boardshift][15] = new City(City.cityType.village);

            board[12 + boardshift][16] = new Terrain();
            board[12 + boardshift][17] = new Terrain();

            board[12 + boardshift][18] = new City(City.cityType.village);

            board[13 + boardshift][17] = new Terrain();

            board[13 + boardshift][16] = new City(City.cityType.city, 1, 2);

            board[13 + boardshift][15] = new Terrain();

            board[13 + boardshift][14] = new City(City.cityType.village);

            board[13 + boardshift][13] = new Terrain();
            board[14 + boardshift][16] = new Terrain();
            board[14 + boardshift][15] = new Terrain();
            board[14 + boardshift][14] = new Terrain();

            board[14 + boardshift][13] = new City(City.cityType.village);

            board[15 + boardshift][15] = new City(City.cityType.village);

            board[15 + boardshift][14] = new Terrain();
            board[15 + boardshift][13] = new Terrain();
            board[15 + boardshift][12] = new Terrain();
            board[16 + boardshift][15] = new Terrain();
            board[16 + boardshift][14] = new Terrain();
            board[16 + boardshift][13] = new Terrain();

            board[16 + boardshift][12] = new City(City.cityType.village);

            board[16 + boardshift][11] = new Terrain();

            board[16 + boardshift][10] = new City(City.cityType.city, 2, 3);

            board[16 + boardshift][9] = new Terrain();

            board[16 + boardshift][8] = new City(City.cityType.village);

            board[17 + boardshift][14] = new Terrain();
            board[17 + boardshift][13] = new Terrain();
            board[17 + boardshift][12] = new Terrain();
            board[17 + boardshift][11] = new Terrain();
            board[17 + boardshift][10] = new Terrain();
            board[17 + boardshift][9] = new Terrain();
            board[17 + boardshift][8] = new Terrain();

            board[18 + boardshift][15] = new City(City.cityType.village);

            board[18 + boardshift][14] = new Terrain();

            board[18 + boardshift][13] = new City(City.cityType.village);

            board[18 + boardshift][11] = new City(City.cityType.village);

            board[18 + boardshift][10] = new Terrain();

            board[18 + boardshift][9] = new City(City.cityType.village);

            board[16 + boardshift][14] = new City(City.cityType.capital);
        }
        // Top Island
        if (numPlayers == 4) {
            board[15 + boardshift][6] = new City(City.cityType.village);

            board[15 + boardshift][5] = new Terrain();

            board[15 + boardshift][4] = new City(City.cityType.village);

            board[16 + boardshift][6] = new Terrain();
            board[16 + boardshift][5] = new Terrain();
            board[16 + boardshift][4] = new Terrain();
            board[16 + boardshift][3] = new Terrain();

            board[16 + boardshift][2] = new City(City.cityType.village);

            board[17 + boardshift][6] = new Terrain();

            board[17 + boardshift][5] = new City(City.cityType.city, 1, 3);

            board[17 + boardshift][4] = new Terrain();

            board[17 + boardshift][3] = new City(City.cityType.village);

            board[17 + boardshift][2] = new Terrain();

            board[18 + boardshift][7] = new City(City.cityType.village);

            board[18 + boardshift][6] = new Terrain();
            board[18 + boardshift][5] = new Terrain();
            board[18 + boardshift][4] = new Terrain();

            board[19 + boardshift][4] = new City(City.cityType.village);

            board[19 + boardshift][3] = new Terrain();
            board[20 + boardshift][4] = new Terrain();

            board[20 + boardshift][3] = new City(City.cityType.village);
        }
        // Bottom Islands
        if (numPlayers == 3 || numPlayers == 4) {

            board[3 + boardshift][20] = new City(City.cityType.village);

            board[4 + boardshift][22] = new City(City.cityType.village);

            board[4 + boardshift][21] = new Terrain();
            board[4 + boardshift][20] = new Terrain();
            board[5 + boardshift][22] = new Terrain();
            board[5 + boardshift][21] = new Terrain();
            board[5 + boardshift][20] = new Terrain();

            board[5 + boardshift][19] = new City(City.cityType.village);

            board[6 + boardshift][23] = new City(City.cityType.village);

            board[6 + boardshift][22] = new Terrain();

            board[6 + boardshift][21] = new City(City.cityType.village);

            board[6 + boardshift][20] = new Terrain();

            board[8 + boardshift][20] = new City(City.cityType.village);

            board[8 + boardshift][21] = new Terrain();
            board[9 + boardshift][19] = new Terrain();
            board[9 + boardshift][20] = new Terrain();

            board[9 + boardshift][21] = new City(City.cityType.village);

            board[10 + boardshift][19] = new City(City.cityType.village);

            board[10 + boardshift][20] = new Terrain();
            board[10 + boardshift][21] = new Terrain();

            board[11 + boardshift][20] = new City(City.cityType.village);

            board[11 + boardshift][19] = new Terrain();
        }
        // bottomIslandSea

        if (numPlayers == 3 || numPlayers == 4) {
            board[2 + boardshift][21] = new Terrain(Terrain.terrainType.sea);
            board[3 + boardshift][21] = new Terrain(Terrain.terrainType.sea);
            board[3 + boardshift][22] = new Terrain(Terrain.terrainType.sea);
            board[4 + boardshift][23] = new Terrain(Terrain.terrainType.sea);
            board[5 + boardshift][23] = new Terrain(Terrain.terrainType.sea);
            board[6 + boardshift][24] = new Terrain(Terrain.terrainType.sea);
            board[7 + boardshift][23] = new Terrain(Terrain.terrainType.sea);
            board[7 + boardshift][22] = new Terrain(Terrain.terrainType.sea);
            board[7 + boardshift][21] = new Terrain(Terrain.terrainType.sea);
            board[7 + boardshift][20] = new Terrain(Terrain.terrainType.sea);
            // board[7][19] = new Terrain(Terrain.terrainType.sea);
            // board[6][19] = new Terrain(Terrain.terrainType.sea);
            // board[6][18] = new Terrain(Terrain.terrainType.sea);
            board[5 + boardshift][18] = new Terrain(Terrain.terrainType.sea);
            board[4 + boardshift][19] = new Terrain(Terrain.terrainType.sea);
            board[2 + boardshift][21] = new Terrain(Terrain.terrainType.sea);
            board[3 + boardshift][19] = new Terrain(Terrain.terrainType.sea);
            board[2 + boardshift][20] = new Terrain(Terrain.terrainType.sea);
            // board[8][19] = new Terrain(Terrain.terrainType.sea);
            // board[9][18] = new Terrain(Terrain.terrainType.sea);
            // board[10][18] = new Terrain(Terrain.terrainType.sea);
            // board[11][17] = new Terrain(Terrain.terrainType.sea);
            // board[11][18] = new Terrain(Terrain.terrainType.sea);
            // board[12][19] = new Terrain(Terrain.terrainType.sea);
            board[12 + boardshift][20] = new Terrain(Terrain.terrainType.sea);
            board[12 + boardshift][21] = new Terrain(Terrain.terrainType.sea);
            board[11 + boardshift][21] = new Terrain(Terrain.terrainType.sea);
            board[10 + boardshift][22] = new Terrain(Terrain.terrainType.sea);
            board[9 + boardshift][22] = new Terrain(Terrain.terrainType.sea);
            board[8 + boardshift][22] = new Terrain(Terrain.terrainType.sea);
        }
        // mainIslandSea
        if (numPlayers > 1) {
            board[6 + boardshift][17] = new Terrain(Terrain.terrainType.sea);
            board[7 + boardshift][16] = new Terrain(Terrain.terrainType.sea);
            board[8 + boardshift][16] = new Terrain(Terrain.terrainType.sea);
            board[9 + boardshift][15] = new Terrain(Terrain.terrainType.sea);
            board[10 + boardshift][15] = new Terrain(Terrain.terrainType.sea);
            board[11 + boardshift][14] = new Terrain(Terrain.terrainType.sea);
            board[12 + boardshift][15] = new Terrain(Terrain.terrainType.sea);
            board[12 + boardshift][14] = new Terrain(Terrain.terrainType.sea);
            board[12 + boardshift][13] = new Terrain(Terrain.terrainType.sea);
            board[13 + boardshift][12] = new Terrain(Terrain.terrainType.sea);
            board[14 + boardshift][12] = new Terrain(Terrain.terrainType.sea);
            board[15 + boardshift][11] = new Terrain(Terrain.terrainType.sea);
            board[15 + boardshift][10] = new Terrain(Terrain.terrainType.sea);
            board[15 + boardshift][9] = new Terrain(Terrain.terrainType.sea);
            board[15 + boardshift][8] = new Terrain(Terrain.terrainType.sea);
            board[15 + boardshift][7] = new Terrain(Terrain.terrainType.sea);
            board[16 + boardshift][7] = new Terrain(Terrain.terrainType.sea);
            board[17 + boardshift][7] = new Terrain(Terrain.terrainType.sea);
            board[15 + boardshift][7] = new Terrain(Terrain.terrainType.sea);
            board[18 + boardshift][8] = new Terrain(Terrain.terrainType.sea);
            board[19 + boardshift][8] = new Terrain(Terrain.terrainType.sea);
            board[19 + boardshift][9] = new Terrain(Terrain.terrainType.sea);
            board[19 + boardshift][10] = new Terrain(Terrain.terrainType.sea);
            board[19 + boardshift][11] = new Terrain(Terrain.terrainType.sea);
            board[18 + boardshift][12] = new Terrain(Terrain.terrainType.sea);
            board[19 + boardshift][12] = new Terrain(Terrain.terrainType.sea);
            board[19 + boardshift][13] = new Terrain(Terrain.terrainType.sea);
            board[19 + boardshift][14] = new Terrain(Terrain.terrainType.sea);
            board[19 + boardshift][15] = new Terrain(Terrain.terrainType.sea);
            board[18 + boardshift][16] = new Terrain(Terrain.terrainType.sea);
            board[17 + boardshift][15] = new Terrain(Terrain.terrainType.sea);
            board[16 + boardshift][16] = new Terrain(Terrain.terrainType.sea);
            board[15 + boardshift][16] = new Terrain(Terrain.terrainType.sea);
            board[14 + boardshift][17] = new Terrain(Terrain.terrainType.sea);
            board[14 + boardshift][18] = new Terrain(Terrain.terrainType.sea);
            board[13 + boardshift][18] = new Terrain(Terrain.terrainType.sea);
            board[11 + boardshift][18] = new Terrain(Terrain.terrainType.sea);
            board[12 + boardshift][19] = new Terrain(Terrain.terrainType.sea);
            board[9 + boardshift][18] = new Terrain(Terrain.terrainType.sea);
            board[11 + boardshift][17] = new Terrain(Terrain.terrainType.sea);
            board[10 + boardshift][18] = new Terrain(Terrain.terrainType.sea);
            board[8 + boardshift][19] = new Terrain(Terrain.terrainType.sea);
            board[6 + boardshift][18] = new Terrain(Terrain.terrainType.sea);
            board[6 + boardshift][19] = new Terrain(Terrain.terrainType.sea);
            board[7 + boardshift][19] = new Terrain(Terrain.terrainType.sea);
        }
        // topIslandSea
        if (numPlayers == 4) {

            board[14 + boardshift][7] = new Terrain(Terrain.terrainType.sea);
            board[14 + boardshift][6] = new Terrain(Terrain.terrainType.sea);
            board[14 + boardshift][5] = new Terrain(Terrain.terrainType.sea);
            board[14 + boardshift][4] = new Terrain(Terrain.terrainType.sea);
            board[15 + boardshift][3] = new Terrain(Terrain.terrainType.sea);
            board[15 + boardshift][2] = new Terrain(Terrain.terrainType.sea);
            board[15 + boardshift][1] = new Terrain(Terrain.terrainType.sea);
            board[16 + boardshift][1] = new Terrain(Terrain.terrainType.sea);
            board[17 + boardshift][1] = new Terrain(Terrain.terrainType.sea);
            board[18 + boardshift][2] = new Terrain(Terrain.terrainType.sea);
            board[18 + boardshift][3] = new Terrain(Terrain.terrainType.sea);
            board[19 + boardshift][2] = new Terrain(Terrain.terrainType.sea);
            board[20 + boardshift][2] = new Terrain(Terrain.terrainType.sea);
            board[21 + boardshift][2] = new Terrain(Terrain.terrainType.sea);
            board[21 + boardshift][3] = new Terrain(Terrain.terrainType.sea);
            board[21 + boardshift][4] = new Terrain(Terrain.terrainType.sea);
            board[20 + boardshift][5] = new Terrain(Terrain.terrainType.sea);
            board[19 + boardshift][5] = new Terrain(Terrain.terrainType.sea);
            board[19 + boardshift][6] = new Terrain(Terrain.terrainType.sea);
            board[19 + boardshift][7] = new Terrain(Terrain.terrainType.sea);
        }
        if (numPlayers == 2) {

        }
    }

    void initGame() {

        reset();

        // set up board here

    }

    private void createAndShowGUI() {
        DrawingPanel panel = new DrawingPanel();

        // JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container content = frame.getContentPane();
        content.add(panel);
        // this.add(panel); -- cannot be done in a static context
        // for hexes in the FLAT orientation, the height of a 10x10 grid is 1.1764 * the
        // width. (from h / (s+t))
        frame.setSize((int) (menuScreenSize / 1.23), menuScreenSize);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        {

        }

    }

    public void checkEnd() {
        int numTerrain = 0;
        for (int i = 0; i < BWIDTH; i++) {
            for (int b = 0; b < BHEIGHT; b++) {
                if (board[i][b] instanceof Terrain
                        && ((Terrain) board[i][b]).getTerrainType() == Terrain.terrainType.land)
                    numTerrain++;

            }
        }
        if (numTerrain == 0) {
            gameOver = true;
            System.out.println(
                    "GAMEOVERRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAARRRRRRRRRRRRRR");
        }

    }

    public void checkCapture(int x, int y) {

        City city = (City) (board[x][y]);
        int[][] playerInfluence = new int[numPlayers][city.numCityResources];
        System.out.println("RESOURCE TYPE" + city.getResourceType(0));

        if (x % 2 == 0) {
            System.out.println("EVENCODE");
            for (int d = 0; d < city.numCityResources; d++) {
                if (board[x - 1][y] instanceof Piece) {
                    System.out.println("PTYPE" + ((Piece) board[x - 1][y]).getPType());
                    System.out.println("PIECETYPE" + ((Piece) board[x - 1][y]).getPieceType());
                    if (((Piece) board[x - 1][y]).getPType() == city.getResourceType(d) ||
                            ((Piece) board[x - 1][y]).getPieceType() == Piece.pieceType.samurai ||
                            ((Piece) board[x - 1][y]).getPieceType() == Piece.pieceType.ronin ||
                            ((Piece) board[x - 1][y]).getPieceType() == Piece.pieceType.ship ||
                            ((Piece) board[x - 1][y]).getPieceType() == Piece.pieceType.tileExchange) {
                        System.out.println("EVEN1");
                        playerInfluence[((Piece) board[x - 1][y]).getPlayer() - 1][d] += ((Piece) board[x - 1][y])
                                .getValue();
                    }
                }
                if (board[x - 1][y - 1] instanceof Piece) {
                    System.out.println("PTYPE" + ((Piece) board[x - 1][y - 1]).getPType());
                    System.out.println("PIECETYPE" + ((Piece) board[x - 1][y - 1]).getPieceType());
                    if (((Piece) board[x - 1][y - 1]).getPType() == city.getResourceType(d) ||
                            ((Piece) board[x - 1][y - 1]).getPieceType() == Piece.pieceType.samurai ||
                            ((Piece) board[x - 1][y - 1]).getPieceType() == Piece.pieceType.ronin ||
                            ((Piece) board[x - 1][y - 1]).getPieceType() == Piece.pieceType.ship ||
                            ((Piece) board[x - 1][y - 1]).getPieceType() == Piece.pieceType.tileExchange) {
                        System.out.println("EVEN2");
                        playerInfluence[((Piece) board[x - 1][y - 1]).getPlayer()
                                - 1][d] += ((Piece) board[x - 1][y - 1]).getValue();
                    }
                }
                if (board[x][y + 1] instanceof Piece) {
                    System.out.println("PTYPE" + ((Piece) board[x][y + 1]).getPType());
                    System.out.println("PIECETYPE" + ((Piece) board[x][y + 1]).getPieceType());
                    if (((Piece) board[x][y + 1]).getPType() == city.getResourceType(d) ||
                            ((Piece) board[x][y + 1]).getPieceType() == Piece.pieceType.samurai ||
                            ((Piece) board[x][y + 1]).getPieceType() == Piece.pieceType.ronin ||
                            ((Piece) board[x][y + 1]).getPieceType() == Piece.pieceType.ship ||
                            ((Piece) board[x][y + 1]).getPieceType() == Piece.pieceType.tileExchange) {
                        System.out.println("EVEN3");
                        playerInfluence[((Piece) board[x][y + 1]).getPlayer() - 1][d] += ((Piece) board[x][y + 1])
                                .getValue();
                    }
                }
                if (board[x][y - 1] instanceof Piece)

                {
                    System.out.println("PTYPE" + ((Piece) board[x][y - 1]).getPType());
                    System.out.println("PIECETYPE" + ((Piece) board[x][y - 1]).getPieceType());
                    if (((Piece) board[x][y - 1]).getPType() == city.getResourceType(d) ||
                            ((Piece) board[x][y - 1]).getPieceType() == Piece.pieceType.samurai ||
                            ((Piece) board[x][y - 1]).getPieceType() == Piece.pieceType.ronin ||
                            ((Piece) board[x][y - 1]).getPieceType() == Piece.pieceType.ship ||
                            ((Piece) board[x][y - 1]).getPieceType() == Piece.pieceType.tileExchange) {
                        System.out.println("EVEN4");
                        playerInfluence[((Piece) board[x][y - 1]).getPlayer() - 1][d] += ((Piece) board[x][y - 1])
                                .getValue();
                    }
                }
                if (board[x + 1][y] instanceof Piece) {
                    System.out.println("PTYPE" + ((Piece) board[x + 1][y]).getPType());
                    System.out.println("PIECETYPE" + ((Piece) board[x + 1][y]).getPieceType());
                    if (((Piece) board[x + 1][y]).getPType() == city.getResourceType(d) ||
                            ((Piece) board[x + 1][y]).getPieceType() == Piece.pieceType.samurai ||
                            ((Piece) board[x + 1][y]).getPieceType() == Piece.pieceType.ronin ||
                            ((Piece) board[x + 1][y]).getPieceType() == Piece.pieceType.ship ||
                            ((Piece) board[x + 1][y]).getPieceType() == Piece.pieceType.tileExchange) {
                        System.out.println("EVEN5");
                        playerInfluence[((Piece) board[x + 1][y]).getPlayer() - 1][d] += ((Piece) board[x + 1][y])
                                .getValue();
                    }
                }
                if (board[x + 1][y - 1] instanceof Piece) {
                    System.out.println("PTYPE" + ((Piece) board[x + 1][y - 1]).getPType());
                    System.out.println("PIECETYPE" + ((Piece) board[x + 1][y - 1]).getPieceType());
                    if (((Piece) board[x + 1][y - 1]).getPType() == city.getResourceType(d) ||
                            ((Piece) board[x + 1][y - 1]).getPieceType() == Piece.pieceType.samurai ||
                            ((Piece) board[x + 1][y - 1]).getPieceType() == Piece.pieceType.ronin ||
                            ((Piece) board[x + 1][y - 1]).getPieceType() == Piece.pieceType.ship ||
                            ((Piece) board[x + 1][y - 1]).getPieceType() == Piece.pieceType.tileExchange) {
                        System.out.println("EVEN6");
                        playerInfluence[((Piece) board[x + 1][y - 1]).getPlayer()
                                - 1][d] += ((Piece) board[x + 1][y - 1]).getValue();
                    }
                }
            }
        } else {
            System.out.println("ODDCODE");
            for (int d = 0; d < city.numCityResources; d++) {
                if (board[x - 1][y + 1] instanceof Piece) {
                    if (((Piece) board[x - 1][y + 1]).getPType() == city.getResourceType(d) ||
                            ((Piece) board[x - 1][y + 1]).getPieceType() == Piece.pieceType.samurai ||
                            ((Piece) board[x - 1][y + 1]).getPieceType() == Piece.pieceType.ronin ||
                            ((Piece) board[x - 1][y + 1]).getPieceType() == Piece.pieceType.ship ||
                            ((Piece) board[x - 1][y + 1]).getPieceType() == Piece.pieceType.tileExchange) {
                        System.out.println("ODD1");
                        playerInfluence[((Piece) board[x - 1][y + 1]).getPlayer()
                                - 1][d] += ((Piece) board[x - 1][y + 1]).getValue();
                    }
                }
                if (board[x - 1][y] instanceof Piece) {
                    if (((Piece) board[x - 1][y]).getPType() == city.getResourceType(d) ||
                            ((Piece) board[x - 1][y]).getPieceType() == Piece.pieceType.samurai ||
                            ((Piece) board[x - 1][y]).getPieceType() == Piece.pieceType.ronin ||
                            ((Piece) board[x - 1][y]).getPieceType() == Piece.pieceType.ship ||
                            ((Piece) board[x - 1][y]).getPieceType() == Piece.pieceType.tileExchange) {
                        System.out.println("ODD2");
                        playerInfluence[((Piece) board[x - 1][y]).getPlayer() - 1][d] += ((Piece) board[x - 1][y])
                                .getValue();
                    }
                }
                if (board[x][y + 1] instanceof Piece) {
                    if (((Piece) board[x][y + 1]).getPType() == city.getResourceType(d) ||
                            ((Piece) board[x][y + 1]).getPieceType() == Piece.pieceType.samurai ||
                            ((Piece) board[x][y + 1]).getPieceType() == Piece.pieceType.ronin ||
                            ((Piece) board[x][y + 1]).getPieceType() == Piece.pieceType.ship ||
                            ((Piece) board[x][y + 1]).getPieceType() == Piece.pieceType.tileExchange) {
                        System.out.println("ODD3");
                        playerInfluence[((Piece) board[x][y + 1]).getPlayer() - 1][d] += ((Piece) board[x][y + 1])
                                .getValue();
                    }
                }
                if (board[x][y - 1] instanceof Piece) {
                    if (((Piece) board[x][y - 1]).getPType() == city.getResourceType(d) ||
                            ((Piece) board[x][y - 1]).getPieceType() == Piece.pieceType.samurai ||
                            ((Piece) board[x][y - 1]).getPieceType() == Piece.pieceType.ronin ||
                            ((Piece) board[x][y - 1]).getPieceType() == Piece.pieceType.ship ||
                            ((Piece) board[x][y - 1]).getPieceType() == Piece.pieceType.tileExchange) {
                        System.out.println("ODD4");
                        playerInfluence[((Piece) board[x][y - 1]).getPlayer() - 1][d] += ((Piece) board[x][y - 1])
                                .getValue();
                    }
                }
                if (board[x + 1][y + 1] instanceof Piece) {
                    if (((Piece) board[x + 1][y + 1]).getPType() == city.getResourceType(d) ||
                            ((Piece) board[x + 1][y + 1]).getPieceType() == Piece.pieceType.samurai ||
                            ((Piece) board[x + 1][y + 1]).getPieceType() == Piece.pieceType.ronin ||
                            ((Piece) board[x + 1][y + 1]).getPieceType() == Piece.pieceType.ship ||
                            ((Piece) board[x + 1][y + 1]).getPieceType() == Piece.pieceType.tileExchange) {
                        System.out.println("ODD5");
                        playerInfluence[((Piece) board[x + 1][y + 1]).getPlayer()
                                - 1][d] += ((Piece) board[x + 1][y + 1]).getValue();
                    }
                }
                if (board[x + 1][y] instanceof Piece) {
                    if (((Piece) board[x + 1][y]).getPType() == city.getResourceType(d) ||
                            ((Piece) board[x + 1][y]).getPieceType() == Piece.pieceType.samurai ||
                            ((Piece) board[x + 1][y]).getPieceType() == Piece.pieceType.ronin ||
                            ((Piece) board[x + 1][y]).getPieceType() == Piece.pieceType.ship ||
                            ((Piece) board[x + 1][y]).getPieceType() == Piece.pieceType.tileExchange) {
                        System.out.println("ODD6");
                        playerInfluence[((Piece) board[x + 1][y]).getPlayer() - 1][d] += ((Piece) board[x + 1][y])
                                .getValue();
                    }
                }
            }

        }

        city.captured = true;

        {
            int[] bestInfluence = new int[city.numCityResources];
            int[] playerBestInfluence = new int[city.numCityResources];
            int[][] playerTies = new int[numPlayers][city.numCityResources];
            boolean[] tie = new boolean[city.numCityResources];

            for (int i = 0; i < numPlayers; i++) {
                for (int b = 0; b < city.numCityResources; b++) {
                    if (playerInfluence[i][b] > bestInfluence[b]) {
                        System.out.println("MAJOR INFLUEEEENNCEEEE");
                        bestInfluence[b] = playerInfluence[i][b];
                        playerBestInfluence[b] = i + 1;
                    } else if (playerInfluence[i][b] == bestInfluence[b]) {
                        System.out.println("PREEEEEETIEEEEEEEEEEEEEEEEEEE");
                        playerTies[i][b] = bestInfluence[b];
                    }
                }
            }
            for (int q = 0; q < numPlayers; q++) {
                for (int w = 0; w < city.numCityResources; w++) {
                    if (playerTies[q][w] == bestInfluence[w]) {
                        System.out.println("TIEEEEEEEEEEEEEEEEEEE");
                        tie[w] = true;
                        numTies++;
                        System.out.println(numTies + " NUMTIESSSSSSSSSSSSSSSSSSSSSS");
                    }
                }
            }
            for (int w = 0; w < city.numCityResources; w++) {
                if (tie[w]) {
                    city.cappedBy[w] = 5;

                }

                else if (numTies >= 4) {
                    gameOver = true;
                }
            }

            for (int i = 0; i < numPlayers; i++) {
                for (int b = 0; b < city.numCityResources; b++) {
                    if (playerInfluence[i][b] == bestInfluence[b] && city.cappedBy[b] != 5) {
                        System.out.println("GIVE POINTS COODDEEEEE");
                        if (i == 0 && city.getResource(b) == 1)
                            player1points[0]++;
                        else if (i == 0 && city.getResource(b) == 2)
                            player1points[1]++;
                        else if (i == 0 && city.getResource(b) == 3)
                            player1points[2]++;

                        else if (i == 1 && city.getResource(b) == 1)
                            player2points[0]++;
                        else if (i == 1 && city.getResource(b) == 2)
                            player2points[1]++;
                        else if (i == 1 && city.getResource(b) == 3)
                            player2points[2]++;

                        else if (i == 2 && city.getResource(b) == 1)
                            player3points[0]++;
                        else if (i == 2 && city.getResource(b) == 2)
                            player3points[1]++;
                        else if (i == 2 && city.getResource(b) == 3)
                            player3points[2]++;

                        else if (i == 3 && city.getResource(b) == 1)
                            player4points[0]++;
                        else if (i == 3 && city.getResource(b) == 2)
                            player4points[1]++;
                        else if (i == 3 && city.getResource(b) == 3)
                            player4points[2]++;

                    }
                }
            }

        }
        city.setCap();

        System.out.println("+++++++++++++++++++++++++++++");
        System.out.println(player1points[0]);
        System.out.println(player1points[1]);
        System.out.println(player1points[2]);
        System.out.println("+++++++++++++++++++++++++++++");
        System.out.println(player2points[0]);
        System.out.println(player2points[1]);
        System.out.println(player2points[2]);
        System.out.println("+++++++++++++++++++++++++++++");

    }

    public void checkCities() {
        for (int i = 0; i < BWIDTH; i++) {
            for (int b = 0; b < BHEIGHT; b++) {
                if (board[i][b] instanceof City) {
                    checkSurround(i, b);
                }

            }
        }

        // return(false);

    }

    // if(p.x % 2 == 0 )
    // {
    // System.out.println(board[p.x-1][p.y].getClass());
    // System.out.println(board[p.x-1][p.y-1].getClass());
    // System.out.println(board[p.x][p.y+1].getClass());
    // System.out.println(board[p.x][p.y-1].getClass());
    // System.out.println(board[p.x+1][p.y].getClass());
    // System.out.println(board[p.x+1][p.y-1].getClass());
    //
    // }
    // else
    // {
    // System.out.println(board[p.x-1][p.y+1].getClass());
    // System.out.println(board[p.x-1][p.y].getClass());
    // System.out.println(board[p.x][p.y+1].getClass());
    // System.out.println(board[p.x][p.y-1].getClass());
    // System.out.println(board[p.x+1][p.y+1].getClass());
    // System.out.println(board[p.x+1][p.y].getClass());
    // }
    public void checkSurround(int x, int y) {
        if (x % 2 == 0)

        {
            if ((board[x - 1][y] instanceof Terrain
                    && ((Terrain) board[x - 1][y]).getTerrainType() == Terrain.terrainType.land) ||
                    (board[x - 1][y - 1] instanceof Terrain
                            && ((Terrain) board[x - 1][y - 1]).getTerrainType() == Terrain.terrainType.land)
                    ||
                    (board[x][y + 1] instanceof Terrain
                            && ((Terrain) board[x][y + 1]).getTerrainType() == Terrain.terrainType.land)
                    ||
                    (board[x][y - 1] instanceof Terrain
                            && ((Terrain) board[x][y - 1]).getTerrainType() == Terrain.terrainType.land)
                    ||
                    (board[x + 1][y] instanceof Terrain
                            && ((Terrain) board[x + 1][y]).getTerrainType() == Terrain.terrainType.land)
                    ||
                    (board[x + 1][y - 1] instanceof Terrain
                            && ((Terrain) board[x + 1][y - 1]).getTerrainType() == Terrain.terrainType.land)) {
                // return(false);
                return;
            } else {
                if (!((City) board[x][y]).captured)
                    checkCapture(x, y);
            }
        } else {
            if ((board[x - 1][y + 1] instanceof Terrain
                    && ((Terrain) board[x - 1][y + 1]).getTerrainType() == Terrain.terrainType.land) ||
                    (board[x - 1][y] instanceof Terrain
                            && ((Terrain) board[x - 1][y]).getTerrainType() == Terrain.terrainType.land)
                    ||
                    (board[x][y + 1] instanceof Terrain
                            && ((Terrain) board[x][y + 1]).getTerrainType() == Terrain.terrainType.land)
                    ||
                    (board[x][y - 1] instanceof Terrain
                            && ((Terrain) board[x][y - 1]).getTerrainType() == Terrain.terrainType.land)
                    ||
                    (board[x + 1][y + 1] instanceof Terrain
                            && ((Terrain) board[x + 1][y + 1]).getTerrainType() == Terrain.terrainType.land)
                    ||
                    (board[x + 1][y] instanceof Terrain
                            && ((Terrain) board[x + 1][y]).getTerrainType() == Terrain.terrainType.land)) {
                // return(false);
                return;
            } else {
                if (!((City) board[x][y]).captured)
                    checkCapture(x, y);
            }

        }
        // return(true);
        // checkCapture(x,y);

    }

    class DrawingPanel extends JPanel {
        // mouse variables here
        // Point mPt = new Point(0,0);

        public DrawingPanel() {
            setBackground(Hex.none);

            MyMouseListener ml = new MyMouseListener();
            addMouseListener(ml);

            // mouseMoved m2 = new mouseMoved();
            // addMouseMotionListener(m2);
        }

        public void paintComponent(Graphics g) {

            Graphics2D g2 = (Graphics2D) g;

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            super.paintComponent(g2);
            // draw grid
            if (startScreen) {
                g.drawImage(Background, 1, 1,
                        getWidth(), getHeight(), this);
                if (help) {

                    // rectangles
                    g2.setColor(Color.red);
                    g2.fillRoundRect(22, 22, 70, 120, 5, 5);
                    g2.fillRoundRect(102, 22, 702, 902, 5, 5);

                    g2.setColor(Color.DARK_GRAY);
                    g2.fillRoundRect(20, 20, 70, 120, 5, 5);
                    g2.fillRoundRect(100, 20, 700, 900, 5, 5);
                    // back buttom
                    g2.setColor(Color.red);
                    g.setFont(new Font("Engravers MT", Font.PLAIN, 33));
                    g.drawString("B", 20, 45);
                    g.drawString("A", 35, 73);
                    g.drawString("C", 40, 104);
                    g.drawString("K", 55, 131);
                    // maine box word code
                    g.setFont(new Font("Engravers MT", Font.PLAIN, 33));
                    g.drawString("S", 108, 45);
                    g.setFont(new Font("Engravers MT", Font.PLAIN, 20));
                    g.drawString("amurai is a hexagon based board game. The ", 130, 45);
                    g.drawString("aim of the game is to place pieces from the", 108, 65);
                    g.drawString("menu on the right side of the screen on ", 108, 85);
                    g.drawString("the board next to the same type city,", 108, 105);
                    g.drawString("to influence it. There are three main types", 108, 125);
                    g.drawString("             RICE               BUDDHA           HELMET", 108, 155);

                    double scaleVal = .25;

                    // RICE
                    g2.translate(120, 180);
                    g2.scale(scaleVal, scaleVal);

                    g2.drawImage(SamuraiBoardGame.rice, 1, 1, null);

                    g2.scale(1.0 / scaleVal, 1.0 / scaleVal);
                    g2.translate(-(120), -180);
                    // BUDDAH
                    g2.translate(340, 180);
                    g2.scale(scaleVal, scaleVal);

                    g2.drawImage(SamuraiBoardGame.buddah, 1, 1, null);

                    g2.scale(1.0 / scaleVal, 1.0 / scaleVal);
                    g2.translate(-(340), -180);
                    // HELMET
                    g2.translate(500, 180);
                    g2.scale(scaleVal, scaleVal);

                    g2.drawImage(SamuraiBoardGame.helmet, 1, 1, null);

                    g2.scale(1.0 / scaleVal, 1.0 / scaleVal);
                    g2.translate(-(500), -(180));

                    // Second half
                    g.drawString("each piece has a stregth, when you ", 108, 375);
                    g.drawString("place a piece next to a city it influences it by", 108, 395);
                    g.drawString("the pieces strength. when a city is surounded on", 108, 415);
                    g.drawString("all land tiles its resources go to whoever had", 108, 435);
                    g.drawString("the greatest inflence on that resource. There ", 108, 455);
                    g.drawString("are special pieces that each have a different ", 108, 475);
                    g.drawString("functions also. ", 108, 495);

                    double scaleVal2;
                    int x = 100;
                    int y = 520;
                    // ship
                    scaleVal2 = .3;
                    g2.translate(x - 5, y - 5);
                    g2.scale(scaleVal2, scaleVal2);

                    g2.drawImage(SamuraiBoardGame.ship, 1, 1, null);

                    g2.scale(1.0 / scaleVal2, 1.0 / scaleVal2);
                    g2.translate(-(x - 5), -(y - 5));

                    // Ronin
                    x = 250;
                    scaleVal2 = .06;
                    g2.translate(x + 15, y + 10);
                    g2.scale(scaleVal2, scaleVal2);

                    g2.drawImage(SamuraiBoardGame.ronin, 1, 1, null);

                    g2.scale(1.0 / scaleVal2, 1.0 / scaleVal2);
                    g2.translate(-(x + 15), -(y + 10));

                    // Samurai
                    x = 430;
                    scaleVal2 = .3;
                    g2.translate(x, y - 10);
                    g2.scale(scaleVal2, scaleVal2);

                    g2.drawImage(SamuraiBoardGame.samurai, 1, 1, null);

                    g2.scale(1.0 / scaleVal2, 1.0 / scaleVal2);
                    g2.translate(-(x), -(y - 10));
                    // swap
                    x = 520;
                    scaleVal2 = .3;
                    g2.translate(x, y - 3);
                    g2.scale(scaleVal2, scaleVal2);

                    g2.drawImage(SamuraiBoardGame.swap, 1, 1, null);

                    g2.scale(1.0 / scaleVal2, 1.0 / scaleVal2);
                    g2.translate(-(x), -(y - 3));

                    // boulder
                    x = 700;
                    scaleVal2 = .4;

                    g2.translate(x - 5, y);

                    g2.scale(scaleVal2, scaleVal2);

                    g2.drawImage(SamuraiBoardGame.hand, 1, 1, null);

                    g2.scale(1.0 / scaleVal2, 1.0 / scaleVal2);

                    g2.translate(-(x - 5), -(y));

                    g.drawString("The first is the ship,that influences all types ", 108, 670);
                    g.drawString(",place it on water. Next is Ronin and Samurai,", 108, 690);
                    g.drawString("and both influences all resources. Next is", 108, 710);
                    g.drawString("Swap, and that allows you to pick up one of ", 108, 730);
                    g.drawString("your own land tiles. And finally, Boulder,", 108, 750);
                    g.drawString("which you can place on any playes piece, ", 108, 770);
                    g.drawString("taking its place,and smashing it", 108, 790);
                    g.drawString("for more clarification, see", 108, 810);
                    g.setColor(Color.WHITE);
                    g.drawString("http://freespace.virgin.net/chris.lawson", 100, 830);
                    g.drawString("/rk/samurai/rules.htm", 100, 850);
                } else {
                    g2.translate(getWidth() / 8, 10);

                    g.drawImage(title, 1, 1,
                            600, 150, this);

                    g2.translate(-getWidth() / 8, -10);

                    // displaying box
                    g2.setColor(Color.red);
                    g2.fillRoundRect(22, 202, 250, 70, 5, 5);
                    g2.fillRoundRect(472, 202, 250, 300, 5, 5);
                    g2.fillRoundRect(22, 302, 250, 70, 5, 5);
                    g2.fillRoundRect(22, 402, 250, 70, 5, 5);

                    g2.setColor(Color.DARK_GRAY);
                    g2.fillRoundRect(20, 200, 250, 70, 5, 5);
                    g2.fillRoundRect(470, 200, 250, 300, 5, 5);
                    g2.fillRoundRect(20, 300, 250, 70, 5, 5);
                    g2.fillRoundRect(20, 400, 250, 70, 5, 5);

                    g.setFont(new Font("Engravers MT", Font.PLAIN, 50));

                    if (numPlayers < 1)
                        g2.setColor(Color.blue);
                    else
                        g2.setColor(Color.red);

                    g.drawString("Start", 30, 255);
                    g2.setColor(Color.red);
                    g.setFont(new Font("Engravers MT", Font.PLAIN, 33));
                    g.drawString("tutorial", 30, 355);
                    g.setFont(new Font("Engravers MT", Font.PLAIN, 25));
                    g.drawString("Rules/", 30, 440);
                    g.drawString("how to play", 30, 460);

                    g.setFont(new Font("Engravers MT", Font.PLAIN, 21));

                    // displaying words in settings box
                    g2.setColor(Color.red);
                    g.drawString("settings", 480, 230);
                    g.drawString("players  |  2  3  4", 480, 260);
                    // highlighting letters once clicked
                    g2.setColor(Color.yellow);
                    if (numPlayers == 2) {
                        g.drawString("2", 639, 260);

                    }
                    if (numPlayers == 3) {
                        g.drawString("3", 667, 260);

                    }
                    if (numPlayers == 4) {
                        g.drawString("4", 695, 260);

                    }
                }
            } else {

                // fill in hexes
                for (int i = 0; i < BWIDTH; i++) {
                    for (int j = 0; j < BHEIGHT; j++) {
                        // if (board[i][j] < 0) hexmech.fillHex(i,j,COLOURONE,-board[i][j],g2);
                        // if (board[i][j] > 0) hexmech.fillHex(i,j,COLOURTWO, board[i][j],g2);

                        hexmech.fillHex(i, j, g2, board[i][j].getColor());
                        if (board[i][j] instanceof Piece)
                            hexmech.fillHex2(i, j, g2, ((Piece) board[i][j]).getPieceType(),
                                    ((Piece) board[i][j]).getValue(), ((Piece) board[i][j]).getColor());
                        if (board[i][j] instanceof City) {
                            hexmech.fillCityHex(i, j, g2, ((City) board[i][j]));
                        }
                    }
                }
                for (int i = 0; i < BWIDTH; i++) {
                    for (int j = 0; j < BHEIGHT; j++) {
                        if (board[i][j].getHexType() != Hex.hexType.none)
                            hexmech.drawHex(i, j, g2);
                    }
                }

                Color newCol = new Color(244, 219, 159);
                g.setColor(newCol);
                g.fillRect(1510, 0, 150, 1000);

                // leftBorder
                g.setColor(Color.BLACK);
                g.fillRect(1510, 0, 10, 1000);

                // topBorder
                g.setColor(Color.BLACK);
                g.fillRect(1510, 0, 620, 10);

                // bottomBorder
                g.setColor(Color.BLACK);
                g.fillRect(1510, 982, 360, 10);

                // rightBorder
                g.setColor(Color.BLACK);
                g.fillRect(1643, 0, 10, 1000);

                // ricePiece

                for (int i = 0; i < 5; i++) {
                    player1[i].setPlayer(1);
                }
                if (numPlayers >= 2)
                    for (int i = 0; i < 5; i++) {
                        player2[i].setPlayer(2);
                    }
                if (numPlayers >= 3)
                    for (int i = 0; i < 5; i++) {
                        player3[i].setPlayer(3);
                    }
                if (numPlayers >= 4)
                    for (int i = 0; i < 5; i++) {
                        player4[i].setPlayer(4);
                    }
                // rice
                if (playerTurn == 1) {
                    g2.setColor(Color.RED);
                    hexmech.fillMenuHex1(27, 3, g2, player1[0]);
                    g2.setColor(Color.RED);
                    hexmech.fillMenuHex1(27, 5, g2, player1[1]);
                    g2.setColor(Color.RED);
                    hexmech.fillMenuHex1(27, 7, g2, player1[2]);
                    g2.setColor(Color.RED);
                    hexmech.fillMenuHex1(27, 9, g2, player1[3]);
                    g2.setColor(Color.RED);
                    hexmech.fillMenuHex1(27, 11, g2, player1[4]);
                    g2.setColor(Color.RED);

                }
                if (playerTurn == 2) {
                    g2.setColor(Color.BLUE);
                    hexmech.fillMenuHex1(27, 3, g2, player2[0]);
                    g2.setColor(Color.BLUE);
                    hexmech.fillMenuHex1(27, 5, g2, player2[1]);
                    g2.setColor(Color.BLUE);
                    hexmech.fillMenuHex1(27, 7, g2, player2[2]);
                    g2.setColor(Color.BLUE);
                    hexmech.fillMenuHex1(27, 9, g2, player2[3]);
                    g2.setColor(Color.BLUE);
                    hexmech.fillMenuHex1(27, 11, g2, player2[4]);
                    g2.setColor(Color.BLUE);

                }
                if (playerTurn == 3) {
                    g2.setColor(Color.GREEN);
                    hexmech.fillMenuHex1(27, 3, g2, player3[0]);
                    g2.setColor(Color.GREEN);
                    hexmech.fillMenuHex1(27, 5, g2, player3[1]);
                    g2.setColor(Color.GREEN);
                    hexmech.fillMenuHex1(27, 7, g2, player3[2]);
                    g2.setColor(Color.GREEN);
                    hexmech.fillMenuHex1(27, 9, g2, player3[3]);
                    g2.setColor(Color.GREEN);
                    hexmech.fillMenuHex1(27, 11, g2, player3[4]);
                    g2.setColor(Color.GREEN);

                }
                if (playerTurn == 4) {
                    g2.setColor(Color.CYAN);
                    hexmech.fillMenuHex1(27, 3, g2, player4[0]);
                    g2.setColor(Color.CYAN);
                    hexmech.fillMenuHex1(27, 5, g2, player4[1]);
                    g2.setColor(Color.CYAN);
                    hexmech.fillMenuHex1(27, 7, g2, player4[2]);
                    g2.setColor(Color.CYAN);
                    hexmech.fillMenuHex1(27, 9, g2, player4[3]);
                    g2.setColor(Color.CYAN);
                    hexmech.fillMenuHex1(27, 11, g2, player4[4]);
                    g2.setColor(Color.CYAN);

                }

                if (playerTurn == 1)
                    g2.setColor(Color.RED);
                if (playerTurn == 2)
                    g2.setColor(Color.BLUE);
                if (playerTurn == 3)
                    g2.setColor(Color.GREEN);
                if (playerTurn == 4)
                    g2.setColor(Color.CYAN);

                g.setFont(new Font("Engravers MT", Font.PLAIN, 19));

                g.drawString("Placing", 1525, 54);
                g.setFont(new Font("Engravers MT", Font.PLAIN, 15));
                g.drawString("Player " + playerTurn + "'s", 1525, 174);
                g.setFont(new Font("Engravers MT", Font.PLAIN, 19));
                g.drawString("hand", 1550, 205);

                hexmech.fillMenuHex1(27, 1, g2, chosen);
                if (chosen.getPieceType() == Piece.pieceType.tileExchange) {
                    g.setColor(Color.BLACK);
                    g.fillRect(1055, 15, 400, 70);

                    g.setColor(newCol);
                    g.fillRect(1060, 20, 390, 60);

                    g.setFont(new Font("Engravers MT", Font.PLAIN, 19));
                    g.setColor(Color.BLACK);
                    g.drawString("Pick one of your pieces to", 1065, 35);
                    g.drawString("pick back up and place", 1065, 50);
                    g.drawString("again :)", 1065, 65);
                }
                // display caputured resources

                g.setColor(Color.BLACK);
                g.fillRect(20, 20, 210, 410);
                g.setColor(newCol);
                g.fillRect(25, 25, 200, 400);
                // player 1
                g.setColor(Color.BLACK);
                g.setFont(new Font("Engravers MT", Font.PLAIN, 15));
                g.drawString("Player1", 25, 40);
                g.drawString("Rice = " + player1points[0], 25, 60);
                g.drawString("Helmet = " + player1points[1], 25, 80);
                g.drawString("Buddah = " + player1points[2], 25, 100);
                g.drawString("Player2", 25, 140);
                g.drawString("Rice = " + player2points[0], 25, 160);
                g.drawString("Helmet = " + player2points[1], 25, 180);
                g.drawString("Buddah = " + player2points[2], 25, 200);
                if (numPlayers > 2) {
                    g.drawString("Player3", 25, 240);
                    g.drawString("Rice = " + player3points[0], 25, 260);
                    g.drawString("Helmet = " + player3points[1], 25, 280);
                    g.drawString("Buddah = " + player3points[2], 25, 300);

                }
                if (numPlayers > 3) {
                    g.drawString("Player4", 25, 340);
                    g.drawString("Rice = " + player4points[0], 25, 360);
                    g.drawString("Helmet = " + player4points[1], 25, 380);
                    g.drawString("Buddah = " + player4points[2], 25, 400);
                }

                if (gameOver) {
                    g.setColor(Color.red);
                    g.drawString("GAMEOVER check scores to see winner", 25, 20);
                }

            }

        }

        class MyMouseListener extends MouseAdapter { // inner class inside DrawingPanel
            public void mouseReleased(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                System.out.println(x + " X \\ Y " + y);
                // System.out.println(x+ " X | Y " + y);
                if (startScreen) {
                    if (638 < x && x < 655 && 243 < y && y < 265) {
                        numPlayers = 2;
                    } else if (665 < x && x < 685 && 243 < y && y < 265) {
                        numPlayers = 3;
                    } else if (693 < x && x < 711 && 243 < y && y < 265) {
                        numPlayers = 4;
                    }
                    if (x > 20 && x < 270 && y > 400 && y < 470) {
                        // JFrame rules = new JFrame("Samurai");
                        help = true;
                        // rules.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
                        // rules.setSize( (int)(SCRSIZE/1.23)*widen, SCRSIZE);
                        // rules.setResizable(false);
                        // rules.setLocationRelativeTo( null );
                        // rules.setVisible(true);
                    }
                    if (x > 20 && x < 270 && y < 270 && y > 200 && numPlayers > 0) {
                        startScreen = false;

                        hexmech.setXYasVertex(false); // RECOMMENDED: leave this as FALSE.

                        hexmech.setHeight(HEXSIZE); // Either setHeight or setSize must be run to initialize the hex
                        hexmech.setBorders(BORDERS);

                        reset();

                        frame.setSize((int) (SCRSIZE / 1.23) * widen, SCRSIZE);
                        frame.setLocation(10, 10);
                        hexmech.setHeight(HEXSIZE); // Either setHeight or setSize must be run to initialize the hex
                        hexmech.setBorders(BORDERS);
                        Island();
                        startGame();
                        System.out.println(numRice);
                        System.out.println(numHelm);
                        System.out.println(numBuddah);

                    }
                    if (x > 20 && x < 90 && y < 140 && y > 20) {
                        help = false;
                        System.out.println(menuScreenSize);

                    }
                } else {
                    if (x > 1539 && x < 1622 && y > 225 && y < 287) {
                        lastChosen = 0;
                        if (playerTurn == 1) {
                            player1[0].setPlayer(1);
                            chosen = new Piece(player1[0]);

                        }
                        if (playerTurn == 2) {
                            player2[0].setPlayer(2);
                            chosen = new Piece(player2[0]);
                        }
                        if (playerTurn == 3) {
                            player3[0].setPlayer(3);
                            chosen = new Piece(player3[0]);
                        }
                        if (playerTurn == 4) {
                            player4[0].setPlayer(4);
                            chosen = new Piece(player4[0]);
                        }

                    } else if (x > 1539 && x < 1622 && y > 364 && y < 432) {
                        lastChosen = 1;
                        if (playerTurn == 1) {
                            player1[1].setPlayer(1);
                            chosen = new Piece(player1[1]);
                        }
                        if (playerTurn == 2) {
                            player2[1].setPlayer(2);
                            chosen = new Piece(player2[1]);
                        }
                        if (playerTurn == 3) {
                            player3[1].setPlayer(3);
                            chosen = new Piece(player3[1]);
                        }
                        if (playerTurn == 4) {
                            player4[1].setPlayer(4);
                            chosen = new Piece(player4[1]);
                        }

                    } else if (x > 1539 && x < 1622 && y > 504 && y < 573) {
                        lastChosen = 2;
                        if (playerTurn == 1) {
                            player1[lastChosen].setPlayer(1);
                            chosen = new Piece(player1[lastChosen]);
                        }
                        if (playerTurn == 2) {
                            player2[lastChosen].setPlayer(2);
                            chosen = new Piece(player2[lastChosen]);
                        }
                        if (playerTurn == 3) {
                            player3[lastChosen].setPlayer(3);
                            chosen = new Piece(player3[lastChosen]);
                        }
                        if (playerTurn == 4) {
                            player4[lastChosen].setPlayer(4);
                            chosen = new Piece(player4[lastChosen]);
                        }

                    } else if (x > 1539 && x < 1622 && y > 645 && y < 711) {
                        lastChosen = 3;
                        if (playerTurn == 1) {
                            player1[lastChosen].setPlayer(1);
                            chosen = new Piece(player1[lastChosen]);
                        }
                        if (playerTurn == 2) {
                            player2[lastChosen].setPlayer(2);
                            chosen = new Piece(player2[lastChosen]);
                        }
                        if (playerTurn == 3) {
                            player3[lastChosen].setPlayer(3);
                            chosen = new Piece(player3[lastChosen]);
                        }
                        if (playerTurn == 4) {
                            player4[lastChosen].setPlayer(4);
                            chosen = new Piece(player4[lastChosen]);
                        }

                    } else if (x > 1539 && x < 1622 && y > 785 && y < 852) {
                        lastChosen = 4;
                        if (playerTurn == 1) {
                            player1[lastChosen].setPlayer(1);
                            chosen = new Piece(player1[lastChosen]);
                        }
                        if (playerTurn == 2) {
                            player2[lastChosen].setPlayer(2);
                            chosen = new Piece(player2[lastChosen]);
                        }
                        if (playerTurn == 3) {
                            player3[lastChosen].setPlayer(3);
                            chosen = new Piece(player3[lastChosen]);
                        }
                        if (playerTurn == 4) {
                            player4[lastChosen].setPlayer(4);
                            chosen = new Piece(player4[lastChosen]);
                        }

                    }

                    Point p = new Point(hexmech.pxtoHex(e.getX(), e.getY()));
                    if (p.x < 0 || p.y < 0 || p.x >= BWIDTH || p.y >= BHEIGHT)
                        return;
                    if (chosen.getPieceType() == Piece.pieceType.tileExchange) {
                        if (board[p.x][p.y] instanceof Piece)
                            if (((Piece) board[p.x][p.y]).player == chosen.player
                                    && ((Piece) board[p.x][p.y]).getPieceType() != Piece.pieceType.ship) {
                                if (chosen.player == 1)
                                    player1[lastChosen] = randomPiecesForPlayers(chosen.player);
                                if (chosen.player == 2)
                                    player2[lastChosen] = randomPiecesForPlayers(chosen.player);
                                if (chosen.player == 3)
                                    player3[lastChosen] = randomPiecesForPlayers(chosen.player);
                                if (chosen.player == 4)
                                    player4[lastChosen] = randomPiecesForPlayers(chosen.player);
                                chosen = ((Piece) board[p.x][p.y]);
                                board[p.x][p.y] = new Terrain();
                                lastChosen = -1;
                            }

                    } else if (board[p.x][p.y] instanceof Terrain) {
                        if (chosen.getPieceType() != Piece.pieceType.none) {
                            if (((Terrain) board[p.x][p.y]).getTerrainType() == Terrain.terrainType.land
                                    && chosen.getPieceType() != Piece.pieceType.ship) {
                                // Changed over to intanceing new piece
                                board[p.x][p.y] = new Piece(chosen);

                                chosen = new Piece(Piece.pieceType.none, 0);
                                // System.out.println(playerTurn);
                                if (lastChosen > -1) {
                                    if (playerTurn == 1) {

                                        // System.out.println(numPiecesPlayer1 + "
                                        // NUMPIECESSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS PLAYER1");
                                        if (numPiecesPlayer1 > 0)
                                            player1[lastChosen] = randomPiecesForPlayers(1);
                                        else
                                            player1[lastChosen] = new Piece(Piece.pieceType.none, 0);

                                    }
                                    if (playerTurn == 2) {

                                        // System.out.println(numPiecesPlayer2 + "
                                        // NUMPIECESSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS PLAYER2");
                                        if (numPiecesPlayer2 > 0)
                                            player2[lastChosen] = randomPiecesForPlayers(2);
                                        else
                                            player2[lastChosen] = new Piece(Piece.pieceType.none, 0);

                                    }
                                    if (playerTurn == 3) {

                                        // System.out.println(numPiecesPlayer1 + "
                                        // NUMPIECESSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS PLAYER1");
                                        if (numPiecesPlayer3 > 0)
                                            player3[lastChosen] = randomPiecesForPlayers(3);
                                        else
                                            player3[lastChosen] = new Piece(Piece.pieceType.none, 0);

                                    }
                                    if (playerTurn == 4) {

                                        // System.out.println(numPiecesPlayer2 + "
                                        // NUMPIECESSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS PLAYER2");
                                        if (numPiecesPlayer4 > 0)
                                            player4[lastChosen] = randomPiecesForPlayers(4);
                                        else
                                            player4[lastChosen] = new Piece(Piece.pieceType.none, 0);

                                    }
                                }

                                playerTurn++;

                                if (playerTurn > numPlayers)
                                    playerTurn = 1;
                            } else if (((Terrain) board[p.x][p.y]).getTerrainType() == Terrain.terrainType.sea
                                    && chosen.getPieceType() == Piece.pieceType.ship) {

                                // Changed over to intanceing new piece
                                board[p.x][p.y] = new Piece(chosen);

                                chosen = new Piece(Piece.pieceType.none, 0);
                                if (lastChosen > -1) {
                                    if (playerTurn == 1) {

                                        System.out.println(numPiecesPlayer1
                                                + " NUMPIECESSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS PLAYER1");
                                        if (numPiecesPlayer1 > 0)
                                            player1[lastChosen] = randomPiecesForPlayers(1);
                                        else
                                            player1[lastChosen] = new Piece(Piece.pieceType.none, 0);

                                    }
                                    if (playerTurn == 2) {

                                        System.out.println(numPiecesPlayer2
                                                + " NUMPIECESSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS PLAYER2");
                                        if (numPiecesPlayer2 > 0)
                                            player2[lastChosen] = randomPiecesForPlayers(2);
                                        else
                                            player2[lastChosen] = new Piece(Piece.pieceType.none, 0);

                                    }
                                    if (playerTurn == 3) {

                                        // System.out.println(numPiecesPlayer1 + "
                                        // NUMPIECESSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS PLAYER1");
                                        if (numPiecesPlayer3 > 0)
                                            player3[lastChosen] = randomPiecesForPlayers(3);
                                        else
                                            player3[lastChosen] = new Piece(Piece.pieceType.none, 0);

                                    }
                                    if (playerTurn == 4) {

                                        // System.out.println(numPiecesPlayer2 + "
                                        // NUMPIECESSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS PLAYER2");
                                        if (numPiecesPlayer4 > 0)
                                            player4[lastChosen] = randomPiecesForPlayers(4);
                                        else
                                            player4[lastChosen] = new Piece(Piece.pieceType.none, 0);

                                    }
                                }

                                playerTurn++;

                                if (playerTurn > numPlayers)
                                    playerTurn = 1;
                            }

                            checkCities();
                            checkEnd();

                            // System.out.println(lastChosen);

                        }

                    } else if (chosen.getPieceType() == Piece.pieceType.figureExchange) {
                        if (board[p.x][p.y] instanceof Terrain || board[p.x][p.y] instanceof Piece) {
                            board[p.x][p.y] = new Piece(chosen);

                            chosen = new Piece(Piece.pieceType.none, 0);
                            if (lastChosen > -1) {
                                if (playerTurn == 1) {
                                    if (numPiecesPlayer1 > 0)
                                        player1[lastChosen] = randomPiecesForPlayers(1);
                                    else
                                        player1[lastChosen] = new Piece(Piece.pieceType.none, 0);

                                }
                                if (playerTurn == 2) {
                                    if (numPiecesPlayer2 > 0)
                                        player2[lastChosen] = randomPiecesForPlayers(2);
                                    else
                                        player2[lastChosen] = new Piece(Piece.pieceType.none, 0);

                                }
                                if (playerTurn == 3) {
                                    if (numPiecesPlayer3 > 0)
                                        player3[lastChosen] = randomPiecesForPlayers(3);
                                    else
                                        player3[lastChosen] = new Piece(Piece.pieceType.none, 0);

                                }
                                if (playerTurn == 4) {
                                    if (numPiecesPlayer4 > 0)
                                        player4[lastChosen] = randomPiecesForPlayers(4);
                                    else
                                        player4[lastChosen] = new Piece(Piece.pieceType.none, 0);
                                    playerTurn++;

                                    if (playerTurn > numPlayers)
                                        playerTurn = 1;
                                }

                            }
                        }
                    }
                    repaint();
                }

            }
        }
    }
}