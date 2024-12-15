def input = new File('input.txt').text.split(/\n\n/)
def map = input[0].readLines()*.split('').toList()
def inst = input[1].replace('\n', '').split('')
def moves = ['^': [-1, 0], '>': [0, 1], 'v': [1, 0], '<': [0, -1]]

def movable
movable = {y, x, offY, offX ->
	def newPos = map[y][x]
	if (newPos == '#') return false
	if (newPos == '.') return true
	if (movable(y + offY, x + offX, offY, offX)) {
		map[y + offY][x + offX] = 'O'
		return true
	}
	false
}

inst.each {
	def m = moves[it]
	def y = map.findIndexOf {it.contains('@')}
	def x = map[y].findIndexOf {it == '@'}
	if (movable(y + m[0], x + m[1], m[0], m[1])) {
		map[y][x] = '.'
		map[y + m[0]][x + m[1]] = '@'
	}
}

def total = 0
for (i in 0..<map.size()) {
	for (j in 0..<map[i].size()) {
		def curr = map[i][j]
		if (curr == 'O') total += i * 100 + j
	}
}
println total
