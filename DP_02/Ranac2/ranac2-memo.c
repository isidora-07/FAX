//Problem ranca
//Druga varijanta - od svake vrste predmeta imamo po jedan primerak
//Optimizacija memorije - umesto matrice opt koristimo dva niza, pret i tren
//Ovako se moze izracunati optimalna vrednost, ali se ne moze izvrsiti rekonstrukcija

#include <stdio.h>
#include <stdlib.h>


int main() {
	
	int *pret, *tren, *vred, *zapr, n, m, *temp;
	int i,j;
	
	scanf("%d%d", &n, &m);
	
	zapr = (int *)malloc((m+1)*sizeof(int));
	vred = (int *)malloc((m+1)*sizeof(int));
	
	pret = (int *)malloc((n+1)*sizeof(int)); //pret[j] umesto opt[i-1][j]
	tren = (int *)malloc((n+1)*sizeof(int)); //tren[j] umesto opt[i][j]
		
	for (i=1; i<=m; i++)
		scanf("%d", &zapr[i]);
		
	for (i=1; i<=m; i++)
		scanf("%d", &vred[i]);
		
	for (i=0; i<=n; i++)
		tren[i] = 0;
		
	for (i=1; i<=m; i++) {	//predmeti
		temp = pret;
		pret = tren;
		tren = temp;	//da se ne bi ponovo alocirala memorija
		
		tren[0] = 0;
		for (j=1; j<=n; j++) {	//zapremine
			if (zapr[i]<=j 
				&& pret[j-zapr[i]]+vred[i] > pret[j]) {
					tren[j] = pret[j-zapr[i]]+vred[i];
			}
			else
				tren[j] = pret[j];
		}
	}
		
	printf("Optimalna vrednost u rancu je %d.\n", tren[n]);
	
	return 0;
	
}









