#include <stdio.h>
main()
{
	int i,n,im1,im,br,br1,br2;
	float s,x;
	scanf("%f%d",&x,&n);
	br1=x;
	br2=1;
	im1=1;
	s=(br1+br2)/(im1+n);
	for(i=2;i<=n;i++)
	{
		br1*=x*x;
		br2+=i;
		im1*=(2*i-2)*(2*i-1);
		br=br1+br2;
		im=im1+n;
		s+=(float)br/im;
	}
	printf("%6.10f",s);
}
