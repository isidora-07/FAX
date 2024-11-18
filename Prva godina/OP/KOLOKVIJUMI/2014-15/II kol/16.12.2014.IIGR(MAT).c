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
			br++;		
	}		
	
	if(br) printf("nije periodican, potrebne su %d izmene", br); 
		else
		{
			if(n%2==0)
				printf("periodican");
			else printf("periodican, ali nema zavrsen period");	
		} 
}
