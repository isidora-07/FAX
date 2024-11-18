#include <stdio.h>

void ucitajMatricu(int a[50][50], int n)
{
	int i,j;
	for(i=0;i<n;i++)
		for(j=0;j<n;j++)
			scanf("%d",&a[i][j]);
}

void niz(int a[50][50], int n)
{
	int i,j,br;
	int nov[50];
	br=0;
	for(i=0;i<n;i++)
		for(j=0;j<n;j++)
			if(i+j<n-1)
				if(a[i][j]==i*j)
					nov[br++]=a[i][j];
	
	for(i=0;i<br;i++)
		printf("%5d",nov[i]);				
}

int main()
{
	int n;
	int a[50][50];
	scanf("%d",&n);
	ucitajMatricu(a,n);
	niz(a,n);
}
