#include <stdio.h>
main()
{
	int i,n,br,im,br1,im1;
	float s,x;
	scanf("%f%d",&x,&n);
	br1=x-1;
	im1=1/n;
	br=br1+1;
	im=1-im1;
	s=(float)br/im;
	for(i=2;i<=n;i++)
	{
		br1*=(x-1);
		br=br1+i;
		im1=im1*(1/n);
		im=1-im1;
		s+=(float)br/im;
	}
	printf("%6.10f",s);
	
}
