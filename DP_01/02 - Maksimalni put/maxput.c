/*
Data je matrica A sa M vrsta i N kolona, 
popunjena celim brojevima. 
Sa svakog polja je dozvoljeno preci samo 
na polje ispod ili na polje desno od tog polja. 
Potrebno je izabrati put od gornjeg levog do donjeg desnog polja 
tako da zbir brojeva u poljima preko kojih se ide 
bude maksimalan. Ispisati vrednost optimalnog puta, 
a zatim i put kao niz koordinata polja preko kojih se ide.
*/

#include <stdio.h>
#include <stdlib.h>

int **a, **opt, m, n;

void ispis(int i, int j) {
	int k;
	
	if (i>1 && j>1) {
		if (opt[i-1][j] > opt[i][j-1]) {
			ispis(i-1, j);
		}
		else {
			ispis(i, j-1);
		}
		printf("(%d,%d) ", i, j);
	}
	
	if (i==1) {
		for (k=1; k<=j; k++) {
			printf("(%d,%d) ", i, k);
		}
	}
	
	if (j==1) {
		for (k=1; k<=i; k++) {
			printf("(%d,%d) ", k, i);
		}
	}
}

main() {
	int i, j;
	
	scanf("%d%d", &m, &n);
	
	a = (int**)malloc((m+1)*sizeof(int *));
	for (i=1; i<=m; i++)
		a[i] = (int *)malloc((n+1)*sizeof(int));
		
	opt = (int**)malloc((m+1)*sizeof(int *));
	for (i=1; i<=m; i++)
		opt[i] = (int *)malloc((n+1)*sizeof(int));
	
	for (i=1; i<=m; i++)
		for (j=1; j<=n; j++)
			scanf("%d", &a[i][j]);
	
	opt[1][1] = a[1][1];
	for (j=2; j<=n; j++)
		opt[1][j] = opt[1][j-1] + a[1][j];
	for (i=2; i<=m; i++)
		opt[i][1] = opt[i-1][1] + a[i][1];
		
	for (i=2; i<=m; i++)
		for (j=2; j<=n; j++) {
			if (opt[i-1][j] > opt[i][j-1]) {
				opt[i][j] = opt[i-1][j] + a[i][j];
			}
			else {
				opt[i][j] = opt[i][j-1] + a[i][j];
			}
		}
		
	printf("Maksimalni zbir je %d.\n", opt[m][n]);
	printf("Dobija se prolaskom kroz sledeca polja:\n");
	
	ispis(m,n);
	printf("\n");
}

























