#include <stdio.h>
#include <stdlib.h>


#define W_MAX 100
int *opt_old, *opt;

int max(int a, int b, int c)
{
    if(a>=b && a>=c)
        return a;
    if(b>=a && b>=c)
        return b;
    return c;
}



void problem()
{
    int *temp;
    int i, j, a, c;
    int h, w;
    scanf("%d%d", &h, &w);

    for(i=0; i<w; i++)
    {
        opt[i] = 0;
    }
    for(i=0; i<h; i++)
    {
        temp = opt;
        opt = opt_old;
        opt_old = temp;
        for(j=0; j<w;j++)
        {
            scanf("%d", &opt[j]);
            if(j>0)
                a = opt_old[j - 1];
            else
                a = -1;
            if(j + 1 < w)
                c = opt_old[j + 1];
            else
                c = -1;
            
            opt[j] = opt[j] + max(a, opt_old[j], c);
            print("(%d - %d ) : %d\n", i, j, opt[j]);

        }
    }

    int optimalni = opt[0];
    for(i=0; i<w; i++)
        if(optimalni < opt[i])
        {
            optimalni = opt[i];
        }
    printf("%d\n", optimalni);

}

int main()
{
    int T;
    scanf("%d",&T);
    opt_old=(int*)malloc(W_MAX * sizeof(int));
    opt = (int*)malloc(W_MAX * sizeof(int));
    int tt;
    for(tt=0; tt<T; tt++)
    {
        problem();
    }
    return 0;
}
