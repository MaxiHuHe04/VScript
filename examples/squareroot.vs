func sqrt(number, maxError) {
    var x = number;

    while (x * x - number > maxError) {
        x = 0.5 * (x + number / x);
    }

    return x;
}

println(sqrt(2, 0.00001));
