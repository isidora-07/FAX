#include <stdio.h>
main()
{
	int n,i,j,fakt,im1,br1,br2;
	float x,s;
	scanf("%f%d",&x,&n);
	
	fakt=1;
	for(i=1;i<=n;i++)
		fakt*=i;	//n!
	br2=1;	
	for(i=1;i<=n;i++)
		br2*=x;	//x^n	
	im1=1; //za 1+..+i
	s=(1+br2)/(im1+fakt);	
	for(i=2;i<=n;i++)
	{
		br1=1;
		for(j=1;j<=i*2;j++)
			br1*=i;	//i^2i	
		im1+=i;
		s+=(br1+br2)/(im1+fakt);
	}		
	printf("%6.10f",(float)s);
}
