def map = new File('input.txt').readLines().collect {it.split('').toList()}
def ords = [[1, 0], [0, 1], [-1, 0], [0, -1]]
def start = [0, 0, 0], end = [0, 0]
map.eachWithIndex {List<String> row, int y ->
	row.eachWithIndex {String v, int x ->
		if (v == 'S') start = [x, y, 0]
		if (v == 'E') end = [x, y]
		if (v == '#') return
		map[y][x] = '.'
	}
}

def find = {s, e ->
	def stack = [[p: s, path: [], cost: 0]], curP, seen = [:]
	def min = Integer.MAX_VALUE
	while (stack) {
		curP = stack.pop()
		curP.path << curP.p[0] + ':' + curP.p[1]
		if (curP.p[0] == e[0] && curP.p[1] == e[1]) {
			if (curP.cost < min) {
				min = curP.cost
			}
			continue
		}

		def k = curP.p.join(':')
		if (seen[k] && seen[k] < curP.cost) continue
		seen[k] = curP.cost
		if (curP.cost > min) continue

		ords.eachWithIndex {o, i ->
			def p = [curP.p[0] + o[0], curP.p[1] + o[1], i]
			if (map[p[1]][p[0]] == '#') return true
			stack << [
					path: curP.path.collect(),
					p   : p,
					cost: curP.cost + (p[2] == curP.p[2] ? 1 : 1001)
			]
		}
	}
	min
}

println find(start, end)