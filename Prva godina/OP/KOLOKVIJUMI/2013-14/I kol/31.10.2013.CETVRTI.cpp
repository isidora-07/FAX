#include <stdio.h>
#include <math.h>
main()
{
	int x,y;
	float z;
	scanf("%d%d",&x,&y);
	if(x+y>4 || x+y<0)
		if(x+y>0)
		{
			z=sqrt(1.0/(x+y)*(x+y)*(x+y));
			printf("%6.10f",z);
		}
		else printf("Ne postoji koren negativnog broja");
	else if((x+y>=0 && x+y<=1) || (x+y>=3 && x+y<=4))
		{
			if(x>y) z=y;
			else z=x;
			printf("%6.10f",z);
		}	
		else if(1-x-y!=0)
			{
				z=1.0/(1-x-y);
				printf("%6.10f",z);
			}
			else printf("NIJE DEFINISANO");



}
	
