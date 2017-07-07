octal::Integer->String
octal 1= "1"
octal 2= "2"
octal 3= "3"
octal 4= "4"
octal 5= "5"
octal 6= "6"
octal 7= "7"
octal n = (octal $ div n 8)++(show $ mod n 8)


entero = [1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1]
numrom = ["M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"]

convert :: Integer->String
convert num | num > 0&& num < 4000 = roman 0 num
            | otherwise = "Numero fuera de rango"

roman _ 0 = ""
roman m n | n >= entero !! m = numrom !! m ++ roman m (n-entero!!m)
          | otherwise = roman (m + 1) n


conv ::Integer->String
conv n= (convert n) ++" "++ (octal n)
