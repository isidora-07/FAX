#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct smestaj{
	int br_jedinica, br_osoba, cena;	
};

struct hotel{
	char *ime;
	char *mesto;
	int tip_smestaja; //razliciti tipovi smestaja koji se nude
	struct smestaj *s;
	struct hotel *sledeci;
};

struct agencija{
	char *ime;
	int br_hotela;
	char *ime_hotela;
	char *mesto_hotela;
	struct hotel *hoteli;
	struct agencija *sledeci;
};

#define allocHotel(x) x = (struct hotel*)malloc(sizeof(struct hotel))

void kreiraj(struct hotel **glava, char ime[], char mesto[], int n, int br_jedinica, int br_osoba, int cena){
	int i;
	struct hotel *temp;
	allocHotel(temp);
	temp->ime = (char*)malloc(strlen(ime)*sizeof(char));
	strcpy(temp->ime, ime);
	temp->mesto = (char*)malloc(strlen(mesto)*sizeof(char));
	strcpy(temp->mesto, mesto);
	temp->tip_smestaja = n;
	temp->sledeci = NULL;
	
	if(*glava == NULL)
		*glava = temp;
	else{
		struct hotel *pom = *glava;
		
		while(pom->sledeci)
			pom = pom->sledeci;
		
		pom->sledeci = temp;
	}
}

struct hotel *ucitajHotele(){
	struct hotel *glava = NULL;
	char ime[20], mesto[20];
	int n, br_jedinica, br_osoba, cena;
	
	FILE *f = fopen("Hoteli.txt", "r");
	
	while(!feof(f)){
		fscanf(f, "%s", ime);
		fscanf(f, "%s", mesto);
		fscanf(f, "%d", &n);
		for(i=0; i<n; i++){
			fscanf(f, "%d%d%d", &br_jedinica, &br_osoba, &cena);
			kreiraj(&glava, ime, mesto, n, br_jedinica, br_osoba, cena);	
		}
		fgetc(f);
	}
	
	fclose(f);
	return(glava);
	
}



int main(){
	
}
