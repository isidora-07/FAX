#include <stdio.h>

void ucitajNiz(int a[], int n){
	int i;
	for(i=0;i<n;i++) scanf("%d",&a[i]);
}

void ispisiNiz(int a[], int n){
	int i;
	for(i=0;i<n;i++) printf("%5d",a[i]);
	printf("\n\n");
}

int CistiNIz(int a[], int n){
	int i,j;
	int pom[30];
	int br,t=0;
	for(i=0;i<n;i++){
		br=0;
		for(j=i+1;j<n;j++)
			if(a[i]==a[j])
				br++;
		
		if(br>0) pom[t++]=a[i];			
	}	

	int ind=1;
	for(i=0;i<n;i++){
		if(ind==1)
			if(a[i]==pom[0]){
				for(j=i;j<n-1;j++)
					a[j]=a[j+1];
				ind=0;//izbacio je element iz niza
			}
	}
	
	if(ind==0) return 1;
	else return 0;
}

int cistiNizPotpuno(int a[], int n){
	int i,j;
	for(i=0;i<n;i++)
		for(j=i+1;j<n;j++)
			if(a[i]==a[j]){
				CistiNIz(a,n);
				j--;
				n--;
			}
	
	return n;
}

int main(){
	int n;
	int a[30];
	scanf("%d",&n);
	ucitajNiz(a,n);
	int flg=CistiNIz(a,n);
	printf("%d\n",flg);
	if(flg==1) 
		ispisiNiz(a,n-1);
	else ispisiNiz(a,n);
	
	n=cistiNizPotpuno(a,n);
	ispisiNiz(a,n);	
}
