#include <stdio.h>

int main(){
	int br_pojavljivanja[26];
	int i;
	FILE *citanje;
	FILE *pisanje;
	char c;
	
	for(i=0;i<26;i++)
		br_pojavljivanja[i]=0;
		
	citanje=fopen("TEKST1.txt","r");
	while(!feof(citanje)){
		c=fgetc(citanje);
		if(c>='a' && c<='z')
			br_pojavljivanja[c-'a']++;
	}
	fclose(citanje);
	
	pisanje=fopen("TEKST2.txt","w");
	for(i=0;i<26;i++)
		fprintf(pisanje,"%c  %d\n",i+'a',br_pojavljivanja[i]);
	fclose(pisanje);		
}
