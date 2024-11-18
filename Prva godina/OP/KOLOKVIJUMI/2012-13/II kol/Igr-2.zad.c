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

int super_niz(int a[50][50],int n,int b[])
{
	int i,j,k,pamti,p,c,t;
	int pom[50];
	
	p=0;
	for(i=0;i<n;i++)
	{
		for(j=0;j<n;j++)
			if(i!=j)
			{
			
				k=0;
				pamti=a[i][j];
				t=a[i][j];
				if(t<0) //za negativne brojeve
					t=-t;
				while(t>0)
				{
					c=t%10;
					t=t/10;
					pom[k++]=c;
				}
				
				if(pom[k-1]==pom[0])
					b[p++]=pamti;
				
			}

	}
	
	return p;
}

int main()
{
	int n;
	int a[50][50],b[50];
	scanf("%d",&n);
	ucitajMatricu(a,n);
	ispisiNiz(b,super_niz(a,n,b));

}
