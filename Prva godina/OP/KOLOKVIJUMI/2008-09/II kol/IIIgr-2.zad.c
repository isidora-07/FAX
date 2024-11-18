#include <stdio.h>

int main()
{
	int n,i,j,br;
	int a[50][50];
	int niz[50];
	scanf("%d",&n);
	for(i=0;i<n;i++)
		for(j=0;j<n;j++)
			scanf("%d",&a[i][j]);
	br=0;
	for(i=0;i<n;i++)
		for(j=0;j<n;j++)
			if(i+j==n-1)
			if(a[i][j]%2==1)
				niz[br++]=a[i][j];

	for(i=0;i<br;i++)
		printf("%5d",niz[i]);		
}
