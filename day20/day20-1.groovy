def input = new File('input.txt').readLines()
def result = 0
def numRows = input.size()
def numCols = input[0].length()
def cur = [x: 0, y: 0]
top:
for (def i = 0; i < numRows; i++) {
	for (def j = 0; j < numCols; j++) {
		if (input[i][j] == 'S') {
			cur.x = j
			cur.y = i
			break top
		}
	}
}

def d = []
numRows.times {i -> d[i] = []; numCols.times {d[i] << -1}}
d[cur.y][cur.x] = 0
while (input[cur.y][cur.x] != 'E') {
	for (dir in ([[0, -1], [1, 0], [0, 1], [-1, 0]])) {
		def x = dir[0], y = dir[1]
		def nx = cur.x + x
		def ny = cur.y + y
		if (ny < 0 || nx < 0 || ny >= numRows || nx >= numCols) continue
		if (input[ny][nx] == "#") continue
		if (d[ny][nx] != -1) continue
		d[ny][nx] = d[cur.y][cur.x] + 1
		cur.x = nx
		cur.y = ny
	}
}

def limit = 102
for (def i = 0; i < numRows; i++) {
	for (def j = 0; j < numCols; j++) {
		if (input[i][j] == '#') continue
		for (n in [[j, i + 2], [j + 1, i + 1], [j + 2, i], [j + 1, i - 1]]) {
			def nx = n[0], ny = n[1]
			if (nx < 0 || ny < 0 || nx >= numCols || ny >= numRows) continue
			if (input[ny][nx] == '#') continue
			if (Math.abs(d[i][j] - d[ny][nx]) >= limit) result++
		}
	}
}

println result