#include <stdio.h>
#include <stdlib.h>

typedef struct
{
    int danUlaska;
    int brojNocenja;
    int danIzlaska;
    int cena;
} Rezervacija;

int main(int argc, char **argv)
{
    int i, j, brojRezervacija, *opt, *prihvacene;
    Rezervacija *rezervacije;
    int datum = 0;
    scanf("%d", &brojRezervacija);

    rezervacije = (Rezervacija *)malloc((brojRezervacija + 1) * sizeof(Rezervacija));

    for (i = 1; i <= brojRezervacija; i++)
    {
        scanf("%d%d%d", &rezervacije[i].danUlaska, &rezervacije[i].brojNocenja, &rezervacije[i].cena);
        rezervacije[i].danIzlaska = rezervacije[i].danUlaska + rezervacije[i].brojNocenja;

        if (rezervacije[i].danIzlaska > datum)
            datum = rezervacije[i].danIzlaska;
    }

    opt = (int *)malloc((datum + 1) * sizeof(int));
    prihvacene = (int *)malloc((datum + 1) * sizeof(int));

    opt[0] = 0;
    prihvacene[0] = 0;

    //prolazimo kroz dane
    for (i = 1; i <= datum; i++)
    {
        opt[i] = opt[i - 1];
        prihvacene[i] = prihvacene[i - 1];
        //prolazimo kroz rezervacije
        for (j = 1; j <= brojRezervacija; j++)
        {
            //da li je to dan izlaska
            if (rezervacije[j].danIzlaska == i)
            {
                if (rezervacije[j].cena + opt[i - rezervacije[j].brojNocenja] > opt[i])
                {
                    opt[i] = rezervacije[j].cena + opt[i - rezervacije[j].brojNocenja];
                    prihvacene[i] = j;
                }
            }
        }
    }
    //maksimum
    printf("%d\n", opt[datum]);

    //rekonstrukcija
    i = datum;
    int *rekonstrukcija = (int *)malloc(brojRezervacija * sizeof(int));
    int r = 0;
    while (opt[i] != 0)
    {
        if (opt[i] == opt[i - 1])
        {
            i--;
        }
        else
        {
            rekonstrukcija[r++] = prihvacene[i];
            i -= rezervacije[prihvacene[i]].brojNocenja;
        }
    }

    while (r)
    {
        printf("%d\n", rekonstrukcija[--r]);
    }

    return 0;
}