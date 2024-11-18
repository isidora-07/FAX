#include <stdio.h>

void ucitaj(int a[50][50], int m, int n)
{
	int i,j;
	for(i=0;i<m;i++)
		for(j=0;j<n;j++)
			scanf("%d",&a[i][j]);
}

void ispisiNiz(int niz[],int m)
{
	int i;
	for(i=0;i<m;i++)
		printf("%5d",niz[i]);
}

int main()
{
	int m,n,br,i,j;
	int a[50][50],niz[50];
	scanf("%d%d",&m,&n);
	ucitaj(a,m,n);

	for(i=0;i<m;i++)
	{
		br=0;
		for(j=0;j<n;j++)
		if(a[i][j]>0)
			br++;
		niz[i]=br;	
	}
	
	ispisiNiz(niz,m);	
	
}
