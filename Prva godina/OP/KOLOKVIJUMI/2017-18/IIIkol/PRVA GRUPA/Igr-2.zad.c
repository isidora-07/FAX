#include <stdio.h>
#include <string.h>

/*vraca broj dece*/
int unosPrijavljenihCifara(FILE *f, char naziv[], int niz[]){
	f=fopen(naziv,"r");
	int n;
	fscanf(f,"%d",&n); //broj dece
	int i;
	for(i=0;i<n;i++)
		fscanf(f,"%d",&niz[i]);
	fclose(f);
	
	return n;
}

void ispisCifara(FILE *f, int n, int niz[]){
	int i;
	fprintf(f,"%d\n",n);
	for(i=0;i<n;i++){
		fprintf(f,"%d\t",niz[i]);
	}
	
	fprintf(f,"\n");
}

int unosCifaraBaze(FILE *f, char ulaz2[], int niz1[]){
	f=fopen(ulaz2,"r");
	int i=0;
	while(!feof(f)){
		fscanf(f,"%d",&niz1[i]);
		i++;
	}
	fclose(f);
	
	return i;
}

int pokrivanjeCifara(int niz1[], int niz[], int n, int nb){
	int i,j;
	
	int a[50],b[50];
	
	for(i=0;i<n;i++)
		a[i]=niz[i];
	for(i=0;i<nb;i++)
		b[i]=niz1[i];	
	
	for(i=0;i<n-1;i++)
		for(j=i+1;j<n;j++){
			int pom1;
			if(a[i]>a[j]){
				pom1=a[i];
				a[i]=a[j];
				a[j]=pom1;
			}
		}
	
	for(i=0;i<n-1;i++)
		for(j=i+1;j<n;j++){
			int pom2;
			if(b[i]>b[j]){
				pom2=b[i];
				b[i]=b[j];
				b[j]=pom2;
			}
		}	
		
	int ind;
	if(n==nb){
		for(i=0;i<n;i++)
			if(a[i]!=b[i]){
				ind=0; //ne moze
				break;
			}
			else ind=1;	//moze
	}
	else return 0;
	if(ind) return 1;
	else return 0;
	
}

void formSifra(FILE *f,int niz[], int n){
	int i;
	for(i=0;i<n;i++)
		fprintf(f,"%c",niz[i]+'a'-1);
		
}


int main(){
	char ulaz1[20],ulaz2[20],izlaz[20];
	int niz[50],niz1[50];
	char form[50];
	scanf("%s",ulaz1);
	scanf("%s",ulaz2);
	scanf("%s",izlaz);
	
	FILE *in1 /*ulaz1*/,*in2 /*ulaz2*/,*out/*izlaz*/;

	int n;
	out=fopen(izlaz,"w");
	
	n=unosPrijavljenihCifara(in1,ulaz1,niz);
	ispisCifara(out,n,niz);
	
	fclose(out);

	int nb;
	out=fopen(izlaz,"a");
	
	nb=unosCifaraBaze(in2,ulaz2,niz1);
	ispisCifara(out,nb,niz1);
	
	fclose(out);
	
	int cif;
	out=fopen(izlaz,"a");
	
	cif=pokrivanjeCifara(niz1,niz,n,nb);
	if(cif) fprintf(out,"POKRIVENO");
	else fprintf(out,"NIJE POKRIVENO");
	formSifra(out,niz,n);
	
	fclose(out);

}



