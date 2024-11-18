#include <stdio.h>

void UcitajMat(int a[30][30], int m, int n)
{
	int i,j;
	for(i=0; i<m; i++)
	{
		for(j=0; j<n; j++)
		{
			scanf("%d", &a[i][j]);
		}
	}
}

void StampajMat(int a[30][30], int m, int n)
{
	int i,j;
	for(i=0; i<m; i++)
	{
		for(j=0; j<n; j++)
		{
			printf("%5d", a[i][j]);
		}
		printf("\n");
	}
}

int PresekNiz(int a[30], int b[30], int n)
{
	int i,j;
	for(i=0; i<n; i++)
	{
		for(j=0; j<n; j++)
		{
			if(a[i] == b[j])
				return 0;
		}
	}
	return 1;
}

int NovMat(int a[30][30], int m, int n, int rez[30][30])
{
	int i,j;
	int k=0;
	int bla;
	int current[30]; //Array for comparing
	int target[30]; //Target for comparing
	int checking=0;
	for(i=0; i<m; i++) //Going through all the rows
	{
		for(bla=0; bla<n; bla++)
			current[bla] = a[i][bla];
			
		for(j=0; j<m; j++) 
		{
			for(bla=0; bla<n; bla++)
				target[bla] = a[j][bla];
				
			if(i!=j)
			{
				if(!PresekNiz(current, target, n))
				{
					checking++;
				}
			}
		}
		
		if(checking==0)
		{
			for(bla=0; bla<n; bla++)
			{
				rez[k][bla] = current[bla];
			}
			k++;
		}
		checking=0;
	}
	
	return k;
}

main()
{

	int m,n,i; 
	int initial[30][30];
	int p[30],q[30];
	scanf("%d%d", &m, &n);
	UcitajMat(initial, m, n);
	StampajMat(initial, m, n);
	printf("\n=======================================\n\n\n");
		
	for(i=0;i<m;i++){
		p[i]=initial[i][0];
		q[i]=initial[i][1];
	}
	
	printf("%d\n",PresekNiz(p,q,m));
		
	if(PresekNiz(p,q,m))
		printf("\nIMA\n");
	else
		printf("\nNEMA\n");



	int final[30][30];
	
	printf("\n=======================================\n\n\n");
	int k = NovMat(initial, m, n, final);
	StampajMat(final, k, n);

}

