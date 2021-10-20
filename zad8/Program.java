import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.io.*;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;

public class Program extends JFrame implements ActionListener{
    JFrame pane;
    ArrayList<Person> lista;
    static JTextField textfieldUSUN;
    static JTextField textfieldIMIE;
    static JTextField textfieldNAZWISKO;
    static JTextField textfieldTELEFON;
    static JTextField textfieldWYSZUKAJ;
    static DefaultTableModel TabModel;
    static JButton DodajButton;
    static JButton UsunButton;
    static JButton XMLENCODEButton;
    static JButton XMLDECODEButton;
    static JButton WyszukajButton;
    private static final long serialVersionUID = 1L;
    public Program()
    { 
        pane = new JFrame("Książka telefoniczna");
        JPanel panel = new JPanel();
        String col[] = {"Imię","Nazwisko","Telefon"};
        TabModel = new DefaultTableModel(col,0);
        JTable table = new JTable(TabModel);
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.green);
        JScrollPane scrollpane = new JScrollPane(table);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        panel.add(scrollpane);
        panel.setBounds(0,300,500,500);
        lista = new ArrayList<Person>();
        DodajButton = new JButton("Dodaj");
        UsunButton = new JButton("Usuń");
        WyszukajButton = new JButton("Wyszukaj");
        XMLENCODEButton = new JButton("Wprowadź do XML");
        XMLDECODEButton = new JButton("Wprowadź z XML");
        DodajButton.setBounds(100,200,140,40);
        UsunButton.setBounds(550,400,140,40);
        XMLENCODEButton.setBounds(530,550,200,40);
        XMLDECODEButton.setBounds(530,650,200,40);
        WyszukajButton.setBounds(470,130,140,40);
        DodajButton.addActionListener(this);
        UsunButton.addActionListener(this);
        XMLENCODEButton.addActionListener(this);
        XMLDECODEButton.addActionListener(this);
        WyszukajButton.addActionListener(this);
        JLabel labelNAZWISKO = new JLabel();
        JLabel labelIMIE = new JLabel();
        JLabel labelTELEFON = new JLabel();        
        JLabel labelDODAJ = new JLabel();        
        JLabel labelWYSZUKAJ = new JLabel();        
        JLabel labelWYSZUKAJ2 = new JLabel();        
        JLabel labelUSUN = new JLabel();        
        labelIMIE.setText("              Imię: ");
        labelUSUN.setText("USUWANIE WIERSZA");
        labelDODAJ.setText("DODAJ NOWĄ OSOBĘ");
        labelWYSZUKAJ.setText("WYSZUKAJ OSOBĘ");
        labelWYSZUKAJ2.setText("(Imię, Nazwisko lub Telefon)");
        labelNAZWISKO.setText("    Nazwisko: ");
        labelTELEFON.setText("        Telefon: ");
        labelNAZWISKO.setBounds(10,65,150,100);
        labelUSUN.setBounds(530,320,200,40);
        labelDODAJ.setBounds(50,-20,200,100);
        labelWYSZUKAJ.setBounds(450,-20,200,100);
        labelWYSZUKAJ2.setBounds(420,10,300,100);
        labelDODAJ.setFont(new Font(Font.SANS_SERIF,Font.BOLD,16));
        labelWYSZUKAJ.setFont(new Font(Font.SANS_SERIF,Font.BOLD,16));
        labelWYSZUKAJ2.setFont(new Font(Font.SANS_SERIF,Font.BOLD,14));
        labelUSUN.setFont(new Font(Font.SANS_SERIF,Font.BOLD,16));
        labelIMIE.setBounds(10,15,150,100);
        labelTELEFON.setBounds(10,110,200,100);
        textfieldIMIE = new JTextField();
        textfieldNAZWISKO = new JTextField();
        textfieldUSUN = new JTextField();
        textfieldTELEFON = new JTextField();
        textfieldWYSZUKAJ = new JTextField();
        textfieldIMIE.setBounds(110,50,130,30);
        textfieldUSUN.setBounds(550,360,140,40);
        textfieldNAZWISKO.setBounds(110,100,130,30);
        textfieldWYSZUKAJ.setBounds(420,80,230,30);
        textfieldTELEFON.setBounds(110,150,130,30);


        pane.add(labelTELEFON);
        pane.add(panel);
        pane.add(labelNAZWISKO);
        pane.add(labelIMIE);
        pane.add(labelDODAJ);
        pane.add(labelWYSZUKAJ);
        pane.add(labelWYSZUKAJ2);
        pane.add(labelUSUN);
        pane.add(textfieldIMIE);
        pane.add(textfieldNAZWISKO);
        pane.add(textfieldWYSZUKAJ);
        pane.add(textfieldTELEFON);
        pane.add(textfieldUSUN);
        pane.add(labelNAZWISKO);
        pane.add(DodajButton);
        pane.add(UsunButton);
        pane.add(WyszukajButton);
        pane.add(XMLENCODEButton);
        pane.add(XMLDECODEButton);
        pane.setSize(800,800);
        pane.setResizable(false);
        pane.setLayout(null);
        pane.setVisible(true);
        pane.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
         
 		if(source == DodajButton)
        {
            if(!textfieldIMIE.getText().equals("") && !textfieldNAZWISKO.getText().equals("") && !textfieldTELEFON.getText().equals(""))
            {
                Object[] temp = new Object[3];
                temp[0] = textfieldIMIE.getText();
                temp[1] = textfieldNAZWISKO.getText();
                temp[2] = textfieldTELEFON.getText();
                lista.add(new Person((String)temp[0],(String)temp[1],(String)temp[2]));
                TabModel.addRow(temp);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Niekompletne Dane!");
            }
            
        }
        if(source == WyszukajButton)
        {
            String message = "Znaleziono!\n";
            for(Person s:lista)
            {
                if(s.getName().equals(textfieldWYSZUKAJ.getText()) || s.getSurname().equals(textfieldWYSZUKAJ.getText()) || s.getPhoneNumber().equals(textfieldWYSZUKAJ.getText()))
                {
                    message+=(s.getName()+" | "+s.getSurname()+" | "+s.getPhoneNumber()+"\n");
                }
            }
            if(message.equals("Znaleziono!\n"))
            {
                JOptionPane.showMessageDialog(null, "Nie znaleziono!");
            }
            else
            {
                JOptionPane.showMessageDialog(null, message);
            }
        }
        if(source == XMLDECODEButton)
        {
            System.out.println(lista);
            final JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
            int r = fc.showOpenDialog(null);
            if(r == JFileChooser.APPROVE_OPTION)
            {
                File file = fc.getSelectedFile();
                try
                {
                    for(int i=1;i<=lista.size();i++)
                    {
                        TabModel.removeRow(0);
                    }
                    lista = deserializeFromXML(file);
                    Object[] temp = new Object[3];
                    for(Person s:lista)
                    {
                        temp[0] = s.getName();
                        temp[1] = s.getSurname();
                        temp[2] = s.getPhoneNumber();
                        TabModel.addRow(temp);
                    }
                }
                catch(IOException ex)
                {
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "Dane zostały zaimportowane!");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Użytkownik przerwał operację!");
            }
        }

        if(source == XMLENCODEButton)
        {
            try
            {
                writeXML(lista);
                JOptionPane.showMessageDialog(null, "Dane zostały zapisane!");
            }
            catch(FileNotFoundException ex)
            {
                ex.printStackTrace();
            }
        }

        if(source == UsunButton)
        {
            if(!textfieldUSUN.getText().equals(""))
            {
                TabModel.removeRow(Integer.parseInt(textfieldUSUN.getText())-1);
                lista.remove(lista.get(Integer.parseInt(textfieldUSUN.getText())-1));
            }
        }
 
 		
    }
     
    public static void writeXML(ArrayList<Person> lista) throws FileNotFoundException
    {
        try
        {
            XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("output.xml")));
            e.writeObject(lista);
            e.close();
        }
        catch(FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Person> deserializeFromXML(File file) throws IOException 
    {
        FileInputStream fis = new FileInputStream(file.getName());
        XMLDecoder decoder = new XMLDecoder(fis);
        ArrayList<Person> decodedSettings = (ArrayList<Person>)decoder.readObject();
        decoder.close();
        fis.close();
        return decodedSettings;
    }
 

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(Program::new);
    }
}
