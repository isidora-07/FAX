#include <stdio.h>
#include <math.h>
main()
{
	int x,n,t,p,p1,min,max,i,j,im,im1,im2,br,brojac,br2,fakt;
	float f;
	scanf("%d%d",&x,&n);
	if(n>0)
	{
		f=0;
		im2=0;
		br2=1;
		im1=0;
		for(i=1;i<=n;i++)
			im1=im1+i;   //1+2+...+n	
		fakt=1;
		for(i=1;i<=x;u++)
			fakt*=i; //x!
		for(i=1;i<=n;i++)
		{
			brojac=1;
			for(j=1;j<=i;j++)
				brojac=brojac*i;  //i^i
			fakt*=(x+i);
			im2=im2+i; //1+2+..+i
			im=im1+im2; //(1+2+...+i)+(1+2+...+n)
			br2=br2*x; //x^i
			br=brojac+br2+fakt;
			f+=(float)br/im;
		}
		printf("%10.5f\n",f);
	}
	else
	{
		t=x+n;
		if(t<=-10 || t>=10)
		{
			f=(x-n)+10;
			printf("%10.5f\n",f);	
		}
		else
		{
			p=x-n; //za min
			p1=-p; //za max
			if(t<0) t=-t;
			if(p1<0) p1=-p1;
			//max
			if(t<p1) max=p1;
				else max=t;
			if(p<0) p=-p;
			//min
			if(t>p) min=p;
				else min=t;	
			if(min!=0)
			{
				f=max/sqrt(min);
				printf("%10.5f\n",f);
			}
			else printf("NIJE DEFINISANO");		
		}
	
	}
}
