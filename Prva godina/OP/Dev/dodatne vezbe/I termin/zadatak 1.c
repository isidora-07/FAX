#include<stdio.h>
int provera(int a[50][50], int n)
{
	int i, j;

	for (i=0; i<n; i++)
		for(j=0; j<n; j++)
			if (j+i>n-1 && a[i][j]!=0)
				return 0;

	return 1;
	
}
main()
{
	int i, j, n, a[50][50];
	
	scanf("%d", &n);
	
	for(i=0; i<n; i++)
		for(j=0; j<n; j++)
			scanf("%d",&a[i][j]);
	
	if(provera(a, n))
		printf("jeste");
	else
		printf("nije");
}
