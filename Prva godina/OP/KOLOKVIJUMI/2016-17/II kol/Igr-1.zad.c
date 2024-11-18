#include <stdio.h>

void unosNiza(float a[],int n){
	int i;
	for(i=0;i<n;i++) scanf("%f",&a[i]);
}

void fja(float a[],int n,float b[]){
	int i,j;
	
}

void printNiz(float a[],int n){
	int i;
	for(i=0;i<n;i++) printf("%6.3f",a[i]);
}

int main(){
	int n,i,j;
	float a[50],b[50];
	scanf("%d",&n);
	unosNiza(a,n);
	for(i=0;i<n;i++){
		b[i]=1;
		for(j=0;j<=i;j++)
			b[i]*=a[i];
	}
	for(i=0;i<n;i++) printf("%6.3f\t",b[i]);
}
