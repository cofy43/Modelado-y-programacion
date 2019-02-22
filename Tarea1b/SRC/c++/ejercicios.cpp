/**
* @file ejercicios.cpp
* @Author Martin Felipe Espinal Cruces
* @date 09/02/2019
*/
#include <iostream>
#include <vector>


using namespace std;

int mcm(int num1, int num2);
int cantidad;
int mcd(int num1, int num2);
bool sucesionCollatz(int n);
int * factoresPrimos(int n);
void menu();

/**
* @brief Método principal donde se realizaran las pruebas.
* @param void
* @return int 0;
*/
int main() {
	bool continua = true;
	int opcion, numero1, numero2, seguir, factores, resultado;
	vector<int> respuesta;
	menu();
	cin >> opcion;
	while (continua){
		switch (opcion) {
		case 1: 
			cout << "introduce el primer número:" << endl;
			cin >> numero1;
			cout << "introduce el segundo número:" << endl;
			cin >> numero2;
			resultado = mcm(numero1, numero2);
			cout << resultado << endl;
			cout << "¿Desea regresar al menú principal?" << "Si (1), Salir (2)" << endl;
			cin >> seguir;
			if (seguir == 2) {
				continua  = false;
			}else if(seguir == 1) {
				menu();
				cin >> opcion;
			}
		break;

		case 2:
			cout << "introduce el primer número:" << endl;
			cin >> numero1;
			cout << "introduce el segundo número:" << endl;
			cin >> numero2;
			resultado = mcm(numero1, numero2);
			cout << resultado << endl;
			cout << "¿Desea regresar al menú principal?" << "Si (1), Salir (2)" << endl;
			cin >> seguir;
			if (seguir == 2) {
				continua  = false;
			}else if(seguir == 1) {
				menu();
				cin >> opcion;
			}
		break;

		case 3:
			cout << "introduce un número:" << endl;
			cin >> numero1;
			if (sucesionCollatz(numero1)) {
				cout << "El último numero de la sucesion es uno" << endl;
			}else {
				cout << "El último numero de la sucesion no es uno" << endl;
			}
			
			cout << "¿Desea regresar al menú principal?" << "Si (1), Salir (2)" << endl;
			cin >> seguir;
			if (seguir == 2) {
				continua  = false;
			}else if(seguir == 1) {
				menu();
				cin >> opcion;
			}
		break;

		case 4:
			cout << "Elige un número" << endl;
			cin >> factores;
			static int * r = factoresPrimos(factores);
			for(int i = 0; i < cantidad; i++) {
				cout << r[i] << endl;
			}
			cout << "¿Desea regresar al menú principal?" << "Si (1), Salir (2)" << endl;
			cin >> seguir;
			if (seguir == 2) {
				continua  = false;
			}else if(seguir == 1) {
				menu();
				cin >> opcion;
			}
			//cout << respuesta << endl;
		break;

		case 5:
			continua = false;
		break;

		default:
			cout << "opcion inválida" << endl;
			menu();
			cin >> opcion;
		break;
		}
	
	}
	return 0;
}

/**
* @brief Método que dado dos números obtiene el mínimo común multiplo.
* @param int num1, int num2: Números para obtener el mínimo común multiplo.
* @return int mcm: Mínimo común multiplo de dos valores.
*/
int mcm(int num1, int num2) {
    int mcm = 0;
    int a = std::max(num1, num2);
    int b = std::min(num1, num2);
    mcm = (a/mcd(a,b))*b;
    return mcm;
}

/**
* @brief Función auxiliar para correr las distintas funciones
* @param void
* @return void
*/
void menu() {
	cout << "Elige alguna opcion" << endl;
	cout << "[1] Calcular el mínimo común múltiplo" << endl;
	cout << "[2] Calcular el máximo común divisor" << endl;
	cout << "[3] Verificar si el último número de la sucesion de Collatz es uno " << endl;
	cout << "[4] lista de descomposición en factores primos de un número" << endl;
	cout << "[5] Salir" << endl;
}

/**
* @brief Método que dado dos números obtiene el maxímo común divisor.
* @param int num1, int num2: Valores numéricos.
* @return int mcd: Mínimo común multiplo de dos valores.
*/
int mcd(int num1, int num2) {
    int mcd = 0;
    int a = std::max(num1, num2);
    int b = std::min(num1, num2);
    while(b != 0) {
        mcd = b;
        b = a % b;
        a = mcd;
    }
    return mcd;
}

/**
* @brief Nos indica si el último número de la suceción de Collatz es uno.
* @param int n: Numero de la posición de la suceción de Collatz.
* @return bolean: Verdadero de es uno, falso si no lo es
*/
bool sucesionCollatz(int n) {
	while (n != 1) {
		if (n % 2 == 0)
			n = n / 2;
		else
			n = n * 3 +1;
		printf("%d ", n);
	}
	return (n == 1);
}

/**
* @brief Método que devuel la factorización de numeros primos de algún número.
* @param int n: Número a factorizar
* @return int[] lista: Factorización en números primos.
*/
int * factoresPrimos(int n) {
	int i = 2;
	static int r[100];
    cantidad = 0;
	while(n != 1) {
	    if (n % i == 0) {
		    n = n / i;
			r[cantidad ++] = i;
    	}else {
			i++;
		}  
  	}
	return r;
  	//return factores;
}