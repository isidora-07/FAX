#include <stdio.h>
#include <stdlib.h>

#define NILL -1

int main() {
	int *optSirina, *optCena, *rek;
	int sirinaZida, brOrmara, brStolova;
	int *sirineOrmara, *sirineStolova, *ceneOrmara, *ceneStolova;
	int i, j;
	
	scanf("%d%d",&brOrmara,&brStolova);
	
	sirineOrmara = (int *)malloc((brOrmara+1)*sizeof(int));
	ceneOrmara = (int *)malloc((brOrmara+1)*sizeof(int));
	sirineStolova = (int *)malloc((brStolova+1)*sizeof(int));
	ceneStolova = (int *)malloc((brStolova+1)*sizeof(int));
	
	// Unos ormara
	for (i=1; i<=brOrmara; i++)
		scanf("%d%d",&sirineOrmara[i], &ceneOrmara[i]);
		
	// Unos stolova
	for (i=1; i<=brStolova; i++)
		scanf("%d%d",&sirineStolova[i], &ceneStolova[i]);

	scanf("%d",&sirinaZida);
	optSirina = (int *)malloc((sirinaZida+1)*sizeof(int));
	optCena = (int *)malloc((sirinaZida+1)*sizeof(int));
	rek = (int *)malloc((sirinaZida+1)*sizeof(int));	
		
	//Racunanje optimuma
	optSirina[0] = 0;
	optCena[0] = 0;
	
	//Napuniti ranac samo ormarima
	for (i=1; i<=sirinaZida; i++) {
		optSirina[i] = optCena[i] = rek[i] = 0;
		for (j=1; j<=brOrmara; j++) {
			if (sirineOrmara[j] <= i) {
				if (sirineOrmara[j]+optSirina[i-sirineOrmara[j]] > optSirina[i]) {
					optSirina[i] = sirineOrmara[j]+optSirina[i-sirineOrmara[j]];
					optCena[i] = ceneOrmara[j]+optCena[i-sirineOrmara[j]];
					rek[i] = j;
				}
				else if (sirineOrmara[j]+optSirina[i-sirineOrmara[j]] == optSirina[i] &&
					ceneOrmara[j]+optCena[i-sirineOrmara[j]] < optCena[i]) 
				{
					optCena[i] = ceneOrmara[j]+optCena[i-sirineOrmara[j]];
					rek[i] = j;
				}
			}
		}
	}


	// Dodati jedan sto
	int maxSirina, minCena, najSto, sirinaZaOrmare;
	najSto = 1;
	sirinaZaOrmare = sirinaZida - sirineStolova[1];
	maxSirina = sirineStolova[1] + optSirina[sirinaZaOrmare];
	minCena = ceneStolova[1] + optCena[sirinaZaOrmare];
	
	for (i=2; i<=brStolova; i++) {
		sirinaZaOrmare = sirinaZida - sirineStolova[i];
		if (sirineStolova[i] + optSirina[sirinaZaOrmare] > maxSirina) {
			maxSirina = sirineStolova[i] + optSirina[sirinaZaOrmare];
			minCena = ceneStolova[i] + optCena[sirinaZaOrmare];
			najSto = i;
		}
		else if (sirineStolova[i] + optSirina[sirinaZaOrmare] == maxSirina &&
			ceneStolova[i]+optCena[sirinaZaOrmare] < minCena)
		{ 
			minCena = ceneStolova[i] + optCena[sirinaZaOrmare];
			najSto = i;
		}
	} 
	
	printf("%d %d\n", maxSirina, minCena);
	
	// Rekonstrukcija sto
	printf("%d\n", najSto);
	
	//Rekonstrukcija
	i = sirinaZida - sirineStolova[najSto];
	while (rek[i]>0) {
		printf("%d ", rek[i]);
		i -= sirineOrmara[rek[i]];
	}
	printf("\n");
	
	return 0;
	
}













