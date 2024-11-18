#include <stdio.h>
#include <string.h>

void unosN(FILE *f, int niz[], int n){
	int i;
	for(i = 0; i < n ; i++)
		fscanf(f, "%d", &niz[i]);
}

int dajBrojRazlicitih(int niz[], int n){
	int i, j, b;
	int nemaIstog;
	b = 0;
	for(i = 0; i < n; i++){
		nemaIstog = 1;
		for(j = i + 1; j < n; j++)
			if(niz[i] == niz[j]){
				nemaIstog = 0;
				break;
			}
		if(nemaIstog)
			b++;
	}
	return b;
}
int main(){
	int n;
	int niz[50];
	FILE * citaj;
	char nazivFajla[20];
	scanf("%s", nazivFajla);
	citaj = fopen(nazivFajla, "r");
	while(!feof(citaj)){
		n = 0;
		fscanf(citaj, "%d", &n);
		unosN(citaj, niz, n);
		printf("%d\n", dajBrojRazlicitih(niz,n));
	}
	fclose(citaj);
}

