def input = new File('input.txt').readLines()
def at = { y, x -> y < 0 || y >= input.size() || x < 0 || x >= input[0].length() ? '' : input[y][x] }
println ((0..input.size() - 1).sum {y ->
	(0..input[0].length() - 1).sum {x ->
		if (input[y][x] != 'A') return 0
		def s1 = at(y-1, x-1)
		def s2 = at(y-1, x+1)
		def s3 = at(y+1, x-1)
		def s4 = at(y+1, x+1)
		if ((s2 == 'M' && s3 == 'S' && s4 == 'M' && s1 == 'S') ||
						(s1 == 'M' && s4 == 'S' && s2 == 'M' && s3 == 'S') ||
						(s1 == 'M' && s4 == 'S' && s3 == 'M' && s2 == 'S') ||
						(s4 == 'M' && s1 == 'S' && s3 == 'M' && s2 == 'S'))
			return 1
		0
	}
})