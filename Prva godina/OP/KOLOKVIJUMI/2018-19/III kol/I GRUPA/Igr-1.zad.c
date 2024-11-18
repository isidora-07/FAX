#include <stdio.h>
#include <string.h>

typedef struct{
		int dan;
		int mesec;
		int godina;
}date;

typedef struct{
	char ime_igraca[20];
	int brojGlasova;
}player;

typedef struct{
	int idGlasaca;
	date d;
	char ime_igraca[20];
}vote;

int UnosIgraca(FILE *f, char igracii[20], player igraci[50]){
    f=fopen(igracii,"r");
    int n;
    fscanf(f,"%d",&n);
    int i;
    for(i=0;i<n;i++){
        fscanf(f,"%s",igraci[i].ime_igraca);
        igraci[i].brojGlasova=0;
    }
    fclose(f);

    return n;
}

void IspisIgraca(FILE *f, player igraci[50], int n){
    int i;
    for(i=0;i<n;i++){
        fprintf(f,"%s\t",igraci[i].ime_igraca);
        fprintf(f,"%d\n",igraci[i].brojGlasova);
    }
}

int unosGlasova(FILE *f, char glasovii[20], vote glasovi[50]){
    f=fopen(glasovii,"r");
    int i=0;
    while(!feof(f)){
        fscanf(f,"%d",&glasovi[i].idGlasaca);
        fscanf(f,"%d",&glasovi[i].d.dan);
        fscanf(f,"%d",&glasovi[i].d.mesec);
        fscanf(f,"%d",&glasovi[i].d.godina);
        fscanf(f,"%s",glasovi[i].ime_igraca);
        i++;
    }
    fclose(f);

    return i;
}

void ispisGlasova(FILE *f, int n, vote glasovi[50]){
    int i;
    for(i=0;i<n;i++){
        fprintf(f,"%d\t",glasovi[i].idGlasaca);
        fprintf(f,"%d\t",glasovi[i].d.dan);
        fprintf(f,"%d\t",glasovi[i].d.mesec);
        fprintf(f,"%d\t",glasovi[i].d.godina);
        fprintf(f,"%s\n",glasovi[i].ime_igraca);
    }
}

int IzbaciSaK(vote glasovi[50], int ng, int k){
    int i;

    for(i=k;i<ng-1;i++)
        glasovi[i]=glasovi[i+1];
	ng--;
	
    return ng;
}

int IstiDatum(int dan1, int mesec1, int godina1, int dan2, int mesec2, int godina2 ){
    if((dan1==dan2) && (mesec1==mesec2) && (godina1==godina2)) return 1;
    else return 0;
}

int izbaciNevazece(vote gl[50], int ng){
    int i,j;
    for(i=0;i<ng;i++)
    	for(j=i+1;j<ng;j++)
    		if(gl[i].idGlasaca==gl[j].idGlasaca)
    			if(IstiDatum(gl[i].d.dan,gl[i].d.mesec,gl[i].d.godina,gl[j].d.dan,gl[j].d.mesec,gl[j].d.godina)){
	    			ng=IzbaciSaK(gl,ng,j);
	    			j--;
				}
	
    return ng;		
}

void Prebroj(vote gl[], player ig[], int ng, int ni){
	int i,j;
	for(i=0;i<ni;i++){
		for(j=0;j<ng;j++)
			if(strcmp(ig[i].ime_igraca,gl[j].ime_igraca)==0){
				
				ig[i].brojGlasova++;
				
			}
	}
}


int main(){
    char igracii[20],glasovii[20],izlaz[20];
    date datumi[50];
    player igraci[50];
    vote glasovi[50];

    scanf("%s",igracii);
    scanf("%s",glasovii);
    scanf("%s",izlaz);

    FILE *in1,*in2,*out;

    int n;
    out=fopen(izlaz,"w");
    n=UnosIgraca(in1,igracii,igraci);
    IspisIgraca(out,igraci,n);
    fclose(out);

    int ng;
    out=fopen(izlaz,"a");
    fprintf(out,"--------------------------------------------------------------------------\n");
    ng=unosGlasova(in2,glasovii,glasovi);
    ispisGlasova(out,ng,glasovi);
    fclose(out);

   	
    out=fopen(izlaz,"a");
    fprintf(out,"--------------------------------------------------------------------------\n");
   	ng=izbaciNevazece(glasovi,ng);
    ispisGlasova(out,ng,glasovi);
    fclose(out);


	out=fopen(izlaz,"a");
    fprintf(out,"--------------------------------------------------------------------------\n");
   	Prebroj(glasovi,igraci,ng,n);
    IspisIgraca(out,igraci,n);
    fclose(out);


}


