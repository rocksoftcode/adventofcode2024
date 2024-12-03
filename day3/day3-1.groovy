println new File("input.txt")
		.text
		.findAll(/(mul\(\d+,\d+\))/)
		.collect { it.replace('mul(', '')
				.replace(')', '')
				.split(',')
		}.collect { it[0].toInteger() * it[1].toInteger() }
		.sum()