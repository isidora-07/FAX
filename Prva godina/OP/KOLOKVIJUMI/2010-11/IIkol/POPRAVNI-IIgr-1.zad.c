#include <stdio.h>
//test primer 100 121 939 4 76 747 86 108 -50 65 225 -275 565 275
//izlaz->100,121,108,225,275,-275
void ucitajNiz(int a[],int n)
{
	int i;
	for(i=0;i<n;i++)
		scanf("%d",&a[i]);		
}

void niz(int a[],int n)
{
	int i,j,k,br,c,pamti,broj,t;
	int niz[50],nov[50];
	
	t=0;
	for(i=0;i<n;i++)
	{
		k=0;
		pamti=a[i];
		br=0;
		while(a[i]>0)
		{
			c=a[i]%10;
			a[i]=a[i]/10;
			niz[k++]=c;
			br++;
		}
		
		if(br==3) //da li je trocifren
			broj=niz[2]*10+niz[0]; //znamo da broj MORA da bude trocifren
		printf("i=%d  %d\n",i,broj);	
		if(pamti%broj==0)
			nov[t++]=pamti;
			
	}
	
	for(i=0;i<t;i++)
		printf("%5d",nov[i]);
}

int main()
{
	int n;
	int a[50];
	scanf("%d",&n);
	ucitajNiz(a,n);
	niz(a,n);
}
