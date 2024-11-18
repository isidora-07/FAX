#include <stdio.h>

void ucitajniz(int a[], int n)
{
	int i;
	for(i=0;i<n;i++)
		scanf("%d",&a[i]);
}

void ispisiNiz(int a[], int n)
{
	int i;
	for(i=0;i<n;i++)
		printf("%5d",a[i]);	
}

void sortiraj(int a[], int n)
{
	int i,j,t;
	for(i=0;i<n-1;i++)
		for(j=i+1;j<n;j++)
			if(a[i]>a[j])
			{
				t=a[i];
		 		a[i]=a[j];
		 		a[j]=t;
			}
}

int main()
{
	int i,n,k,br,br1;
	int a[50],niz1[50],niz2[50];
	scanf("%d",&n);
	ucitajniz(a,n);
	printf("Unesi k:"); scanf("%d",&k);	
	sortiraj(a,n);
	br=0;
	for(i=0;i<n;i++)
		if(a[i]%k==0)
			niz1[br++]=a[i];
	br1=0;		
	for(i=0;i<n;i++)
		if(a[i]%k!=0)
			niz2[br1++]=a[i];
	ispisiNiz(niz1,br);
	printf("\n");
	ispisiNiz(niz2,br1);		
				
	
	
	
		

	
}
