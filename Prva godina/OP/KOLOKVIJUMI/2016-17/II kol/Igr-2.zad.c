#include <stdio.h>

void unosNiza(int a[],int n){
	int i;
	for(i=0;i<n;i++) scanf("%d",&a[i]);
}

int Provera(int a[],int n){
	int i,br;
	br=0;
	for(i=0;i<n;i++)
		if(a[i]==0 || a[i]==1) br++;
	if(br==n) return 1;
	else return 0;			
}

int PrevediDekadno(int a[],int n)
{
	int p=1,br=0;
	int i;
	for(i=n-1;i>=0;i--){
		br=br+p*a[i];
		p=p*2;
	}
	return br;
}

void SveUsesnaest(int a[],int n){
	int i,j,broj,c,k;
	int b[50];
	k=0;
	broj=PrevediDekadno(a,n);
	while(broj){
		c=broj%16;
		broj=broj/16;
		b[k++]=c;
	}
	for(i=k-1;i>=0;i--){
		if(b[i]==10) printf("A");
		else if(b[i]==11) printf("B");
		else if(b[i]==12) printf("C");
		else if(b[i]==13) printf("D");
		else if(b[i]==14) printf("E");
		else if(b[i]==15) printf("F");
		else printf("%d",b[i]);
	}
}

int main(void){
	int n;
	int a[32];
	scanf("%d",&n);
	unosNiza(a,n);
	
	if(Provera(a,n)){
		printf("%d  ",PrevediDekadno(a,n));
		SveUsesnaest(a,n);
	}
	else printf("NEMOGUC PREVOD");	
	return 0;
}
