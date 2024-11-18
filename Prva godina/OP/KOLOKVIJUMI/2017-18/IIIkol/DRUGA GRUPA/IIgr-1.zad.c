#include <stdio.h>
#include <string.h>

typedef struct{
	int idUcenika;
	char ime[20];
	char prezime[20];
	float prosek;
}ucenik;

typedef struct{
	int idUcionice;
	int slobodno_h,slobodno_min;
	int zauzeto_h,zauzeto_min;
	int zauzetaMesta;
}ucionica;

int unosUcenika(FILE *f, char naziv1[], ucenik ucenici[]){
	f=fopen(naziv1,"r");
	int n;
	fscanf(f,"%d",&n);
	int i;
	for(i=0;i<n;i++){
		fscanf(f,"%s",ucenici[i].ime);
		fscanf(f,"%s",ucenici[i].prezime);
		fscanf(f,"%f",&ucenici[i].prosek);
		ucenici[i].idUcenika=i+1;
	}
	fclose(f);
	return n;
}

void ispisUcenika(FILE *f, char izlaz[], int n, ucenik ucenici[]){
	int i;
	for(i=0;i<n;i++){
		fprintf(f,"%d\t",ucenici[i].idUcenika);
		fprintf(f,"%s\t",ucenici[i].ime);
		fprintf(f,"%s\t",ucenici[i].prezime);
		fprintf(f,"%1.1f\n",ucenici[i].prosek);
	}
}

int unosUcionica(FILE *f, char naziv2[], ucionica ucionice[]){
	f=fopen(naziv2,"r");
	int i=0;
	while(!feof(f)){
		fscanf(f,"%d",&ucionice[i].slobodno_h);
		fscanf(f,"%d",&ucionice[i].slobodno_min);
		fscanf(f,"%d",&ucionice[i].zauzeto_h);
		fscanf(f,"%d",&ucionice[i].zauzeto_min);
		ucionice[i].idUcionice=i+1;
		ucionice[i].zauzetaMesta=0;
		i++;
	}
	fclose(f);

	return i;
}

void ispisUcionica(FILE *f, ucionica ucionice[], int n){
	int i;
	for(i=0;i<n;i++){
		fprintf(f,"%d\t",ucionice[i].idUcionice);
		fprintf(f,"%d\t",ucionice[i].slobodno_h);
		fprintf(f,"%d\t",ucionice[i].slobodno_min);
		fprintf(f,"%d\t",ucionice[i].zauzeto_h);
		fprintf(f,"%d\t",ucionice[i].zauzeto_min);
		fprintf(f,"%d\n",ucionice[i].zauzetaMesta);
	}
}

int main(){
	char ulaz1[20],ulaz2[20],izlaz[20];
	scanf("%s",ulaz1);
	scanf("%s",ulaz2);
	scanf("%s",izlaz);
	ucenik ucenici[50];
	ucionica ucionice[50];
	FILE *in1,*in2,*out;
	
	int n1,n2;
	
	out=fopen(izlaz,"w");
	
	n1=unosUcenika(in1,ulaz1,ucenici);
	ispisUcenika(out,izlaz,n1,ucenici);

	fclose(out);
	
	out=fopen(izlaz,"a");
	
	n2=unosUcionica(in2,ulaz2,ucionice);
	ispisUcionica(out,ucionice,n2);
	
	fclose(out);
	
}
