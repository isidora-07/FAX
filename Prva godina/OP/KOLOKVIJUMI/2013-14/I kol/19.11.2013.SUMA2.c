#include <stdio.h>
main()
{
	int n,i,fakt,sum,br;
	float x,s;
	scanf("%f%d",&x,&n);
	sum=0;
	s=0;
	fakt=1;
	for(i=1;i<=n;i++)
	{
		br=x*x*x+i+3;
		sum+=i;
		fakt*=i;
		s+=(float)br/(fakt+sum);
	}
	printf("%6.10f",s);
	
}
