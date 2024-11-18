#include<stdio.h>
main()
{
	float a,b,f,t,min,p,k;
	scanf("%f%f",&a,&b);
	t=2*a+3*b;
	if(t<-5 || t>5)
	{
		p=3+b;
		if(p<0) p=-p;
		f=a*p;
		printf("%6.3f",f);
	}
	else if((t>=-5 && t<-2) || (t>2 && t<=5))
		{
			k=t+6;
			if(k<0) k=-k;
			if(t>k)
				f=k;
			else f=t;	
			printf("%6.3f",f);
		}	
		else
		{
			if(a*a+b*b!=0)
			{
				f=t/(a*a+b*b);
				printf("%6.3f",f);
			}	
			else printf("NIJE DEFINISANO");
		}	

}
