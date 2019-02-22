--MODELADO Y PROGRAMACIÓN 2019-2
--Tarea 1b
--Author: Martin Felipe Espinal Cruces.
--Date 2019-02-14

{-
Función que dado dos números enteros y la ayuda de dos funciones
auxiliares "minimo comun" y "multiplos", regresa un entero que 
representa el mínimo común múltiplo.
-}
mcm :: Int -> Int -> Int
mcm 0 _ = 0
mcm _ 0 = 0
mcm a b
   | a == b = a
   | otherwise = minimoComun (multiplos a) (multiplos b)

{-
Función auxiliar que recibe como parámetro dos listas de 
entero verificando valor por valor el elemento mas pequeño
de ambas listas, y lo devuleve.
-}
minimoComun :: [Int] -> [Int] -> Int
minimoComun (x:xs) (y:ys)
   | x > y = minimoComun (x:xs) ys
   | x < y = minimoComun (y:ys) xs
   | otherwise = x


{-
Función auxiliar que dado un número entero, devuelve una
lista de enteros que son divisores comunes de un número.
-}
multiplos :: Int -> [Int]
multiplos n = [x*n | x <- [1,2..]]


{-
Función que recibe dos valores de tipo entero que ayudandose
de dos funciones auxiliares "divisoresPropios" y "comun", y que devuelve 
un entero con el máximo común divisor.
-}
mcd :: Int -> Int -> Int
mcd 0 _ = 0
mcd _ 0 = 0
mcd a b
   |a == b = a
   |otherwise = maximum $ comun (divisoresPropios a) (divisoresPropios b)

{-
Función auxiliar que recibe dos listas y regresa una lista con los
elementos en común.
-}
comun :: Eq a => [a] -> [a] -> [a]
comun a b = filter (\x -> x `elem` a) b

{-
Función auxiliar que dado dos enteros regresa una lista de enteros en la
que se encuentran los valores que dividen al entero.
-}
divisores :: Int -> Int -> [Int]
divisores n 1 = [1]
divisores n m
   | (mod n m == 0) = (m):divisores n (m-1) 
   | otherwise = divisores n (m-1)

{-
Lista auxiliar por comprensión que devuelve la lista de todos divisores de un número
-}
divisoresPropios n = [x | x <-divisores n n, x/=n || x == n]

{-
Función que verifica si el último elemento de la sucesión de Collatz es 1 y devuelve 
verdadero en caso de ser así, o falso en caso contrario.
-}
collatz :: Int -> Bool
collatz 0 = False
collatz 1 = True
collatz a 
   | (last (sucesion a) == 1) = True
   | otherwise = False

{-
Función auxiliar a collatz que regresa una lista con la sucesión de Collatz de un número.
-}
sucesion :: Int -> [Int]
sucesion 1 = []
sucesion y
   | (y `mod` 2 == 0) = par : sucesion par
   | otherwise = impar : sucesion impar
   where 
    par = y `div` 2
    impar = (y*3) +1

{-
Función auxiliar que dado un entero, genera una lista por comprención la cual recibe un una
lista de 2 hasta el número enviado (exceptando al 1 que no es par) y con la condición de saber
si el número de la lista es primo, respuesta que se obtiene con la función auxiliar "primos".
-}
factores ::Int -> [Int]
factores n  = [x | x <- [2..n] , primos x]

{-
Función auxiliar que dado un entero verifica si es un número primo.
-}
primos ::Int -> Bool
primos n = length (divisoresPropios n) <=2