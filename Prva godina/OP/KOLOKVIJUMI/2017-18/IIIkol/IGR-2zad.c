#include <stdio.h>
#include <string.h>

int unosPrijavljenihCifara(FILE *f, char naziv[], int a[]){
	f=fopen(naziv,"r");
	int n;
	fscanf(f,"%d",&n);
	int i;
	for(i=0;i<n;i++)
		fscanf(f,"%d",&a[i]);
	fclose(f);
	
	return n;
}

void ispisCifara(FILE *f, int a[], int n){
	int i;
	fprintf(f,"%d\n",n);
	for(i=0;i<n;i++)
		fprintf(f,"%5d",a[i]);	
}

int unosCifaraBaze(FILE *f, char naziv[], int b[]){
	f=fopen(naziv,"r");
	int i=0;
	while(!feof(f)){
		fscanf(f,"%d",&b[i]);
		i++;
	}
	fclose(f);

	return i;
}

int PokrivanjeCifaraBaze(int a[], int b[], int np, int nb){
	int a1[50],b1[50];
	int i,j,t,t1;
	for(i=0;i<np;i++) a1[i]=a[i];
	for(i=0;i<nb;i++) b1[i]=b[i];
	
	for(i=0;i<np;i++)
		for(j=i+1;j<np;j++)
			if(a1[i]<a1[j])
			{
				t=a1[i];
				a1[i]=a1[j];
				a1[j]=t;	
			}
	
	for(i=0;i<nb;i++)
		for(j=i+1;j<nb;j++)
			if(b1[i]<b1[j])
			{
				t1=b1[i];
				b1[i]=b1[j];
				b1[j]=t;
			}
	int br=0;
	if(np==nb)
	{
		for(i=0;i<np;i++)
			if(a1[i]==b1[i])
				br++;
	}
	else return 0;
	if(br==np) return 1;
	else return 0;
}

void FormSif(FILE *f, int a[], int np){
	int i;
	for(i=0;i<np;i++)
		fprintf(f,"%c",a[i]+'a'-1);
}

int main(){
	char u1[10],u2[10],iz[10];
	scanf("%s%s%s",u1,u2,iz);
	int a[50],b[50];
	char s[50];
	FILE *in1,*in2,*out;
	
	int np;
	np=unosPrijavljenihCifara(in1,u1,a);
	out=fopen(iz,"w");
	ispisCifara(out,a,np);
	fclose(out);
	
	int nb;
	nb=unosCifaraBaze(in2,u2,b);
	out=fopen(iz,"a");
	fprintf(out,"\n\n--------------------------------------------------\n");
	ispisCifara(out,b,nb);
	fclose(out);
	
	out=fopen(iz,"a");
	fprintf(out,"\n\n--------------------------------------------------\n");
	if(PokrivanjeCifaraBaze(a,b,np,nb)) fprintf(out,"POKRIVENO");
	else fprintf(out,"NEPOKRIVENO");
	FormSif(out,a,np);
	fclose(out);
	
}
