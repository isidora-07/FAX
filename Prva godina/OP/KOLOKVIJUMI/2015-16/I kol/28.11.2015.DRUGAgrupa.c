#include <stdio.h>
main()
{
	int im,i,k,n,br2,p,j;
	float s=0;
	scanf("%d%d",&k,&n);
	br2=1;
	for(i=1;i<=n;i++)
		br2*=k;  //k^n
	im=1;	
	for(i=1;i<=n;i++)
	{
		p=1;
		for(j=1;j<=k;j++)
			p*=(i+3); //(i+3)^k ??? PROVERITI
		im*=(2*i-1)*2*i; //(2i)!
		s+=((float)p+br2)/im;	
	}	
		
		
	printf("%6.2f",s);
	
}
