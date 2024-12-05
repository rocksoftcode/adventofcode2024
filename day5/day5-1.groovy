def input = new File('input.txt').text.split('\n\n')*.readLines()
def rules = [:]
input[0]*.split(/\|/).collect {[it[0].toInteger(), it[1].toInteger()]}.each {
	if (!rules[it[0]]) rules[it[0]] = [it[1]]
	else rules[it[0]] << it[1]
}
def updates = input[1]*.split(',').collect {it.toList()*.toInteger()}
println updates.withIndex().findAll {
	def pages = it.v1
	for (i in 0..<pages.size()) {
		def currentPage = pages[i]
		def mustBeBefore = rules[currentPage] ?: []
		for (j in 0..<mustBeBefore.size()) {
			def location = pages.findIndexOf {it == mustBeBefore[j]}
			if (location >= 0 && location < i) return false
		}
	}
	true
}*.v1.sum { it[((it.size() - 1) / 2)] }