#include <stdio.h>
#include <string.h>

int unosBrojeva(FILE *f, char naziv[], int niz[]){
	f=fopen(naziv,"r");
	int i=0;
	while(!feof(f)){
		fscanf(f,"%d",&niz[i]);
		i++;
	}
	fclose(f);
	
	return i;
}

void ispisBrojeva(FILE *f, int niz[], int n){
	int i;
	fprintf(f,"%d\n",n);
	for(i=0;i<n;i++)
		fprintf(f,"%d\t",niz[i]);
	fprintf(f,"\n");	
}

int izbaciNajmanji(int niz[], int n){
	int i,min;
	min=niz[0];
	for(i=1;i<n;i++)
		if(min>niz[i])
			min=niz[i];	
			
	int j;
	int br=0;
	for(i=0;i<n;i++)
		if(min==niz[i]){
			for(j=i;j<n-1;j++)
				niz[j]=niz[j+1];
			i--;
			n--;	
		}
	
	return n;	
}

int formNov(int niz[], int n, int nov[]){
	int i,j,k,t;
	t=0;
	for(i=0;i<n;i++){
		k=0;
		for(j=0;j<n;j++)
			if(niz[i]==niz[j])
				k++;
		if(k==1)
			nov[t++]=niz[i];
	}
	
	return t;
}


int main(){
	
	char ulaz[20],izlaz[20];
	scanf("%s%s",ulaz,izlaz);
	
	int niz[50],nov[50];
	
	FILE *in,*out;
	
	int n;
	n=unosBrojeva(in,ulaz,niz);
	
	out=fopen(izlaz,"w");
	ispisBrojeva(out,niz,n);
	fclose(out);
	

	int min;
	out=fopen(izlaz,"a");
	fprintf(out,"\n---------------------------------------------------------------\n");
	min=izbaciNajmanji(niz,n);
	ispisBrojeva(out,niz,min);
	fclose(out);

	int fo;
	out=fopen(izlaz,"a");
	fprintf(out,"\n---------------------------------------------------------------\n");
	fo=formNov(niz,min,nov);	
	ispisBrojeva(out,nov,fo);
	fclose(out);
	
	
	
}
