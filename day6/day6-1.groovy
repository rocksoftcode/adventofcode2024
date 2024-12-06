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

def curY = grid.findIndexOf { it.findIndexOf { it in ['^', '>', 'v', '<'] } >= 0 }
def curX = grid[curY].findIndexOf { it in ['^', '>', 'v', '<'] }

while (true) {
	def guardDir = grid[curY][curX]
	grid[curY][curX] = 'X'
	def nextY = curY + ordinals[guardDir][0]
	def nextX = curX + ordinals[guardDir][1]

	if (nextX < 0 || nextX >= grid[0].size() || nextY < 0 || nextY >= grid.size()) break
	if (grid[nextY][nextX] == '#') {
		guardDir = turn[guardDir]
		nextY = curY + ordinals[guardDir][0]
		nextX = curX + ordinals[guardDir][1]
	}
	grid[nextY][nextX] = guardDir
	curY = nextY
	curX = nextX
}

println grid.sum { it.count { it == 'X' } }