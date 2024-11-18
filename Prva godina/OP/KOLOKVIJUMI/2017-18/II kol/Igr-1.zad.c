#include <stdio.h>

void ucitajMatricu(int a[50][50], int m, int n){
	int i,j;
	for(i=0;i<m;i++)
		for(j=0;j<n;j++)
			scanf("%d",&a[i][j]);
}

int imi_jedinicna(int a[50][50],int m,int n){
	int i,j,br,br1;
	br=0;
	for(i=0;i<m;i++)
		for(j=0;j<n;j++)
			if(i+j==n-1)
				if(a[i][j]==1)
					br++;
	br1=0;
	for(i=0;i<m;i++)
		for(j=0;j<n;j++)
			if(i+j!=n-1)
				if(a[i][j]==0)
					br1++;
					
	if(br==m && br==n && m*n-m==br1) return 1;
		else return 0;
}

int main(){
	int m,n,p;
	int a[50][50];
	scanf("%d%d",&m,&n);
	ucitajMatricu(a,m,n);
	p=imi_jedinicna(a,m,n);
	if(m==n)
		if(p==1)
			printf("JESTE");
		else printf("NIJE");	
	else printf("Nije kvadratna matrica");	
}
