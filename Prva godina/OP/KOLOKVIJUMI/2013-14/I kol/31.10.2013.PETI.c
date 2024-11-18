#include <stdio.h>
main()
{
	int x,y,t,p,min;
	float z;
	scanf("%d%d",&x,&y);
	t=x*x+y*y;
	if(t>1 && t<7)
	{
		z=(float)(x+y)/(8-x*x-y*y);
		printf("%6.10f",z);
	}
	else if((t>-2 && t<=1) || (t>=7 && t<10))
		{
			z=1.0/(t-3);
			printf("%6.10f",z);
		}
		else 
		{
			p=x-2;
			if(p<0) p=-p;
			min=x*y;
			if(min>y) z=y;
			else z=min;
			if(min>p) z=p;
			else z=min;
			if(y>p) z=p;
			else z=y;
			printf("%6.10f",z);
		}
		
}
