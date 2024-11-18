#include <stdio.h>
#include <stdlib.h>
#include <math.h>

typedef struct Proces
{
    int id;
    int vreme;
    int vaznost;
    float latencija;
} Proces;

int main(int argc, char **argv)
{
    int N; //broj arhitektura
    int P; //broj procesa
    int i, j, a, k;
    int *arhitekture;  //dostupne arhitekture
    int dostupnoVreme; //dostupno vreme za jedan cpu
    Proces *procesi;   //procesi za izvrsavanje

    scanf("%d", &N);

    arhitekture = (int *)malloc((N * sizeof(int)));

    //ucitavanje arhitektura
    for (i = 0; i < N; i++)
    {
        scanf("%d", &arhitekture[i]); //broj procesora
    }

    scanf("%d", &dostupnoVreme);
    dostupnoVreme *= 60; //pretvaranje u sekunde
    scanf("%d", &P);

    procesi = (Proces *)malloc((P + 1) * sizeof(Proces));

    for (i = 1; i <= P; i++)
        scanf("%d%d%d%f", &procesi[i].id, &procesi[i].vreme, &procesi[i].vaznost, &procesi[i].latencija);

    int vreme;
    int **opt;                   //matrica vaznosti
    int vremeIzvrsavanjaProcesa; //sa latencijom
    int optimalnaVaznost = 0;
    int *rekonstrukcija;
    int optimalnaArhitektura;
    int latencijaFlag;

    rekonstrukcija = (int *)malloc((P + 1) * sizeof(int));

    //prolazimo kroz sve arhitekture
    for (a = 0; a < N; a++)
    {
        //dostupno vreme kroz broj procesora koji ce da koriste to vreme
        vreme = dostupnoVreme / arhitekture[a];

        opt = (int **)malloc((P + 1) * sizeof(int *));
        for (i = 0; i <= P; i++)
            opt[i] = (int *)malloc((vreme + 1) * sizeof(int));

        //vreme - x osa
        //P - y osa

        for (i = 0; i <= P; i++)
            for (j = 0; j <= vreme; j++)
                opt[i][j] = 0;

        if (arhitekture[a] == 1)
            latencijaFlag = 0;
        else
        {
            latencijaFlag = 1;
        }

        //prolazimo kroz sve procese
        for (i = 1; i <= P; i++)
        {
            //ovoliko je potrebno procesu da se izvrsi na ovoj arhitekturi
            vremeIzvrsavanjaProcesa = ceil((procesi[i].vreme / (arhitekture[a] * 1.0)) + (latencijaFlag * arhitekture[a] * procesi[i].latencija));
            printf("vreme izvrsavanja %d\n", vremeIzvrsavanjaProcesa);
            //prolazimo kroz dostupno vreme tj sekunde
            for (j = 1; j <= vreme; j++)
            {
                if (vremeIzvrsavanjaProcesa <= j)
                {
                    if (procesi[i].vaznost + opt[i - 1][j - vremeIzvrsavanjaProcesa] > opt[i - 1][j])
                    {
                        opt[i][j] = procesi[i].vaznost + opt[i - 1][j - vremeIzvrsavanjaProcesa];
                    }
                    else
                    {
                        opt[i][j] = opt[i - 1][j];
                    }
                }
                else
                {
                    opt[i][j] = opt[i - 1][j];
                }
            }
        }

        if (opt[P][vreme] > optimalnaVaznost)
        {
            //radi rekonstrukciju
            i = P;
            j = vreme;
            k = 0;
            optimalnaArhitektura = arhitekture[a];
            optimalnaVaznost = opt[P][vreme];

            while (opt[i][j] != 0)
            {
                if (opt[i][j] == opt[i - 1][j])
                    i--;
                else
                {
                    rekonstrukcija[k++] = procesi[i].id;
                    j -= ceil((procesi[i].vreme / (arhitekture[a] * 1.0)) + (latencijaFlag * arhitekture[a] * procesi[i].latencija));
                    i--;
                }
            }
        }
    }
    printf("%d\n", optimalnaArhitektura);
    printf("%d\n", optimalnaVaznost);
    for (i = 0; i < k; i++)
        printf("%d ", rekonstrukcija[i]);

    return 0;
}