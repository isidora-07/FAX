#include <stdio.h>
#include <math.h>
main()
{
	int x,y,max;
	float z;
	scanf("%d%d",&x,&y);
	if(x*x<=-10 || x*x>=4) //uslov x*x<=-10 je suvisan??? 
		if(x-1>=0)
		{
			z=sqrt((float)(x-1)*(x-1)*(x-1));
			printf("%6.10f",z);	
		}	
		else printf("Ne postoji koren negativnog broja");
	else if(x>=-1 && x<=1)
			if(x*x-1!=0)
			{
				z=1/((float)(x*x-1));
				printf("%6.10f",z);
			}	
			else printf("NIJE DEFINISANO");
		else 
		{
			if(x*x<x*y) //NIKADA NECE DA UDJE U USLOV INACE???
				z=x*y;
			else z=x*x;	
			printf("%6.10f",z);
		}	
}
