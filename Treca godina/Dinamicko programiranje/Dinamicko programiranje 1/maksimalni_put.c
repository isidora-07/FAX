/*
• Data je matrica A sa M vrsta i N kolona, popunjena celim brojevima.
• Sa svakog polja je dozvoljeno preći samo na polje ispod ili na polje
desno od tog polja.
• Potrebno je izabrati put od gornjeg levog do donjeg desnog polja tako
da zbir brojeva u poljima preko kojih se ide bude maksimalan.
• Ispisati vrednost optimalnog puta, a zatim i put kao niz koordinata
polja preko kojih se ide.

    TEST PRIMER
        3 4
        2 15 -17 16
        14 5 -3 -14
        -19 -12 5 -16

    Ispis optimalne matrice:
        2       17      0       16
        16      22      19      5
        -3      10      24      8

  
    REKURZIVNO koordinate u matrici
        (1,1) (1,2) (2,2) (2,3) (3,3) (3,4)    
*/

#include <stdio.h>
#include <stdlib.h>

int max(int a, int b)
{
    return a > b ? a : b;
}

void ispis_matrice(int **opt, int m, int n)
{
    int i, j;
    for (i = 1; i <= m; i++)
    {
        for (j = 1; j <= n; j++)
        {
            printf("%d\t", opt[i][j]);
        }
        printf("\n");
    }
}

void rekonstrukcija(int **opt, int i, int j)
{
    int k;
    if (i > 1 && j > 1)
    {
        if (opt[i - 1][j] > opt[i][j - 1])
            rekonstrukcija(opt, i - 1, j);
        else
            rekonstrukcija(opt, i, j - 1);

        printf("(%d,%d) ", i, j);
    }
    //stiglo do vrha matrice, ideja je da se dodje  od 1,1 do tog dela
    if (i == 1)
        for (k = 1; k <= j; k++)
            printf("(%d,%d) ", i, k);

    //stiglo levo do kraja matrice,  ideja je da se dodje do tog dela matrice od 1,1
    if (j == 1)
        for (k = 1; k <= i; k++)
            printf("(%d,%d) ", k, i);
}

int main(int argc, char *argv)
{
    int i, j, m, n;
    int **a, **opt;

    scanf("%d %d", &m, &n);

    //alokacija memorije
    a = (int **)malloc((m + 1) * sizeof(int *));
    opt = (int **)malloc((m + 1) * sizeof(int *));

    for (i = 1; i <= m; i++)
    {
        a[i] = (int *)malloc((n + 1) * sizeof(int));
        opt[i] = (int *)malloc((n + 1) * sizeof(int));
    }

    //ucitavanje elemenata matrice
    for (i = 1; i <= m; i++)
    {
        for (j = 1; j <= n; j++)
        {
            scanf("%d", &a[i][j]);
        }
    }

    //ispis unete matrice
    ispis_matrice(a, m, n);

    opt[1][1] = a[1][1];

    //postavljanje elemenata prve vrste optimalne matrice
    for (i = 2; i <= n; i++)
        opt[1][i] = opt[1][i - 1] + a[1][i];

    //postavljanje elemenata prve kolone optimalne matrice
    for (i = 2; i <= m; i++)
        opt[i][1] = opt[i - 1][1] + a[i][1];

    for (i = 2; i <= m; i++)
        for (j = 2; j <= n; j++)
        {
            //da li je veci element iznad ili levo
            opt[i][j] = max(opt[i - 1][j], opt[i][j - 1]) + a[i][j];
        }

    printf("Maksimalni zbir puta je %d.\n", opt[m][n]);

    printf("\nIspis optimalne matrice:\n");
    ispis_matrice(opt, m, n);

    //rekonstrukcija rezultata bez rekurzije
    i = m;
    j = n;

    printf("(%d,%d) ", i, j);
    while (i > 1 && j > 1)
    {
        if (opt[i][j - 1] > opt[i - 1][j])
        {
            printf("(%d,%d) ", i, j - 1);
            j--;
        }
        else
        {
            printf("(%d,%d) ", i - 1, j);
            i--;
        }
        //specijalni slucaji kada dodje do levog zida matrice ili do gornjeg zida matrice
        if (i == 1)
        {
            for (i = j - 1; i >= 1; i--)
            {
                printf("(%d,%d) ", 1, i);
            }
            break;
        }

        if (j == 1)
        {
            for (j = i - 1; j >= 1; j--)
            {
                printf("(%d,%d) ", j, 1);
            }
            break;
        }
    }

    //rekurzivni nacin nalazenja kordinata na optimalnoj putanji
    printf("\nREKURZIVNO\n");
    rekonstrukcija(opt, m, n);

    return 0;
}
