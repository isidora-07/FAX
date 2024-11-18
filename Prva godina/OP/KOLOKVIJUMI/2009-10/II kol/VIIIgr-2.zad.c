#include <stdio.h>

void ispis(int a[50][50],int m,int n)
{
	int i,j;
	for(i=0;i<m;i++)
		for(j=0;j<n;j++)
		{
			if(i==0) a[i][j]=1;
				else a[i][j]=0;
				
			if(i==m-1) a[i][j]=1;
		}
	for(j=0;j<n;j++)	
		for(i=0;i<m;i++)
		{
			if(j==0) a[i][j]=1;
	
			if(j==n-1) a[i][j]=1;
		}
		
	for(i=0;i<m;i++)
	{
		for(j=0;j<n;j++)	
			printf("%5d",a[i][j]);
		printf("\n");	
	}
		
}

int main()
{
	int m,n,i,j;
	int a[50][50];
	scanf("%d%d",&m,&n);
	ispis(a,m,n);
}
