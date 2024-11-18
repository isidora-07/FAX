#include <stdio.h>

void unesi(int a[50][50],int m,int n)
{
	int i,j;
	for(i=0;i<m;i++)
		for(j=0;j<n;j++)
			scanf("%d",&a[i][j]);
}

void ispis(int a[50][50],int m,int n)
{
	int i,j;
	for(i=0;i<m;i++)
	{
		for(j=0;j<n;j++)
			printf("%5d",a[i][j]);
		printf("\n");	
	}
}

void transformisi(int a[50][50],int m,int n)
{
	int i,j;
	for(i=0;i<m;i++)
	{
		for(j=0;j<n;j++)
		{
			if(a[i][j]<0)
				a[i][j]=-1;
			else 
				if(a[i][j]>0)
					a[i][j]=1;	
			else a[i][j]=0;		
		}	
	}
}

int main()
{
	int m,n,i,j;
	int a[50][50];
	scanf("%d%d",&m,&n);
	unesi(a,m,n);
	transformisi(a,m,n);
	ispis(a,m,n);
}
