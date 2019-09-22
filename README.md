# HibernateFunctions

CREATE FUNCTION `fn_count_phones` (personId integer)
RETURNS INTEGER
	BEGIN
		DECLARE phoneCount integer; 
        SELECT COUNT(*) INTO phoneCount 
        FROM Phone p 
        WHERE p.person_id = personId; 
        RETURN phoneCount;
	END
