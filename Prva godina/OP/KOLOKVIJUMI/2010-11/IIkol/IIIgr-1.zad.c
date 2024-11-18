#include <stdio.h>
void ucitajNiz(float a[],int n)
{
	int i;
	for(i=0;i<n;i++)
		scanf("%f",&a[i]);		
}

void ispisiNiz(float a[],int n)
{
	int i;
	for(i=0;i<n;i++)
		printf("%6.3f\t",a[i]);		
}

float maxNiza(float a[],int n)
{
	int i;
	float max;
	max=a[0];
	for(i=1;i<n;i++)
		if(max<a[i])
			max=a[i];
	
	return max;		
}

int main()
{
	int n,i,br;
	float a[50],nov[50];
	float t;
	scanf("%d",&n);
	ucitajNiz(a,n);

	t=maxNiza(a,n)/2.0;
	br=0;
	for(i=0;i<n;i++)
		if(a[i]<t)
			nov[br++]=a[i];	

	ispisiNiz(nov,br);	
	
}
