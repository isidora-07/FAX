#include <stdio.h>

int main()
{
	int m,n,i,j,br;
	int a[50][50],b[50];
	scanf("%d%d",&m,&n);
	for(i=0;i<m;i++)
		for(j=0;j<n;j++)
			scanf("%d",&a[i][j]);
	for(i=0;i<m;i++)
	{
		br=0;
		for(j=0;j<n;j++)
			if(a[i][j]==0)
				br++;
		b[i]=br;				
	}
	for(i=0;i<m;i++)
		printf("%5d",b[i]);		
}
