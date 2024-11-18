#include <stdio.h>

int ProveravaPodniz (int a[], int b[], int n, int m)
{
	int i, j=0, k=0;
	
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

int ProveraPokriva (int a[], int b[], int n, int m)
{
	int i, j=0;
	
	for (i=0; i<n; i++)
		if (a[i]==b[j])
		{
			j++;
			if (j==m) 
				return 1;
		}	
	
	return 0;	
}

int Unija (int a[], int b[], int c[], int n, int m)
{
	int i, j, in=0, p;
	
	for (i=0; i<n; i++)
		c[i]=a[i];
		
	p=n;
	
	for (i=0; i<m; i++)
	{
		for (j=0; j<p; j++)
			if (b[i]==c[j])
				in++;		
		if (in==0)
		{
			c[p]=b[i];
			p++;
		}	
		else
			in=0;
	}	

	return p;	
}

int Presek (int a[], int b[], int c[], int n, int m)
{
	int i, j, p=0;
	
	for (i=0; i<n; i++)
	{
		for (j=0; j<m; j++)
		{
			if (a[i]==b[j])
			{
				c[p]=a[i];
				p++;
			}
		}
	}
	
	return p;
}

int Razlika (int a[], int b[], int c[], int n, int m)
{
	int i, j, p=0, in;
	
	for (i=0; i<n; i++)
	{
		for (j=0; j<m; j++)
		{
			if (a[i]==b[j])
				in=1;
		}
		
		if (in==0)
		{
			c[p]=a[i];
			p++;
		}
		else
			in=0;
	}	
	
	return p;
}

main()
{
	int i, j, a[50], b[50], c[50], n, m, x, y;
	
	scanf ("%d", &n);
	for (i=0; i<n; i++)
		scanf ("%d", &a[i]);
	
	scanf ("%d", &m);
	for (i=0; i<m; i++)
		scanf ("%d", &b[i]);	
	
	x=ProveravaPodniz(a,b,n,m);
	
	if (x==0)
		y=Presek(a,b,c,n,m);
	else
		y=Razlika(a,n,c,n,m);	
	
	if (y==0)
		printf ("Prazan niz");
	else
	{
		printf ("broj elemenata %d", y);
		for (i=0; i<y; i++)
			printf ("%d", c[i]);
	}		
		
		
	
	
	
}
