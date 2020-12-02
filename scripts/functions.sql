--------------------------------------------------------------------------------------------------------------
-- get factory workers
CREATE OR REPLACE FUNCTION get_Workers(id_factory integer)
    RETURNS TABLE
            (
                name       varchar(32),
                surname    varchar(32),
                birth_date date,
                contacts   varchar(32),
                address    text,
                salary     decimal(8, 2)
            )
AS
$$
SELECT humans.name, humans.surname, humans.birth_date, humans.contacts, humans.address, providers.salary
FROM humans
         JOIN providers ON providers.id_human = humans.id_human
WHERE providers.id_factory = get_Workers.id_factory;
$$ LANGUAGE sql;
--------------------------------------------------------------------------------------------------------------


--------------------------------------------------------------------------------------------------------------
-- get clients for provider
CREATE OR REPLACE FUNCTION get_clients_for_provider(id_provider integer, OUT name varchar, OUT surname varchar) RETURNS SETOF record AS
$$
BEGIN
    SELECT h.name, h.surname
    FROM humans AS h
    WHERE (h.id_human IN (SELECT c.id_human
                          FROM clients AS c
                          WHERE (c.id_delivery_place =
                                 (SELECT p.id_delivery_place FROM providers AS p WHERE (p.id_provider = $1)))));
END;
$$ LANGUAGE plpgsql;
---------------------------------------------------------------------------------------------------------------


--------------------------------------------------------------------------------------------------------------
-- get clients for factories
CREATE OR REPLACE FUNCTION get_clients_for_factory(id_factory integer, OUT name varchar, OUT surname varchar) RETURNS SETOF record AS
$$
BEGIN
    SELECT h.name, h.surname
    FROM humans AS h
    WHERE (h.id_human IN (SELECT c.id_human
                          FROM clients AS c
                          WHERE (c.id_delivery_place IN
                                 (SELECT p.id_delivery_place FROM providers AS p WHERE (p.id_factory = $1)))));
END;
$$ LANGUAGE plpgsql;