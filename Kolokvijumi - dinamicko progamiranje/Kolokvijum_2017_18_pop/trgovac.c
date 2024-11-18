//2017_18_pop

#include <stdio.h>
#include <stdlib.h>

typedef struct
{
    int nabavna;
    int prodajna;
    int zarada;
} Roba;

int Max(int a, int b)
{
    return a > b ? a : b;
}

int main(int argc, char **argv)
{
    int i, j, n, m;
    int budzet;

    Roba *roba;

    scanf("%d", &n);
    roba = (Roba *)malloc((n + 1) * sizeof(Roba));

    for (i = 1; i <= n; i++)
    {
        scanf("%d", &roba[i].nabavna);
    }

    for (i = 1; i <= n; i++)
    {
        scanf("%d", &roba[i].prodajna);
        roba[i].zarada = roba[i].prodajna - roba[i].nabavna;
    }

    //odnosi se na m po zadatku
    scanf("%d", &budzet);

    int *opt, *proizvod;
    //u optimum nizu pamtimu najbolju zaradu za proizvode
    //alocira se na duzinu budzeta
    opt = (int *)malloc((budzet + 1) * sizeof(int)); //zarada
    //proizvod pamti koji prozivod je kupljen za tu kolicinu novca
    proizvod = (int *)malloc((budzet + 1) * sizeof(int));

    
    //PRONALAZENJE OPTIMUMA
    opt[0] = 0;

    //prolazimo kroz sve kolicine novca
    for (i = 1; i <= budzet; i++)
    {
        opt[i] = 0;
        //prolazimo kroz svaki proizvod
        for (j = 1; j <= n; j++)
        {
            //da li moze da se kupi za tu kolicinu novca
            if (roba[j].nabavna <= i)
            {
                //da li je zarada od kupovine ovog prozivoda plus ono sto je optmimum bez novca za taj proizvod
                // bolje nego ono sto vec stoji tu
                if (roba[j].zarada + opt[i - roba[j].nabavna] > opt[i])
                {
                    opt[i] = roba[j].zarada + opt[i - roba[j].nabavna];
                    //pamtimo koji proizvod je izabran
                    proizvod[i] = j;
                }
            }
        }
    }

    printf("%d\n", opt[budzet]);

    //REKONSTRUKCIJA
    //treba ispisati koliko kog prozivoda je kupljeno
    int *kolicina = (int *)malloc((n + 1) * sizeof(int));
    for (i = 1; i <= n; i++)
        kolicina[i] = 0;

    i = budzet;

    while ((opt[i] != 0))
    {
        //kupljeni proizvod povecavamo kolicinu
        kolicina[proizvod[i]]++;
        //pomeramo se levo u nizu do sledeceg kupljenog proizvoda
        i -= roba[proizvod[i]].nabavna;
    }

    //stampamo one proizvod koji su kupljeni, tj kolicina[i] > 0
    for (i = 1; i <= n; i++)
        if (kolicina[i] > 0)
            printf("%d %d\n", i, kolicina[i]);

    return 0;
}