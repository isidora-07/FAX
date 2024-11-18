#include <stdio.h>

void ucitajNiz(int a[],int n)
{
	int i;
	for(i=0;i<n;i++)
		scanf("%d",&a[i]);		
}

int sum(int a[],int k)
{
	int i,s;
	s=0;
	for(i=0;i<k;i++)
		s+=a[i];
	
	return s;	
}

int sumPlocica(int a[],int b[],int n,int k)
{
	int i,s;
	s=0;
	for(i=0;i<sum(b,k);i++)
	{
		s+=a[i];
			
		if(s>100)
			break;
	}
	
	return s;	
}

int main()
{
	int n,k;
	int a[50],b[50];
	scanf("%d",&n);
	ucitajNiz(a,n);
	scanf("%d",&k);
	ucitajNiz(b,k);
	
	if(sum(b,k)<=n)
		if(sumPlocica(a,b,n,k)>100) printf("Uspesno predjen poligon\n");
			else printf("Neuspesno predjen poligon\n");
	else printf("Takmicar je diskvalifikovan\n");	
}
