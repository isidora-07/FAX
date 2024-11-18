#include <stdio.h>

void ucitajMatricu(int a[50][50], int m, int n)
{
	int i,j;
	for(i=0;i<m;i++)
		for(j=0;j<n;j++)
			scanf("%d",&a[i][j]);
}

void veci(int a[50][50], int m, int n,int k)
{
	int i,j,br;
	int niz[50];
	for(i=0;i<m;i++)
	{
		br=0;
		for(j=0;j<n;j++)
			if(a[i][j]>k)
				br++;
		niz[i]=br;		
	}
	
		for(i=0;i<m;i++)
		printf("%5d",niz[i]);
}

int main()
{
	int n,m,k;
	int a[50][50];
	scanf("%d%d",&m,&n);
	ucitajMatricu(a,m,n);
	scanf("%d",&k);
	veci(a,m,n,k);
}
