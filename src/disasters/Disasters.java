
package disasters;

/**
* This enum holds the constants for disasters used by all maps.
*/

public enum Disasters
{

    FLOODING(1,true),
    HURRICANES(2,false),
    DESERTIFICATION(3,false),
    FIRES(3,false),
    TORNADOES(1,false),
    NULLDISASTER(0,false);

    /**
    * Impede is not used in current setup. An option for future implementations.
    */

    public int size;
    public boolean impede;
    public int threshold;

    ///// CONSTRUCTION /////////////////////////////////////////////////////////

    Disasters(int Size, boolean Impede)
    {
        size = Size;
        impede = Impede;
    }

    ///// METHODS //////////////////////////////////////////////////////////////

    /**
    * Sets threshold for determining whether disaster runs based on map size.
    */

    public void setThreshold(int mapSize)
    {
        threshold = mapSize * mapSize;
    }

    /**
    * Determines whether disaster is run based on Co2 and random number generator.
    */

    boolean isDisaster(int co2)
    {

        if (co2 >= threshold)
        {
            double random = (double)(Math.random() * 100);
            double level = (((co2/100)*random));
            if(level > threshold)
            {
                return true;
            }
            return false;

        }

        if(co2 < threshold){
            double random = (double)(Math.random() * 100);
            double level = ((co2/20)*random);
            if(level > threshold)
            {
                return true;
            }
            return false;
        }
      return false;
    }

    /**
    * Determines size(range) of disaster based on Co2 and random number generator.
    */

    int getSize(int co2)
    {

        int random = (int)(Math.random() * 10);
        int num = (co2 * (random * size)/100);
        System.out.println("Size is " + num);
        return num;

    }

    /**
    * accessed from maps;
    * checks if disaster runs and returns size, if not returns 0.
    */

    public int runDisaster(int co2)
    {
        if(isDisaster(co2))
        {
            return size + getSize(co2);
        }
        else return 0;
    }
}
