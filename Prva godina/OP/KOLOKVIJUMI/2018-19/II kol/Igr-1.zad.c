#include <stdio.h>

void ucitajNiz(int a[], int n){
	int i;
	for(i=0;i<n;i++) scanf("%d",&a[i]);
}

void ispisiNiz(int a[], int n){
	int i;
	for(i=0;i<n;i++) printf("%5d",a[i]);
	printf("\n");
}

int SkratiRazlomke(int a[], int b[], int n){
	int i;
	int ind1=0;
	for(i=0;i<n;i++)
		if(a[i]%3==0){
			a[i]=a[i]/3;
			ind1=1;
		}
	int ind2=0;
	for(i=0;i<n;i++)
		if(b[i]%3==0){
			b[i]=b[i]/3;	
			ind2=1;	
		}
	
	if(ind1==1 && ind2==1) return 1;
	else return 0;
}

void SrediRazlomke(int a[], int b[], int n){
	int i,j,t;
	for(i=0;i<n;i++)
		for(j=2;j<=b[i];j++)
			if(a[i]%j==0 && b[i]%j==0){
				a[i]=a[i]/j;
				b[i]=b[i]/j;
				j--;
		}
}

int main(){
	int n;
	int a[30],b[30];
	scanf("%d",&n);
	ucitajNiz(a,n);
	ucitajNiz(b,n);
	printf("%d\n",SkratiRazlomke(a,b,n));
	ispisiNiz(a,n);
	ispisiNiz(b,n);
	SrediRazlomke(a,b,n);
	ispisiNiz(a,n);
	ispisiNiz(b,n);
}
