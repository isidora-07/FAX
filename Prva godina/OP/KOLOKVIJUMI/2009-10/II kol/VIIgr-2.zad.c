#include <stdio.h>

void unos(int a[50][50],int n)
{
	int i,j;
	for(i=0;i<n;i++)
		for(j=0;j<n;j++)
			scanf("%d",&a[i][j]);
}

int main()
{
	int n,i,j,s,br;
	int a[50][50];
	scanf("%d",&n);
	unos(a,n);
	s=0;
	br=0;
	for(i=1;i<n;i++)
		for(j=0;j<i;j++)
		{	
			s+=a[i][j];
			br++;
		}
	printf("%6.3f",(float)s/br);
}
