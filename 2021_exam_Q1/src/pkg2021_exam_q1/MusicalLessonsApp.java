import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Arrays;

public class MusicalLessonsApp extends JFrame implements ActionListener {

    // --- Data Storage ---
    // Two-dimensional structure to store the lesson costs: Map<Level, Map<Instrument, Cost>>
    private static final Map<String, Map<String, Integer>> LESSON_COSTS = new HashMap<>();

    // Static block to initialize the data (like the table in the question)
    static {
        // Beginner Costs
        Map<String, Integer> beginnerCosts = new HashMap<>();
        beginnerCosts.put("Guitar", 150);
        beginnerCosts.put("Piano", 250);
        beginnerCosts.put("Violin", 280);
        LESSON_COSTS.put("Beginner", beginnerCosts);

        // Intermediate Costs
        Map<String, Integer> intermediateCosts = new HashMap<>();
        intermediateCosts.put("Guitar", 215);
        intermediateCosts.put("Piano", 232);
        intermediateCosts.put("Violin", 280);
        LESSON_COSTS.put("Intermediate", intermediateCosts);

        // Advanced Costs
        Map<String, Integer> advancedCosts = new HashMap<>();
        advancedCosts.put("Guitar", 130);
        advancedCosts.put("Piano", 185);
        advancedCosts.put("Violin", 310);
        LESSON_COSTS.put("Advanced", advancedCosts);
    }

    // List of Instruments and Levels for ComboBoxes
    private final String[] instruments = {"Guitar", "Piano", "Violin"};
    private final String[] levels = {"Beginner", "Intermediate", "Advanced"};

    // --- GUI Components ---
    private JComboBox<String> instrumentComboBox;
    private JComboBox<String> levelComboBox;
    private JButton submitButton;
    private JButton reportButton;
    private JMenuBar menuBar;

    public MusicalLessonsApp() {
        super("Musical Lessons"); // Set frame title
        
        // 1. Setup the main window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 10)); // Simple layout for components
        
        // 2. Create and add Menu Bar (Q.1.2)
        createMenuBar();
        setJMenuBar(menuBar);

        // 3. Create ComboBoxes and Labels (Q.1.1)
        instrumentComboBox = new JComboBox<>(instruments);
        levelComboBox = new JComboBox<>(levels);

        // Add components to the frame
        add(new JLabel("INSTRUMENT:"));
        add(instrumentComboBox);
        add(new JLabel("LEVEL:"));
        add(levelComboBox);

        // 4. Create Buttons (Q.1.1)
        submitButton = new JButton("SUBMIT");
        reportButton = new JButton("REPORT");
        
        // Register the class (this) as the ActionListener for buttons
        submitButton.addActionListener(this);
        reportButton.addActionListener(this);
        
        // Add buttons to the frame, spanning both columns
        add(submitButton);
        add(reportButton);

        // 5. Finalize Frame Setup
        pack(); // Adjusts size to fit components
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }
    
    // Helper method to create the menu system (Q.1.2)
    private void createMenuBar() {
        menuBar = new JMenuBar();
        
        // File Menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0)); // Exit functionality
        fileMenu.add(exitItem);
        
        // Tools Menu
        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem submitMenuItem = new JMenuItem("Submit");
        JMenuItem reportMenuItem = new JMenuItem("Report");
        
        // Action listeners for menu items (same functionality as buttons)
        submitMenuItem.addActionListener(e -> displayLessonCost());
        reportMenuItem.addActionListener(e -> displayAverageReport());
        
        toolsMenu.add(submitMenuItem);
        toolsMenu.add(reportMenuItem);
        
        // Add menus to the bar
        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
    }

    // --- Action Handling (Q.1.1, Q.1.2) ---
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            displayLessonCost();
        } else if (e.getSource() == reportButton) {
            displayAverageReport();
        }
    }

    // --- Logic for SUBMIT/Tools->Submit (Q.1.1) ---
    private void displayLessonCost() {
        String instrument = (String) instrumentComboBox.getSelectedItem();
        String level = (String) levelComboBox.getSelectedItem();

        // Retrieve cost from the static map structure
        int cost = LESSON_COSTS.getOrDefault(level, new HashMap<>())
                               .getOrDefault(instrument, 0);

        // Prepare the message for the dialog box
        String message = String.format(
            "INSTRUMENT: %s\n" +
            "LEVEL: %s\n" +
            "LESSON PRICE: R %d", 
            instrument, level, cost
        );

        // Display the message using JOptionPane (Q.1.1 Sample Screenshot)
        JOptionPane.showMessageDialog(
            this,
            message,
            "Music Lessons Price",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    // --- Logic for REPORT/Tools->Report (Q.1.1, Q.1.3) ---
    private void displayAverageReport() {
        // Use StringBuilder to construct the report message
        StringBuilder report = new StringBuilder();

        // Calculate and append the average for each level
        for (String level : levels) {
            double average = calculateAverageForLevel(level);
            // Format the output as per the sample screenshot
            report.append(String.format("Average lesson price for %s: R %.0f\n", level, average));
        }
        
        // Display the average report in a dialog box (Q.1.3 Sample Screenshot)
        JOptionPane.showMessageDialog(
            this,
            report.toString().trim(), // trim to remove trailing newline
            "Message",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    /**
     * Calculates the average lesson cost across all instruments for a given level.
     * @param level The musical level (Beginner, Intermediate, Advanced).
     * @return The calculated average cost.
     */
    private double calculateAverageForLevel(String level) {
        if (!LESSON_COSTS.containsKey(level)) {
            return 0.0;
        }

        Map<String, Integer> levelCosts = LESSON_COSTS.get(level);
        int totalCost = 0;
        int count = 0;

        for (int cost : levelCosts.values()) {
            totalCost += cost;
            count++;
        }

        return (double) totalCost / count;
    }

    // --- Main Method ---
    public static void main(String[] args) {
        // Use SwingUtilities.invokeLater to ensure thread safety for GUI creation
        SwingUtilities.invokeLater(() -> new MusicalLessonsApp());
    }
}