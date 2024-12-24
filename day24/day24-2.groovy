def input = new File('input.txt').text.split(/\n\n/)
def vals = input[0].readLines()*.split(': ').collectEntries()
def lines = input[1].readLines()
def ops = lines.collect {
	def parts = it.split(/\s/)
	[p1: parts[0], o: parts[1], p2: parts[2], t: parts[4]]
}
def orig = lines.collect {
	def parts = it.split(/\s/)
	[p1: parts[0], o: parts[1], p2: parts[2], t: parts[4]]
}
def x = {it.substring(1).toInteger()}
def minKey = vals.keySet().collect(x).min(), maxKey = vals.keySet().collect(x).max() + 1
def gates = []

while (ops) {
	def op = ops.pop()

	if (!(vals[op.p1] && vals[op.p2])) {
		ops << op
		continue
	}

	if (op.o == 'XOR') vals[op.t] = vals[op.p1] != vals[op.p2] ? '1' : '0'
	if (op.o == 'OR') vals[op.t] = vals[op.p1] == '1' || vals[op.p2] == '1' ? '1' : '0'
	if (op.o == 'AND') vals[op.t] = vals[op.p1] == '1' && vals[op.p2] == '1' ? '1' : '0'

	if ((op.t[0] == 'z' && op.o != 'XOR' && op.t.substring(1).toInteger() < maxKey) ||
			(op.t[0] != 'z' && !(op.p1[0] in ['x', 'y']) && !(op.p2[0] in ['x', 'y']) && op.o == 'XOR') ||
			(op.p1[0] in ['x', 'y'] && op.p1.substring(1).toInteger() > minKey && op.p2[0] in ['x', 'y'] && op.p2.substring(1).toInteger() > minKey && op.o == 'XOR' && !orig.any {it.o == 'XOR' && (it.p1 == op.t || it.p2 == op.t)}) ||
			(op.p1[0] in ['x', 'y'] && op.p1.substring(1).toInteger() > minKey && op.p2[0] in ['x', 'y'] && op.p2.substring(1).toInteger() > minKey && op.o == 'AND' && !orig.any {it.o == 'OR' && (it.p1 == op.t || it.p2 == op.t)}))
		gates << op.t
}

println gates.sort().join(',')