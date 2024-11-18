#include <stdio.h>
#include <string.h>

struct pivo{
	float x;
};

struct ranac{
	int koliko;
	struct pivo vrste_piva[3];
};

struct student{
	char ime[20];
	float zapremina_ranca;
	int n;
	float suma_piva;
	struct pivo niz_piva[100];	
	struct ranac rancevi[100];
};

void ucitajStudente(struct student s[], int *n){
	int i = 0, j, m;
	FILE *f = fopen("Studenti.txt", "r");
	fscanf(f, "%d", &m);
	*n = m;
	
	while(m){
		fscanf(f, "%s", s[i].ime);
		fscanf(f, "%f", &s[i].zapremina_ranca);
		fscanf(f, "%d", &s[i].n);
		for(j=0; j<s[i].n; j++)
			fscanf(f, "%f", &s[i].niz_piva[j].x);
		s[i].rancevi[i].koliko = 0;
		s[i].suma_piva = 0;
		fgetc(f);
		i++;
		m--;
	}
	
	fclose(f);
}

void ispisStudenata(int n, struct student s[]){
	int i, j;
	for(i=0; i<n; i++){
		printf("Ime: %s, ", s[i].ime);
		printf("ranac: %.1fl\n", s[i].zapremina_ranca);
		printf("PIVA: ");
		for(j=0; j<s[i].n; j++)
			printf("%.1fl  ", s[i].niz_piva[j].x);
		printf("\n");
		printf("SUMA %.1f\n", s[i].suma_piva);
		printf("\n");
	}
}

void sort(int n, struct student s[]){
	int i, j;
	struct student t;
	for(i=0; i<n; i++){
		for(j=i+1; j<n; j++){
			if(s[i].zapremina_ranca < s[j].zapremina_ranca){
				t = s[j];
				s[j] = s[i];
				s[i] = t;
			}
		}
	}
}

void sort_piva(int n, struct student s[]){
	int i, j, t;
	float p;
	for(i=0; i<n; i++){
		for(j=0; j<s[i].n; j++){
			for(t=j+1; t<s[i].n; t++){
				if(s[i].niz_piva[j].x > s[i].niz_piva[t].x){
					p = s[i].niz_piva[j].x;
					s[i].niz_piva[j].x = s[i].niz_piva[t].x;
					s[i].niz_piva[t].x = p;
				}
			}
		}
	}
}

void izbaci_piva(int n, struct student s[], float x){
	int i, j, t;
	
	for(i=0; i<n; i++){
		for(j=0; j<s[i].n; j++){
			if(s[i].niz_piva[j].x == x){
				for(t=j+1; t<s[i].n; t++)
					s[i].niz_piva[j].x = s[i].niz_piva[t].x;
				
				s[i].n--;
			}
		}
	}
}


int main(){
	int n;
	struct student s[100];
	ucitajStudente(s, &n);
	ispisStudenata(n, s);
	printf("\n\n");
	sort(n, s);
	sort_piva(n, s);
	ispisStudenata(n, s);
	printf("\n\n");
	sort_piva(n, s);
	rasporedi(n, s);
	ispisStudenata(n, s);
}
