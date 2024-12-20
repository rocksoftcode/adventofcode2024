def input = new File('input.txt').readLines()
def result = 0
def numRows = input.size()
def numCols = input[0].size()
def cur = [x: 0, y: 0]
top:
for (def i = 0; i < numRows; i++) {
    for (def j = 0; j < numCols; j++) {
        if (input[i][j] == 'S') {
            cur.x = j
            cur.y = i
            break top
        }
    }
}
def d = []
numRows.times {i -> d[i] = []; numCols.times {d[i] << -1}}
d[cur.y][cur.x] = 0
while (input[cur.y][cur.x] != 'E') {
    for (dir in ([[0, -1], [1, 0], [0, 1], [-1, 0]])) {
        def x = dir[0], y = dir[1]
        def nx = cur.x + x
        def ny = cur.y + y
        if (ny < 0 || nx < 0 || ny >= numRows || nx >= numCols) continue
        if (input[ny][nx] == "#") continue
        if (d[ny][nx] != -1) continue
        d[ny][nx] = d[cur.y][cur.x] + 1
        cur.x = nx
        cur.y = ny
    }
}

for (i in 0..<input.size()) {
    for (j in 0..<input[0].size()) {
        if (input[i][j] == '#') continue;
        for (def r = 2; r < 21; r++) {
            for (def dy = 0; dy < r+1; dy++) {
                def dx = r - dy
                def cdirs = [
                    [i + dy, j + dx],
                    [i + dy, j - dx],
                    [i - dy, j + dx],
                    [i - dy, j - dx]
                ] as Set
                for (dir in cdirs) {
                    def ny = dir[0], nx = dir[1]
                    if (nx < 0 || ny < 0 || nx >= input[0].length() || ny >= input.size()) continue
                    if (input[ny][nx] == '#') continue
                    if ((d[i][j] - d[ny][nx]) >= 100+r) result++
                }
            }
        }
    }
}

println result