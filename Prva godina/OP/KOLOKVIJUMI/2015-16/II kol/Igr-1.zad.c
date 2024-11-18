#include <stdio.h>

void ucitajNiz(int a[],int n){
	int i;
	for(i=0;i<n;i++) scanf("%d",&a[i]);		
}

void ispisiNiz(int a[],int n){
	int i;
	for(i=n-1;i>=0;i--)	printf("%d\n",a[i]);		
}

int broj(int a[],int n){
	int i,br;
	br=0;	
	for(i=n-1;i>=0;i--)
		br=br*10+a[i];
	return br;
}

int suma(int a[],int n,int b[],int k){
	int suma1,suma2,SUM;
	suma1=broj(a,n);
	suma2=broj(b,k);
	SUM=suma1+suma2;
	return SUM;
}

void niz(int a[],int n,int b[],int k,int c[]){
	int i,t,temp,br;
	temp=suma(a,n,b,k);
	br=0;
	while(temp>0){
		t=temp%10;
		temp=temp/10;
		c[br++]=t;
	}
}

int niz1(int a[],int n,int b[],int k,int c[]){
	int i,t,temp,br;
	temp=suma(a,n,b,k);
	br=0;
	while(temp>0){
		t=temp%10;
		temp=temp/10;
		c[br++]=t;
	}
	return br;
}

int main(void){
	int n,k;
	int a[20],b[20],c[20];
	scanf("%d",&n);
	ucitajNiz(a,n);
	scanf("%d",&k);
	ucitajNiz(b,k);
	niz(a,n,b,k,c);
	printf("\n\n");
	ispisiNiz(c,niz1(a,n,b,k,c));
	return 0;
}
