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

int avg(int a[50][50], int m, int n,int niz[])
{
	int i,j,s,t,br;
	float p;

	for(i=0;i<m;i++)
	{
		s=0;
		for(j=0;j<n;j++)
			s+=a[i][j];
			
		p=(float)s/n;	
		br=0;
		for(j=0;j<n;j++)
			if(a[i][j]>p)
				br++;
	
		niz[i]=br;		
	}
	
	return m;
}

int main()
{
	int n,m;
	int a[50][50],b[50];
	scanf("%d%d",&m,&n);
	ucitajMatricu(a,m,n);
	ispisiNiz(b,avg(a,m,n,b));
}
