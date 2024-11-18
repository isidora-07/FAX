#include <stdio.h>

int SaberiNizove(int a,int b){
	return a+b;
}

int main(void){
	int n;
	int niz[50],mat[50][50],pom[50];
	scanf("%d",&n);
	int i,j;
	for(i=0;i<n;i++) niz[i]=i+1;
	int ind;
	ind=0;
	for(i=0;i<n;i++){
		if(ind==0){
			for(j=0;j<n;j++) mat[j][i]=niz[j];
			ind=1;
		}
		else{
			for(j=0;j<n;j++) mat[j][i]=SaberiNizove(niz[j],pom[j]);
		}
		for(j=0;j<n;j++) pom[j]=mat[j][i];	
	}
	for(i=0;i<n;i++){
		for(j=0;j<n;j++) printf("%d\t",mat[i][j]);
		printf("\n");	
	}
	return 0;
}
