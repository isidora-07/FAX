#include <stdio.h>

void unosNiza(int a[],int n){
	int i;
	for(i=0;i<n;i++) scanf("%d",&a[i]);
}

void niz(int a[],int n,int b[]){
	int i,j,fakt;
	for(i=0;i<n;i++){
		fakt=1;
		for(j=1;j<=a[i];j++)
			fakt*=j;
		b[i]=fakt;
	}
}

void ispisNiza(int a[],int n){
	int i;
	for(i=0;i<n;i++) printf("%d\t",a[i]);
}

int main(void){
	int n;
	int a[50],b[50];
	scanf("%d",&n);
	unosNiza(a,n);
	niz(a,n,b);
	ispisNiza(b,n);
}
