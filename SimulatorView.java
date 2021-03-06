import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * A graphical view of the simulation grid.
 * The view displays a colored rectangle for each location 
 * representing its contents. It uses a default background color.
 * Colors for each type of species can be defined using the
 * setColor method.
 * 
 * @author David J. Barnes and Michael Kölling, Adam Murray and Ryan Yan
 * @version 2022.02.28
 */
public class SimulatorView extends JFrame
{
    // Colors used for empty locations.
    private static final Color EMPTY_COLOR = Color.white;

    // Color used for objects that have no defined color.
    private static final Color UNKNOWN_COLOR = Color.gray;

    private final String STEP_PREFIX = "Step: ";
    private final String POPULATION_PREFIX = "Population: ";
    private JLabel stepLabel, population, infoLabel;
    private FieldView fieldView;

    // A map for storing colors for participants in the simulation
    private Map<LifeEnumInterface, Color> colors;
    // A statistics object computing and storing simulation information
    private FieldStats stats;
    
    private Simulator simulator;
    private Boolean simulationRunning = false;

    /**
     * Create a view of the given width and height.
     * @param height The simulation's height.
     * @param width  The simulation's width.
     */
    public SimulatorView(int height, int width, Simulator simulator)
    {
        this.simulator = simulator;
        stats = new FieldStats();
        colors = new LinkedHashMap<>();

        setTitle("Wildlife Simulation");
        stepLabel = new JLabel(STEP_PREFIX, JLabel.CENTER);
        infoLabel = new JLabel("  ", JLabel.CENTER);
        population = new JLabel(POPULATION_PREFIX, JLabel.CENTER);
        
        setLocation(100, 50);

        fieldView = new FieldView(height, width);

        Container contents = getContentPane();
        
        Box buttonsVbox = Box.createVerticalBox();
        JButton reset = new JButton("Reset simulation");
        reset.addActionListener(e -> {
            if (!simulationRunning)
            {
                simulator.reset();
            }});
        JButton oneStep = new JButton("Simulate 1 step");
        oneStep.addActionListener(e -> {
            if (!simulationRunning)
            {
                simulator.simulateOneStep();
            }});
        JButton longSim = new JButton("Simulate long (150 steps)");
        longSim.addActionListener(e -> 
            {   Thread thread = new Thread() {
                    public void run()
                    {
                        simulator.simulate(150);
                        simulationRunning = false;
                    }   
                };
                if (!simulationRunning)
                {
                    simulationRunning = true;
                    thread.start();
                }
        });
        JTextField input = new JTextField(1);
        JButton anySim = new JButton("Simulate");
        anySim.addActionListener(e -> 
            {   Thread thread = new Thread() {
                    public void run()
                    {
                        simulator.simulate(Integer.parseInt(input.getText()));
                        simulationRunning = false;
                    }   
                };
                if (!simulationRunning && isInt(input.getText()))
                {
                    simulationRunning = true;
                    thread.start();
                }
        });
        buttonsVbox.add(reset);
        buttonsVbox.add(oneStep);
        buttonsVbox.add(longSim);
        buttonsVbox.add(anySim);
        JPanel buttonsLayout = new JPanel(new BorderLayout());
        JPanel buttonsLayoutSouth = new JPanel(new BorderLayout());
        buttonsLayout.add(input, BorderLayout.SOUTH);
        buttonsLayout.add(buttonsLayoutSouth, BorderLayout.CENTER);
        buttonsLayoutSouth.add(buttonsVbox, BorderLayout.SOUTH);
        
        JPanel infoPane = new JPanel(new BorderLayout());
        infoPane.add(stepLabel, BorderLayout.WEST);
        infoPane.add(infoLabel, BorderLayout.CENTER);
        contents.add(infoPane, BorderLayout.NORTH);
        contents.add(fieldView, BorderLayout.CENTER);
        contents.add(population, BorderLayout.SOUTH);
        contents.add(buttonsLayout, BorderLayout.WEST);
        pack();
        setVisible(true);
    }
    
    // Tests if a given string is an int.
    private boolean isInt(String test)
    {   
        try
        {
            Integer.parseInt(test);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    /**
     * Define a color to be used for a given class of animal.
     * @param animalClass The animal's Class object.
     * @param color The color to be used for the given class.
     */
    public void setColor(LifeEnumInterface life, Color color)
    {
        colors.put(life, color);
    }

    /**
     * Display a short information label at the top of the window.
     */
    public void setInfoText(String text)
    {
        infoLabel.setText(text);
    }

    /**
     * @return The color to be used for a given class of animal.
     */
    private Color getColor(LifeEnumInterface life)
    {
        Color col = colors.get(life);
        if(col == null) {
            // no color defined for this class
            return UNKNOWN_COLOR;
        }
        else {
            return col;
        }
    }

    /**
     * Show the current status of the field.
     * @param step Which iteration step it is.
     * @param field The field whose status is to be displayed.
     */
    public void showStatus(int step, Field field)
    {
        if(!isVisible()) {
            setVisible(true);
        }

        stepLabel.setText(STEP_PREFIX + step);
        stats.reset();

        fieldView.preparePaint();

        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                Plant plant = field.getPlantAt(row, col);
                Animal animal = field.getAnimalAt(row, col);
                fieldView.drawMark(col, row, EMPTY_COLOR, 1);
                //Code below to test if colours can be layered
                if(plant != null) {
                    stats.incrementCount(plant.getType());
                    fieldView.drawMark(col, row, getColor(plant.getType()), 1);
                    Set<DiseaseEnum> diseases = plant.getInfectedBy();
                    for (DiseaseEnum disease : diseases)
                    {
                        stats.incrementCount(disease);
                    }
                }
                if(animal != null) {
                    stats.incrementCount(animal.getType());
                    fieldView.drawMark(col, row, getColor(animal.getType()), 2);
                    Set<DiseaseEnum> diseases = animal.getInfectedBy();
                    for (DiseaseEnum disease : diseases)
                    {
                        stats.incrementCount(disease);
                    }
                }
            }
        }
        stats.countFinished();

        population.setText(POPULATION_PREFIX + stats.getPopulationDetails(field));
        fieldView.repaint();
    }

    /**
     * Determine whether the simulation should continue to run.
     * @return true If there is more than one species alive.
     */
    public boolean isViable(Field field)
    {
        return stats.isViable(field);
    }

    /**
     * Provide a graphical view of a rectangular field. This is 
     * a nested class (a class defined inside a class) which
     * defines a custom component for the user interface. This
     * component displays the field.
     * This is rather advanced GUI stuff - you can ignore this 
     * for your project if you like.
     */
    private class FieldView extends JPanel
    {
        private final int GRID_VIEW_SCALING_FACTOR = 6;

        private int gridWidth, gridHeight;
        private int xScale, yScale;
        Dimension size;
        private Graphics g;
        private Image fieldImage;

        /**
         * Create a new FieldView component.
         */
        public FieldView(int height, int width)
        {
            gridHeight = height;
            gridWidth = width;
            size = new Dimension(0, 0);
        }

        /**
         * Tell the GUI manager how big we would like to be.
         */
        public Dimension getPreferredSize()
        {
            return new Dimension(gridWidth * GRID_VIEW_SCALING_FACTOR,
                gridHeight * GRID_VIEW_SCALING_FACTOR);
        }

        /**
         * Prepare for a new round of painting. Since the component
         * may be resized, compute the scaling factor again.
         */
        public void preparePaint()
        {
            if(! size.equals(getSize())) {  // if the size has changed...
                size = getSize();
                fieldImage = fieldView.createImage(size.width, size.height);
                g = fieldImage.getGraphics();

                xScale = size.width / gridWidth;
                if(xScale < 1) {
                    xScale = GRID_VIEW_SCALING_FACTOR;
                }
                yScale = size.height / gridHeight;
                if(yScale < 1) {
                    yScale = GRID_VIEW_SCALING_FACTOR;
                }
            }
        }

        /**
         * Paint on grid location on this field in a given color.
         */
        // added additional scale @param 
        public void drawMark(int x, int y, Color color, int scale) 
        {
            g.setColor(color);
            g.fillRect(x * xScale, y * yScale, (xScale-1) - scale, (yScale-1) - scale);
        }

        /**
         * The field view component needs to be redisplayed. Copy the
         * internal image to screen.
         */
        public void paintComponent(Graphics g)
        {
            if(fieldImage != null) {
                Dimension currentSize = getSize();
                if(size.equals(currentSize)) {
                    g.drawImage(fieldImage, 0, 0, null);
                }
                else {
                    // Rescale the previous image.
                    g.drawImage(fieldImage, 0, 0, currentSize.width, currentSize.height, null);
                }
            }
        }
    }
}
