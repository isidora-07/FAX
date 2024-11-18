#include <stdio.h>

void ucitajMatricu(float a[50][50], int n)
{
	int i,j;
	for(i=0;i<n;i++)
		for(j=0;j<n;j++)
			scanf("%f",&a[i][j]);
}

//ispod glavne i iznad sporedne dijagonale
int max1(float a[50][50],int n)
{
	int i,j,br;
	br=0;
	for(i=0;i<n;i++)
		for(j=0;j<n;j++)
			if(i!=j && i+j!=n-1 && i>j && i+j<n-1)
				if(a[i][j]<0) br++;
				
	return br;			
}

// ispod sporedne i iznad glavne dijagonale
int max2(float a[50][50],int n)
{
	int i,j,br;
	br=0;
	for(i=0;i<n;i++)
		for(j=0;j<n;j++)
			if(i!=j && i+j!=n-1 && i<j && i+j>n-1)
				if(a[i][j]<0) br++;
				
	return br;	
}

//max 2 broja
int max(int a,int b)
{
	int maks;
	if(a<b)
		maks=b;
	else maks=a;
	
	return maks;		
}

int main()
{
	int n,m1,m2;
	float a[50][50];
	scanf("%d",&n);
	ucitajMatricu(a,n);
	m1=max1(a,n);
	m2=max2(a,n);
	printf("%d",max(max1(a,n),max2(a,n)));
	printf("\n");
	//Komentar
	if(m1>m2)
		printf("Broj negativnih elemenata ispod glavne i iznad sporedne dijagonale %d\n",m1);
	else printf("Broj negativnih elemenata ispod sporedne i iznad glavne dijagonale %d\n",m2);	
}
