/* Vasilije Dugalic */

#include<stdio.h>
#include<stdlib.h>

#define INF -1
#define POVEZANO 1
#define NEDEFINISANO -1
#define TRUE 1
#define FALSE 0

typedef struct element
{
	int podatak;
	struct element *sledeci;
} Element;

int je_manji(int a, int b)
{
	if(a == INF) return FALSE;
	if(b == INF) return TRUE;
	return a < b;
}

void dodaj(Element **poc, Element **kraj, int broj)
{
	Element *novi = (Element *)malloc(sizeof(Element));
	novi->podatak = broj;
	novi->sledeci = NULL;
	
	if(*poc == NULL)
		*poc = *kraj = novi;
	else
	{
		(*kraj)->sledeci = novi;
		*kraj = novi;
	}
}

int skini(Element **poc, Element **kraj)
{
	if(*poc == NULL) return NEDEFINISANO;
	
	Element *pom = *poc;
	*poc = pom->sledeci;
	int broj = pom->podatak;
	free(pom);
	if(*poc == NULL) *kraj = NULL;
	
	return broj;
}

void bfs(int **graf, int *d, int *obidjeni, int n, int cvor)
{
	Element *poc, *kraj;
	poc = kraj = NULL;
	
	int u, v;
	
	dodaj(&poc, &kraj, cvor);
	u = skini(&poc, &kraj);
	obidjeni[u] = TRUE;
	
	while(u != NEDEFINISANO)
	{
		for(v = 0; v < n; v++)
			if(graf[u][v] != INF && obidjeni[v] == FALSE)
			{
				obidjeni[v] = TRUE;
				d[v] = d[u] + 1;
				dodaj(&poc, &kraj, v);
			}
		
		u = skini(&poc, &kraj);
	}
}

int jednaki(int a, int b)
{
	if(a == INF || b == INF) return FALSE;
	return a == b;
}

int main(void)
{
	int n, m;
	
	scanf("%d%d", &n, &m);
	
	int **graf = (int **)malloc(n * sizeof(int *)); // matrica povezanosti
	
	int *dx = (int *)malloc(n * sizeof(int)); // koliko je potrebno koraka do svakog cvora od cvora x
	int *ox = (int *)malloc(n * sizeof(int)); // obidjeni cvorovi cvora x
	
	int *dy = (int *)malloc(n * sizeof(int)); // -||- za y
	int *oy = (int *)malloc(n * sizeof(int));
	
	// inicijalizacija
	for(int i = 0; i < n; i++)
	{
		graf[i] = (int *)malloc(n * sizeof(int));
		for(int j = 0; j < n; j++)
			graf[i][j] = INF;
			
		dx[i] = dy[i] = INF;
		ox[i] = oy[i] = FALSE;
	}
	
	for(int i = 0; i < m; i++)
	{
		int u, v;
		scanf("%d%d", &u, &v);
		graf[u][v] = graf[v][u] = POVEZANO;
	}
	
	int x, y, z;
	int min;
	scanf("%d%d", &x, &y);
	
	dx[x] = dy[y] = 0;
	
	bfs(graf, dx, ox, n, x);
	bfs(graf, dy, oy, n, y);
	
	int *isto_vreme_gradovi = (int *)malloc(n * sizeof(int));
	
	for(int i = 0; i < n; i++)
		isto_vreme_gradovi[i] = FALSE;
	
	// pronalaze se svi gradovi do kojih je obojici potreban jednak broj presedanja
	for(int u = 0; u < n; u++)
		if(jednaki(dx[u], dy[u]))
			isto_vreme_gradovi[u] = TRUE;

	z = NEDEFINISANO;
	min = INF;
	
	// pronalazi se minimum broja presedanja za gradove do kojih im je bio potreban jednak broj presedanja
	for(int u = 0; u < n; u++)
		if(isto_vreme_gradovi[u] == TRUE && je_manji(dx[u], min))
		{
			z = u;
			min = dx[u];
		}
		
	if(z != NEDEFINISANO) 
		printf("%d\n", z);
	else
		printf("NE POSTOJI\n");
	
	
	return 0;
}
