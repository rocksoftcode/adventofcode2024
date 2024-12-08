def input = new File('input.txt').readLines()
def antennae = [:]
input.eachWithIndex { line, y ->
	line.eachWithIndex { c, x ->
		if (c != '.') {
			if (!antennae[c]) antennae[c] = []
			antennae[c] << [x, y]
		}
	}
}

def antis = [] as Set
antennae.values().each {
	for (i in 0..<it.size()) {
		for (j in 0..<it.size()) {
			if (i == j) continue
			def x1 = it[i][0]
			def y1 = it[i][1]
			def x2 = it[j][0]
			def y2 = it[j][1]
			def dx = x2 - x1
			def dy = y2 - y1
			def x = x1
			def y = y1
			while (0 <= y && y < input.size() && 0 <= x && x < input[0].size()) {
				antis << [x,y]
				x += dx
				y += dy
			}
		}
	}
}
def count = 0
antis.each {
	def x = it[0], y = it[1]
	if (x >= 0 && x < input[0].size() && y >= 0 && y < input.size()) count++
}
println count