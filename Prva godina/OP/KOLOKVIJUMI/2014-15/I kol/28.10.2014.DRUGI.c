#include <stdio.h>
#include <math.h>
main()
{
	int x,y,max;
	float z;
	scanf("%d%d",&x,&y);
	if((x+y>=0) || (y>=0 && y>-3 && y<3)) //da li treba (y>=0 &&???? y>-3 && y<3) ili (y>=0 || ???? (y>-3 && y<3))
		if(2-y>=0)
		{
			z=sqrt((float)(2-y)*(2-y)*(2-y)*(2-y)*(2-y));
			printf("%6.10f",z);
		}
		else printf("Ne postoji koren negativnog broja");
	else if(y<=-4 || y>=4)
			if(y*y-1!=0)
			{
				z=1/(float)(y*y-1);
				printf("%6.10f",z);	
			}	
			else printf("NIJE DEFINISANO");
		else 
		{
			max=x*x;
			if(max<x*x*x)
				z=x*x*x;
			else z=max;	
			if(max<x*y)
				z=x*y;
			else z=max;
			if(x*x*x<x*y)
				z=x*y;
			else z=x*x*x;
			printf("%6.10f",z);						
		}	
	
	
}
