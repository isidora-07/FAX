#include <stdio.h>
main()
{
	
	int n,i,step,sum,br;
	float x,s;
	scanf("%f%d",&x,&n);
	step=1;
	sum=0;
	s=0;
	for(i=1;i<=n;i++)
	{
		step*=x;
		sum+=i;
		br=step+i+2;
		s+=(float)br/sum;
	}
	printf("%6.10f",s);
	
	
}
