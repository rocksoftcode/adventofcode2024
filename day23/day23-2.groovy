def input = new File('input.txt').readLines()
def records = input.collect {it.split('-').toList().sort()}
def comps = [] as Set
def password
def p = [:]
records.each {
	def a = it[0], b = it[1]
	comps << a
	comps << b
	if (!p[a]) p[a] = [a] as Set
	p[a] << b
	if (!p[b]) p[b] = [b] as Set
	p[b] << a
}

def compC = comps.collect()
for (comp in compC) {
	p.values().each { p1 ->
		def count = 0
		if (!p1.contains(comp)) return
		def clone = p1.collect().toSet()
		clone.remove(comp)
		def p1c = clone.collect()
		p.values().each { p2 ->
			def profile2Arr = p2.collect()
			if (p1c.every { element -> profile2Arr.contains(element) }) count++
		}
		if (count == 13) {
			println p1c.sort().join(',')
			System.exit(0)
		}
	}
}