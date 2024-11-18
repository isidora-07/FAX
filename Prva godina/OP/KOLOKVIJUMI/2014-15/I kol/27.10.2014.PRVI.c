#include <stdio.h>
#include <math.h>
main()
{
	int x,y,max;
	float z;
	scanf("%d%d",&x,&y);
	if(x>3 && x<15)
	{
		z=x+y*y*y*1.0;
		printf("%6.10f",z);
	}
	else if(x>-5 && x<23)
			if(x>=0)
			{
				z=1.0*y*sqrt(x);
				printf("%6.10f",z);
			}
			else printf("Ne postoji koren negativnog broja");
		else 
		{
			max=x-5;
			if(max<x+y)
				z=x+y;
			else z=max;	
			if(max<x)
				z=x;
			else z=max;	
			if(x+y<x)
				z=x;
			else z=x+y;			
			
			printf("%6.10f",z);
		}
}
