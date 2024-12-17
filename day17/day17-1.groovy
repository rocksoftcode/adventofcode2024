def input = []
new File('input.txt').text.eachMatch(/\d+/) {input << it.toInteger()}
def register = [input.pop(), input.pop(), input.pop()]
def inst = []
for (def i = 0; i < input.size(); i += 2) inst << [input[i], input[i + 1]]
def p = 0
def o = []
while (p < inst.size()) {
	def code = inst[p][0], op = inst[p][1]
	def l = op
	def c = op < 4 ? op : register[op - 4]


	if (code == 0) {
		register[0] = Math.floor(register[0] / (Math.pow(2, c))).toInteger()
		p++
	} else if (code == 1) {
		register[1] = (register[1] ^ l) >>> 0
		p++
	} else if (code == 2) {
		register[1] = c % 8
		p++
	} else if (code == 3) {
		if (register[0] != 0) p = l
		else p++
	} else if (code == 4) {
		register[1] = (register[1] ^ register[2]) >>> 0
		p++
	} else if (code == 5) {
		o << (c % 8)
		p++
	} else if (code == 6) {
		register[1] = Math.floor(register[0] / (Math.pow(2, c))).toInteger()
		p++
	} else if (code == 7) {
		register[2] = Math.floor(register[0] / (Math.pow(2, c))).toInteger()
		p++
	}
}


println o.join(',')