#include <stdio.h>
main()
{
	int n,m=0,c,br=0;
	scanf("%d",&n);
	while(n>0)
	{
		c=n%10;
		n=n/10;
		br++;
		if(br%2==1)
			m=m*10+c;
	}
	printf("%2d",m);
}
