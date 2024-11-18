/*
• Dat je niz A od N celih brojeva
• Odrediti podniz niza A čiji zbir elemenata je maksimalan, a u kome
    nema susednih elemenata niza A.
• Smatrati da prazan niz ima zbir elemenata 0.

    TEST PRIMERI
    7
    3 5 2 4 3 1 6

    7
    3 -5 2 4 -3 -1 6

*/

#include <stdio.h>
#include <stdlib.h>

int max(int a, int b)
{
    return a > b ? a : b;
}

int nadji_optimalne(int opt[], int a[], int i)
{
    if(i > 0)
        if(opt[i] == opt[i-1])
            nadji_optimalne(opt, a, i-1);
        else
        {
            nadji_optimalne(opt, a, i-2);
            printf("%d ", a[i]);
        }
}

int main(int argc, char *argv)
{
    int *a, i, n;
    int *opt;

    scanf("%d", &n);

    a = (int *) malloc ((n+1) * sizeof(int));
    opt = (int *) malloc ((n+1) * sizeof(int));

    for(i = 1; i <= n; i++)
    {
        scanf("%d", &a[i]);
    }

    //pronalaznje najveceg zbira
    opt[0] = 0;
    opt[1] = max(0, a[1]);
    for (i = 2; i <= n; i++)
    {
        opt[i] = max(a[i] + opt[i-2], opt[i-1]);
    }
    printf("Najveca suma je: %d.\n", opt[n]);

    //ispis svih elemenata optimalnog niza 
    for(i=0;i<=n;i++)
        printf("%d\t", opt[i]);
      printf("\n");  
    
    //rekonstrukcija resenja
    for (i = n; i > 0; i--)
    {
        if(opt[i] != opt[i-1])
        {
            printf("%d ", a[i]);
            i--;
        }
    }
    printf("\n");


    //ispis rekonstrukcije u drugom redosledum rekurzivno
    nadji_optimalne(opt, a, n);

    return 0;
}