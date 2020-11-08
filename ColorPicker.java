package connect4package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * <h1>ColorPicker</h1>
 * This class generates the GUI prompt for color selection that is called
 * at runtime before any game mode begins. It allows user selection of a
 * human player's color, either once for single player mode, or twice for
 * multi-player mode where it prevents selecting an already chose color.
 * The AI's color is chosen at random for the PVE and spectator modes.
 */
public class ColorPicker {
    /**
     * Generates GUI pop-up for checker color selection
     * @param playerPrompted The current player choosing their color
     */
    public static void run(String playerPrompted){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        // make black button:
        JRadioButton button1 = new JRadioButton("BLACK");
        button1.setMnemonic(KeyEvent.VK_1);
        button1.setActionCommand("BLACK");
        button1.addActionListener(new ClickListener());
        button1.setForeground(Color.BLACK);
        // make red button:
        JRadioButton button2 = new JRadioButton("RED");
        button2.setMnemonic(KeyEvent.VK_2);
        button2.setActionCommand("RED");
        button2.addActionListener(new ClickListener());
        button2.setForeground(Color.RED);
        // make green button:
        JRadioButton button3 = new JRadioButton("GREEN");
        button3.setMnemonic(KeyEvent.VK_3);
        button3.setActionCommand("GREEN");
        button3.addActionListener(new ClickListener());
        button3.setForeground(Color.GREEN);
        // make pink button:
        JRadioButton button4 = new JRadioButton("PINK");
        button4.setMnemonic(KeyEvent.VK_3);
        button4.setActionCommand("PINK");
        button4.addActionListener(new ClickListener());
        button4.setForeground(Color.PINK);
        // make yellow button:
        JRadioButton button5 = new JRadioButton("YELLOW");
        button5.setMnemonic(KeyEvent.VK_5);
        button5.setActionCommand("YELLOW");
        button5.addActionListener(new ClickListener());
        button5.setForeground(Color.YELLOW);
        // make blue button:
        JRadioButton button6 = new JRadioButton("BLUE");
        button6.setMnemonic(KeyEvent.VK_6);
        button6.setActionCommand("BLUE");
        button6.addActionListener(new ClickListener());
        button6.setForeground(Color.BLUE);
        // make orange button:
        JRadioButton button7 = new JRadioButton("ORANGE");
        button7.setMnemonic(KeyEvent.VK_7);
        button7.setActionCommand("ORANGE");
        button7.addActionListener(new ClickListener());
        button7.setForeground(Color.ORANGE);
        // make gray button:
        JRadioButton button8 = new JRadioButton("GRAY");
        button8.setMnemonic(KeyEvent.VK_8);
        button8.setActionCommand("GRAY");
        button8.addActionListener(new ClickListener());
        button8.setForeground(Color.GRAY);
        // make cyan button:
        JRadioButton button9 = new JRadioButton("CYAN");
        button9.setMnemonic(KeyEvent.VK_9);
        button9.setActionCommand("CYAN");
        button9.addActionListener(new ClickListener());
        button9.setForeground(Color.CYAN);
        // add buttons into single logical grouping:
        ButtonGroup group = new ButtonGroup();
        group.add(button1);
        group.add(button2);
        group.add(button3);
        group.add(button4);
        group.add(button5);
        group.add(button6);
        group.add(button7);
        group.add(button8);
        group.add(button9);
        // add buttons to their own panel:
        JPanel radioPanel = new JPanel(new GridLayout(0,1));
        radioPanel.add(button1);
        radioPanel.add(button2);
        radioPanel.add(button3);
        radioPanel.add(button4);
        radioPanel.add(button5);
        radioPanel.add(button6);
        radioPanel.add(button7);
        radioPanel.add(button8);
        radioPanel.add(button9);

        panel.add(radioPanel);
        panel.add(Box.createVerticalGlue());
        panel.add(Box.createVerticalGlue());
        JOptionPane.showMessageDialog(null, panel, "Set " +playerPrompted+ " Color...", JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * Event handler for each button when clicked
     */
    private static class ClickListener implements ActionListener {
        public void actionPerformed(ActionEvent click) {
            String command = click.getActionCommand();
            if(GameEngine.currentPlayer == 1) {
                if(command.equals( "BLACK" ))  {
                    Player.HP1.setCheckerColor(Color.black);
                }
                if(command.equals( "RED" ))  {
                    Player.HP1.setCheckerColor(Color.red);
                }
                if(command.equals( "GREEN" ))  {
                    Player.HP1.setCheckerColor(Color.green);
                }
                if(command.equals( "PINK" ))  {
                    Player.HP1.setCheckerColor(Color.pink);
                }
                if(command.equals( "YELLOW" ))  {
                    Player.HP1.setCheckerColor(Color.yellow);
                }
                if(command.equals( "BLUE" ))  {
                    Player.HP1.setCheckerColor(Color.blue);
                }
                if(command.equals( "ORANGE" ))  {
                    Player.HP1.setCheckerColor(Color.orange);
                }
                if(command.equals( "GRAY" ))  {
                    Player.HP1.setCheckerColor(Color.gray);
                }
                if(command.equals( "CYAN" ))  {
                    Player.HP1.setCheckerColor(Color.cyan);
                }
            }

            if(GameEngine.currentPlayer == 2) {
                if(command.equals( "BLACK" ))  {
                    if(Player.HP1.getCheckerColor() == Color.black) {
                        JFrame parent = new JFrame(); //Creates a new error window stating the color choice was invalid
                        JOptionPane.showMessageDialog(parent, "Player 1 has already chosen Black. Please choose a different color!");
                    }
                    Player.HP2.setCheckerColor(Color.black);
                }
                else if(command.equals( "RED" ))  {
                    if(Player.HP1.getCheckerColor() == Color.red) {
                        JFrame parent = new JFrame(); //Creates a new error window stating the color choice was invalid
                        JOptionPane.showMessageDialog(parent, "Player 1 has already chosen Red. Please choose a different color!");
                    }
                    Player.HP2.setCheckerColor(Color.red);
                }
                else if(command.equals( "GREEN" ))  {
                    if(Player.HP1.getCheckerColor() == Color.green) {
                        JFrame parent = new JFrame(); //Creates a new error window stating the color choice was invalid
                        JOptionPane.showMessageDialog(parent, "Player 1 has already chosen Green. Please choose a different color!");
                    }
                    Player.HP2.setCheckerColor(Color.green);
                }
                else if(command.equals( "PINK" ))  {
                    if(Player.HP1.getCheckerColor() == Color.pink) {
                        JFrame parent = new JFrame(); //Creates a new error window stating the color choice was invalid
                        JOptionPane.showMessageDialog(parent, "Player 1 has already chosen Pink. Please choose a different color!");
                    }
                    Player.HP2.setCheckerColor(Color.pink);
                }
                else if(command.equals( "YELLOW" ))  {
                    if(Player.HP1.getCheckerColor() == Color.yellow) {
                        JFrame parent = new JFrame(); //Creates a new error window stating the color choice was invalid
                        JOptionPane.showMessageDialog(parent, "Player 1 has already chosen Yellow. Please choose a different color!");
                    }
                    Player.HP2.setCheckerColor(Color.yellow);
                }
                else if(command.equals( "BLUE" ))  {
                    if(Player.HP1.getCheckerColor() == Color.blue) {
                        JFrame parent = new JFrame(); //Creates a new error window stating the color choice was invalid
                        JOptionPane.showMessageDialog(parent, "Player 1 has already chosen Blue. Please choose a different color!");
                    }
                    Player.HP2.setCheckerColor(Color.blue);
                }
                else if(command.equals( "ORANGE" ))  {
                    if(Player.HP1.getCheckerColor() == Color.orange) {
                        JFrame parent = new JFrame(); //Creates a new error window stating the color choice was invalid
                        JOptionPane.showMessageDialog(parent, "Player 1 has already chosen Orange. Please choose a different color!");
                    }
                    Player.HP2.setCheckerColor(Color.orange);
                }
                else if(command.equals( "GRAY" ))  {
                    if(Player.HP1.getCheckerColor() == Color.gray) {
                        JFrame parent = new JFrame(); //Creates a new error window stating the color choice was invalid
                        JOptionPane.showMessageDialog(parent, "Player 1 has already chosen Gray. Please choose a different color!");
                    }
                    Player.HP2.setCheckerColor(Color.gray);
                }
                else if(command.equals( "CYAN" ))  {
                    if(Player.HP1.getCheckerColor() == Color.cyan) {
                        JFrame parent = new JFrame(); //Creates a new error window stating the color choice was invalid
                        JOptionPane.showMessageDialog(parent, "Player 1 has already chosen Cyan. Please choose a different color!");
                    }
                    Player.HP2.setCheckerColor(Color.cyan);
                }
            }
        }
    }


    /**
     * Automatically assigns the AI checker color at random
     */
    public static void AIColorPicker() {
        Random rand = new Random();
        int rand_int = rand.nextInt(8);

        if(GameEngine.currentPlayer == 1) {
            switch (rand_int) {
                case 0:
                    Player.AI1.setCheckerColor(Color.black);
                    break;
                case 1:
                    Player.AI1.setCheckerColor(Color.red);
                    break;
                case 2:
                    Player.AI1.setCheckerColor(Color.green);
                    break;
                case 3:
                    Player.AI1.setCheckerColor(Color.pink);
                    break;
                case 4:
                    Player.AI1.setCheckerColor(Color.yellow);
                    break;
                case 5:
                    Player.AI1.setCheckerColor(Color.blue);
                    break;
                case 6:
                    Player.AI1.setCheckerColor(Color.orange);
                    break;
                case 7:
                    Player.AI1.setCheckerColor(Color.gray);
                    break;
                case 8:
                    Player.AI1.setCheckerColor(Color.cyan);
                    break;
            }
        }
        if(GameEngine.currentPlayer == 2) {
            switch (rand_int) {
                case 0:
                    Player.AI2.setCheckerColor(Color.black);
                    break;
                case 1:
                    Player.AI2.setCheckerColor(Color.red);
                    break;
                case 2:
                    Player.AI2.setCheckerColor(Color.green);
                    break;
                case 3:
                    Player.AI2.setCheckerColor(Color.pink);
                    break;
                case 4:
                    Player.AI2.setCheckerColor(Color.yellow);
                    break;
                case 5:
                    Player.AI2.setCheckerColor(Color.blue);
                    break;
                case 6:
                    Player.AI2.setCheckerColor(Color.orange);
                    break;
                case 7:
                    Player.AI2.setCheckerColor(Color.gray);
                    break;
                case 8:
                    Player.AI2.setCheckerColor(Color.cyan);
                    break;
            }
        }
    }
}
