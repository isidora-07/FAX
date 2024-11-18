#include <stdio.h>
void ucitajNiz(int a[],int n){
	int i;
	for(i=0;i<n;i++)
		scanf("%d",&a[i]);		
}

int PomnoziNizove(int a,int b){
	return a*b;
}

void ispisMatrice(int a[50][50],int n){
	int i,j;
	for(i=0;i<n;i++){
		for(j=0;j<n;j++) printf("%d\t",a[i][j]);
		printf("\n");
	}
}

int main(void){
	int n,i,j;
	int niz[50],mat[50][50],pom[50];
	scanf("%d",&n);
	
	for(i=0;i<n;i++) niz[i]=i+1;
	int ind=0;
	for(i=0;i<n;i++){
		if(ind==0){
			for(j=0;j<n;j++) mat[i][j]=niz[j];
			ind=1;
		}
		else {
			for(j=0;j<n;j++) mat[i][j]=PomnoziNizove(niz[j],pom[j]);
		}
		
		for(j=0;j<n;j++) pom[j]=mat[i][j];
	}
	ispisMatrice(mat,n);
	return 0;
}
