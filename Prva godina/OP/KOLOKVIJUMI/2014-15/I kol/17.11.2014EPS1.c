#include <stdio.h>
main()
{
	int i,p;
	float t,im1,step,br,im,eps,s;
	scanf("%f",&eps);
	i=1;
	t=1.0;
	s=0.0;
	step=2.0;
	do
	{
		br=i*4.0;
		step*=2;
		im1=t*(i+1)*(i+2);		
		im=step*im1*1.0;
		s+=(float)br/im;
		i++;
		t=i;
	}
	while(eps<(float)br/im); //USLOV???
	printf("%6.10f",s);
}
