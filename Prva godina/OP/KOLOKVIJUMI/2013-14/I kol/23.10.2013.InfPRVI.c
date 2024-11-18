#include <stdio.h>
main()
{
	int x,y,p,max;
	float z;
	scanf("%d%d",&x,&y);
	if(x>-4 && x<0)
	{
		z=(x+y)*(x+y)*(x+y);
		printf("%6.10f",z);
	}
	else if((x>-6 && x<=-4) || (x>-6 && x<=0))
			if(x!=0)
			{
				z=1.0*y/x;
				printf("%6.10f",z);
			}
			else printf("NIJE DEFINISANO");
		else
		{
			max=x;
			p=x-2;
			if(p<0) p=-p;
			if(max<x*x) z=x*x;
			else z=max;
			if(max<p) z=p;
			else z=max;
			if(x*x<p) z=p;
			else z=x*x;
			printf("%6.10f",z);
		}	
}
