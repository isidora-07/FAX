#include <stdio.h>

void RotirajUlevo (int a[], int n)
{
	int i, t;
	
	t=a[0];
	
	for (i=0; i<n; i++)
		a[i]=a[i+1];
		
	a[n-1]=t;	
	
}



main()
{
	int i, n, j, m, a[50], b[50][500];
	
	scanf ("%d%d", &n, &m);
	
	for (i=0; i<n; i++)
		scanf ("%d", &a[i]);
		
	for (j=0; j<n; j++)	
		b[0][j]=a[j];
		
	for (i=1; i<m; i++)
	{
		for (j=0; j<n; j++)
			a[j]=b[i-1][j];
			RotirajUlevo(a,n);
		for (j=0; j<n; j++)
			b[i][j]=a[j];	
	}	
	
	for (i=0; i<m; i++)
	{
		for (j=0; j<n; j++)
			printf ("%d\t", b[i][j]);
		printf ("\n");	
	}
}
