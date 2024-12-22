def input = new File('input.txt').readLines()*.toLong()
def nextRand = { num ->
	def mul = num * 64L
	num = (mul ^ num) >>> 0L
	num = num % 16777216L
	def rd = Math.floor(num / 32).toLong()
	num = (rd ^ num) >>> 0L
	num = num % 16777216L
	mul = num * 2048L
	num = (mul ^ num) >>> 0L
	num % 16777216L
}

def seq = [:]
input.eachWithIndex {n, i ->
	def add = [:]
	def last = n % 10L, mods = []
	for (j in 0..<2000) {
		n = nextRand(n)
		def cost = n % 10L
		def diff = cost - last
		mods << diff
		if (mods.size() == 4) {
			def k = mods.join(',')
			if (add[k] == null) {
				if (seq[k] == null) seq[k] = 0
				seq[k] += cost
				add[k] = 1
			}
			mods.pop()
		}
		last = cost
	}
}

println seq.values().sort {a, b -> b - a}.first()
