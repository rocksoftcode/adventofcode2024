def data = new File('input.txt').readLines()[0].split('').collect { new BigInteger(it)}
def comp = []
def id = 0
for (i in 0..<data.size()) {
	comp << []
	for (j in 0..<data[i]) {
		comp[comp.size() - 1] << (i % 2 == 0 ? id : null)
	}
	if (i % 2 == 0) id++
}

def shift = comp.flatten()
for (def i = comp.size() - 1; i >= 0; i--) {
	if (comp[i].every { it == null }) continue;

	def mapIdx = shift.indexOf(comp[i][0])
	def first = shift.withIndex().findIndexOf {
		it.v1 == null && it.v2 < mapIdx && shift[it.v2..it.v2 + comp[i].size() - 1].every { it == null }
	}
	if (first == -1) continue
	if (!shift[first..first + comp[i].size() - 1].every { it == null }) continue

	comp[i].size().times {
		shift.removeAt(first)
	}
	shift = shift.plus(first, comp[i])
	comp[i].size().times {
		shift.removeAt(mapIdx)
	}
	shift = shift.plus(mapIdx, [null] * comp[i].size())
}

println shift.withIndex().inject(new BigInteger("0")) { a, x -> a + (x.v1 == null ? BigInteger.ZERO : x.v1 * x.v2)}
