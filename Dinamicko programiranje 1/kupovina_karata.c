/*
• Neka N ljudi čeka u redu da kupi karte za predstavu, pri čemu je t i
vreme koje je i-tom kupcu potrebno da kupi kartu.
• Ako se po dvoje suseda u redu udruži da kupi karte, npr. k-ti i k + 1-vi
kupac, onda je vreme potrebno da oni kupe karte p k , k = 1. . n − 1.
• Udruživanje može da ubrza kupovinu, a i ne mora.
• Za dato n i nizove t i p odrediti takav način udruživanja da ukupno
vreme potrebno da svih n kupaca kupi kartu bude minimalno.


    TEST PRIMER
    KUPCI         2  5   3     7   2   1   9
    UDRUZENO       6   4   12    8   5   8

input:
7
2 5 3 7 2 1 9
6 4 12 8 5 8

output: 22

input:
4
2 2 6 3
5 5 4
output: 8

*/

#include <stdio.h>
#include <stdlib.h>

int main()
{
    int i, j, n; //broj kupaca
    int *t;      //vreme potrebno za kupovinu svakom kupcu n
    int *p;      //vreme kad se udruzuju susedni kupci n-1
    int *opt;

    scanf("%d", &n);

    t = (int *)malloc((n + 1) * sizeof(int));
    p = (int *)malloc((n + 1) * sizeof(int));
    opt = (int *)malloc((n + 1) * sizeof(int));

    //vreme pojedinacno
    for (i = 1; i <= n; i++)
    {
        scanf("%d", &t[i]);
    }
    //vreme udruzenih kupaca
    for (i = 1; i < n; i++)
    {
        scanf("%d", &p[i]);
    }

    opt[0] = 0;
    opt[1] = t[1];

    for (i = 2; i <= n; i++)
    {
        if (opt[i - 2] + p[i - 1] > opt[i - 1] + t[i])
            opt[i] = opt[i - 1] + t[i];
        else
        {
            opt[i] = opt[i - 2] + p[i - 1];
        }
    }

    printf("\n\n%d\n", opt[n]);
    return 0;
}