#include <stdio.h>
#include <math.h>
main()
{
	int x,n,max,min,t,p,q,j,i,stepen,step,im,br,u,sum;
	long fakt;
	float f;
	scanf("%d%d",&x,&n);
	if(n>0)
	{
		f=0.0;
		stepen=1;
		for(i=1;i<=n;i++)
			stepen*=x;	//x^n
		fakt=1;
		for(i=1;i<=x+1;i++)
			fakt*=i;		
		sum=0;
		for(i=1;i<=n;i++)
		{
			step=1;
			for(j=1;j<=n;j++)
				step*=i;	
			sum+=2*i;
			im=sum+1+n;
			fakt*=(x+i+1);
			br=step+stepen+fakt;
			f+=(float)br/im;	
		}
			printf("%10.5f\n",f);
	}
	else
	{
		t=x+n;
		if((t>=-10 && t<=-5) || (t>=5 && t<=10))
		{
			f=t+5;
			printf("%10.5f\n",f);
		}
		else
		{
			p=x-n;
			if(t<0) t=-t;
			if(p<0) p=-p;
			if(t<p)
				max=p;
			else max=t;	
			q=x+3*n*n;
			if(q<0) q=-q;
			if(q>(n*n*n*n-x))
				min=n*n*n*n-x;
			else min=q;
			if(min!=0)
			{
				f=sqrt((float)max)/min;
				printf("%10.5f\n",f);
			}	
			else printf("NIJE DEFINISANO");
		}
	}
}
