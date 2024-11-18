#include <stdio.h>
#include <math.h>
#include <string.h>

typedef struct{
	int x,y;
	char c[30];	
}t_cent;

int ucitaj(FILE *f, char ime[], t_cent a[]){
	f=fopen(ime,"r");
	int i=0;
	while(!feof(f)){
		fscanf(f,"%d%d",&a[i].x,&a[i].y);
		fscanf(f,"%s",a[i].c);
		i++;	
	}
	fclose(f);

	return i;
}

float rastojanje(t_cent a, t_cent b){
	float d;
	d=sqrt((a.x-a.y)*(a.x-a.y)+(b.x-b.y)*(b.x-b.y));
	
	return d;	
}

int min(t_cent a[], int n, float cena){
	int i;
	for(i=0;i<n;i++)
		if(strcpm(a[i],a[i+1])==0)
}




void ispis(FILE *f, t_cent a[], int n){
	int i;
	for(i=0;i<n;i++)
		fprintf(f,"%s\t%d\t%d\n",a[i].c,a[i].x,a[i].y);
}

int main(){
	int n,i;
	char ulaz[20],izlaz[20];
	t_cent centrala[50];
	scanf("%s%s",ulaz,izlaz);
	FILE *in,*out;
	float cena;
	
	n=ucitaj(in,ulaz,centrala);
	out=fopen(izlaz,"w");
	ispis(out,centrala,n);
	fclose(out);
	
	printf("%f",rastojanje(centrala[0],centrala[1]));
}
