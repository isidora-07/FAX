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
		printf("%6.2f\t",a[i]);		
}

void sort(float a[],int n)
{
	int i,j;
	float t;
	for(i=0;i<n-1;i++)
		for(j=i+1;j<n;j++)
			if(a[i]>a[j])
			{
				t=a[i];
				a[i]=a[j];
				a[j]=t;
			}		
}

int k_najmanjih(float a[],int n,int k)
{
	int i;
	sort(a,n);
	for(i=0;i<k;i++)
		printf("%6.1f\t",a[i]);	
	
}

int main()
{
	int n,k;
	float a[50];
	scanf("%d",&n);
	ucitajNiz(a,n);
	scanf("%d",&k);
	k_najmanjih(a,n,k);
	
}
