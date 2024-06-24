import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {

        String promptProgrammer = "You are the programmer";
        String promptCreativer = "You are the creativer";

        // JFrame
        JFrame frame = new JFrame("Co-extension");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new FlowLayout());

        // Button Programmer
        JButton buttonProgrammer = new JButton("Programmer");
        // buttonProgrammer.addActionListener(e -> System.out.println(promptProgrammer));
        buttonProgrammer.setToolTipText("Pre-prompt for Programmer role");
        panel.add(buttonProgrammer);

        // Button Creativer
        JButton buttonCreative = new JButton("Creativer");
        // buttonCreative.addActionListener(e -> System.out.println(promptCreativer));
        buttonCreative.setToolTipText("Pre-prompt for Creative role");
        panel.add(buttonCreative);

        // Perform action
        performAction(buttonProgrammer, "control 1", promptProgrammer);
        performAction(buttonCreative, "control 2", promptCreativer);
        // Add JPanel to JFrame
        frame.getContentPane().add(panel);

        // Make frame visible
        frame.setVisible(true);
    }

    public static void performAction(JButton button, String keyStrokeString, String prompt) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClipboardUtils.copyToClipboard(prompt);
            }
        });

        KeyStroke keyStroke = KeyStroke.getKeyStroke("control " + button.getText());
        button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, button.getText());
        button.getActionMap().put(button.getText(), new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClipboardUtils.copyToClipboard(prompt);
            }
        });

    }
}
