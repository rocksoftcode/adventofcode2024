def reports = new File("input.txt").readLines().collect { it.split(/\s/)*.toInteger() }
def check = {
	def deltaDir = []
	for (i in 0..<it.size() - 1) {
		def diff = Math.abs(it[i] - it[i + 1])
		deltaDir << (it[i] > it[i+1])
		if (!(diff in [1, 2, 3])) {
			return true
		}
	}
	return deltaDir.unique().size() > 1
}
def passes = reports.findAll { !check(it) }.size()
def failures = reports.findAll(check)
println passes + failures.count {
	for (i in 0..<it.size()) {
		def readings = it.clone()
		readings.removeAt(i)
		if (!check(readings)) {
			return true
		}
	}
	return false
}