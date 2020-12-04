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
WHERE providers.id_factory = $1;
$$ LANGUAGE sql;
--------------------------------------------------------------------------------------------------------------


--------------------------------------------------------------------------------------------------------------
-- get clients for provider
CREATE OR REPLACE FUNCTION get_clients_for_provider(id_provider integer)
    RETURNS TABLE
            (
                name    varchar(32),
                surname varchar(32)
            )
AS
$$
SELECT humans.name, humans.surname
FROM humans
WHERE (humans.id_human IN (SELECT clients.id_human
                           FROM clients
                           WHERE (clients.id_delivery_place =
                                  (SELECT p.id_delivery_place FROM providers AS p WHERE (p.id_provider = $1)))));
$$ LANGUAGE sql;
---------------------------------------------------------------------------------------------------------------


--------------------------------------------------------------------------------------------------------------
-- get clients for factories
CREATE OR REPLACE FUNCTION get_clients_for_factory(id_factory integer)
    RETURNS TABLE
            (
                name    varchar(32),
                surname varchar(32)
            )
AS
$$
SELECT humans.name, humans.surname
FROM humans
WHERE (humans.id_human IN (SELECT clients.id_human
                           FROM clients
                           WHERE (clients.id_delivery_place IN (SELECT providers.id_delivery_place
                                                                FROM providers
                                                                WHERE (providers.id_factory = $1)))));
$$
    LANGUAGE sql;
---------------------------------------------------------------------------------------------------------------

