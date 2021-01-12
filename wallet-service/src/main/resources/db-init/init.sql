CREATE TABLE IF NOT EXISTS wallet (
	id serial PRIMARY KEY,
	username varchar(32) NOT NULL,
	currency varchar(4) NOT NULL,
	amount double PRECISION DEFAULT 0,
	frozen double PRECISION DEFAULT 0
);
CREATE UNIQUE INDEX IF NOT EXISTS idx_username ON wallet (username);


CREATE TABLE IF NOT EXISTS wallet_change_log (
	id serial PRIMARY KEY,
	wallet_id integer NOT NULL,
	amount double PRECISION NOT NULL,
	"action" smallint NOT NULL,
	status varchar(32) NOT NULL,
	request_id varchar(64) NOT NULL,
	submit_time bigint NOT NULL,
	last_update_time bigint
);

INSERT INTO public.wallet (username,currency,amount)	VALUES ('ming01','USD',20000.0) ON CONFLICT DO NOTHING;




INSERT INTO wallet_change_log (wallet_id, "action", amount, status, request_id, submit_time)
VALUES (
    1234,
    1,
    1234.567,
    'COMPLETED',
    'teadsa-1234',
    (extract(epoch from now() at time zone 'UTC')*1000)::bigint
);
