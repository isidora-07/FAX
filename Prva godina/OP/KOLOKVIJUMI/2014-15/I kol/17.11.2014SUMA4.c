#include <stdio.h>
main()
{
	int n,i,j,im,br,fakt,br1,s=0;
	scanf("%d",&n);
	im=1;
	for(i=1;i<=n;i++)
		im*=2; //2^n

	for(i=1;i<=n;i++)
	{
		fakt=1;
		for(j=1;j<=n+i;j++)
			fakt*=j; //(n+i)!
		br1=i-1; //i-1
		br=fakt+br1;
		s+=br/im;	
	}
	printf("%6.10f",(float)s);
}
