#include <stdio.h>
#include <stdlib.h>
/*
    svake vrste predmeta postoji po jedan primerak
*/

int main()
{
    int i, j, n, m;
    int *zapremina, *vrednost;
    int **opt;

    scanf("%d%d", &n, &m);

    //alokacija memorije
    zapremina = (int *)malloc((m + 1) * sizeof(int));
    vrednost = (int *)malloc((m + 1) * sizeof(int));

    opt = (int **)malloc((m + 1) * sizeof(int *));

    for (i = 0; i <= m; i++)
        opt[i] = (int *)malloc((n + 1) * sizeof(int));

    //ucitavanje predmeta
    for (i = 1; i <= m; i++)
    {
        scanf("%d%d", &zapremina[i], &vrednost[i]);
    }

    //postavka problema

    for (i = 0; i <= m; i++)
        opt[i][0] = 0;

    for (i = 0; i <= n; i++)
        opt[0][j] = 0;

    //prolazimo kroz svaki predmet pojedinacno
    for (i = 1; i <= m; i++)
    {
        //proveravamo svaki predmet za svaku velicinu ranca
        for (j = 1; j <= n; j++)
        {
            //da li je zapremina ranca [j] dovoljna da se stavi predmet [i]
            if (zapremina[i] <= j)
            {
                if (vrednost[i] + opt[i - 1][j - zapremina[i]] > opt[i - 1][j])
                    opt[i][j] = vrednost[i] + opt[i - 1][j - zapremina[i]];
                else
                {
                    opt[i][j] = opt[i - 1][j];
                }
            }
            else
            {
                //uzima vrednost za tu velicinu ranca za predmet iznad
                opt[i][j] = opt[i - 1][j];
            }
        }
    }

    printf("Rezultat: %d\n", opt[m][n]);

    i = m;
    j = n;

    //radi dok ne dodje do vrednosti gde je 0
    while (opt[i][j] != 0)
    {
        //ako su dva elementa jedan ispod drugog isti idi iznad
        // to znaci da je element u tom polju prepisan iz polja iznad
        if (opt[i][j] == opt[i - 1][j])
            i--;
        else
        {
            //ako nisu isti, ispisuje taj predmet i pomera se u levo za zapreminu itog elementa i za vrstu iznad
            //stampa predmeta
            printf("%d ", i);
            j -= zapremina[i];
            i--;
        }
    }

    return 0;
}