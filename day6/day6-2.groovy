def grid = new File("input.txt").readLines().collect { it.split('').toList() }

def ordinals = [
		'^': [-1, 0],
		'>': [0, 1],
		'v': [1, 0],
		'<': [0, -1]
]
def turn = [
		'^': '>',
		'>': 'v',
		'v': '<',
		'<': '^'
]

def loops = 0
for (i in 0..<grid.size()) {
	for (j in 0..<grid[0].size()) {
		def gridL = grid.collect { it.collect() }
		def cache = [:]
		def curY = gridL.findIndexOf { it.findIndexOf { it in ['^', '>', 'v', '<'] } >= 0 }
		def curX = gridL[curY].findIndexOf { it in ['^', '>', 'v', '<'] }
		def guardDir = '^'
		if (gridL[i][j] == '.') gridL[i][j] = '#'

		while (true) {
			gridL[curY][curX] = 'X'
			def nextY = curY + ordinals[guardDir][0]
			def nextX = curX + ordinals[guardDir][1]

			if (nextX < 0 || nextX >= gridL[0].size() || nextY < 0 || nextY >= gridL.size()) break
			if (gridL[nextY][nextX] == '#') {
				if (cache[guardDir + ' ' + curY + ' ' + curX]) {
					loops++
					break
				}
				cache[guardDir + ' ' + curY + ' ' + curX] = true

				guardDir = turn[guardDir]
				continue
			}
			gridL[nextY][nextX] = guardDir
			curY = nextY
			curX = nextX
		}
	}
}
println loops