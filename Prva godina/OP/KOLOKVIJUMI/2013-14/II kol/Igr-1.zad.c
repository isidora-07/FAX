#include <stdio.h>

void ucitajNiz(float a[],int n)
{
	int i;
	for(i=0;i<n;i++)
		scanf("%f",&a[i]);		
}

void ucitajNiz1(int a[],int n)
{
	int i;
	for(i=0;i<n;i++)
		scanf("%d",&a[i]);		
}

float min(float a[],int n)
{
	int i;
	float min;
	min=a[0];
	for(i=1;i<n;i++)
		if(min>a[i])
			min=a[i];
	
	return min;		
}

int minPoz(float a[],int n)
{
	int i,poz;
	float min;
	min=a[0];
	poz=0;
	for(i=1;i<n;i++)
		if(min>a[i])
		{
			min=a[i];
			poz=i;
		}
	
	return poz;	
}

void izbaciMinEl(float a[],int n)
{
	int i,poz;
	poz=minPoz(a,n);
	for(i=poz;i<n-1;i++)
		a[i]=a[i+1];	
}

void izbaciBr_takmicara(float a[],int b[],int n)
{
	int i,poz;
	poz=minPoz(a,n);
	for(i=poz;i<n-1;i++)
		b[i]=b[i+1];
}

int brTakmicara(float a[],int b[],int n)
{
	int i,redniBr,pozicija;
	pozicija=minPoz(a,n);
	redniBr=b[pozicija];
			
	return redniBr;			
}

int main()
{
	int n;
	float a[50];
	int b[50];
	scanf("%d",&n);	
	ucitajNiz(a,n);
	ucitajNiz1(b,n);
	printf("Prvi takmicar je: %d\n",brTakmicara(a,b,n));
	izbaciMinEl(a,n);
	izbaciBr_takmicara(a,b,n);
	printf("Drugi takmicar je: %d\n",brTakmicara(a,b,n-1));
}
