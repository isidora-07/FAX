#include <stdio.h>
#include <string.h>

int UnosProizvoda(FILE *f, char naziv[], int sifre[]){
	f=fopen(naziv,"r");
	int n;
	fscanf(f,"%d",&n);
	int i;
	for(i=0;i<n;i++)
		fscanf(f,"%d",&sifre[i]);
	fclose(f);
	
	return n;
}

int UnosLicitacija(FILE *f, char naziv[], int sifre[], int max_niz[], int ns){
	f=fopen(naziv,"r");
	int br=0;
	int s1[50], s2[50];
	while(!feof(f)){
		fscanf(f,"%d",&s1[br]); //sifre
		fscanf(f,"%d",&s2[br]); //cene
		br++;
	}
	fclose(f);
	
	int i,j;
	int max;
	
	for(i=0;i<ns;i++){
		max=0;
		for(j=0;j<br;j++)
			if(sifre[i]==s1[j])
				if(max<s2[j])
					max=s2[j];
		max_niz[i]=max;			
	}
	
	return br;
}

void ispisProizvoda(FILE *f, int sifre[], int max_niza[], int ns){
	int i;
	for(i=0;i<ns;i++)
		fprintf(f,"%d  %d\n",sifre[i],max_niza[i]);
}

int IzbaciNelicitirane(int sifre[], int max_niza[], int ns){
	int i,j;
	for(i=0;i<ns-1;i++)
	{
		if(max_niza[i]==0)
		{
			for(j=i;j<ns;j++)
			{
				sifre[j]=sifre[j+1];
				max_niza[j]=max_niza[j+1];
			}		
			i--;
			ns--;
		}			
	}
	
	return ns;
}

void SortProizvode(int sifre[], int max_niza[], int ns){
	int i,j;
	int pom,pom1;
	for(i=0;i<ns-1;i++)
		for(j=i+1;j<ns;j++)
			if(max_niza[i]<max_niza[j])
			{
				pom=max_niza[i];
				max_niza[i]=max_niza[j];
				max_niza[j]=pom;
				
				pom1=sifre[i];
				sifre[i]=sifre[j];
				sifre[j]=pom1;
			}
}

int main(){
	char pr[20],li[20],izlaz[20];
	scanf("%s%s%s",pr,li,izlaz);
	int sifre[50],max_niza[50];
	
	FILE *in1,*in2,*out;
	
	int ns,np;
	ns=UnosProizvoda(in1,pr,sifre);
	out=fopen(izlaz,"w");
	UnosLicitacija(in2,li,sifre,max_niza,ns);
	ispisProizvoda(out,sifre,max_niza,ns);
	fclose(out);
	
	out=fopen(izlaz,"a");
	fprintf(out,"\n--------------------------------------------\n");
	ns=IzbaciNelicitirane(sifre,max_niza,ns);
	ispisProizvoda(out,sifre,max_niza,ns);
	fclose(out);
	
	out=fopen(izlaz,"a");
	fprintf(out,"\n--------------------------------------------\n");
	SortProizvode(sifre,max_niza,ns);
	ispisProizvoda(out,sifre,max_niza,ns);
	fclose(out);
}
