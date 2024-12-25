def input = new File('input.txt').text.split('\n\n')
def h = input[0].readLines().size() - 2
def l = []
def k = []
input.each {
	it = it.split('\n')
	def hts = []
	if (it[0][0] == '#') {
		for (x in 0..<it[0].size()) {
			def y = 0
			while (it[y][x] == '#') {
				y++
			}
			hts << y - 1
		}
		l << hts
	} else if (it[0][0] == '.') {
		for (x in 0..<it[0].size()) {
			def y = it.size() - 1
			def counter = -1
			while (it[y][x] == '#') {
				y--
				counter++
			}
			hts << counter
		}
		k << hts
	}
}
def res = 0
for (lock in l) {
	for (key in k) {
		def m = false
		for (def i = 0; i < lock.size(); i++) {
			if (lock[i] + key[i] > h) {
				m = true
				break
			}
		}
		if (m) {
			m = false
		} else {
			res++
		}
	}
}
println res