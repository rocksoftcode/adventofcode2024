def mem = [:]
def count
count = {num, s ->
	def k = num + '-' + s;
	if (mem[k]) return mem[k]
	if (s == 0) return BigInteger.ONE
	if (num == 0) {
		def r = count(1, s - 1)
		mem[k] = r
		return r
	}
	def asStr = num.toString()
	def len = asStr.length()
	def r
	if (len % 2 == 0) {
		def left = new BigInteger(asStr.substring(0, (len / 2).toInteger()));
		def right = new BigInteger(asStr.substring((len / 2).toInteger()))
		r = count(left, s - 1) + count(right, s - 1);
	} else r = count(num * new BigInteger(2024L), s - 1)
	mem[k] = r
}
def input = new File('input.txt').readLines()[0].split(/\s/).collect {it.toBigInteger()}
println input.collect {count(it, 75)}.inject(0) {acc, v -> acc + v}