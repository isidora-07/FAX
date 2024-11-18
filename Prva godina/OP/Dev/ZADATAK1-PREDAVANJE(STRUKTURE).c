#include <stdio.h>

typedef struct{
	float x,y,z;
} vektor;
///////////////////////////////////////////////////////////////////////////////
vektor zbirVektora(vektor a, vektor b){
	vektor c;
	c.x=a.x+b.x;
	c.y=a.y+b.y;
	c.z=a.z+b.z;
	return c;
}
///////////////////////////////////////////////////////////////////////////////
float skalarniProizvod(vektor a, vektor b){
	float s;
 	s=a.x*b.x+a.y*b.y+a.z*b.z;
 	return s;
 }
/////////////////////////////////////////////////////////////////////////////// 
 vektor vektorskiProizvod(vektor a, vektor b){
 	vektor c;
 	c.x=a.y*b.z-b.y*a.z;
 	c.y=a.z*b.x-b.z*a.x;
 	c.z=a.x*b.y-b.y*a.x;
 	return c;
 }
 ///////////////////////////////////////////////////////////////////////////////
void stampajVektor(vektor a){
 	printf("%10.2f%10.2f%10.2f\n",a.x,a.y,a.z);
 }
 ///////////////////////////////////////////////////////////////////////////////
 int main(){
 	vektor a,b,c;
 	scanf("%f%f%f",&a.x,&a.y,&a.z);
 	scanf("%f%f%f",&b.x,&b.y,&b.z);
	c=zbirVektora(a,b);
	stampajVektor(c);
	printf("%6.10f\n",skalarniProizvod(a,b));
	c=vektorskiProizvod(a,b);
	stampajVektor(c);
 }
