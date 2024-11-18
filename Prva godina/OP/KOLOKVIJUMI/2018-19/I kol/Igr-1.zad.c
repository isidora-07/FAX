#include <stdio.h>

int main(){
	int n;
	scanf("%d",&n);
	int c, ukupno=0;
	int pom;
	pom=n;
	while(pom>0){
		c=pom%10;
		pom=pom/10;
		ukupno++;
	}
	
	int im=0;
	int i,j;
	int step1=1;
	int step2;
	int sum=0;
	int brojilac;
	float s=0.0;
	
	if(ukupno<3){
		
		for(i=1;i<=n;i++)
			im=im+2*i;
		
		for(i=1;i<n;i++)
		{
			step1=step1*n;
			sum=sum+i;
			step2=1;
			for(j=1;j<=i;j++)
				step2=step2*i;
				
			brojilac=step1-step2;
			
			if(brojilac<0) brojilac=-brojilac;
			
			s=s+(brojilac*sum*1.0)/im;
				
		}	
		
		printf("%6.5f",s);
		
	}
	
}
