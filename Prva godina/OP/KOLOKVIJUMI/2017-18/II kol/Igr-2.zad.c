#include<stdio.h>
int NapraviNiz(int n,int a[])
{
	int t,i,br,p=0;
	while(n>0)
	{
		a[p]=n%10;
		n=n/10;
		p++;
	}
	for(i=0;i<p/2;i++)
	{
		t=a[i];
		a[i]=a[p-1-i];
		a[p-i-1]=t;
	}
	return p;
}
int FormirajBroj(int a[], int n)
{
	int i,j,br=0,t=1;
	for(i=n-1;i>=0;i--)
	{
		br=br+a[i]*t;
		t=t*10;
	}
	return br;
}
int FormirajMaxBroj(int a[],int n, int m)
{
	int i,j,max,b[50],x;
	for(i=0;i<n;i++)
		b[i]=a[i];
	max=FormirajBroj(b,m);
	for(i=1;i<n-m;i++)
	{
		for(j=0;j<m;j++)
		{
			b[j]=a[i+j];
		}
		x=FormirajBroj(b,m);
		if(x>max);
		max=x;
		
	}
	return max;
	
}
void TransformisiNiz(int a[],int n)
{
	int i,j,t;
	for(i=0;i<n/2;i++)
	{
		t=a[i];
		a[i]=a[n-1-i];
		a[n-i-1]=t;
	}
}

int main()
{
	int i,j,n,m,a[50],b[50],c[50];
	int k,z=0,y,x,t=0,l=0;
		printf("Uneti m: ");
	scanf("%d", &m);
	printf("Uneti n: ");
	scanf("%d", &n);
	printf("Uneti elemente niza: ");
	for(i=0;i<n;i++)
	scanf("%d", &a[i]);
	while(1){
		for(i=0;i<n;i++)
		{
			if(a[i]<0 || (NapraviNiz(a[i],b))<m)
			{
				printf("Uneti m: ");
				scanf("%d", &m);
				printf("Uneti n: ");
				scanf("%d", &n);
				printf("Uneti elemente niza: ");
				for(i=0;i<n;i++)
					scanf("%d", &a[i]);
				l=1;
			}
		}
		if(l==0)
			break;
	}
	for(i=0;i<n;i++)
	{
		k=NapraviNiz(a[i],b);
		
		if(a[i]%2==0)
		{
			a[i]=FormirajMaxBroj(b,k,m);
		}
		else
		{	
			TransformisiNiz(b,k);
			a[i]=FormirajBroj(b,k);
		}	
	}
	printf("\n");
	for(i=0;i<n;i++)
			printf("%d  ",a[i]);
	printf("\n");

	
}
