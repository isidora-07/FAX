#include <stdio.h>

void ucitajMatricu(float a[50][50], int n)
{
	int i,j;
	for(i=0;i<n;i++)
		for(j=0;j<n;j++)
			scanf("%f",&a[i][j]);
}

float maxNiz(float a[50][50],int n)
{
	int i,j,br;
	float max;
	float niz[50];
	
	br=0;
	for(i=0;i<n;i++)
		for(j=0;j<n;j++)
			if(i+j!=n-1 && i!=j && i+j<n-1 && i<j) 
				niz[br++]=a[i][j];
				
	max=niz[0];
	for(i=1;i<br;i++)
		if(max<niz[i])
			max=niz[i];
			
	return max;					
}

int main()
{
	int n;
	float a[50][50];
	scanf("%d",&n);
	ucitajMatricu(a,n);
	printf("%6.3f",maxNiz(a,n));
}
