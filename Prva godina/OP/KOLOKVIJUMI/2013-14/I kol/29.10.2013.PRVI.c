#include<stdio.h>
main()
{
	int x,y,t;
	float z;
	scanf("%d%d",&x,&y);
	
	if(x>4 && x<6)
		if(x-7!=0)
		{
			z=((x+y)*(x+y)*(x+y))/(x-7);
			printf("%6.3f",z);
		}
		else printf("NIJE DEFINISANO");
		else if((x>-1 && x<=4) || (x>=6 && x<11))
				if(x!=0)
				{
					z=(float)y/x;
					printf("%6.3f",z);								
				}
				else printf("NIJE DEFINISANO"); 
			else 
			{
				t=x-2;
				if(t<0) t=-t;
				if(y<t)
					z=y;
				else	
					z=t;
				printf("%6.3f",z);		
			}	
}
