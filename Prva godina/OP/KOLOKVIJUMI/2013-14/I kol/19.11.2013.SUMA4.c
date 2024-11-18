#include <stdio.h>
main()
{
	int n,i,sum,br1,br,step,fakt;
	float x,s;
	scanf("%f%d",&x,&n);
	sum=1;
	fakt=1;	
	br1=x*x;
	s=(float)(br1+1)/1.0;
	for(i=2;i<=n;i++)
	{
		fakt*=(2*i-2)*(2*i-1);
		br1*=x*x;
		sum+=i;
		br=br1+sum;
		s+=(float)br/fakt;
	}
	printf("%6.10f",s);
}	
