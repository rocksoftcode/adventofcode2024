def input = new File('input.txt').readLines()[0].split(/\s/).toList()
def stones = input.collect()
25.times {
	def after = []
	stones.each {
		if (new BigInteger(it) == BigInteger.ZERO) after << '1'
		else if (it.length() % 2 == 0) after += [new BigInteger(it.substring(0, (it.length() / 2).toInteger())).toString(), new BigInteger(it.substring((it.length() / 2).toInteger())).toString()]
		else after << new BigInteger(it).multiply(new BigInteger(2024L)).toString()
	}
	stones = after
}
println stones.size()