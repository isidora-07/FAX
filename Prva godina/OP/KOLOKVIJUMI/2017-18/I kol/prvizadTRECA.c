#include <stdio.h>
main()
{
	int n,m=0,p=0,c,br=0,sum=0;
	scanf("%d",&n);
	while(n>0)
	{
		c=n%10;
		n=n/10;
		br++;
		sum+=c;
		if(br==1)
			m=m*10+c;
		else
			if(c>(sum/br))
				m=m*10+c;
			
	}
	printf("%d",m);
	
}
