def input = new File('input.txt').readLines()
def antennae = [:];
input.eachWithIndex { row, y ->
	row.eachWithIndex { c, x ->
		if (c != '.') {
			if (!antennae[c]) antennae[c] = []
			antennae[c] << [x, y]
		}
	}
}

def antis = [] as Set
antennae.values().each {
	for (i in 0..<it.size()) {
		for (def j=i+1; j < it.size(); j++) {
			def x1 = it[i][0]
			def y1 = it[i][1]
			def x2 = it[j][0]
			def y2 = it[j][1]
			antis << [2*x1-x2, 2*y1-y2]
			antis << [2*x2-x1, 2*y2-y1]
		}
	}
}
def count = 0
antis.each {
	def x = it[0], y = it[1]
	if (x >= 0 && x < input[0].size() && y >= 0 && y < input.size()) count++
}
println count
