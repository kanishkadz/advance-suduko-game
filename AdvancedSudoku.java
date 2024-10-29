import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdvancedSudoku extends JFrame {
    private static final int GRID_SIZE = 9; // 9x9 Sudoku grid
    private static final int SUBGRID_SIZE = 3; // 3x3 subgrids
    private JTextField[][] cells = new JTextField[GRID_SIZE][GRID_SIZE];
    private int[][] solution = {
            { 5, 3, 4, 6, 7, 8, 9, 1, 2 },
            { 6, 7, 2, 1, 9, 5, 3, 4, 8 },
            { 1, 9, 8, 3, 4, 2, 5, 6, 7 },
            { 8, 5, 9, 7, 6, 1, 4, 2, 3 },
            { 4, 2, 6, 8, 5, 3, 7, 9, 1 },
            { 7, 1, 3, 9, 2, 4, 8, 5, 6 },
            { 9, 6, 1, 5, 3, 7, 2, 8, 4 },
            { 2, 8, 7, 4, 1, 9, 6, 3, 5 },
            { 3, 4, 5, 2, 8, 6, 1, 7, 9 }
    }; // Solution grid

    private int[][] puzzle = {
            { 5, 3, 0, 0, 7, 0, 0, 0, 0 },
            { 6, 0, 0, 1, 9, 5, 0, 0, 0 },
            { 0, 9, 8, 0, 0, 0, 0, 6, 0 },
            { 8, 0, 0, 7, 6, 0, 0, 0, 3 },
            { 4, 0, 0, 8, 0, 3, 0, 0, 1 },
            { 7, 0, 0, 0, 2, 0, 0, 0, 6 },
            { 0, 6, 0, 0, 0, 0, 2, 8, 0 },
            { 0, 0, 0, 4, 1, 9, 0, 0, 5 },
            { 0, 0, 0, 0, 8, 0, 0, 7, 9 }
    }; // Puzzle grid (zeros represent empty cells)

    public AdvancedSudoku() {
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        JPanel sudokuPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
        sudokuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Initialize cells
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                cells[row][col] = new JTextField();
                cells[row][col].setHorizontalAlignment(JTextField.CENTER);
                cells[row][col].setFont(new Font("Monospaced", Font.BOLD, 20));
                if (puzzle[row][col] != 0) {
                    cells[row][col].setText(String.valueOf(puzzle[row][col]));
                    cells[row][col].setEditable(false);
                    cells[row][col].setBackground(Color.LIGHT_GRAY);
                }
                sudokuPanel.add(cells[row][col]);

                // Add borders for subgrids
                int top = (row % SUBGRID_SIZE == 0) ? 2 : 1;
                int left = (col % SUBGRID_SIZE == 0) ? 2 : 1;
                int bottom = (row == GRID_SIZE - 1) ? 2 : 1;
                int right = (col == GRID_SIZE - 1) ? 2 : 1;
                cells[row][col].setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));
            }
        }

        // Control buttons
        JPanel controlPanel = new JPanel();
        JButton checkButton = new JButton("Check Solution");
        JButton resetButton = new JButton("Reset");

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkSolution()) {
                    JOptionPane.showMessageDialog(null, "Congratulations! The solution is correct!");
                } else {
                    JOptionPane.showMessageDialog(null, "Solution is incorrect. Keep trying!");
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetBoard();
            }
        });

        controlPanel.add(checkButton);
        controlPanel.add(resetButton);

        cp.add(sudokuPanel, BorderLayout.CENTER);
        cp.add(controlPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Advanced Sudoku Game");
        setSize(600, 600);
        setVisible(true);
    }

    private boolean checkSolution() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                String text = cells[row][col].getText();
                try {
                    if (text.isEmpty() || Integer.parseInt(text) != solution[row][col]) {
                        return false;
                    }
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetBoard() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (puzzle[row][col] == 0) {
                    cells[row][col].setText("");
                    cells[row][col].setEditable(true);
                    cells[row][col].setBackground(Color.WHITE);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AdvancedSudoku();
            }
        });
    }
}
