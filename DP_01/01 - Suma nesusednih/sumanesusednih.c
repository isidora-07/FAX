/*
Dat je niz A od N celih brojeva. 
Odrediti podniz niza A ciji zbir elemenata je maksimalan, 
a u kome nema susednih elemenata niza A.
Smatrati da prazan niz ima zbir elemenata 0.
*/

#include <stdio.h>
#include <stdlib.h>

int *a, *opt, n;

void ispis(int i) {
	if (i>0) {
		if (opt[i]==opt[i-1])
			ispis(i-1);
		else {
			ispis(i-2);
			printf("%d ", a[i]);
		}
	}
}

main() {
	int i;
	
	scanf("%d", &n);
	a = (int *)malloc((n+1)*sizeof(int));
	opt = (int *)malloc((n+1)*sizeof(int));
	
	for (i=1; i<=n; i++)
		scanf("%d", &a[i]);
		
	opt[0]=0;
	if (a[1]>0)
		opt[1]=a[1];
	else
		opt[1]=0;
		
	for (i=2; i<=n; i++) {
		if (opt[i-1] > a[i]+opt[i-2])
			opt[i] = opt[i-1];
		else
			opt[i] = a[i]+opt[i-2];
	}
	
	printf("Maksimalna suma nesusednih u nizu je %d\n", opt[n]);
	
	printf("Suma je sastavljena od sledecih brojeva:\n");
	
	ispis(n);
	printf("\n");

}
