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

int totalno_parni(int a[50][50],int b[],int n)
{
	int i,j,c,br,pamti,p,t;
	int pom[50];
	p=0;
	for(i=0;i<n;i++)
	{
		for(j=0;j<n;j++)
			if(i+j==n-1 || i==j)
			{
				br=0;
				pamti=a[i][j];
				
				t=a[i][j];
				if(t<0)
					t=-t;
				while(t>0)
				{
					c=t%10;
					t=t/10;
					pom[br++]=c;
				}
				
				if(br==3) //ako neko unese broj koji nije trocifren
				{
					if(pom[0]%2==0 && pom[2]%2==0)
						b[p++]=pamti;
				}
				
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
	ispisiNiz(b,totalno_parni(a,b,n));
	
}
