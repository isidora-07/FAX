#include <stdio.h>
#include <string.h>

int UnosIgraca(FILE *f, char naziv[20], int niz[]){
	f=fopen(naziv,"r");
	int n;
	fscanf(f,"%d",&n);
	int i;
	for(i=0;i<n;i++)
		fscanf(f,"%d",&niz[i]);
	fclose(f);
	
	return n;
}

int UnosGlasova(FILE *f, char naziv[20], int niz[]){
	f=fopen(naziv,"r");
	int i;
	i=0;
	while(!feof(f)){
		fscanf(f,"%d",&niz[i]);
		i++;
	}
	fclose(f);
	
	return i;
}

void ispisIgraca(FILE *f, int ig[], int gl[],int ni){
	int i;
	for(i=0;i<ni;i++)
		fprintf(f,"%d    %d\n",ig[i],gl[i]);
}

void ispisGlasova(FILE *f, int glasovi[], int ng){
	int i;
	for(i=0;i<ng;i++)
		fprintf(f,"%d\n",glasovi[i]);
}

int IzbaciSaK(int niz[], int n, int k){
	int i;
	for(i=k;i<n-1;i++)
		niz[i]=niz[i+1];
	n--;
	
	return n;
}

int izbaciNevazece(int glasovi[], int ng){
	int i,j;
	int pom=ng/2;
	for(i=0;i<ng/2-1;i++){
		for(j=i+1;j<ng/2;j++)
			if(glasovi[i]==glasovi[j]){
				ng=IzbaciSaK(glasovi,ng,j);
				pom--;
				j--;
			}
	}
	
	for(i=pom;i<ng-1;i++)
		for(j=i+1;j<ng;j++)
			if(glasovi[i]==glasovi[j]){
				ng=IzbaciSaK(glasovi,ng,j);
				j--;
			} 
			
	return ng;		
}

void Prebroj(int glasovi[], int gl[], int sifre_igraca[], int ni, int ng){
	int i,j;
	for(i=0;i<ni;i++)
	{
		for(j=0;j<ng;j++)
			if(glasovi[j]==sifre_igraca[i])
				gl[i]++;
	}
	
}

int main(){
	char nba[20],gl1[20],izlaz[20];
	scanf("%s%s%s",nba,gl1,izlaz);
	int sifre_igraca[50],glasovi[50];
	int gl[50];
	FILE *in1,*in2,*out;
	int ni;
	ni=UnosIgraca(in1,nba,sifre_igraca);
	int i;
	for(i=0;i<ni;i++)
		gl[i]=0;
	
	out=fopen(izlaz,"w");
	ispisIgraca(out,sifre_igraca,gl,ni);	
	fclose(out);
	
	int ng;
	ng=UnosGlasova(in2,gl1,glasovi);
	
	out=fopen(izlaz,"a");
	fprintf(out,"\n----------------------------------------------\n");
	ispisGlasova(out,glasovi,ng);
	fclose(out);	
	
	out=fopen(izlaz,"a");
	fprintf(out,"\n----------------------------------------------\n");
	ng=izbaciNevazece(glasovi,ng);
	ispisGlasova(out,glasovi,ng);		
	fclose(out);	
	
	out=fopen(izlaz,"a");
	fprintf(out,"\n----------------------------------------------\n");
	Prebroj(glasovi,gl,sifre_igraca,ni,ng);
	ispisIgraca(out,sifre_igraca,gl,ni);
	fclose(out);
	
}
