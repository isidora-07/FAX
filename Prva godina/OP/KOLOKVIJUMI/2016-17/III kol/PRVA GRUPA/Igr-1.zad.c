#include <stdio.h>
#include <string.h>

typedef struct{
	char ime[20];
	int id_igraca;
	int broj;
}igrac;

int unosIgraca(FILE *f, char naziv1[], igrac igraci[]){
	f=fopen(naziv1,"r");
	int i=0;
	while(!feof(f)){
		fscanf(f,"%s",igraci[i].ime);
		fscanf(f,"%d",&igraci[i].broj);
		igraci[i].id_igraca=i+1;
		i++;
	}
	fclose(f);
	return i;
}

void ispisIgraca(FILE *f, igrac igraci[], int n){
	int i;
	for(i=0;i<n;i++){
		fprintf(f,"%d\t",igraci[i].id_igraca);
		fprintf(f,"%s\t",igraci[i].ime);
		fprintf(f,"%d\n",igraci[i].broj);
	}
}

int formRunda(igrac igraci[], igrac igraci1[], int n){
	int i,j,k,t;
	t=0;
	for(i=0;i<n;i++){
		k=0;
		for(j=0;j<n;j++)
			if(igraci[i].broj==igraci[j].broj)
				k++;
		if(k==1)
			igraci1[t++]=igraci[i];		
	}
	
	return t;
}

int izbaciNajmanji(igrac igraci[], int n){
	igrac min;
	int i,j,br;
	br=0;
	min=igraci[0];
	for(i=1;i<n;i++)
		if(min.broj>igraci[i].broj)
			min=igraci[i];
	for(i=0;i<n;i++)
		if(igraci[i].broj==min.broj){
			for(j=i;j<n;j++)
				igraci[j]=igraci[j+1];
			i--;
			n--;	
		}		
	
	return n;		
}


int najduzeIme(igrac igraci[], int n){
	int i;
	igrac max;
	max=igraci[0];
	for(i=1;i<n;i++)
		if(strlen(igraci[i].ime)>strlen(max.ime))
			max=igraci[i];
	
	return max.id_igraca;			
		
}

int main(){
	char ulaz[20],izlaz[20];
	scanf("%s%s",ulaz,izlaz);
	igrac igraci[50],igraci1[50];
	FILE *in,*out;
	int n;
	out=fopen(izlaz,"w");
	n=unosIgraca(in,ulaz,igraci);
	ispisIgraca(out,igraci,n);
	fclose(out);
	
	int poz=najduzeIme(igraci,n);
	int i;
	for(i=0;i<n;i++)
		if(igraci[i].id_igraca==poz)
			printf("%d\t%s\t%d\n",igraci[i].id_igraca,igraci[i].ime,igraci[i].broj);

	int min;
	out=fopen(izlaz,"a");
	fprintf(out,"--------------------------------------------------------------------------\n");
	min=izbaciNajmanji(igraci,n);
	ispisIgraca(out,igraci,min);
	fclose(out);
	
	int p;
	out=fopen(izlaz,"a");
	fprintf(out,"--------------------------------------------------------------------------\n");	
	p=formRunda(igraci,igraci1,min);
	ispisIgraca(out,igraci1,p);
	fclose(out);

}
