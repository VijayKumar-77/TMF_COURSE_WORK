public class PrimeNumber {
public static void main(String [] args) {

int x = 10;
int count = 0;
if (x == 1 || x < 0) {
System.out.println ("Given number is not prime");
}
for (int i=2; i<=x-1; i++) {
if (x % i == 0) {
count ++; 
  }
}
if (count > 0) {
System.out.println ("Given number is not a prime");
}
else
{
System.out.println ("Given number is a prime");
    } 
  }
}

Output :

Given number is not a prime


