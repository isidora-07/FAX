#include<stdio.h>
main()
{
	float x,y,f,t,p;
	scanf("%f%f",&x,&y);
	t=x+2*y;
	if(t<-8 || t>8)
	{
		f=(x+2)*(y+2)*(y+2);
		printf("%6.3f",f);
	}
	else 
		if(t>-2 && t<2)
			if(x+2!=0)
			{
				f=t/(x+2);
				printf("%6.3f",f);
			}
			else printf("NIJE DEFINISANO");	
		else 
		{
			p=t+6;
			if(p<0) p=-p;
			if(x*x<p)
				f=p;
			else f=x*x;	
			printf("%6.3f",f);
		}		
			
	
	
}
