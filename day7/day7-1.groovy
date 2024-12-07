def input = new File('input.txt').readLines().collect {
	def parts = it.split(' ').toList()
	[t: parts[0].replace(':','').toBigInteger(), p: parts[1..parts.size()-1].toList()*.toBigInteger()]
}
println input.findAll {
	def result = it.t as BigInteger
	def numbers = it.p as List<BigInteger>
	def poss = []
	def op
	op = { List<BigInteger> l ->
		if (l.size() == 2) {
			poss << l[0] + l[1]
			poss << l[0] * l[1]
		} else {
			def add = [l[0] + l[1]] + l[2..l.size() - 1]
			def mult = [l[0] * l[1]] + l[2..l.size() - 1]
			op(add)
			op(mult)
		}
	}
	op(numbers)
	poss.contains(result)
}.sum { it.t }