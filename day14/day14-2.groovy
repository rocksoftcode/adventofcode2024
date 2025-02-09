def input = new File('input.txt').readLines().collect {
	def parts = it.split(' ')
	def p = parts[0].replace('p=', '').split(',').collect {it.toInteger()}
	def v = parts[1].replace('v=', '').split(',').collect {it.toInteger()}
	[p: [x: p[0], y: p[1]], v: [x: v[0], y: v[1]]]
}

def rows = 103
def cols = 101
def ov = { bots ->
	for (i in 0..<bots.size()) {
		def bot = bots[i]
		if (bots.findAll { it.x === bot.x && it.y === bot.y }.size() > 1) return false
	}
	return true
}

for (i in 0..<100000) {
	def robots = input.collect {
		def y = (it.p.y + it.v.y * i) % rows
		if (y < 0) y += rows
		def x = (it.p.x + it.v.x * i) % cols
		if (x < 0) x += cols
		[y: y, x: x]
	}
	if (ov(robots)) {
		println i
		return
	}
}
