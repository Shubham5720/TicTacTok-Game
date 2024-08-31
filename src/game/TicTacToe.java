package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame {
    private JButton[][] buttons;
    private boolean playerXTurn;

    public TicTacToe() {
        buttons = new JButton[3][3];
        playerXTurn = true; // X starts first

        setTitle("Tic-Tac-Toe");
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 3));

        initializeButtons();
    }

    private void initializeButtons() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[row][col].setFocusPainted(false);
                buttons[row][col].addActionListener(new ButtonClickListener());
                add(buttons[row][col]);
            }
        }
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton buttonClicked = (JButton) e.getSource();

            if (buttonClicked.getText().equals("")) {
                if (playerXTurn) {
                    buttonClicked.setText("X");
                } else {
                    buttonClicked.setText("O");
                }

                if (checkForWin()) {
                    JOptionPane.showMessageDialog(null, (playerXTurn ? "X" : "O") + " Wins!");
                    resetBoard();
                } else if (isBoardFull()) {
                    JOptionPane.showMessageDialog(null, "It's a Draw!");
                    resetBoard();
                }

                playerXTurn = !playerXTurn;
            }
        }
    }

    private boolean checkForWin() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (checkRow(i) || checkColumn(i)) {
                return true;
            }
        }

        // Check diagonals
        if (checkDiagonal()) {
            return true;
        }

        return false;
    }

    private boolean checkRow(int row) {
        return !buttons[row][0].getText().equals("") &&
               buttons[row][0].getText().equals(buttons[row][1].getText()) &&
               buttons[row][1].getText().equals(buttons[row][2].getText());
    }

    private boolean checkColumn(int col) {
        return !buttons[0][col].getText().equals("") &&
               buttons[0][col].getText().equals(buttons[1][col].getText()) &&
               buttons[1][col].getText().equals(buttons[2][col].getText());
    }

    private boolean checkDiagonal() {
        return (!buttons[0][0].getText().equals("") &&
                buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][2].getText())) ||
               (!buttons[0][2].getText().equals("") &&
                buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][0].getText()));
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
        playerXTurn = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToe game = new TicTacToe();
            game.setVisible(true);
        });
    }
}