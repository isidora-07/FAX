#include <stdio.h>

int niz_niz (int a[], int b[], int n, int m)
{
	int i=0, j=0, k=0;
	
	for (i=0; i<n; i++)
	{
		if (a[i]==b[j])
			for (j=0; j<m; j++)
				if (a[i+j]==b[j])
					k++;
		if (k==m)
			return 1;			
	}
	
	return 0;
}

main()
{
	int a[50], b[50];
	int n, m, i, j;
	
	scanf ("%d", &n);
	for (i=0; i<n; i++)
		scanf ("%d", &a[i]);
		
	scanf ("%d", &m);
	for (i=0; i<m; i++)
		scanf ("%d", &b[i]);
			
	if (niz_niz(a,b,n,m))
		printf ("Niz b je deo niza a\n");
	else
		printf ("Niz b nije deo podniza a\n");			
	
	
	
}
