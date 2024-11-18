#include <stdio.h>
#include <math.h>
main()
{
	int i,fakt,step,p,br,sum;
	float x,eps,s,stepen,im;
	scanf("%f%f",&x,&eps);
	fakt=1;
	for(i=1;i<=x;i++)
		fakt*=i; //x!
	i=1;
	sum=1;
	s=2.0/(2+fakt);	
	do
	{
		i++;		
		br=2*i;
		step=1;
		for(p=1;p<=i;p++)
			step=step*i;
		stepen=sqrt(step);
		sum+=i;
		im=stepen+fakt+sum;
		s+=(float)br/im;
		
	}while(eps>s);
	printf("%6.10f",s);
	
}
