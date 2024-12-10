def input = new File('input.txt').readLines()
		.collect { it.split('')*.toInteger() }
def dirs = [[-1, 0], [1, 0], [0, -1], [0, 1]]
def paths = [:]
def traverse
traverse = { y, x, start ->
	def c = input[y][x]
	dirs.each { d ->
		def ny = y + d[0], nx = x + d[1]
		if (ny < 0 || ny >= input.size() || nx < 0 || nx >= input[0].size()) return
		if (c == 8 && input[ny][nx] == 9) {
			paths[start] << ny + ':' + nx
		}
		if (input[ny][nx] == c + 1) traverse(ny, nx, start)
	}
}

for (y in 0..<input.size()) {
	for (x in 0..<input[y].size()) {
		if (input[y][x] == 0) {
			def k = y + ':' + x
			paths[k] = [] as Set
			traverse(y, x, k)
		}
	}
}

println paths.values().sum { it.size() }