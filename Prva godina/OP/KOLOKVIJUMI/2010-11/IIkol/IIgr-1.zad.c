#include <stdio.h>

void ucitajNiz(float a[],int n)
{
	int i;
	for(i=0;i<n;i++)
		scanf("%f",&a[i]);		
}

void transf(float a[],int n, int p,int q)
{
	int i;
	for(i=p-1;i<q;i++)
		printf("%6.3f\t",a[i]);
	printf("\n\n");	
}

float avg(float a[],int n,int p,int q)
{
	int i;
	float s;
	s=0.0;
	for(i=p-1;i<q;i++)
		s+=a[i];
	
	printf("%6.3f",s/(q-1));
}

int main()
{
	int i,n,p,q;
	float a[50];
	scanf("%d",&n);
	ucitajNiz(a,n);
	scanf("%d%d",&p,&q);
	transf(a,n,p,q);
	avg(a,n,p,q);

}
