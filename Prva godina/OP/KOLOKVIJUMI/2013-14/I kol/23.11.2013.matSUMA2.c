#include <stdio.h>
main()
{
	int n,i,sum,br1,br,fakt;
	float x,s;
	scanf("%f%d",&x,&n);
	br1=1;
	fakt=1;
	s=3.0;
	for(i=1;i<n;i++)
	{
		br1*=x*x;
		br=br1+2;
		fakt*=2*i*(2*i+1);
		s+=(float)br/(fakt+i);
	}
	
	printf("%6.10f",s);
}			
