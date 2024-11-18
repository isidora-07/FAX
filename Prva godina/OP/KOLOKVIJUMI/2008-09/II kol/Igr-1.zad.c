#include <stdio.h>

int main()
{
	int n,i,t,p;
	int a[50];
	scanf("%d",&n);
	for(i=0;i<n;i++)
		scanf("%d",&a[i]); 
	if(n%2==0)
	{
		for(i=0;i<n/2;i++)
		{
			t=a[i];
			a[i]=a[n/2+i];
			a[n/2+i]=t;
		}
		for(i=0;i<n;i++)
			printf("%4d",a[i]);	
		
	}	
	else
	{
		for(i=n/2;i<n-1;i++)
		{
			t=a[i];
			a[i]=a[i+1];
			a[i+1]=t;
		}
		for(i=0;i<n/2;i++)
		{
			t=a[i];
			a[i]=a[n/2+i];
			a[n/2+i]=t;
		}
		for(i=0;i<n-1;i++)
			printf("%4d",a[i]);	
			
	}
}
