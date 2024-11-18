#include <stdio.h>
main()
{
	int i;
	double s,br,im,im1;
	s=1.0;
	i=1;
	im1=1.0;
	while(i<11)
	{
		br=i/10.0+i*i*i;
		im=im1+(i+1)+(i+2);
		s=s*(double)br/im*1.0;
		i++;
		im1=i;
	}
	printf("%6.3f",s);  //2355124.957132
}
