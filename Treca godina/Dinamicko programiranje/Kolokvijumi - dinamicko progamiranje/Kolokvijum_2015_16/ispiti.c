#include <stdio.h>
#include <stdlib.h>

typedef struct
{
    int rbr;
    int daniDoIspita;
    int brojESPB;
    int daniZaUcenje;
} Ispit;

int max(int a, int b)
{
    return a > b ? a : b;
}

void sortiraj(Ispit *niz, int n)
{
    int i, j;
    Ispit temp;

    for (i = 1; i < n; i++)
    {
        for (j = i + 1; j <= n; j++)
        {
            if (niz[i].daniDoIspita > niz[j].daniDoIspita)
            {
                temp = niz[i];
                niz[i] = niz[j];
                niz[j] = temp;
            }
        }
    }
}

int main()
{
    int N; // nepolozeni ispiti
    int M; //espb
    int i, j;
    Ispit *ispiti;
    int poslednjiIspit = 0;
    scanf("%d%d", &N, &M);

    ispiti = (Ispit *)malloc((N + 1) * sizeof(Ispit));

    for (i = 1; i <= N; i++)
    {
        scanf("%d%d%d", &ispiti[i].brojESPB, &ispiti[i].daniDoIspita, &ispiti[i].daniZaUcenje);
        ispiti[i].rbr = i;
        //po x osi matrice su dani do vreme do poslednjeg ispita po danima
        if (ispiti[i].daniDoIspita > poslednjiIspit)
            poslednjiIspit = ispiti[i].daniDoIspita;
    }
    sortiraj(ispiti, N);

    int **opt; //skupljanje espb
    opt = (int **)malloc((N + 1) * sizeof(int *));

    for (i = 0; i <= N; i++)
        opt[i] = (int *)malloc((poslednjiIspit + 1) * sizeof(int));

    for (i = 0; i <= N; i++)
        opt[i][0] = 0;

    for (j = 0; j <= poslednjiIspit; j++)
        opt[0][j] = 0;

    //prolazimo kroz ispite sortirane
    for (i = 1; i <= N; i++)
    {
        //kroz dane za ispite
        for (j = 1; j <= poslednjiIspit; j++)
        {
            opt[i][j] = opt[i - 1][j];
            //j mora da bude manje od dani do ispita jer ispit ne moze da se sprema kad ispit prodje, i dani  za ucenje manji od j da bi imao dovoljno vremena da prodje
            if (ispiti[i].daniZaUcenje <= j && j <= ispiti[i].daniDoIspita)
            {
                opt[i][j] = max(ispiti[i].brojESPB + opt[i - 1][j - ispiti[i].daniZaUcenje], opt[i - 1][j]);
            }
            else
            {
                opt[i][j] = max(opt[i - 1][j], opt[i][j - 1]);
            }
        }
    }

    if (opt[N][poslednjiIspit] >= M)
    {
        printf("moze\n");
        i = N;
        j = poslednjiIspit;

        while (opt[i][j] > 0)
        {
            if (opt[i][j] == opt[i - 1][j])
                i--;
            else
            {
                printf("%d\n", ispiti[i].rbr);
                j -= ispiti[i].daniZaUcenje;
                i--;
            }
            if (ispiti[i].daniDoIspita < j)
                j = ispiti[i].daniDoIspita;
        }
    }
    else
    {
        printf("ne moze\n");
    }

    return 0;
}