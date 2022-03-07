func generatePrimes(start, end) {
	var number = start;
	if (number < 2) {
		number = 2;
	}
	while (number < end) {
		var i = 2;
		var isPrime = true;
		while (i < number - 1) {
			if (number % i == 0) {
				isPrime = false;
				break;
			}
			i = i + 1;
		}
		if (isPrime) {
			print(number);
			print("\t");
		}
		number = number + 1;
	}
}

generatePrimes(0, 100);
