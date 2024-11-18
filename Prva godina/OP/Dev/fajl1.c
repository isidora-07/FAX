#include <stdio.h>

int main(){
	int m,n;
	int mat[50][50];
	FILE *fajl;
	scanf("%d%d",&m,&n);
	int i,j;
	for(i=0;i<m;i++)
		for(j=0;j<n;j++) scanf("%d",&mat[i][j]);
	fajl=fopen("PR1.txt","w");
	fprintf(fajl,"%d %d\n",m,n);
	for(i=0;i<m;i++){
		for(j=0;j<n;j++)
			fprintf(fajl,"%5d",mat[i][j]);
		fprintf(fajl,"\n");	
	}	
	fclose(fajl);
}
