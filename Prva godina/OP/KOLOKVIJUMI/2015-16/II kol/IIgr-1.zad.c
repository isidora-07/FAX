#include <stdio.h>

void ucitajNiz(int a[],int n){
	int i;
	for(i=0;i<n;i++) scanf("%d",&a[i]);		
}

int main(void){
	int n,k,i;
	int a[20],b[20],c[20];
	scanf("%d",&n);
	ucitajNiz(a,n);
	scanf("%d",&k);
	ucitajNiz(b,k);
	if(k<n){
		for(i=k;i<n;i++)
			b[i]=0;
		k=n;	
	}
	if(n<k){
		for(i=n;i<k;i++)
			a[i]=0;
		k=n;	
	}
	int sum,prenos=0;
	for(i=0;i<n;i++){
		sum=a[i]+b[i]+prenos;
		if(sum==1) {
			c[i]=1;
			prenos=0;
		}
		else if(sum==2){
				c[i]=0;
				prenos=1;
			}
		else if(sum==3) {
				c[i]=1;
				prenos=1;
			}
	}
	printf("\n");
	if(prenos){
		c[n]=1;
		for(i=n;i>=0;i--) printf("%d\n",c[i]);
	}
	else {
		for(i=n-1;i>=0;i--) 
			printf("%d\n",c[i]);
	}
	return 0;
}
