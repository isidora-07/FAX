#include <stdio.h>

main()
{
	int i, j, m, n, a[50][50];
	
	scanf ("%d%d", &m, &n);
	
	for (j=0; j<n; j+=2)
		for (i=0; i<m; i++)
			scanf ("%d", &a[i][j]);
	
	for (j=1; j<n; j+=2)
	{
		for (i=0; i<m; i++)
			a[i][j]=0;
	
	}		
	
	
	
	for (i=0; i<m; i++)
	{
		for (j=0; j<n; j++)
			printf ("%d\t", a[i][j]);
		printf ("\n");		
	}
	
}
