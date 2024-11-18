#include <stdio.h>
#include <stdlib.h>

typedef struct
{
    int cena;
    int tezina;
} Poklon;

int main(int argc, char **argv)
{
    int m, n, k; //budzet, broj patuljaka, broj poklona
    int budzet;  //budzet za poklon jednom patuljku
    int i, j;
    Poklon *pokloni;
    scanf("%d%d%d", &m, &n, &k);

    //budzet za svakog patuljka je isti, mozemo da gledao kao poklon za jednog patuljka
    budzet = m / n;

    pokloni = (Poklon *)malloc((k + 1) * sizeof(Poklon));

    for (i = 1; i <= k; i++)
        scanf("%d", &pokloni[i].cena);
    for (i = 1; i <= k; i++)
        scanf("%d", &pokloni[i].tezina);

    //maksimalna cena minimalna tezina u opt se nalazi cena
    int *opt = (int *)malloc((budzet + 1) * sizeof(int));
    int *rek = (int *)malloc((budzet + 1) * sizeof(int));
    int *tezina = (int *)malloc((budzet + 1) * sizeof(int));

    opt[0] = 0;
    rek[0] = 0;
    tezina[0] = 0;
    //prolazimo korz budzet
    for (i = 1; i <= budzet; i++)
    {
        opt[i] = 0;
        rek[i] = 0;
        tezina[i] = 0;
        //prolazimo kroz poklone
        for (j = 1; j <= k; j++)
        {
            if (pokloni[j].cena <= i)
            {
                if (pokloni[j].cena + opt[i - pokloni[j].cena] > opt[i])
                {
                    opt[i] = pokloni[j].cena + opt[i - pokloni[j].cena];
                    rek[i] = j;
                    tezina[i] = pokloni[j].tezina + tezina[i - pokloni[j].cena];
                }
                else if (pokloni[j].cena + opt[i - pokloni[j].cena] == opt[i] && pokloni[j].tezina + tezina[i - pokloni[j].cena] < tezina[i])
                {
                    rek[i] = j;
                    tezina[i] = pokloni[j].tezina + tezina[i - pokloni[j].cena];
                }
            }
        }
    }

    printf("%d\n", opt[budzet]);
    printf("%d\n", tezina[budzet]);
    i = budzet;
    while (opt[i] != 0)
    {
        printf("%d ", rek[i]);
        i -= pokloni[rek[i]].cena;
    }

    return 0;
}