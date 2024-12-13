def input = new File('input.txt').text.split(/\n\n/)
		.collect { it.split(/\n/) }.collect {
			def a = it[0].replace('Button A: ', '').split(', ')
			def b = it[1].replace('Button B: ', '').split(', ')
			def prize = it[2].replace('Prize: ', '').split(', ')
			[
					a: [
							x: new BigInteger(a[0].replace('X', '')),
							y: new BigInteger(a[1].replace('Y', ''))
					],
					b: [
							x: new BigInteger(b[0].replace('X', '')),
							y: new BigInteger(b[1].replace('Y', ''))
					],
					prize: [
							x: new BigInteger(prize[0].replace('X=', '')),
							y: new BigInteger(prize[1].replace('Y=', ''))
					],
			]
		}
def all = new BigInteger(0L)
input.each {
	it.prize.x += new BigInteger("10000000000000")
	it.prize.y += new BigInteger("10000000000000")
	def a = (it.prize.x * it.b.y - it.prize.y * it.b.x) / (it.a.x * it.b.y - it.a.y * it.b.x)
	def b = (it.prize.x - it.a.x * a) / it.b.x
	if (a % 1 == 0 && b % 1 == 0) {
		all += a * 3 + b
	}
}
println all