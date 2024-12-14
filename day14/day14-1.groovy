def input = new File('input.txt').readLines().collect {
	def parts = it.split(' ')
	def p = parts[0].replace('p=', '').split(',').collect {it.toInteger()}
	def v = parts[1].replace('v=', '').split(',').collect {it.toInteger()}
	[p: [x: p[0], y: p[1]], v: [x: v[0], y: v[1]]]
}

def rows = 103
def cols = 101
def robots = input.collect {
	def y = (it.p.y + it.v.y * 100) % rows
	if (y < 0) y += rows
	def x = (it.p.x + it.v.x * 100) % cols
	if (x < 0) x += cols
	[y: y, x: x]
}

def avr = (rows - 1) / 2
def avc = (cols - 1) / 2
def tl = robots.findAll {it.y < avr && it.x < avc}.size()
def tr = robots.findAll {it.y < avr && it.x > avc}.size()
def bl = robots.findAll {it.y > avr && it.x < avc}.size()
def br = robots.findAll {it.y > avr && it.x > avc}.size()
println tl * tr * bl * br
