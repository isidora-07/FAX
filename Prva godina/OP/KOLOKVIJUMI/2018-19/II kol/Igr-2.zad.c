#include <stdio.h>

void ucitajMat(int mat[30][30], int m, int n){
	int i,j;
	for(i=0;i<m;i++)
		for(j=0;j<n;j++)
			scanf("%d",&mat[i][j]);	
}

void stampajMat(int mat[30][30], int m, int n){
	int i, j;
	for(i=0;i<m;i++){
		for(j=0;j<n;j++)
			printf("%d\t",mat[i][j]);
		printf("\n");	
	}
}

int presekNiz(int a[], int b[], int n){
	int i,j,br,ukupan_br;
	ukupan_br=0;
	for(i=0;i<n;i++){
		br=0;
		for(j=0;j<n;j++)
			if(a[i]==b[j])
				br++;
		ukupan_br+=br;		
	}
	if(ukupan_br==0) return 0;
	else return 1;		
}


int main(){
	int m,n;
	int mat[30][30];
	int nova[30][30];
	
	scanf("%d%d",&m,&n); 
	
	ucitajMat(mat,m,n);
	stampajMat(mat,m,n);

	int i;
	int a[30],b[30];
	for(i=0;i<m;i++){
		a[i]=mat[i][0];
		b[i]=mat[i][1];
	}
	
	if(presekNiz(a,b,m)==0) printf("\nIma prazan presek\n");
	else printf("\nNema prazan presek\n");	
}
