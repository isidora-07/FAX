#include <stdio.h>

void unos(int a[], int n)
{
	int i;
	for(i=0;i<n;i++)
		scanf("%d",&a[i]);
}

void sort(int a[], int n)
{
	int i,j,t;
	for(i=0;i<n-1;i++)
	{
		for(j=i+1;j<n;j++)
			if(a[i]<a[j])
			{
				t=a[i];
				a[i]=a[j];
				a[j]=t;
			}
	}
}

void ispis(int a[],int n)
{
	int i;
	for(i=0;i<n;i++)
		printf("%5d",a[i]);
}

int main()
{
	int n,i,br,br1;
	int a[50];
	int niz1[50],niz2[50];
	scanf("%d",&n);
	unos(a,n);
	sort(a,n);
	br=0;
	for(i=0;i<n;i++)
		if(a[i]%2==0)
			niz1[br++]=a[i];
	br1=0;	
	for(i=0;i<n;i++)
		if(a[i]%2!=0)
			niz2[br1++]=a[i];
	ispis(niz1,br);
	printf("\n");
	ispis(niz2,br1);			
	
}
