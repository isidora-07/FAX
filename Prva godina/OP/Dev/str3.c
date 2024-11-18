#include <stdio.h>

typedef struct{
	char ime[20];
	int br;
} super_broj;

int UnosBrojeva(char naziv[],super_broj niz[]){
	FILE *f;
	f=fopen(naziv,"r");
	int i=0;
	while(!feof(f)){
		fscanf(f,"%s",niz[i].ime);
		fscanf(f,"%d",&niz[i].br);
		i++;		
	}
	fclose(f);
	return i;
}

void ispisBrojeva(char naziv[],super_broj niz[],int n){
	int i;
	for(i=0;i<n;i++){
		printf("%s\n",niz[i].ime);
		printf("%d\n",niz[i].br);
	}
}

void formMatrica(super_broj niz[],int n,super_broj mat[30][30]){
	int i,j;

	for(j=0;j<n;j++)
		mat[0][j]=niz[i];
	super_broj pom;	
	for(i=1;i<n;i++){
		pom=niz[0];
		for(j=0;i<n-1;j++)
			niz[j]=niz[j+1];
		niz[j]=pom;	
		
		for(j=0;j<n;j++)
			mat[i][j]=niz[j];
	}		
}

void ispisMatrice(char naziv[],super_broj mat[30][30],int n){
	FILE *f;
	f=fopen(naziv,"w");
	int i,j;
	for(i=0;i<n;i++){
		for(j=0;j<n;j++) fprintf(f,"%s %d\t",mat[i][j].ime,mat[i][j].br);
		fprintf(f,"\n");
	}
	
	fclose(f);
}

int main(){
	int n;
	super_broj niz[20],mat[20][20];
	char nazivF[20],izlazF[20];
	scanf("%s%s",nazivF,izlazF);
	n=unosBrojeva(nazivF,niz);
	ispisBrojeva(izlazF,niz,n);
	forMatrica(niz,n,mat);
	ispisMatrice(izlazF,mat,n);
	
}
