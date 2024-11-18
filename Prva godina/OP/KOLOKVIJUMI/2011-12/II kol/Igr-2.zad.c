#include <stdio.h>

void ucitajMatricu(int a[50][50], int n)
{
	int i,j;
	for(i=0;i<n;i++)
		for(j=0;j<n;j++)
			scanf("%d",&a[i][j]);
}

void ispisiNiz(int a[],int n)
{
	int i;
	for(i=0;i<n;i++)
		printf("%5d",a[i]);		
}

int sum(int a[50][50], int b[],int n)
{
	int i,j,br;
	int niz[50];
	
	br=0;
	for(i=0;i<n;i++)
		for(j=0;j<n;j++)
			if(i+j!=n-1 && i!=j && i+j>n-1 && i<j)
				if(a[i][j]>i+j)
					b[br++]=a[i][j];

	return br;				
}

int main()
{
	int n;
	int a[50][50],b[50];
	scanf("%d",&n);
	ucitajMatricu(a,n);
	ispisiNiz(b,sum(a,b,n));
}
