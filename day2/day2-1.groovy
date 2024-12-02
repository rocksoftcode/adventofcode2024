def reports = new File("input.txt").readLines().collect { it.split(/\s/)*.toInteger() }
println reports.count {
	def deltaDir = []
	for (i in 0..<it.size() - 1) {
		def diff = Math.abs(it[i] - it[i + 1])
		deltaDir << (it[i] > it[i+1])
		if (!(diff in [1, 2, 3])) {
			return false
		}
	}
	return deltaDir.unique().size() == 1
}