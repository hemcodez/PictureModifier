import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PictureModifier extends JPanel
        implements ItemListener {

    // -- Declare the variables for the checkboxes, the user's choices, and the picture label.
    JCheckBox chinButton;
    JCheckBox glassesButton;
    JCheckBox hairButton;
    JCheckBox teethButton;

    StringBuffer choices;
    JLabel pictureLabel;

    // =============== Main method ============== \\
    public static void main(String[] args) {
        // -> Schedule a job for the event-dispatching thread: creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }//END RUN METHOD
        });//END METHOD
    }//END MAIN METHOD

    // -- Constructor for the picture modifier
    public PictureModifier() {
        // -- Call the constructor on the super class.
        super(new BorderLayout());

        // -- Create the check boxes.
        chinButton = new JCheckBox("Chin");
        chinButton.setMnemonic(KeyEvent.VK_C);
        chinButton.setSelected(true);

        glassesButton = new JCheckBox("Glasses");
        glassesButton.setMnemonic(KeyEvent.VK_G);
        glassesButton.setSelected(true);

        hairButton = new JCheckBox("Hair");
        hairButton.setMnemonic(KeyEvent.VK_H);
        hairButton.setSelected(true);

        teethButton = new JCheckBox("Teeth");
        teethButton.setMnemonic(KeyEvent.VK_T);
        teethButton.setSelected(true);

        // -- Register a listener for the check boxes.
        chinButton.addItemListener(this);
        glassesButton.addItemListener(this);
        hairButton.addItemListener(this);
        teethButton.addItemListener(this);

        // -- Set the value of the choices to describe the accessories on the geek.
        choices = new StringBuffer("cght");

        // -- Set up the picture label
        pictureLabel = new JLabel();
        pictureLabel.setFont(pictureLabel.getFont().deriveFont(Font.ITALIC));
        updatePicture();

        // -- Put the check boxes in a column in a panel
        JPanel checkPanel = new JPanel(new GridLayout(0, 1));
        checkPanel.add(chinButton);
        checkPanel.add(glassesButton);
        checkPanel.add(hairButton);
        checkPanel.add(teethButton);

        // -- Add the checkboxes and picture label to the JFrame, then
        // - Set the border on the frame.
        add(checkPanel, BorderLayout.LINE_START);
        add(pictureLabel, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    } //END CONSTRUCTOR


    public void itemStateChanged(ItemEvent e) {
        // -- The combination of the index and the character determines the name of the file that's needed to display
        // - the correct accessories for this geek.
        int index = 0;
        char c = '-';
        Object source = e.getItemSelectable();

        // -- Check to see which checkbox the user clicked and set the corresponding values for the image name
        if (source == chinButton) {
            index = 0;
            c = 'c';
        } else if (source == glassesButton) {
            index = 1;
            c = 'g';
        } else if (source == hairButton) {
            index = 2;
            c = 'h';
        } else if (source == teethButton) {
            index = 3;
            c = 't';
        } //END IF

        // -- Now that we know which button was pushed, find out whether it was selected or deselected.
        if (e.getStateChange() == ItemEvent.DESELECTED) {
            c = '-';
        } //END ig

        // -> Apply the change to the string.
        choices.setCharAt(index, c);

        // -> Call the method to swap out the picture
        updatePicture();
    } //END METHOD



    // ============= Method to change the picture ============== \\
    protected void updatePicture() {
        // -- Get the icon corresponding to the image.
        Icon icon = new ImageIcon(
                "images/geek-"
                        + choices.toString()
                        + ".gif");

        // -- Set the icon for the picture and the tool tiptext for the choices
        pictureLabel.setIcon(icon);
        pictureLabel.setToolTipText(choices.toString());
        if (icon == null) {
            pictureLabel.setText("Missing Image");
        } else {
            pictureLabel.setText(null);
        }//END IF
    }//END METHOD


    // ================== Create the GUI and show it. ============== \\
    private static void createAndShowGUI() {
        // -> Create and set up the window.
        JFrame frame = new JFrame("Geek Picture Modifier");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // -> Create and set up the content pane.
        JComponent newContentPane = new PictureModifier();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        // -> Display the window.
        frame.pack();
        frame.setVisible(true);
    }//END METHOD


}//END CLASS