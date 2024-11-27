/*
 * Na ulazu se daje broj Web strana (n) i broj
 * njihovih povezanosti linkovima (l, orijentisano).
 * Zatim se daju povezanosti, pocetna strana i
 * maksimalna dozvoljena dubina.
 * Odrediti strane cija je dubina veca od dozvoljene.
*/

#include <stdio.h>
#include <stdlib.h>

#define POVEZANO 1
#define NEPOVEZANO 0
#define NEDEFINISANO -1

typedef struct element
{
    int cvor;
    struct element *sledeci;
} Element;

//dodavanje na red
void dodaj(Element **pocetak, Element **kraj, int u)
{
    Element *novi;
    novi = (Element *)malloc(sizeof(Element));
    novi->cvor = u;
    novi->sledeci = NULL;

    if (*pocetak == NULL)
    {
        *pocetak = *kraj = novi;
    }
    else
    {
        (*kraj)->sledeci = novi;
        *kraj = novi;
    }
}

//skidanje sa reda
int skini(Element **pocetak, Element **kraj)
{
    Element *temp;
    int u;

    if (*pocetak == NULL)
    {
        return NEDEFINISANO;
    }
    else
    {
        temp = *pocetak;
        *pocetak = temp->sledeci;
        if (*kraj == temp)
            *kraj = NULL;
        u = temp->cvor;
        free(temp);
        return u;
    }
}

void stampajPut(int *pret, int s, int v)
{
    if (v == s)
        printf("%d ", s);
    else if (pret[v] == NEDEFINISANO)
        printf("Nema puta od %d do %d.\n", v, s);
    else
    {
        stampajPut(pret, s, pret[v]);
        printf("%d ", v);
    }
}

int vukUBlizini(int **graf, int *vukovi, int n, int u)
{
    int v;

    for (v = 0; v < n; v++)
        if (graf[u][v] == POVEZANO && vukovi[v] == POVEZANO)
            return 1;

    return 0;
}

void pomeriVukove(int **graf, int *vukovi, int n)
{
    int i, j;

    for (i = 0; i < n; i++)
        if (vukovi[i] == POVEZANO)
            for (j = 0; j < n; j++)
                if (graf[i][j] == POVEZANO && vukovi[j] != POVEZANO) {
                    vukovi[i] = NEDEFINISANO;
                    vukovi[j] = POVEZANO;
                    break;
                }                    
}

int main()
{
    int **graf, *d, *pret, *vukovi;
    int n, m, l;
    int u, v;
    int i, j;
    int sPoc, sKraj;
    int pomeriVuka;
    Element *pocetak, *kraj;

    scanf("%d%d%d", &n, &l, &m);

    graf = (int **)malloc(n * sizeof(int *));
    for (i = 0; i < n; i++)
        graf[i] = (int *)malloc(n * sizeof(int));

    d = (int *)malloc(n * sizeof(int));
    pret = (int *)malloc(n * sizeof(int));
    vukovi = (int *)malloc(n * sizeof(int));

    for (i = 0; i < n; i++)
        for (j = 0; j < n; j++)
            graf[i][j] = NEPOVEZANO;

    for (i = 0; i < n; i++) {
        d[i] = NEDEFINISANO;
        pret[i] = NEDEFINISANO;
        vukovi[i] = NEDEFINISANO;
    }

    for (i = 0; i < l; i++) {
        scanf("%d%d", &u, &v);
        graf[u][v] = POVEZANO;
        graf[v][u] = POVEZANO;
    }

    for (i = 0; i < m; i++) {
        scanf("%d", &u);
        vukovi[u] = POVEZANO;
    }

    scanf("%d%d", &sPoc, &sKraj);

    d[sPoc] = 0;
    pocetak = kraj = NULL;
    pomeriVuka = 0;

    dodaj(&pocetak, &kraj, sPoc);

    while ((u = skini(&pocetak, &kraj)) != NEDEFINISANO) {
        if (pomeriVuka && d[u] % 3 == 0) {
            pomeriVukove(graf, vukovi, n);
            pomeriVuka = 0;
        }

        if (d[u] % 3 == 1)
            pomeriVuka = 1;

        printf("Obradjujem cvor %d - NIVO: %d\n", u, d[u]);
        printf("Vukovi se nalaze na pozicijama: ");
        for (i = 0; i < n; i++)
            if (vukovi[i] == POVEZANO)
                printf("%3d", i);
        printf("\n");


        for (v = 0; v < n; v++) {
            if (graf[u][v] == POVEZANO && !vukUBlizini(graf, vukovi, n, v)) {
                if (d[v] == NEDEFINISANO) {
                    printf("Dodajem cvor %d u red!\n", v);
                    d[v] = d[u] + 1;
                    pret[v] = u;
                    dodaj(&pocetak, &kraj, v);
                }
            }
        }

        printf("\n\n");
    }

    if (d[sKraj] == NEDEFINISANO) {
        printf("Zec nije stigao do svog mladunceta.\n");
    }
    else {
        printf("Zec je stigao za %d skokova.\n", d[sKraj]);
        stampajPut(pret, sPoc, sKraj);
        printf("\n");
    }
    
    return 0;
}
