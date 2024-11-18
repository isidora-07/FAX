#include <stdio.h>
#include <math.h>
main()
{
	int x,n,t,p,stepen,i,j,step,fakt,s;
	float f;
	scanf("%d%d",&x,&n);
	if(n>0)
	{
		
		stepen=1;
		for(i=1;i<=n;i++)
			stepen*=x; //x^n
		fakt=1;
		s=0;
		f=0;
		for(i=1;i<n;i++)
		{
			step=1;
			for(j=1;j<=i;j++)
				step*=i; //i^i
			s+=step;
			fakt*=2*i*(2*i+1);	
			f+=((float)s)/(stepen*fakt);
		}
		printf("%10.5f",f);
	}
	else
	{
		t=2*x*x+n;
		if((t>-10 && t<-2) || (t>2 && t<10))
		{
			if(t+10<n)
				f=n;
			else f=t+10;	
			printf("%10.5f",f);
		}
		else if(t<=-20 || t>=20)
			{
				f=(x+n)*(x+n)-10;
				printf("%10.5f",f);
			}
			else
			{
				p=x+n;
				if(p<0) p=-p;
				if(t*t!=0)
				{
					f=(float)p/(sqrt(t*t));
					printf("%10.5f",f);
				}
				else printf("NIJE DEFINISANO");
			}
	}
	
	
}
