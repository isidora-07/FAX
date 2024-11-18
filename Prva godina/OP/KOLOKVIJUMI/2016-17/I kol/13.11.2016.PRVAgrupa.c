#include <stdio.h>
#include <math.h>
main()
{
	int x,n,t,p,k,fakt,i,o,br;
	float f,im,g;
	scanf("%d%d",&x,&n);
	
	if(n>0)
	{
		o=x;
		g=1.0;
		im=1.0;
		
/* n! */	for(i=1;i<=n;i++)
				fakt=fakt*i;
	
		f=(o+g)/fakt;
	
		for(i=2;i<=n;i++)
		{
			o=o*x*x; 
			g=g+i;
			br=o+g;
			im=im*(2*i-1)*(2*i-2);
			f+=br/(im*fakt*1.0);
		}
		printf("%10.5f",f);		
	}
	else
	{
		t=3*x*x+n;
		if(t<=-25 || t>=25)
		{
			f=(x-3*n)+15;
			printf("%10.5f",f);	
		}
		else if((t>=-15 && t<-3) || (t>3 && t<=15))
		{
			if(t-15>n)
			{
				f=n;
				printf("%10.5f",f);	
			}
			else
			{
				f=t-15;	
				printf("%10.5f",f);	
			}
		}	
		else 
			{
				p=x+n;
				if(p<0) p=-p;
				
				if(t*t!=0)
				{
				f=(sqrt(p))/(t*t);
				printf("%10.5f",f);	
				}
				else printf("NIJE DEFINISANO");	
			}
	}
}
