def input = new File('input.txt').text.split(/\n\n/)
def vals = input[0].readLines()*.split(': ').collectEntries()
def ops = input[1].readLines()

while (ops) {
	def op = ops.pop()
	def parts = op.split(/\s/)
	def p1 = parts[0], o = parts[1], p2 = parts[2], t = parts[4]

	if (!(vals[p1] && vals[p2])) {
		ops << op
		continue
	}

	if (o == 'XOR') vals[t] = vals[p1] != vals[p2] ? '1' : '0'
	if (o == 'OR') vals[t] = vals[p1] == '1' || vals[p2] == '1' ? '1' : '0'
	if (o == 'AND') vals[t] = vals[p1] == '1' && vals[p2] == '1' ? '1' : '0'
}

println new BigInteger(vals.keySet().findAll { it.startsWith('z') }
		.sort { a, b -> a.replace('z', '').toInteger().compareTo(b.replace('z', '').toInteger()) * -1 }
		.collect { vals[it] }.join(''), 2)
