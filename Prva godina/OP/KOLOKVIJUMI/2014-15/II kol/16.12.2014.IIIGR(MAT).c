#include <stdio.h>
int main()
{
	int n,a[100],p,i,br,t;
	scanf("%d",&n);
	for(i=0;i<n;i++)
		scanf("%d",&a[i]);
	scanf("%d",&p);
	br=0;
	for(i=0;i<n-p;i++)
	{
		if(a[i]!=a[i+p])
			a[i+p]=a[i];
	}
	if(n%p!=0) printf("Nije moguce transformisati");
	else 
	{
		for(i=0;i<n;i++)
			printf("%5d",a[i]);
	}
}
