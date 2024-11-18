#include <stdio.h>
#include <string.h>

void PrepisiString (char a[], int k, char c)
{
	int i, d,  n;
	
	d=strlen(a);
	
	for (i=d-1; i>=d-k; i--)
		a[i]=c;
}

void TransformisiString (char a[])
{
	int i, n, d, t;
	
	for (i=0; i<n/2; i++)
	{
		t=a[i];
		a[i]=a[n-1-i];
		a[n-1-i]=t;
	}
}

void Nadovezi (char a[], char b[], char c[])
{
	int n, i, j=0, d;
	
	n=strlen(a);
	
	a[n]=c;
	d=strlen(b);
	for (i=n+1; i<n+d+1; i++)
	{
		a[i]=b[j];
		j++;
	} 
}

main()
{
	char a[20];
	
	scanf ("%s", a);
	printf ("%c", a[2]);
	
}
