package gui;

import javax.swing.*;

/**
* Instantiates gui for map and game controls.
*/

public class Gui
{

    public Gui()
    {
        /**
        * Main window of application
        */

        JFrame canvas = new MainGameGui("Transit-Game");

        canvas.setSize(1000, 700);

        canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas.setVisible(true);
    }

}
