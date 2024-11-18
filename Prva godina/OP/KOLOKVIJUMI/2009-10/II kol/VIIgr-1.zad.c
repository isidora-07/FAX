#include <stdio.h>

void unos(int a[],int n)
{
	int i;
	for(i=0;i<n;i++)
		scanf("%d",&a[i]);
}

void ispisi(int a[],int n)
{
	int i;
	for(i=0;i<n;i++)
		printf("%5d",a[i]);
}

int min(int a[],int n)
{
	int min,i;
	min=a[0];
	for(i=0;i<n;i++)
		if(min>a[i])
			min=a[i];
	return min;		
}

void ubaci(int a[],int n,int k)
{
	int i;
	
	for(i=n-1; i>=k; i--)
		a[i+1]=a[i];
	
	a[k]=min(a,n);
}

int main()
{
	int i,n,k,t;
	int a[50];
	scanf("%d",&n);
	unos(a,n);
	scanf("%d",&k);
	//ubacuje na k-tu poziciju minimalni element a ostale pomera udesno
	ubaci(a,n,k);
	ispisi(a,n+1);
	
		
	
}
