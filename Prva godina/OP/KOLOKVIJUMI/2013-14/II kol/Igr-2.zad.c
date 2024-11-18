#include <stdio.h>

void ucitajMatricu(int a[50][50], int m, int n)
{
	int i,j;
	for(i=0;i<m;i++)
		for(j=0;j<n;j++)
			scanf("%d",&a[i][j]);
}

void ispisiNiz(int a[],int n)
{
	int i;
	for(i=0;i<n;i++)
		printf("%5d",a[i]);		
}

int max(int a[50][50], int m, int n,int niz[])
{
	int i,j,br,max,t;
	for(i=0;i<m;i++)
	{
	
		max=a[i][i];
		for(j=0;j<n;j++)
			if(max<a[i][j])
				max=a[i][j];
				
		br=0;
		for(t=0;t<n;t++)
			if(max==a[i][t])
				br++;
		
		niz[i]=br;
	}
	
	return m;
}

int main()
{
	int m,n;
	int a[50][50],b[50];
	scanf("%d%d",&m,&n);
	ucitajMatricu(a,m,n);
	ispisiNiz(b,max(a,m,n,b));
}


