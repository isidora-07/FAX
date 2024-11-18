#include <stdio.h>
#include <string.h>
#include <math.h>

typedef struct{
	char ime[20];
	int god;
	char c;
	int red;
}kupac;

int ucitaj(char im[], kupac k[]){
	FILE *f=fopen(im,"r");
	int i=0;
	while(!feof(f)){
		fscanf(f,"%s%d%c%d",k[i].ime,&k[i].god,&k[i].c,&k[i].red);
		i++;
	}
	fclose(f);
	return i;
}

int min(kupac k[], int n, int niz[]){


}

void ispis(FILE *f, kupac k[], int n){
	int i;
	for(i=0;i<n;i++)
		fprintf(f,"%d\t%s\t%d\t%c\n",k[i].red,k[i].ime,k[i].god,k[i].c);
}


int main(){
	kupac kupci[100];
	int n;
	char ulaz[10],izlaz[10];
	scanf("%s",ulaz);
	scanf("%s",izlaz);
	FILE *in,*out;
	n=ucitaj(ulaz,kupci);
	out=fopen(izlaz,"w");
	ispis(out,kupci,n);
	fclose(out);
}
