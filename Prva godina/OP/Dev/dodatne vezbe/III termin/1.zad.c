#include <stdio.h>

int Umetni (int a[], int n, int b)
{
	int i, j, poz=0;
	
	for (i=0; i<n; i++)
	{
		if (a[i] < b)
			poz++;
		else
			break;
	}
	
	for (i=n; i>poz; i--)
		a[i]=a[i-1];
	

	a[poz]=b;
	
	
	return n+1;
}

int Ucitaj (char nazivFile[], int a[])
{
	int n=0, pom;
	
	FILE *vezbe=fopen("ulaz.txt", "r");
	
	fscanf (vezbe, "%d", &pom);	
	
	while (!feof (vezbe))
	{
		
		n=Umetni(a,n,pom);
		fscanf (vezbe, "%d", &pom);	
	}
	
	fclose(vezbe);
	
	return n;
}


main()
{
	int a[50], n, b, i;
	char nazivFile[20];
	
	scanf ("%s", nazivFile);
	printf ("%s", nazivFile);		
	
	n=Ucitaj(nazivFile, a);
	
	for (i=0; i<n; i++)
		printf ("%d\n", a[i]);
		
}
