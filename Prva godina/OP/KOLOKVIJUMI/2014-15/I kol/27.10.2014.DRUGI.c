#include <stdio.h>
#include <math.h>
main()
{
	int x,y,max;
	float z;
	scanf("%d%d",&x,&y);
	if(x>-16 && x<4)
	{
		z=1.0*x+y*y*y;
		printf("%6.10f",z);	
	}	
	else if(x>-26 && x<14)
			if(x-6!=0)
			{
				z=(y*1.0)/(x-6);
				printf("%6.10f",z);
			}
			else printf("NIJE DEFINISANO");
		else 
		{
			max=x;
			if(max<x*y)
				z=x*y;
			else z=max;
			if(max<x-6)
				z=x-6;
			else z=max;
			if(x*y<x-6)
				z=x-6;
			else z=x*y;		
			printf("%6.10f",z);		
		}	

	
}
