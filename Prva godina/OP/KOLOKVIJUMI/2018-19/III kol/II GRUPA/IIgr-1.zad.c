#include <stdio.h>
#include <string.h>

typedef struct{
	int ID_oblika,kolicina;
	char boja[20];
}LegoKockica;

typedef struct{
	char boja[20];
	int br_razlicitih_kockica;
	LegoKockica k[50];
}LegoKutija;

int UnosLegoKockica(FILE *f, char kockice[], LegoKockica a[]){
	f=fopen(kockice,"r");
	int n;
	fscanf(f,"%d",&n);
	int i;
	for(i=0;i<n;i++){
		fscanf(f,"%d",&a[i].ID_oblika);
		fscanf(f,"%s",a[i].boja);
		fscanf(f,"%d",&a[i].kolicina);
	}
	fclose(f);
	
	return n;
}

int UnosKutija(FILE *f, char kutije[], LegoKutija a[]){
	f=fopen(kutije,"r");
	int i=0;
	while(!feof(f)){
		fscanf(f,"%s",a[i].boja);
		a[i].br_razlicitih_kockica=0;
		
		i++;
	}
	fclose(f);
	
	return i;
}

void IspisKutije(FILE *f, LegoKutija a[]){
	
	
}

int main(){
	char kock[20],kut[20],izlaz[20],iz1[20],iz2[20],iz3[20];
	scanf("%s%s%s",kock,kut,izlaz);
	LegoKockica kockice[50];
	LegoKutija kutije[50];
	
}
