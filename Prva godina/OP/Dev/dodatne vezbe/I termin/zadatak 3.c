#include <stdio.h>

main()
{
	int a[50], i, n, j, br=0;
	
	scanf ("%d", &n);
	
	for (i=0; i<n; i++)
		scanf ("%d", &a[i]);
		
	j=0;	
	for (i=0; i<n; i++)
		for (j=1; j<n-1; j++)
			if (a[i] != a[j])
				br++;
	
	
	
	printf ("%d", br);	
	
}
