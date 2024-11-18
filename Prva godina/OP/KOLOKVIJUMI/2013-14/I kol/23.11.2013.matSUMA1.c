#include <stdio.h>
main()
{
	int n,i,sum,br1,br,step,fakt;
	float x,s;
	scanf("%f%d",&x,&n);
	br1=x*x*x;
	fakt=1;
	s=(br1+1)/6.0;
	for(i=2;i<=n;i++)
	{
		br1*=x*x*x;
		br=br+i;
		fakt*=(2*i-2)*(2*i-1);
		s+=(float)br/(fakt+5);
	}
	printf("%6.10f",s);
}		
