#include <stdio.h>

void unos(int a[],int n){
	int i;
	for(i=0;i<n;i++) scanf("%d",&a[i]);
}

void ispis(int a[],int n){
	int i;
	for(i=0;i<n;i++) printf("%d\t",a[i]);
}

//rotacija levo
void rotacijaLevo(int a[],int n){
	int i,t;
	t=a[0];
	for(i=0;i<n-1;i++)
		a[i]=a[i+1];
	a[n-1]=t;	
}

//izbuje poslednji el iz niza
int izbaciPoslednji(int a[], int n)
{
   return n-1;   
}

//izbacuje prvi el iz niza
int izbaciPrvi(int a[], int n)
{
  int i;
  for(i=0; i<n-1; i++) a[i]=a[i+1];
  return n-1;    
}

//ubacuje X na prvo mesto u nizu
int ubaciX(int a[],int n, int x){
	int i;
	for(i=n;i>0;i--)
		a[i]=a[i-1];
	a[0]=x;	
	return n+1;	
}

//ubaci na k-to mesto broj X
int ubaciK(int a[],int n,int x,int k){
	int i;
	for(i=n;i>k;i--) a[i]=a[i-1];
	a[k]=x;
	return n+1;	
}

//izbaci sa k-tog mesta
int izbaciK(int a[],int n,int k){
	int i;
	for(i=k;i<n-1;i++) a[i]=a[i+1];
	return n-1;
}

//izbacuje sva pojavljivanja datog el. x iz niza 
int izbaciX(int a[],int n,int x){
	int i,j;
	j=0;
	for(i=0;i<n;i++)
		if(a[i]!=x)
			a[j++]=a[i];
	return j;
}

//dodaj broj X na pocetak i kraj niza
int DodajBroj(int a[],int n,int x){
	int i;
	for(i=n;i>0;i--)
		a[i]=a[i-1];
	a[0]=x;
	a[n+1]=x;
	return n+2;	
}

//dodaj broj x na pocetak i kraj niza i napravi broj od toga
int NapraviBroj(int a[],int n,int x){
	int i;
	for(i=n;i>0;i--)
		a[i]=a[i-1];
	a[0]=x;
	a[n+1]=x;
	int br,p;
	br=0;
	p=n+2;
	for(i=0;i<p;i++)
		br=br*10+a[i];
	return br;	
}

//obrne ceo niz
void Rotacija(int a[],int n){
	int i,t;
	for(i=0;i<n/2;i++){
		t=a[i];
		a[i]=a[n-1-i];
		a[n-1-i]=t;
	}
}


//rotacija za k mesta ulevo
void kLevo(int a[],int n,int k){
	int i,t;
	while(k>0){
		t=a[0];
		for(i=0;i<n-1;i++)
				a[i]=a[i+1];
		a[n-1]=t;
		k--;
	}	
}

//rotacija udesno za jedno mesto
void rotacijaDesno(int a[],int n){
	int i,t;
	t=a[n-1];
	for(i=n-1;i>0;i--)
		a[i]=a[i-1];
	a[0]=t;
}

//rotacija za k mesta udesno
void kDesno(int a[],int n,int k){
	int i,t;
	while(k>0){
		t=a[n-1];
		for(i=n-1;i>0;i--)
			a[i]=a[i-1];
		a[0]=t;
		k--;
	}
}

//izbaci neparne elemente
int izbacivanjeNeparnih(int a[],int n){
	int i,j,br;
	for(i=0;i<n;i++){
		if(a[i]%2==1){
			for(j=i;j<n-1;j++)
				a[j]=a[j+1];
			n--;
			i--;
		}
	}
	return n;
}

//dodaje broj X iza parnog el
int dodavanjeK(int a[],int n,int x){
	int i,j,br,t;
	for(i=0;i<n;i++){
		if(a[i]%2==0){
			for(j=n;j>i+1;j--){
				t=a[j];
				a[j]=a[j-1];
				a[j-1]=t;
			}
			a[i+1]=x;
			n++;
			i++;
		}
	}
	return n;
}

int main(void){
	int n,k,x,p;
	int a[50];
	scanf("%d",&n);
	unos(a,n);
	scanf("%d",&x);
	//scanf("%d",&p);
	
	//rotacijaLevo(a,n);
	//k=izbaciPoslednji(a,n);
	//k=izbaciPoslednji(a,n);
	//k=ubaciX(a,n,x);
	//k=ubaciK(a,n,x,p);
	//k=izbaciK(a,n,k);
	//k=izbaciX(a,n,x);
	//k=DodajBroj(a,n,x);
	//ispis(a,k);
	//printf("%d",NapraviBroj(a,n,x));
	//Rotacija(a,n);
	//kLevo(a,n,p);
	//rotacijaDesno(a,n);
	//kDesno(a,n,p);
	//k=izbacivanjeNeparnih(a,n);
	k=dodavanjeK(a,n,x);
	ispis(a,k);
	return 0;
}
