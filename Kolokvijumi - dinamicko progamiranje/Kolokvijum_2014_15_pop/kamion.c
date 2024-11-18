#include <stdio.h>
#include <stdlib.h>

typedef struct
{
    int k; //kolicina robe u skladistu
    int c; //cena po toni
    int t; //vreme od prodavnice do skladista i nazad
} Skladiste;

typedef struct
{
    int vreme; //vreme do lokacije
    int kolicina;
    int cena;
} Lokacija;

int main(int argc, char **argv)
{
    int i, j, pom, N; //broj skladista
    int M, T;         //M - nosivost; T - radno vreme
    scanf("%d", &N);
    Skladiste *skladista = (Skladiste *)malloc((N + 1) * sizeof(Skladiste));
    int ukupnoLokacija = 0;

    for (i = 1; i <= N; i++)
    {
        scanf("%d%d%d", &skladista[i].k, &skladista[i].c, &skladista[i].t);
    }

    scanf("%d%d", &M, &T);
    //ideja da svaki odlazak u jedno skladiste posmaram kao zasebnu lokaciju
    for (i = 1; i <= N; i++)
    {
        ukupnoLokacija += (skladista[i].k / M);
    }
    printf("Ukupno lokacija: %d\n", ukupnoLokacija);

    Lokacija *lokacije = (Lokacija *)malloc((ukupnoLokacija + 1) * sizeof(Lokacija));

    j = 1;
    for (i = 1; i <= N; i++)
    {
        do
        {
            lokacije[j].vreme = skladista[i].t;
            lokacije[j].cena = skladista[i].c;
            lokacije[j].kolicina = M;
            j++;
            skladista[i].k -= M;
        } while (skladista[i].k - M >= 0);
    }

    printf("LokacijeL:\n");
    for(i=1;i<=ukupnoLokacija;i++)
    {
        printf("Lokacija %d.:%d %d %d\n", i, lokacije[i].vreme, lokacije[i].kolicina, lokacije[i].cena);
    }

    int **opt = (int **)malloc((ukupnoLokacija + 1) * sizeof(int *));
    for (i = 0; i <= ukupnoLokacija; i++)
        opt[i] = (int *)malloc((T + 1) * sizeof(int));

    for (i = 0; i <= ukupnoLokacija; i++)
        opt[i][0] = 0;
    for (j = 0; j <= T; j++)
        opt[0][j] = 0;

    //prolazimo kroz svako skladiste
    for (i = 1; i <= ukupnoLokacija; i++)
    {
        for (j = 1; j <= T; j++)
        {
            opt[i][j] = opt[i - 1][j];

            if (lokacije[i].vreme <= j)
            {
                if (lokacije[i].kolicina * lokacije[i].cena + opt[i - 1][j - lokacije[i].vreme] > opt[i][j])
                {
                    opt[i][j] = lokacije[i].kolicina * lokacije[i].cena + opt[i - 1][j - lokacije[i].vreme];
                }
            }
        }
    }

    printf("%d\n", opt[ukupnoLokacija][T]);

    return 0;
}