#include <stdio.h>

main()
{
	int m, n, i, j, a[50][50];
	
	scanf ("%d%d", &m, &n);
	
	for (i=0; i<m; i+=3)
	{
		for (j=0; j<n; j++)
			a[i][j]=0;
	}
	
	for (i=1; i<m-1; i++)
		for (j=0; j<n; j++)		
			scanf ("%d", &a[i][j]);
		
			
			
	for (i=0; i<m; i++)
	{
		for (j=0; j<n; j++)
			printf ("%d\t", a[i][j]);
		printf ("\n");	
	}				
	
	
	
	
}
