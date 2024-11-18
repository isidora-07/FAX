#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct knjiga{
	char *naslov;
	char *autor;
	char *zanr;
	int prodate_pocetak_godine;
	int ostali; //preostali primerci
	struct knjiga *sledeci;
};

#define alloc(x) x = (struct knjiga*)malloc(sizeof(struct knjiga))

void dodaj(char naslov[], char autor[], char zanr[], int t1, int t2, struct knjiga **glava){
	struct knjiga *temp;
	alloc(temp);
	temp->naslov = (char*)malloc(strlen(naslov)*sizeof(char));
	naslov[strlen(naslov)-1]= '\0';
	strcpy(temp->naslov, naslov);
	temp->autor = (char*)malloc(strlen(autor)*sizeof(char));
	autor[strlen(autor)-1]= 0;
	strcpy(temp->autor, autor);
	temp->zanr = (char*)malloc(strlen(zanr)*sizeof(char));
	zanr[strlen(zanr)-1]= 0;
	strcpy(temp->zanr, zanr);
	temp->prodate_pocetak_godine = t1;
	temp->ostali = t2;
	temp->sledeci = NULL;
	
	if(*glava == NULL)
		*glava = temp;
	else{
		struct knjiga *pom = *glava;
		
		while(pom->sledeci)
			pom = pom->sledeci;
		
		pom->sledeci = temp;
		
	}
}

struct knjiga *kreiraj(){
	struct knjiga *glava = NULL;
	char naslov[20], autor[20], zanr[20];
	int t1, t2, n;
	
	FILE *f = fopen("knjiga.txt", "r");
	fscanf(f, "%d", &n);
	while(n){
		fgets(naslov, 20, f);
		fgets(autor, 20, f);
		fgets(zanr, 20, f);
		fscanf(f, "%d", &t1);
		fscanf(f, "%d", &t2);
		dodaj(naslov, autor, zanr, t1, t2, &glava);
		fgetc(f);
		n--;
	}
	fclose(f);
	
	return glava;
}

void ispisi(struct knjiga *p){
	if(p){
		while(p){
			printf("Naslov: %s, autor: %s, zanr: %s, prodate: %d, ostalo: %d\n", p->naslov, p->autor, p->zanr, p->prodate_pocetak_godine, p->ostali);
			p = p->sledeci;
		}
	}
	else printf("LISTA JE PRAZNA");
}


int main(){
	struct knjiga *glava = NULL;
	glava = kreiraj();
	ispisi(glava);
	printf("\n\n");
	
}
