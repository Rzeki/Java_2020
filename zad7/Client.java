import java.io.*;
import java.lang.reflect.InvocationTargetException;

import pliki.*;
import java.net.*;
import javax.swing.*;
import java.util.Random;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Client extends TicTacToe{
    private static final long serialVersionUID = 1L;
    static public Socket clientSocket;
    static public ObjectOutputStream out;
    static public ObjectInputStream in;

    @Override
    public void initializeBoard()
    {
        int i=0;
        int j=0;
        for(i=0;i<res;i++)
        {
            for(j=0;j<res;j++)
            {   
                JButton btn = new JButton();
                btn.setFont(new Font(Font.SANS_SERIF,Font.BOLD,scaleFont(res)));
                board[i][j]=btn;
                    btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        if(((JButton)e.getSource()).getText().equals("") && End == false)
                        {
                            btn.setText(currentPlayer);
                            hasWinner(currentPlayer);
                            sendPacket(board);
                            togglePlayer();
                        }
                    }
                    });
                
                pane.add(btn);
            }
        }
    }

    public Client(int a,int b) throws IOException
    {
        this.res = a;
        this.movesToWin = b;
        pane = getContentPane();
        pane.setLayout(new GridLayout(a,a));
        setTitle("Tic Tac Toe");
        setSize(800,800);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        choosePlayer();
        board = new JButton[a][a];
        End=false;
        initializeBoard();
        initializeMenuBar();
    }

    public void sendPacket(Object object)
    {
        try
        {
            out.writeObject(object);
            out.flush();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }


    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run()
            {
                try
                {
                    JButton[][] temp;
                    clientSocket = new Socket("localhost",1234);
                    out = new ObjectOutputStream(clientSocket.getOutputStream());
                    in = new ObjectInputStream(clientSocket.getInputStream());
                    // String rozmiar,ruchy,gracz;
                    // rozmiar = in.readObject().toString();
                    // ruchy = in.readObject().toString();
                    // gracz = in.readObject().toString();
                    TicTacToe gra2 = new Client(Integer.parseInt("3"),Integer.parseInt("3"));
                    //gra2.currentPlayer=gracz;
                    while(gra2.End!=true)
                    {
                        while((temp = (JButton[][])in.readObject())!=null)
                        {
                            gra2.board = (JButton[][])temp;
                        }
                        
                    }
        
                    clientSocket.close();
                    in.close();
                    out.close();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
                catch(ClassNotFoundException g)
                {
                    g.printStackTrace();
                }
            }
        });
    }
}