#include <stdio.h>

void ucitajMatricu(int a[50][50], int n)
{
	int i,j;
	for(i=0;i<n;i++)
		for(j=0;j<n;j++)
			scanf("%d",&a[i][j]);
}

int suma(int a[50][50],int n)
{
	int i,j,s;
	s=0;
	for(i=0;i<n;i++)
		for(j=0;j<i;j++)
			if(i+j<n && i!=j && i+j!=n-1)
				s+=a[i][j];
	
	printf("%d",s);			
}

int main()
{
	int n;
	int a[50][50];
	scanf("%d",&n);
	ucitajMatricu(a,n);
	suma(a,n);
	
}
