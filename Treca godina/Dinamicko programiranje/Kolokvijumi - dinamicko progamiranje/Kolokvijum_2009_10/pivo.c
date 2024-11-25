#include <stdio.h>
#include <stdlib.h>

int main() {
    int m, n, i, j;
    int *zapremine, *cene, *opt, *litri;

    // m budzet
    // n broj razlicitih pakovanja
    scanf("%d%d", &m, &n);

    zapremine = (int*)malloc((n + 1)*sizeof(int));
    cene = (int*)malloc((n + 1)*sizeof(int));

    opt = (int*)malloc((m + 1)*sizeof(int));
    litri = (int*)malloc((m + 1)*sizeof(int));

    for(i=1; i<=n; i++) 
        scanf("%d", &zapremine[i]);

    for(i=1; i<=n; i++) 
        scanf("%d", &cene[i]);

    opt[0] = 0;

    // maks litara za budzet
    for (j = 1; j <= m; j++) {
        opt[j] = 0;
        litri[j] = 0;

        for (i = 1; i <= n; i++) {
            if (cene[i] <= j) {
                int nova_vrednost = opt[j - cene[i]] + zapremine[i];
                if (nova_vrednost > opt[j]) {
                    opt[j] = nova_vrednost;
                    litri[j] = i;
                }
            }
        }
    }

    // for(i=0; i<=m; i++)
    //     printf("%d\t", opt[i]);
    // printf("\n");
    // for(i=0; i<=m; i++)
    //     printf("%d\t", litri[i]);
    // printf("\n");

    // Ispis rezultata
    printf("Maksimalna kolicina piva: %d litara\n", opt[m]);

    // Ispis odabranih pakovanja
    printf("Odabrana pakovanja:\n");
    int kolicina = m;
    while (kolicina > 0) {
        int odabrano_pakovanje = litri[kolicina];
        if (odabrano_pakovanje == 0) break;
        printf("Pakovanje %d: zapremina %d litara, cena %d dinara\n", odabrano_pakovanje, zapremine[odabrano_pakovanje], cene[odabrano_pakovanje]);
        kolicina -= cene[odabrano_pakovanje];
    }


    return 0;
}