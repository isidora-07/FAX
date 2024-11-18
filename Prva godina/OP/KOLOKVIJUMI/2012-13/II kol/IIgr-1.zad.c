#include <stdio.h>

void ucitajNiz(int a[],int n)
{
	int i;
	for(i=0;i<n;i++)
		scanf("%d",&a[i]);		
}

int koraci(int b[],int k)
{
	int i,s;
	s=0;
	for(i=0;i<k;i++)
		s+=b[i];
	
	return s;	
}

int sum(int a[],int b[],int n,int k)
{
	int i,s;
	s=0;
	for(i=0;i<koraci(b,k);i++)
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
	
	if(koraci(b,k)<=n)
		if(sum(a,b,n,k)>100) printf("Programer ima dovoljno novca za kafu\n");
			else printf("Programer nema dovoljno novca za kafu\n");
		else printf("Programer je diskvalifikovan\n");	
	
}
