#include <stdio.h>
main()
{
	int x,y,p,min;
	float z;
	scanf("%d%d",&x,&y);
	if(x<=-5 || x>=-1)
	{
		z=(x+y)*(x+y)*(x+y);
		printf("%6.10f",z);
	}
	else if((x>-5 && x<=-4) || (x>=-2 && x<-1))
			if(x!=0)
			{
				z=(float)y/x;
				printf("%6.10f",z);
			}
			else printf("NIJE DEFINISANO");
		else
		{
			p=x-2;
			if(p<0) p=-p;
			min=x;
			if(min>y) z=y;
			else z=min;
			if(min>p) z=p;
			else z=min;
			if(y>p) z=p;
			else z=y;
			printf("%6.10f",z);
		} 
}
