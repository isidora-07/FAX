//popravni 2018_19//

#include <stdio.h>
#include <stdlib.h>

typedef struct ormar
{
    int sirina;
    int cena;
} Ormar;

typedef struct sto
{
    int sirina;
    int cena;
} Sto;

int main(int argc, char **argv)
{
    int i, j;
    int brOrmara, brStolova, sirinaZida;
    scanf("%d%d", &brOrmara, &brStolova);

    Ormar *ormari;
    Sto *stolovi;

    ormari = (Ormar *)malloc((brOrmara + 1) * sizeof(Ormar));
    stolovi = (Sto *)malloc((brStolova + 1) * sizeof(Sto));

    for (i = 1; i <= brOrmara; i++)
    {
        scanf("%d%d", &ormari[i].sirina, &ormari[i].cena);
    }

    for (i = 1; i <= brStolova; i++)
    {
        scanf("%d%d", &stolovi[i].sirina, &stolovi[i].cena);
    }

    scanf("%d", &sirinaZida);

    //maksimizujemo sirinu minimizujemo cenu
    int *minCena = (int *)malloc((sirinaZida + 1) * sizeof(int));   //opt cena
    int *maxSirina = (int *)malloc((sirinaZida + 1) * sizeof(int)); //max sirina
    int *odabraniOrmari = (int *)malloc((sirinaZida + 1) * sizeof(int));

    minCena[0] = 0;
    maxSirina[0] = 0;

    //BIRAMO REGALE
    //za svaku sirinu zida gledamo najbolji sklop ormara
    for (i = 1; i <= sirinaZida; i++)
    {
        minCena[i] = 0;
        maxSirina[i] = 0;
        odabraniOrmari[i] = 0;
        //gledamo za svaki ormar
        for (j = 1; j <= brOrmara; j++)
        {
            //regal moze da stane na tu sirinu zida
            if (ormari[j].sirina <= i)
            {
                //ako je ovaj regal veci od dosadasnje sirine uzimamo, postavljamu sirinu i cenu
                if (ormari[j].sirina + maxSirina[i - ormari[j].sirina] > maxSirina[i])
                {
                    maxSirina[i] = ormari[j].sirina + maxSirina[i - ormari[j].sirina];
                    odabraniOrmari[i] = j;
                    minCena[i] = ormari[j].cena + minCena[i - ormari[j].sirina];
                }
                //ukoliko vec postoji regal sa tom sirinom, proveravamo koji regal ima manju cenu i njega uzimamo
                //takodje moramo da promenimo odabrani regal
                //max sirinu nije potrebno ponovo promeniti jer je ista
                else if (ormari[j].sirina + maxSirina[i - ormari[j].sirina] == maxSirina[i])
                {
                    if (minCena[i] > ormari[j].cena + minCena[i - ormari[j].sirina])
                    {
                        minCena[i] = ormari[j].cena + minCena[i - ormari[j].sirina];
                        odabraniOrmari[i] = j;
                    }
                }
            }
        }
    }

    int najboljiSto = 1;
    int minimalnaCena;    //najmanja cena za ormare i sto
    int maksimalnaSirina; //ormari plus sto
    int sirinaOrmara;     //sirina ormara bez stola

    //pocetna sirina ormara kada se ubaci 1 sto
    sirinaOrmara = sirinaZida - stolovi[1].sirina;
    //za taj prvi sto racunamo maksimalnu sirinu sa optimalnim ormarima za tu novu sirinu zida koja je ostala za ormare
    maksimalnaSirina = maxSirina[sirinaOrmara] + stolovi[1].sirina;
    //za tu sirinu zida gledmao optimlanu cenu i na nju dodajemo cenu za sto 1
    minimalnaCena = minCena[sirinaOrmara] + stolovi[1].cena;

    //prolazimo kroz sve ostale stolove i trazimo da li ima neki sto koji ce da da vecu sirinu zita sa ormarima
    for (i = 2; i <= brStolova; i++)
    {
        //ostatak zida za ormare
        sirinaOrmara = sirinaZida - stolovi[i].sirina;
        //gledamo da li je novi sto bolji sa dosadasnjim ormarima za tu sirinu koja im je ostala od maksimalne sirine zida
        if (stolovi[i].sirina + maxSirina[sirinaOrmara] > maksimalnaSirina)
        {
            //ako jeste postavljamo novu cenu, novu sirinu zida i biramo najbolji sto
            //taj najbolji sto znaci da sa preostalim prostorom za orpame pokriva najvecu sirinu zida
            maksimalnaSirina = maxSirina[sirinaOrmara] + stolovi[i].sirina;
            minimalnaCena = minCena[sirinaOrmara] + stolovi[i].cena;
            najboljiSto = i;
        }
        //proveravamo ako dva stola daju istu sirinu zida
        else if (stolovi[i].sirina + maxSirina[sirinaOrmara] == maksimalnaSirina)
        {
            //da li neki sto od ta dva moze da ima bolju cenu
            if (stolovi[i].cena + minCena[sirinaOrmara] < minimalnaCena)
            {
                minimalnaCena = minCena[sirinaOrmara] + stolovi[i].cena;
                najboljiSto = i;
            }
        }
    }

    printf("%d %d\n", maksimalnaSirina, minimalnaCena);
    printf("%d\n", najboljiSto);

    i = sirinaZida - stolovi[najboljiSto].sirina;
    while (odabraniOrmari[i] > 0)
    {
        printf("%d ", odabraniOrmari[i]);
        i -= ormari[odabraniOrmari[i]].sirina;
    }
    printf("\n");

    return 0;
}
