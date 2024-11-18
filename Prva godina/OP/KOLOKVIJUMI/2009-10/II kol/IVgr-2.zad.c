#include <stdio.h>

void unos(int a[50][50],int m,int n)
{
	int i,j;
	for(i=0;i<m;i++)
		for(j=0;j<n;j++)
			scanf("%d",&a[i][j]);
}

void ispisiNiz(float a[],int n)
{
	int i;
	for(i=0;i<n;i++)
		printf("%6.3f\t",a[i]);
}

int main()
{
	int i,j,m,n,s;
	int a[50][50];
	float niz[50];
	scanf("%d%d",&m,&n);
	unos(a,m,n);
	for(j=0;j<n;j++)
	{
		s=0;
		for(i=0;i<n;i++)
			s+=a[i][j];
		niz[j]=(float)s/m;	
	}
	ispisiNiz(niz,n);
}
