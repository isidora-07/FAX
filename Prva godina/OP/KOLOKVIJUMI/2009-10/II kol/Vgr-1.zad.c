#include <stdio.h>

void ucitajNiz(int a[],int n)
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

int max(int a[],int n)
{
	int max,i;
	max=a[0];
	for(i=1;i<n;i++)
		if(max<a[i])
			max=a[i];
	return max;		
}

void ubaci(int a[],int n,int k)
{
	int i;
	for(i=n-1; i>=k; i--)
		a[i+1]=a[i];
	a[k]=max(a,n);	
}

int main()
{
	int n,i,k,t,pamti3;
	int a[50];
	scanf("%d", &n);
	ucitajNiz(a,n);
	scanf("%d",&k);
	ubaci(a,n,k);
	ispisi(a,n+1);
	
}
