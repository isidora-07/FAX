#include <stdio.h>

int main()
{
	int n,i,j,k;
	int a[50][50];
	scanf("%d",&n);
	for(i=0;i<n;i++)
		for(j=0;j<n;j++)
			scanf("%d",&a[i][j]);
	printf("Unesi k:"); scanf("%d",&k);	
	
	for(i=1;i<n;i++)
	{
		for(j=0;j<i;j++)
			if(a[i][j]>=2*k)
				printf("%5d",a[i][j]);
		
	}
		
	
	
}
