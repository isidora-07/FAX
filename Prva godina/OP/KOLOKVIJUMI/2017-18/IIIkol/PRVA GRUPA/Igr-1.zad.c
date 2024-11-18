#include <stdio.h>
#include <string.h>

typedef struct{
	int idPutnika,klasa;
	char imePutnika[20],prezime[20];
}putnik;

typedef struct{
	int idAutobusa, v_polaska_h,v_polaska_min,v_dolaska_h,v_dolaska_min,brPutnika;
}autobus;

int UnosPutnika(FILE *f, char putnici[], putnik pu[]){
	f=fopen(putnici,"r");
	int n;
	fscanf(f,"%d",&n);
	int i;
	for(i=0;i<n;i++){
		pu[i].idPutnika=i+1;
		fscanf(f,"%s%s%d",pu[i].imePutnika,pu[i].prezime,&pu[i].klasa);
	}
	
	fclose(f);
	
	return n;
}

void IspisPutnika(FILE *f, putnik pu[], int np){
	int i;
	for(i=0;i<np;i++)
		fprintf(f,"%d%15s%15s%2d\n",pu[i].idPutnika,pu[i].imePutnika,pu[i].prezime,pu[i].klasa);
}

int UnosAutobusa(FILE *f, char autobusi[], autobus a[]){
	f=fopen(autobusi,"r");
	int i=0;
	while(!feof(f)){
		fscanf(f,"%d%d%d%d",&a[i].v_polaska_h,&a[i].v_polaska_min,&a[i].v_dolaska_h,&a[i].v_dolaska_min);	
		a[i].idAutobusa=i+1;
		a[i].brPutnika=0;
		i++;
	}
	fclose(f);
	
	return i;
}

void ispisAutobusa(FILE *f, autobus a[], int na){
	int i;
	for(i=0;i<na;i++)
		fprintf(f,"%d%4d%4d%4d%4d%4d\n",a[i].idAutobusa, a[i].v_polaska_h,a[i].v_polaska_min,a[i].v_dolaska_h,a[i].v_dolaska_min,a[i].brPutnika);
}

int rasporediPutnike(autobus a[], putnik pu[], int np, int na){
	int i,j;
	autobus t;
	for(i=0;i<na;i++)
		for(j=i+1;j<na;j++)
			if(((a[i].v_dolaska_h*60+a[i].v_dolaska_min)-(a[i].v_polaska_h*60+a[i].v_polaska_min))>((a[j].v_dolaska_h*60+a[j].v_dolaska_min)-(a[j].v_polaska_h*60+a[j].v_polaska_min)))
			{
				t=a[i];
				a[i]=a[j];
				a[j]=t;
			}
		
	int k;	
	int indeks;
	
	for(i=0;i<na;i++)
	{
		for(;;) 
		{	
			indeks = 0;
			for(j=0;j<np;j++){
				if(pu[j].klasa<pu[indeks].klasa)
					indeks=j; 
			}
			
			for(k=indeks+1;k<np;k++){
				pu[k-1]=pu[k];
			}
			np--;
			a[i].brPutnika++;
			if(a[i].brPutnika==5 || np==0)
			{
				break;
			}
		}
	}			
			
	return np;
}

void foromSifra(FILE *f, putnik pu[], int np){
	int i;
	for(i=0;i<np;i++)
		fprintf(f,"%s%d%s\n",pu[i].imePutnika,pu[i].klasa,pu[i].prezime);
}

int main(){
	char putnici[20],autobusi[20],izlaz[20];
	
	scanf("%s%s%s",putnici,autobusi,izlaz);
	
	putnik pu[50];
	autobus a[50];
	
	FILE *in1,*in2,*out;
	
	int na,np;
	np=UnosPutnika(in1,putnici,pu);
	out=fopen(izlaz,"w");
	IspisPutnika(out,pu,np);
	fclose(out);
	
	na=UnosAutobusa(in2,autobusi,a);
	out=fopen(izlaz,"a");
	fprintf(out,"\n------------------------------------------------------------------------------\n\n");
	ispisAutobusa(out,a,na);
	fclose(out);
	
	out=fopen(izlaz,"a");
	fprintf(out,"\n------------------------------------------------------------------------------\n\n");
	np=rasporediPutnike(a,pu,np,na);
	ispisAutobusa(out,a,na);
	fprintf(out,"\n------------------------------------------------------------------------------\n\n");
	IspisPutnika(out,pu,np);
	fclose(out);
		
	out=fopen(izlaz,"a");
	fprintf(out,"\n------------------------------------------------------------------------------\n\n");
	foromSifra(out,pu,np);
	fclose(out);

}
