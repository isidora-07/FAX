#include <stdio.h>
#include <stdlib.h>

int main()
{
    int i, j, n, m;
    int *zapremine, *vrednosti, *opt, *predmeti;

    scanf("%d%d", &n, &m); //n - zapremina ranca; m - broj predmeta

    //alokacija memorije
    zapremine = (int *)malloc((m + 1) * sizeof(int));
    vrednosti = (int *)malloc((m + 1) * sizeof(int));

    opt = (int *)malloc((n + 1) * sizeof(int));
    predmeti = (int *)malloc((n + 1) * sizeof(int));

    //indeksi krecu od 1 za predmete da bi se lakse poklopilo sa optimum nizom
    for (i = 1; i <= m; i++)
    {
        scanf("%d%d", &zapremine[i], &vrednosti[i]);
    }

    opt[0] = 0;
    predmeti[0] = 0;

    for (j = 1; j <= n; j++)
    {
        opt[j] = 0;
        predmeti[j] = 0;

        for (i = 1; i <= m; i++)
        {
            //da li moze predmet zapremine [i] da stane u ranac velicine [j]
            if (zapremine[i] <= j)
            {
                //da li je vrednost predmeta [i] sa vrednosti opt[j- zapremina predmeta[i]] veca od vrednosti opt[j]
                if (vrednosti[i] + opt[j - zapremine[i]] > opt[j])
                {
                    opt[j] = vrednosti[i] + opt[j - zapremine[i]];
                    //pamti predmet koji je dodat ranac za tu velicinu
                    predmeti[j] = i;
                }
            }
        }
    }

    printf("Vrednost %d\n", opt[n]);

    printf("Rekonstrukcija predmeta u rancu:\n");

    i = n;
    //prolazi kroz sve otpimume dok ne stigne do 0
    while(opt[i] > 0)
    {
        //stampa predmet koji je za taj optimum
        printf("%d ", predmeti[i]);
        //smanuje vrednost zapremine za taj predmet u rancu koji je upravo ispisan
        i -= zapremine[predmeti[i]];
    }

    return 0;
}