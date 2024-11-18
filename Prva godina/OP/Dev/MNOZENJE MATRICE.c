#include <stdio.h>

int MnozenjeMatrice (int a[50][50], int b[50][50], int n, int k, int l)
{
	int j, zbir=0, pro;
	
	for (j=0; j<n; j++)
	{
		pro=a[k][j]*b[j][l];
		zbir+=pro;
	}
	
	return zbir;
}

main()
{
	int c[50][50], a[50][50], b[50][50], n, i, j;
	
	scanf ("%d", &n);
	
	for (i=0; i<n; i++)
		for (j=0; j<n; j++)
			scanf ("%d", &a[i][j]);
	
	for (i=0; i<n; i++)
		for (j=0; j<n; j++)
			scanf ("%d", &b[i][j]);	
			
	for (i=0; i<n; i++)
		for (j=0; j<n; j++)
		c[i][j]=MnozenjeMatrice(a,b,n,i,j);		
		
	for (i=0; i<n; i++)
	{ 
		for (j=0; j<n; j++)
			printf ("%d\t", c[i][j]);	
		printf ("\n");
	}
}
