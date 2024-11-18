#include <stdio.h>
#include <math.h>
main()
{
	int x,n,t,p,p1,max,min,znak,i,j,step,stepen,sum,fakt,im,br;
	float f;
	scanf("%d%d",&x,&n);
	if(n>0)
	{
		step=1;
		for(i=1;i<=3*n;i++)
			step*=x; //x^3n
		sum=0;
		znak=1;	
		fakt=1;
		f=0;
		for(i=1;i<=n;i++)
		{
			stepen=1;
			for(j=1;j<=n;j++)
				stepen*=3*i; //(3i)^n
			fakt*=(3*i-2)*(3*i-1)*3*i;
			sum+=3*i;
			znak=-znak;
			br=step+sum;
			im=fakt+stepen;	
			f=f+(znak*br*1.0)/im;			
		}		
		printf("%10.5f\n",f);
	}
	else
	{
		t=3*x+3*n;
		if((t>=-15 && t<-10) || (t>10 && t<=15))
		{
			if(t!=0)
			{
				f=1.0/(t-10);
				printf("%10.5f\n",f);
			}
			else printf("NIJE DEFINISANO");
		}
		else
		{
			p=3*x-3*n;
			p1=-p;
			if(t<0) t=-t;
			if(p1<0) p1=-p;
			if(t<p1) max=p1;
			else max=t;
			if(t>p) min=p;
			else min=t;	
			if(min!=0)
			{
				f=(float)max/sqrt(min);
				printf("%10.5f\n",f);
			}	
			else printf("NIJE DEFINISANO");	
		}
		
	}
		
}
