#include <stdio.h>
#include <math.h>
main()
{
	int x,y,t;
	float z;
	scanf("%d%d",&x,&y);
	if(x>=-3 && x<=1)
	{	
		z=(x*x*y*y*1.0)/sqrt((x+4)*(x+4)*(x+4)*(x+4));
		printf("%6.10f",z);
	}
	else if((x>-7 && x<-3) || (x>1 && x<5))
			if(3-x!=0)
			{
				z=(float)(y-2)/(3-x);
				printf("%6.3f",z);
			}
			else printf("NIJE DEFINISANO");
		else 
		{
			t=x-2;
			if(t<0) t=-t;
			if(log(x*x)<t)
				z=t;
			else z=log(x*x*1.0);
			printf("%6.3f",z);	
		}	
	
	
}
