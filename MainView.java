package connect4package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <h1>MainView</h1>
 * This class handles the primary GUI drawing of the main menu, including options
 * to start each game mode and to open the board size options.
 * <p>
 * Contains the global variables for board dimensions and the primary {@code JFrame}
 * object in which all other GUI elements are contained. </p>
 */
public class MainView {
    public static int rows = 6;
    public static int columns = 7;
    public static JFrame frame = new JFrame();

    /**
     * This method defines the global {@code JFrame} used by all GUI related classes,
     * and adds the buttons for starting PVE mode, PVP mode, and EVE mode, as well as
     * the button for board size options.
     */
    public static void drawGUI(){
        frame.setTitle("Connect Four by Rat Army");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900,900);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //Make welcome header:
        JLabel headerLabel = new JLabel("Welcome to the Main Menu. Please make a selection below:");
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Make menu:
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        //Add components to menu:
        menuPanel.add(Box.createVerticalGlue());        //Spacing
        menuPanel.add(headerLabel);
        menuPanel.add(Box.createVerticalGlue());        //Spacing
        addButton("Single-Player Mode", menuPanel);
        menuPanel.add(Box.createVerticalGlue());        //Spacing
        addButton("Multi-Player Mode", menuPanel);
        menuPanel.add(Box.createVerticalGlue());        //Spacing
        addButton("Spectator Mode", menuPanel);
        menuPanel.add(Box.createVerticalGlue());        //Spacing
        addButton("Set Board Size", menuPanel);
        menuPanel.add(Box.createVerticalGlue());        //Spacing

        frame.add(menuPanel); //Add menu to window frame
        frame.repaint();
    }

    /**
     * Resets the window by removing all GUI components
     */
    public static void clear(){
        frame.getContentPane().removeAll();
        frame.revalidate();
    }

    /**
     * Helper function for creating buttons
     * @param text String for the button's title
     * @param container Container element where button is added to
     */
    private static void addButton(String text, Container container){
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(200, 100));
        button.setActionCommand(text);
        button.addActionListener(new ClickListener());
        container.add(button);
    }

    /**
     * Determines number of cells, dependent on current board size
     * @return Total number of cells
     */
    public static int getCellCount(){
        return rows * columns;
    }

    /**
     * Initializes a Player vs AI game
     */
    public static void initPVE(){
        GameEngine.setMode("PVE");
        GameEngine.setCurrentPlayer();
        ColorPicker.run("Player 1");
        if(Player.HP1.getCheckerColor() != null) {
            GameEngine.switchPlayer();
            ColorPicker.AIColorPicker();
            if(Player.AI2.getCheckerColor() != null)
                GameEngine.switchPlayer();
        }
        GameEngine.drawGameGUI();
    }

    /**
     * Initializes a Player vs Player game
     */
    public static void initPVP(){
        GameEngine.setMode("PVP");
        GameEngine.setCurrentPlayer();
        ColorPicker.run("Player 1");
        if(Player.HP1.getCheckerColor() != null) {
            GameEngine.switchPlayer();
            ColorPicker.run("Player 2");
            if(Player.HP2.getCheckerColor() != null)
                GameEngine.switchPlayer();
        }
        else {
            return;
        }
        GameEngine.drawGameGUI();
    }

    /**
     * Initializes an AI vs AI game
     */
    public static void initEVE(){
        GameEngine.setMode("EVE");
        GameEngine.setCurrentPlayer();
        ColorPicker.AIColorPicker();
        if(Player.AI1.getCheckerColor() != null) {
            GameEngine.switchPlayer();
            ColorPicker.AIColorPicker();
            if (Player.AI2.getCheckerColor() != null)
                GameEngine.switchPlayer();
            }
        else {
            return;
        }
        GameEngine.drawGameGUI();
    }

    /**
     * Generates GUI pop-up for changing the size of the game board
     * @return String representing the size selected
     */
    private static String changeBoardSize(){
        String[] choices = {"6 x 7 (42 cells)", "7 x 8 (56 cells)", "8 x 9 (72 cells)"};
        String newSize = (String) JOptionPane.showInputDialog(null, "(Currently " +MainView.rows+ " by " +MainView.columns+ "). Select Size:", "Board Size Options",
                JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
        return newSize;
    }

    /**
     * Event handler for each main menu button when clicked
     */
    private static class ClickListener implements ActionListener {
        public void actionPerformed(ActionEvent click) {
            String command = click.getActionCommand();
            switch(command){
                case "Single-Player Mode":
                    initPVE();              // Start a single-player game
                    break;
                case "Multi-Player Mode":
                    initPVP();              // Start a multi-player game
                    break;
                case "Spectator Mode":
                    initEVE();              // Start a spectated game
                    break;
                case "Set Board Size":
                    String newSize = changeBoardSize();
                    switch (newSize){
                        //Set board to 6 by 7:
                        case "6 x 7 (42 cells)":
                            MainView.rows = 6;
                            MainView.columns = 7;
                            break;
                        //Set board to 7 by 8:
                        case "7 x 8 (56 cells)":
                            MainView.rows = 7;
                            MainView.columns = 8;
                            break;
                        //Set board to 8 by 9:
                        case "8 x 9 (72 cells)":
                            MainView.rows = 8;
                            MainView.columns = 9;
                            break;
                    }
            }
        }
    }
}
