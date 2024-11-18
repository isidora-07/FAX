#include <stdio.h>
#include <math.h>
main()
{
	int x;
	float y,z;
	scanf("%d",&x);
	if(x*x<=-4 || x*x>=16)
		if(x+6>0)
		{
			y=sqrt((float)(x+6)*(x+6)*(x+6));
			printf("%6.10f\n",y);	
		}	
		else printf("Ne postoji koren negativnog broja\n");
	else if(x>-3 && x<3)
			if(x*x-4!=0)
			{
				y=1.0/(x*x-4);
				printf("%6.10f\n",y);	
			}
			else printf("NIJE DEFINISANO\n");
		else 
		{
			y=x*x;
			printf("%6.10f\n",y);		
		}	
	if(x>=4)
		z=log(y);
	else 
	{
		if(x*x*x<x-4)
			z=x-4;
		else z=x*x*x;	
	}
	printf("%6.10f",z);	
}
