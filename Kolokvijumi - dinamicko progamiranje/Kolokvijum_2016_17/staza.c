//2016/17

#include <stdio.h>
#include <stdlib.h>

#define POCETAK 0

typedef struct
{
    int duzina;
    int cena;
} Ploca;

typedef struct
{
    int duzina;
    int udaljenost;
} Ocuvana;

int main(int argc, char **argv)
{
    int i, j, k, D, N, M;
    Ploca *ploce;
    Ocuvana *ocuvane;

    scanf("%d%d", &D, &N);
    ploce = (Ploca *)malloc((N + 1) * sizeof(Ploca));

    for (i = 1; i <= N; i++)
    {
        scanf("%d%d", &ploce[i].duzina, &ploce[i].cena);
    }

    scanf("%d", &M);
    ocuvane = (Ocuvana *)malloc((M + 1) * sizeof(Ocuvana));

    for (i = 1; i <= M; i++)
    {
        scanf("%d%d", &ocuvane[i].duzina, &ocuvane[i].udaljenost);
    }

    int duzinaZaObnovu;
    int minimalnaCenaRada = 0;
    //optmimum trazi najmanju cenu za popunjavanje
    //imamo vise ranaca 1
    //svaka slobodna deonica je ranac 1
    int **iskoriscenePloce; //nije ranac 2 pa matrica, nego niz ranaca 1
    //svaka ocuvana ploca daje jedno vise polje za popunjavanje
    //ako je granicni slucaj(pocetak ili kraj staze ispisuje se "-")
    //zbog indeksa od 1 dodajemo plus 2 (jer imamo M+1 polje za popunjavanje)
    iskoriscenePloce = (int **)malloc((M + 2) * sizeof(int *));
    int *minCena; //cene za racunanje
    int *duzineStaza;
    duzineStaza = (int *)malloc((M + 1) * sizeof(int));

    for (i = 1; i <= M + 1; i++)
    {
        if (i == 1) //prva ploca ocuvana
            duzinaZaObnovu = ocuvane[i].udaljenost;
        else if (i == M + 1) //poslednja ploca ocuvana
            duzinaZaObnovu = D - (ocuvane[i - 1].udaljenost + ocuvane[i - 1].duzina);
        else //ostali slucajevi iz sredine
            duzinaZaObnovu = ocuvane[i].udaljenost - (ocuvane[i - 1].udaljenost + ocuvane[i - 1].duzina);

        duzineStaza[i] = duzinaZaObnovu; //ovo sluzi da ne bi morali ponovo da prolazimo kroz indekse sve da nadjemo koji nam je potreban
        //odredili smo duzinu svakog dela staze koji treba da se obnovi
        iskoriscenePloce[i] = (int *)malloc((duzinaZaObnovu + 1) * sizeof(int));
        minCena = (int *)malloc((duzinaZaObnovu + 1) * sizeof(int));

        if (duzinaZaObnovu == 0)
        {
            //znaci da je spojeno sa pocetkom krajem ili nekom drugom  //+1 jer je 0 a hocemo indeks opt[1][1] da sacuvamo
            iskoriscenePloce[i][duzinaZaObnovu + 1] = -1;
        }
        else
        {
            //trazimo optimalne ploce i minimalnu cenu prolazimo kroz duzine staze
            for (j = 1; j <= duzinaZaObnovu; j++)
            {
                iskoriscenePloce[i][j] = 0; // OVO NIJE KLASICNA MATRICA VEC NIZ NIZOVA  SAMO JE J BITNO
                minCena[j] = 9999999;
                //prolazimo kroz ploce
                for (k = 1; k <= N; k++)
                {
                    if (ploce[k].duzina <= j)
                    {
                        if (ploce[k].cena + minCena[j - ploce[k].duzina] < minCena[j])
                        {
                            minCena[j] = ploce[k].cena + minCena[j - ploce[k].duzina];
                            iskoriscenePloce[i][j] = k;
                        }
                    }
                }
            }
            //izracunali smo minimalnu cenu za obnovu ovog dela ploce tu cenu dodajemo na ukupnu cenu za obnovu
            minimalnaCenaRada += minCena[duzinaZaObnovu];
        }
    }
    //nakon ove for petlje prosle su sve deonice za obnovu stampamo ukupnu minimalnu cenu za obnovu staze
    printf("%d\n", minimalnaCenaRada);
    //prikaz staza koje smo koristili
    int *pomocniNiz;// = (int *)malloc((D + 1) * sizeof(int));

    for (i = 1; i <= M + 1; i++)
    {
        j = duzineStaza[i];
        pomocniNiz = (int *)malloc((j + 1) * sizeof(int));
        k = 0;
        if (j == 0)
            printf("-");
        else
        {
            while (iskoriscenePloce[i][j] != 0)
            {
                pomocniNiz[k++] = iskoriscenePloce[i][j];
                j -= ploce[iskoriscenePloce[i][j]].duzina;
            }
            for (j = k - 1; j >= 0; j--)
                printf("%d ", pomocniNiz[j]);
        }
        printf("\n");
    }
    return 0;
}
