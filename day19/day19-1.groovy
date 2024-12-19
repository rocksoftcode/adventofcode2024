def input = new File('input.txt').text.trim().split('\n\n')
def towels = input[0].split(', ').toList()
def wants = input[1].readLines()
def mem = ['': 1]

def search
search = { towel, p, count = 0 as long ->
	if (mem[p] != null) {
		return mem[p]
	}
	for (t in towel) {
		if (p.substring(0, Math.min(p.length(), t.length())) == t)
			count += search(towel, p.substring(t.length()))
	}
	mem[p] = count
	count
}

println wants.collect { search(towels, it) }.findAll { it > 0L }.size()
