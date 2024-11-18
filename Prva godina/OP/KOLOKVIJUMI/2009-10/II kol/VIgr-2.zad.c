#include <stdio.h>

void unos(int a[50][50],int n)
{
	int i,j;
	for(i=0;i<n;i++)
		for(j=0;j<n;j++)
			scanf("%d",&a[i][j]);
}

void ispis(int a[50][50],int n)
{
	int i,j;
	for(i=0;i<n;i++)
	{
		for(j=0;j<n;j++)
			printf("%5d",a[i][j]);
		printf("\n");
	}
}

void diag(int a[50][50],int n)
{
	int i,j;
	for(i=0;i<n;i++)
	{
		for(j=0;j<n;j++)
		{
			if(i+j==n-1 || i==j)
				a[i][j]=1;
			else a[i][j]=0;	
		}
	}
}

int main()
{
	int n,i,j;
	int a[50][50];
	scanf("%d",&n);

	diag(a,n);
	ispis(a,n);
}
