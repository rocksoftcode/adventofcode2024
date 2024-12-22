def input = new File('input.txt').readLines()*.toLong()
def nextRand = { num ->
	def mul = num * 64L
	num = (mul ^ num) >>> 0L
	num = num % 16777216L
	def rd = Math.floor(num / 32).toLong()
	num = (rd ^ num) >>> 0L
	num = num % 16777216L
	mul = num * 2048L
	num = (mul ^ num) >>> 0L
	num % 16777216L
}

println input.inject(0L) { res, num ->
	for (i in 0..<2000) num = nextRand(num)
	res + num
}

