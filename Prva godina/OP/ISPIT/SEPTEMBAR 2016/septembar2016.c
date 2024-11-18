#include <stdio.h>

typedef struct{
	int x;
}cifra;

typedef struct{
	int n;
	cifra niz[100];
	cifra formirani[100];
}broj;

int ucitaj(broj br[]){
	FILE *f=fopen("brojevi.txt","r");
	int i=0, j;
	while(!feof(f)){
		fscanf(f,"%d", &br[i].n);
		for(j=0; j<br[i].n; j++)
			fscanf(f, "%d", &br[i].niz[j].x);
			
		br[i].formirani[i].x = 0;
		i++;
	}
	fclose(f);

	return i;
}

void ispisi(int n, broj br[]){
	FILE *f = fopen("Izlaz.txt", "w");
	int i, j;
	
	for(i=0; i<n; i++){
		fprintf(f, "Broj cifara %d\n", br[i].n);
		fprintf(f, "NIZ: ");
		for(j=0; j<br[i].n; j++)
			fprintf(f, "%d ", br[i].niz[j].x);
		fprintf(f, "\n");	
	}
	
	fclose(f);
}

long int stepen(int x, int n){
	int i;
	int p = 1;
	
	for(i=0; i<n; i++)
		p *= x;
	
	return p;
}

void form_num(broj br[], int n){
	int i, j;
	for(i=0; i<n; i++){
		j = 0;
		while(br[i].n){
			br[i].formirani[i].x += br[i].niz[j].x*stepen(10, br[i].n - 1); 
			j++;
			br[i].n--;
		}
	}
}

long int sub(int x, int y){
	return x+y;
}

int oduzimanje(int x, int y){
	if(x>y) return x-y;
	else return y-x;
}

void sub_br(int n, broj br[]){
	int i;
	int p, q;
	
	for(i=0; i<n; i+=2){
		p = br[i].formirani[i].x;
		q = br[i+1].formirani[i+1].x;
		printf("%ld\n", sub(p, q)); 
	}
}

void oduz_br(int n, broj br[]){
	int i;
	int p, q;
	
	for(i=0; i<n; i+=2){
		p = br[i].formirani[i].x;
		q = br[i+1].formirani[i+1].x;
		printf("%d\n", oduzimanje(p, q));
	}
}


int main(){
	broj br[100];
	int n;
	n = ucitaj(br);
	ispisi(n, br);
	form_num(br, n);
	sub_br(n, br);
	printf("\n");
	oduz_br(n, br);
}
