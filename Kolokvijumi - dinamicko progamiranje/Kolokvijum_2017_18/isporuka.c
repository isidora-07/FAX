//2017_18
#include <stdio.h>
#include <stdlib.h>

#define BEGIN 6
#define END 24

typedef struct
{
    int isporukaOd;
    int isporukaDo;
    int vremeDoKupca;
    int potrebnoVreme; //do kupca i nazad
} Posiljka;

//soritra isporuke po vremenu od kad kada moze da se isporuci
void SortirajIsporuke(Posiljka *posiljke, int n)
{
    int i, j;
    Posiljka temp;
    for (i = 1; i <= n - 1; i++)
        for (j = i + 1; i <= n; i++)
            if (posiljke[i].isporukaOd > posiljke[i].isporukaOd)
            {
                temp = posiljke[i];
                posiljke[i] = posiljke[j];
                posiljke[j] = temp;
            }
}

//vraca veci broj od a i b
int Max(int a, int b)
{
    return a > b ? a : b;
}

void print(int **dp, int n, int m)
{
    int i, j;
    for (i = 0; i <= n; i++)
    {
        printf("\n");
        for (j = 0; j <= m; j++)
            printf("%d ", dp[i][j]);
    }
}

int main(int argc, char **argv)
{
    int i, j, n;
    Posiljka *posiljke;
    scanf("%d", &n);

    posiljke = (Posiljka *)malloc((n + 1) * sizeof(Posiljka));

    for (i = 1; i <= n; i++)
    {
        scanf("%d%d%d", &posiljke[i].isporukaOd, &posiljke[i].isporukaDo, &posiljke[i].vremeDoKupca);
        posiljke[i].potrebnoVreme = 2 * posiljke[i].vremeDoKupca;
    }

    //sortirati po isporuka od, tj vreme od kada moze da primi porudzbinu
    //ranac2

    //potreban nam je sortiran rasproed isporuka da bi se uklopilo ui ranac 2
    SortirajIsporuke(posiljke, n);

    int **opt;
    //satnica za koju sluzba radi 6 - 24 = 18 podeoka  tj 19 slucajeva
    int satnica = (END - BEGIN) + 1;
    opt = (int **)malloc((n + 1) * sizeof(int *));
    for (i = 0; i <= n; i++)
    {
        opt[i] = (int *)malloc((satnica + 1) * sizeof(int));
    }

    for (i = 0; i <= n; i++)
        opt[i][0] = 0;

    for (j = 0; j <= satnica; j++)
        opt[0][j] = 0;

    int sati;     // j + 5;
    int krenuo;   //vreme kad je krenuo na destinaciju
    int stigao;   // vreme kad je stigao na destinaciju
    int povratak; //kad ase vraca

    //prolazimo kroz porudzbine
    for (i = 1; i <= n; i++)
    {
        //prolazimo kroz sate
        for (j = 1; j <= satnica; j++)
        {
            //PRISTUP TREBA DA BUDE DA SU OVI SATI VREME POVRATKA U SKLADISTE
            sati = j + 5;                              //trenutno vreme kad sam se vratio u skladiste
            krenuo = sati - posiljke[i].potrebnoVreme; //vreme kad je krenuo iz skladista
            stigao = sati - posiljke[i].vremeDoKupca;  //stigao kod kupca
            povratak = sati; //stigao u skladiste

            //mora da krene posle 6 i da se vrati pre 24 u skladiste
            //mora da bude kod klijenta izmedju vremena Od i Do
            if (stigao >= posiljke[i].isporukaOd && stigao <= posiljke[i].isporukaDo && krenuo >= BEGIN && povratak <= END)
            {
                //dodaje se jedan kao vrednost na polje opt i plus iznad levo za vreme potrebno da ode i da se vrati
                opt[i][j] = Max(opt[i - 1][j], 1 + opt[i - 1][j - posiljke[i].potrebnoVreme]);
            }
            else
            {
                //mora da se proveri da li je bolje iznad ili levo
                //jer nije klasican pristup, zbog razlicitih vremena DO
                opt[i][j] = Max(opt[i - 1][j], opt[i][j - 1]);
            }
        }
    }

   
    printf("%d\n", opt[n][satnica]);
    return 0;
}
