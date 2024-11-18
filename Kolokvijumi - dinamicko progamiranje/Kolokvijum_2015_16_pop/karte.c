#include <stdio.h>
#include <stdlib.h>

#define INF -1

int manji(int x, int y)
{
    if (y == INF)
        return 1;
    if (x == INF)
        return 0;
    return x < y;
}

int main(int argc, char **argv)
{
    int i, j, N, Z, X;
    scanf("%d%d%d", &N, &Z, &X);

    int *karte = (int *)malloc((N + 1) * sizeof(int));
    for (i = 1; i <= N; i++)
    {
        scanf("%d", &karte[i]);
    }

    int **opt;
    opt = (int **)malloc((N + 1) * sizeof(int *));
    for (i = 0; i <= N; i++)
        opt[i] = (int *)malloc((Z + 1) * sizeof(int));

    for (i = 0; i <= N; i++)
        opt[i][0] = 0;

    //suma nije setovana
    for (i = 1; i <= Z; i++)
        opt[0][i] = INF;

    //prolazimo kroz karte
    for (i = 1; i <= N; i++)
    {
        //prolazimo kroz sume
        for (j = 1; j <= Z; j++)
        {
            opt[i][j] = opt[i - 1][j];
            //da li karta prekoracuje sumu
            if (karte[i] <= j)
            {
            //optimum predstavlja broj izabranih karata
                if (opt[i - 1][j - karte[i]] != INF && manji(opt[i - 1][j - karte[i]] + 1, opt[i - 1][j]))
                    opt[i][j] = opt[i - 1][j - karte[i]] + 1;
            }
        }
    }

    i = N;
    j = Z;
    //ako nije setovano ili je premasilo broj ti pomeraj levo za prethodnu sumu pribliznu
    while (opt[i][j] == INF || opt[i][j] > X)
        j--;

    printf("%d\n", j);

    while (opt[i][j] != 0)
    {
        if (opt[i][j] == opt[i - 1][j])
        {
            i--;
        }
        else
        {
            printf("%d\n", karte[i]);
            j -= karte[i];
            i--;
        }
    }
    printf("\n");

    return 0;
}
