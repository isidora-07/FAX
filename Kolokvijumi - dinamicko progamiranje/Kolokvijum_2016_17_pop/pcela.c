#include <stdio.h>
#include <stdlib.h>

typedef struct
{
    float medOdNektara; //koliko meda se dobije od 1mg nektara
    int kolikoPuta;     //koliko puta moze da sleti na taj cvet
} Vrste;

typedef struct
{
    int vrsta;
    int vreme;
    float medOdNektara;
    int kolikoPuta;
} Cvet;

int main(int argc, char **argv)
{
    int M; //broj vrsta cveca
    int N; //broj cveca oko kosnice
    int K; //kolicina nektara koliko pcela moze da ponese
    int T; //vreme koliko moze da leti
    int i, j;
    Vrste *vrste;
    Cvet *cvece;

    scanf("%d", &M);
    vrste = (Vrste *)malloc((M + 1) * sizeof(Vrste));

    for (i = 1; i <= M; i++)
    {
        scanf("%f%d", &vrste[i].medOdNektara, &vrste[i].kolikoPuta);
    }

    scanf("%d", &N);
    cvece = (Cvet *)malloc((N + 1) * sizeof(Cvet));

    int maksimalniBrojLetova = 0;
    for (i = 1; i <= N; i++)
    {
        scanf("%d%d", &cvece[i].vrsta, &cvece[i].vreme);
        cvece[i].medOdNektara = vrste[cvece[i].vrsta].medOdNektara;
        cvece[i].kolikoPuta = vrste[cvece[i].vrsta].kolikoPuta;
        maksimalniBrojLetova += vrste[cvece[i].vrsta].kolikoPuta;
    }

    Cvet *finalCvece = (Cvet *)malloc((maksimalniBrojLetova + 1) * sizeof(Cvet));
    int p = 1;
    for (i = 1; i <= N; i++)
    {
        while (cvece[i].kolikoPuta--)
        {
            finalCvece[p++] = cvece[i];
        }
    }

    scanf("%d%d", &K, &T);
    float **opt;
    opt = (float **)malloc((maksimalniBrojLetova + 1) * sizeof(float *));
    for (i = 0; i <= maksimalniBrojLetova; i++)
        opt[i] = (float *)malloc((T + 1) * sizeof(float));

    for (i = 0; i <= maksimalniBrojLetova; i++)
        opt[i][0] = 0;

    for (i = 0; i <= T; i++)
        opt[0][i] = 0;

    for (i = 1; i <= maksimalniBrojLetova; i++)
    {
        for (j = 1; j <= T; j++)
        {

            opt[i][j] = opt[i - 1][j];
            if (finalCvece[i].vreme <= j)
                if (K * finalCvece[i].medOdNektara + opt[i - 1][j - finalCvece[i].vreme] > opt[i][j])
                {
                    opt[i][j] = K * finalCvece[i].medOdNektara + opt[i - 1][j - finalCvece[i].vreme];
                }
        }
    }
    printf("%.2f", opt[maksimalniBrojLetova][T]);
    return 0;
}
