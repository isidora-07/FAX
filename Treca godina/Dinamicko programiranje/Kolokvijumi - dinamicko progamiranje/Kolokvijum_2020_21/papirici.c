#include <stdio.h>
#include <stdlib.h>

//sortiraj
void sortiraj(int *a, int n)
{
    int i, j, pom;
    for (i = 1; i <= n - 1; i++)
    {
        for (j = i + 1; j <= n; j++)
        {
            if (a[i] > a[j])
            {
                pom = a[i];
                a[i] = a[j];
                a[j] = pom;
            }
        }
    }
}

int main()
{
    int n; //broj papirica
    int k; //najvise papirica na gomili
    FILE *f = fopen("papirici.in", "r");
    int *papirici;
    int i, j = 0;
    fscanf(f, "%d%d", &n, &k);

    papirici = (int *)malloc((n + 1) * sizeof(int));

    for (i = 1; i <= n; i++)
        fscanf(f, "%d", &papirici[i]);

    fclose(f);

    sortiraj(papirici, n);
   
    int *opt = (int *)malloc((n + 1) * sizeof(int));

    if (k == 2)
    {
        for (i = 1; i <= n; i += 2)
            j += papirici[i + 1] - papirici[i];
    }
    else
    {
        opt[1] = 9999999;
        opt[2] = papirici[2] - papirici[1];
        opt[3] = papirici[3] - papirici[1];
        for (i = 4; i <= n; i++)
        {
            if (opt[i - 2] - papirici[i - 1] < opt[i - 3] - papirici[i - 2])
            {
                opt[i] = opt[i - 2] + papirici[i] - papirici[i - 1];
            }
            else
            {
                opt[i] = opt[i - 3] + papirici[i] - papirici[i - 2];
            }
        }
        j = opt[n];
    }

    f = fopen("papirici.out", "w");
    fprintf(f, "%d", j);
    fclose(f);
}