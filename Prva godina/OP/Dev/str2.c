#include <stdio.h>

typedef struct{
	float realno;
	float imaginarno;
} kompleksan_br;

int main(){
	kompleksan_br br1,br2,c;
	scanf("%f%f%f%f",&br1.imaginarno,&br1.realno,&br2.imaginarno,&br2.realno);
	c.imaginarno=br1.imaginarno+br2.imaginarno;
	c.realno=br1.realno+br2.realno;
	printf("%6.3f+%6.3fi",c.realno,c.imaginarno);
}
