#include <stdio.h>
int main()
{
	int n,m,a[100],i,t,tmp,j;
	scanf("%d",&n);
	for(i=0;i<n;i++)
		scanf("%d",&a[i]);
	scanf("%d",&m);	

	for(i=0; i<m; i++)
	{
		tmp=a[0];
			for(j=0; j<n-1; j++)
				a[j]=a[j+1];
		a[n-1]=tmp;
	}
	for(i=0;i<n;i++)
		printf("%5d",a[i]);
}
