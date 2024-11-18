#include <stdio.h>

void ucitajMatricu(int a[50][50], int n)
{
	int i,j;
	for(i=0;i<n;i++)
		for(j=0;j<n;j++)
			scanf("%d",&a[i][j]);
}

void ispisiNiz(int a[],int n)
{
	int i;
	for(i=0;i<n;i++)
		printf("%5d",a[i]);		
}

int totalni_niz(int a[50][50], int nov[],int n)
{
	int i,j,k,c,br,t,pamti,r;
	int pom[50];
	
	t=0;
	for(i=0;i<n;i++)
	{
		for(j=0;j<n;j++)
			if(i+j>n-1)
			{
				k=0;
				pamti=a[i][j];
				r=a[i][j];
				
				if(r<0) r=-r; //za neg. br.
				while(r>0)
				{
					c=r%10;
					r=r/10;
					pom[k++]=c;
				}
				
				br=0;
				while(k>=0)
				{
					k--;
					if(pom[k]%2==1)
						br++;
				}
				
				if(br==3)
					nov[t++]=pamti;
			}
	}
	
	return t;
}

int main()
{
	int n;
	int a[50][50],b[50];
	scanf("%d",&n);
	ucitajMatricu(a,n);
	ispisiNiz(b,totalni_niz(a,b,n));
}
