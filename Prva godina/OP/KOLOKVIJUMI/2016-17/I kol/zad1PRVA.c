#include <stdio.h>
main()
{
	int i;
	double s,br,im,im1;
	s=1/20;
	i=2;
	im1=2.0;
	while(i<11)
	{
		br=i*i*(i/10.0);
		im=im1*(i+1)*1.0;
		s=s+br/im*1.0;
		i++;
		im1=i;
	}
	printf("%6.3lf",s); //4.701 
}
