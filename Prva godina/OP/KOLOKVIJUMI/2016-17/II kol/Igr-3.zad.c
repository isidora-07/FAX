#include <stdio.h>

void unosMatrice(int a[50][50],int m,int n){
	int i,j;
	for(i=0;i<m;i++)
		for(j=0;j<n;j++) scanf("%d",&a[i][j]);
}

void ispisMatrice(int a[50][50],int m,int n){
	int i,j;
	for(i=0;i<m;i++){
		for(j=0;j<n;j++) 
			printf("%d\t",a[i][j]);
		printf("\n");	
	}
}


int daLiJesedlo(int a[50][50],int m,int n,int k,int l){
	
	int max,min,i,j;
	min=a[k][0];
	for(i=1;i<m;i++)
		if(a[k][i]<min)
			min=a[k][i];
	max=a[0][l];
	for(i=1;i<n;i++)
		if(a[i][l]>max)
			max=a[i][l];
	if(a[k][l]==max && a[k][l]==min)
		return 1;
	else
		return 0;	
}

int main(void){
	int m,n,k,l;
	int a[50][50],c[50][50];
	scanf("%d%d",&m,&n);
	unosMatrice(a,m,n);
	scanf("%d%d",&k,&l);
	int i,j;
	for(i=0;i<m;i++)
		for(j=0;j<n;j++)
			c[i][j]=daLiJesedlo(a,m,n,i,j);
	ispisMatrice(c,m,n);	
}
