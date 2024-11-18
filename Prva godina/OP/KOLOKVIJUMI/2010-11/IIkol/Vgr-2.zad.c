#include <stdio.h>

void ucitajMatricu(int a[50][50], int m, int n)
{
	int i,j;
	for(i=0;i<m;i++)
		for(j=0;j<n;j++)
			scanf("%d",&a[i][j]);
}

int sum(int a[50][50],int m,int n)
{
	int i,j,s;
	
	s=0;

	for(i=0;i<m;i++)
		for(j=0;j<n;j++)
		{
			if(i==0)
				s+=a[i][j];
			if(i==m-1)
				s+=a[i][j];	
		}
	for(j=0;j<n;j++)	
		for(i=1;i<m-1;i++)
		{
			if(j==0)
				s+=a[i][j];
			if(j==n-1)
				s+=a[i][j];	
		}	
		
	return s;	
}

int main()
{
	int m,n;
	int a[50][50];
	scanf("%d%d",&m,&n);
	ucitajMatricu(a,m,n);
	printf("%d",sum(a,m,n));
}
