#include <stdio.h>

int main()
{
	int m,n,i,j,k,s,br;
	int a[50][50];
	scanf("%d%d",&m,&n);
	for(i=0;i<m;i++)	
		for(j=0;j<n;j++)
			scanf("%d",&a[i][j]);
	printf("Unesi k:"); scanf("%d",&k);		
	
	s=0;
	br=0;
	for(i=0;i<m;i++)	
	{
		for(j=0;j<n;j++)	
			if(a[i][j]%k==0)
			{
				s+=a[i][j];
				br++;
			}
	}
	
	printf("%1.2f",(float)s/br);
}
