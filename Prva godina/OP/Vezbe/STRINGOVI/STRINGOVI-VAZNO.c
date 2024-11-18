#include <stdio.h>
#include <string.h>

//strlen
int duzina_stringa(char s[]){
	int i;
	for(i=0;s[i]!='\0';i++);
	return i;
}

//strcpy
void kopiraj_string(char s1[],char s2[]){
	int i;
	for(i=0;s1[i]!='\0';i++)
		s2[i]=s1[i];
	s2[i]='\0';	
}

void kopiraj_deo(char dest[], char src[], int poz, int br){
	int i;
	for (i = 0; i<br; i++)
		dest[i]=src[poz+i];
	dest[i]='\0';
}

//strcat
void nadovezi(char s[],char t[]){
	int i,j;
	for(i=0;s[i]!='\0';i++); //duzina
	
	for(j=0;t[j]!='\0';j++)
		s[i+j]=t[j];
	s[i+j]='\0';	
}

//pozicija odredjenog karaktera
int poz(char s[],char c){
	int i;
	for(i=0;s[i]!='\0';i++)
		if(s[i]==c)
			return i;
	return -1;		
}

int pod_string(char s[],char sub[]){
	int i,j;
	for(i=0;s[i]!='\0';i++){
		for(j=0;sub[j]!='\0';j++)
			if(s[i+j]!=sub[j])
				break;
		if(sub[j]=='\0') return i;		
	}
	return -1;
}

void brisi_deo(char s[],int  poz,int br){
	int i;
	for(i=0;s[i+poz+br]!='\0';i++)
		s[poz+i]=s[poz+i+br];
	s[poz+i]='\0';	
}

int main(){
	char s[]="Kragujevac";
	char s1[30],s2[30],s3[30];
	char t1[]="Dobar",t2[]="dan";
	char c='a';
	
	printf("MOJA FJA: Duzina stringa:%d\n",duzina_stringa(s));
	printf("UGRADJENA:Duzina dtringa %d\n\n",strlen(s));
	printf("---------------------------------------------------------------------------");
	kopiraj_string(t1,s1);
	printf("\n\nMOJA FJA: Kopiranje: %s\n",s1);
	strcpy(s2,t2);
	printf("UGRADJENA: Kopiranje %s\n\n",s2);
	printf("---------------------------------------------------------------------------");
	kopiraj_deo(s3,s,3,2);
	printf("\n\nMOJA FJA: %s\n\n",s3);
	printf("---------------------------------------------------------------------------");
	nadovezi(t1,t2);
	printf("\n\nMOJA FJA: %s\n",t1);
	char t3[]="Isidora",t4[]=" Arandjelovic";
	strcat(t3,t4);
	printf("UGRADJENA: %s\n\n",t3);
	printf("---------------------------------------------------------------------------");
	printf("\n\nMOJA FJA: Prva pozicija karaktera: %d\n\n",poz(s,c));
	printf("---------------------------------------------------------------------------");
	printf("\n\nMOJA FJA: Pozicija podstringa: %d\n\n", pod_string("Kragujevac","uj"));
	printf("---------------------------------------------------------------------------");
	
	
}
