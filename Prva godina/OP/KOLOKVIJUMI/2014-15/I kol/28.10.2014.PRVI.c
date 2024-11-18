#include <stdio.h>
#include <math.h>
main()
{
	int x,y,max,t;
	float z;
	scanf("%d%d",&x,&y);
	if((x>-3 && x<1) || (x+y>-6 && x+y<6))
	{
		z=1+x*y+y*y;
		printf("%6.10f",z);		
	}
	else  
		if(x<-4)
		{
			if(x+7!=0)
				if(x+7>=0)
			{	
				z=(float)y/(sqrt(x+7));
				printf("%6.10f",z);		
			}
				else printf("Ne postoji koren negativnog broja");
			else printf("NIJE DEFINISANO");
		}	
		else 
		{
			t=x*y;
			max=y;
			if(max<x)
				z=x;
			else z=max; 
			if(max<t)
				z=t;
			else z=max;
			if(x<t)
				z=t;
			else z=x;					
			printf("%6.10f",z);		
		}
	
}
