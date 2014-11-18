SELECT
	datum AS Date,
	extract(year FROM datum) AS Year,
	extract(month FROM datum) AS Month,
	extract(day FROM datum) AS Day
	
FROM (
	-- There are 3 leap years in this range, so calculate 365 * 10 + 3 records
	SELECT '2000-01-01'::DATE + sequence.day AS datum
	FROM generate_series(0,3652) AS sequence(day)
	GROUP BY sequence.day
     ) 
ORDER BY 1;
