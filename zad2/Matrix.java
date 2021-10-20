import java.util.Scanner;

public class Matrix {

        public int m_size;
        public double[][] tablica;
        Scanner scan = new Scanner(System.in);
        Matrix(int size)
        {
            m_size=size; //rozmiar macierzy kwadratowej
            tablica = new double[m_size][m_size]; //deklaracja tablicy w ktorej beda przechowywane liczby
            for(int i=0;i<m_size;i++)
            {
                for(int j=0;j<m_size;j++)
                {
                    tablica[i][j]=0;  //zerowanie macierzy (przydatne pozniej)
                }
            }
        }


        void show()
        {
            System.out.println();
            for(int i=0;i<m_size;i++)
            {
                for(int j=0;j<m_size;j++)
                {
                    System.out.print("  "+tablica[i][j]+"  ");  //wyswietlenie macierzy
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();
        }

        void fill()
        {
            for(int i=0;i<m_size;i++)
            {
                for(int j=0;j<m_size;j++)
                {
                    System.out.println("Uzupelnij element, wiersz: "+(i+1)+" kolumna: "+(j+1)); //wypelnianie macierzy liczbami zaczynajac od lewego gornego rogu
                    tablica[i][j] = scan.nextDouble();
                }
                System.out.println();
            }
        }

        void edit(int i, int j)
        {
            System.out.println("Edytuj element, wiersz: "+(i+1)+" kolumna: "+(j+1));  //edytowanie poszczegolnego elementu
            tablica[i][j]=scan.nextDouble();
        }

        void access(int i, int j)
        {
            System.out.println("Wyswietlanie elementu, wiersz: "+(i+1)+" kolumna: "+(j+1)+"  element: "+tablica[i][j]); //wyswietlanie poszczegolnego elementu
        }

        Matrix add(Matrix b)
        {
            if(m_size!=b.m_size)
            {
                System.out.println("Macierze nie moga zostac dodane!"); //warunek konieczny do dodawania
                System.exit(1);
                return b;  //musialem zwrocic cos bo inaczej wyrzucalo blad mimo ze linijke wyzej koncze dzialanie programu
            }
            else
            {
                Matrix res = new Matrix(b.m_size);
                for(int i=0;i<m_size;i++)
                {
                    for(int j=0;j<m_size;j++)
                    {
                        res.tablica[i][j] = tablica[i][j] + b.tablica[i][j]; //sumowanie macierz + macierz
                    }
                }
                return res;
            }
            
        }

        Matrix multiply(Matrix b)
        {
            if(m_size!=b.m_size)
            {
                System.out.println("Macierze nie moga zostac pomnozone!");
                System.exit(1);
                return b; //musialem zwrocic cos bo inaczej wyrzucalo blad mimo ze linijke wyzej koncze dzialanie programu
            }
            else
            {
                Matrix res = new Matrix(m_size);
                for(int i=0;i<m_size;++i)
                {
                    for(int j=0;j<m_size;++j)
                    {
                        double sum=0; 
                        for(int z=0;z<m_size;++z) sum+=tablica[i][z]*b.tablica[z][j];  //mnozenie macierz * macierz
                        res.tablica[i][j]=sum;
                    }
                }
                return res;
            }
        }

        public double det (double[][] matrix) {
            double temporary[][];
            double result = 0;
    
            if (matrix.length == 1) {
                result = matrix[0][0];
                return (result);                //wyznacznik macierzy rekurencyjnie
            }
    
            if (matrix.length == 2) {
                result = ((matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]));
                return (result);
            }
    
            for (int i = 0; i < matrix[0].length; i++) {
                temporary = new double[matrix.length - 1][matrix[0].length - 1];
    
                for (int j = 1; j < matrix.length; j++) {
                    for (int k = 0; k < matrix[0].length; k++) {
                        if (k < i) {
                            temporary[j - 1][k] = matrix[j][k];
                        } else if (k > i) {
                            temporary[j - 1][k - 1] = matrix[j][k];
                        }
                    }
                }
    
                result += matrix[0][i] * Math.pow (-1, (double) i) * det (temporary);
            }
            return (result);
        }

    public static void main(String[] args)
    {
        Matrix obj = new Matrix(2); // 2x2
        obj.fill();
        Matrix obj2 = new Matrix(2);
        obj2.fill();
        System.out.println("pierwsza:");
        obj.show();
        System.out.println("druga:");
        obj2.show();
        System.out.println("Suma:");
        Matrix sum = obj.add(obj2);
        sum.show();
        sum.access(0, 1);
        System.out.println("Mnozenie:");
        Matrix mult = obj.multiply(obj2);
        mult.show();
        System.out.println();
        System.out.println("Wyznacznik pierwszej: "+obj.det(obj.tablica)+" , wyznacznik drugiej: "+obj2.det(obj2.tablica)); 
        
    }
    
}
