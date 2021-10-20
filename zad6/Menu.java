import pliki.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Menu extends JFrame {
    private static final long serialVersionUID = 1L;
    JFrame pane;
    public Menu()
    {
        super("Options");
        pane = new JFrame("Options");
        JButton b = new JButton("Play!");
        b.setBounds(100,150,140,40);
        JLabel label = new JLabel();
        JLabel label0 = new JLabel();        //MENU Z WPROWADZENIEM WYMIARU PLANSZY I PÓL DO WYGRANIA
        label0.setText("Fields to win:");
        label.setText("Size of board: ");
        label.setBounds(10,15,150,100);
        label0.setBounds(10,65,150,100);

        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        label1.setBounds(10,110,200,100);
        label2.setBounds(10,210,200,100);
        JTextField textfield1 = new JTextField();
        JTextField textfield2 = new JTextField();
        textfield1.setBounds(110,50,130,30);
        textfield2.setBounds(110,100,130,30);

        pane.add(label1);
        pane.add(label2);
        pane.add(label0);
        pane.add(textfield1);
        pane.add(textfield2);
        pane.add(label);
        pane.add(b);
        pane.setSize(300,300);
        pane.setResizable(false);
        pane.setLayout(null);
        pane.setVisible(true);
        pane.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int temp1 = Integer.parseInt(textfield1.getText()); //POBIERANIE INTÓW Z PÓL
                int temp2 = Integer.parseInt(textfield2.getText());
                if(temp1<temp2)
                {
                    JOptionPane.showMessageDialog(null, "Number of fields to win cannot be higher than size of board!");

                }
                else
                {
                    dispose();
                    pane.setVisible(false);
                    new TicTacToe(temp1,temp2); //URUCHAMIANIE GRY
                }   
            }
        });
    }
    
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(Menu::new);
    }

    
        
}