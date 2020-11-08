package connect4package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <h1>Grid</h1>
 * This class handles dynamically generating an array of {@code Cell} objects to populate the
 * game board according to the current board dimensions chosen. In this process, each cell is
 * assigned its unique ID for location determination and its behaviors are defined when clicked.
 */
public class Grid{
    public static Cell[] cells;

    /**
     * Generates {@code Cell} array and places buttons into a grid pattern. Sets ID for each cell,
     * sets size, and adds {@code ClickListener}
     * @return {@code JPanel} containing cells (buttons) in proper pattern
     */
    public static JPanel drawGrid() {
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(MainView.rows, MainView.columns, 5, 5));
        cells = new Cell[MainView.getCellCount()];  //initialize cell array

        // Populate array of cells, according to grid size:
        int counter = 0;
        for (int r = 0; r < MainView.rows; r++) {
            for (int c = 0; c < MainView.columns; c++) {
                cells[counter] = new Cell(r + 1,c + 1);     //Create each cell
                cells[counter].setID(counter);      //Assign ID
                cells[counter].setPreferredSize(new Dimension(75, 120));
                //cells[counter].setText(("R:" +cells[counter].getRow()+ (" C:" +cells[counter].getColumn()))
                //        + (" ID:" +cells[counter].getID()));//debug
                cells[counter].addActionListener(new ClickListener());
                gridPanel.add(cells[counter]);
                counter++;
            }
        }
        return gridPanel;
    }

    /**
     * Event handler for when cells are clicked on
     */
    static class ClickListener implements ActionListener {
        public void actionPerformed(ActionEvent click) {
            Cell button = (Cell) click.getSource();
            if (!GameEngine.isValidMove(button.getID())) {      // Invalid move selected
                return;
            }
            else if (GameEngine.isValidMove(button.getID())) {  // Valid move selected
                // For single-player mode:
                if (GameEngine.getMode().equals("PVE")) {
                    button.setBackground(Player.HP1.getCheckerColor());
                    button.setColor(Player.HP1.getCheckerColor());
                    button.setIsFilled();
                    if (GameEngine.isWinningMove(button.getID())) {
                        GameEngine.declareWinner();
                    }
                    else{
                        GameEngine.switchPlayer();
                    }
                }
                // For multi-player mode:
                else if (GameEngine.getMode().equals("PVP")) {
                    if (GameEngine.currentPlayer == 1) {
                        button.setBackground(Player.HP1.getCheckerColor());
                        button.setColor(Player.HP1.getCheckerColor());
                        button.setIsFilled();
                        if (GameEngine.isWinningMove(button.getID())) {
                            GameEngine.declareWinner();
                        }
                        else{
                            GameEngine.switchPlayer();
                        }
                    } else if (GameEngine.currentPlayer == 2) {
                        button.setBackground(Player.HP2.getCheckerColor());
                        button.setIsFilled();
                        button.setColor(Player.HP2.getCheckerColor());
                        if (GameEngine.isWinningMove(button.getID())) {
                            GameEngine.declareWinner();
                        }
                        else{
                            GameEngine.switchPlayer();
                        }
                    }
                }
            }
        }
    }
}
