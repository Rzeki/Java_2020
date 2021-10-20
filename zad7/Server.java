import java.io.*;
import java.lang.reflect.InvocationTargetException;

import pliki.*;
import java.net.*;
import javax.swing.*;
import java.util.Random;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Server extends TicTacToe{
    private static final long serialVersionUID = 2L;
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



    public Server(int a,int b) throws IOException
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
        JOptionPane.showMessageDialog(null, "Player "+currentPlayer+" starts!");
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

    static public void sendStarters(Object object,Object object2, Object object3)
    {
        try
        {
            out.writeObject(object);
            out.writeObject(object2);
            out.writeObject(object3);
            out.flush();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    static public void main(String[] args)
    {
        try{SwingUtilities.invokeAndWait(new Runnable()
            {
                @Override
                public void run()
                {
                    try(var serverSocket = new ServerSocket(1234))
                    {
                        System.out.println("Gracz 1 przybył");
                        JButton[][] temp;
                        
                        
                        while(true)
                        {
                        Socket clientSocket = serverSocket.accept();
                        out = new ObjectOutputStream(clientSocket.getOutputStream());
                        in = new ObjectInputStream(clientSocket.getInputStream());
                        System.out.println("Gracz 2 przybył");
                        TicTacToe gra = new Server(3,3);   
                        //sendStarters((Object)gra.res,(Object)gra.movesToWin,(Object)gra.currentPlayer);
                        
                        
                        while(gra.End!=true)
                        {
                            temp = (JButton[][])in.readObject();
                            gra.board = (JButton[][])temp;
                        }
                        clientSocket.close();
                        in.close();
                        out.close();
                        }
                        
                    }
                    catch(IOException | ClassNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                }
            });}
        catch(NumberFormatException | InterruptedException | InvocationTargetException e)
        {
            e.printStackTrace();
        }
    }
}