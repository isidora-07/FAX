#include <stdio.h>

void unesiMatricu(int a[50][50],int m, int n)
{
	int i,j;
	for(i=0;i<m;i++)
		for(j=0;j<n;j++)
			scanf("%d",&a[i][j]);
}

void ispisiNiz(int niz[], int m)
{
	int i;
	for(i=0;i<m;i++)
		printf("%5d",niz[i]);
}

int main()
{
	int i,j,m,n,k,br;
	int a[50][50],niz[50];
	scanf("%d%d",&m,&n);
	unesiMatricu(a,m,n);
	scanf("%d",&k);
	for(i=0;i<m;i++)
	{
		br=0;
		for(j=0;j<n;j++)
			if(a[i][j]%k==0)
				br++;
		niz[i]=br;		
	}
	ispisiNiz(niz,m);
			
}

