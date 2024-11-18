#include <stdio.h>

typedef struct{
	char ime[10];
	char prezime[20];
	int godRodjenja;	
}osoba;

int main(){
	osoba o1,o2;
	scanf("%s%s%d",o1.ime,o1.prezime,&o1.godRodjenja);
	scanf("%s%s%d",o2.ime,o2.prezime,&o2.godRodjenja);
	
	printf("Osoba1: %s %s %d\nOsoba2: %s %s %d\n",o1.ime,o1.prezime,o1.godRodjenja,o2.ime,o2.prezime,o2.godRodjenja);
	
	if(o1.godRodjenja<o2.godRodjenja)
		printf("Starija osoba:%s %s ",o1.ime,o1.prezime);
	else	printf("Starija osoba:%s %s ",o2.ime,o2.prezime);
}
