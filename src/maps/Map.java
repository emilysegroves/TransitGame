package maps;
import transitlines.*;
import disasters.*;

enum Terrain{
    GRASS,
    ROCK,
    RIVER,
    OCEAN,
    FOREST,
    DESERT,
    INDUSTRIAL,
    HOME,
    SHOPPING,
    FARM,
    DOWNTOWN;
}

public class Map{

    /*
    * To create multiple maps create new class that extends class maps and override constructor.
    */

    public int bank = 15;//keeps track of how much money the player has to use.
    public int turnCounter = 0;//keeps track of turns taken.
    public int totalCo2;//keeps track of accumulative Co2 count.
    public String tile[][] = new String[10][10];//string representation of map.
    Terrain terrainArray[][] = new Terrain[10][10];//array keeps track of terrain placement.
    TransitLine[][] transitArray = new TransitLine[10][10];//array keeps track of transitline placement.
    Disasters[][] disastersArray = new Disasters[10][10];//Array keeps track of where disasters occur.


    ///// CONSTANTS ///////////////////////////////////////////////////////////

    public static final String GRASS = "[GRASS]";
    public static final String ROCKS = "[ROCKS]";
    public static final String RIVER = "[RIVER]";
    public static final String OCEAN = "[OCEAN]";
    public static final String FOREST = "[FORST]";
    public static final String DESERT = "[DESRT]";
    public static final String ROAD = "[ROADS]";
    public static final String BIKEPATH = "[BKPTH]";
    public static final String GASTRAIN = "[GSTRN]";
    public static final String ELECTRICTRAIN = "[ELTRN]";
    public static final String ORBITALTRAIN = "[ORTRN]";
    public static final String FLOOD = "[FLOOD]";
    public static final String HURRICANE = "[HURRI]";
    public static final String FIRE = "[FIRES]";
    public static final String TORNADO = "[TRNDO]";
    public static final String FARM = "[FAARM]";
    public static final String INDUSTRY = "[INDUS]";
    public static final String DOWNTOWN = "[DOWNT]";
    public static final String HOME = "[HOOME]";
    public static final String SHOP = "[SHOPP]";
    public static final String NULL = "[NULL]";


    public Map(){//constructor for map.

        /*
        * Note that if different map size is required the following arrays must be initialized inside constructor:
        ** tile, transitArray, disastersArray.
        * If different starting bank number is required for new map size, initialize bank inside constructor.
        */

        //Fills terrainArray with values.
        for (int a = 0; a < terrainArray.length; a++){
            for (int b = 0; b < terrainArray.length; b ++){
                terrainArray[a][b] = Terrain.GRASS;
            }
        }

        for (int a = 0; a < 8; a++){
            terrainArray[0][a] = Terrain.OCEAN;
        }

        for (int a = 0; a < 6; a++){
            terrainArray[1][a] = Terrain.OCEAN;
        }

        for (int a = 0; a < 4; a++){
            terrainArray[2][a] = Terrain.OCEAN;
        }

        for (int a = 0; a < 10; a++){
            for (int b = 3; b < 10; b++){
                terrainArray[b][a] = Terrain.GRASS;
            }
        }

        terrainArray[1][8] = Terrain.FOREST;
        terrainArray[7][0] = Terrain.DESERT;

        terrainArray[9][4] = Terrain.RIVER;
        terrainArray[8][5] = Terrain.RIVER;
        terrainArray[7][6] = Terrain.RIVER;
        terrainArray[7][7] = Terrain.RIVER;
        terrainArray[7][8] = Terrain.RIVER;
        terrainArray[7][9] = Terrain.RIVER;

        terrainArray[8][8] = Terrain.FARM;
        terrainArray[4][8] = Terrain.INDUSTRIAL;
        terrainArray[7][3] = Terrain.SHOPPING;
        terrainArray[4][1] = Terrain.HOME;
        terrainArray[4][4] = Terrain.DOWNTOWN;

        //Sets starting transitArray values to NULLTRANSIT.
        for (int a = 0; a < transitArray.length; a++){
            for (int b = 0; b < transitArray.length; b++){
                transitArray[a][b] = TransitLine.NULLTRANSIT;
            }
        }

        //Sets starting disastersArray values to NULLDISASTER.
        for (int a = 0; a < disastersArray.length; a++){
            for (int b = 0; b < disastersArray.length; b++){
                disastersArray[a][b] = Disasters.NULLDISASTER;
            }
        }

        updateTile();//updates tile based on changes to terrainArray, transitArray, and disastersArray.

    }//ends constructor

    public boolean terrainChecker(int x, int y){///Checks if terrain is buildable.

         if(terrainArray[x][y] == Terrain.GRASS)return true;
         if(terrainArray[x][y] == Terrain.FOREST)return true;
         if(terrainArray[x][y] == Terrain.DESERT)return true;
         if(terrainArray[x][y] == Terrain.RIVER)return true;
         if(terrainArray[x][y] == Terrain.ROCK)return false;
         if(terrainArray[x][y] == Terrain.OCEAN)return false;
         if(terrainArray[x][y] == Terrain.INDUSTRIAL)return false;
         if(terrainArray[x][y] == Terrain.HOME)return false;
         if(terrainArray[x][y] == Terrain.SHOPPING)return false;
         if(terrainArray[x][y] == Terrain.FARM)return false;
         if(terrainArray[x][y] == Terrain.DOWNTOWN)return false;

         else return false;

    }

    public boolean transitChecker(int x, int y,TransitLine selection){//checks against transitArray to see if map coordinte is buildable.

        if(transitArray[x][y] == TransitLine.NULLTRANSIT){
            if (selection != TransitLine.BIKEPATH){
                return true;
            }
        }

        if(transitArray[x][y] == TransitLine.ROAD){
            if(selection == TransitLine.BIKEPATH) return true;
            else return false;
        }

        if(transitArray[x][y] == TransitLine.BIKEPATH)return false;
        if(transitArray[x][y] == TransitLine.GASTRAIN)return false;
        if(transitArray[x][y] == TransitLine.ELECTRICTRAIN)return false;
        if(transitArray[x][y] == TransitLine.ORBITALTRAIN)return false;

        else return false;

    }

    public void displayMap(){//Dipslays tile in command line. Use for debugging purposes.

        for(int t = 0; t < tile.length; t++){
            System.out.print("\n");
            for(int i= 0; i < tile.length; i++){
                System.out.print(tile[t][i]);
            }
        }

    }

    public void updateTile(){//Updates tile with terrainArray, transitArray, and disastersArray.

        for(int t = 0; t < tile.length; t++){ //Updates tile[][] with terrainArray[][]
            for(int i= 0; i < tile.length; i++){
                if (terrainArray[t][i] == Terrain.GRASS){
                    tile[t][i] = GRASS;
                }
                if (terrainArray[t][i] == Terrain.ROCK){
                    tile[t][i] = ROCKS;
                }
                if (terrainArray[t][i] == Terrain.RIVER){
                    tile[t][i] = RIVER;
                }
                if (terrainArray[t][i] == Terrain.OCEAN){
                    tile[t][i] = OCEAN;
                }
                if (terrainArray[t][i] == Terrain.FOREST){
                    tile[t][i] = FOREST;
                }
                if (terrainArray[t][i] == Terrain.DESERT){
                    tile[t][i] = DESERT;
                }
            }
        }

        for(int t = 0; t < tile.length; t++){//Updates tile[][] with transitArray[][]
            for(int i= 0; i < tile.length; i++){
                if (transitArray[t][i] == TransitLine.ROAD){
                    tile[t][i] = ROAD;
                }
                if (transitArray[t][i] == TransitLine.BIKEPATH){
                    tile[t][i] = BIKEPATH;
                }
                if (transitArray[t][i] == TransitLine.GASTRAIN){
                    tile[t][i] = GASTRAIN;
                }
                if (transitArray[t][i] == TransitLine.ELECTRICTRAIN){
                    tile[t][i] = ELECTRICTRAIN;
                }
                if (transitArray[t][i] == TransitLine.ORBITALTRAIN){
                    tile[t][i] = ORBITALTRAIN;
                }
            }
        }

      for(int x = 0; x < tile.length; x++){//updates tile with disastersArray.
          for(int y = 0; y < tile.length; y++){
              if(disastersArray[x][y] == Disasters.FLOODING){
                  tile[x][y] = FLOOD;
              }
              if(disastersArray[x][y] == Disasters.HURRICANES){
                  tile[x][y] = HURRICANE;
              }
              if(disastersArray[x][y] == Disasters.FIRES){
                  tile[x][y] = FIRE;
              }
              if(disastersArray[x][y] == Disasters.TORNADOES){
                  tile[x][y] = TORNADO;
              }
          }
      }

      for(int t = 0; t < tile.length; t++){ //Updates tile[][] with terrainArray for city hubs.
          for(int i= 0; i < tile.length; i++){
              if (terrainArray[t][i] == Terrain.FARM){
                  tile[t][i] = FARM;
              }
              if (terrainArray[t][i] == Terrain.INDUSTRIAL){
                  tile[t][i] = INDUSTRY;
              }
              if (terrainArray[t][i] == Terrain.DOWNTOWN){
                  tile[t][i] = DOWNTOWN;
              }
              if (terrainArray[t][i] == Terrain.HOME){
                  tile[t][i] = HOME;
              }
              if (terrainArray[t][i] == Terrain.SHOPPING){
                  tile[t][i] = SHOP;
              }
          }
      }

    }

    public boolean debitCost(int x, int y, TransitLine selection){//debits cost for builing transitLine.

        int a = 0;

        if (terrainArray[x][y] == Terrain.RIVER){
            a = selection.cost + selection.returnBridgeCost();
            if(a < bank) {
                bank = bank - a;
                return true;
            }
            if(bank < a) return false;
        }
        else {
            a = selection.cost;
            if(a < bank) {
                bank = bank - a;
                return true;
            }
            if(bank < a) return false;
        }
        return false;

    }

    public void insertTransit(int x, int y, TransitLine selection){//inserts transitline at selected coordinates.

        if (terrainChecker(x, y) && transitChecker(x, y, selection)){//uses terrainChecker() and transitChecker() to validate move;

            //uses debitCost() to verfy the cost can be taken from bank and completes debit if possible.
            //If debitCost returns false keeps play from occuring.
            if (debitCost(x, y, selection)){
                transitArray[x][y] = selection;//inserts correct TransitLine value into transitArray.
                updateTile();//updates map using updateTile();
            }

        } else System.out.println("Error");

    }

    int returnVal(int input){
        return input;
    }

    public void removeTransit(int x, int y){//removes transit and debits fee from bank.

        if(transitArray[x][y] != TransitLine.NULLTRANSIT){
            if(bank >= 5){
                transitArray[x][y] = TransitLine.NULLTRANSIT;
                bank = bank - 5;
                updateTile();
            }
        }

    }

    public int TurnValueFromTransit(){//tallies up the income produced this turn.

        int valueCounter = 0;
        int connectionCounter = 0;

        connectionCounter = HubConnectedCounter(HOME, ROAD, DOWNTOWN, SHOP, INDUSTRY, FARM);
        valueCounter = valueCounter + (connectionCounter * TransitLine.ROAD.value);
        connectionCounter = HubConnectedCounter(HOME, GASTRAIN, DOWNTOWN, SHOP, INDUSTRY, FARM);
        valueCounter = valueCounter + (connectionCounter * TransitLine.GASTRAIN.value);
        connectionCounter = HubConnectedCounter(HOME, ELECTRICTRAIN, DOWNTOWN, SHOP, INDUSTRY, FARM);
        valueCounter = valueCounter + (connectionCounter * TransitLine.ELECTRICTRAIN.value);
        connectionCounter = HubConnectedCounter(HOME, ORBITALTRAIN, DOWNTOWN, SHOP, INDUSTRY, FARM);
        valueCounter = valueCounter + (connectionCounter * TransitLine.ORBITALTRAIN.value);
        connectionCounter = HubConnectedCounter(HOME, BIKEPATH, DOWNTOWN, SHOP, INDUSTRY, FARM);
        valueCounter = valueCounter + (connectionCounter * TransitLine.BIKEPATH.value);

        connectionCounter = HubConnectedCounter(DOWNTOWN, ROAD, HOME, SHOP, INDUSTRY, FARM);
        valueCounter = valueCounter + (connectionCounter * TransitLine.ROAD.value);
        connectionCounter = HubConnectedCounter(DOWNTOWN, GASTRAIN, HOME, SHOP, INDUSTRY, FARM);
        valueCounter = valueCounter + (connectionCounter * TransitLine.GASTRAIN.value);
        connectionCounter = HubConnectedCounter(DOWNTOWN, ELECTRICTRAIN, HOME, SHOP, INDUSTRY, FARM);
        valueCounter = valueCounter + (connectionCounter * TransitLine.ELECTRICTRAIN.value);
        connectionCounter = HubConnectedCounter(DOWNTOWN, ORBITALTRAIN, HOME, SHOP, INDUSTRY, FARM);
        valueCounter = valueCounter + (connectionCounter * TransitLine.ORBITALTRAIN.value);
        connectionCounter = HubConnectedCounter(DOWNTOWN, BIKEPATH, HOME, SHOP, INDUSTRY, FARM);
        valueCounter = valueCounter + (connectionCounter * TransitLine.BIKEPATH.value);

        connectionCounter = HubConnectedCounter(SHOP, ROAD, DOWNTOWN, HOME, INDUSTRY, FARM);
        valueCounter = valueCounter + (connectionCounter * TransitLine.ROAD.value);
        connectionCounter = HubConnectedCounter(SHOP, GASTRAIN, DOWNTOWN, HOME, INDUSTRY, FARM);
        valueCounter = valueCounter + (connectionCounter * TransitLine.GASTRAIN.value);
        connectionCounter = HubConnectedCounter(SHOP, ELECTRICTRAIN, DOWNTOWN, HOME, INDUSTRY, FARM);
        valueCounter = valueCounter + (connectionCounter * TransitLine.ELECTRICTRAIN.value);
        connectionCounter = HubConnectedCounter(SHOP, ORBITALTRAIN, DOWNTOWN, HOME, INDUSTRY, FARM);
        valueCounter = valueCounter + (connectionCounter * TransitLine.ORBITALTRAIN.value);
        connectionCounter = HubConnectedCounter(SHOP, BIKEPATH, DOWNTOWN, HOME, INDUSTRY, FARM);
        valueCounter = valueCounter + (connectionCounter * TransitLine.BIKEPATH.value);

        connectionCounter = HubConnectedCounter(INDUSTRY, ROAD, DOWNTOWN, SHOP, HOME, FARM);
        connectionCounter = (connectionCounter * TransitLine.ROAD.value);
        connectionCounter = HubConnectedCounter(INDUSTRY, GASTRAIN, DOWNTOWN, SHOP, HOME, FARM);
        valueCounter = valueCounter + (connectionCounter * TransitLine.GASTRAIN.value);
        connectionCounter = HubConnectedCounter(INDUSTRY, ELECTRICTRAIN, DOWNTOWN, SHOP, HOME, FARM);
        valueCounter = valueCounter + (connectionCounter * TransitLine.ELECTRICTRAIN.value);
        connectionCounter = HubConnectedCounter(INDUSTRY, ORBITALTRAIN, DOWNTOWN, SHOP, HOME, FARM);
        valueCounter = valueCounter + (connectionCounter * TransitLine.ORBITALTRAIN.value);
        connectionCounter = HubConnectedCounter(INDUSTRY, BIKEPATH, DOWNTOWN, SHOP, HOME, FARM);
        valueCounter = valueCounter + (connectionCounter * TransitLine.BIKEPATH.value);

        connectionCounter = HubConnectedCounter(FARM, ROAD, DOWNTOWN, SHOP, INDUSTRY, HOME);
        valueCounter = valueCounter + (connectionCounter * TransitLine.ROAD.value);
        connectionCounter = HubConnectedCounter(FARM, GASTRAIN, DOWNTOWN, SHOP, INDUSTRY, HOME);
        valueCounter = valueCounter + (connectionCounter * TransitLine.GASTRAIN.value);
        connectionCounter = HubConnectedCounter(FARM, ELECTRICTRAIN, DOWNTOWN, SHOP, INDUSTRY, HOME);
        valueCounter = valueCounter + (connectionCounter * TransitLine.ELECTRICTRAIN.value);
        connectionCounter = HubConnectedCounter(FARM, ORBITALTRAIN, DOWNTOWN, SHOP, INDUSTRY, HOME);
        valueCounter = valueCounter + (connectionCounter * TransitLine.ORBITALTRAIN.value);
        connectionCounter = HubConnectedCounter(FARM, BIKEPATH, DOWNTOWN, SHOP, INDUSTRY, HOME);
        valueCounter = valueCounter + (connectionCounter * TransitLine.BIKEPATH.value);

        valueCounter = valueCounter/2;

        double desertsubtract = 0;

        for(int a = 0; a < tile.length; a++){
            for(int b = 0; b < tile.length; b++){
                if(tile[a][b] == DESERT){
                    desertsubtract = desertsubtract + 0.5;
                }
            }
        }

        valueCounter = valueCounter - ((int)(desertsubtract));
        return valueCounter;

    }

    //Used for path detection to determine if specified starting city hub is connected to another hub.
    public boolean isHubConnected(String start, String transitType, String Loc1, String Loc2, String Loc3, String Loc4){

        int startX = 0;
        int startY = 0;
        boolean isX1Connected = false;
        boolean isX2Connected = false;
        boolean isY1Connected = false;
        boolean isY2Connected = false;
        int connections = 0;

        for(int a = 0; a < tile.length; a++){
            for(int b = 0; b < tile.length; b++){
                if (tile[a][b] == start){
                    startX = a;
                    startY = b;
                }
            }
        }

        if(isXConnected(startX, startY, 1, transitType, Loc1, Loc2, Loc3, Loc4)){
            isX1Connected = true;
            connections++;
        }
        if(isXConnected(startX, startY, -1, transitType, Loc1, Loc2, Loc3, Loc4)){
            isX2Connected = true;
            connections++;
        }
        if(isYConnected(startX, startY, 1, transitType, Loc1, Loc2, Loc3, Loc4)){
            isY1Connected = true;
            connections++;
        }
        if(isYConnected(startX, startY, -1, transitType, Loc1, Loc2, Loc3, Loc4)){
            isY2Connected = true;
            connections++;
        }

        if(connections > 0){
            return true;
        }
        return false;

    }

    //returns the number of connections from specified starting city hub.
    public int HubConnectedCounter(String start, String transitType, String Loc1, String Loc2, String Loc3, String Loc4){

        int startX = 0;
        int startY = 0;
        boolean isX1Connected = false;
        boolean isX2Connected = false;
        boolean isY1Connected = false;
        boolean isY2Connected = false;
        int connections = 0;

        for(int a = 0; a < tile.length; a++){
            for(int b = 0; b < tile.length; b++){
                if (tile[a][b] == start){
                    startX = a;
                    startY = b;
                }
            }
        }

        if(isXConnected(startX, startY, 1, transitType, Loc1, Loc2, Loc3, Loc4)){
            isX1Connected = true;
            connections++;
        }
        if(isXConnected(startX, startY, -1, transitType, Loc1, Loc2, Loc3, Loc4)){
            isX2Connected = true;
            connections++;
        }
        if(isYConnected(startX, startY, 1, transitType, Loc1, Loc2, Loc3, Loc4)){
            isY1Connected = true;
            connections++;
        }
        if(isYConnected(startX, startY, -1, transitType, Loc1, Loc2, Loc3, Loc4)){
            isY2Connected = true;
            connections++;
        }

        return connections;

    }

    //takes in starting coordinates(x and y), negative or positive direction from start along x axis(int dir),
    //takes in type of transitline to look for - String transitType;
    //Loc1 - 4 are hold what city hub we are looking for connections to.
    public boolean isXConnected(int startX, int startY, int dir, String transitType, String Loc1, String Loc2, String Loc3, String Loc4){

        //checks to see if transitType selected starts at specified coordinate;
        try{
            if(tile[startX + dir][startY] == transitType){

                //Sets coordinate values being used currently for search;
                int currentX = (returnVal(startX) + dir);
                int currentY = returnVal(startY);

                //Keeps track of coordinate values used previously in search;
                int previousX = returnVal(startX);
                int previousY = returnVal(startY);

                int iteration = 0;//keeps track of iterations through loop to avoid endless looping issue;

                FindPath:{
                    do{
                        TransitSearchBlock:{//changes current and previous coordinates if transitline path continues.

                            try{
                                if(tile[currentX + 1][currentY] == transitType){
                                    if((currentX + 1) != previousX){
                                        previousX = returnVal(currentX);
                                        previousY = returnVal(currentY);
                                        currentX = currentX + 1;
                                        break TransitSearchBlock;
                                    }
                                }
                            }catch(Exception e){
                            }

                            try{
                                if(tile[currentX - 1][currentY] == transitType){
                                    if((currentX - 1) != previousX){
                                        previousX = returnVal(currentX);
                                        previousY = returnVal(currentY);
                                        currentX = currentX - 1;
                                        break TransitSearchBlock;
                                    }
                                }
                            }catch(Exception e){
                            }

                            try{
                                if(tile[currentX][currentY + 1] == transitType){
                                    if((currentY + 1) != previousY){
                                        previousX = returnVal(currentX);
                                        previousY = returnVal(currentY);
                                        currentY = currentY + 1;
                                        break TransitSearchBlock;
                                    }
                                }
                            }catch(Exception e){
                            }

                            try{
                                if(tile[currentX][currentY - 1] == transitType){
                                    if((currentY - 1) != previousY){
                                        previousX = returnVal(currentX);
                                        previousY = returnVal(currentY);
                                        currentY = currentY - 1;
                                        break TransitSearchBlock;
                                    }
                                }
                            }catch(Exception e){
                            }

                        }//closes TransitSearchBlock.

                        IsConnectedBlock:{//returns true if transitline is connected to city hub.

                            //Returns true if transitline is connected at X+1 direction;
                            try{
                                if(tile[currentX + 1][currentY] == Loc1){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX + 1][currentY] == Loc2){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX + 1][currentY] == Loc3){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX + 1][currentY] == Loc4){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            //returns true if transitline is connected at X-1 direction;
                            try{
                                if(tile[currentX - 1][currentY] == Loc1){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX - 1][currentY] == Loc2){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX - 1][currentY] == Loc3){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX - 1][currentY] == Loc4){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            //returns true if transitline is connected at Y+1 direction;
                            try{
                                if(tile[currentX][currentY + 1] == Loc1){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX][currentY + 1] == Loc2){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX][currentY + 1] == Loc3){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX][currentY + 1] == Loc4){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            //returns true if transitline is connected at Y-1 direction;
                            try{
                                if(tile[currentX][currentY - 1] == Loc1){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX][currentY - 1] == Loc2){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX][currentY - 1] == Loc3){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX][currentY - 1] == Loc4){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                        }//closes IsConnectedBlock.

                        iteration++;

                    }while (iteration <= 15);
                }

                if (iteration == 15){
                return false;
                }
            }

            return false;

        }catch(Exception e){
        }

        return false;

    }

    //determines if specified city hub is connected in Y + 1 or Y - 1 directions.
    public boolean isYConnected(int startX, int startY, int dir, String transitType, String Loc1, String Loc2, String Loc3, String Loc4){

        //checks to see if transitType selected starts at specified coordinate;
        try{
            if(tile[startX][startY + dir] == transitType){

                //Sets coordinate values being used currently for search;
                int currentX = returnVal(startX);
                int currentY = (returnVal(startY) + dir);

                //Keeps track of coordinate values used previously in search;
                int previousX = returnVal(startX);
                int previousY = returnVal(startY);

                int iteration = 0;//keeps track of iterations through loop to avoid endless looping issue;

                FindPath:{
                    do{
                        TransitSearchBlock:{

                            try{
                                if(tile[currentX + 1][currentY] == transitType){
                                    if((currentX + 1) != previousX){
                                        previousX = returnVal(currentX);
                                        previousY = returnVal(currentY);
                                        currentX = currentX + 1;
                                        break TransitSearchBlock;
                                    }
                                }
                            }catch(Exception e){
                            }

                            try{
                                if(tile[currentX - 1][currentY] == transitType){
                                    if((currentX - 1) != previousX){
                                        previousX = returnVal(currentX);
                                        previousY = returnVal(currentY);
                                        currentX = currentX - 1;
                                        break TransitSearchBlock;
                                    }
                                }
                            }catch(Exception e){
                            }

                            try{
                                if(tile[currentX][currentY + 1] == transitType){
                                    if((currentY + 1) != previousY){
                                        previousX = returnVal(currentX);
                                        previousY = returnVal(currentY);
                                        currentY = currentY + 1;
                                        break TransitSearchBlock;
                                    }
                                }
                            }catch(Exception e){
                            }

                            try{
                                if(tile[currentX][currentY - 1] == transitType){
                                    if((currentY - 1) != previousY){
                                        previousX = returnVal(currentX);
                                        previousY = returnVal(currentY);
                                        currentY = currentY - 1;
                                        break TransitSearchBlock;
                                    }
                                }
                            }catch(Exception e){
                            }

                        }

                        IsConnectedBlock:{
                            //Returns true if transitline is connected at X+1 direction;
                            try{
                                if(tile[currentX + 1][currentY] == Loc1){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX + 1][currentY] == Loc2){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX + 1][currentY] == Loc3){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX + 1][currentY] == Loc4){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            //returns true if transitline is connected at X-1 direction;
                            try{
                                if(tile[currentX - 1][currentY] == Loc1){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX - 1][currentY] == Loc2){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX - 1][currentY] == Loc3){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX - 1][currentY] == Loc4){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            //returns true if transitline is connected at Y+1 direction;
                            try{
                                if(tile[currentX][currentY + 1] == Loc1){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX][currentY + 1] == Loc2){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX][currentY + 1] == Loc3){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX][currentY + 1] == Loc4){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            //returns true if transitline is connected at Y-1 direction;
                            try{
                                if(tile[currentX][currentY - 1] == Loc1){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX][currentY - 1] == Loc2){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX][currentY - 1] == Loc3){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX][currentY - 1] == Loc4){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                        }

                        iteration++;

                    }while (iteration <= 15);
                }

                if (iteration == 15){
                    return false;
                }

            }

            return false;
        }catch(Exception e){
        }
        return false;
    }


    public void resetDisasterArray(){//Sets disastersArray to null to ensure that disaster display only lasts one turn.

        for (int a = 0; a < disastersArray.length; a++){
            for (int b = 0; b < disastersArray.length; b++){
                disastersArray[a][b] = Disasters.NULLDISASTER;
            }
        }
        updateTile();
    }


    public void disasterStrike(int totalCo2) {//cycles through array and calls runDisaster(totalCo2);

        //RIVER - FLOODING
        if(turnCounter % 2 == 0){

            for(int i = 0; i < terrainArray.length; i++){
                for(int a = 0; a < terrainArray.length; a++){

                    if(terrainArray[i][a] == Terrain.RIVER){

                        int disasterX = i;
                        int disasterY = a;
                        int disasterSize = Disasters.FLOODING.runDisaster(totalCo2);

                        if(disasterSize >= 1){//LEVEL 1

                            try {
                                transitArray[disasterX + 1][disasterY] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX + 1][disasterY] = Disasters.FLOODING;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX - 1][disasterY] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX - 1][disasterY] = Disasters.FLOODING;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX][disasterY + 1] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX][disasterY + 1] = Disasters.FLOODING;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX][disasterY - 1] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX][disasterY - 1] = Disasters.FLOODING;
                            }catch (Exception e){
                            }

                        }

                        if(disasterSize >= 2){//LEVEL 2

                            try {
                                transitArray[disasterX + 1][disasterY] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX + 1][disasterY] = Disasters.FLOODING;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX - 1][disasterY] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX - 1][disasterY] = Disasters.FLOODING;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX][disasterY + 1] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX][disasterY + 1] = Disasters.FLOODING;
                            }catch (Exception e){
                            }
                            try {
                                transitArray[disasterX][disasterY - 1] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX][disasterY - 1] = Disasters.FLOODING;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX - 1][disasterY - 1] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX - 1][disasterY - 1] = Disasters.FLOODING;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX + 1][disasterY + 1] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX + 1][disasterY + 1] = Disasters.FLOODING;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX + 1][disasterY - 1] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX + 1][disasterY - 1] = Disasters.FLOODING;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX - 1][disasterY + 1] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX - 1][disasterY + 1] = Disasters.FLOODING;
                            }catch (Exception e){
                            }
                        }
                    }
                }
            }
        }

        //OCEAN - HURRICANE
        if (turnCounter % 4 == 0){

            for(int i = 0; i < terrainArray.length; i++){
                for(int a = 0; a < terrainArray.length; a++){

                    if(terrainArray[i][a] == Terrain.OCEAN){

                        int disasterX = i;
                        int disasterY = a;
                        int disasterSize = Disasters.HURRICANES.runDisaster(totalCo2);

                        if(disasterSize >= 1){//LEVEL 1

                            try {
                                transitArray[disasterX + 1][disasterY] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX + 1][disasterY] = Disasters.HURRICANES;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX - 1][disasterY] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX - 1][disasterY] = Disasters.HURRICANES;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX][disasterY + 1] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX][disasterY + 1] = Disasters.HURRICANES;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX][disasterY - 1] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX][disasterY - 1] = Disasters.HURRICANES;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX - 1][disasterY - 1] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX - 1][disasterY - 1] = Disasters.HURRICANES;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX + 1][disasterY + 1] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX + 1][disasterY + 1] = Disasters.HURRICANES;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX + 1][disasterY - 1] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX + 1][disasterY - 1] = Disasters.HURRICANES;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX - 1][disasterY + 1] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX - 1][disasterY + 1] = Disasters.HURRICANES;
                            }catch (Exception e){
                            }

                        }

                        if(disasterSize >= 2){//LEVEL 2

                            try {
                                transitArray[disasterX + 2][disasterY] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX + 2][disasterY] = Disasters.HURRICANES;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX - 2][disasterY] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX - 2][disasterY] = Disasters.HURRICANES;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX][disasterY + 2] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX][disasterY + 2] = Disasters.HURRICANES;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX][disasterY - 2] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX][disasterY - 2] = Disasters.HURRICANES;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX - 2][disasterY - 2] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX - 2][disasterY - 2] = Disasters.HURRICANES;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX + 2][disasterY + 2] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX + 2][disasterY + 2] = Disasters.HURRICANES;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX + 2][disasterY - 2] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX + 2][disasterY - 2] = Disasters.HURRICANES;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX - 2][disasterY + 2] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX - 2][disasterY + 2] = Disasters.HURRICANES;
                            }catch (Exception e){
                            }
                        }
                    }
                }
            }
        }

          //DESERT - DESERTIFICATION
          if(turnCounter % 2 == 0){

              for(int i = 0; i < terrainArray.length; i++){
                  for(int a = 0; a < terrainArray.length; a++){

                      if(terrainArray[i][a] == Terrain.DESERT){

                          int disasterX = i;
                          int disasterY = a;
                          int disasterSize = Disasters.DESERTIFICATION.runDisaster(totalCo2);

                          if(disasterSize >= 1){//LEVEL 1
                              try {
                                  if(terrainArray[disasterX + 1][disasterY] == Terrain.GRASS){
                                      terrainArray[disasterX + 1][disasterY] = Terrain.DESERT;
                                  }
                              }catch (Exception e){
                              }

                              try {
                                  if(terrainArray[disasterX - 1][disasterY] == Terrain.GRASS){
                                      terrainArray[disasterX - 1][disasterY] = Terrain.DESERT;
                                  }
                              }catch (Exception e){
                              }

                              try {
                                  if(terrainArray[disasterX][disasterY + 1] == Terrain.GRASS){
                                      terrainArray[disasterX][disasterY + 1] = Terrain.DESERT;
                                  }
                              }catch (Exception e){
                              }

                              try {
                                  if(terrainArray[disasterX][disasterY - 1] == Terrain.GRASS){
                                      terrainArray[disasterX][disasterY - 1] = Terrain.DESERT;
                                  }
                              }catch (Exception e){
                              }

                        }

                        if(disasterSize >= 2){//LEVEL 2
                            try {
                                if(terrainArray[disasterX - 1][disasterY - 1] == Terrain.GRASS){
                                    terrainArray[disasterX - 1][disasterY - 1] = Terrain.DESERT;
                                }
                            }catch (Exception e){
                            }

                            try {
                                if(terrainArray[disasterX + 1][disasterY + 1] == Terrain.GRASS){
                                    terrainArray[disasterX + 1][disasterY + 1] = Terrain.DESERT;
                                }
                            }catch (Exception e){
                            }

                            try {
                                if(terrainArray[disasterX + 1][disasterY - 1] == Terrain.GRASS){
                                    terrainArray[disasterX + 1][disasterY - 1] = Terrain.DESERT;
                                }
                            }catch (Exception e){
                            }

                            try {
                                if(terrainArray[disasterX - 1][disasterY + 1] == Terrain.GRASS){
                                    terrainArray[disasterX - 1][disasterY + 1] = Terrain.DESERT;
                                }
                            }catch (Exception e){
                            }

                        }
                    }
                }
            }
        }

        updateTile();

    }//Ends disasterStrike.

    public void incrementCo2(){//Adds co2 created this turn to total Co2.

        int turnCo2 = 0;

        for(int a = 0; a < tile.length; a++){
            for(int b = 0; b < tile.length; b++){
                if (transitArray[a][b] == TransitLine.ROAD){
                    turnCo2 = turnCo2 + TransitLine.ROAD.co2;
                }
                if(transitArray[a][b] == TransitLine.BIKEPATH){
                    turnCo2 = turnCo2 + TransitLine.BIKEPATH.co2;
                }
                if(transitArray[a][b] == TransitLine.GASTRAIN){
                    turnCo2 = turnCo2 + TransitLine.GASTRAIN.co2;
                }
                if(transitArray[a][b] == TransitLine.ELECTRICTRAIN){
                    turnCo2 = turnCo2 + TransitLine.ELECTRICTRAIN.co2;
                }
                if(transitArray[a][b] == TransitLine.ORBITALTRAIN){
                    turnCo2 = turnCo2 + TransitLine.ORBITALTRAIN.co2;
                }
            }
        }

        totalCo2 = totalCo2 + turnCo2;
    }

    public boolean winCondition(){

        /*
        * Tests to see if win condition is met by determining if their are connections
        * leading from home to all other city hubs. Connections can run through other
        * hubs before reaching final destination.
        */

        //Booleans keep track of immediate connections between hubs.
        boolean HomeToDowntown = false;
        boolean HomeToShopping = false;
        boolean HomeToFarm = false;
        boolean HomeToIndustrial = false;
        boolean DowntownToHome = false;
        boolean DowntownToShopping = false;
        boolean DowntownToFarm = false;
        boolean DowntownToIndustrial = false;
        boolean ShoppingToHome = false;
        boolean ShoppingToFarm = false;
        boolean ShoppingToIndustrial = false;
        boolean ShoppingToDowntown = false;
        boolean FarmToDowntown = false;
        boolean FarmToHome = false;
        boolean FarmToShopping = false;
        boolean FarmToIndustrial = false;
        boolean IndustrialToHome = false;
        boolean IndustrialToShopping = false;
        boolean IndustrialToFarm = false;
        boolean IndustrialToDowntown = false;

        //Checks to see if home is immediately connected to downtown
        if(isHubConnected(HOME, ROAD, DOWNTOWN, NULL, NULL, NULL)){
            HomeToDowntown = true;
        }
        if(isHubConnected(HOME, ELECTRICTRAIN, DOWNTOWN, NULL, NULL, NULL)){
            HomeToDowntown = true;
        }
        if(isHubConnected(HOME, GASTRAIN, DOWNTOWN, NULL, NULL, NULL)){
            HomeToDowntown = true;
        }
        if(isHubConnected(HOME, ORBITALTRAIN, DOWNTOWN, NULL, NULL, NULL)){
            HomeToDowntown = true;
        }
        if(isHubConnected(HOME, BIKEPATH, DOWNTOWN, NULL, NULL, NULL)){
            HomeToDowntown = true;
        }

        //Checks to see if home is immediately connected to shopping.
        if(isHubConnected(HOME, ROAD, SHOP, NULL, NULL, NULL)){
            HomeToShopping = true;
        }
        if(isHubConnected(HOME, ELECTRICTRAIN, SHOP, NULL, NULL, NULL)){
            HomeToShopping = true;
        }
        if(isHubConnected(HOME, GASTRAIN, SHOP, NULL, NULL, NULL)){
            HomeToShopping = true;
        }
        if(isHubConnected(HOME, ORBITALTRAIN, SHOP, NULL, NULL, NULL)){
            HomeToShopping = true;
        }
        if(isHubConnected(HOME, BIKEPATH, SHOP, NULL, NULL, NULL)){
            HomeToShopping = true;
        }

        //Checks ot see if home is immediately connected to farm.
        if(isHubConnected(HOME, ROAD, FARM, NULL, NULL, NULL)){
            HomeToFarm = true;
        }
        if(isHubConnected(HOME, ELECTRICTRAIN, FARM, NULL, NULL, NULL)){
            HomeToFarm = true;
        }
        if(isHubConnected(HOME, GASTRAIN, FARM, NULL, NULL, NULL)){
            HomeToFarm = true;
        }
        if(isHubConnected(HOME, ORBITALTRAIN, FARM, NULL, NULL, NULL)){
            HomeToFarm = true;
        }
        if(isHubConnected(HOME, BIKEPATH, FARM, NULL, NULL, NULL)){
            HomeToFarm = true;
        }

        //Checks to see if home is immediately connected to industry.
        if(isHubConnected(HOME, ROAD, INDUSTRY, NULL, NULL, NULL)){
            HomeToIndustrial = true;
        }
        if(isHubConnected(HOME, ELECTRICTRAIN, INDUSTRY, NULL, NULL, NULL)){
            HomeToIndustrial = true;
        }
        if(isHubConnected(HOME, GASTRAIN, INDUSTRY, NULL, NULL, NULL)){
            HomeToIndustrial = true;
        }
        if(isHubConnected(HOME, ORBITALTRAIN, INDUSTRY, NULL, NULL, NULL)){
            HomeToIndustrial = true;
        }
        if(isHubConnected(HOME, BIKEPATH, INDUSTRY, NULL, NULL, NULL)){
            HomeToIndustrial = true;
        }

        //Checks to see if downtown is immediately connected to home.
        if(isHubConnected(DOWNTOWN, ROAD, HOME, NULL, NULL, NULL)){
            DowntownToHome = true;
        }
        if(isHubConnected(DOWNTOWN, ELECTRICTRAIN, HOME, NULL, NULL, NULL)){
            DowntownToHome = true;
        }
        if(isHubConnected(DOWNTOWN, GASTRAIN, HOME, NULL, NULL, NULL)){
            DowntownToHome = true;
        }
        if(isHubConnected(DOWNTOWN, ORBITALTRAIN, HOME, NULL, NULL, NULL)){
            DowntownToHome = true;
        }
        if(isHubConnected(DOWNTOWN, BIKEPATH, HOME, NULL, NULL, NULL)){
            DowntownToHome = true;
        }

        //Checks to see if downtown is immediately connected to shopping.
        if(isHubConnected(DOWNTOWN, ROAD, SHOP, NULL, NULL, NULL)){
            DowntownToShopping = true;
        }
        if(isHubConnected(DOWNTOWN, ELECTRICTRAIN, SHOP, NULL, NULL, NULL)){
            DowntownToShopping = true;
        }
        if(isHubConnected(DOWNTOWN, GASTRAIN, SHOP, NULL, NULL, NULL)){
            DowntownToShopping = true;
        }
        if(isHubConnected(DOWNTOWN, ORBITALTRAIN, SHOP, NULL, NULL, NULL)){
            DowntownToShopping = true;
        }
        if(isHubConnected(DOWNTOWN, BIKEPATH, SHOP, NULL, NULL, NULL)){
            DowntownToShopping = true;
        }

        //Checks to see if downtown is immediately connected to farm.
        if(isHubConnected(DOWNTOWN, ROAD, FARM, NULL, NULL, NULL)){
            DowntownToFarm = true;
        }
        if(isHubConnected(DOWNTOWN, ELECTRICTRAIN, FARM, NULL, NULL, NULL)){
            DowntownToFarm = true;
        }
        if(isHubConnected(DOWNTOWN, GASTRAIN, FARM, NULL, NULL, NULL)){
            DowntownToFarm = true;
        }
        if(isHubConnected(DOWNTOWN, ORBITALTRAIN, FARM, NULL, NULL, NULL)){
            DowntownToFarm = true;
        }
        if(isHubConnected(DOWNTOWN, BIKEPATH, FARM, NULL, NULL, NULL)){
            DowntownToFarm = true;
        }

        //Checks to see if downtown is immediately connected to industry.
        if(isHubConnected(DOWNTOWN, ROAD, INDUSTRY, NULL, NULL, NULL)){
            DowntownToIndustrial = true;
        }
        if(isHubConnected(DOWNTOWN, ELECTRICTRAIN, INDUSTRY, NULL, NULL, NULL)){
            DowntownToIndustrial = true;
        }
        if(isHubConnected(DOWNTOWN, GASTRAIN, INDUSTRY, NULL, NULL, NULL)){
            DowntownToIndustrial = true;
        }
        if(isHubConnected(DOWNTOWN, ORBITALTRAIN, INDUSTRY, NULL, NULL, NULL)){
            DowntownToIndustrial = true;
        }
        if(isHubConnected(DOWNTOWN, BIKEPATH, INDUSTRY, NULL, NULL, NULL)){
            DowntownToIndustrial = true;
        }

        //Checks to see if shopping is immediately connected to home.
        if(isHubConnected(SHOP, ROAD, HOME, NULL, NULL, NULL)){
            ShoppingToHome = true;
        }
        if(isHubConnected(SHOP, ELECTRICTRAIN, HOME, NULL, NULL, NULL)){
            ShoppingToHome = true;
        }
        if(isHubConnected(SHOP, GASTRAIN, HOME, NULL, NULL, NULL)){
            ShoppingToHome = true;
        }
        if(isHubConnected(SHOP, ORBITALTRAIN, HOME, NULL, NULL, NULL)){
            ShoppingToHome = true;
        }
        if(isHubConnected(SHOP, BIKEPATH, HOME, NULL, NULL, NULL)){
            ShoppingToHome = true;
        }

        //Checks to see if shopping is immediately connected to farm.
        if(isHubConnected(SHOP, ROAD, FARM, NULL, NULL, NULL)){
            ShoppingToFarm = true;
        }
        if(isHubConnected(SHOP, ELECTRICTRAIN, FARM, NULL, NULL, NULL)){
            ShoppingToFarm = true;
        }
        if(isHubConnected(SHOP, GASTRAIN, FARM, NULL, NULL, NULL)){
            ShoppingToFarm = true;
        }
        if(isHubConnected(SHOP, ORBITALTRAIN, FARM, NULL, NULL, NULL)){
            ShoppingToFarm = true;
        }
        if(isHubConnected(SHOP, BIKEPATH, FARM, NULL, NULL, NULL)){
            ShoppingToFarm = true;
        }

        //Checks to see if Shopping is connected to industry.
        if(isHubConnected(SHOP, ROAD, INDUSTRY, NULL, NULL, NULL)){
            ShoppingToIndustrial = true;
        }
        if(isHubConnected(SHOP, ELECTRICTRAIN, INDUSTRY, NULL, NULL, NULL)){
            ShoppingToIndustrial = true;
        }
        if(isHubConnected(SHOP, GASTRAIN, INDUSTRY, NULL, NULL, NULL)){
            ShoppingToIndustrial = true;
        }
        if(isHubConnected(SHOP, ORBITALTRAIN, INDUSTRY, NULL, NULL, NULL)){
            ShoppingToIndustrial = true;
        }
        if(isHubConnected(SHOP, BIKEPATH, INDUSTRY, NULL, NULL, NULL)){
            ShoppingToIndustrial = true;
        }

        //Checks to see if shopping is connected to Downtown.
        if(isHubConnected(SHOP, ROAD, DOWNTOWN, NULL, NULL, NULL)){
            ShoppingToDowntown = true;
        }
        if(isHubConnected(SHOP, ELECTRICTRAIN, DOWNTOWN, NULL, NULL, NULL)){
            ShoppingToDowntown = true;
        }
        if(isHubConnected(SHOP, GASTRAIN, DOWNTOWN, NULL, NULL, NULL)){
            ShoppingToDowntown = true;
        }
        if(isHubConnected(SHOP, ORBITALTRAIN, DOWNTOWN, NULL, NULL, NULL)){
            ShoppingToDowntown = true;
        }
        if(isHubConnected(SHOP, BIKEPATH, DOWNTOWN, NULL, NULL, NULL)){
            ShoppingToDowntown = true;
        }

        //Checks ot see if farm is connected to downtown.
        if(isHubConnected(FARM, ROAD, DOWNTOWN, NULL, NULL, NULL)){
            FarmToDowntown = true;
        }
        if(isHubConnected(FARM, ELECTRICTRAIN, DOWNTOWN, NULL, NULL, NULL)){
            FarmToDowntown = true;
        }
        if(isHubConnected(FARM, GASTRAIN, DOWNTOWN, NULL, NULL, NULL)){
            FarmToDowntown = true;
        }
        if(isHubConnected(FARM, ORBITALTRAIN, DOWNTOWN, NULL, NULL, NULL)){
            FarmToDowntown = true;
        }
        if(isHubConnected(FARM, BIKEPATH, DOWNTOWN, NULL, NULL, NULL)){
            FarmToDowntown = true;
        }

        //Checks to see if farm is connected to home.
        if(isHubConnected(FARM, ROAD, HOME, NULL, NULL, NULL)){
            FarmToHome = true;
        }
        if(isHubConnected(FARM, ELECTRICTRAIN, HOME, NULL, NULL, NULL)){
            FarmToHome = true;
        }
        if(isHubConnected(FARM, GASTRAIN, HOME, NULL, NULL, NULL)){
            FarmToHome = true;
        }
        if(isHubConnected(FARM, ORBITALTRAIN, HOME, NULL, NULL, NULL)){
            FarmToHome = true;
        }
        if(isHubConnected(FARM, BIKEPATH, HOME, NULL, NULL, NULL)){
            FarmToHome = true;
        }

        //Checks to see if farm is connected to shopping.
        if(isHubConnected(FARM, ROAD, SHOP, NULL, NULL, NULL)){
            FarmToShopping = true;
        }
        if(isHubConnected(FARM, ELECTRICTRAIN, SHOP, NULL, NULL, NULL)){
            FarmToShopping = true;
        }
        if(isHubConnected(FARM, GASTRAIN, SHOP, NULL, NULL, NULL)){
            FarmToShopping = true;
        }
        if(isHubConnected(FARM, ORBITALTRAIN, SHOP, NULL, NULL, NULL)){
            FarmToShopping = true;
        }
        if(isHubConnected(FARM, BIKEPATH, SHOP, NULL, NULL, NULL)){
            FarmToShopping = true;
        }

        //Checks to see if farm is connected to industry.
        if(isHubConnected(FARM, ROAD, INDUSTRY, NULL, NULL, NULL)){
            FarmToIndustrial = true;
        }
        if(isHubConnected(FARM, ELECTRICTRAIN, INDUSTRY, NULL, NULL, NULL)){
            FarmToIndustrial = true;
        }
        if(isHubConnected(FARM, GASTRAIN, INDUSTRY, NULL, NULL, NULL)){
            FarmToIndustrial = true;
        }
        if(isHubConnected(FARM, ORBITALTRAIN, INDUSTRY, NULL, NULL, NULL)){
            FarmToIndustrial = true;
        }
        if(isHubConnected(FARM, BIKEPATH, INDUSTRY, NULL, NULL, NULL)){
            FarmToIndustrial = true;
        }

        //Checks ot see if industry is connected to home.
        if(isHubConnected(INDUSTRY, ROAD, HOME, NULL, NULL, NULL)){
            IndustrialToHome = true;
        }
        if(isHubConnected(INDUSTRY, ELECTRICTRAIN, HOME, NULL, NULL, NULL)){
            IndustrialToHome = true;
        }
        if(isHubConnected(INDUSTRY, GASTRAIN, HOME, NULL, NULL, NULL)){
            IndustrialToHome = true;
        }
        if(isHubConnected(INDUSTRY, ORBITALTRAIN, HOME, NULL, NULL, NULL)){
            IndustrialToHome = true;
        }
        if(isHubConnected(INDUSTRY, BIKEPATH, HOME, NULL, NULL, NULL)){
            IndustrialToHome = true;
        }

        //Checks to see if industry is connected to shopping.
        if(isHubConnected(INDUSTRY, ROAD, SHOP, NULL, NULL, NULL)){
            IndustrialToShopping = true;
        }
        if(isHubConnected(INDUSTRY, ELECTRICTRAIN, SHOP, NULL, NULL, NULL)){
            IndustrialToShopping = true;
        }
        if(isHubConnected(INDUSTRY, GASTRAIN, SHOP, NULL, NULL, NULL)){
            IndustrialToShopping = true;
        }
        if(isHubConnected(INDUSTRY, ORBITALTRAIN, SHOP, NULL, NULL, NULL)){
            IndustrialToShopping = true;
        }
        if(isHubConnected(INDUSTRY, BIKEPATH, SHOP, NULL, NULL, NULL)){
            IndustrialToShopping = true;
        }

        //Checks to see if industry is connected to farm.
        if(isHubConnected(INDUSTRY, ROAD, FARM, NULL, NULL, NULL)){
            IndustrialToFarm = true;
        }
        if(isHubConnected(INDUSTRY, ELECTRICTRAIN, FARM, NULL, NULL, NULL)){
            IndustrialToFarm = true;
        }
        if(isHubConnected(INDUSTRY, GASTRAIN, FARM, NULL, NULL, NULL)){
            IndustrialToFarm = true;
        }
        if(isHubConnected(INDUSTRY, ORBITALTRAIN, FARM, NULL, NULL, NULL)){
            IndustrialToFarm = true;
        }
        if(isHubConnected(INDUSTRY, BIKEPATH, FARM, NULL, NULL, NULL)){
            IndustrialToFarm = true;
        }

        //Checks to see if industry is connected to downtown.
        if(isHubConnected(INDUSTRY, ROAD, DOWNTOWN, NULL, NULL, NULL)){
            IndustrialToDowntown = true;
        }
        if(isHubConnected(INDUSTRY, ELECTRICTRAIN, DOWNTOWN, NULL, NULL, NULL)){
            IndustrialToDowntown = true;
        }
        if(isHubConnected(INDUSTRY, GASTRAIN, DOWNTOWN, NULL, NULL, NULL)){
            IndustrialToDowntown = true;
        }
        if(isHubConnected(INDUSTRY, ORBITALTRAIN, DOWNTOWN, NULL, NULL, NULL)){
            IndustrialToDowntown = true;
        }
        if(isHubConnected(INDUSTRY, BIKEPATH, DOWNTOWN, NULL, NULL, NULL)){
            IndustrialToDowntown = true;
        }

        boolean Connect1 = false;//Home connects to downtown somehow
        boolean Connect2 = false;//Home connects to Farm somehow
        boolean Connect3 = false;//Home connects to Shopping somehow
        boolean Connect4 = false;//Home connects to Industrial somehow

        //Follows logical pathways to determine if home is connected to each of the other city hubs.
        //Uses booleans for immediate connections to build to larger boolen connections above.
        if(HomeToDowntown){
            Connect1 = true;

            if(DowntownToFarm){
                Connect2 = true;
                if(FarmToShopping){
                    Connect3 = true;
                    if(ShoppingToIndustrial){
                        Connect4 = true;
                    }
                }
                if(FarmToIndustrial){
                    Connect4 = true;
                    if(IndustrialToShopping){
                        Connect3 = true;
                    }
                }
            }
            if(DowntownToShopping){
                Connect3 = true;
                if(ShoppingToFarm){
                    Connect2 = true;
                    if(FarmToIndustrial){
                        Connect4 = true;
                    }
                }
                if(ShoppingToIndustrial){
                    Connect4 = true;
                    if(IndustrialToFarm){
                        Connect2 = true;
                    }
                }
            }
            if(DowntownToIndustrial){
                Connect4 = true;
                if(IndustrialToFarm){
                    Connect2 = true;
                    if(FarmToShopping){
                        Connect3 = true;
                    }
                }
                if(IndustrialToShopping){
                    Connect3 = true;
                    if(ShoppingToFarm){
                        Connect2 = true;
                    }
                }
            }
        }

        if(HomeToFarm){
            Connect2 = true;

            if(FarmToDowntown){
                Connect1 = true;
                if(DowntownToShopping){
                    Connect3 = true;
                    if(ShoppingToIndustrial){
                        Connect4 = true;
                    }
                }
                if(DowntownToIndustrial){
                    Connect4 = true;
                    if(IndustrialToShopping){
                        Connect3 = true;
                    }

                }
            }
            if(FarmToShopping){
                Connect3 = true;
                if(ShoppingToDowntown){
                    Connect1 = true;
                    if(DowntownToIndustrial){
                        Connect4 = true;
                    }
                }
                if(ShoppingToIndustrial){
                    Connect4 = true;
                    if(IndustrialToDowntown){
                        Connect1 = true;
                    }
                }
            }
            if(FarmToIndustrial){
                Connect4 = true;
                if(IndustrialToDowntown){
                    Connect1 = true;
                    if(DowntownToShopping){
                        Connect3 = true;
                    }
                }
                if(IndustrialToShopping){
                    Connect3 = true;
                    if(ShoppingToDowntown){
                        Connect1 = true;
                    }
                }
            }
        }

        if(HomeToShopping){
            Connect3 = true;

            if(ShoppingToFarm){
                Connect2 = true;
                if(FarmToDowntown){
                    Connect1 = true;
                    if(DowntownToIndustrial){
                        Connect4 = true;
                    }
                }
                if(FarmToIndustrial){
                    Connect4 = true;
                    if(IndustrialToDowntown){
                        Connect1 = true;
                    }
                }
            }
            if(ShoppingToIndustrial){
                Connect4 = true;
                if(IndustrialToFarm){
                    Connect2 = true;
                    if(FarmToDowntown){
                        Connect1 = true;
                    }
                }
                if(IndustrialToDowntown){
                    Connect1 = true;
                    if(DowntownToFarm){
                        Connect2 = true;
                    }
                }
            }
            if(ShoppingToDowntown){
                Connect1 = true;
                if(DowntownToFarm){
                    Connect2 = true;
                    if(FarmToIndustrial){
                        Connect4 = true;
                    }
                }
                if(DowntownToIndustrial){
                    Connect4 = true;
                    if(IndustrialToFarm){
                        Connect2 = true;
                    }
                }
            }
        }

        if(HomeToIndustrial){
            Connect4 = true;

            if(IndustrialToFarm){
                Connect2 = true;
                if(FarmToDowntown){
                    Connect1 = true;
                    if(DowntownToShopping){
                        Connect3 = true;
                    }
                }
                if(FarmToShopping){
                    Connect3 = true;
                    if(ShoppingToDowntown){
                        Connect1 = true;
                    }
                }
            }
            if(IndustrialToDowntown){
                Connect1 = true;
                if(DowntownToFarm){
                    Connect2 = true;
                    if(FarmToShopping){
                        Connect3 = true;
                    }
                }
                if(DowntownToShopping){
                    Connect3 = true;
                    if(ShoppingToFarm){
                        Connect2 = true;
                    }
                }
            }
            if(IndustrialToShopping){
                Connect3 = true;
                if(ShoppingToFarm){
                    Connect2 = true;
                    if(FarmToDowntown){
                        Connect1 = true;
                    }
                }
                if(ShoppingToDowntown){
                    Connect1 = true;
                    if(DowntownToFarm){
                        Connect2 = true;
                    }
                }
            }
        }

        if(Connect1 && Connect2 && Connect3 && Connect4){//if all hubs are connected.
            if(turnCounter == 16){//for win condition to be met turncounter must be at 16.
                return true;
            }
        }
        return false;
    }

    public String tileReturn(int x, int y){//returns tile at specified coordinate. Used for gui representation.
        return tile[x][y];
    }

    public int tileLength(){//returns length of map. Used for gui representation.
        return tile.length;
    }

}//ends map
