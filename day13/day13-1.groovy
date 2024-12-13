def input = new File('input.txt').text.split(/\n\n/)
		.collect { it.split(/\n/) }.collect {
			def a = it[0].replace('Button A: ', '').split(', ')
			def b = it[1].replace('Button B: ', '').split(', ')
			def prize = it[2].replace('Prize: ', '').split(', ')
			[
					a: [
							x: Integer.parseInt(a[0].replace('X', '')),
							y: Integer.parseInt(a[1].replace('Y', ''))
					],
					b: [
							x: Integer.parseInt(b[0].replace('X', '')),
							y: Integer.parseInt(b[1].replace('Y', ''))
					],
					prize: [
							x: Integer.parseInt(prize[0].replace('X=', '')),
							y: Integer.parseInt(prize[1].replace('Y=', ''))
					],
			]
		}
def all = 0
input.each {
	def min = Integer.MAX_VALUE
	for (pressA in 0..<100) {
		for (pressB in 0..<100) {
			def rx = pressA * it.a.x + pressB * it.b.x
			def ry = pressA * it.a.y + pressB * it.b.y
			if (rx == it.prize.x && ry == it.prize.y) {
				def total = pressA * 3 + pressB
				if (total < min) {
					min = total
				}
			}
		}
	}
	if (min != Integer.MAX_VALUE) {
		all += min
	}
}
println all