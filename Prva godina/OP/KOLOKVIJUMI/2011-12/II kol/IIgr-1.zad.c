#include <stdio.h>

void ucitajNiz(int a[],int n)
{
	int i;
	for(i=0;i<n;i++)
		scanf("%d",&a[i]);		
}

int sum(int a[],int b[],int n)
{
	int i,s;
	s=0;
	for(i=0;i<n;i++)
		if(a[i]==1)
			if(b[i]==0)	s+=1;
			else if(b[i]==1) s+=3;
				else s+=0;
			
	return s;			
}

int main()
{
	int a[50],b[50];
	int n;
	scanf("%d",&n);
	ucitajNiz(a,n);
	ucitajNiz(b,n);
	printf("%d",sum(a,b,n));
}
