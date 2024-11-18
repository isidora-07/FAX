#include <stdio.h>

typedef struct{
	char ime[20];
	int bodovi;
	int kartoni;
} ekipa;

typedef ekipa niz_ekipa[100];

ekipa ucitaj_ekipu(){
	ekipa e;
	printf("Unesi naziv ekipe:\n");
	scanf("%s",e.ime);
	printf("Unesi broj ostvarenih bodova\n");
	scanf("%d",&e.bodovi);
	printf("Unesi broj zutih kartona:\n");
	scanf("%d",&e.kartoni);
	return e;
}

void sortiraj(niz_ekipa lista,int n){
	int i,j;
	ekipa pom;
	for(i=0;i<n-1;i++)
		for(j=i+1;j<n;j++)
			if(lista[i].bodovi<lista[j].bodovi){
				pom=lista[i];
				lista[i]=lista[j];
				lista[j]=pom;			
			}	
}

int ferplej(niz_ekipa lista,int n){
	int i;
	int min_k,min_ind;
	min_k=lista[0].kartoni;
	min_ind=0;
	for(i=1;i<n;i++)
		if(lista[i].kartoni<min_k){
			min_k=lista[i].kartoni;
			min_ind=i;
		}
	return min_ind;	
}

void stampaj_tabelu(niz_ekipa lista,int n){
	int i;
	for(i=0;i<n;i++)
		printf("%20s%10d%10d\n",lista[i].ime,lista[i].bodovi,lista[i].kartoni);
}

int main(){
	niz_ekipa lista;
	int n,i;
	//br ekipa
	scanf("%d",&n);
	
	for(i=0;i<n;i++)
		lista[i]=ucitaj_ekipu();
	sortiraj(lista,n);
	stampaj_tabelu(lista,n);
	i=ferplej(lista,n);
	printf("%s\n",lista[i].ime);	
}
