#include <stdio.h>
int main()
{
	int i,n,niz[100],k=0,b[100];
	scanf("%d",&n);
	for(i=0;i<n;i++)
		scanf("%d",&niz[i]);
	for(i=0;i<n;i++)
		if(i%2==0)
			b[k++]=niz[i];
	for(i=0;i<k;i++)
		printf("%5d",b[i]);			
}
