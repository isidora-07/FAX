#include <stdio.h>

void ucitajNiz(int a[],int n)
{
	int i;
	for(i=0;i<n;i++)
		scanf("%d",&a[i]);		
}

void ispisNiz(int a[],int n)
{
	int i;
	for(i=0;i<n;i++)
		printf("%5d",a[i]);		
}

int podniz(int a[], int n)
{
    int i,br,max,ind;
    
    ind=1;
    max=-1;
    br=0;
    
    for(i=0;i<n;i++)
    {
    	if(a[i]==0)
    	{
    		br++;
    		ind=1;
		}
		else 
			ind=0;
		
		if(ind!=1)
			br=0;
			
		if(max<br)
			max=br;	
	}
	
	
	printf("%d",max);
	
}



int main()
{
	int n,i;
	int a[50];
	scanf("%d",&n);
	ucitajNiz(a,n);
	podniz(a,n);
}
