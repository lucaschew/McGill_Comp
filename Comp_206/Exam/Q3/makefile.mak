secretapp: main.o secret.o crypto.o
	gcc -o secretapp main.o secret.o crypto.o

main.o: main.c secret.h
	gcc -c main.c 
	
secret.o: secret.c secret.h crypto.h
	gcc -c secret.c 
	
crypto.o: crypto.c crypto.h
	gcc -c crypto.c