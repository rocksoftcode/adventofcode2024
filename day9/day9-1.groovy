def data = new File('input.txt').readLines()[0].split('').collect { new BigInteger(it)}
def comp = []
def id = 0
for (i in 0..<data.size()) {
	for (j in 0..<data[i]) {
		comp << (i % 2 == 0 ? id : null)
	}
	if (i % 2 == 0) id++
}

for (def i = comp.size() - 1; i >= 0; i--) {
	if (comp[i] == null) continue
	def first = comp.withIndex().findIndexOf { it.v1 == null && it.v2 < i }
	if (first == -1) continue
	comp[first] = comp[i]
	comp[i] = null
}
println comp.withIndex().inject(new BigInteger("0")) { a, x -> a + (x.v1 == null ? BigInteger.ZERO : x.v1 * x.v2)}