#include <stdio.h>
main()
{
	int i,n,br,im,br1,im1;
	float s,x;
	scanf("%f%d",&x,&n);
	br1=x*x;
	br=br1-3;
	fakt=2*3;
	s=(float)br/fakt;
	for(i=2;i<=n;i++)
	{
		br1*=x*x;
		br=br1-3;
		fakt*=(2+i);
		s+=(float)br/fakt;
	}
	printf("%6.10f",s);
	
	
}
