#include <stdio.h>

void ispis(char naziv_fajla[0],int mat[30][30],int m,int n){
	int i,j;
	FILE *fajl;
	fajl=fopen (naziv_fajla,"w");
	fprintf(fajl,"%d %d\n",m,n);
	for(i=0;i<m;i++){
		for(j=0;j<n;j++) fprintf(fajl,"%5d",mat[i][j]);
		fprintf(fajl,"\n");
			
	}
	fclose(fajl);
}

void nizz(int mat[30][30],int niz[],int m,int n){
	int i,j,br,max;
	for(i=0;i<m;i++){
		max=mat[i][0];
		br=1;
		for(j=1;j<n;j++)
			if(mat[i][j]>max){
				max=mat[i][j];
				br++;
			}
		niz[i]=br;
	}
}

int main(){
	int mat[30][30];
	int niz[30];
	
	FILE *otvori;
	otvori=fopen("PR1.txt","r");
	
	int m,n;
	fscanf(otvori,"%d%d",&m,&n);
	int i,j;
	for(i=0;i<m;i++)
		for(j=0;j<n;j++)
			scanf("%d",&mat[i][j]);
	fclose(otvori);
	
	ispis("PR2.txt",mat,m,n);
	nizz(mat,niz,m,n);
	for(i=0;i<m;i++)
		printf("%5d",niz[i]);
	
}
