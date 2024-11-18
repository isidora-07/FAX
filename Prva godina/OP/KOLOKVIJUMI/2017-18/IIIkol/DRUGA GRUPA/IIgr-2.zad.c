#include <stdio.h>
#include <string.h>

int unosPrijavljenihBrojeva(FILE *f, char ulaz1[], int niz1[]){
	f=fopen(ulaz1,"r");
	int n;
	fscanf(f,"%d",&n); //broj dece
	int i;
	for(i=0;i<n;i++)
		fscanf(f,"%d",&niz1[i]);
	fclose(f);
	
	return n;
}

void ispisBrojeva(FILE *f, int niz[], int n){
	fprintf(f,"%d\n",n);
	int i;
	for(i=0;i<n;i++)
		fprintf(f,"%d\t",niz[i]);
	fprintf(f,"\n");	
}

int unosBrojevaBaze(FILE *f, char ulaz2[], int niz2[]){
	f=fopen(ulaz2,"r");
	int i=0;
	while(!feof(f)){
		fscanf(f,"%d",&niz2[i]);
		i++;
	}
	fclose(f);
	return i;
}

int pokrivanjeBrojeva(int niz1[], int n1, int niz2[], int n2){
	int a[50],b[50];
	int i;
	for(i=0;i<n1;i++)
		a[i]=niz1[i];
	for(i=0;i<n2;i++)
		b[i]=niz2[i];
	
	int j;
	for(i=0;i<n1-1;i++)
		for(j=i+i;j<n1;j++)
			if(a[i]>a[j]){
				int t;
				t=a[i];
				a[i]=a[j];
				a[j]=t;
			}
			
	for(i=0;i<n2-1;i++)
		for(j=i+i;j<n2;j++)
			if(b[i]>b[j]){
				int t1;
				t1=b[i];
				b[i]=b[j];
				b[j]=t1;
			}	
	
	int ind;		
	if(n1==n2){
		for(i=0;i<n1;i++)
			if(a[i]!=b[i]){
				ind=0;
				break;
			}
			else ind=1;
	}		
	else return 0;
	
	if(ind) return 1;
	else return 0;
}

void formSifraa(FILE *f, int niz1[], int n1){
	int i;
	for(i=0;i<n1;i++)
		fprintf(f,"%c",niz1[i]+'A'-1);
}

main(){
	int niz1[50],niz2[50];
	char ulaz1[20],ulaz2[20],izlaz[20];
	scanf("%s",ulaz1);
	scanf("%s",ulaz2);
	scanf("%s",izlaz);
	
	FILE *in1,*in2,*out;
	int n1;
	out=fopen(izlaz,"w");
	n1=unosPrijavljenihBrojeva(in1,ulaz1,niz1);
	ispisBrojeva(out,niz1,n1);
	int n2;
	n2=unosBrojevaBaze(in2,ulaz2,niz2);
	ispisBrojeva(out,niz2,n2);
	fclose(out);
	
	
	out=fopen(izlaz,"a");	
	int tr;
//	n1=unosPrijavljenihBrojeva(in1,ulaz1,niz1);
	tr=pokrivanjeBrojeva(niz1,n2,niz2,n2);
	if(tr) fprintf(out,"POKRIVENO");
	else fprintf(out,"NIJE POKRIVENO");
	formSifraa(out,niz1,n1);
	fclose(out);
	
}
