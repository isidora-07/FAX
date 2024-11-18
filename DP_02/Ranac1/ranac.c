// Problem ranca
// Prva varijanta - beskonacno mnogo primeraka za svaku vrstu predmeta
//
// Provalnik ima ranac zapremine N. Pljacka prostoriju u kojoj se
// nalazi M razlicitih vrsta vrednosnih predmeta. Za svaki predmet 
// je data njegova vrednost vred[i] i njegova zapremina zapr[i], i=1,...,M. 
// Broj primeraka od svake vrste predmeta je neogranicen. Potrebno je
// popuniti ranac predmetima tako da vrednost predmeta u rancu bude
// najveca moguca.

#include <stdio.h>
#include <stdlib.h>

int main() {
	int *opt, *pred;
	int n, m;
	int *zapr, *vred;
	int v, i;
	
	//Unos zapremine ranca i broja predmeta
	scanf("%d%d",&n,&m);
	
	//Alociranje nizova
	opt = (int *)malloc((n+1)*sizeof(int)); 	// opt[v] - vrednost u optimalno popunjenom rancu zapremine v
	pred = (int *)malloc((n+1)*sizeof(int));	// pred[i] - poslednji predmet stavljen u ranac da bi se dopunio do zapremine v.
	
	zapr = (int *)malloc((m+1)*sizeof(int));
	vred = (int *)malloc((m+1)*sizeof(int));
	
	//Unos zapremina predmeta
	for (i=1; i<=m; i++)
		scanf("%d",&zapr[i]);
	
	//Unos vrednosti predmeta
	for (i=1; i<=m; i++)
		scanf("%d",&vred[i]);
		
	//Racunanje optimuma
	opt[0] = 0;
	pred[0] = 0;
	
	//Po zapreminama
	for (v=1; v<=n; v++) {	//Popunjavaju se sve zapremine v od 1 do n
		opt[v] = 0; pred[v] = 0;
		//Po predmetima
		for (i=1; i<=m; i++) {	//Proveravaju se svi predmeti i od 1 do m
			if (zapr[i]<=v) {
				if (vred[i]+opt[v-zapr[i]] > opt[v]) {
					opt[v] = vred[i]+opt[v-zapr[i]];
					pred[v] = i;
				}
			}
		}
	}
	
	printf("Maksimalna vrednost u rancu je %d.\n", opt[n]);
	
	//Rekonstrukcija
	printf("Predmeti u rancu su:\n");
	i=n;
	while(pred[i]>0) {
		printf("%d ", pred[i]);
		i -= zapr[pred[i]];
	}
	printf("\n");
	
	return 0;
	
}













