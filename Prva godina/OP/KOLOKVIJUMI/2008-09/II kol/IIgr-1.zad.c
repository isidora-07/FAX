#include <stdio.h>

int main()
{
	int n,i;
	float b[50];
	float d,a,s;
	scanf("%d",&n);
	scanf("%f%f",&d,&a);
	for(i=0;i<n;i++) b[i]=0;
	b[0]=a;
	b[1]=a+d;
	s=b[1];
	for(i=1;i<n;i++)
	{
		s+=d;
		b[i+1]=s;
	}
	for(i=0;i<n;i++)
		printf("%5.1f",b[i]);
}
