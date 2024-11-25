#include <stdio.h>
 
int main()
{
    int n, k = 0, podeljeno = 0;
    scanf("%d", &n);
 
    // na koliko dece je podeljeno
    while (podeljeno + (k + 1) <= n)
    {
        k++;
        podeljeno += k;
    }
 
    int niz[k];
    for (int i = 0; i < k; i++)
        niz[i] = i + 1;

    // visak se dodaje poslednjem detetu
    niz[k - 1] += n - podeljeno;
 
    printf("%d\n", k);
    for (int i = 0; i < k; i++)
        printf("%d ", niz[i]);
 
    return 0;
}