#include <stdio.h>
main()
{
	int n,i,sum,br1,br,step;
	float x,s;
	scanf("%f%d",&x,&n);
	s=0;
	sum=0;
	br1=1;
	for(i=1;i<=n;i++)
	{
		step=1;
		br1*=x*x;
		br=br1+i;
		sum+=i;
		step*=i*i;
		s+=(float)br/(step+sum);
	}
	printf("%6.10f",s);
}
