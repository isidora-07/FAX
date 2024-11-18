#include <stdio.h>

int Unesi (char naziv[], float a[])
{
	int n=0, x;
	
	FILE *vezbe;
	vezbe=fopen (naziv, "r");	
	
	while (!feof(vezbe))
	{
		fscanf (vezbe, "%f", &a[n]);
		n++;		
	}
	
	fclose(vezbe);
	
	return n;
}

main()
{
	
	
	
	
	
}
