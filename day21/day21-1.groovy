def input = new File('input.txt').readLines()
def dirs = [[0, -1], [1, 0], [0, 1], [-1, 0]]
def pts = ['^', '>', 'v', '<']
def padNum = [['#', '#', '#', '#', '#'], ['#', '7', '8', '9', '#'], ['#', '4', '5', '6', '#'], ['#', '1', '2', '3', '#'], ['#', '#', '0', 'A', '#'], ['#', '#', '#', '#', '#']]
def padDir = [['#', '#', '#', '#', '#'], ['#', '#', '^', 'A', '#'], ['#', '<', 'v', '>', '#'], ['#', '#', '#', '#', '#']]

def findPaths = {com, start, end ->
	def s = []
	com.eachWithIndex {row, y ->
		row.eachWithIndex {v, x ->
			if (v == start) s = [x, y]
		}
	}
	def inst = [[p: s, path: [], cost: 0]], c, seen = [:], paths = []
	def min = Integer.MAX_VALUE

	while (inst) {
		c = inst.pop()
		if (c.dirId != null) c.path << pts[c.dirId]
		if (com[c.p[1]][c.p[0]] == end) {
			if (c.cost < min) {
				paths = []
				min = c.cost
			}
			if (c.cost == min) paths << c.path
			continue
		}

		def k = c.p.join('_')
		if (seen[k] != null && seen[k] < c.cost) continue
		seen[k] = c.cost
		if (c.cost > min) continue

		dirs.eachWithIndex {it, i ->
			def p = [c.p[0] + it[0], c.p[1] + it[1]]
			if (com[p[1]][p[0]] != '#')
				inst << [
						path : c.path.collect(),
						p    : p,
						dirId: i,
						cost : c.cost + 1
				]
		}
	}

	paths.collect {p -> p.join('') + 'A'}
}

def run
run = {com, code, d ->
	def curPos = 'A', length = 0
	code.split('').each {nextPos ->
		def paths = findPaths(com, curPos, nextPos)
		if (d == 0) length += paths[0].size()
		else length += paths.collect {path -> run(padDir, path, d - 1)}.min()
		curPos = nextPos
	}

	length
}

println input.inject(0) {a, c -> a + c.replace('A', '').toInteger() * run(padNum, c, 2)}