def input = new File('input.txt').readLines()
def grid = []
71.times {grid << [''] * 71}
def rows = grid.size()
def cols = grid[0].size()
for (def i = 0; i < 1024; i++) {
	def parts = input[i].split(/,/)*.toInteger()
	def x = parts[0], y = parts[1]
	grid[y][x] = '#'
}

def search = {
	def v = [] as Set
	def q = [[x: 0, y: 0, g: 0]]
	v << q[0].x + '-' + q[0].y
	while (q) {
		def l = q.pop()
		if (l.x == 70 && l.y == 70) {
			return l.g
		}
		[[0, -1], [1, 0], [0, 1], [-1, 0]].each {
			def xn = l.x + it[0]
			def yn = l.y + it[1]
			if (xn >= 0 && yn >= 0 && xn < cols && yn < rows && grid[yn][xn] != '#') {
				if (!v.contains(xn + '-' + yn)) {
					q << [x: xn, y: yn, g: l.g + 1]
					v << xn + '-' + yn
				}
			}
		}
	}
	-1
}

def start = 1024
while (search() > 0) {
	def parts = input[++start].split(/,/)*.toInteger()
	grid[parts[1]][parts[0]] = '#'
}
println input[start]