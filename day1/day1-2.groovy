def data = new File("input.txt").text.readLines()
		.collect { it.split(/\s+/)*.toInteger() }
def side1 = data.collect { it[0] }
def side2 = data.collect { it[1] }
println side1.collect { t -> t * side2.count { it == t } }.sum()