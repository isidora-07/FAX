#include <stdio.h>

void ucitajNiz(float a[],int n)
{
	int i;
	for(i=0;i<n;i++)
		scanf("%f",&a[i]);		
}

void ispisiNiz(float a[],int n)
{
	int i;
	for(i=0;i<n;i++)
		printf("%6.1f\t",a[i]);		
}

float avg(float a[],int n)
{
	int i;
	float s;
	s=0.0;
	for(i=0;i<n;i++)
		s+=a[i];
	return s/n;	
}

void razlika(float a[],int n,float d)
{
	int i,br;
	float niz[50];
	float sr;
	sr=avg(a,n);
	br=0;
	for(i=0;i<n;i++)
		if(a[i]-sr<d)
			niz[br++]=a[i];
	
	ispisiNiz(niz,br);		
	
}

int main()
{
	int n;
	float d;
	float a[50];
	scanf("%d",&n);
	ucitajNiz(a,n);
	scanf("%f",&d);
	razlika(a,n,d);
	
}
