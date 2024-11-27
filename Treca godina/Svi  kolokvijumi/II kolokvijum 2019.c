#include <stdio.h>
#include <stdlib.h>

#define INF (-1)
#define NINF (-2)

#define NILL (-1)
#define TRUE 1
#define FALSE 0


#define IMA 1
#define NEMA 0

int time = 0;
int ciklus = NEMA;

void dfsVisit(double **graf, int *d, int *f, int *topsort, int *br, int n, int u) 
{
	int v;	
	time++;
	d[u] = time;
	
	for (v=1; v<=n; v++) {
		if (graf[u][v] != INF) 
        {
			if (d[v]==NILL) {
				dfsVisit(graf, d, f, topsort, br, n, v);
			}
			else if (f[v]==NILL) {
				ciklus=IMA;
				printf("Ciklus pravi veza (%d, %d)\n",u,v);
			}
		}
	}
	
	time++;
	f[u]=time;
	if (ciklus==NEMA) {
		topsort[*br] = u;
		(*br)--;
	}
	
}

int* get_topsort(double **graf, int n)
{
    int i;
    int *d = (int *)malloc((n+1) * sizeof(int));
    int *f = (int *)malloc((n+1) * sizeof(int));
    int *topsort = (int *)malloc((n + 1) * sizeof(int));
    for (i=1; i<=n; i++) {
		d[i] = NILL;
		f[i] = NILL;
	}
	
	int br=n;
	
	for (i=1; i<=n; i++)
		if (d[i]==NILL)
			dfsVisit(graf, d, f, topsort, &br, n, i);
    
    return topsort;
}

int manji(int i, int j) {
	if (i==NINF)
		return TRUE;
	if (j==NINF)
		return FALSE;
	return i<j;
}

void rekonstrukcija(int i, int polaziste, int *pret) 
{
	if (i==polaziste) {
		printf("%d ", i);
	} else if (pret[i] == NILL)
    {
        printf("Ne postoji putanja do %d\n", i);
    }
	else  {
		rekonstrukcija(pret[i], polaziste, pret);
		printf("%d ", i);
	}
}

int main() {
	double **graf;
	int *pret;
    double *d;
	int n, m;
    double l, ku;
    int i, j, u, v;
	scanf("%d%d", &n, &m);

	graf = (double **)malloc((n+1)*sizeof(double *));
	d = (double *)malloc((n + 1) *sizeof(double));
	pret = (int *)malloc((n + 1) *sizeof(int));

	for (i=0; i<=n; i++) {
		graf[i] = (double *)malloc((n+1)*sizeof(double));
		d[i] = INF;
		pret[i] = NILL;
	}

	for (i=0; i<=n; i++)
		for (j=0; j<=n; j++)
			graf[i][j] = INF;

	for (i=0; i<m; i++) {
		scanf("%d%d%lf%lf", &u, &v, &l, &ku);
		graf[u][v] =  l * ku;
	}

    int k, kafic, v_pom;
    scanf("%d", &k);
    for(i=0; i<k; i++)
    {
        scanf("%d", &kafic);
        for (v=0; v<=n; v++) 
        {
			if (graf[kafic][v]!=INF )
                graf[kafic][v] = graf[kafic][v] * 1.15;
                
		}
        
    }

    int V,P;
    scanf("%d%d", &V, &P);
    int *podnozja = (int *)malloc(P * sizeof(int));
    for(i=0; i<P; i++)
    {
        scanf("%d", &podnozja[i]);
    }
    int *topsort = get_topsort(graf, n);
    
    d[V] = 0;	
    for(i=1; i<=n; i++)
    {
        u = topsort[i];
        for(v=1; v<=n; v++)
        {
            if( graf[u][v] != INF)
            {
                if(manji(d[v], d[u] + graf[u][v]))
                {
                    d[v] = d[u] + graf[u][v];
                    pret[v] = u;
                }
            }
        }
    }
//-------------------------------------------------

	double max = d[podnozja[0]];
    int max_index = 0;
    for(i=1; i<P; i++)
    {
        if(manji( max, d[podnozja[i]]))
        {
            max = d[podnozja[i]];
            max_index = i;
        }
    }


    printf("%.4lf\n", max );
    rekonstrukcija(podnozja[max_index], V, pret);
    printf("\n");
	return 0;

}
