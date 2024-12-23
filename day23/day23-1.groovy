def input = new File('input.txt').readLines()
def links = input.collect {it.split('-').toList().sort()}
def all = [] as Set
for (r1 in links) {
	def a = r1[0], b = r1[1]
	if (a.startsWith('t') || b.startsWith('t')) {
		for (r2 in links) {
			def c = r2[0], d = r2[1]
			if (b != c && b != d) continue
			if (a == c || a == d) continue
			for (r3 in links) {
				def e = r3[0], f = r3[1]
				if (e != a) continue
				if (b == f) continue
				if (c != f && d != f) continue
				all << [a, b, f].sort().join('')
			}
		}
	}
}

println all.size()