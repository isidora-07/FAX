#include<stdio.h>

int daLiJePrugasta (int a[50][50], int m, int n)
{
	int i, j;
	
	for (i=0; i<m; i+=2)
		for (j=0; j<n; j++)	
			if (a[i][j]!=1 && a[i+1][j]!=0)
				return 0;
				
						
	return 1;
	
}

main()
{
	
	int m, n, i, j, a[50][50];
	
	scanf ("%d%d", &m, &n);
	
	for (i=0; i<m; i++)
		for (j=0; j<n; j++)
			scanf ("%d", &a[i][j]);
			
			
	if (daLiJePrugasta(a,m,n))
		printf ("Jeste");
	else
		printf ("Nije");	
}
