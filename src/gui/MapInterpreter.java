package gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JButton;

import maps.*;
import transitlines.*;

/**
* Class for displaying visual map elements.
*/

class MapInterpreter extends JPanel{

    Map gameMap = new Map();

    int DEFAULT_WIDTH = (gameMap.tileLength() * 40);//Sets width based on map size.
    int DEFAULT_HEIGHT = ((gameMap.tileLength() * 40) + 20);//sets height based on map size.

    //for refreshing map at regular intervals.
    private int myTimerDelay;
    private final Timer myTimer;

    int selectorX;//for keeping track of selected coordinate in map.
    int selectorY;//for keeping track of selected coordinate in map.

    //Sets image icons for map tiles.
    ImageIcon ocean = new ImageIcon(getClass().getResource("../BoardImages/Ocean-01.png"));
    ImageIcon selector = new ImageIcon(getClass().getResource("../BoardImages/Selector-01.png"));
    ImageIcon bikepath = new ImageIcon(getClass().getResource("../BoardImages/Bikepath-01.png"));
    ImageIcon desert = new ImageIcon(getClass().getResource("../BoardImages/Desert-01.png"));
    ImageIcon downtown = new ImageIcon(getClass().getResource("../BoardImages/Downtown-01.png"));
    ImageIcon electrictrain = new ImageIcon(getClass().getResource("../BoardImages/ElectricTrain-01.png"));
    ImageIcon farm = new ImageIcon(getClass().getResource("../BoardImages/Farm-01.png"));
    ImageIcon forest = new ImageIcon(getClass().getResource("../BoardImages/Forest-01.png"));
    ImageIcon gastrain = new ImageIcon(getClass().getResource("../BoardImages/GasTrain-01.png"));
    ImageIcon grass = new ImageIcon(getClass().getResource("../BoardImages/Grass-01.png"));
    ImageIcon home = new ImageIcon(getClass().getResource("../BoardImages/Home-01.png"));
    ImageIcon industrial = new ImageIcon(getClass().getResource("../BoardImages/Industrial-01.png"));
    ImageIcon orbitaltrain = new ImageIcon(getClass().getResource("../BoardImages/OrbitalTrain-01.png"));
    ImageIcon river = new ImageIcon(getClass().getResource("../BoardImages/River-01.png"));
    ImageIcon road = new ImageIcon(getClass().getResource("../BoardImages/Road-01.png"));
    ImageIcon rock = new ImageIcon(getClass().getResource("../BoardImages/Rock-01.png"));
    ImageIcon shopping = new ImageIcon(getClass().getResource("../BoardImages/Shopping-01.png"));
    ImageIcon win = new ImageIcon(getClass().getResource("../BoardImages/Win-01.png"));
    ImageIcon lose = new ImageIcon(getClass().getResource("../BoardImages/Lose-01.png"));
    ImageIcon fires = new ImageIcon(getClass().getResource("../BoardImages/Fire-01.png"));
    ImageIcon tornadoes = new ImageIcon(getClass().getResource("../BoardImages/Tornado-01.png"));

    //Declares Image for use in drawImage() method. Does not initialize.
    Image oceanTile;
    Image selectorTile;
    Image bikepathTile;
    Image desertTile;
    Image downtownTile;
    Image electrictrainTile;
    Image farmTile;
    Image forestTile;
    Image gastrainTile;
    Image grassTile;
    Image homeTile;
    Image industrialTile;
    Image orbitaltrainTile;
    Image riverTile;
    Image roadTile;
    Image rockTile;
    Image shoppingTile;
    Image youWin;
    Image youLose;
    Image fireTile;
    Image tornadoTile;

    public MapInterpreter(){//constructor for MapInterpreter.

        this.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        this.setVisible(true);
        this.setBackground(Color.WHITE);

        myTimerDelay = 100;//sets refresh rate for map.
        myTimer = new Timer(myTimerDelay, gameTimer);
        myTimer.start();

        setImageFiles();//initializes tile Image with ImageIcons set above.
        setSelector(0,0);

    }

    public void setImageFiles(){//initializes tile Image with ImageIcons set above.

        oceanTile = ocean.getImage();
        selectorTile = selector.getImage();
        bikepathTile = bikepath.getImage();
        desertTile = desert.getImage();
        downtownTile = downtown.getImage();
        electrictrainTile = electrictrain.getImage();
        farmTile = farm.getImage();
        forestTile = forest.getImage();
        gastrainTile = gastrain.getImage();
        grassTile = grass.getImage();
        homeTile = home.getImage();
        industrialTile = industrial.getImage();
        orbitaltrainTile = orbitaltrain.getImage();
        riverTile = river.getImage();
        roadTile = road.getImage();
        rockTile = rock.getImage();
        shoppingTile = shopping.getImage();
        youWin = win.getImage();
        youLose = lose.getImage();
        fireTile = fires.getImage();
        tornadoTile = tornadoes.getImage();

    }

        public void paintComponent(Graphics g){

            super.paintComponent(g);

            if(!gameMap.winCondition()){//Draws map if win Condition has not been met.

                //Cycles through tile array in map and sets Images based on array values.
                for(int a = 0; a < gameMap.tileLength(); a++){
                    for(int b = 0; b < gameMap.tileLength(); b++){

                        //sets pixel coordinates for image display.
                        int xcoord = a * 40;
                        int ycoord = b * 40;

                        //Draws image based on tile array in map class.
                        if(gameMap.tileReturn(a, b) == Map.OCEAN){
                            g.drawImage(oceanTile, xcoord, ycoord, null);
                        }
                        if(gameMap.tileReturn(a,b) == Map.BIKEPATH){
                            g.drawImage(bikepathTile, xcoord, ycoord, null);
                        }
                        if(gameMap.tileReturn(a,b) == Map.DESERT){
                            g.drawImage(desertTile, xcoord, ycoord, null);
                        }
                        if(gameMap.tileReturn(a,b) == Map.DOWNTOWN){
                            g.drawImage(downtownTile, xcoord, ycoord, null);
                        }
                        if(gameMap.tileReturn(a,b) == Map.ELECTRICTRAIN){
                            g.drawImage(electrictrainTile, xcoord, ycoord, null);
                        }
                        if(gameMap.tileReturn(a,b) == Map.FARM){
                            g.drawImage(farmTile, xcoord, ycoord, null);
                        }
                        if(gameMap.tileReturn(a,b) == Map.FOREST){
                            g.drawImage(forestTile, xcoord, ycoord, null);
                        }
                        if(gameMap.tileReturn(a,b) == Map.GASTRAIN){
                            g.drawImage(gastrainTile, xcoord, ycoord, null);
                        }
                        if(gameMap.tileReturn(a,b) == Map.GRASS){
                            g.drawImage(grassTile, xcoord, ycoord, null);
                        }
                        if(gameMap.tileReturn(a,b) == Map.HOME){
                            g.drawImage(homeTile, xcoord, ycoord, null);
                        }
                        if(gameMap.tileReturn(a,b) == Map.INDUSTRY){
                            g.drawImage(industrialTile, xcoord, ycoord, null);
                        }
                        if(gameMap.tileReturn(a,b) == Map.ORBITALTRAIN){
                            g.drawImage(orbitaltrainTile, xcoord, ycoord, null);
                        }
                        if(gameMap.tileReturn(a,b) == Map.RIVER){
                            g.drawImage(riverTile, xcoord, ycoord, null);
                        }
                        if(gameMap.tileReturn(a,b) == Map.ROAD){
                            g.drawImage(roadTile, xcoord, ycoord, null);
                        }
                        if(gameMap.tileReturn(a,b) == Map.ROCKS){
                            g.drawImage(rockTile, xcoord, ycoord, null);
                        }
                        if(gameMap.tileReturn(a,b) == Map.SHOP){
                            g.drawImage(shoppingTile, xcoord, ycoord, null);
                        }
                        if(gameMap.tileReturn(a,b) == Map.FIRE){
                            g.drawImage(fireTile, xcoord, ycoord, null);
                        }
                        if(gameMap.tileReturn(a,b) == Map.TORNADO){
                            g.drawImage(tornadoTile, xcoord, ycoord, null);
                        }
                        if(gameMap.tileReturn(a,b) == Map.FLOOD){
                            g.drawImage(riverTile, xcoord, ycoord, null);
                        }
                        if(gameMap.tileReturn(a,b) == Map.HURRICANE){
                            g.drawImage(oceanTile, xcoord, ycoord, null);
                        }
                    }
                }

                g.drawImage(selectorTile, (selectorX * 40), (selectorY * 40), null);//Draws selector square on map.

                int numDisplayHeight = gameMap.tileLength() * 40 + 15;//sets position of stat display.
                g.drawString(bankDisplay(), 0, numDisplayHeight);//draws stat display for bank.
                g.drawString(turnDisplay(), 75, numDisplayHeight);//draws stat display for turn counter.
            }

            if(gameMap.winCondition()){//Draws you win image if win condition is met.
                g.drawImage(youWin, 0, 0, null);
            }
            if((!gameMap.winCondition())&&(gameMap.turnCounter == 16)){//Draws you lose image if game is complete and win condition not met.
                g.drawImage(youLose, 0, 0, null);
            }
        }

        public void redraw(){//redraws map. Called when refresh occurs.
        this.repaint();

        }

        public int mapLength(){//returns length of map.
            return gameMap.tileLength();
        }

        public void setSelector(int x, int y){//sets selector x and y coordinates on map.
            selectorX = x;
            selectorY = y;
        }

        public void insert(TransitLine selection){//Inserts selected transitline into tile array in map.
            gameMap.insertTransit(selectorX, selectorY, selection);
        }

        public void endTurn(){//actions that should occur at end of turn.

            gameMap.turnCounter ++;//increments turn counter.

            if(gameMap.winCondition()){//ends method is win condition met.
                return;
            }

            gameMap.resetDisasterArray();//resets disaster array in map to null values.
            gameMap.bank = gameMap.bank + gameMap.TurnValueFromTransit();//adds value to bank based on turn income.
            gameMap.incrementCo2();//adds Co2 created this turn to total Co2 count.
            gameMap.disasterStrike(gameMap.totalCo2);//runs disasters.

        }

        public void remove(){//removes transitline from map at selected coordinates.
            gameMap.removeTransit(selectorX, selectorY);
        }

        String bankDisplay(){//sets text for bank stats - used in paintComponent().

            String number = Integer.toString(gameMap.bank);
            String display = "BANK: $";
            return display + number;
        }
        String turnDisplay(){//sets text for turn counter stats - used in paintComponent().

            String number = Integer.toString(gameMap.turnCounter);
            String display = "TURN COUNT: ";
            return display + number;
        }

        ActionListener gameTimer = new ActionListener() {//redraws map based on timer refresh rate.

            public void actionPerformed(ActionEvent theEvent){
                redraw();
            }
        };

}
