#include <stdio.h>
#include <stdlib.h>

void rekonstrukcija(int *brojNocenja, int *rek, int i) {
	if (rek[i]>0) {
		rekonstrukcija(brojNocenja, rek, i-brojNocenja[rek[i]]);
		printf("%d\n", rek[i]);
	}
	else if (rek[i]==-1) {
		rekonstrukcija(brojNocenja, rek, i-1);
	}
}

int main() {
	int n, maks, i, j;
	int *danUlaska, *brojNocenja, *cena;
	int *danIzlaska;
	int *opt, *rek;
	
	scanf("%d", &n);
	danUlaska = (int *)malloc((n+1)*sizeof(int));
	danIzlaska = (int *)malloc((n+1)*sizeof(int));
	brojNocenja = (int *)malloc((n+1)*sizeof(int));
	cena = (int *)malloc((n+1)*sizeof(int));
	maks = 1;
	for (i=1; i<=n; i++) {
		scanf("%d%d%d", &danUlaska[i], &brojNocenja[i], &cena[i]);
		danIzlaska[i] = danUlaska[i]+brojNocenja[i];
		if (danIzlaska[i] > maks) {
			maks = danIzlaska[i];
		}
	}
	opt = (int *)malloc((maks+1)*sizeof(int));
	rek = (int *)malloc((maks+1)*sizeof(int));
	opt[0] = rek[0] = 0;
	for (i=1; i<=maks; i++) { // dani
		opt[i] = opt[i-1];
		rek[i] = -1;
		for (j=1; j<=n; j++) { // rezervacije
			if (danIzlaska[j]==i) {
				if (opt[i-brojNocenja[j]]+cena[j]>opt[i]) {
					opt[i] = opt[i-brojNocenja[j]]+cena[j];
					rek[i] = j;
				}
			}
		}
	}
/*	
	printf("Opt\n");
	for(i=0; i<=maks; i++)
		printf("%d ", opt[i]);
	printf("\n");
*/	
	printf("%d\n", opt[maks]);
	rekonstrukcija(brojNocenja, rek, maks);

	return 0;
}
