#include <stdio.h>
#include <string.h>

int Najduza(FILE *f1, FILE *f2)
{
int max = 0;
int i = 1;
char linija[255];
int duzina;
while( !feof(f1))
{
//Ucitaj celu liniju
//do 255 karaktera
fgets(linija, 255, f1);
duzina = strlen(linija);
if(duzina > max)
max = duzina;
fprintf(f2, "%d %s\n", i++, linija);
}
return max;
}

main(){
FILE *f1, *f2;
char nazivFajla1[20], nazivFajla2[20];
printf("Unesite naziv prve datoteke:");
scanf("%s", nazivFajla1);
printf("Unesite naziv druge datoteke:");
scanf("%s", nazivFajla2);
f1 = fopen(nazivFajla1, "r");
f2 = fopen(nazivFajla2, "w");
printf("Najduza = %d", Najduza(f1, f2));
fclose(f1);
fclose(f2);
}
