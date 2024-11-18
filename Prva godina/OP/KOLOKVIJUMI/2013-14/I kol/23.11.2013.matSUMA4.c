#include <stdio.h>
main()
{
	int n,i,step,fakt,k;
	float x,s;
	scanf("%f%d",&x,&n);
	k=-1;
	s=0;
	fakt=1;
	step=1;
	for(i=1;i<=n;i++)
	{
		k*=-1;
		fakt*=(2*i-1)*2*i;
		step*=x*x;
		s+=(float)(k*step)/(fakt+i+1);
	}
	printf("%6.10f",s);
}		
