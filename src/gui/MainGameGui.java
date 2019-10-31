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
* MainGameGui holds all game controls for navigating and playing game.
*/

public class MainGameGui extends JFrame{

    //// INSTANCE VARIABLES ////////////////////////////////////////////////////

    private MapInterpreter mapDisplay;
    private int selectedx;
    private int selectedy;
    private TransitLine selectedTransit;

    ///// CONSTRUCTION /////////////////////////////////////////////////////////

    public MainGameGui(String title)
    {
        super(title);

        mapDisplay = new MapInterpreter();

        setLayout(new FlowLayout());
        JPanel mainPanel = new JPanel();
        add(mainPanel);
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints mainConstraints = new GridBagConstraints();

        /**
        * Adds map graphics to Main JPanel.
        */

        mainConstraints.gridx = 1;
        mainConstraints.gridy = 0;
        mainPanel.add(mapDisplay, mainConstraints);

        /**
        * Buttons for navigating game board
        */

        JButton upButton = new JButton("UP");
        upButton.setPreferredSize(new Dimension(60, 20));
        JButton downButton = new JButton("DOWN");
        downButton.setPreferredSize(new Dimension(60, 20));
        JButton leftButton = new JButton("LEFT");
        leftButton.setPreferredSize(new Dimension(60, 20));
        JButton rightButton = new JButton("RIGHT");
        rightButton.setPreferredSize(new Dimension(60, 20));

        /**
        * Buttons for placing or removing transitline pieces.
        */

        JButton placeButton = new JButton("PLACE");
        JButton removeButton = new JButton("REMOVE");

        /**
        * Buttons for selecting which type of transitline to place.
        */

        JButton roadButton = new JButton("ROAD");
        JButton bikepathButton = new JButton("ROAD WITH BIKEPATH");
        JButton gasTrainButton = new JButton("GAS TRAIN");
        JButton electricTrainButton = new JButton("ELECTRIC TRAIN");
        JButton orbitalTrainButton = new JButton("ORBITAL TRAIN");

        JButton endTurnButton = new JButton("END TURN");

        /**
        * Creates ImageIcons for use in key.
        */

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

        /**
        * ButtonLayoutA for Directional Buttons.
        */

        JPanel buttonLayoutA = new JPanel();
        buttonLayoutA.setLayout(new GridBagLayout());
        mainConstraints.gridx = 0;
        mainConstraints.gridy = 1;
        mainPanel.add(buttonLayoutA, mainConstraints);
        GridBagConstraints constraintsA = new GridBagConstraints();

        constraintsA.gridx = 1;
        constraintsA.gridy = 0;
        buttonLayoutA.add(upButton, constraintsA);
        constraintsA.gridx = 1;
        constraintsA.gridy = 2;
        buttonLayoutA.add(downButton, constraintsA);
        constraintsA.gridx = 0;
        constraintsA.gridy = 1;
        buttonLayoutA.add(leftButton, constraintsA);
        constraintsA.gridx = 2;
        constraintsA.gridy = 1;
        buttonLayoutA.add(rightButton, constraintsA);

        /**
        * buttonLayoutB for place, remove, and end turn buttons.
        */

        JPanel buttonLayoutB = new JPanel();
        buttonLayoutB.setLayout(new GridBagLayout());
        mainConstraints.gridx = 1;
        mainConstraints.gridy = 1;
        mainPanel.add(buttonLayoutB, mainConstraints);
        GridBagConstraints constraintsB = new GridBagConstraints();

        constraintsB.gridx = 0;
        constraintsB.gridy = 0;
        buttonLayoutB.add(placeButton, constraintsB);
        placeButton.setPreferredSize(new Dimension(400, 20));
        constraintsB.gridx = 0;
        constraintsB.gridy = 1;
        buttonLayoutB.add(removeButton, constraintsB);
        removeButton.setPreferredSize(new Dimension(400, 20));
        constraintsB.gridx = 0;
        constraintsB.gridy = 2;
        buttonLayoutB.add(endTurnButton, constraintsB);
        endTurnButton.setPreferredSize(new Dimension(400, 20));

        /**
        * Creates JPanel for Key.
        */

        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setLayout(new GridBagLayout());
        GridBagConstraints infoPanelConstraints = new GridBagConstraints();
        infoPanelConstraints.anchor = GridBagConstraints.WEST;

        mainConstraints.gridx = 0;
        mainConstraints.gridy = 0;
        mainPanel.add(infoPanel, mainConstraints);

        JLabel roadKey = new JLabel(road);
        infoPanelConstraints.gridx = 0;
        infoPanelConstraints.gridy = 0;
        infoPanel.add(roadKey, infoPanelConstraints);

        infoPanelConstraints.gridx = 1;
        infoPanelConstraints.gridy = 0;
        roadButton.setPreferredSize(new Dimension(150, 40));
        infoPanel.add(roadButton, infoPanelConstraints);

        JLabel bikepathKey = new JLabel(bikepath);
        infoPanelConstraints.gridx = 0;
        infoPanelConstraints.gridy = 1;
        infoPanel.add(bikepathKey, infoPanelConstraints);

        infoPanelConstraints.gridx = 1;
        infoPanelConstraints.gridy = 1;
        bikepathButton.setPreferredSize(new Dimension(150, 40));
        infoPanel.add(bikepathButton, infoPanelConstraints);

        JLabel gastrainKey = new JLabel(gastrain);
        infoPanelConstraints.gridx = 0;
        infoPanelConstraints.gridy = 2;
        infoPanel.add(gastrainKey, infoPanelConstraints);

        infoPanelConstraints.gridx = 1;
        infoPanelConstraints.gridy = 2;
        gasTrainButton.setPreferredSize(new Dimension(150, 40));
        infoPanel.add(gasTrainButton, infoPanelConstraints);

        JLabel eltrainKey = new JLabel(electrictrain);
        infoPanelConstraints.gridx = 0;
        infoPanelConstraints.gridy = 3;
        infoPanel.add(eltrainKey, infoPanelConstraints);

        infoPanelConstraints.gridx = 1;
        infoPanelConstraints.gridy = 3;
        electricTrainButton.setPreferredSize(new Dimension(150, 40));
        infoPanel.add(electricTrainButton, infoPanelConstraints);

        JLabel orbitaltrainKey = new JLabel(orbitaltrain);
        infoPanelConstraints.gridx = 0;
        infoPanelConstraints.gridy = 4;
        infoPanel.add(orbitaltrainKey, infoPanelConstraints);

        infoPanelConstraints.gridx = 1;
        infoPanelConstraints.gridy = 4;
        orbitalTrainButton.setPreferredSize(new Dimension(150, 40));
        infoPanel.add(orbitalTrainButton, infoPanelConstraints);

        JLabel homeKey = new JLabel(home);
        infoPanelConstraints.gridx = 0;
        infoPanelConstraints.gridy = 5;
        infoPanel.add(homeKey, infoPanelConstraints);

        JLabel homeText = new JLabel(" Home ");
        infoPanelConstraints.gridx = 1;
        infoPanelConstraints.gridy = 5;
        infoPanel.add(homeText, infoPanelConstraints);

        JLabel shoppingKey = new JLabel(shopping);
        infoPanelConstraints.gridx = 0;
        infoPanelConstraints.gridy = 6;
        infoPanel.add(shoppingKey, infoPanelConstraints);

        JLabel shoppingText = new JLabel(" Shopping Center ");
        infoPanelConstraints.gridx = 1;
        infoPanelConstraints.gridy = 6;
        infoPanel.add(shoppingText, infoPanelConstraints);

        JLabel downtownKey = new JLabel(downtown);
        infoPanelConstraints.gridx = 0;
        infoPanelConstraints.gridy = 7;
        infoPanel.add(downtownKey, infoPanelConstraints);

        JLabel downtownText = new JLabel(" Downtown ");
        infoPanelConstraints.gridx = 1;
        infoPanelConstraints.gridy = 7;
        infoPanel.add(downtownText, infoPanelConstraints);

        JLabel industrialKey = new JLabel(industrial);
        infoPanelConstraints.gridx = 0;
        infoPanelConstraints.gridy = 8;
        infoPanel.add(industrialKey, infoPanelConstraints);

        JLabel industrialText = new JLabel(" Industrial Zone ");
        infoPanelConstraints.gridx = 1;
        infoPanelConstraints.gridy = 8;
        infoPanel.add(industrialText, infoPanelConstraints);

        JLabel farmKey = new JLabel(farm);
        infoPanelConstraints.gridx = 0;
        infoPanelConstraints.gridy = 9;
        infoPanel.add(farmKey, infoPanelConstraints);

        JLabel farmText = new JLabel(" Farmland ");
        infoPanelConstraints.gridx = 1;
        infoPanelConstraints.gridy = 9;
        infoPanel.add(farmText, infoPanelConstraints);

        /**
        * Adds behaviors to buttons
        */

        upButton.addActionListener(new UpAction());
        downButton.addActionListener(new DownAction());
        leftButton.addActionListener(new LeftAction());
        rightButton.addActionListener(new RightAction());

        placeButton.addActionListener(new PlaceAction());
        removeButton.addActionListener(new RemoveAction());

        roadButton.addActionListener(new RoadAction());
        bikepathButton.addActionListener(new BikeAction());
        gasTrainButton.addActionListener(new GasTrainAction());
        electricTrainButton.addActionListener(new ElectricTrainAction());
        orbitalTrainButton.addActionListener(new OrbitalTrainAction());
        endTurnButton.addActionListener(new EndTurnAction());
    }

    ///// ACTION LISTENERS /////////////////////////////////////////////////////

    class UpAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (selectedy >= 1)
            {
                selectedy = selectedy - 1;
                mapDisplay.setSelector(selectedx, selectedy);
            }
        }
    }

    class DownAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (selectedy < (mapDisplay.mapLength()-1))
            {
                selectedy = selectedy + 1;
                mapDisplay.setSelector(selectedx, selectedy);
            }
        }
    }

    class LeftAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (selectedx >= 1)
            {
                selectedx = selectedx - 1;
                mapDisplay.setSelector(selectedx, selectedy);
            }
        }
    }

    class RightAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (selectedx < (mapDisplay.mapLength()-1))
            {
               selectedx = selectedx + 1;
               mapDisplay.setSelector(selectedx, selectedy);
            }
        }
    }

    class PlaceAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
                mapDisplay.insert(selectedTransit);
        }
    }

    class RemoveAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
                mapDisplay.remove();
        }
    }

    class RoadAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
                selectedTransit = TransitLine.ROAD;
        }
    }

    class BikeAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
                selectedTransit = TransitLine.BIKEPATH;
        }
    }

    class GasTrainAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
                selectedTransit = TransitLine.GASTRAIN;
        }
    }

    class ElectricTrainAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
                selectedTransit = TransitLine.ELECTRICTRAIN;
        }
    }

    class OrbitalTrainAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
                selectedTransit = TransitLine.ORBITALTRAIN;
        }
    }

    class EndTurnAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            mapDisplay.endTurn();

        }
    }

}
