#include <stdio.h>

void ucitajNiz(int a[],int n){
	int i;
	for(i=0;i<n;i++) scanf("%d",&a[i]);
}
void shift(int a[],int n){
	int i,t;
	t=a[0];
	for(i=0;i<n-1;i++)
		a[i]=a[i+1];
	a[n-1]=t;	
}

void printMat(int a[20][20],int n){
	int i,j;
	for(i=0;i<n;i++){
		for(j=0;j<n;j++)
			printf("%5d",a[i][j]);
		printf("\n");	
	}
}

int main(){
	int n,i,j;
	int a[20],b[20][20];
	scanf("%d",&n);
	ucitajNiz(a,n);
	for(i=0;i<n;i++) b[0][i]=a[i];
	for(i=1;i<n;i++)
	{
		shift(a,n);
		for(j=0;j<n;j++)
			b[i][j]=a[j];
	}
	printMat(b,n);
}
