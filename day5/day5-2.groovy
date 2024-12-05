def input = new File('input.txt').text.split('\n\n')*.readLines()
def rules = [:]
input[0]*.split(/\|/).collect {[it[0].toInteger(), it[1].toInteger()]}.each {
	if (!rules[it[0]]) rules[it[0]] = [it[1]]
	else rules[it[0]] << it[1]
}
def updates = input[1]*.split(',').collect {it.toList()*.toInteger()}
def invalid = updates.withIndex().findAll {
	def pages = it.v1
	for (i in 0..<pages.size()) {
		def currentPage = pages[i]
		def mustBeBefore = rules[currentPage] ?: []
		for (j in 0..<mustBeBefore.size()) {
			def location = pages.findIndexOf {it == mustBeBefore[j]}
			if (location >= 0 && location < i) return true
		}
	}
	false
}*.v1
println invalid.collect { pages ->
	pages.sort {p1, p2 ->
		def mustBeBefore = rules[p1] ?: []
		mustBeBefore.contains(p2) ? -1 : 1
	}
}.sum { it[((it.size() - 1) / 2)] }