package pliki;
import javax.swing.*;
import java.util.Random;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class TicTacToe extends JFrame {
    private static final long serialVersionUID = 2L;
    public Integer res; //ROZMIAR TABLICY res x res
    public Integer movesToWin; //Liczba pól do zwyciestwa
    public Container pane;
    public String currentPlayer;
    public String Startingplayer;
    public JButton[][] board; //TABLICA PRZYCISKÓW
    public boolean End;
    public JMenuBar menuBar;
    public JMenu menu;
    public JMenuItem quit;
    public JMenuItem newGame;

    public TicTacToe() {}

    public TicTacToe(int a,int b) {
        super("TicTacToe");
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

    public void initializeMenuBar() 
    {
        menuBar = new JMenuBar();
        menu = new JMenu("Options");
        newGame = new JMenuItem("New Game");
        newGame.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                resetBoard();
            }
        });
        quit = new JMenuItem("Quit");
        quit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu.add(newGame);
        menu.add(quit);
        menuBar.add(menu);
        setJMenuBar(menuBar);

    }
    public void resetBoard() 
    {
        choosePlayer();
        Startingplayer = currentPlayer;
        End = false;
        for(int i=0;i<res;i++)
        {
            for(int j=0;j<res;j++)
            {
                board[i][j].setText("");
            }
        }
    }

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
                            togglePlayer();
                        }
                    }
                });
                pane.add(btn);
            }
        }
    }
    public void togglePlayer() 
    {
        if(currentPlayer.equals("x"))
        {
            currentPlayer = "o";
        }
        else
        {
            currentPlayer = "x";
        }
    }
    public void hasWinner(String currentPlayer) 
    {
        int rekord=0;
        int current=1;
        for(int i=0;i<res;i++)
        {
            for(int j=0;j<res-1;j++)
            {
                if(board[i][j].getText().equals(currentPlayer) && board[i][j+1].getText().equals(currentPlayer))
                {
                    current++;
                }
                else                                                                                        //SPRAWDZANIE W POZIOMIE
                {
                    current=1;
                }
                if(current>rekord)
                {
                    rekord=current;
                }
                if(rekord>=movesToWin)
                {
                    End = true;
                    JOptionPane.showMessageDialog(null,"Player "+currentPlayer+" WINS!");
                    resetBoard();
                    System.exit(0);
                }   
            }
            current=1; 
        }
        rekord =0;
        current =1;
        for(int i=0;i<res;i++)
        {
            for(int j=0;j<res-1;j++)
            {   
                if(board[j][i].getText().equals(currentPlayer) && board[j+1][i].getText().equals(currentPlayer))
                {
                    current++;
                }
                else
                {                                                                                   //SPRAWDZANIE W PIONIE
                    current=1;
                }
                if(current>rekord)
                {
                    rekord=current;
                }
                if(rekord>=movesToWin)
                {
                    End = true;
                    JOptionPane.showMessageDialog(null,"Player "+currentPlayer+" WINS!");
                    resetBoard();
                    System.exit(0);
                } 
            }
            current=1;
            
        }
        /////////////////////////////////////////////////////////////////////////////////////DIAGONALE
        current=1;
        rekord=0;
        for(int i = res-1;i>0;i--)
        {
            for(int j=0,x=i;x<=res-2;j++,x++)
            {
                
                if(board[x][j].getText().equals(currentPlayer) && (board[x+1][j+1].getText().equals(currentPlayer)))
                {
                    current++;
                }
                else
                {                                                                                                        //  DIAGONALE  "\"
                    current=1;
                }
                if(current>rekord)
                {
                    rekord=current;
                }
                if(rekord>=movesToWin)
                {
                    End = true;
                    JOptionPane.showMessageDialog(null,"Player "+currentPlayer+" WINS!");
                    resetBoard();
                    System.exit(0);
                }
            }
            current=1; 
        }
        current=1;
        for(int i=0;i<=res-1;i++)
        {
            for(int j=0,y=i;y<=res-2;j++,y++)
            {
                if(board[j][y].getText().equals(currentPlayer) && (board[j+1][y+1].getText().equals(currentPlayer)))        
                {
                    current++;
                }
                else
                {                                                                                                       
                    current=1;
                }
                if(current>rekord)
                {
                    rekord=current;
                }
                if(rekord>=movesToWin)
                {
                    End = true;
                    JOptionPane.showMessageDialog(null,"Player "+currentPlayer+" WINS!");
                    resetBoard();
                    System.exit(0);
                }
            }
            current=1;
        }
        current=1;
        rekord=0;

        for(int i = 0;i<res;i++)
        {
            for(int j=0,x=i;x>0 && j<res-1;j++,x--)
            {
                if(board[j][x].getText().equals(currentPlayer) && (board[j+1][x-1].getText().equals(currentPlayer)))
                {
                    current++;
                }
                else
                {
                    current=1;
                }
                if(current>rekord)
                {
                    rekord=current;
                }
                if(rekord>=movesToWin)
                {
                    End = true;                                                                                         //DIAGONALE "/"
                    JOptionPane.showMessageDialog(null,"Player "+currentPlayer+" WINS!");
                    resetBoard();
                    System.exit(0);
                }   
            }
            current=1;
        }
        current=1;
        for(int i=res-1;i>=0;i--)
        {
            for(int j=res-1,y=i;j>0 && y<res-1;j--,y++)
            {
                
                if(board[y][j].getText().equals(currentPlayer) && (board[y+1][j-1].getText().equals(currentPlayer)))
                {
                    current++;
                }
                else
                {
                    current=1;
                }
                if(current>rekord)
                {
                    rekord=current;
                }
                if(rekord>=movesToWin)
                {
                    End = true;
                    JOptionPane.showMessageDialog(null,"Player "+currentPlayer+" WINS!");
                    resetBoard();
                    System.exit(0);
                }
            }
            current=1;
        }

    }

    public void choosePlayer()
    {
        Random rand = new Random();
        if(rand.nextInt(2)==1)
        {
            currentPlayer = "x";
        } 
        else                           //losowe wybranie gracza
        {
            currentPlayer = "o";
        }
    }

    public int scaleFont(int res)
    {
        if(100 - 5*res < 0)
        {
            return 10;               //skalowanie czcionki, więcej pól = mniejsza czcionka
        }
        else
        {
            return (100 - 5*res);
        }
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(TicTacToe::new);
    }

}
