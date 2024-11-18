#include <stdio.h>

void ucitajNiz(int a[],int n)
{
	int i;
	for(i=0;i<n;i++)
		scanf("%f",&a[i]);		
}

int regularno(int a[],int n)
{
	int i,br,j;
	br=0;
	for(i=0;i<1;i++)
		for(j=1;j<n;j++)
			if(a[i]==a[j])
			{
				br++;
				break;	
			}
	return br;		
}

int main()
{
	int n;
	int a[50];
	scanf("%d",&n);
	ucitajNiz(a,n);
	if(regularno(a,n)==0)
		printf("Brojevi su jedinstveni");
	else printf("Prijava se mora izmeniti");		
}

