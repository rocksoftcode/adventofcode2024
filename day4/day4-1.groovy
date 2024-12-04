def input = new File('input.txt').readLines()
def target = ['X', 'M', 'A', 'S']
def ordinals = [
    n: [-1, 0],
		ne: [-1, 1],
    e: [0, 1],
		se: [1, 1],
		s: [1, 0],
		sw: [1, -1],
		w: [0, -1],
		nw: [-1, -1]
]
def boundY = input.size() - 1
def boundX = input[0].length() - 1

def scan
scan = { x, y, index, ordinal ->
	def count = 0
	def stepY = y + ordinal[0]
	def stepX = x + ordinal[1]
	def letter = index + 1
	if (letter >= target.size())
		return count
	if (stepX < 0 || stepY < 0)
		return count
	if (stepX > boundX || stepY > boundY)
		return count
	if (input[stepY][stepX] == target[letter]) {
		if (letter == target.size()-1) {
			count = 1
		} else {
			count = scan(stepX, stepY, letter, ordinal)
		}
	}
	count
}

println ((0..boundY).sum { y ->
	(0..boundX).sum {x ->
		if (input[y][x] == target[0]) return ordinals*.value.sum { scan(x, y, 0, it) }
		return 0
	}
})
