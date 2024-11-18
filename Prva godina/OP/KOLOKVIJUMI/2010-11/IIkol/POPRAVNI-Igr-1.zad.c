#include <stdio.h>

void ucitajNiz(int a[],int n)
{
	int i;
	for(i=0;i<n;i++)
		scanf("%d",&a[i]);		
}

void amstrongov_br(int a[],int n)
{
	int i,br,pamti,c,k,t,p,j;
	int nov[50],niz[50];
	k=0;
	for(i=0;i<n;i++)
	{
		br=0;
		t=0;
		pamti=a[i];
		while(a[i]>0)
		{
			c=a[i]%10;
			a[i]=a[i]/10;
			niz[t++]=c;
			br++;
		}
		
		if(br==3) //jeste trocifren broj
		{
			j=0;
			p=0;
			while(j<t)
			{
				p=p+niz[j]*niz[j]*niz[j];
				j++;
				printf("%d\n",p);
			}
		}
		
		if(p==pamti)
			nov[k++]=pamti;
	}
	
	for(i=0;i<k;i++)
		printf("%5d",nov[i]);
}

int main()
{
	int n;
	int a[50];
	scanf("%d",&n);
	ucitajNiz(a,n);
	amstrongov_br(a,n);
	
}
