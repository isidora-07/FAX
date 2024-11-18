#include <stdio.h>
main()
{
	int n,i,step,fakt,k;
	float x,s;
	scanf("%f%d",&x,&n);
	k=-1;
	step=1;
	fakt=1;
	s=0;
	for(i=1;i<=n;i++)
	{
		k*=-1;
		step*=x*x*x;
		fakt*=(2*i-1)*2*i;
		s+=(float)(k*step)/(fakt+2);
	}
	printf("%6.10f",s);
}	
