#include <stdio.h>
#include <math.h>
main()
{
	int x,y,p,min;
	float z;
	scanf("%d%d",&x,&y);
	if(x+y>-1 && x+y<1)
	{
		z=pow(x,6)-y+3;
		printf("%6.10f",z);
	}
	else if((x+y>-2 && x+y<=-1) || (x+y>=1 && x+y<2))
			if(x+y+3>0)
			{
				z=1.0/sqrt(x+y+3);
				printf("%6.10f",z);
			}
			else printf("Ne postoji koren negativnog broja");
		else 
		{
			z=log(x+y+2.0);
			printf("%6.10f",z);	
		}		
}
