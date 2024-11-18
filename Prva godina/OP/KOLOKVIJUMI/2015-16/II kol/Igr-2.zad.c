#include <stdio.h>

void unesiNiz(int a[],int n){
	int i;
	for(i=0;i<n;i++) scanf("%d",&a[i]);
}

void shift(int a[],int n){
	int i,t;
	t=a[n-1];
	for(i=n-1;i>0;i--)
		a[i]=a[i-1];
	a[0]=t;			
}

void printMat(int a[20][20],int n){
	int i,j;
	for(i=0;i<n;i++){
		for(j=0;j<n;j++) printf("%5d",a[i][j]);
		printf("\n");	
	}
}

int main(void){
	int n,i,j;
	int a[20],b[20][20];
	scanf("%d",&n);
	unesiNiz(a,n);
	for(j=0;j<n;j++) b[j][0]=a[j];
	for(j=1;j<n;j++) {
		shift(a,n);
		for(i=0;i<n;i++) 
			b[i][j]=a[i];
	}
	printMat(b,n);
	return 0;
}
