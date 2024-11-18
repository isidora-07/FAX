#include <stdio.h>

int main()
{
	int n,i,br;
	int a[50],b[50];
	scanf("%d",&n);
	for(i=0;i<n;i++)
		scanf("%d",&a[i]);
	br=0;	
	for(i=0;i<n;i++)
	{
		if(a[i]%2==0)
			b[br++]=a[i];	
	}	
		for(i=0;i<n;i++)
	{
		if(a[i]%2==1)
			b[br++]=a[i];	
	}
	for(i=0;i<n;i++)	
		printf("%5d",b[i]);
}
