def input = []
new File('input.txt').text.eachMatch(/\d+/) {input << it.toLong() }
input.pop()
def initB = input.pop()
def initC = input.pop()
def inst = []
for (def i = 0; i < input.size(); i += 2) inst << [input[i], input[i + 1]]

def run = {a, b, c ->
	def register = [a, b, c]
	def p = 0
	def o = []
	while (p < inst.size()) {
		def code = inst[p][0], op = inst[p][1]
		def l = op
		def cm = op < 4 ? op : register[op - 4]


		if (code == 0) {
			register[0] = Math.floor(register[0] / (Math.pow(2, cm))).toLong()
			p++
		} else if (code == 1) {
			register[1] = (register[1] ^ l) >>> 0
			p++
		} else if (code == 2) {
			register[1] = cm % 8
			p++
		} else if (code == 3) {
			if (register[0] != 0) p = l
			else p++
		} else if (code == 4) {
			register[1] = (register[1] ^ register[2]) >>> 0
			p++
		} else if (code == 5) {
			o << (cm % 8)
			p++
		} else if (code == 6) {
			register[1] = Math.floor(register[0] / (Math.pow(2, cm))).toLong()
			p++
		} else if (code == 7) {
			register[2] = Math.floor(register[0] / (Math.pow(2, cm))).toLong()
			p++
		}
	}
	o.join(',')
}

def poss = [[r: '', l: 0]]
while (poss) {
	def q = poss.pop()
	if (q.l == input.size()) {
		println Long.parseLong(q.r, 2)
		return
	}
	def from = Long.parseLong(q.r + '000', 2)
	def to = Long.parseLong(q.r + '111', 2)
	def expect = input[(q.l + 1) * -1..input.size()-1].join(',')
	for (def a = from; a <= to; a++) {
		def r = run(a, initB, initC)
		if (r == expect) {
			poss << [r: Long.toBinaryString(a), l: q.l + 1]
		}
	}
}
