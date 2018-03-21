package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuestionMasterQuestionPanel extends JPanel {

    public JTextArea txtrQuestion = new JTextArea();
    private JTextField textField;
    private ClientGui gui;

    /**
     * Create the panel.
     */
    public QuestionMasterQuestionPanel(ClientGui guiConstructor) {
        this.gui = guiConstructor;
        setBackground(new Color(135, 206, 250));
        setLayout(null);

        JLabel lblNewLabel = new JLabel("Round ");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel.setBounds(10, 11, 80, 28);
        add(lblNewLabel);

        txtrQuestion = new JTextArea();
        txtrQuestion.setFont(new Font("Showcard Gothic", Font.PLAIN, 15));
        txtrQuestion.setBounds(20, 50, 450, 150);
        add(txtrQuestion);


        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
        textField.setBounds(20, 255, 43, 34);
        add(textField);
        textField.setColumns(10);

        Timer timer = new Timer(1000, new ActionListener() {                            //creates a timer
            private int count = 5;

            @Override
            public void actionPerformed(ActionEvent e) {                                //after each second the timer will reduce the counter  by one until it is one and then displays end
                if (count <= 0) {
                    textField.setText("end");
                    ((Timer) e.getSource()).stop();
                    count = 5;
                } else {
                    textField.setText(Integer.toString(count));
                    count--;
                }
            }
        });

        timer.start();

    }
}


