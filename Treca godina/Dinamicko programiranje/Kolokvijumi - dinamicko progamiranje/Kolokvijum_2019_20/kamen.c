#include <stdio.h>
#include <stdlib.h>

#define MAX 100

int max(int a, int b, int c)
{
    if (a >= b && a >= c)
        return a;
    else if (b >= a && b >= c)
        return b;
    else
    {
        return c;
    }
}

void resenje()
{
    int h; //broj redova
    int w; //broj kolona
    int i, j, a, c;
    int *opt, *opt2, *temp;
    scanf("%d%d", &h, &w);

    //alokacija memorije
    opt = (int *)malloc((w + 1) * sizeof(int));
    opt2 = (int *)malloc((w + 1) * sizeof(int));

    for (i = 0; i < w; i++)
    {
        opt[i] = 0;
    }

    //za svaki red
    for (i = 0; i < h; i++)
    {
        temp = opt;
        opt = opt2;
        opt2 = temp;

        for (j = 0; j < w; j++)
        {
            scanf("%d", &opt[j]);
            //prva kolona matrice, nema levo
            if (j > 0)
                a = opt2[j - 1];
            else
                a = -1;
            
            //poslednja kolona matrice, nema desno
            if (j + 1 < w)
                c = opt2[j + 1];
            else
                c = -1;

            opt[j] = opt[j] + max(a, opt2[j], c);
        }
    }

    int optimalni = opt[0];

    for (i = 0; i < w; i++)
        if (optimalni < opt[i])
            optimalni = opt[i];

    printf("%d\n", optimalni);

    free(opt);
    free(opt2);
}

int main(int arvc, char *argv)
{
    int t;
    //ucitavam broj test primera
    scanf("%d", &t);

    while (t)
    {
        //za svaki test primer resavam problem
        resenje();
        t--;
    }

    return 0;
}
