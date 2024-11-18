#include <stdio.h>
main()
{
	int i,x,n,fakt,fakt1,im;
	float s;
	scanf("%d%d",&x,&n);
	fakt=1;
	for(i=1;i<=x;i++)
		fakt*=i; //x!
	im=1;
	fakt1=2*3;
	s=((float)fakt1+fakt)/im;
	for(i=2;i<=n;i++)
	{
		im+=i; //1+...+i	
		fakt1*=2*i*(2*i+1);
		s+=((float)fakt1+fakt)/im;
	}	
	printf("%6.2f",s);
}
