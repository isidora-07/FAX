#include <stdio.h>

void ucitajNiz(float a[],int n)
{
	int i;
	for(i=0;i<n;i++)
		scanf("%f",&a[i]);		
}

void ind(float a[],int n, int p,int q)
{
	int i,s,br;
	
	s=0;
	br=0;
	for(i=0;i<n;i++)
		if(i<p-1 || i>q-1)
		{
			s+=a[i];
			br++;
			printf("%6.1f",a[i]);
		}
	printf("\n\n");	
	printf("Srednja vrednost:%6.1f",(float)s/br);	
	
}


int main()
{
	int n,p,q;
	float a[50];
	scanf("%d",&n);
	ucitajNiz(a,n);
	scanf("%d%d",&p,&q);
	ind(a,n,p,q);
}
