#include <stdio.h>

void ucitajNiz(float a[],int n)
{
	int i;
	for(i=0;i<n;i++)
		scanf("%f",&a[i]);		
}

void step(float a[],int n,int k)
{
	int br,p,i,j;
	br=0;
	for(i=0;i<n;i++)
		if(a[i]<0)
		{
			p=1;
			for(j=0;j<k;j++)
				p*=a[i];	
			a[i]=p;
			br++;
		}
	
	a[n]=br;
	for(i=0;i<n+1;i++)
		printf("%6.1f\t",a[i]);
}

int main()
{
	int n,k;
	float a[50];
	scanf("%d",&n);
	ucitajNiz(a,n);
	scanf("%d",&k);
	step(a,n,k);
}
