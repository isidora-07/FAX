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


void transfMat(int a[50][50],int n)
{
	int i,j,br;
	int niz[50];
	br=0;
	for(i=0;i<n;i++)
		for(j=0;j<n;j++)
			if(i+j==n-1 || i+j>n-1)
				if(a[i][j]%2==0)
					niz[br++]=a[i][j];
				
	ispisiNiz(niz,br);			
}

int main()
{
	int n;
	int a[50][50];
	scanf("%d",&n);
	ucitajMatricu(a,n);
	transfMat(a,n);
}
