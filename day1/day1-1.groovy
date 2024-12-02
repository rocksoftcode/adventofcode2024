def data = new File("input.txt").text.readLines()
		.collect { it.split(/\s+/)*.toInteger() }
def side1 = data.collect { it[0] }.sort()
def side2 = data.collect { it[1] }.sort()
println side1.withIndex().collect { Math.abs(it.v1 - side2[it.v2]) }.sum()