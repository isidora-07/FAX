#include <stdio.h>
#include <stdlib.h>

int max(int a, int b, int c) {
    if(a >= b && a >= c) 
        return a;
    if(b >= a && b >= c)
        return b;
    return c;
}

int maksimalniBrojKamenja(int **plocice, int h, int w) {
    int i, j;

    int **opt = (int**)malloc((h+1)*sizeof(int*));
    for(i=0; i<=h; i++)
        opt[i] = (int*)malloc((w+1)*sizeof(int));

    for (j = 1; j <= w; j++) 
        opt[1][j] = plocice[1][j];
    
     for (i = 2; i <= h; i++) {
        for (j = 1; j <= w; j++) {
            if (j == 1)
                opt[i][j] = plocice[i][j] + max(opt[i-1][j], opt[i-1][j+1], 0);
            else if (j == w)
                opt[i][j] = plocice[i][j] + max(opt[i-1][j], opt[i-1][j-1], 0);
            else
                opt[i][j] = plocice[i][j] + max(opt[i-1][j], opt[i-1][j-1], opt[i-1][j+1]);
        }
    }

    int maksimalni = opt[h][1];
    for (j = 2; j <= w; j++) {
        if (opt[h][j] > maksimalni)
            maksimalni = opt[h][j];
    }

    return maksimalni;
}

int main() {
    int i, j, m, T, h, w;

    scanf("%d", &T);

    while (T--) {
        scanf("%d%d", &h, &w);

        int **plocice = (int**)malloc((h+1)*sizeof(int*));
        for(i=0; i<=h; i++)
            plocice[i] = (int*)malloc((w+1)*sizeof(int));

        for(i=1; i<=h; i++) {
            for(j=1; j<=w; j++) {
                scanf("%d", &plocice[i][j]);
            }
        }

        printf("%d\n", maksimalniBrojKamenja(plocice, h, w));

        for (i = 0; i <= h; i++)
            free(plocice[i]);
        free(plocice);
    }

    return 0;
}