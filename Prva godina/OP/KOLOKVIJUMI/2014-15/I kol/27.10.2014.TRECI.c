#include <stdio.h>
#include <math.h>
main()
{
	int x,y,max,t;
	float z;
	scanf("%d%d",&x,&y);
	if(x-y>-2 && x-y<2)
	{
		z=x+x*y*1.0;	
		printf("%6.10f",z);
	}
	else if(x-y>-6 && x-y<6)
		{
			t=x+3;
			if(t<0) t=-t;
			if(t!=0)
			{
				z=1.0/t;
				printf("%6.10f",z);
			}
			else printf("NIJE DEFINISANO");	
		}
		else
		{
			max=x*x;
			if(max<x+y)
				z=x+y;
			else z=max;
			if(max<y)
				z=y;
			else z=max;
			if(x+y<y)
				z=y;
			else z=x+y;	
			printf("%6.10f",z);
		}
	
}
