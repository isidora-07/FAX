#include <stdio.h>
#include <stdlib.h>

/*
Matricu A MxN naci minimalni put krecuci se od 1,1 do 3,4 samo u smeru dole i desno
TEST PRIMER
        3 4
        2 15 -17 16
        14 5 -3 -14
        -19 -12 5 -16

        Optimalna matrica.
        2       17      0       16
        16      21      -3      -17
        -3      -15     -10     -33
        kordinate
        (1,1) (1,2) (1,3) (2,3) (2,4) (3,4)
*/

int min(int a, int b)
{
    return a < b ? a : b;
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
    if( i > 1 && j > 1)
    {
        if(opt[i-1][j] < opt[i][j-1])
            rekonstrukcija(opt,i-1,j);
        else
        {
            rekonstrukcija(opt,i,j-1);
        }
        printf("(%d,%d) ", i,j);
    }
    //stigli do vrha skroz
    if(i==1)
    {
        for(k = 1; k <= j; k++)
            printf("(%d,%d) ", i, k);
    }
    //stigli levo skroz
    if(j==1)
    {
         for(k = 1; k <= i; k++)
            printf("(%d,%d) ", k, j);
    }
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

    //kretanjem na dole desno trazimo jeftiniju putanju
    for (i = 2; i <= m; i++)
    {
        for (j = 2; j <= n; j++)
        {
            opt[i][j] = min(opt[i][j - 1], opt[i - 1][j]) + a[i][j];
        }
    }
    printf("Najniza cena puta je: %d.\n", opt[m][n]);
    //ispis optimalne matrice najnizih puteva
    printf("\nOptimalna matrica.\n");
    ispis_matrice(opt, m, n);

    rekonstrukcija(opt, m, n);
    return 0;
}