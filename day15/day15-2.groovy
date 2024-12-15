def input = new File('input.txt').text.split(/\n\n/)
def rawMap = input[0].readLines()*.split('').toList()
def map = []
rawMap.each { l ->
	def r = []
	l.each { c ->
		if (c == '#') r += ['#', '#']
		else if (c == 'O') r += ['[', ']']
		else if (c == '@') r += ['@', '.']
		else r = r += ['.', '.']
	}
	map << r
}

def inst = input[1].replace('\n', '').split('')
def moves = ['^': [-1, 0], '>': [0, 1], 'v': [1, 0], '<': [0, -1]]
def changes = []

def movable
movable = {y, x, offY, offX ->
	def next = map[y][x]
	if (next == '#') return false
	if (next == '.') return true
	if (offY == 0) {
		if (movable(y + offY, x + offX, offY, offX)) {
			map[y + offY][x + offX] = next
			return true
		}
		false
	} else if (next == '[') {
		if (movable(y + offY, x + offX, offY, offX) && movable(y + offY, x + offX + 1, offY, offX)) {
			changes = changes += [
					[ y: y + offY, x: x + offX, v: '[' ],
					[ y: y + offY, x: x + offX + 1, v: ']' ],
					[ y: y, x: x, v: '.' ],
					[ y: y, x: x + 1, v: '.' ],
			]
			return true
		}
		false
	} else {
		if (movable(y + offY, x + offX, offY, offX) && movable(y + offY, x + offX - 1, offY, offX)) {
			changes = changes += [
					[ y: y + offY, x: x + offX - 1, v: '[' ],
					[ y: y + offY, x: x + offX, v: ']' ],
					[ y: y, x: x - 1, v: '.' ],
					[ y: y, x: x, v: '.' ],
			]
			return true
		}
		false
	}
}

inst.each {
	changes = []
	def m = moves[it]
	def y = map.findIndexOf {it.contains('@')}
	def x = map[y].findIndexOf {it == '@'}
	if (movable(y + m[0], x + m[1], m[0], m[1])) {
		changes.sort { a, b ->
			if (a.v == '.' && b.v != '.') return -1
			if (a.v !== '.' && b.v == '.') return 1
			0
		}
		changes.each { map[it.y][it.x] = it.v }

		map[y][x] = '.'
		map[y + m[0]][x + m[1]] = '@'
	}
}

def total = 0
for (i in 0..<map.size()) {
	for (j in 0..<map[i].size()) {
		def curr = map[i][j]
		if (curr == '[') total += i * 100 + j
	}
}
println total

