#include <stdio.h>

void unos(int a[50][50],int n)
{
	int i,j;
	for(i=0;i<n;i++)
		for(j=0;j<n;j++)
			scanf("%d",&a[i][j]);
}

int maxNiza(int a[],int n)
{
	int i,max;
	max=a[0];
	for(i=1;i<n;i++)
		if(max<a[i])
			max=a[i];
	return max;		
}

int main()
{
	int n,br,i,j;
	int a[50][50], niz[50];
	scanf("%d",&n);
	unos(a,n);

	br=0;
	for(i=0;i<n;i++)
		for(j=0;j<i;j++)
			if(i!=j && i+j!=n-1 && i+j<n)
				niz[br++]=a[i][j];	
			
	printf("%d",maxNiza(niz,br));			
}
