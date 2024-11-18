//Problem ranca
//Druga varijanta - od svake vrste predmeta postoji po jedan primerak

#include <stdio.h>
#include <stdlib.h>

int main() {
	
	int **opt, *zapr, *vred, n, m;
	int i,j;
	
	scanf("%d%d", &n, &m);
	
	zapr = (int *)malloc((m+1)*sizeof(int));
	vred = (int *)malloc((m+1)*sizeof(int));

	opt = (int **)malloc((m+1)*sizeof(int *));
	
	for (i=0; i<=m; i++)
		opt[i] = (int *)malloc((n+1)*sizeof(int));
	// opt[i][j] - vrednost u rancu zapremine j koji je optimalno popunjen predmetima od 1 do i
		
	for (i=1; i<=m; i++)
		scanf("%d", &zapr[i]);
		
	for (i=1; i<=m; i++)
		scanf("%d", &vred[i]);
		
	for (i=0; i<=m; i++)
		opt[i][0] = 0;
		
	for (i=0; i<=n; i++)
		opt[0][i] = 0;
		
	for (i=1; i<=m; i++) {	//predmeti
		for (j=1; j<=n; j++) {	//zapremine
			if (zapr[i]<=j 
				&& opt[i-1][j-zapr[i]]+vred[i] > opt[i-1][j]) {
					opt[i][j] = opt[i-1][j-zapr[i]]+vred[i];
			}
			else
				opt[i][j] = opt[i-1][j];
		}
	}
		
	printf("Optimalna vrednost u rancu je %d.\n", opt[m][n]);
	
	//Rekonstrukcija
	printf("Predmeti u rancu su:\n");
	i=m; j=n;
	while (opt[i][j]!=0) {
		if (opt[i][j] == opt[i-1][j]) {
			i--;
		}
		else {
			printf("%d ", i);
			j -= zapr[i];
			i--;
		}
	}
	printf("\n");
	
	return 0;
	
}









