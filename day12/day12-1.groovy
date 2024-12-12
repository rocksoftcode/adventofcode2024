def field = new File('input.txt').readLines().collect { it.split('').toList() }
def v = [] as Set
def inbounds = { pos -> pos[0] >= 0 && pos[0] < field.size() && pos[1] >= 0 && pos[1] < field[pos[0]].size() }
def at = { pos -> pos && inbounds(pos) ? field[pos[0]][pos[1]] : '' }
def traverse
traverse = { pos, previous, plan ->
	def x = pos[0], y = pos[1]
	def k = x + ':' + y
	if (previous && at(previous) != at(pos)) return
	if (v.contains(k)) return
	v << k

	def n = at([x - 1, y]) == at(pos) ? 0 : 1
	def e = at([x, y + 1]) == at(pos) ? 0 : 1
	def s = at([x + 1, y]) == at(pos) ? 0 : 1
	def w = at([x, y - 1]) == at(pos) ? 0 : 1
	plan[k] = s + e + n + w
	return traverse([x + 1, y], pos, plan) || traverse([x - 1, y], pos, plan) || traverse([x, y + 1], pos, plan) || traverse([x, y - 1], pos, plan);
}
def plot = {pos ->
	def plan = [:]
	traverse(pos, null, plan)
	def count = plan.values().sum()
	return [area: plan.size(), perimeter: count]
}

def result = 0
for (i in 0..<field.size()) {
	for (j in 0..<field[0].size()) {
		if (!v.contains(i + ':' + j)) {
			def chunk = plot([i, j])
			result += chunk.area * chunk.perimeter
		}
	}
}

println result