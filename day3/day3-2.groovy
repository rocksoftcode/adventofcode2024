def instructions = new File("input.txt")
		.text
		.findAll(/(mul\(\d+,\d+\))|do\(\)|don't\(\)/)
def sum = 0
def on = true
instructions.each {
	if (it == 'don\'t()') on = false
	if (it == 'do()') on = true
	if (on && it.startsWith('mul(')) {
		def parts = it.replace('mul(', '')
				.replace(')', '')
				.split(',')*.toInteger()
		sum += parts[0] * parts[1]
	}
}
println sum